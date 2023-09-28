CREATE TABLE IF NOT EXISTS "lab-assignment".lab_config
(
    seat_count integer
);


CREATE TABLE IF NOT EXISTS "lab-assignment".lab_session
(
    seat_no integer NOT NULL,
    session_id integer,
    student_no integer,
    CONSTRAINT lab_session_pkey PRIMARY KEY (seat_no)
);

CREATE TABLE IF NOT EXISTS "lab-assignment".lab_session_waiting_list
(
    sequence_no serial NOT NULL ,
	session_id integer,
    student_no integer,
    CONSTRAINT lab_session_waiting_list_pkey PRIMARY KEY (sequence_no)
);


insert into "lab-assignment".lab_config(seat_count) values(25);