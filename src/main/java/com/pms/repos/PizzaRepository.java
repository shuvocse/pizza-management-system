package com.pms.repos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pms.model.Supplier;
import com.pms.utils.Concat;
import com.pms.utils.IngForRestockDTO;
import com.pms.utils.IngredientDTO;
import com.pms.utils.OrderDTO;
import com.pms.utils.PizzaOrderDTO;
import com.pms.utils.SupplierDto;

@Repository
public class PizzaRepository {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<IngredientDTO> getIngredientsForCustomer() {
		List<IngredientDTO> ingredient = new ArrayList<IngredientDTO>();
		List<Object[]> results = em.createNativeQuery("SELECT * FROM GET_INGREDIENT_LIST_FOR_CUSTOMER()")
				.getResultList();

		if (results != null) {

			results.stream().forEach((record) -> {

				int id = ((int) record[0]);
				String name = ((String) record[1]);
				double price = ((double) record[2]);
				String origin = ((String) record[3]);

				ingredient.add(new IngredientDTO(id, name, price, origin));
			});
		}
		return ingredient;
	}

	public void saveOrder(OrderDTO o) {
		em.createNativeQuery("SELECT SAVE_ORDER(:name, :size, :ings)").setParameter("name", o.getBasePizza())
				.setParameter("size", o.getPizzaSize()).setParameter("ings", Concat.concatString(o.getIngredient()))
				.getSingleResult();

	}

	public List<PizzaOrderDTO> getOrderList() {
		List<Object[]> results = em.createNativeQuery("SELECT * FROM GET_ORDER_LIST()").getResultList();
		List<PizzaOrderDTO> pos = new ArrayList<>();
		if (results != null) {

			results.stream().forEach((record) -> {

				int id = ((int) record[0]);
				String name = ((String) record[1]);
				String ing = ((String) record[2]);
				int size = ((int) record[3]);

				pos.add(new PizzaOrderDTO(id, name, ing, size));
			});
		}
		return pos;
	}

	@SuppressWarnings("unchecked")
	public List<IngForRestockDTO> getIngredientsWithSupplier() {
		List<Object[]> results = em.createNativeQuery("SELECT * FROM GET_SUPPLIER_LIST_WITH_INGREDIENTS_FOR_RESTOCK();")
				.getResultList();
		List<IngForRestockDTO> iws = new ArrayList<>();
		if (results != null) {

			results.stream().forEach((record) -> {

				int supId = ((int) record[0]);
				String supName = ((String) record[1]);
				int ingId = ((int) record[2]);
				String ingName = ((String) record[3]);

				iws.add(new IngForRestockDTO(supName, supId, ingId, ingName));
			});
		}
		return iws;
	}

	public void restockIngredients(int ingId, int quantity) {
		em.createNativeQuery("SELECT RESTOCK_INGREDIENT(:id, :noi)").setParameter("id", ingId)
				.setParameter("noi", quantity).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<IngredientDTO> getIngredientsForBecker() {
		List<IngredientDTO> ingredient = new ArrayList<IngredientDTO>();
		List<Object[]> results = em.createNativeQuery("SELECT * FROM GET_INGREDIENT_LIST_FOR_BAKER()").getResultList();

		if (results != null) {

			results.stream().forEach((record) -> {

				int id = ((int) record[0]);
				String name = ((String) record[1]);
				int noi = ((int) record[2]);
				double price = ((double) record[3]);
				String origin = ((String) record[4]);
				boolean status = ((boolean) record[5]);

				ingredient.add(new IngredientDTO(id, name, price, origin, noi, status));
			});
		}
		return ingredient;
	}

	public void changeIngStatus(int id, boolean status) {
		em.createNativeQuery("SELECT UPDATE_INGREDIENT_STATUS(:status, :id)").setParameter("id", id)
		.setParameter("status", status).getSingleResult();
	}

	public void removeIngredient(int id) {
		em.createNativeQuery("SELECT REMOVE_INGREDIENT(:id)").setParameter("id", id).getSingleResult();
	}

	public void saveIngredient(IngredientDTO ing) {
		em.createNativeQuery("SELECT SAVE_INGREDIENT(:name, :price, :origin, :status)")
		.setParameter("name", ing.getName())
		.setParameter("status", true)
		.setParameter("price", ing.getPrice())
		.setParameter("origin", ing.getOrigin())
		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplier> getSuppliers() {
		List<Supplier> sups = new ArrayList<Supplier>();
		List<Object[]> results = em.createNativeQuery("SELECT * FROM GET_SUPPLIER_LIST()").getResultList();

		if (results != null) {

			results.stream().forEach((record) -> {

				int id = ((int) record[0]);
				String name = ((String) record[1]);
				boolean status = ((boolean) record[2]);

				sups.add(new Supplier(id, name, status));
			});
		}
		return sups;
	}

	public void changeSupStatus(int id, boolean status) {
		em.createNativeQuery("SELECT UPDATE_SUPPLIER_STATUS(:id, :status)").setParameter("id", id)
		.setParameter("status", status).getSingleResult();
	}
	
	public void removeSupplier(int id) {
		em.createNativeQuery("SELECT REMOVE_SUPPLIER(:id)").setParameter("id", id).getSingleResult();
	}

	public void saveSupplier(SupplierDto sup) {
		em.createNativeQuery("SELECT SAVE_SUPPLIER(:name, :ids)").setParameter("name", sup.getName())
		.setParameter("ids", Concat.concatString(sup.getIngIds()))
		.getSingleResult();

	}

}
