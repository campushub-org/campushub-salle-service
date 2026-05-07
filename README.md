# 🏫 CampusHub - Salle Service

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)

> Le **Salle Service** est le module dédié à la gestion de l'infrastructure physique du campus. Il centralise l'inventaire des salles de cours, des amphithéâtres et des laboratoires, tout en assurant le suivi de leurs capacités et équipements.

---

## 🚀 Fonctionnalités Clés

- **Gestion du Patrimoine** : Référencement complet des bâtiments et des salles de l'établissement.
- **Spécifications Techniques** : Suivi des capacités d'accueil et des équipements disponibles (Projecteurs, PCs, Climatisation, etc.).
- **Filtrage par Filière** : Organisation des ressources physiques par affinité pédagogique.
- **Statut en Temps Réel** : Indicateur de disponibilité physique (Actif/Inactif) pour la planification.
- **Intégration Cloud** : Enregistrement sur Eureka pour une résolution transparente par le service de planification.

---

## 🛠️ Stack Technique

- **Core :** Spring Boot 3.2.5
- **Persistence :** Spring Data JPA + Hibernate
- **Base de données :** MySQL 8.0
- **Discovery :** Eureka Client
- **Lombok :** Pour un code concis et maintenable

---

## 📡 API Endpoints Principaux

| Méthode | Path | Description | Accès |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/salles` | Liste de toutes les salles (filtrage possible) | Public |
| `GET` | `/api/salles/:id` | Détails d'une salle spécifique par son ID | Public |
| `GET` | `/api/salles/code/:code` | Recherche d'une salle par son code technique | Interne / Public |
| `POST` | `/api/salles` | Création d'une nouvelle ressource physique | Admin / Doyen |
| `PUT` | `/api/salles/:id` | Mise à jour des équipements ou de la capacité | Admin / Doyen |
| `DELETE` | `/api/salles/:id` | Suppression d'une salle du référentiel | Admin |

---

## ⚙️ Configuration & Installation

### Build du package
```bash
# Générer le JAR sans exécuter les tests
./mvnw clean package -DskipTests
```

### Lancement Local
```bash
# Lancer via Maven
./mvnw spring-boot:run
```

### Déploiement Docker
```bash
docker build -t campushub-salle-service .
```

---
<p align="center">Optimisation de l'espace et confort pédagogique</p>
