CREATE TABLE student
(
studentId int NOT NULL,
studentName char(20) ,
classId INT ,
PRIMARY KEY (studentId),
FOREIGN KEY (classId) REFERENCES class(classId)
);