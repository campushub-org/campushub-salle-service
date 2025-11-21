# campushub-salle-service - Service de Gestion des Salles

Ce service est responsable de la gestion des salles (par exemple, salles de cours, laboratoires, amphithéâtres) au sein de l'écosystème Campushub. Il gère les opérations CRUD (Create, Read, Update, Delete) pour les salles et leurs attributs associés.

### Fonctionnalités

*   **Gestion des salles**: Création, lecture, mise à jour et suppression des informations sur les salles.
*   **Intégration Eureka**: S'enregistre auprès de `campushub-registry` et peut découvrir d'autres services.
*   **Configuration centralisée**: Obtient sa configuration de `campushub-config`.
*   **Persistance des données**: Interagit avec une base de données MySQL (`campushub-salle-db`) pour stocker les informations des salles.

### Comment ça marche

`campushub-salle-service` est une application Spring Boot. Au démarrage, il se connecte à `campushub-config` pour obtenir sa configuration, s'enregistre auprès de `campushub-registry`, et se connecte à sa base de données MySQL dédiée (`campushub-salle-db`). Il expose ensuite des API REST pour interagir avec les données des salles.

### Commandes Utiles

#### Construire le service (localement, sans Docker)

Pour construire le fichier JAR exécutable du service:

```bash
cd campushub-deployment/campushub-salle-service
./mvnw clean install -DskipTests -Dspring.cloud.config.uri=http://localhost:8888
```
*(Note: L'option `-Dspring.cloud.config.uri=http://localhost:8888` est nécessaire pour que les tests et la construction locale puissent se connecter au service `campushub-config` si celui-ci est démarré via Docker sur votre machine locale. `-DskipTests` est utilisé car les tests requièrent une base de données MySQL et d'autres services qui peuvent ne pas être disponibles localement.)*

#### Exécuter le service (localement, sans Docker)

Assurez-vous d'avoir construit le JAR au préalable.

```bash
cd campushub-deployment/campushub-salle-service
java -jar target/salle_Service-0.0.1-SNAPSHOT.jar
```

Le service sera accessible sur `http://localhost:8082`.

#### Construire et exécuter avec Docker Compose

Dans le répertoire `campushub-deployment`, ce service est défini dans le fichier `docker-compose.yml`.

Pour construire l'image Docker (cela inclut la construction du JAR si ce n'est pas déjà fait):

```bash
cd campushub-deployment
docker-compose build campushub-salle-service
```

Pour démarrer le conteneur du service:

```bash
cd campushub-deployment
docker-compose up -d campushub-salle-service
```

Pour vérifier les logs du service une fois démarré:

```bash
cd campushub-deployment
docker-compose logs campushub-salle-service
```