package com.jax.jersey.JerseyProject.client;

import com.jax.jersey.JerseyProject.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class GenericDemo {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        List<Message> response = client
                .target("http://localhost:8080/")
                .path("messages")
                .queryParam("year", 2020)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List< Message >>() {});

        System.out.println(response);
    }
}
