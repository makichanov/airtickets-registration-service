package com.makichanov.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "air_tickets")
// TODO: 7/26/22 гляди на подсказки ide. С точки зрения требований не будет ли @Data избыточно?
@Data
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
    @EqualsAndHashCode.Exclude
    private FlightDetails flightDetails;

    @ManyToMany(mappedBy = "airTickets")
    @EqualsAndHashCode.Exclude
    private List<Order> orders;
}
