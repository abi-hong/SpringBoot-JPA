package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    private LocalDate orderDate; //주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상탱 [ORDER, CANCEL]
}
