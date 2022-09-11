package com.main.sqs.consumer;

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ConsumerString {
    @SqsListener(value = "${sqs.queue-3}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(String message, @Header("SenderId") String senderId) {

        System.out.println("message:" + message);

    }
}
