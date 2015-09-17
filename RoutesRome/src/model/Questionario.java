package model;

import java.util.Map;

public class Questionario {

	private String id;
	private Map<String, Integer> preferenze;
	
	public Questionario() {
		// TODO Auto-generated constructor stub
	}
	
	public Questionario(String id, Map<String, Integer> preferenze) {
		super();
		this.id = id;
		this.preferenze = preferenze;
	}

	public String getId() {
		return id;
	}
	
	public void setUtente(String id) {
		this.id = id;
	}
	
	public Map<String, Integer> getPreferenze() {
		return preferenze;
	}
	
	public void setPreferenze(Map<String, Integer> preferenze) {
		this.preferenze = preferenze;
	}

	@Override
	public String toString() {
		return "Questionario [id=" + id + ", preferenze=" + preferenze + "]";
	}
	
	
}
