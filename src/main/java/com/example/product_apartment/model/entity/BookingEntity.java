package com.example.product_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "booking_info")
public class BookingEntity {

    @Id
    @SequenceGenerator(name = "booking_info", sequenceName = "booking_info_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_info")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_start")
    private LocalDateTime startDate;

    @Column(name = "date_end")
    private LocalDateTime endDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private ClientApplicationEntity client;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public BookingEntity(LocalDateTime startDate, LocalDateTime endDate, ApartmentEntity apartment, ClientApplicationEntity client, ProductEntity product) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.apartment = apartment;
        this.client = client;
        this.product = product;
    }
}
