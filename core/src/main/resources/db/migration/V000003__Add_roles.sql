create table roles
(
    ro_id   smallserial
        constraint roles_pk
            primary key,
    ro_name varchar(20) not null
);

alter table users
    add us_role smallserial not null;

alter table users
    add constraint "FK_users_us_role"
        foreign key (us_role) references roles (ro_id);