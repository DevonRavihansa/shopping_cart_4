package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
@Entity @Table(name = "orderitems")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @NonNull
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NonNull
    private Product product;

    @Column(nullable = false)
    @NonNull
    private int quantity;
}
