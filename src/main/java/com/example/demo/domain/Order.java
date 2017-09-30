package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
@Entity @Table(name = "orders")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Order {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String poNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NonNull
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems = new HashSet<>(0);

    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
