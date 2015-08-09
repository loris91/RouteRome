package model.facade;

import java.util.List;

import model.Item;
import persistence.clusterPoint.ItemDAOClusterPoint;

public class FacadeItem {
	private ItemDAOClusterPoint dao;

	public FacadeItem() {
		super();
		this.dao = new ItemDAOClusterPoint();
	}

	public boolean addItem(int key, Item item) {
		return this.dao.insert(item);
	}

	public List<Item> getItemByCategoria(String tag) {
		return this.dao.findByCategoria(tag);
	}
	
	public List<Item> getItems() {
		return this.dao.findAll();
	}
}
