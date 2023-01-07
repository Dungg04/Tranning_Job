package com.example.demoapi.controller;

import com.example.demoapi.models.User;
import com.example.demoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(name = "id")Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public  ResponseEntity<?> editUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
        Optional<User> u = userRepository.findById(id);
        u.get().setFirstName(user.getFirstName());
        u.get().setLastName(user.getLastName());
        u.get().setEmail(user.getEmail());
        userRepository.save(u.get());
        return ResponseEntity.ok().body(u.get());
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
        return ResponseEntity.ok().build();
    }
}
