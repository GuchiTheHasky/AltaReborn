create table if not exists task
(
    id serial primary key,
    number integer not null,
    image_path varchar(255) not null,
    level varchar(128) not null,
    text varchar(255) not null,
    answer varchar(255) not null,
    zno_id integer references zno(id) on delete cascade,
    topic_id integer references topic(id) on delete cascade
)