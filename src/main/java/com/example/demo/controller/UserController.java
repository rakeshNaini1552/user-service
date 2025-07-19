package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
            System.out.println(id);
            User user = userService.findById(id);
            return ResponseEntity.ok(user);  // 200 OK with user
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers(){
            List<User> allUsers = userService.findAllUsers();
            if(!allUsers.isEmpty())
                return ResponseEntity.ok(allUsers);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("There are no users at all in database");
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user){
        System.out.println(user);
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(user);
    }

    @PostMapping("/multipleUser")
    public ResponseEntity<?> addMultipleUsers( @RequestBody @Valid List<User> multipleUsers){
        System.out.println(multipleUsers);
        userService.addMultipleUsers(multipleUsers);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(multipleUsers);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        boolean deleted = userService.deleteUserById(id);
        return ResponseEntity.ok("User is deleted");
    }

}
