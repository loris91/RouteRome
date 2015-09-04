package persistence.clusterPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSInsertRequest;
import com.clusterpoint.api.request.CPSSearchRequest;
import com.clusterpoint.api.response.CPSSearchResponse;

import model.Coordinata;
import model.Luogo;
import persistence.LuogoDAO;

public class LuogoDAOClusterPoint implements LuogoDAO {
	private DataSource data;

	public LuogoDAOClusterPoint() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(Luogo luogo) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Luoghi");

			String id = luogo.getId();
			String nome = luogo.getNome();
			String via = luogo.getVia();
			int durata = luogo.getDurata();

			List<String> docs = new ArrayList<String>();
			docs.add("<document><id>" + id + "</id><nome>" + nome + "</nome><via>" + via + "</via><durata>" + durata
					+ "</durata></document>");

			// Create Insert request
			CPSInsertRequest insert_req = new CPSInsertRequest();
			// Add documents to request
			insert_req.setStringDocuments(docs);
			// Send request
			connessione.sendRequest(insert_req);
			esito = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Esito Inserimento Luogo: " + esito);
		return esito;
	}

	@Override
	public List<Luogo> findByCategoria(String tag, Integer rate) {
		boolean esito = false;

		List<Luogo> luoghi = new ArrayList<Luogo>();

		CPSConnection connessione;

		try {
			connessione = this.data.getConnection("Luoghi");

			String query = "<tags><tag><tipo>" + tag + "</tipo></tag></tags>";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 100;
			// return these fields from the documents
			Map<String, String> list = new HashMap<String, String>();
			list.put("id", "yes");

			CPSSearchRequest search_req = new CPSSearchRequest(query, offset, docs, list);
			CPSSearchResponse search_resp = (CPSSearchResponse) connessione.sendRequest(search_req);

			if (search_resp.getHits() > 0) {
				List<Element> documents = search_resp.getDocuments();

				for (Element element : documents) {
					NodeList attributes = element.getChildNodes();
					String id = attributes.item(0).getTextContent();
					String nome = attributes.item(1).getTextContent();
					String via = attributes.item(2).getTextContent();
					int durata = Integer.parseInt(attributes.item(3).getTextContent());
					double lat = Double.parseDouble(attributes.item(4).getChildNodes().item(0).getTextContent());
					double lon = Double.parseDouble(attributes.item(4).getChildNodes().item(1).getTextContent());
					Coordinata coordinata = new Coordinata(lat, lon);

					Map<String, Integer> tags = new HashMap<String, Integer>();
					NodeList tagNodes = attributes.item(5).getChildNodes();
					for (int i = 0; i < tagNodes.getLength() - 1; i++) {
						Node tagNode = tagNodes.item(i);
						String tagName = tagNode.getChildNodes().item(0).getTextContent();
						int tagRate = Integer.parseInt(tagNode.getChildNodes().item(1).getTextContent());
						tags.put(tagName, tagRate);
					}

					Luogo luogo = new Luogo(id, nome, via, coordinata, durata, tags);
					if (luogo.hasTag(tag, rate)) {
						luoghi.add(luogo);
					}
					esito = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Esito Recupero Luogo in base alla Categoria: " + esito);
		return luoghi;
	}

	@Override
	public List<Luogo> findAll() {
		boolean esito = false;

		List<Luogo> items = new ArrayList<Luogo>();

		CPSConnection connessione;

		try {
			connessione = this.data.getConnection("Luoghi");

			String query = "*";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 100;
			// return these fields from the documents
			Map<String, String> list = new HashMap<String, String>();
			list.put("id", "yes");

			CPSSearchRequest search_req = new CPSSearchRequest(query, offset, docs, list);
			CPSSearchResponse search_resp = (CPSSearchResponse) connessione.sendRequest(search_req);

			if (search_resp.getHits() > 0) {
				List<Element> documents = search_resp.getDocuments();

				for (Element element : documents) {
					NodeList attributes = element.getChildNodes();
					String id = attributes.item(0).getTextContent();
					String nome = attributes.item(1).getTextContent();
					String via = attributes.item(2).getTextContent();
					int durata = Integer.parseInt(attributes.item(3).getTextContent());
					double lat = Double.parseDouble(attributes.item(4).getChildNodes().item(0).getTextContent());
					double lon = Double.parseDouble(attributes.item(4).getChildNodes().item(1).getTextContent());
					Coordinata coordinata = new Coordinata(lat, lon);

					Map<String, Integer> tags = new HashMap<String, Integer>();
					NodeList tagNodes = attributes.item(5).getChildNodes();
					for (int i = 0; i < tagNodes.getLength() - 1; i++) {
						Node tagNode = tagNodes.item(i);
						String tagName = tagNode.getChildNodes().item(0).getTextContent();
						int tagRate = Integer.parseInt(tagNode.getChildNodes().item(1).getTextContent());
						tags.put(tagName, tagRate);
					}

					Luogo luoghi = new Luogo(id, nome, via, coordinata, durata, tags);

					items.add(luoghi);
					esito = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Esito Recupero tutti i Luoghi: " + esito);
		return items;

	}

	public Luogo getLuogoByID(String idLuogo) {
		boolean esito = false;

		Luogo luogo = null;

		CPSConnection connessione;

		try {
			connessione = this.data.getConnection("Luoghi");

			String query = "<id>" + idLuogo + "</id>";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 100;
			// return these fields from the documents
			Map<String, String> list = new HashMap<String, String>();
			list.put("id", "yes");

			CPSSearchRequest search_req = new CPSSearchRequest(query, offset, docs, list);
			CPSSearchResponse search_resp = (CPSSearchResponse) connessione.sendRequest(search_req);

			if (search_resp.getHits() > 0) {
				List<Element> documents = search_resp.getDocuments();

				for (Element element : documents) {
					NodeList attributes = element.getChildNodes();
					String id = attributes.item(0).getTextContent();
					String nome = attributes.item(1).getTextContent();
					String via = attributes.item(2).getTextContent();
					int durata = Integer.parseInt(attributes.item(3).getTextContent());
					double lat = Double.parseDouble(attributes.item(4).getChildNodes().item(0).getTextContent());
					double lon = Double.parseDouble(attributes.item(4).getChildNodes().item(1).getTextContent());
					Coordinata coordinata = new Coordinata(lat, lon);

					Map<String, Integer> tags = new HashMap<String, Integer>();
					NodeList tagNodes = attributes.item(5).getChildNodes();
					for (int i = 0; i < tagNodes.getLength() - 1; i++) {
						Node tagNode = tagNodes.item(i);
						String tagName = tagNode.getChildNodes().item(0).getTextContent();
						int tagRate = Integer.parseInt(tagNode.getChildNodes().item(1).getTextContent());
						tags.put(tagName, tagRate);
					}

					luogo = new Luogo(id, nome, via, coordinata, durata, tags);
					esito = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Esito Recupero Luogo in base al suo ID: " + esito);
		return luogo;
	}

}
