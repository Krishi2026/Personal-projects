SET search_path TO "lab-assignment";

create or replace procedure removeSession( sessionid int)
language plpgsql    
as $$
declare
	lab_seat_count integer;
	waiting_students record;
begin
	select count(*)
	   into lab_seat_count
	   from lab_session
	   where session_id = sessionid;
	raise notice 'The number of lab seats: % for session % ', lab_seat_count, sessionid; 
	update lab_session set session_id = NULL, student_no = NULL where session_id = sessionid;
	for waiting_students in select sequence_no, session_id, student_no from lab_session_waiting_list
			order by sequence_no
			limit lab_seat_count
	loop
		raise notice 'Finding seat for student % for session %', waiting_students.student_no, waiting_students.session_id;
		call assign(waiting_students.session_id, waiting_students.student_no, 1);
		delete from lab_session_waiting_list where sequence_no = waiting_students.sequence_no;
	end loop;
end;$$