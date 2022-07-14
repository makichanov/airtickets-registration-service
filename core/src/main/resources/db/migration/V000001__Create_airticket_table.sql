--todo: сделать ренейм миграций: a) номер по формату Vчисло_число_число__first_letter_in_lower_case
create table air_tickets
(
    --todo: Зачем явно прописывать констреинт. Достаточно просто id bigserial primary key.
    at_id         bigserial
        constraint air_tickets_pk
            primary key,
    at_route_name varchar(100) not null
);

alter table air_tickets
    owner to ars_user;

create index "IDX_air_tickets_at_id"
    on air_tickets (at_id);