package persistence;

import java.util.List;

import model.Questionario;

public interface QuestionarioDAO {
	
	boolean insert(Questionario questionario);
	
	Questionario findByID(String id);
	
	List<Questionario> findAll();

}