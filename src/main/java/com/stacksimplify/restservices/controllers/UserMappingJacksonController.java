package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/{id}")  //Fields with HashSet
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
			Set<String> userfields = new HashSet<String>();
			userfields.add("id");
			userfields.add("username");
			userfields.add("ssn");
			userfields.add("orders");
			
			Set<String> orderfields = new HashSet<String>();
			orderfields.add("orderid");
			orderfields.add("orderdescription");
			orderfields.add("links");
			
			
			//FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(userfields))
					.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(orderfields ));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			
			mapper.setFilters(filterProvider );
			return mapper;
		}catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			
		}
	}
	
	//get user id Fields with @Request Parameters
	@GetMapping(path="/params/{id}")  
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
						
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
//			FilterProvider filterProvider = new SimpleFilterProvider()
//					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(userfields))
//					.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(orderfields ));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			
			mapper.setFilters(filterProvider );
			return mapper;
		}catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			
		}
	}
}
