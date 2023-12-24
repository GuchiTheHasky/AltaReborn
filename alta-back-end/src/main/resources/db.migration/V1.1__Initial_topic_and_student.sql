
create table if not exists public.topic
(
    id    serial primary key,
    tasks jsonb,
    title varchar(255)
);


create table if not exists public.students
(
    id         serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    grade      varchar(255),
    comment    varchar(255),
    email      varchar(255),
    tasks      jsonb
);

