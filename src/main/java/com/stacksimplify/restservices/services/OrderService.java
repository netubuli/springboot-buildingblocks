package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Order> getAllOrders(@PathVariable Long id) throws UserNotFoundException{
		 Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent())
			{throw new UserNotFoundException("user not found");}
		return userOptional.get().getOrders();
	}

}
