DELETE FROM exam_tasks;
DELETE FROM exam_student;
DELETE FROM exam;
DELETE FROM task;
DELETE FROM Student;
DELETE FROM Topic;
DELETE FROM Zno;

DROP TABLE IF EXISTS exam_tasks CASCADE;
DROP TABLE IF EXISTS exam_student CASCADE;
DROP TABLE IF EXISTS exam CASCADE;
DROP TABLE IF EXISTS task CASCADE;
DROP TABLE IF EXISTS Student CASCADE;
DROP TABLE IF EXISTS Topic CASCADE;
DROP TABLE IF EXISTS Zno CASCADE;
