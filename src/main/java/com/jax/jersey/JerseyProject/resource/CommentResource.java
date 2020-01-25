package com.jax.jersey.JerseyProject.resource;

import com.jax.jersey.JerseyProject.model.Comment;
import com.jax.jersey.JerseyProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId")long messageId) {
        return commentService.getAllComments(messageId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment addComment(@PathParam("messageId") long messageId,
                              Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId,
                                 Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId) {
        return commentService.removeComments(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentService.getComment(messageId, commentId);
    }
}
