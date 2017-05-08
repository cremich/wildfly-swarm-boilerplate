package de.onkelpixel.example.wildflyswarm.rest;

import io.restassured.RestAssured;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Arquillian.class)
@DefaultDeployment
public class HelloWorldDefaultArquillianTest {

    @Deployment
    public static JAXRSArchive createDeployment() {
        return null;
    }

    @Test
    @RunAsClient
    public void testResponseIsHelloWorld() {
        RestAssured.given().port(8090)
                .expect().statusCode(200)
                .when().get("/hello")
                .then().contentType(JSON)
                .body("hello", equalTo("Hello World"));
    }
}
