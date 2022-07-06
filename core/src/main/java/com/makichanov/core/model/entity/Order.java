package com.makichanov.core.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_or_id_seq")
    @SequenceGenerator(name = "orders_or_id_seq", sequenceName = "orders_or_id_seq", allocationSize = 1)
    @Column(name = "or_id")
    private Long id;

    @Column(name = "or_total_price_cent")
    private Integer totalPrice;

    @Column(name = "or_create_date")
    @CreatedDate
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "or_us_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "m2m_orders_tickets",
            joinColumns = @JoinColumn(name = "ot_or_id"),
            inverseJoinColumns = @JoinColumn(name = "ot_at_id"))
    private List<AirTicket> airTickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;
        return createDate != null ? createDate.equals(order.createDate) : order.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", createDate=" + createDate +
                '}';
    }
}
