ROLLBACK;
START TRANSACTION;

DROP TABLE IF EXISTS member, interestGroup, event CASCADE;


-- member table
CREATE TABLE member
(
	memberNumber serial,
	lastName varchar(50) not null,
	firstName varchar(50)not null,
	emailAddress varchar(64) not null,
	phoneNumber varchar(14) null,
	dateOfBirth date not null,
	doesWantEmail boolean not null,
	
	CONSTRAINT pk_member PRIMARY KEY (memberNumber)
	
);

-- interest group table
CREATE TABLE interestGroup
(
	groupNumber serial,
	name varchar(64) not null,
	
	CONSTRAINT pk_interestGroup PRIMARY KEY (groupNumber)
	
);


-- event table
CREATE TABLE event 
(
	eventNumber serial,
	name varchar(64) not null,
	description varchar(200) not null,
	startDate date not null,
	startTime time not null,
	duration int not null, 
	GroupRunningEvent varchar (64) not null,
	
	CONSTRAINT pk_event PRIMARY KEY (eventNumber)
	
);

-- Group/member table

CREATE TABLE group_member (

	member_id int not null,
	group_id int not null,
	
	CONSTRAINT pk_group_member_m PRIMARY KEY (member_id),
	CONSTRAINT fk_group_member_member FOREIGN KEY (member_id) REFERENCES member (memberNumber),
	CONSTRAINT fk_group_member_group FOREIGN KEY (group_id) REFERENCES interestGroup (groupNumber)

);

SELECT * FROM group_member;


CREATE TABLE event_members (

	member_id int not null,
	event_id int not null,
	
	CONSTRAINT pk_event_members_m PRIMARY KEY (member_id),
	CONSTRAINT fk_event_members_member FOREIGN KEY (member_id) REFERENCES member (memberNumber),
	CONSTRAINT fk_event_members_event FOREIGN KEY (event_id) REFERENCES event (eventNumber)
);



COMMIT;
 
 










--ROLLBACK
