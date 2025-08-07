package com.eminpolat.evantproject.controller;

import com.eminpolat.evantproject.business.concretes.UserManager;
import com.eminpolat.evantproject.entites.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userManager.save(user);
        return ResponseEntity.ok("User added successfully");
    }

    // READ - Get All
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userManager.getAll());
    }

    // READ - Get by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userManager.getById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userManager.getById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id); // Güncellenecek ID'yi net olarak belirt
            userManager.update(updatedUser);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> user = userManager.getById(id);
        if (user.isPresent()) {
            userManager.delete(user.get());
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
