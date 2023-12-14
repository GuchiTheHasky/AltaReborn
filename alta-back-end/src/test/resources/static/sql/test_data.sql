create sequence if not exists zno_id_seq;

create table if not exists zno
(
    id integer default nextval('zno_id_seq'::regclass) not null primary key,
    name varchar(255) not null,
    year integer not null
);

insert into zno (name, year) values ('ЗНО з математики – демонстраційний варіант', 2021);
insert into zno (name, year) values ('ЗНО з математики – основна сесія', 2020);
insert into zno (name, year) values ('ЗНО з математики – додаткова сесія', 2019);