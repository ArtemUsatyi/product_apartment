package com.example.rent_apartment.repository;

import com.example.rent_apartment.model.entity.ClientApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientApplicationEntity, Long> {

    public ClientApplicationEntity findClientApplicationByNikeName(String nikeName);

    public ClientApplicationEntity findClientApplicationByEmail(String email); // Spring Data Generation Query

    @Query(nativeQuery = true, value = "SELECT * FROM client_application WHERE nike_name = :nikeName") // Native Query
    public ClientApplicationEntity findClientByNike(String nikeName);

    @Query(value = "SELECT ca FROM ClientApplicationEntity ca WHERE ca.nikeName = :nikeName") // JPQL/HQL
    public ClientApplicationEntity findClientByNikeName(String nikeName);

    @Query(value = "SELECT ca FROM ClientApplicationEntity ca WHERE ca.tokenEntity.token = :token") // JPQL/HQL
    public ClientApplicationEntity findClientByToken(String token);
}
