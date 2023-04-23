--create database
CREATE DATABASE prestapoints
    DEFAULT CHARACTER SET = 'utf8mb4';
--create user..
CREATE USER 'prestapoints'@'localhost' IDENTIFIED BY 'prestapoints';
-- give all privileges to prestapoints database;
GRANT ALL PRIVILEGES ON prestapoints.* TO 'prestapoints'@'localhost' WITH GRANT OPTION;
--flush this user in MYSQL
FLUSH PRIVILEGES;

