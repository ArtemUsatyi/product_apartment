package com.example.rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "client_application")
@NoArgsConstructor
public class ClientApplicationEntity {

    @Id
    @SequenceGenerator(name = "client_application", sequenceName = "client_application_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_application")
    @Column(name = "id")
    private Long id;

    @Column(name = "nike_name")
    private String nikeName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private TokenClientEntity tokenEntity;

    @OneToOne(mappedBy = "client")
    private BookingEntity booking;

    public ClientApplicationEntity(String nikeName, String email, String password) {
        this.nikeName = nikeName;
        this.email = email;
        this.password = password;
    }
}
