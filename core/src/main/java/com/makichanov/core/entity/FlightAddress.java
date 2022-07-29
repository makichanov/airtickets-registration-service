package com.makichanov.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flight_address")
// TODO: 7/26/22 гляди на подсказки ide. С точки зрения требований не будет ли @Data избыточно?
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

    // TODO: 7/26/22 Покажи мне кейс использования двустороннюю связь в этом случае и вопрос будет закрыт. Для поля ниже тоже самое.
    @OneToMany(mappedBy = "flightFrom")
    @EqualsAndHashCode.Exclude
    private  List<FlightDetails> flightsFrom;

    @OneToMany(mappedBy = "flightTo")
    @EqualsAndHashCode.Exclude
    private List<FlightDetails> flightsTo;
}
