package persistence.clusterPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSInsertRequest;
import com.clusterpoint.api.request.CPSSearchRequest;
import com.clusterpoint.api.response.CPSSearchResponse;

import model.Coordinata;
import model.Ristorante;
import persistence.RistoranteDAO;

public class RistoranteDAOClusterPoint implements RistoranteDAO {
	private DataSource data;

	public RistoranteDAOClusterPoint() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(Ristorante ristorante) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Ristoranti");

			String id = ristorante.getId();
			String nome = ristorante.getNome();
			String via = ristorante.getVia();

			List<String> docs = new ArrayList<String>();
			docs.add("<document><id>" + id + "</id><nome>" + nome
					+ "</nome><via>" + via + "</via><durata></document>");

			// Create Insert request
			CPSInsertRequest insert_req = new CPSInsertRequest();
			// Add documents to request
			insert_req.setStringDocuments(docs);
			// Send request
			connessione.sendRequest(insert_req);
			esito = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Esito Inserimento Ristorante: " + esito);
		return esito;
	}

	@Override
	public List<Ristorante> findAll() {
		boolean esito = false;

		List<Ristorante> resturants = new ArrayList<Ristorante>();
		
		CPSConnection connessione;

		try {
			connessione = this.data.getConnection("Ristoranti");

			String query = "*";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 100;
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
					String nome = attributes.item(1).getTextContent();
					String via = attributes.item(2).getTextContent();
					double lat = Double.parseDouble(attributes.item(3).getChildNodes().item(0).getTextContent());
					double lon = Double.parseDouble(attributes.item(3).getChildNodes().item(1).getTextContent());
					Coordinata coordinata = new Coordinata(lat,lon); 
					Ristorante ristorante = new Ristorante(id, nome, via, coordinata);
					resturants.add(ristorante);
					esito = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Esito Recupero tutti i Ristoranti: " + esito);
		return resturants;

	}

}
