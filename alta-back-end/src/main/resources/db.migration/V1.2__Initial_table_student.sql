create table if not exists student
(
    id serial primary key,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    email varchar(128) not null,
    grade varchar(64) not null,
    status varchar(128) not null
);