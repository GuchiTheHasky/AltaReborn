create table if not exists student_task (
    id serial primary key,
    student_id int not null,
    task_id int not null,
    foreign key (student_id) references public.students(id),
    foreign key (task_id) references public.task(id)
)