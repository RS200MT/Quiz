drop table if exists users;
drop table if exists correct_answers;
drop table if exists quiz_logs;
drop table if exists quizes;
drop table if exists multiple_choices;
drop table if exists question_images;
drop table if exists friends;
drop table if exists questions;


CREATE TABLE IF NOT EXISTS users (
	id int not null auto_increment primary key, -- Could use varchar
    user_name varchar(60) not null,
    email varchar(45) not null,
    password char(40) not null, -- variable type ??
    reg_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quizes_written int DEFAULT 0,
	type int not null default 2,    
    key(user_name),
    key(email)    
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE=utf8_bin;

create table if not exists achievements(
    user_id int not null, 
    type int    
);

CREATE TABLE IF NOT EXISTS correct_answers(
	id int not null auto_increment primary key,
    question_id int not null, 
    correct_answer text, -- May need change of variable type
    
    key(question_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE=utf8_bin;

create table if not exists friends(
	id int not null primary key auto_increment,
	user1_id int,
    user2_id int,
    status int not null,
    key(user1_id),
    key(user2_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



create table if not exists quizes (
	id int auto_increment primary key,
    title tinytext not null,
    description text not null,
    author int not null,
    create_time timestamp DEFAULT CURRENT_TIMESTAMP,
    times_written int DEFAULT 0,
    randomize int default 0,
    immediate_correction int default 0,
    practice_mode int default 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


create table if not exists question_images(
	id int auto_increment,
    question_id int,
    image_url varchar(255),
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table if not exists questions (
	id int auto_increment primary key,
    quiz_id int not null,
    question tinytext not null,
    q_type int not null,
    
    key (quiz_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table if not exists multiple_choices (
	id int auto_increment primary key,
    question_id int not null,
    answer tinytext,
    
    key (question_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table if not exists quiz_logs (
	id int auto_increment primary key,
    user_id int not null,
    quiz_id int not null,
    score int,
    start_time long,
    quizTime BIGINT,
    
    key (user_id),
    key (quiz_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


create table if not exists messages (
	id int primary key auto_increment,
    sender int not null, 
    recipient int not null,
    type int not null,
    message_text text,
    seen int default 0,
    receive_time timestamp not null default now()
);

