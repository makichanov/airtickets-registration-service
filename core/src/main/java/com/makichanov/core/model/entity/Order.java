package com.makichanov.core.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "or_id")
    private Long id;
//TODO: алиасы перед каждой колонкой -- избыточно
    @Column(name = "or_total_price_cent")
    private Long totalPrice;

    //TODO: Почему Timestamp? Есть LocalDateTime
    @Column(name = "or_create_date")
    @CreatedDate
    private Timestamp createDate;

    //TODO: Ты уверен, что тебе нужны все каскадные операции?
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "or_us_id", nullable = false)
    @EqualsAndHashCode.Exclude
    // TODO: Place @CreatedBy annotation
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "m2m_orders_tickets",
            joinColumns = @JoinColumn(name = "ot_or_id"),
            inverseJoinColumns = @JoinColumn(name = "ot_at_id"))
    @EqualsAndHashCode.Exclude
    private List<AirTicket> airTickets;

}
