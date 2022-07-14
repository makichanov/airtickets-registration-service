// TODO: 7/14/22 разбиение по пакетам, зачем entity пихать в model?
//      мб лучше разделить dto на request и response и разбить по отдельным пакетам в model? а пакет entity положить просто в core
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

    // TODO: 7/14/22 Перепроектировать систему.
    @ManyToMany(mappedBy = "airTickets")
    @EqualsAndHashCode.Exclude
    private List<Order> orders;

}
