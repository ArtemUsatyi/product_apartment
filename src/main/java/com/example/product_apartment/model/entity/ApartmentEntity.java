package com.example.rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "apartment_application")
@NoArgsConstructor
public class ApartmentEntity {

    @Id
    @SequenceGenerator(name = "apartment_application", sequenceName = "apartment_application_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_application")
    @Column(name = "id")
    private Long id;

    @Column(name = "value_rooms") // количество комнат в офисе
    private String valueRoomsApartment;

    @Column(name = "rating") // рейтинг помещения А - (элит класс), В - (бизнес класс), С - (эконом класс)
    private String ratingApartment;

    @Column(name = "amount") // сумма аренды квартиры
    private String amountApartment;

    @Column(name = "smoking") // Отделка помещений : (с отделкой) / (без отделки)
    private Boolean smokingApartment;

    @Column(name = "floor") // этаж квартиры
    private Integer floorApartment;

    @Column(name = "availability") // свободно / не свободно
    private Boolean availability;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "apartment")
    private List<FeedbackEntity> feedbackEntities;

    @OneToOne(mappedBy = "apartment")
    private BookingEntity booking;

    public ApartmentEntity(String valueRoomsApartment, String ratingApartment, String amountApartment, Boolean smokingApartment, Integer floorApartment, AddressEntity addressEntity) {
        this.valueRoomsApartment = valueRoomsApartment;
        this.ratingApartment = ratingApartment;
        this.amountApartment = amountApartment;
        this.smokingApartment = smokingApartment;
        this.floorApartment = floorApartment;
        this.addressEntity = addressEntity;
    }
}
