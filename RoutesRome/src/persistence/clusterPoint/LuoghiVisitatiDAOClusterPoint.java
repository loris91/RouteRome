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
import com.clusterpoint.api.request.CPSUpdateRequest;
import com.clusterpoint.api.response.CPSModifyResponse;
import com.clusterpoint.api.response.CPSSearchResponse;

import persistence.LuoghiVisitatiDAO;

public class LuoghiVisitatiDAOClusterPoint implements LuoghiVisitatiDAO {
	private DataSource data;

	public LuoghiVisitatiDAOClusterPoint() {
		super();
		this.data = new DataSource();
	}

	@Override
	public boolean insert(String idUtente, List luoghiRaccomandati) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("LuoghiVisitati");
			List<String> luoghiVisitati = this.luoghiVisitati(idUtente);
			System.out.println("Dimensione lista prima: "+ luoghiVisitati.size());
			luoghiVisitati.addAll(luoghiRaccomandati);
			System.out.println("Dimensione lista dopo: "+ luoghiVisitati.size());

			String doc = "<document><id>"+idUtente+"</id><luoghi>"+getStringa(luoghiVisitati)+"</luoghi></document>";
			CPSUpdateRequest update_req = new CPSUpdateRequest(doc);
			CPSModifyResponse update_resp = (CPSModifyResponse) connessione.sendRequest(update_req);

			esito = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

	@Override
	public List<String> luoghiVisitati(String idUtente) {
		List<String> luoghiVisitati = new ArrayList<String>();
		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("LuoghiVisitati");

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
					NodeList luoghi = attributes.item(1).getChildNodes();

					for (int i = 0; i < luoghi.getLength()-1; i++) {
						String idLuogo = luoghi.item(i).getTextContent();
						System.out.println("idLuogo: " + idLuogo);
						luoghiVisitati.add(idLuogo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return luoghiVisitati;
	}

	private String getStringa(List<String> luoghiVisitati) {
		String partOfQuery = "";

		for (String string : luoghiVisitati) {
			partOfQuery = partOfQuery.concat("<luogo>" + string + "</luogo>");
		}

		return partOfQuery;
	}

}
