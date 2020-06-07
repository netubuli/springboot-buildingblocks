package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.entities.Views;
import com.stacksimplify.restservices.entities.Views.External;
import com.stacksimplify.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value="jsonview/users")
public class UserJsonViewController {
	
	@Autowired
	private UserService userService;
	
	// get user by id external
	@GetMapping(path="/external/{id}")
	@JsonView(External.class)
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
		return userService.getUserById(id);
		}catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			
		}
	}
	
	// get user by id internal
		@GetMapping(path="/internal/{id}")
		@JsonView(Views.Internal.class)
		public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id) {
			try {
			return userService.getUserById(id);
			}catch (Exception ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
				
			}
		}

}
