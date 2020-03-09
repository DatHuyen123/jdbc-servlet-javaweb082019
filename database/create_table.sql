CREATE DATABASE estate;

use estate;

CREATE TABLE role(
  id bigint NOT NULL PRIMARY KEY auto_increment,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(255) NOT NULL,
  
  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

INSERT INTO role(name,code) VALUES('MANAGER','Quản Lý');
INSERT INTO role(name,code) VALUES('STAFF','Nhân Viên');

CREATE TABLE user (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  username VARCHAR(150) NOT NULL,
  password VARCHAR(150) NOT NULL,
  fullname VARCHAR(150) NULL,
  status int NOT NULL,

  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

CREATE TABLE user_role(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
	roleid BIGINT NOT NULL,
	userid BIGINT NOT NULL
);

ALTER TABLE user_role ADD CONSTRAINT fk_userrole_role FOREIGN KEY (roleid) REFERENCES role(id);
ALTER TABLE user_role ADD CONSTRAINT fk_userrole_user FOREIGN KEY (userid) REFERENCES user(id);

CREATE TABLE building(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NULL,
	numberofbasement INT NULL,
	buildingarea INT NULL,
	district VARCHAR(255) NOT NULL,
	ward VARCHAR(100) NULL,
	street VARCHAR(100) NULL,
	structure VARCHAR(100) NULL,
	costrent INT NULL,
	cosdescription TEXT NULL,
	servicecost VARCHAR(255) NULL,
	carcost VARCHAR(255) NULL,
	motobikecost VARCHAR(255) NULL,
	overtimecost VARCHAR(255) NULL,
	electtricost VARCHAR(255) NULL,
	deposit VARCHAR(255) NULL,
	payment VARCHAR(255) NULL,
	timerent VARCHAR(255) NULL,
	timedecorater VARCHAR(255) NULL,
    timecontract VARCHAR(255) NULL,
	managername VARCHAR(255) NULL,
	managerphone VARCHAR(255) NULL,
	type VARCHAR(255) NULL,
    levelbuilding VARCHAR(255) NULL,
    direction VARCHAR(255) NULL,

	createddate TIMESTAMP NULL,
  	modifieddate TIMESTAMP NULL,
  	createdby VARCHAR(255) NULL,
  	modifiedby VARCHAR(255) NULL
);

ALTER TABLE building ADD COLUMN timecontract VARCHAR(255) NULL AFTER timedecorater;

CREATE TABLE assignmentbuilding(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
	buildingid BIGINT NOT NULL,
	staffid BIGINT NOT NULL
);

ALTER TABLE assignmentbuilding ADD CONSTRAINT fk_assignment_user FOREIGN KEY (staffid) REFERENCES user(id);
ALTER TABLE assignmentbuilding ADD CONSTRAINT fk_assignment_building FOREIGN KEY (buildingid) REFERENCES building(id);

ALTER TABLE assignmentbuilding ADD createddate TIMESTAMP NULL AFTER staffid;
ALTER TABLE assignmentbuilding ADD modifieddate TIMESTAMP NULL AFTER createddate;
ALTER TABLE assignmentbuilding ADD createdby VARCHAR(255) NULL AFTER modifieddate;
ALTER TABLE assignmentbuilding ADD modifiedby VARCHAR(255) NULL AFTER createdby;

CREATE TABLE rentarea(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
	value VARCHAR(255) NOT NULL,
	buildingid BIGINT NOT NULL,

	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

ALTER TABLE rentarea ADD CONSTRAINT fk_rentarea_building FOREIGN KEY (buildingid) REFERENCES building(id);

CREATE TABLE customer
(
	id BIGINT PRIMARY KEY auto_increment,
	fullname VARCHAR(255) NULL,
	email VARCHAR(255) NULL,
	companyname VARCHAR(255) NULL,
	demand TEXT NULL,
	note TEXT NULL,
	status int NOT NULL,

	createddate TIMESTAMP NULL,
  	modifieddate TIMESTAMP NULL,
  	createdby VARCHAR(255) NULL,
  	modifiedby VARCHAR(255) NULL
);
INSERT INTO customer (fullname , email , companyname , demand , note , status) VALUES ('dang van dat' , 'dangdat.09071997@gmail.com' , 'FPT','hello','khong co gi de noi',1);
INSERT INTO customer (fullname , email , companyname , demand , note , status) VALUES ('Dinh Thi Huyen' , 'mrhuyenduc@gmail.com' , 'Ha Noi Univerty','hello','khong co gi de noi',1);

CREATE TABLE assignmentstaff
(
	id BIGINT NOT NULL PRIMARY KEY auto_increment ,
	customerid BIGINT NOT NULL,
	userid BIGINT NOT NULL,

	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

DROP TABLE customer_building;
DROP TABLE customer;

ALTER TABLE assignmentstaff ADD CONSTRAINT fk_assignmentstaff_customer FOREIGN KEY (customerid) REFERENCES customer(id);
ALTER TABLE assignmentstaff ADD CONSTRAINT fk_assignmentstaff_user FOREIGN KEY (userid) REFERENCES user(id);
