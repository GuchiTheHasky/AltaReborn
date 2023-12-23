create table if not exists alta.topic
(
    id serial primary key,
    name varchar(255) not null,
    subtopics json not null default '[]'::json
);