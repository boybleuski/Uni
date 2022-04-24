 
drop table Register    
drop table Student
drop table Course
drop table Semester


CREATE TABLE Student (
stdNo		CHAR(5)		PRIMARY KEY,
login		CHAR(10)	UNIQUE	NOT NULL,
lastname	VARCHAR(25),
givenNames	VARCHAR(50),
programCode	CHAR(4))
go

CREATE TABLE Course (
courseID	CHAR(8)		PRIMARY KEY,
cName		VARCHAR(25)	UNIQUE	NOT NULL,
credits		TINYINT		CHECK (credits BETWEEN 0 AND 200) DEFAULT 20)
go

CREATE TABLE Semester (
semesterID	INTEGER		PRIMARY KEY CHECK (semesterID >= 0),
semester	TINYINT			NOT NULL CHECK(semester BETWEEN 0 AND 4),
year		SMALLINT		NOT NULL CHECK(year BETWEEN 2000 AND 9999),
UNIQUE (semester,year))

 

go

CREATE TABLE Register (
stdNo		CHAR(5),
courseID	CHAR(8),
semesterID	INTEGER			REFERENCES Semester ON UPDATE CASCADE ON DELETE NO ACTION,
grade		CHAR(2),
mark		DECIMAL(5,2)	DEFAULT 0.0,
PRIMARY KEY (stdNo, courseID, semesterID),
CONSTRAINT	fkRegisterStd	FOREIGN KEY(stdNo) REFERENCES Student(stdNo) ON UPDATE CASCADE ON DELETE NO ACTION,
FOREIGN KEY(courseID) REFERENCES Course(courseID) ON UPDATE CASCADE ON DELETE NO ACTION)
go


--insert osme data into the tables (as in Wk 6) for more practice
INSERT INTO Student VALUES ('S0001', 'ABI723', 'Ingel', 'Abby Kate', 'BITC');
INSERT INTO Student VALUES ('S0210', 'KWE231', 'Kent', 'Robert', 'BSCS');

INSERT INTO Course VALUES ('INFT2040', 'Database Management', 20);
INSERT INTO Course VALUES ('INFT2132', 'Advance Programming', 20);
INSERT INTO Course VALUES ('INFT4001', 'Project', 20);

INSERT INTO Semester VALUES (1,1, 2006);
INSERT INTO Semester VALUES (2,2, 2006);
INSERT INTO Semester VALUES (3,1, 2008);

INSERT INTO Register VALUES ('S0001', 'INFT2040', 1, 'A', 98.02);
INSERT INTO Register VALUES ('S0001', 'INFT2132', 2, 'B', 80.32);
INSERT INTO Register VALUES ('S0210', 'INFT2132', 2, 'B+', 87.89);
INSERT INTO Register VALUES ('S0210', 'INFT2040', 3, null, null);


--an example to see update:
delete from Semester
where semesterID=2
select * from Semester

