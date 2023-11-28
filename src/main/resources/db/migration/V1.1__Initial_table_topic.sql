create table if not exists topic
(
    id        serial primary key,
    title     varchar(255) not null,
    sub_title varchar(255) not null,
    tasks     jsonb
);