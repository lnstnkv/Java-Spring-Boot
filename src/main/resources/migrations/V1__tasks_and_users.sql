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
    id          varchar(255) not null
        primary key,
    description varchar(255),
    header      varchar(255),
    priority    varchar(255),
    users_id    varchar(255)
        constraint user_id_constraint
            references users
);

alter table tasks
    owner to hits;


