package com.prometheus.rknaturals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.rknaturals.entity.UserEntity;
import com.prometheus.rknaturals.repository.UserRepository;

@RestController
@RequestMapping("/api/test/")
public class TestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String greeting() throws Exception {
		return "ALIVE";
	}
	
	@GetMapping("/users")
	public UserEntity getUser() throws Exception {
		return userRepository.findById("pbeerelly").get();
	}
}	
