package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@Validated
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	//get all orders
	
		@GetMapping("/{userid}/order")
		public List<Order> getAllOrder(@PathVariable Long userid) throws UserNotFoundException{
			 Optional<User> optionalUser=userRepository.findById(userid);
			 if(!optionalUser.isPresent())
				{throw new UserNotFoundException("user not found");}
			return optionalUser.get().getOrders();
		}
		
}
