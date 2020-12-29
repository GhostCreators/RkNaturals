package com.prometheus.rknaturals.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prometheus.rknaturals.dto.UserDTO;
import com.prometheus.rknaturals.entity.UserEntity;
import com.prometheus.rknaturals.repository.UserRepository;
import com.prometheus.rknaturals.service.blueprint.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Override
	@Transactional
	public Boolean registerUser(UserDTO userDTO) throws Exception {

		if (userDTO.getEmail() == null || userDTO.getEmail().isBlank())
			throw new Exception("Check Your Email ID");
		
		if (userDTO.getPassword() == null || userDTO.getPassword().isBlank())
			throw new Exception("Enter valid password");

		Optional<UserEntity> ueFromDB = userRepository.findById(userDTO.getEmail());

		if (ueFromDB.isPresent())
			throw new Exception("Email ID already Exists");
		
		UserEntity ue = UserServiceImpl.transform(userDTO);
		ue.setPassword(encoder.encode(ue.getPassword()));
		
		userRepository.save(ue);

		return true;

	}

	public static UserEntity transform(UserDTO userDTO) {
		return new UserEntity(userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(), null);
	}

	public static UserDTO transform(UserEntity ue) {
		return new UserDTO(ue.getUsername(), ue.getPassword(), ue.getEmail(), null);
	}

}
