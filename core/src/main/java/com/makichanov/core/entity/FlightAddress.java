package com.makichanov.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "flight_address")
@Getter
@Setter
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
}
