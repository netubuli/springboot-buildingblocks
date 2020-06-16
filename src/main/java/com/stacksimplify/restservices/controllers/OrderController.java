package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.OrderService;

@RestController
@Validated
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders
	
	@GetMapping("/{userid}/order")
	public List<Order> getAllOrder(@PathVariable Long userid) throws UserNotFoundException{
		 Optional<User> optionalUser=userRepository.findById(userid);
		 if(!optionalUser.isPresent())
			{throw new UserNotFoundException("user not found");}
		return optionalUser.get().getOrders();
	}
	
//	@GetMapping("/{id}/orders")
//	public List<Order> getAllOrders(@PathVariable("id") Long id) {
//		try {
//		return orderService.getAllOrders(id);
//		}catch (Exception ex) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//			
//		}
//	}
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid,@RequestBody Order order)throws UserNotFoundException  {
		 Optional<User> optionalUser=userRepository.findById(userid);
		 if(!optionalUser.isPresent())
			throw new UserNotFoundException("user not found");
		 User user =optionalUser.get();
		 order.setUser(user);
		 return orderRepository.save(order);
		
	}

}
