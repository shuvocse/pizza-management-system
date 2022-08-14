package com.pms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngForRestockDTO {
	private String supName;
	private int supId;
	private int ingId;
	private String ingName;
	private int quantity;

	public IngForRestockDTO(String supName, int supId, int ingId, String ingName) {
		this.supName = supName;
		this.supId = supId;
		this.ingId = ingId;
		this.ingName = ingName;
	}
}
