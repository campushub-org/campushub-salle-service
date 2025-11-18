package com.CampusHub.salle_Service.controller;


import com.CampusHub.salle_Service.entity.Salle;
import com.CampusHub.salle_Service.service.SalleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    @GetMapping("/{id}")
    public Salle getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.saveSalle(salle);
    }

    @DeleteMapping("/{id}")
    public void deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
    }
}
