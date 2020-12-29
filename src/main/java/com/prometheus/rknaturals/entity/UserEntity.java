package com.prometheus.rknaturals.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_details")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id
	private String email;
	
	private String username;
	private String password;

	@OneToMany
	@JoinTable(name = "cart_items", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "prod_id"))
	private List<ProductEntity> cartProducts;

}
