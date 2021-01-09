package com.prometheus.rknaturals.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.rknaturals.dto.UserDTO;
import com.prometheus.rknaturals.repository.UserRepository;
import com.prometheus.rknaturals.service.blueprint.UserService;
import com.prometheus.rknaturals.springjwt.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/login")
	public String login(@RequestBody UserDTO userDTO) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		return jwt;
	}

	@PostMapping("/register")
	public Boolean registerUser(@RequestBody UserDTO userDTO) throws Exception {
		return userService.registerUser(userDTO);
	}

}
