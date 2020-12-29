package com.prometheus.rknaturals.service.blueprint;

import com.prometheus.rknaturals.dto.UserDTO;


public interface UserService {
	Boolean registerUser(UserDTO userDTO) throws Exception;
}
