DROP SCHEMA IF EXISTS quiz_site_database;
CREATE SCHEMA quiz_site_database;
USE quiz_site_database;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS correct_answers;

CREATE TABLE users (
	id int not null auto_increment, -- Could use varchar
    user_name varchar(60) not null,
    email varchar(45) not null,
    password varchar(50) not null, -- May need change of variable type
    reg_date timestamp,
    quizes_written int DEFAULT 0,
    
    primary key (id)
);

CREATE TABLE correct_answers(
	id int not null auto_increment primary key,
    question_id int not null, 
    correct_answer text -- May need change of variable type
);

create table if not exists quizes (
	id int auto_increment primary key,
    title tinytext not null,
    author varchar(60) not null,
    create_time timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

