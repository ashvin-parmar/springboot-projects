drop table if exists student;

create table student 
(
  id int not null auto_increment,
  first_name varchar(45) default null,
  last_name varchar(45) default null,
  email varchar(45) default null,
  primary key (id)
)engine=InnoDB auto_increment=1 default charset=latin1;

