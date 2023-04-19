package com.makichanov.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
// TODO: 7/26/22 Я бы не хранил заказы в бд.
// TODO: 7/26/22 а какая мотивация делать отдельную сущность под заказ? мб это отдать фронту?
//     если хочешь, чтобы пачка билетов создавалась транзакционно, пихни в сервис createMany или сделай какой-нибудь batchCreate

// Пачка билетов и так создается с помощью batch create
// С юридической точки зрения хорошей практикой является хранение истории о покупках пользователей.
// Возврат заказов, налоговая, банальное удобство использование приложения
@Getter
@Setter
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
    @CreatedBy
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m2m_orders_tickets",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<AirTicket> airTickets;
}
