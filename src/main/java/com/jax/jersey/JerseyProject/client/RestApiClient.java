package com.jax.jersey.JerseyProject.client;

import com.jax.jersey.JerseyProject.model.Message;

import javax.print.attribute.standard.Media;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestApiClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        //Create Url Target
        WebTarget baseTarget = client.target("http://localhost:8080/");
        WebTarget messageTarget = baseTarget.path("messages");
        WebTarget singleMessageTarget = messageTarget.path("{messageId}");

        Message message = singleMessageTarget
                .resolveTemplate("messageId", "2")
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);
//        System.out.println(message.getMessage());

//        Old Code
//        WebTarget webTarget= client.target("http://localhost:8080/messages/1");
        //you need json response
//        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
//        Response response = builder.get();
//        Message message = builder.get(Message.class);
//        String message = builder.get(String.class);


//        Message message = response.readEntity(Message.class);
        //System.out.println(message.getMessage());
//        System.out.println(message);



        //Create Post Request
        Message newMessage = new Message(4, "My new message", "Fuad");
        Response postResponse = messageTarget
                .request()
                .post(Entity.json(newMessage));
        if(postResponse.getStatus() != 201)
            System.out.println("Error");
        Message createdMessage = postResponse.readEntity(Message.class);
        System.out.println(createdMessage.getMessage());
    }
}
