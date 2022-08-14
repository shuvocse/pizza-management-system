package com.pms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
	private int id;
	private String name;
	private double price;
	private String origin;
	private int quantity;
	private boolean curStatus;

	public IngredientDTO(int id, String name, double price, String origin) {

		this.id = id;
		this.name = name;
		this.price = price;
		this.origin = origin;
	}

}