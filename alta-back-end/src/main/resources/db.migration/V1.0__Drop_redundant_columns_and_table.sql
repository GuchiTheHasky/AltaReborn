

delete from public.tasks_group;
drop table if exists public.tasks_group;

alter table task drop column status;

drop sequence if exists group_id_seq;
drop sequence if exists tasks_group_id_seq;