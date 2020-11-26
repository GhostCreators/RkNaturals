package com.prometheus.rknaturals.controllers;

import java.util.ArrayList;
import java.util.List;

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
	public List<UserEntity> getUser() throws Exception {
		UserEntity ue = new UserEntity(null,"prudhvi","password","email");
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		return userRepository.findAllById(list);
		
	}
}	
