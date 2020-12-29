package com.prometheus.rknaturals.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodId;
	private String name;
	private String type;
	private Integer prize;
	private Integer stock;
	private String description;

}
