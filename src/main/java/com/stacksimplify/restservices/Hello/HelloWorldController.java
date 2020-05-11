package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello  World1";
	}
	
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Nelson", "Etubuli", "Kisumu");
	}

}
