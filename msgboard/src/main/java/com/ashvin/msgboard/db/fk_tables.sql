-- 2: Second to source

create table student_branch_mapping
(
roll_number char(15) not null,
branch_code int not null,
primary key (roll_number,branch_code),
foreign key (roll_number) references student(roll_number),
foreign key (branch_code) references branch(code)
);

create table student_semester_mapping
(
roll_number char(15) not null,
semester_code int not null,
primary key (roll_number,semester_code),
foreign key (roll_number) references student(roll_number),
foreign key (semester_code) references semester(code)
);
