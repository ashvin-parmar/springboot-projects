-- 1: First to source
create table branch
(
code int primary key auto_increment,
title char(50) not null unique
);

create table semester
(
code int primary key auto_increment,
title char(25) not null unique
);

create table student
(
roll_number char(15) primary key,
first_name char(20) not null,
last_name char(20) not null,
email_id char(100) not null unique,
password char(100) not null,
password_id char(100) not null
);

create table administrator
(
username char(15) not null primary key,
password char(100) not null,
password_id char(100) not null
);
