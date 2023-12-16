create table if not exists topic
(
    id serial primary key,
    title varchar(255) not null,
    subtopics json not null default '[]'::json
);