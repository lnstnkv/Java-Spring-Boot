create table projects
(
    id          varchar(255) not null
        primary key,
    date_create date,
    date_edit   date,
    description varchar(255),
    name        varchar(255)
);

alter table projects
    owner to hits;

create table users
(
    id          varchar(255) not null
        primary key,
    date_create date,
    date_edit   date,
    email       varchar(255),
    name        varchar(255),
    password    varchar(255),
    role        varchar(255)
);

alter table users
    owner to hits;

create table tasks
(
    id            varchar(255) not null
        primary key,
    date_create   date,
    date_edit     date,
    description   varchar(255),
    header        varchar(255),
    priority      varchar(255),
    time_estimate integer,
    creator_id    varchar(255)
        constraint creator_task_constraint
            references users,
    performer_id  varchar(255)
        constraint performer_task_constraint
            references users,
    project_id    varchar(255)
        constraint project_task_constraint
            references projects
);

alter table tasks
    owner to hits;



create table comments
(
    id          varchar(255) not null
        primary key,
    date_create date,
    date_edit   date,
    text        varchar(255),
    users_id    varchar(255)
        constraint comment_user_constraint
            references users
);

alter table comments
    owner to hits;

create table comment_tasks
(
    comment_id varchar(255) not null
        constraint task_comment_constraint
            references comments,
    task_id    varchar(255) not null
        constraint comment_task_constraint
            references tasks
);

alter table comment_tasks
    owner to hits;



