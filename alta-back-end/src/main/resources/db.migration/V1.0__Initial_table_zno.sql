create table if not exists alta.zno
(
    id serial primary key,
    name varchar(255) not null,
    year integer not null
);