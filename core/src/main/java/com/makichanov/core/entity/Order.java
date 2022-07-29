package com.makichanov.core.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
// TODO: 7/26/22 гляди на подсказки ide. С точки зрения требований не будет ли @Data избыточно? Я бы не хранил заказы в бд.
// TODO: 7/26/22 а какая мотивация делать отдельную сущность под заказ? мб это отдать фронту?
//     если хочешь, чтобы пачка билетов создавалась транзакционно, пихни в сервис createMany или сделай какой-нибудь batchCreate
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "total_price_cent")
    private Long totalPrice;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @CreatedBy
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m2m_orders_tickets",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    @EqualsAndHashCode.Exclude
    private List<AirTicket> airTickets;
}
