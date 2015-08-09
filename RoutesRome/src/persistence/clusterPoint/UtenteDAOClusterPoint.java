package persistence.clusterPoint;

import java.util.ArrayList;
import java.util.List;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSInsertRequest;

import model.Utente;
import persistence.UtenteDAO;

public class UtenteDAOClusterPoint implements UtenteDAO {
	private DataSource data;

	public UtenteDAOClusterPoint() {
		this.data = new DataSource();
	}
	
	@Override
	public boolean insert(Utente utente) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("User");

			String username = utente.getUsername();
			String password = utente.getPassword();

			List<String> docs = new ArrayList<String>();
			docs.add("<document><id>" + username + "</id><password>" + password
					+ "</password>");

			// Create Insert request
			CPSInsertRequest insert_req = new CPSInsertRequest();
			// Add documents to request
			insert_req.setStringDocuments(docs);
			// Send request
			connessione.sendRequest(insert_req);
			esito = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

	@Override
	public boolean delete(Utente Utente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utente findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
