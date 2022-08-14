package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pms.model.Supplier;
import com.pms.repos.PizzaRepository;
import com.pms.utils.IngForRestockDTO;
import com.pms.utils.IngredientDTO;
import com.pms.utils.OrderDTO;
import com.pms.utils.PizzaOrderDTO;
import com.pms.utils.SupplierDto;

@Controller
public class PizzaView {
	
	@Autowired
	private PizzaRepository pr;
	
	@GetMapping("/")
	public String index(Model model) {
		List<IngredientDTO> ing = pr.getIngredientsForCustomer();
		List<PizzaOrderDTO> pos = pr.getOrderList();
		List<IngForRestockDTO> iws = pr.getIngredientsWithSupplier();
		List<IngredientDTO> ifb = pr.getIngredientsForBecker();
		List<Supplier> suppliers = pr.getSuppliers();
		model.addAttribute("ingredients", ing);
		model.addAttribute("orders", pos);
		model.addAttribute("restock", iws);
		model.addAttribute("listOfIngs", ifb);
		model.addAttribute("listOfSupplier", suppliers);
		
		return "index";
	}
	
	@PostMapping("/order")
	public String takeOrder(Model model,OrderDTO o, BindingResult result) {
		pr.saveOrder(o);
		return "redirect:/";
	}
	
	
	
	@PostMapping("/restock")
	public String restock(Model model,IngForRestockDTO stock, BindingResult result) {
		pr.restockIngredients(stock.getIngId(),stock.getQuantity());
		return "redirect:/";
	}
	
	
	@PostMapping("/status/ingredient")
	public String changeIngredientStatus(Model model,IngredientDTO ing, BindingResult result) {
		boolean status = true;
		if(ing.isCurStatus())
			status = false;

		pr.changeIngStatus(ing.getId(),status);
		return "redirect:/";
	}
	
	@PostMapping("/status/supplier")
	public String changeSupplierStatus(Model model,Supplier sup, BindingResult result) {
		boolean status = true;
		if(sup.isStatus())
			status = false;

		pr.changeSupStatus(sup.getId(),status);
		return "redirect:/";
	}
	
	@PostMapping("/remove/ingredient")
	public String removeIngredient(Model model,IngredientDTO ing, BindingResult result) {
		pr.removeIngredient(ing.getId());
		return "redirect:/";
	}
	
	@PostMapping("/remove/supplier")
	public String removeSupplier(Model model,Supplier sup, BindingResult result) {
		pr.removeSupplier(sup.getId());
		return "redirect:/";
	}
	
	@PostMapping("/ing/save")
	public String saveIngredient(Model model,IngredientDTO ing, BindingResult result) {
		pr.saveIngredient(ing);
		return "redirect:/";
	}
	
	@PostMapping("/sup/save")
	public String savesupplier(Model model,SupplierDto sup, BindingResult result) {
		pr.saveSupplier(sup);
		return "redirect:/";
	}
}
