package com.makichanov.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
// TODO: 7/26/22 гляди на подсказки ide. С точки зрения требований не будет ли @Data избыточно?
@Data
@NoArgsConstructor
public class FlightDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "base_price_cent")
    private Long basePrice;

    @Column(name = "max_places")
    private Integer maxPlaces;

    @Column(name = "places_sold")
    private Integer placesSold = 0;

    @OneToMany(mappedBy = "flightDetails")
    @EqualsAndHashCode.Exclude
    private List<AirTicket> tickets = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_from")
    private FlightAddress flightFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_to")
    @EqualsAndHashCode.Exclude
    private FlightAddress flightTo;

    // TODO: 7/29/22 Бизнес-логика в доменном объекте
    public void incrementPlacesSold() {
        placesSold++;
    }
}
