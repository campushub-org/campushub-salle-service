package com.CampusHub.salle_Service.repository;


import com.CampusHub.salle_Service.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    List<Salle> findByFiliere(String filiere);
    Optional<Salle> findByCode(String code);
}