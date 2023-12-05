create table if not exists student
(
    id serial primary key,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    email varchar(128) not null,
    class varchar(64) not null,
    status varchar(128) not null,
    tasks json not null default '[]'::json
);