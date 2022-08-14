package com.pms.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String provenance;
	@Column(scale = 2)
	private double price;
	private int numberOfItem;
	private boolean status;
	@ManyToMany(mappedBy = "ingredients")
	private Set<Supplier> suppliers;
	@ManyToMany(mappedBy = "ingredients")
	private Set<BasePizza> pizzas;
}
