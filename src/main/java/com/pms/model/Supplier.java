package com.pms.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique=true)
	private String name;
	private boolean status;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "supplier_ingredient", joinColumns = @JoinColumn(name = "sup_id"), inverseJoinColumns = @JoinColumn(name = "ing_id"))
	private Set<Ingredient> ingredients;
	
	public Supplier(int id, String name, boolean status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	
}
