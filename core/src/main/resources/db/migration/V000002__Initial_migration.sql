create table flight_address
(
    fa_id              bigserial primary key,
    fa_airport_name    varchar(50) not null,
    fa_airport_address varchar(50) not null
);

create index "IDX_flight_address_fa_id"
    on flight_address (fa_id);

-------------------------------------------------------------------------------------------

alter table air_tickets
    add at_fa_from bigserial not null;

alter table air_tickets
    add at_fa_to bigserial not null;

alter table air_tickets
    add constraint "FK_air_tickets_fa_from"
        foreign key (at_fa_from) references flight_address (fa_id);

alter table air_tickets
    add constraint "FK_air_tickets_fa_to"
        foreign key (at_fa_to) references flight_address (fa_id);

alter table air_tickets
    add at_price_cent int not null;

alter table air_tickets
    add at_tickets_quantity int not null;

alter table air_tickets
    add at_flight_time_seconds int not null;

alter table air_tickets
    add at_departure_time timestamptz not null;

alter table air_tickets
    add at_arrival_time timestamptz not null;

-------------------------------------------------------------------------------------------

create table users
(
    us_id            bigserial
        constraint users_pk
            primary key,
    us_username      varchar(50)   not null,
    us_password_hash varchar(60)   not null,
    us_balance_cent  int default 0 not null
);

-------------------------------------------------------------------------------------------

create table orders
(
    or_id               bigserial
        constraint orders_pk
            primary key,
    or_total_price_cent int not null,
    or_create_date timestamptz not null,
    or_us_id            bigint not null
);

-------------------------------------------------------------------------------------------

create table m2m_orders_tickets
(
    ot_at_id bigint,
    ot_or_id bigint,
    constraint m2m_orders_tickets_pk
        primary key (ot_or_id, ot_at_id)
);