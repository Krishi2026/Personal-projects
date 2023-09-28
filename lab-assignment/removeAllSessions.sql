SET search_path TO "lab-assignment";

create or replace procedure removeAllSessions()
language plpgsql    
as $$
declare
	lab_seat_count integer;
	waiting_students record;
begin
	select seat_count
	   into lab_seat_count
	   from lab_config;
	raise notice 'The number of lab seats: %', lab_seat_count;  
	update lab_session set session_id = NULL, student_no = NULL;
	for waiting_students in select sequence_no, session_id, student_no from lab_session_waiting_list
			order by sequence_no
			limit lab_seat_count
	loop
		raise notice 'Finding seat for student % for session %', waiting_students.student_no, waiting_students.session_id;
		call assign(waiting_students.session_id, waiting_students.student_no, 1);
		delete from lab_session_waiting_list where sequence_no = waiting_students.sequence_no;
	end loop;
end;$$