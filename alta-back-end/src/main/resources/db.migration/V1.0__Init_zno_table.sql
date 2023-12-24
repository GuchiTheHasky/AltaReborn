create schema if not exists public;

create table if not exists public.flyway_schema_history (
    installed_rank integer not null,
    version        varchar(50),
    description    varchar(200),
    type           varchar(20) not null,
    script         varchar(1000) not null,
    checksum       integer not null,
    installed_by   varchar(100) not null,
    installed_on   timestamp not null default now(),
    execution_time integer not null,
    success        boolean not null
);


create table if not exists public.zno
(
    id   serial primary key,
    year integer,
    name varchar(255)
);