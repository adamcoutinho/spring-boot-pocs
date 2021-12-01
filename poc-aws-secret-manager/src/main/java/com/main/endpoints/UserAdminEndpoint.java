package com.main.endpoints;

import com.amazonaws.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.main.config.UserAdminTest;
import java.io.UncheckedIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserAdminEndpoint {

    @Autowired
    private UserAdminTest userAdminTest;



    @GetMapping("/user")
    public ResponseEntity<?>get(){
            return ResponseEntity.ok(new Gson().toJson(this.userAdminTest));
    }
}
