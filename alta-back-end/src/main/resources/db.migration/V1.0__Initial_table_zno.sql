create sequence if not exists zno_id_seq;

create table if not exists zno
(
    id integer default nextval('zno_id_seq'::regclass) not null primary key,
    name varchar(255) not null,
    year integer not null
);
