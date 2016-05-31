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
