CREATE SEQUENCE student_id_sequence
    INCREMENT 10
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
    INCREMENT 10
    START 1;

create table if not exists topic
(
    id integer default nextval('topic_id_sequence') not null,
    title varchar(255) not null,
    primary key (id)
    );

insert into topic (title) values ('Числа і вирази');
insert into topic (title) values ('Рівняння, нерівності та їхні системи');