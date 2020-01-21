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
		userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/v1/users/{userId}")
	public ResponseEntity<UserEntity> getUser(@PathVariable int userId) {
		Optional<UserEntity> user = userService.listUserById(userId);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			throw new UserNotFoundException("id: " + userId);
		}
	}

	@PutMapping("/v1/users/{userId}")
	public ResponseEntity<Object> updateUser(@RequestBody UserEntity user, @PathVariable int userId) {
		user.setUserId(userId);
		Optional<UserEntity> foundUser = userService.listUserById(userId);
		if (foundUser.isPresent()) {
			userService.updateUser(user);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		} else {
			throw new UserNotFoundException("id: " + userId);
		}
	}

	@DeleteMapping("/v1/users/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
		Optional<UserEntity> foundUser = userService.listUserById(userId);
		if (foundUser.isPresent()) {
			userService.deleteUserById(userId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			throw new UserNotFoundException("id: " + userId);
		}
	}
}
