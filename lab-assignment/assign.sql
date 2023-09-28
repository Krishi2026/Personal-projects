SET search_path TO "lab-assignment";

create or replace procedure assign(
   sessionid int,
   studentNoFrom int, 
   noOfStudents int
)
language plpgsql    
as $$
declare
	lab_seat_count integer;
	studentNo integer;
	studentNoExist integer;
	selected_seat lab_session%rowtype;
	errorCount integer;
	invalidStudentNo integer[];
begin
	studentNo = studentNoFrom;
	errorCount = 0;
    select seat_count
   into lab_seat_count
   from lab_config;
   raise notice 'The number of lab seats: %', lab_seat_count;
   for counter in 1..lab_seat_count loop
		 select * from lab_session
			into selected_seat
			where seat_no = counter;
  
		  if not found then
			 raise notice 'The seat no % could not be found', counter;
			 
			 select student_no into studentNoExist from lab_session
				where student_no = studentNo; 
			 
			 if not found then
				insert into lab_session(seat_no, session_id, student_no)values(counter, sessionid, studentNo);
				raise notice 'The seat no % assigned to studebt %', counter, studentNo;
				studentNo = studentNo + 1;	
				
				exit when studentNo-studentNoFrom = noOfStudents-errorCount;	
			 else 
				invalidStudentNo = invalidStudentNo || studentNo;
				raise notice 'Student No % already exists in lab', studentNo;
				errorCount = errorCount + 1;
			 end if;
			 
		  else
			 raise notice 'Seat No % Already Exists, session % studebt %', selected_seat.seat_no, selected_seat.session_id, selected_seat.student_no;
			 
			 if selected_seat.session_id is NULL and selected_seat.student_no is NULL then
				 select student_no into studentNoExist from lab_session
					where student_no = studentNo; 
				 
				 if not found then
					update lab_session set session_id = sessionid, student_no = studentNo where seat_no=counter;
					raise notice 'The seat no % assigned to studebt %', counter, studentNo;
					studentNo = studentNo + 1;	
					exit when studentNo-studentNoFrom = noOfStudents-errorCount;	
				 else 
					invalidStudentNo = invalidStudentNo || studentNo;
					raise notice 'Student No % already exists in lab', studentNo;
					errorCount = errorCount + 1;
				 end if;
		    end if;
		 end if;	
		exit when studentNo-studentNoFrom = noOfStudents-errorCount;	
   end loop;
   if studentNo-studentNoFrom < noOfStudents-errorCount then 
		raise notice 'Lab is full!!!';
		for  counter in studentNo..studentNoFrom+noOfStudents-1 loop
				 
			if not counter = ANY (invalidStudentNo) then
				raise notice 'Student % is waiting for lab seat', counter;
				insert into lab_session_waiting_list(session_id, student_no)values(sessionid, counter);
			else 
				raise notice 'Student No % already exists in Waiting List', counter;
			end if;	
		end loop;
   end if;
   
end;$$