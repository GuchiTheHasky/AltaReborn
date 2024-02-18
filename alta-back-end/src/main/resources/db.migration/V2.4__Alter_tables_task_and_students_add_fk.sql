alter table public.students
    add constraint fk_students_tasks_group
        foreign key (tasks_group_id) references tasks_group(id);

alter table public.task
    add constraint fk_task_tasks_group
        foreign key (tasks_group_id) references tasks_group(id);