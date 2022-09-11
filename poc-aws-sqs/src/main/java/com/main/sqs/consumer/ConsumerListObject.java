package com.main.sqs.consumer;

import com.main.sqs.endpoint.UserRequest;
import java.util.List;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListObject {

    @SqsListener(value = "${sqs.queue-2}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(@Payload List<UserRequest> users, @Header("SenderId") String senderId) {

        users.stream().forEach(System.out::println);

    }
}
