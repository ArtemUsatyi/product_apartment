package com.example.rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product_discount")
public class ProductEntity {

    @Id
    @SequenceGenerator(name = "product_discount", sequenceName = "product_discount_sequence", allocationSize = 1, initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_discount")
    @Column(name = "id")
    private Long id;

    @Column(name = "discount")
    private String discount;  // описание скидки

    @Column(name = "value")
    private Integer value; // значение скидки

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<BookingEntity> booking;
}
