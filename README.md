# Hexagonal Template

This service is a template for hexagonal architecture in Spring Boot.

# Architecture explanation
Thinking of a more productive but still decoupled architectural model, I used a hexagonal architecture based on the implementation made by Netflix (link below).
- [Ready for changes with Hexagonal Architecture](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749)


This model has isolation from each of the layers facilitating unit testing and maintenance.
Ideally, you should make only one public class per layer and context, thus isolated all other classes with the package-private modifier.

Each layer should throw its own `Exceptions` if necessary. The HTTP layer intercepts exceptions via `@ControllerAdvice` and does the proper handling for HTTP (remember that this is a very simple example and should be changed as needed).

Below, a brief description of the responsibility of each layer.

- **Entities** are the domain objects (e.g., a Movie or a Shooting Location) — they have no knowledge of where they’re stored (unlike Active Record in Ruby on Rails or the Java Persistence API).
- **Repositories** are the interfaces to getting entities as well as creating and changing them. They keep a list of methods that are used to communicate with data sources and return a single entity or a list of entities. (e.g. UserRepository)
- **Interactors** are classes that orchestrate and perform domain actions — think of Service Objects or Use Case Objects. They implement complex business rules and validation logic specific to a domain action (e.g., onboarding a production)
- **Data Sources** are adapters to different storage implementations.A data source might be an adapter to a SQL database (an Active Record class in Rails or JPA in Java), an elastic search adapter, REST API, or even an adapter to something simple such as a CSV file or a Hash. A data source implements methods defined on the repository and stores the implementation of fetching and pushing the data.
- **Transport Layer** can trigger an interactor to perform business logic. We treat it as an input for our system. The most common transport layer for microservices is the HTTP API Layer and a set of controllers that handle requests. By having business logic extracted into interactors, we are not coupled to a particular transport layer or controller implementation. Interactors can be triggered not only by a controller, but also by an event, a cron job, or from the command line.

### Source code

To build the source code at the root folder, execute:

```bash
mvn clean install
```

The jar file will be generated at target folder


## Testing

To test the project at the root folder, execute:
```bash
mvn test
```

# Technologies

- Important versions 
  - java 11
  - maven 3.6+
  - spring-boot 2.5
- Data migration
  - [liquibase](https://www.liquibase.org/)
- Logbook
  - [logbook](https://github.com/zalando/logbook)
- Plugins
  - [openapi-generator](https://github.com/OpenAPITools/openapi-generator) Code generator from OpenAPI 3 specification. Generates code for various languages ​​and patters. The template is generating code from the specification in `src/main/resources/api.yaml`
  - [liquibase-maven](https://docs.liquibase.com/tools-integrations/maven/home.html) Plugin to generate/apply database scripts (data migration). The plugin settings are present in the `pom.xml` file and in the properties `src/main/resources/liquibase.properties`
- Monitor
  - micrometer registry and prometheus + grafana + jaeger. 

# Docker dependencies

All dependencies are in the `docker` folder, you can use as follows:
```bash
docker-compose -f docker-compose.sqlserver.yml up
```


# Commands
- openapi-generator
  - Generate stubs `mvn clean generate-resources`
- liquibase-maven plugin
  - apply changeset `mvn liquibase:update`
  - generate script to update `mvn liquibase:updateSQL` 
  - apply rollback `mvn liquibase:rollback -Dliquibase.rollbackCount=1` rollback of a changeset.
  - generate script to rollback `mvn liquibase:rollbackSQL -Dliquibase.rollbackCount=1` rollback of a changeset
- docker
  - Create docker image by maven plugin: `mvn spring-boot:build-image -Dspring-boot.build-image.imageName=hexagonal-template:0.0.2` 
  
# URLs
 - Swagger documentation: `http://localhost:8080/hexagonal-template/swagger-ui.html`
 - Spring Actuator: `http://localhost:8080/hexagonal-template/actuator`
 - Metrics prometheus: `http://localhost:8080/hexagonal-template/actuator/prometheus`  
 - Prometheus: `http://localhost:9090`
 - Grafana: `http://localhost:3000`
 - Jaeger `http://localhost:16686`
