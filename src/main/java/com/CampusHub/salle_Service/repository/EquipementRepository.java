package com.CampusHub.salle_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CampusHub.salle_Service.entity.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
