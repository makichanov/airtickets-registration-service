----------------------------------------------------------------------

alter table air_tickets
    rename column at_id to "ticket_id";

alter table air_tickets
    rename column at_fl_id to "flight_id";

alter table air_tickets
    rename column at_plane_number to "plane_number";

alter table air_tickets
    rename column at_ticket_place to "ticket_place";

alter table air_tickets
    rename column at_price_cent to "price_cent";

----------------------------------------------------------------------

alter table flights
    rename column fl_id to "flight_id";

alter table flights
    rename column fl_base_price_cent to "base_price_cent";

alter table flights
    rename column fl_arrival_time to "arrival_time";

alter table flights
    rename column fl_departure_time to "departure_time";

alter table flights
    rename column fl_flight_to to "flight_to";

alter table flights
    rename column fl_flight_from to "flight_from";

----------------------------------------------------------------------

alter table flight_address
    rename column fa_id to "flight_address_id";

alter table flight_address
    rename column fa_airport_address to "airport_address";

alter table flight_address
    rename column fa_airport_name to "airport_name";

----------------------------------------------------------------------

alter table orders
    rename column or_id to "order_id";

alter table orders
    rename column or_create_date to "create_date";

alter table orders
    rename column or_total_price_cent to "total_price_cent";

alter table orders
    rename column or_us_id to "user_id";

----------------------------------------------------------------------

alter table users
    rename column us_id to "user_id";

alter table users
    rename column us_username to "username";

alter table users
    rename column us_password_hash to "password_hash";

alter table users
    rename column us_balance_cent to "balance_cent";

alter table users
    rename column us_role to "role";

----------------------------------------------------------------------

alter table roles
    rename column ro_id to "role_id";

alter table roles
    rename column ro_name to "name";

----------------------------------------------------------------------

alter table m2m_orders_tickets
    rename column ot_or_id to "order_id";

alter table m2m_orders_tickets
    rename column ot_at_id to "ticket_id";

----------------------------------------------------------------------