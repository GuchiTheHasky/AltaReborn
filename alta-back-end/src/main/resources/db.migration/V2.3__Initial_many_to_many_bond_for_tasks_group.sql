create table if not exists public.students_tasks_group
(
    id serial primary key,
    student_id int not null,
    tasks_group_id int not null,
    foreign key (student_id) references public.students(id),
    foreign key (tasks_group_id) references public.tasks_group(id)
);

create table if not exists public.tasks_group_task
(
    id serial primary key,
    tasks_group_id int not null,
    task_id int not null,
    foreign key (tasks_group_id) references public.tasks_group(id)
    foreign key (task_id) references public.task(id),
);

