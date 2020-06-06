package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	ResourceBundleMessageSource messageSource;
	
	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello  World1";
	}
	
	@GetMapping("/helloworldbean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Nelson", "Etubuli", "Kisumu");
	}
	
	@GetMapping("/hello_int")
	public String getMessagesinI18n(@RequestHeader(name="Accept-Language",required = false) String locale ) {
		return messageSource.getMessage("label.hello", null,new Locale(locale));
		
	}
	
	@GetMapping("/hello_int2")
	public String getMessagesinI18n()  {
		return messageSource.getMessage("label.hello", null,LocaleContextHolder.getLocale() );
		
	}
}
