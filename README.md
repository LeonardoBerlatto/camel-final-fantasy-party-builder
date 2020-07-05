# :camel: camel-final-fantasy-party-builder
Project used to learn the basics of Apache Camel integration framework and to work like a Final Fantasy party building application.

## :book: About
Highly inspired by [jntpablo's introducao-apache-camel](https://github.com/jntpablo/introducao-apache-camel)

Divided in one Camel Application(camel-party-builder) and one API(party-builder-service) for saving data sent by Camel.

The application flow consists of:
1. camel-party-builder reads XML content from a RabbitMQ queue;
2. camel-party-builder then process the XML content and sends a request to an API, getting Final Fantasy Characters data;
3. after getting characters data, Camel sends the data in a post request to party-builder-service;
4. party-builder-service saves the data in a PostgreSQL database.

## ▶ Running the project 
1. Build the apps
```bash
cd ./camel-party-builder
mvn clean install

cd ./party-builder-service
mvn clean install
```
2. Deploy docker containers(also with queue and postgres database)
```bash
docker-compose up
```

## :crystal_ball: Technologies
### Java
* Apache Camel
* Spring Boot
* Spring Data JPA
### SQL Database
* PostgreSQL
