package com.CampusHub.salle_Service;

import com.CampusHub.salle_Service.entity.Salle;
import com.CampusHub.salle_Service.repository.SalleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970",
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=password",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.cloud.config.enabled=false",
    "eureka.client.enabled=false",
    "spring.cloud.discovery.enabled=false"
})
public class SalleIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        salleRepository.deleteAll();
    }

    @Test
    void shouldCreateSalle() throws Exception {
        Salle salle = new Salle();
        salle.setCode("S101");
        salle.setNom("Salle 101");
        salle.setBatiment("Batiment A");
        salle.setCapacite(30);
        salle.setFiliere("Informatique");
        salle.setEquipements("Projecteur, Tableau");
        salle.setActif(true);

        mockMvc.perform(post("/api/salles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("S101")))
                .andExpect(jsonPath("$.nom", is("Salle 101")))
                .andExpect(jsonPath("$.batiment", is("Batiment A")));
    }

    @Test
    void shouldGetAllSalles() throws Exception {
        saveSalle("S101", "Salle 101", "INFO");
        saveSalle("S102", "Salle 102", "INFO");

        mockMvc.perform(get("/api/salles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldGetSallesByFiliere() throws Exception {
        saveSalle("S101", "Salle 101", "INFO");
        saveSalle("S201", "Salle 201", "MATH");

        mockMvc.perform(get("/api/salles").param("filiere", "INFO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("S101")));
    }

    @Test
    void shouldGetSalleById() throws Exception {
        Salle savedSalle = saveSalle("S101", "Salle 101", "INFO");

        mockMvc.perform(get("/api/salles/" + savedSalle.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("S101")));
    }

    @Test
    void shouldGetSalleByCode() throws Exception {
        saveSalle("S101", "Salle 101", "INFO");

        mockMvc.perform(get("/api/salles/code/S101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("S101")));
    }

    @Test
    void shouldDeleteSalle() throws Exception {
        Salle savedSalle = saveSalle("S101", "Salle 101", "INFO");

        mockMvc.perform(delete("/api/salles/" + savedSalle.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/salles/" + savedSalle.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    private Salle saveSalle(String code, String nom, String filiere) {
        Salle salle = new Salle();
        salle.setCode(code);
        salle.setNom(nom);
        salle.setFiliere(filiere);
        salle.setBatiment("Bat A");
        salle.setCapacite(20);
        return salleRepository.save(salle);
    }
}
