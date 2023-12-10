create table if not exists zno
(
    id serial primary key,
    name varchar(255) not null,
    year integer not null
);

insert into zno values (1, 'ЗНО з математики – демонстраційний варіант', 2021);
insert into zno values (2, 'ЗНО з математики – основна сесія', 2020);
insert into zno values (3, 'ЗНО з математики – додаткова сесія', 2019);