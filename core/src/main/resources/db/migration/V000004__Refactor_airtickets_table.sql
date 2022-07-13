alter table air_tickets drop column at_tickets_quantity;

alter table air_tickets drop column at_route_name;

alter table air_tickets add column  at_ticket_place varchar(5) not null;

alter table air_tickets add column at_plane_number varchar(7) not null;