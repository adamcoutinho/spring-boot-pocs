package com.main.aws.app.endpoints;

import com.main.aws.app.entities.User;
import com.main.aws.app.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserEndPoint
{

    private final UserRepository userRepository;

    public UserEndPoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id, @RequestParam("login") String login) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findById(id,login));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody User user) {
        this.userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.update(user,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){

        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.delete(id));
    }
}
