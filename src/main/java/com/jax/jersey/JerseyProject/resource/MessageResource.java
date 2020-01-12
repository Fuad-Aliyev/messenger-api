package com.jax.jersey.JerseyProject.resource;

import com.jax.jersey.JerseyProject.MessageFilterBean;
import com.jax.jersey.JerseyProject.model.Message;
import com.jax.jersey.JerseyProject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
public class MessageResource {
    @Autowired
    MessageService messageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
        if (messageFilterBean.getYear() > 0)
            return messageService.getAllMessagesByYear(messageFilterBean.getYear());
        if (messageFilterBean.getStart() > 0 && messageFilterBean.getSize() > 0)
            return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message deleteMesssage(@PathParam("messageId") long id) {
        return messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
}
