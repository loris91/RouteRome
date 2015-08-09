package model.facade;

import java.util.List;

import model.Item;
import persistence.postgres.ItemDAOPostgres;

public class FacadeItem {
	private ItemDAOPostgres dao;

	public FacadeItem() {
		super();
		this.dao = new ItemDAOPostgres();
	}

	public boolean addItem(int key, Item item) {
		return this.dao.insert(key,item);
	}

	public List<Item> getItemByCategoria(String tag) {
		return this.dao.findByCategoria(tag);
	}
	
	public List<Item> getItems() {
		return this.dao.findAll();
	}
}
