package com.musthafa.springboot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.musthafa.springboot.models.UserEntity;
import com.musthafa.springboot.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/v1/users")
	public ResponseEntity<List<UserEntity>> listUsers() {
		return ResponseEntity.ok(userService.listUsers());
	}

	@PostMapping("/v1/users")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(user.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> getUser(@PathVariable int userId) {
		return ResponseEntity.ok(userService.listUserById(userId));
	}

	@PutMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user, @PathVariable int userId) {
		user.setUserId(userId);
		userService.listUserById(userId);
		userService.updateUser(user);
		return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable int userId) {
		userService.listUserById(userId);
		userService.deleteUserById(userId);
		return new ResponseEntity<UserEntity>(HttpStatus.OK);

	}
}
