package com.CampusHub.salle_Service.service;

import com.CampusHub.salle_Service.entity.Disponibilite;
import com.CampusHub.salle_Service.repository.DisponibiliteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DisponibiliteService {

    private final DisponibiliteRepository disponibiliteRepository;

    public DisponibiliteService(DisponibiliteRepository disponibiliteRepository) {
        this.disponibiliteRepository = disponibiliteRepository;
    }

    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteRepository.findAll();
    }

    public Optional<Disponibilite> getDisponibiliteById(Long id) {
        return disponibiliteRepository.findById(id);
    }

    public Disponibilite createDisponibilite(Disponibilite disponibilite) {
        return disponibiliteRepository.save(disponibilite);
    }

    public Disponibilite updateDisponibilite(Long id, Disponibilite newDisponibilite) {
        return disponibiliteRepository.findById(id)
            .map(d -> {
                d.setDateDebut(newDisponibilite.getDateDebut());
                d.setDateFin(newDisponibilite.getDateFin());
                d.setDescription(newDisponibilite.getDescription());
                d.setSalle(newDisponibilite.getSalle());
                return disponibiliteRepository.save(d);
            })
            .orElseThrow(() -> new RuntimeException("Disponibilité non trouvée"));
    }

    public void deleteDisponibilite(Long id) {
        disponibiliteRepository.deleteById(id);
    }
}

