package de.onkelpixel.example.wildflyswarm.rest;


import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/hello")
public class HelloWorldController {

    @Inject
    @ConfigurationValue("hello.world")
    String stringProperty;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hello doGet() {

        return new Hello(stringProperty);
    }
}
