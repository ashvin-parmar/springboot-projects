create database sb_learn_db;

create user 'sblearnuser1'@'localhost' identified by 'sblearn#User1';

grant all privileges on sb_learn_db.* to 'sblearnuser1'@'localhost' with grant option;
flush privileges;
