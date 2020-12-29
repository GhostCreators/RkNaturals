package com.prometheus.rknaturals.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String username;
	private String password;
	private String email;
	
	private List<ProductDTO> cartItems;
}
