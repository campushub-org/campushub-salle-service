package com.CampusHub.salle_Service.repository;

import com.CampusHub.salle_Service.entity.Disponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
}



