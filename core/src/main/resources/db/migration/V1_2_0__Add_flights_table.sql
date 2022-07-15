create unique index "UIDX_air_tickets_plane_place"
    on air_tickets (at_plane_number, at_ticket_place);

create table flights
(
    fl_id          bigserial
            primary key,
    fl_flight_from bigserial,
    fl_flight_to   bigserial,
    fl_departure_time timestamptz,
    fl_arrival_time timestamptz,
    fl_base_price_cent integer
);

alter table air_tickets drop constraint "FK_air_tickets_fa_from";
alter table air_tickets drop constraint "FK_air_tickets_fa_to";
drop index "IDX_air_tickets_at_id";

alter table air_tickets drop column at_fa_from;
alter table air_tickets drop column at_fa_to;
alter table air_tickets drop column at_flight_time_seconds;
alter table air_tickets drop column at_departure_time;
alter table air_tickets drop column at_arrival_time;

alter table air_tickets add column at_fl_id bigserial;

alter table air_tickets
    add constraint FK_flights_fl_id
        foreign key (at_fl_id) references flights (fl_id);