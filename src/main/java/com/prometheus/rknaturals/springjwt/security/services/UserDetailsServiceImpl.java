package com.prometheus.rknaturals.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prometheus.rknaturals.dto.UserDTO;
import com.prometheus.rknaturals.entity.UserEntity;
import com.prometheus.rknaturals.repository.UserRepository;
import com.prometheus.rknaturals.service.UserServiceImpl;
import com.prometheus.rknaturals.service.blueprint.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity ue = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

		UserDTO user = UserServiceImpl.transform(ue);

		return UserDetailsImpl.build(user);
	}

}
