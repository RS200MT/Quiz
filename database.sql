create table if not exists friends(
	user1_id int,
    user2_id int,
    status varchar(50)
);

create table if not exists quizes (
	id int auto_increment primary key,
    title tinytext not null,
    author varchar(60) not null,
    create_time timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table if not exists question_images(
	id int auto_increment,
    question_id int,
    image_url varchar(255),
    primary key(id)
);

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
    start_time timestamp,
    end_time timestamp,
    
    key (user_id),
    key (quiz_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
