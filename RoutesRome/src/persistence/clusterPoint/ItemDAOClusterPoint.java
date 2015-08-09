package persistence.clusterPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSInsertRequest;
import com.clusterpoint.api.response.CPSModifyResponse;

import model.Item;
import persistence.ItemDAO;

public class ItemDAOClusterPoint implements ItemDAO {
	private DataSource data;

	public ItemDAOClusterPoint() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(Item item) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Luoghi");

			String id = item.getId();
			String nome = item.getNome();
			String via = item.getVia();
			int durata = item.getDurata();

			List<String> docs = new ArrayList<String>();
			docs.add("<document><id>" + id + "</id><nome>" + nome
					+ "</nome><via>" + via + "</via><durata>" + durata
					+ "</durata></document>");

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
	public List<Item> findByCategoria(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
