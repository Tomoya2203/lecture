-- Courses テーブルにデータを挿入
INSERT INTO Courses (Name) VALUES ('プログラミング第一');
INSERT INTO Courses (Name) VALUES ('データベースシステム');

-- Teachers テーブルにデータを挿入
INSERT INTO Teachers (Name) VALUES ('例題 名前');
INSERT INTO Teachers (Name) VALUES ('氏名 例えば');

-- Students テーブルにデータを挿入
INSERT INTO Students (Name) VALUES ('生徒 ああ');
INSERT INTO Students (Name) VALUES ('いい 学生');

-- Sessions テーブルにデータを挿入
INSERT INTO Sessions (CourseID, Time, Location) VALUES (1, '火曜2限', 'Room 101');
INSERT INTO Sessions (CourseID, Time, Location) VALUES (2, '木曜3限', 'Room 102');
INSERT INTO Sessions (CourseID, Time, Location) VALUES (2, '木曜4限', 'Room 201');

-- Instructors テーブルにデータを挿入
INSERT INTO Instructors (CourseID, TeacherID) VALUES (1, 1);
INSERT INTO Instructors (CourseID, TeacherID) VALUES (1, 2);
INSERT INTO Instructors (CourseID, TeacherID) VALUES (2, 2);

-- Enrollments テーブルにデータを挿入
INSERT INTO Enrollments (CourseID, StudentID) VALUES (1, 1);
INSERT INTO Enrollments (CourseID, StudentID) VALUES (1, 2);
INSERT INTO Enrollments (CourseID, StudentID) VALUES (2, 2);
