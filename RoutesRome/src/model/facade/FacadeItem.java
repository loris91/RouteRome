package model.facade;

import java.util.List;

import model.Luogo;
import persistence.clusterPoint.ItemDAOClusterPoint;

public class FacadeItem {
	private ItemDAOClusterPoint dao;

	public FacadeItem() {
		super();
		this.dao = new ItemDAOClusterPoint();
	}

	public boolean addItem(int key, Luogo item) {
		return this.dao.insert(item);
	}

	public List<Luogo> getItemByCategoria(String tag) {
		return this.dao.findByCategoria(tag);
	}
	
	public List<Luogo> getItems() {
		return this.dao.findAll();
	}
}
