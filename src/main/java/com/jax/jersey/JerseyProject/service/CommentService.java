package com.jax.jersey.JerseyProject.service;

import com.jax.jersey.JerseyProject.database.DatabaseClass;
import com.jax.jersey.JerseyProject.model.Comment;
import com.jax.jersey.JerseyProject.model.ErrorMessage;
import com.jax.jersey.JerseyProject.model.Message;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messageMap = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> commentMap = messageMap.get(messageId).getComments();
        return new ArrayList<Comment>(commentMap.values());
    }

    public Comment getComment(long messageId, long commentId) {
        ErrorMessage errorMessage = new ErrorMessage("Not Found",
                404, "http://something/api");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        Message message = messageMap.get(messageId);
        if (message == null)
            throw new NotFoundException(response);
        Map<Long, Comment> commentMap = messageMap.get(messageId).getComments();
        Comment comment = commentMap.get(commentId);
        if (comment == null)
            throw new WebApplicationException(response);
        return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> commentMap = messageMap.get(messageId).getComments();
        comment.setId(commentMap.size() + 1);
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(Long messageId, Comment comment) {
        Map<Long, Comment> commentMap = messageMap.get(messageId).getComments();
        if(comment.getId() <= 0)
            return null;
        commentMap.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComments(long messageId, long commentId) {
        Map<Long, Comment> commentMap = messageMap.get(messageId).getComments();
        return commentMap.remove(commentId);
    }
}
