package com.main.sqs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.messaging.support.MessageBuilder.withPayload;

@Service
public class ProducerService<T> {

    @Value("${sqs.queue-1}")
    private String queueProcessObject;
    @Value("${sqs.queue-2}")
    private String queueProcessListObject;
    @Value("${sqs.queue-3}")
    private String queueProcessString;

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ObjectMapper objectMapper;

    public ProducerService(QueueMessagingTemplate queueMessagingTemplate, ObjectMapper objectMapper) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendObject(T object) {
        try {
            this.queueMessagingTemplate.send(this.queueProcessObject, withPayload(this.objectMapper.writeValueAsString(object)).build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendListObject(List<T> objects)  {
        try {
            this.queueMessagingTemplate.send(this.queueProcessListObject, withPayload(this.objectMapper.writeValueAsString(objects)).build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendString(String message) {
        this.queueMessagingTemplate.send(this.queueProcessString, withPayload(message).build());
    }

}
