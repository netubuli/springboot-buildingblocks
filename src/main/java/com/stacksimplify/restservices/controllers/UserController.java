package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")

@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;

	//get all users method
	@ApiOperation(value = "Retrieve list of users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		try {
//		return userService.createUser(user);}
//		catch(UserExistsException ex) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
//		}
//	}
	//@PostMapping("/users")
	@PostMapping
	public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created.") @Valid @RequestBody User user,UriComponentsBuilder builder) {
		try {
		 userService.createUser(user);
		 HttpHeaders headers=new HttpHeaders(); 
		 headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());
		 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		 }
		catch(UserExistsException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
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
	//Get User by Id
	//@GetMapping(path="/users/{id}")
	@GetMapping(path="/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
		return userService.getUserById(id);
		}catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			
		}
	}
	//@PutMapping("/users/{id}")
	@PutMapping("/{id}")
	public User updateUserByid(@PathVariable("id") Long id, @RequestBody User user) {
		try {
		return userService.updateuUserById(id, user);}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
			
		}
	}
	@PutMapping("/updateusersfromclient/{id}")
	public User updateUserByIdfromClient(@PathVariable("id") Long id, @PathParam("username") String username, @PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname, @PathParam("email") String email, @PathParam("role") String role,
			@PathParam("ssn") String ssn) {
		return userService.updateUserByIdfromClient(id, username, firstname, lastname, email, role, ssn);
	}

	// get user by username
	//@GetMapping("/users/byusername/{username}")
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNotFoundException {
		//return userService.getUserByUsername(username);
		User user= userService.getUserByUsername(username);
		if (user==null) {
			throw new UserNotFoundException("Username'"+username+"' not found");}
		return user;
	}
   @GetMapping("/ssn/{ssn}")
   public User getUserBySsn(@PathVariable("ssn") String ssn) {
	   return userService.getUserBySsn(ssn);
   }
	//@DeleteMapping("/users/{id}")
	@DeleteMapping(path="/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
}
