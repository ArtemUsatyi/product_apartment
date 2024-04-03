package com.example.rent_apartment.repository;

import com.example.rent_apartment.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    @Query(value = "SELECT ad FROM AddressEntity ad WHERE ad.nameCity = :nameCity")
    public List<AddressEntity> findAddressEntitiesByNameCity(String nameCity);
}
