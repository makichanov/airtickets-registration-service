alter table air_tickets
    drop column plane_number;

alter table air_tickets
    drop column ticket_place;

alter table air_tickets
    add column place smallint not null;

alter table flights
    add column max_places smallint not null;

alter table flights
    add column places_sold smallint not null;