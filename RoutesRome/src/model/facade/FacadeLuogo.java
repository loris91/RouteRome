package model.facade;

import java.util.List;

import model.Luogo;
import persistence.clusterPoint.LuogoDAOClusterPoint;

public class FacadeLuogo {
	private LuogoDAOClusterPoint dao;

	public FacadeLuogo() {
		super();
		this.dao = new LuogoDAOClusterPoint();
	}

	public boolean addItem(int key, Luogo luogo) {
		return this.dao.insert(luogo);
	}

	public List<Luogo> getItemByCategoria(String tag) {
		return this.dao.findByCategoria(tag);
	}
	
	public List<Luogo> getItems() {
		return this.dao.findAll();
	}
}
