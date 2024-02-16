drop table public.student;

drop sequence if exists public.student_id_sequence;
drop sequence if exists public.student_id_seq;

alter table public.task drop column is_completed;
alter table public.task drop column topic_id_origin;
alter table public.task drop column topic;

alter table public.topic drop column tasks;

alter table public.students drop column tasks_ids;