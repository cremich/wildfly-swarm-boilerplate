# Wildfly-Swarm boilerplate
This is a simple and small boilerplate to provide a basic setup for a JavaEE application running in a wildfly-swarm 
server, including the following dependencies:
* JAX-RS
* CDI
* Arquillian
* Wildfly-Swarm
* Gson
* rest-assured

Simply checkout the repository and execute
```bash
mvn package
java -jar ./target/demo-swarm.jar
```

to run the application. In this basic setup, the application provides a Hello-REST-Resource `http://localhost:8090/hello`
Run a `curl -X GET http://localhost:8090/hello` to verify, the application works as designed.

## Stage Configuration
The setup provides some stage configs for `development` and `production` to demonstrate stage specific configuration.
You can start your application by providing the java property `-Dswarm.project.stage=<stage>`. For example

`java -jar ./target/demo-swarm.jar -Dswarm.project.stage=development`

If you don't provide any stage, swarm uses the settings from `project-defaults.yml`.
Keep in mind, that the port for the development and production stages differs so that you can run `development` and
`production` in parallel. In the below table you find the port and endpoint settings from the provided stages. Feel
free to add or customize more stages based on your demand.

| Stage        | Port | Endpoint                    |
| -------------|------|-----------------------------|
| development  | 8090 | http://localhost:8090/hello |
| production   | 8180 | http://localhost:8180/hello |

## Integration Tests
The project includes a basic arquillian setup to run integration tests and test the Hello-Resource. 
Simply run `mvn test` to start the integration tests. The test will startup a wildfly-swarm instance using the
`@DefaultDeployment` annotation of wildfly.
