package persistence.clusterPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSDeleteRequest;
import com.clusterpoint.api.request.CPSInsertRequest;
import com.clusterpoint.api.request.CPSSearchRequest;
import com.clusterpoint.api.response.CPSModifyResponse;
import com.clusterpoint.api.response.CPSSearchResponse;

import model.Item;
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
	public boolean delete(Utente utente) {
		Boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("User");

			String usernname = utente.getUsername();
			CPSDeleteRequest delete_req = new CPSDeleteRequest(usernname);
			connessione.sendRequest(delete_req);

			esito = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utente findByUsername(String username) {

		Utente utente = new Utente();

		CPSConnection connessione;

		try {
			connessione = this.data.getConnection("User");

			String query = "<id>" + username + "</id>";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 5;
			// return these fields from the documents
			Map<String, String> list = new HashMap<String, String>();
			list.put("id", "yes");

			CPSSearchRequest search_req = new CPSSearchRequest(query, offset,
					docs, list);
			CPSSearchResponse search_resp = (CPSSearchResponse) connessione
					.sendRequest(search_req);

			if (search_resp.getHits() > 0) {
				List<Element> documents = search_resp.getDocuments();

				for (Element element : documents) {
					NodeList attributes = element.getChildNodes();
					String id = attributes.item(0).getTextContent();
					String password = attributes.item(1).getTextContent();
					utente.setUsername(id);
					utente.setPassword(password);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utente;

	}

	@Override
	public boolean findByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
