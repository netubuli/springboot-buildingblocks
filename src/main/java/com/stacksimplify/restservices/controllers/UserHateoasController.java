package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value="hateoas/users")
public class UserHateoasController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	
	@GetMapping(path="/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional =userService.getUserById(id);
			User user =userOptional.get();
		Long userid=user.getId();
			Link selflink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			EntityModel<User> finalResouce= new EntityModel<User>(user);
		return finalResouce;
		}catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			
		}
	}
	//get all users method
		@GetMapping		
		public List<User> getAllUsers() {
			return userService.findAllUsers();
		}
	
	/* stacksimplify*/
	// getAllUsers Method
	/*	@GetMapping
		public EntityModel<User> getAllUsers() throws UserNotFoundException {
			List<User> allusers = userService.findAllUsers();
			
			for(User user : allusers) {
				//Self Link 
				Long userid = user.getId();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				
				//Relationship link with getAllOrders
				List<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
						.getAllOrder(userid);
				Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
				user.add(orderslink);
				
			}
			//Self link for getAllUsers
			Link selflinkgetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
			EntityModel<User> finalResources = new EntityModel<User>();
			return finalResources;

		}*/

}
