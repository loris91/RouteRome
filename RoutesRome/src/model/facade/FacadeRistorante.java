package model.facade;

import java.util.List;

import model.Ristorante;
import persistence.clusterPoint.RistoranteDAOClusterPoint;

public class FacadeRistorante {
	private RistoranteDAOClusterPoint dao;

	public FacadeRistorante() {
		super();
		this.dao = new RistoranteDAOClusterPoint();
	}

	public boolean addRistorante(int key, Ristorante ristorante) {
		return this.dao.insert(ristorante);
	}
	
	public List<Ristorante> getRistoranti() {
		return this.dao.findAll();
	}
}
