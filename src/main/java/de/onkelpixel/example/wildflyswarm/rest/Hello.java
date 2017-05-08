package de.onkelpixel.example.wildflyswarm.rest;

public class Hello {
    private String hello;

    public Hello(final String hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return hello;
    }
}
