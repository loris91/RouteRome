package model.facade;

import java.util.Map;

import persistence.clusterPoint.TagRimossiDAOClusterPoint;

public class FacadeTagRimossi {
	private TagRimossiDAOClusterPoint dao;

	public FacadeTagRimossi() {
		super();
		this.dao = new TagRimossiDAOClusterPoint();
	}

	public boolean addTagRimosso(String idUtente, String tag, int rate) {
		return this.dao.insert(idUtente, tag, rate);
	}
	
	public Map<String, Integer> getTagRimossi(String idUtente) {
		return this.dao.findByUtente(idUtente);
	}
}
