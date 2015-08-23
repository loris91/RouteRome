package model.facade;

import java.util.List;

import persistence.LuoghiVisitatiDAO;
import persistence.clusterPoint.LuoghiVisitatiDAOClusterPoint;

public class FacadeLuoghiVisitati {
	private LuoghiVisitatiDAO dao;
	
	public FacadeLuoghiVisitati(){
		super();
		this.dao = new LuoghiVisitatiDAOClusterPoint();
	}
	
	
	public boolean addList(String idUtente, List<String> luoghiRaccomandati){
		return this.dao.insert(idUtente, luoghiRaccomandati);
	}
	
	public List<String> getLuoghiVisitati(String idUtente){
		return this.dao.findByID(idUtente);
	}

}
