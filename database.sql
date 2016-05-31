create table if not exists friends(
	user1_id int,
    user2_id int,
    status varchar(50)
);

create table if not exists question_images(
	id int auto_increment,
    question_id int,
    image_url varchar(255),
    primary key(id)
);