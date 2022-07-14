--todo: кодстайл миграций
create table roles
(
    ro_id   smallserial
        constraint roles_pk
            primary key,
    ro_name varchar(20) not null
);

--todo: обязательно последовательность создавать для fk? достаточно просто int
alter table users
    add us_role smallserial not null;

--todo: зачем явно прописывать констреинт? почему не сделал при объявлении столбца?
--todo: что за naming convention для констреинтов?
alter table users
    add constraint "FK_users_us_role"
        foreign key (us_role) references roles (ro_id);