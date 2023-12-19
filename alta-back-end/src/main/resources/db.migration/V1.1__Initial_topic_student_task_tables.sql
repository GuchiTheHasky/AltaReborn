create table if not exists alta_reborn.topic
(
    topic_id serial primary key,
    title    varchar(255),
    tasks jsonb
);

create table if not exists alta_reborn.student
(
    student_id serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255),
    grade      varchar(64),
    comment    varchar(255),
    tasks      JSONB
);

-- create table if not exists alta_reborn.task
-- (
--     task_id     serial primary key,
--     description varchar(255),
--     answer      varchar(255),
--     image_path  varchar(255),
--     level       varchar(64),
--     topic_id    integer references alta_reborn.topic (topic_id),
--     student_id  integer references alta_reborn.student (student_id)
-- );

-- CREATE TABLE alta_reborn.task
-- (
--     task_id     SERIAL PRIMARY KEY,
--     description VARCHAR(2000),
--     answer      VARCHAR(255),
--     image_path  VARCHAR(255),
--     level       VARCHAR(255),
--     topic_id    INT,
--     student_id  INT,
--     FOREIGN KEY (topic_id) REFERENCES alta_reborn.topic (topic_id),
--     FOREIGN KEY (student_id) REFERENCES alta_reborn.student (student_id)
-- );

