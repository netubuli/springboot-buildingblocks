package com.stacksimplify.restservices.dtos;

import java.util.List;

import com.stacksimplify.restservices.entities.Order;

public class UserMmDto {
	
	private Long id;
	private String username;
	private String firstname;
	//private List<Order> orders;  /*Use this to return all content for the order*/
	private List<OrderMmDto> orders;
	
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
//	public List<Order> getOrders() {
//		return orders;
//	}
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	public List<OrderMmDto> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderMmDto> orders) {
		this.orders = orders;
	}
	
	

}
