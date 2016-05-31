CREATE SCHEMA IF NOT EXISTS quiz_site_database;
USE quiz_site_database;

drop table if exists users;
drop table if exists correct_answers;

CREATE TABLE IF NOT EXISTS users (
	id int not null auto_increment primary key, -- Could use varchar
    user_name varchar(60) not null,
    email varchar(45) not null,
    password char(40) not null, -- variable type ??
    reg_date timestamp,
    quizes_written int DEFAULT 0,
    
    key(user_name),
    key(email)    
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS correct_answers(
	id int not null auto_increment primary key,
    question_id int not null, 
    correct_answer text, -- May need change of variable type
    
    key(question_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE=utf8_bin;

