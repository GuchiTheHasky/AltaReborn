alter table public.task add column if not exists topic_id int;
alter table public.task add column if not exists student_id int;

alter table public.task
    add constraint fk_task_student
        foreign key (student_id)
            references public.student(id);

alter table public.task
    add constraint fk_task_topic
        foreign key (topic_id)
            references public.topic(id);