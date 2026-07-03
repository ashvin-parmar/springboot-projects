-- 3: third to source

insert into branch (title) values("Electronics & Telecommunication");

insert into semester (title) value("VII");

insert into student (roll_number,first_name,last_name,email_id,password,password_id) values("A10010","Rohit","Solanki","rohittemp@gmail.com","rohitpass","rohitpass");

insert into administrator (username,password,password_id) values("admin","admin","admin");

insert into student_branch_mapping (roll_number,branch_code) values("A10010",1);

insert into student_semester_mapping (roll_number,semester_code) values("A10010",1);
