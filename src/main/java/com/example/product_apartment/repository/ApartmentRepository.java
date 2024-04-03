package com.example.rent_apartment.repository;

import com.example.rent_apartment.model.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {
}
