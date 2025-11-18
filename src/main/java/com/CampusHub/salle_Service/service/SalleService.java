package com.CampusHub.salle_Service.service;


import com.CampusHub.salle_Service.entity.Salle;
import com.CampusHub.salle_Service.repository.SalleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalleService {

    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle getSalleById(Long id) {
        return salleRepository.findById(id).orElse(null);
    }

    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }
}
