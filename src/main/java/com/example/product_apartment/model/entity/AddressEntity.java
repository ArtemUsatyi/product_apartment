package com.example.rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "address_apartment_application")
public class AddressEntity {

    @Id
    @SequenceGenerator(name = "address_apartment_application", sequenceName = "address_apartment_application_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_apartment_application")
    @Column(name = "id")
    private Long id;

    @Column(name = "name_street") // название улицы
    private String nameStreet;

    @Column(name = "name_city") // название города
    private String nameCity;

    @Column(name = "number_house") // номер дома
    private String numberHouse;

    @OneToOne(mappedBy = "addressEntity")
    private ApartmentEntity apartmentEntity;

    public AddressEntity(String nameStreet, String nameCity, String numberHouse) {
        this.nameStreet = nameStreet;
        this.nameCity = nameCity;
        this.numberHouse = numberHouse;
    }
}
