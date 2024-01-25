CREATE SEQUENCE student_id_sequence
    INCREMENT 20
    START 1;

create table if not exists students
(
    id integer default nextval('student_id_sequence') not null,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    email varchar(128) not null,
    grade varchar(64) not null,
    comment varchar(128) not null,
    primary key (id)
    );

insert into students (first_name, last_name, email, grade, comment) values ('Anna', 'Ivanova', 'burova@email', '10', 'offline');
insert into students (first_name, last_name, email, grade, comment) values ('Olga', 'Boiko', 'boyko@email', '9', 'online');
insert into students (first_name, last_name, email, grade, comment) values ('Anastasiya', 'Moroz', 'moroz@email', '11', 'offline');


CREATE SEQUENCE topic_id_sequence
    INCREMENT 20
    START 1;

create table if not exists topic
(
    id integer default nextval('topic_id_sequence') not null,
    title varchar(255) not null,
    primary key (id)
    );

insert into topic (title) values ('Числа і вирази');
insert into topic (title) values ('Рівняння, нерівності та їхні системи');


CREATE SEQUENCE task_id_sequence
    INCREMENT 20
    START 1;

create table if not exists task
(
    id integer default nextval('task_id_sequence') not null,
    image_path varchar(255) not null,
    level varchar(128) not null,
    text varchar(255) not null,
    answer varchar(255) not null,
    title varchar(255) not null,
    is_completed boolean default false not null,
    topic_id int,
    primary key (id),
    foreign key (topic_id) references topic (id)
);

insert into task (image_path, level, text, answer, title, is_completed, topic_id) values ('image_url_1', 'level_1', 'text_for_1', 'answer_to_1', 'title_1', false, 1);
insert into task (image_path, level, text, answer, title, is_completed, topic_id) values ('image_url_2', 'level_2', 'text_for_2', 'answer_to_2', 'title_2', false, 1);
insert into task (image_path, level, text, answer, title, is_completed, topic_id) values ('image_url_3', 'level_3', 'text_for_3', 'answer_to_3', 'title_3', false, 21);
insert into task (image_path, level, text, answer, title, is_completed, topic_id) values ('image_url_4', 'level_4', 'text_for_4', 'answer_to_4', 'title_4', false, 21);


create table if not exists student_task (
    id serial primary key,
    student_id int not null,
    task_id int not null,
    foreign key (student_id) references students(id),
    foreign key (task_id) references task(id)
)