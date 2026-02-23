create table roles (
    id serial primary key,
    name varchar(100) unique not null
);

create table users (
    id bigserial primary key,
    role_id int not null,
    name varchar(100) unique not null,
    password varchar(255) not null,
    email varchar(100) unique not null,
    created_at timestamp default current_timestamp,
    foreign key (role_id) references roles(id)
);

create table teachers (
    id bigserial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    patronymic varchar(100) not null,
    department varchar(150) not null,
    created_at timestamp default current_timestamp
);

create table rating_criteria (
    id bigserial primary key,
    name varchar(100) unique not null,
    description varchar(255) not null,
    created_at timestamp default current_timestamp
);

create table teachers_ratings (
    id bigserial primary key,
    user_id bigint not null,
    teacher_id bigint not null,
    criteria_id bigint not null,
    unique (user_id, teacher_id, criteria_id),
    rating smallint not null check(rating between 1 and 10),
    foreign key (user_id) references users(id),
    foreign key (teacher_id) references teachers(id),
    foreign key (criteria_id) references rating_criteria(id)
);