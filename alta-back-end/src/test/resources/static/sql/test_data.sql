CREATE SCHEMA if not exists alta;

CREATE SEQUENCE zno_id_sequence
INCREMENT 20
START 1;

create table if not exists alta.zno
(
    id integer default nextval('zno_id_sequence') not null,
    name varchar(255) not null,
    year integer not null,
    primary key (id)
);

insert into alta.zno (name, year) values ('ЗНО з математики – демонстраційний варіант', 2021);
insert into alta.zno (name, year) values ('ЗНО з математики – основна сесія', 2020);
insert into alta.zno (name, year) values ('ЗНО з математики – додаткова сесія', 2019);


CREATE SEQUENCE topic_id_sequence
INCREMENT 20
START 1;

create table if not exists alta.topic
(
    id integer default nextval('topic_id_sequence') not null,
    name varchar(255) not null,
    subtopics json not null default '[]'::json,
    primary key (id)
);

insert into alta.topic (name) values ('Числа і вирази');
insert into alta.topic (name) values ('Рівняння, нерівності та їхні системи');



CREATE SEQUENCE student_id_sequence
INCREMENT 20
START 1;

create table if not exists alta.student
(
    id integer default nextval('student_id_sequence') not null,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    email varchar(128) not null,
    grade varchar(64) not null,
    comment varchar(128) not null,
    primary key (id)
);

insert into alta.student (first_name, last_name, email, grade, comment) values ('Anna', 'Burova', 'burova@email', '10', 'offline');
insert into alta.student (first_name, last_name, email, grade, comment) values ('Olga', 'Boiko', 'boyko@email', '9', 'offline');
insert into alta.student (first_name, last_name, email, grade, comment) values ('Anastasiya', 'Moroz', 'moroz@email', '11', 'offline');



CREATE SEQUENCE task_id_sequence
INCREMENT 20
START 1;

create table if not exists alta.task
(
    id integer default nextval('task_id_sequence') not null,
    number integer not null,
    description varchar(255) not null,
    topic_id int,
    answer varchar(255) not null,
    path_to_image varchar(255) not null,
    level varchar(128) not null,
    primary key (id),
    foreign key (topic_id) references alta.topic (id)
);

insert into alta.task (number, description, answer, path_to_image, level) values (10, 'description_1', 'answer_to_1', 'image_url_1', 'level_1');
insert into alta.task (number, description, answer, path_to_image, level) values (20, 'description_2', 'answer_to_2', 'image_url_2', 'level_2');
insert into alta.task (number, description, answer, path_to_image, level) values (30, 'description_3', 'answer_to_3', 'image_url-3', 'level_3');



create table if not exists alta.task_student (
  student_id integer not null,
  task_id integer not null,
  constraint FK_STUDENT_ID foreign key (student_id)
      references alta.student (id),
  constraint FK_TASK_ID foreign key (task_id)
      references alta.task (id)
);


