package com.makichanov.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "air_tickets")
@Getter
@Setter
@NoArgsConstructor
public class AirTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "price_cent")
    private Long price;

    @Column(name = "place")
    private Integer place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private FlightDetails flightDetails;

    @ManyToMany(mappedBy = "airTickets")
    private List<Order> orders;
}
