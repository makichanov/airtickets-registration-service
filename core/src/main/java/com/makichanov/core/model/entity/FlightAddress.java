package com.makichanov.core.model.entity;

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
    @Column(name = "fa_id")
    private Long id;

    @Column(name = "fa_airport_name")
    private String airportName;

    @Column(name = "fa_airport_address")
    private String airportAddress;

    @OneToMany(mappedBy = "flightFrom")
    @EqualsAndHashCode.Exclude
    private  List<FlightDetails> flightsFrom;

    @OneToMany(mappedBy = "flightTo")
    @EqualsAndHashCode.Exclude
    private List<FlightDetails> flightsTo;

}
