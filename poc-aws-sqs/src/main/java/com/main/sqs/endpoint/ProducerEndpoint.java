package com.main.sqs.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.sqs.service.ProducerService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerEndpoint {

    private ProducerService producerService;

    public ProducerEndpoint(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/message/object")
    public ResponseEntity<?> postObject(@RequestBody UserRequest request){
        this.producerService.sendObject(request);
        return ResponseEntity.ok().body("Object send ok");
    }

    @PostMapping("/message/list/object")
    public ResponseEntity<?> postListObject(@RequestBody List<UserRequest> requests) throws JsonProcessingException {
        this.producerService.sendListObject(requests);
        return ResponseEntity.ok().body("List Object send ok");
    }

    @PostMapping("/message/string")
    public ResponseEntity<?> postString(@RequestBody String message){
        this.producerService.sendString(message);
        return ResponseEntity.ok().body("message send ok");
    }

}
