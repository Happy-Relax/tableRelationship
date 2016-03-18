CREATE TABLE studentToTeacher
(
studentId int ,
teacherId int ,
FOREIGN KEY (studentId) REFERENCES student(studentId),
FOREIGN KEY (teacherId) REFERENCES teacher(teacherId)
);