alter table orders
    add constraint "FK_orders_or_us_id"
        foreign key (or_us_id) references users (us_id);

alter table m2m_orders_tickets
    add constraint "FK_orders_or_id"
        foreign key (ot_or_id) references orders (or_id);

alter table m2m_orders_tickets
    add constraint "FK_tickets_at_id"
        foreign key (ot_at_id) references air_tickets (at_id);