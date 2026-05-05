CREATE DATABASE employeapp;
USE employeapp;

CREATE TABLE employee (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
salary DOUBLE,
joining_date DATE,
position VARCHAR(50)
);
