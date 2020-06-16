package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;

//import org.springframework.hateoas.ResourceSupport;
@ApiModel(description = "This model is to create a user")
@Entity
@Table
//@JsonIgnoreProperties({"firstname","lastname"})  part of static filtering JSONIgnore
//@JsonFilter(value="userFilter") used for mappingJacksonValue filtering
public class User extends RepresentationModel<User> {
//public class User{
	@ApiModelProperty(notes = " Auto generated unique id", required = true, position = 1)
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long id;

	@ApiModelProperty(notes = "username should be in format flname", example = "netubuli", required = false, position = 2)
	@NotEmpty(message = "Username is a mandatory field")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;

	@Size(min = 2, message = "firstname must be atleast 2 characters")
	@JsonView(Views.External.class)
	@Column(name = "FIRST_NAME", length = 50, nullable = false)

	private String firstname;
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;

	@Column(name = "EMAIL", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;

	@Size(min = 2, max = 50)
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	// @JsonIgnore part of static filtering JSONIgnore
	@JsonView(Views.Internal.class)
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;

	/* added to demo versioning */
	@Column(name = "ADDRESS")
	private String address;

	// no argument cnstructor(mandatory)
	public User() {

	}
	// fields constructor(not mandatory)

	public User(Long id, @NotEmpty(message = "Username is a mandatory field") String username,
			@Size(min = 2, message = "firstname must be atleast 2 characters") String firstname, String lastname,
			String email, String role, String ssn, List<Order> orders, String address) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	// setters and getters (mandatory)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address
				+ "]";
	}

}
