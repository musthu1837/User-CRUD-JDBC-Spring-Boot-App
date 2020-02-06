package com.musthafa.springboot.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.musthafa.springboot.exceptions.UserAlreadyExistsException;
import com.musthafa.springboot.exceptions.UserNotFoundException;
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
		int response = userService.addUser(user);
		if (response == 0)
			throw new UserAlreadyExistsException("User already exists with userId: " + user.getUserId());
		else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
					.buildAndExpand(user.getUserId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@GetMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> getUser(@PathVariable int userId) {
		Optional<UserEntity> user = userService.listUserById(userId);
		if (user.isPresent())
			return ResponseEntity.ok(user.get());
		else
			throw new UserNotFoundException("No user exists with userId: " + userId);
	}

	@PutMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user, @PathVariable int userId) {
		user.setUserId(userId);
		Optional<UserEntity> exsistingUser = userService.listUserById(userId);
		if (exsistingUser.isPresent()) {
			userService.updateUser(user);
			return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
		} else
			throw new UserNotFoundException("No user exists with userId: " + userId);
	}

	@DeleteMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable int userId) {
		Optional<UserEntity> exsistingUser = userService.listUserById(userId);
		if (exsistingUser.isPresent()) {
			userService.deleteUserById(userId);
			return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
		} else
			throw new UserNotFoundException("No user exists with userId: " + userId);
	}
}
