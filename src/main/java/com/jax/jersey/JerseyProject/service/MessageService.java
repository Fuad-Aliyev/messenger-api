package com.jax.jersey.JerseyProject.service;

import com.jax.jersey.JerseyProject.database.DatabaseClass;
import com.jax.jersey.JerseyProject.model.Message;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Component
public class MessageService {
    private Map<Long, Message> messageMap = DatabaseClass.getMessages();

    public MessageService() {
        messageMap.put(1l, new Message(1, "Hello World1", "Fuad"));
        messageMap.put(2l, new Message(2, "Hello Jersey", "Fuad"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messageMap.values());
    }

    public Message getMessage(long id) {
        return messageMap.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messageMap.size() + 1);
        messageMap.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if(message.getId() <= 0)
            return null;
        messageMap.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messageMap.remove(id);
    }

    public List<Message> getAllMessagesByYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(Message message : messageMap.values()) {
            calendar.setTime(message.getCreated());
            if(calendar.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }

        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        ArrayList<Message> list = new ArrayList<Message>(messageMap.values());
        if(start + size > list.size()) return new ArrayList<Message>();
        return list.subList(start, start + size);
    }

}
