package com.makichanov.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "air_tickets")
@Data
@NoArgsConstructor
public class AirTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private Long id;

    @Column(name = "at_plane_number")
    private String planeNumber;

    @Column(name = "at_price_cent")
    private Long price;

    @Column(name = "at_ticket_place")
    private String place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "at_fl_id")
    @EqualsAndHashCode.Exclude
    private FlightDetails flightDetails;

    @ManyToMany(mappedBy = "airTickets")
    @EqualsAndHashCode.Exclude
    private List<Order> orders;

}
