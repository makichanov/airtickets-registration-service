// TODO: 7/14/22 разбиение по пакетам, зачем entity пихать в model?
//      мб лучше разделить dto на request и response и разбить по отдельным пакетам в model? а пакет entity положить просто в core
package com.makichanov.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "air_tickets")
@Getter
@Setter
@NoArgsConstructor
public class AirTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "air_tickets_at_id_seq")
    @SequenceGenerator(name = "air_tickets_at_id_seq", sequenceName = "air_tickets_at_id_seq", allocationSize = 1)
    @Column(name = "at_id")
    private Long id;

    //TODO: имя полета должно принадлежать самому полету, а не билету на него
    @Column(name = "at_route_name")
    private String routeName;

    @Column(name = "at_price_cent")
    private Long price;

    @Column(name = "at_tickets_quantity")
    private Integer quantity;

    @Column(name = "at_flight_time_seconds")
    private Long flightTimeSeconds;

    @Column(name = "at_departure_time")
    private Timestamp departureTime;

    @Column(name = "at_arrival_time")
    private Timestamp arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinColumn(name = "at_fa_from", nullable = false)
    private FlightAddress addressFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "at_fa_to", nullable = false)
    private FlightAddress addressTo;

    // TODO: 7/14/22 Перепроектировать систему.
    @ManyToMany(mappedBy = "airTickets")
    private List<Order> orders;

    //TODO: Что это за хуйня?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirTicket airTicket = (AirTicket) o;

        if (id != null ? !id.equals(airTicket.id) : airTicket.id != null) return false;
        if (routeName != null ? !routeName.equals(airTicket.routeName) : airTicket.routeName != null) return false;
        if (price != null ? !price.equals(airTicket.price) : airTicket.price != null) return false;
        if (quantity != null ? !quantity.equals(airTicket.quantity) : airTicket.quantity != null) return false;
        if (flightTimeSeconds != null ? !flightTimeSeconds.equals(airTicket.flightTimeSeconds) : airTicket.flightTimeSeconds != null)
            return false;
        if (departureTime != null ? !departureTime.equals(airTicket.departureTime) : airTicket.departureTime != null)
            return false;
        return arrivalTime != null ? arrivalTime.equals(airTicket.arrivalTime) : airTicket.arrivalTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (flightTimeSeconds != null ? flightTimeSeconds.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AirTicket{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", flightTimeSeconds=" + flightTimeSeconds +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
