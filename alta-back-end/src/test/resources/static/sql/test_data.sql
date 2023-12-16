CREATE SEQUENCE zno_id_sequence
INCREMENT 20
START 1;

create table if not exists zno
(
    id integer default nextval('zno_id_sequence') not null,
    name varchar(255) not null,
    year integer not null,
    primary key (id)
);

insert into zno (name, year) values ('ЗНО з математики – демонстраційний варіант', 2021);
insert into zno (name, year) values ('ЗНО з математики – основна сесія', 2020);
insert into zno (name, year) values ('ЗНО з математики – додаткова сесія', 2019);


CREATE SEQUENCE topic_id_sequence
INCREMENT 20
START 1;

create table if not exists topic
(
    id integer default nextval('topic_id_sequence') not null,
    name varchar(255) not null,
    subtopics json not null default '[]'::json,
    primary key (id)
);

insert into topic (name) values ('Числа і вирази');
insert into topic (name) values ('Рівняння, нерівності та їхні системи');



CREATE SEQUENCE student_id_sequence
INCREMENT 20
START 1;

create table if not exists student
(
    id integer default nextval('student_id_sequence') not null,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    email varchar(128) not null,
    grade varchar(64) not null,
    comment varchar(128) not null,
    primary key (id)
);

insert into student (first_name, last_name, email, grade, comment) values ('Anna', 'Burova', 'burova@email', '10', 'offline');
insert into student (first_name, last_name, email, grade, comment) values ('Olga', 'Boiko', 'boyko@email', '9', 'offline');
insert into student (first_name, last_name, email, grade, comment) values ('Anastasiya', 'Moroz', 'moroz@email', '11', 'offline');



CREATE SEQUENCE task_id_sequence
INCREMENT 20
START 1;

create table if not exists task
(
    id integer default nextval('task_id_sequence') not null,
    number integer not null,
    description varchar(255) not null,
    topic_id int,
    answer varchar(255) not null,
    image_url varchar(255) not null,
    level varchar(128) not null,
    primary key (id),
    foreign key (topic_id) references topic (id)
);

insert into task (number, description, answer, image_url, level) values (10, 'description_1', 'answer_to_1', 'image_url_1', 'level_1');
insert into task (number, description, answer, image_url, level) values (20, 'description_2', 'answer_to_2', 'image_url_2', 'level_2');
insert into task (number, description, answer, image_url, level) values (30, 'description_3', 'answer_to_3', 'image_url-3', 'level_3');



create table if not exists task_student (
  student_id integer not null,
  task_id integer not null,
  constraint FK_STUDENT_ID foreign key (student_id)
      references student (id),
  constraint FK_TASK_ID foreign key (task_id)
      references task (id)
);


