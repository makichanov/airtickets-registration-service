create table air_tickets
(
    at_id         bigserial
        constraint air_tickets_pk
            primary key,
    at_route_name varchar(100) not null
);

alter table air_tickets
    owner to ars_user;

create index "IDX_air_tickets_at_id"
    on air_tickets (at_id);