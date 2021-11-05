# springboot-multimodule
PoC that shows how to create a Spring Boot multimodule applications.

There are several modules:
* **module-app**: the main library providing features that support the other parts of Spring Boot. These include the `SpringbootMultimoduleApplication` class, providing static methods to start this Spring Boot Application, controllers and resources. It has module-persistence, module-service and module-common dependencies.
* **module-persistence**: contains entities and repositories.
* **module-service**: contains all services and service implementations. It depends on module-persistence and module-common.
* **module-common**: contains all commons classes and exception handler.

There are 4 properties files:
* **application-local.properties** for local environment
* **application-dev.properties** for dev environment
* **application-cert.properties** for cert/preprod environment
* **application-prod.properties** for production environment

## Build with Maven
To build a Spring Boot application into a single executable Jar file with Maven, use the below command.

Build for **local** environment

```bash
mvn clean package -Plocal
```

Build for other environment

```bash
mvn clean package -Pdev -Dlogging.file.path=./logs/dev
mvn clean package -Pcert -Dlogging.file.path=./logs/cert
mvn clean package -Pprod -Dlogging.file.path=./logs/prod
```

## Project setup

```bash
java -jar environment\springboot-app-0.0.1.jar
```
Where *environment* can be:
* local
* dev
* cert
* prod

Alternatively, another way is to execute the `main` method in the `SpringbootMultimoduleApplication` class from IDE specifying the environment to use.

