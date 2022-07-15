package com.makichanov.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
public class FlightDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_departure_time")
    private Timestamp departureTime;

    @Column(name = "fl_arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "fl_base_price_cent")
    private Long basePrice;

    @OneToMany(mappedBy = "flightDetails")
    @EqualsAndHashCode.Exclude
    private List<AirTicket> tickets = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fl_flight_from")
    private FlightAddress flightFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fl_flight_to")
    @EqualsAndHashCode.Exclude
    private FlightAddress flightTo;

}
