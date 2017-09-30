package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Devon Ravihansa on 9/25/2017.
 */
@Entity @Table(name = "orderstatus")
@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor
public class OrderStatus {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private Set<Order> orders = new HashSet<>(0);
}
