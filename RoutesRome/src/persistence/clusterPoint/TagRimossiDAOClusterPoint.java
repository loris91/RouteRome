package persistence.clusterPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSSearchRequest;
import com.clusterpoint.api.request.CPSUpdateRequest;
import com.clusterpoint.api.response.CPSSearchResponse;

import persistence.TagRimossiDAO;

public class TagRimossiDAOClusterPoint implements TagRimossiDAO {
	private DataSource data;

	public TagRimossiDAOClusterPoint() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(String idUtente, String tag, int rate) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("TagRimossi");
			Map<String, Integer> tagRimossi = this.findByUtente(idUtente);
			tagRimossi.put(tag, rate);

			String doc = "<document><id>" + idUtente + "</id><tags>" + this.getStringa(tagRimossi) + "</tags></document>";
			CPSUpdateRequest update_req = new CPSUpdateRequest(doc);
			connessione.sendRequest(update_req);

			esito = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

	@Override
	public Map<String, Integer> findByUtente(String idUtente) {
		Map<String, Integer> tagRimossi = new HashMap<String, Integer>();
		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("TagRimossi");

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
					NodeList tags = attributes.item(1).getChildNodes();

					for (int i = 0; i < tags.getLength() - 1; i++) {
						NodeList tag = attributes.item(i).getChildNodes();
						String tagName = tag.item(0).getTextContent();
						int tagRate = Integer.parseInt(tag.item(1).getTextContent());
						System.out.println("tagName: " + tagName);
						System.out.println("tagRate: " + tagRate);
						tagRimossi.put(tagName, tagRate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagRimossi;
	}

	private String getStringa(Map<String, Integer> tagRimossi) {
		String partOfQuery = "";
		Set<String> keys = tagRimossi.keySet();

		for (String key : keys) {
			partOfQuery = partOfQuery.concat("<tag><tipo>" + key + "</tipo><rate>" + tagRimossi.get(key) + "</tag>");
		}

		return partOfQuery;
	}
}
