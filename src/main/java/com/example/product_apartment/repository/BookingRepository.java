package com.example.rent_apartment.repository;

import com.example.rent_apartment.model.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
