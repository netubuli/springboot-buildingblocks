package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/users2")
	public String createUserOption2(@PathParam("username") String username, @PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname, @PathParam("email") String email, @PathParam("role") String role,
			@PathParam("ssn") String ssn) {
		return userService.createUserOption2(username, firstname, lastname, email, role, ssn);
	}
	@PostMapping("/users3")
	public User createUserOption3(@PathParam("username") String username, @PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname, @PathParam("email") String email, @PathParam("role") String role,
			@PathParam("ssn") String ssn) {
		return userService.createUserOption3(username, firstname, lastname, email, role, ssn);
	}
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	@PutMapping("/users/{id}")
	public User updateUserByid(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateuUserById(id, user);
	}
	@PutMapping("/updateusersfromclient/{id}")
	public User updateUserByIdfromClient(@PathVariable("id") Long id, @PathParam("username") String username, @PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname, @PathParam("email") String email, @PathParam("role") String role,
			@PathParam("ssn") String ssn) {
		return userService.updateUserByIdfromClient(id, username, firstname, lastname, email, role, ssn);
	}

	// get user by username
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
   @GetMapping("/users/ssn/{ssn}")
   public User getUserBySsn(@PathVariable("ssn") String ssn) {
	   return userService.getUserBySsn(ssn);
   }
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
}
