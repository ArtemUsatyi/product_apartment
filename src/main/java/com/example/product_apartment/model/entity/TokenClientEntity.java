package com.example.rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "token_client")
@NoArgsConstructor
public class TokenClientEntity {

    @Id
    @SequenceGenerator(name = "token_client", sequenceName = "token_client_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_client")
    @Column(name = "id")
    private Long id;

    @Column(name = "token_csrf")
    private String token;

    @OneToOne(mappedBy = "tokenEntity")
    private ClientApplicationEntity clientEntity;

    public TokenClientEntity(String token) {
        this.token = token;
    }
}
