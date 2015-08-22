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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

	@Override
	/* 
	 * Metodo da rivedere poich� ritorna sempre liste non nulle ma vuote
	 */
	public List<Luogo> findByCategoria(String tag, Integer rate) {

		List<Luogo> items = new ArrayList<Luogo>();

		CPSConnection connessione;

		try {
			connessione = this.data.getConnection("Luoghi");

			String query = "<document><tags><tag><tipo>" + tag + "</tipo><rate>" + rate
					+ "</rate></tag></tags></document>";

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

					Luogo luoghi = new Luogo(id, nome, via, durata, coordinata, tags);

					items.add(luoghi);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public List<Luogo> findAll() {

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

					Luogo luoghi = new Luogo(id, nome, via, durata, coordinata, tags);

					items.add(luoghi);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;

	}

	public Luogo getLuogoByID(String idLuogo) {
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

					luogo = new Luogo(id, nome, via, durata, coordinata, tags);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return luogo;
	}

}
