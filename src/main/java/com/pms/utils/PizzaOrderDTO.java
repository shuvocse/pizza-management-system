package com.pms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaOrderDTO {
	private int id;
	private String pizzaName;
	private String ingredients;
	private int size;
}
