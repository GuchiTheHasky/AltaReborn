alter table public.task add column if not exists topic_id int;

alter table public.task add constraint task_topic_fk foreign key (topic_id) references public.topic(id);