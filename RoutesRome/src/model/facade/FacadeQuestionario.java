package model.facade;

import java.util.List;

import model.Questionario;
import persistence.clusterPoint.QuestionarioDAOClusterPoint;

public class FacadeQuestionario {
	private QuestionarioDAOClusterPoint dao;

	public FacadeQuestionario() {
		super();
		this.dao = new QuestionarioDAOClusterPoint();
	}

	public boolean addQuestionario(Questionario questionario) {
		return this.dao.insert(questionario);
	}

	public Questionario getQuestionarioByID(String id) {
		return this.dao.findByID(id);
	}
	
	public List<Questionario> getQuestionari() {
		return this.dao.findAll();
	}
	
	public boolean existQuestionario(String id) {
		return this.dao.exist(id);
	}

}
