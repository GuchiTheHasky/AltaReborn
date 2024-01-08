alter table public.students drop column if exists tasks cascade;
alter table public.task drop column if exists student_id cascade;
alter table public.task drop column if exists student;