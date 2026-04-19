package com.CampusHub.salle_Service.config;

import com.CampusHub.salle_Service.entity.Salle;
import com.CampusHub.salle_Service.repository.SalleRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(SalleRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Chargement des salles depuis rooms.json...");
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<Map<String, List<Map<String, String>>>> typeReference = new TypeReference<>() {};
                InputStream inputStream = new ClassPathResource("/rooms.json").getInputStream();
                
                try {
                    Map<String, List<Map<String, String>>> data = mapper.readValue(inputStream, typeReference);
                    
                    for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
                        List<Map<String, String>> roomsList = entry.getValue();
                        
                        for (Map<String, String> roomData : roomsList) {
                            Salle salle = new Salle();
                            salle.setCode(roomData.get("num"));
                            salle.setNom(roomData.get("num"));
                            salle.setBatiment(roomData.get("batiment"));
                            
                            // Conversion sécurisée de la capacité
                            try {
                                salle.setCapacite(Integer.parseInt(roomData.get("capacite")));
                            } catch (NumberFormatException e) {
                                log.warn("Capacité invalide pour la salle {}: {}", roomData.get("num"), roomData.get("capacite"));
                                salle.setCapacite(0);
                            }
                            
                            salle.setFiliere(roomData.get("filier"));
                            salle.setActif(true);
                            
                            repository.save(salle);
                        }
                    }
                    log.info("Importation terminée avec succès !");
                } catch (Exception e) {
                    log.error("Erreur lors du chargement des salles: {}", e.getMessage());
                }
            } else {
                log.info("Salles déjà présentes dans la base de données, l'importation est ignorée.");
            }
        };
    }
}
