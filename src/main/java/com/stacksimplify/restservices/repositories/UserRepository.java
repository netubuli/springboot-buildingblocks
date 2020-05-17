package com.stacksimplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/*the code line below if strictly for the method getUserByUsername in UserService
	 * Note that getUserById already has a java predefined method, 'findById'*/
	User findByUsername(String username);
	
	/*the code line below if strictly for the method getUserBySsn in UserService*/
	User findBySsn(String ssn);
}
