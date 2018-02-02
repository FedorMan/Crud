create table users(
    id serial primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table roles(
    id serial primary key,
    name_role varchar(100) not null
);

create table user_roles(
    id serial primary key,
    user_id int not null references users(id),
    role_id int not null references roles(id),
    unique(user_id,role_id)
)

insert into users values (1,'admin','')
insert into roles values (1,'admin')
insert into user_roles values (1,1,1)