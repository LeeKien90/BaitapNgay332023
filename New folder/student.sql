create database student;
use student;
create table Student(
StudentId int auto_increment primary key,
StudentName nvarchar(50),
StudentAge int,
StudentStatus bit
);