create table if not exists task
(
    id serial primary key,
    number integer not null,
    description varchar(255) not null,
    topic_id int,
    answer varchar(255) not null,
    image_url varchar(255) not null,
    level varchar(128) not null,
    foreign key (topic_id) references alta_db.topic (id)
)

create table if not exists task_student (
  student_id integer not null,
  task_id integer not null,
  CONSTRAINT FK_STUDENT_ID FOREIGN KEY (student_id)
      REFERENCES student (id),
  CONSTRAINT FK_TASK_ID FOREIGN KEY (task_id)
      REFERENCES task (id)
);