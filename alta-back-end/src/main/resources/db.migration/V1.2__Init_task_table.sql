create table if not exists public.task
(
    id         serial primary key,
    image_path varchar(255),
    level      varchar(255),
    text       varchar(2000),
    textHtml   varchar(2000),
    answer     varchar(255),
    title      varchar(255),
    topic      jsonb,
    student    jsonb
);