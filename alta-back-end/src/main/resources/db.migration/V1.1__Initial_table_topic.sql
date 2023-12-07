create table if not exists topic
(
    id serial primary key,
    name varchar(255) not null,
    subtopics json not null default '[]'::json
    tasks json not null default '[]'::json
);