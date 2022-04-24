CREATE TABLE Student(
	stdNo		char(5)		PRIMARY KEY,
	login		char(6)		UNIQUE NOT NULL,
	lastName	varchar(25),
	givenNames	varchar(50),
	programCode	char(4)
)

CREATE TABLE Course(
	courseID	char(8)		PRIMARY KEY,
	cName		varchar(25) UNIQUE NOT NULL,
	credits		int			CHECK (credits >= 0 and credits <= 200) DEFAULT 20
)

CREATE TABLE Semester(
	semesterID	int			PRIMARY KEY CHECK (semesterID >= 0),
	semester	int			UNIQUE NOT NULL CHECK (semester >= 1 and semester <= 4),
	year		int			UNIQUE NOT NULL CHECK (year >= 2000 and year <= 9999)
)

CREATE TABLE Register(
	stdNo		char(5)		REFERENCES Student(stdNo) ON UPDATE CASCADE ON DELETE NO ACTION,
	courseID	char(8)		REFERENCES Course(courseId) ON UPDATE CASCADE ON DELETE NO ACTION,
	semesterID	int			REFERENCES Semester(semesterId) ON UPDATE CASCADE ON DELETE NO ACTION,
	grade		char(2),
	mark		decimal(3,2) CHECK (mark >= 0.00 and mark <= 100.00) DEFAULT 0.0
)