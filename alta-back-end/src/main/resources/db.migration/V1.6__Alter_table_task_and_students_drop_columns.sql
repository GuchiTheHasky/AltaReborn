
alter table public.students
    drop column if exists tasks;
alter table public.task
    drop column if exists student_id;
alter table public.task
    drop column if exists student;

drop table if exists public.task_student;