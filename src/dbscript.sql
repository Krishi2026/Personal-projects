DROP TABLE "age-calculator".users;

CREATE TABLE "age-calculator".users
(
    username text PRIMARY KEY,
    firstname text NOT NULL,
    lastname text NOT NULL,
    password text  NOT NULL,
    dob date NOT NULL
)



DROP TABLE "age-calculator".users_activity;

CREATE TABLE "age-calculator".users_activity
(
    id SERIAL PRIMARY KEY,
    activitydate timestamp  NOT NULL,
    username text  NOT NULL,
    sessionid text  NOT NULL,
    useraction text  NOT NULL
)


DROP TABLE "age-calculator".users_activity_io;

CREATE TABLE "age-calculator".users_activity_io
(
    id SERIAL PRIMARY KEY,
    refid int  NOT NULL,
    input text  NOT NULL,
    output text  NOT NULL,
    CONSTRAINT fk_activityio
      FOREIGN KEY(refid) 
	  REFERENCES "age-calculator".users_activity(id)
)

insert into "age-calculator".users(username,firstname,lastname,password,dob)values('superuser','Super','User','$31$16$aBafgSE02yxVTXV7YP7ZtUrLeaQI26OvFhZY9_bt4bw',now())