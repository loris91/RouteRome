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

	public boolean addLuogo(int key, Luogo luogo) {
		return this.dao.insert(luogo);
	}

	public List<Luogo> getLuogoByCategoria(String tag, Integer rate) {
		return this.dao.findByCategoria(tag, rate);
	}
	
	public List<Luogo> getLuoghi() {
		return this.dao.findAll();
	}

	public Luogo getLuogoById(String idLuogo) {
		return this.dao.getLuogoByID(idLuogo);
	}
}
