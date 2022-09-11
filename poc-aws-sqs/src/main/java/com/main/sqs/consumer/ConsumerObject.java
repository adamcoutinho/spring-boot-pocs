package com.main.sqs.consumer;


import com.main.sqs.endpoint.UserRequest;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerObject {

    @SqsListener(value = "${sqs.queue-1}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(@Payload UserRequest request, @Header("SenderId") String senderId) {

        System.out.println(request.toString());

    }
}
