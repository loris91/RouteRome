package persistence.clusterPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSSearchRequest;
import com.clusterpoint.api.request.CPSUpdateRequest;
import com.clusterpoint.api.response.CPSSearchResponse;

import persistence.LuoghiVisitatiDAO;

public class LuoghiVisitatiDAOClusterPoint implements LuoghiVisitatiDAO {
	private DataSource data;

	public LuoghiVisitatiDAOClusterPoint() {
		super();
		this.data = new DataSource();
	}

	@Override
	public boolean insert(String idUtente, List<String> luoghiRaccomandati) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("LuoghiVisitati");
			List<String> luoghiVisitati = this.findByID(idUtente);
			luoghiVisitati.addAll(luoghiRaccomandati);

			String doc = "<document><id>"+idUtente+"</id><luoghi>"+getPartOfQuery(luoghiVisitati)+"</luoghi></document>";
			CPSUpdateRequest update_req = new CPSUpdateRequest(doc);
			connessione.sendRequest(update_req);

			esito = true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Esito Inserimento Luoghi Visitati: " + esito);
		return esito;
	}

	@Override
	public List<String> findByID(String idUtente) {
		boolean esito = false;
		
		List<String> luoghiVisitati = new ArrayList<String>();
		CPSConnection connessione;
		
		try {
			connessione = this.data.getConnection("LuoghiVisitati");

			String query = "<id>" + idUtente + "</id>";

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
					NodeList luoghi = attributes.item(1).getChildNodes();

					for (int i = 0; i < luoghi.getLength()-1; i++) {
						String idLuogo = luoghi.item(i).getTextContent();
						luoghiVisitati.add(idLuogo);
						esito = true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Esito Recupero Luoghi Visitati in base al suo ID: " + esito);
		return luoghiVisitati;
	}

	private String getPartOfQuery(List<String> luoghiVisitati) {
		String partOfQuery = "";

		for (String luogoVisitato : luoghiVisitati) {
			partOfQuery = partOfQuery.concat("<luogo>" + luogoVisitato + "</luogo>");
		}
		return partOfQuery;
	}

}
