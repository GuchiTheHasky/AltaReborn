create sequence if not exists group_id_seq increment by 20;

create table if not exists tasks_group (
    id serial primary key,
    creating_at timestamp,
    student_id int,
    tasks jsonb
);
