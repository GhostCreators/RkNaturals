package com.prometheus.rknaturals.dto;

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
public class ProductDTO {
	private Integer prodId;
	private String name;
	private String type;
	private Integer prize;
	private Integer stock;
	private String description;
}
