package com.makichanov.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flight_address")
@Data
@NoArgsConstructor
public class FlightAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_address_id")
    private Long id;

    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "airport_address")
    private String airportAddress;

    @OneToMany(mappedBy = "flightFrom")
    @EqualsAndHashCode.Exclude
    private  List<FlightDetails> flightsFrom;

    @OneToMany(mappedBy = "flightTo")
    @EqualsAndHashCode.Exclude
    private List<FlightDetails> flightsTo;
}
