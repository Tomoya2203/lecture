-- Courses
INSERT INTO Courses (Name) VALUES ('Programming 1');
INSERT INTO Courses (Name) VALUES ('Database management');

-- Teachers
INSERT INTO Teachers (Name) VALUES ('Example Name);
INSERT INTO Teachers (Name) VALUES ('Name Eg');

-- Students
INSERT INTO Students (Name) VALUES ('Student One');
INSERT INTO Students (Name) VALUES ('Two kids');

-- Sessions
INSERT INTO Sessions (CourseID, Time, Location) VALUES (1, 'Monday3', 'Room 101');
INSERT INTO Sessions (CourseID, Time, Location) VALUES (2, 'Friday3', 'Room 102');
INSERT INTO Sessions (CourseID, Time, Location) VALUES (2, 'Thursday2', 'Room 201');

-- Instructors
INSERT INTO Instructors (CourseID, TeacherID) VALUES (1, 1);
INSERT INTO Instructors (CourseID, TeacherID) VALUES (1, 2);
INSERT INTO Instructors (CourseID, TeacherID) VALUES (2, 2);

-- Enrollments
INSERT INTO Enrollments (CourseID, StudentID) VALUES (1, 1);
INSERT INTO Enrollments (CourseID, StudentID) VALUES (1, 2);
INSERT INTO Enrollments (CourseID, StudentID) VALUES (2, 2);
