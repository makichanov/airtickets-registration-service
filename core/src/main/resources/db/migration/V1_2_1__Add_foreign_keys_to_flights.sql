alter table flights
    add constraint "FK_flights_flight_from"
        foreign key (fl_flight_from) references flight_address;

alter table flights
    add constraint "FK_flights_flight_to"
        foreign key (fl_flight_to) references flight_address;

