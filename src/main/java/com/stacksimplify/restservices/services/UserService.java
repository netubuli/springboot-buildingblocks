package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
//@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	//Create users
	public User createUser(User user) {
		return userRepository.save(user);
	}
@PersistenceContext
private EntityManager entityManager;
public String createUserOption2(String username,String firstname,String lastname,String email,String role,String ssn)
{
		
	User user=new User();
	user.setEmail(email);
	user.setFirstname(firstname);
	user.setLastname(lastname);
	user.setRole(role);
	user.setSsn(ssn);
	user.setUsername(username);
	//return userRepository.save(user);
	entityManager.persist(user);
	return user.toString();
	
	}

public User createUserOption3(String username,String firstname,String lastname,String email,String role,String ssn)
{
		
	User user=new User();
	user.setEmail(email);
	user.setFirstname(firstname);
	user.setLastname(lastname);
	user.setRole(role);
	user.setSsn(ssn);
	user.setUsername(username);
	return userRepository.save(user);
	
	}
	// get uder by id
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	//get user by username
	public User getUserByUsername(String username) {
		User user=userRepository.findByUsername(username);
		return user;
		
	}
	//
	//get user by username
		public User getUserBySsn (String ssn) {
			User user=userRepository.findBySsn(ssn);
			return user;
			
		}
	
	//update user by id
	public User updateuUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
		
	}
	//update user by id(the varaiables are passed from a consuming client)
	public User updateUserByIdfromClient(Long id,String username,String firstname,String lastname,String email,String role,String ssn) {
		User user=new User();
		user.setId(id);
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setRole(role);
		user.setSsn(ssn);
		user.setUsername(username);
		return userRepository.save(user);
	}
	
	
	//delete user by id
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
		userRepository.deleteById(id);
		}
	}

}
