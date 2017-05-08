package de.onkelpixel.example.wildflyswarm.rest;

import io.restassured.RestAssured;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import java.util.Properties;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Arquillian.class)
@DefaultDeployment
public class HelloWorldProductionArquillianTest {

    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        Properties containerProperties = new Properties();
        containerProperties.setProperty("swarm.project.stage", "production");
        return new Swarm(containerProperties);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return null;
    }

    @Test
    @RunAsClient
    public void testResponseIsHelloProd() {
        RestAssured.given().port(8180)
                .expect().statusCode(200)
                .when().get("/hello")
                .then().contentType(JSON)
                .body("hello", equalTo("Hello Prod"));
    }
}
