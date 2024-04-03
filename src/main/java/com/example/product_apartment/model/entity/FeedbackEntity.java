package com.example.rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "feedback_application")
public class FeedbackEntity {

    @Id
    @SequenceGenerator(name = "feedback_application", sequenceName = "feedback_application_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_application")
    @Column(name = "id")
    private Long id;

    @Column(name = "rating") // рейтинг от посетителя
    private String rating;

    @Column(name = "comment") // комментарий от посетителя
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

    public FeedbackEntity(String rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}
