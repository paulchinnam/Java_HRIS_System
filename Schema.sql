-- If you already have this schema it drops it before processing the rest.
DROP SCHEMA IF EXISTS hris; 
CREATE SCHEMA hris;
-- This tells mySQL to use the hris scehema so that it isn't required to be specified the rest of the script.
USE hris;

-- Creates a table for employees
CREATE TABLE Employee (
  employee_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  position VARCHAR(45) NOT NULL);
  
-- Creates a table for Payroll
CREATE TABLE Payroll (
employee_id INT NOT NULL,
pay_rate DOUBLE NOT NULL,
benefit_id INT NOT NULL,
FOREIGN KEY(employee_id) REFERENCES Employee(employee_id));

-- Creates a table for benefits
CREATE TABLE Benefits (
benefit_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
health_plan VARCHAR(45) NOT NULL,
vision_plan VARCHAR(45) NOT NULL,
dental_plan VARCHAR(45) NOT NULL);

-- Inserts benefits into the database
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('none', 'none', 'none');
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('Basic', 'none', 'none');
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('Basic', 'Basic', 'none');
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('Basic', 'Basic', 'Basic');
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('none', 'Basic', 'none');
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('none', 'Basic', 'Basic');
INSERT INTO Benefits (health_plan, vision_plan, dental_plan) VALUES ('none', 'none', 'Basic');

-- Inserts employees into the database
INSERT INTO Employee (first_name, last_name, position) VALUES ('Alex', 'Hardwick','Secretary' );
INSERT INTO Payroll VALUES (1,  42, 7);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Penelope', 'Easton','HR Lead' );
INSERT INTO Payroll VALUES (2,  31, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Nick', 'Irvine','SCRUM Master' );
INSERT INTO Payroll VALUES (3,  32, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jacob', 'Brant','Business Analyst' );
INSERT INTO Payroll VALUES (4,  50, 5);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Steve', 'Garret','Accountant' );
INSERT INTO Payroll VALUES (5,  50, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('John', 'Smith','HR Lead' );
INSERT INTO Payroll VALUES (6,  32, 7);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Chris', 'Blackbourne','SCRUM Master' );
INSERT INTO Payroll VALUES (7,  37, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Edward', 'Brant','HR Lead' );
INSERT INTO Payroll VALUES (8,  32, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Susan', 'Niles','Manager' );
INSERT INTO Payroll VALUES (9,  24, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Chris', 'Irvine','HR Lead' );
INSERT INTO Payroll VALUES (10,  27, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Matthew', 'Howard','Accountant' );
INSERT INTO Payroll VALUES (11,  42, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Alex', 'Lyons','Secretary' );
INSERT INTO Payroll VALUES (12,  46, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Joseph', 'Ford','Business Analyst' );
INSERT INTO Payroll VALUES (13,  41, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Susan', 'Niles','Manager' );
INSERT INTO Payroll VALUES (14,  29, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jennifier', 'Beck','SCRUM Master' );
INSERT INTO Payroll VALUES (15,  46, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Elizabeth', 'Beck','SCRUM Master' );
INSERT INTO Payroll VALUES (16,  30, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Elizabeth', 'Banks','HR Lead' );
INSERT INTO Payroll VALUES (17,  46, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Penelope', 'Lyons','SCRUM Master' );
INSERT INTO Payroll VALUES (18,  47, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Penelope', 'Beck','Manager' );
INSERT INTO Payroll VALUES (19,  35, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('John', 'Garret','HR Lead' );
INSERT INTO Payroll VALUES (20,  45, 7);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Joseph', 'Hardwick','Engineer' );
INSERT INTO Payroll VALUES (21,  25, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Susan', 'Beck','Business Analyst' );
INSERT INTO Payroll VALUES (22,  25, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('John', 'Ford','HR Lead' );
INSERT INTO Payroll VALUES (23,  20, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Matthew', 'Rayne','Accountant' );
INSERT INTO Payroll VALUES (24,  21, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Chris', 'Rayne','Manager' );
INSERT INTO Payroll VALUES (25,  36, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Susan', 'Evans','Manager' );
INSERT INTO Payroll VALUES (26,  47, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Matthew', 'Niles','Manager' );
INSERT INTO Payroll VALUES (27,  47, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jennifier', 'Niles','Manager' );
INSERT INTO Payroll VALUES (28,  29, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Alex', 'Brant','Developer' );
INSERT INTO Payroll VALUES (29,  26, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Chris', 'Garret','Secretary' );
INSERT INTO Payroll VALUES (30,  36, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Matthew', 'Blackbourne','HR Lead' );
INSERT INTO Payroll VALUES (31,  50, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Steve', 'Ford','Developer' );
INSERT INTO Payroll VALUES (32,  36, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Alex', 'Dalton','Engineer' );
INSERT INTO Payroll VALUES (33,  42, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jimmy', 'Irvine','Business Analyst' );
INSERT INTO Payroll VALUES (34,  29, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Elizabeth', 'Rayne','Accountant' );
INSERT INTO Payroll VALUES (35,  43, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Smith', 'Easton','Engineer' );
INSERT INTO Payroll VALUES (36,  45, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Smith', 'Garret','SCRUM Master' );
INSERT INTO Payroll VALUES (37,  38, 5);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Steve', 'Ford','Manager' );
INSERT INTO Payroll VALUES (38,  27, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('John', 'Brant','SCRUM Master' );
INSERT INTO Payroll VALUES (39,  24, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jennifier', 'Ford','SCRUM Master' );
INSERT INTO Payroll VALUES (40,  29, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jennifier', 'Irvine','Business Analyst' );
INSERT INTO Payroll VALUES (41,  50, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Matthew', 'Banks','Secretary' );
INSERT INTO Payroll VALUES (42,  33, 4);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jimmy', 'Dalton','Manager' );
INSERT INTO Payroll VALUES (43,  47, 5);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Matthew', 'Easton','Manager' );
INSERT INTO Payroll VALUES (44,  36, 1);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jacob', 'Beck','Manager' );
INSERT INTO Payroll VALUES (45,  42, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Smith', 'Banks','Business Analyst' );
INSERT INTO Payroll VALUES (46,  42, 6);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Elizabeth', 'Beck','Business Analyst' );
INSERT INTO Payroll VALUES (47,  37, 7);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Jimmy', 'Banks','Business Analyst' );
INSERT INTO Payroll VALUES (48,  20, 2);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Nick', 'Lyons','Accountant' );
INSERT INTO Payroll VALUES (49,  29, 3);

INSERT INTO Employee (first_name, last_name, position) VALUES ('Daniel', 'Irvine','HR Lead' );
INSERT INTO Payroll VALUES (50,  24, 6);

-- Creates a view for use later in the CRUD application
CREATE VIEW EmployeeInfo AS SELECT employee_id AS ID, CONCAT(first_name, ' ', last_name) AS Name, position AS Position, pay_rate AS 'Pay Rate', health_plan AS "Health Plan",
 vision_plan AS "Vision Plan", dental_plan AS "Dental Plan" FROM Employee
 JOIN Payroll USING(employee_id)
 JOIN Benefits USING(benefit_id);
 
 SELECT * FROM EmployeeInfo;
