CREATE DATABASE sos DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE sos;

CREATE TABLE  User  (
	 u_id 	Integer	NOT NULL	AUTO_INCREMENT,
	 u_nickname 	Varchar(100)	NULL	COMMENT '"익명" + #',
	 u_email 	Varchar(50)	NULL,
	 u_age_range 	Varchar(10)	NULL,
	 u_pic 	Blob	NULL	COMMENT '기본 프로필 이미지',
	 u_status 	Boolean	NOT NULL	DEFAULT True	COMMENT 'True: 비공개 / False: 공개',
	 PRIMARY KEY (u_id)
);

CREATE TABLE  Chat  (
	 c_id 	Integer	NOT NULL	AUTO_INCREMENT,
	 u_id 	Integer	NOT NULL,
	 c_name 	Varchar(50)	NOT NULL,
	 PRIMARY KEY (c_id),
	 FOREIGN KEY (u_id) REFERENCES User(u_id)
);

CREATE TABLE  Counseling_Appointment  (
	 ca_id 	Integer	NOT NULL	AUTO_INCREMENT,
	 ca_name 	Varchar(10)	NOT NULL,
	 ca_contact 	Varchar(30)	NOT NULL,
	 ca_area 	Varchar(30)	NOT NULL,
	 ca_place 	Varchar(50)	NOT NULL,
	 ca_scheduled_date 	Date	NOT NULL,
	 ca_scheduled_time 	Varchar(30)	NOT NULL,
	 ca_response_status Varchar(10)	NOT NULL DEFAULT '답변대기',
	 ca_date 	Datetime	NOT NULL	DEFAULT NOW(),
	 PRIMARY KEY (ca_id)
);

CREATE TABLE  Attachment_File  (
	 pc_file_id 	Integer	NULL	AUTO_INCREMENT,
	 af_file_name 	Varchar(50)	NOT NULL,
	 af_upload_path 	Varchar(100)	NOT NULL,
	 PRIMARY KEY (pc_file_id)
);

CREATE TABLE  Admin  (
	 a_id 	Integer	NOT NULL	AUTO_INCREMENT,
	 a_name 	Varchar(10)	NOT NULL,
	 a_contact 	Varchar(30)	NOT NULL,
	 a_email 	Varchar(50)	NOT NULL,
	 a_pw 	Varchar(10)	NOT NULL,
	 a_admin_type 	Integer	NOT NULL	COMMENT '0: 경찰 / 1:  상담사 / 2: 변호사',
	 a_company_name 	Varchar(50)	NOT NULL,
	 a_company_contact 	Varchar(30)	NOT NULL,
	 a_company_address 	Varchar(100)	NOT NULL,
	 PRIMARY KEY (a_id)
);

CREATE TABLE  Online_Complaint  (
	 oc_id 	Integer	NOT NULL	AUTO_INCREMENT,
	 a_id 	Integer	NULL,
	 oc_pw 	Varchar(10)	NOT NULL,
	 oc_title 	Varchar(50)	NOT NULL,
	 oc_content 	Varchar(500)	NOT NULL,
	 oc_name 	Varchar(10)	NOT NULL,
	 oc_advisor 	Varchar(30)	NOT NULL,
	 oc_contact 	Varchar(30)	NULL,
	 oc_date 	Datetime	NULL	DEFAULT NOW(),
	 oc_response_status 	Varchar(10)	NOT NULL DEFAULT '답변대기',
	 oc_response_content 	Varchar(500)	NULL,
	 PRIMARY KEY (oc_id),
	 FOREIGN KEY (a_id) REFERENCES Admin(a_id)
); 

CREATE TABLE  Police_Complaint  (
	 pc_id 	Integer	NOT NULL	AUTO_INCREMENT,
	 pc_file_id 	Integer	NULL,
	 a_id 	Integer	NULL,
	 pc_pw 	Varchar(10)	NOT NULL,
	 pc_title 	Varchar(50)	NOT NULL,
	 pc_content 	Varchar(500)	NOT NULL,
	 pc_name 	Varchar(10)	NOT NULL,
	 pc_contact 	Varchar(30)	NULL,
	 pc_victim_name 	Varchar(10)	NULL,
	 pc_victim_age 	Varchar(10)	NULL,
	 pc_victim_contact 	Varchar(30)	NULL,
	 pc_victim_other 	Varchar(100)	NULL,
	 pc_bully_name 	Varchar(10)	NULL,
	 pc_bully_relationship 	Varchar(10)	NULL,
	 pc_bully_age 	Varchar(10)	NULL,
	 pc_bully_contact 	Varchar(30)	NULL,
	 pc_bully_other 	Varchar(100)	NULL,
	 pc_date 	Datetime	NOT NULL	DEFAULT NOW(),
	 pc_response_status 	Varchar(10)	NOT NULL DEFAULT '답변대기',
	 pc_response_content 	Varchar(500)	NULL,
	 PRIMARY KEY (pc_id),
	 FOREIGN KEY (pc_file_id) REFERENCES Attachment_File(pc_file_id),
	 FOREIGN KEY (a_id) REFERENCES Admin(a_id)
); 