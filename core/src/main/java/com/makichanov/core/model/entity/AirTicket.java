package com.makichanov.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "air_tickets")
@Data
@NoArgsConstructor
public class AirTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "at_id")
    private Long id;

    @Column(name = "at_plane_number")
    private String planeNumber;

    @Column(name = "at_price_cent")
    private Long price;

    @Column(name = "at_ticket_place")
    private String place;

    @Column(name = "at_flight_time_seconds")
    private Long flightTimeSeconds;

    @Column(name = "at_departure_time")
    private Timestamp departureTime;

    @Column(name = "at_arrival_time")
    private Timestamp arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinColumn(name = "at_fa_from", nullable = false)
    @EqualsAndHashCode.Exclude
    private FlightAddress addressFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "at_fa_to", nullable = false)
    @EqualsAndHashCode.Exclude
    private FlightAddress addressTo;

    @ManyToMany(mappedBy = "airTickets")
    @EqualsAndHashCode.Exclude
    private List<Order> orders;

}
