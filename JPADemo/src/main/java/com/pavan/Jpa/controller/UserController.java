package com.pavan.Jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.Jpa.entity.User;
import com.pavan.Jpa.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private final UserService us;

	public UserController(UserService us) {
		this.us = us;
	}
	
	 @PostMapping
	    public ResponseEntity<User> createUser(@RequestBody User user) {
	        User createdUser = us.createUser(user);
	        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	        Optional<User> user = us.getUserById(id);
	        return user.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @GetMapping
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = us.getAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }

	    @GetMapping("/by-email/{email}")
	    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
	        User user = us.getUserByEmail(email);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @GetMapping("/by-first-name/{firstName}")
	    public ResponseEntity<List<User>> getUsersByFirstName(@PathVariable String firstName) {
	        List<User> users = us.getUsersByFirstName(firstName);
	        return ResponseEntity.ok(users);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        us.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }
	}



