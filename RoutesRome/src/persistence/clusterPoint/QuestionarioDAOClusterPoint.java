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

import model.Questionario;
import persistence.QuestionarioDAO;

public class QuestionarioDAOClusterPoint implements QuestionarioDAO {
	private DataSource data;

	public QuestionarioDAOClusterPoint() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(Questionario questionario) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Questionari");

			String id = questionario.getId();
			Map<String, Integer> preferene = questionario.getPreferenze();

			List<String> docs = new ArrayList<String>();
			docs.add("<document><id>" + id + "</id><musei>" + preferene.get("Musei") + "</musei><chiesa>"
					+ preferene.get("Chiesa") + "</chiesa><sitiStorici>" + preferene.get("SitiStorici")
					+ "</sitiStorici><arene>" + preferene.get("Arene") + "</arene><edificiArchitettonici>"
					+ preferene.get("EdificiArchitettonici") + "</edificiArchitettonici><sitiReligiosi>"
					+ preferene.get("SitiReligiosi") + "</sitiReligiosi><villa>" + preferene.get("Villa")
					+ "</villa></document>");

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
		System.out.println("Esito Inserimento Questionario: " + esito);
		return esito;

	}

	@Override
	public Questionario findByID(String id) {
		boolean esito = false;

		Questionario questionario = new Questionario();

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Questionario");

			String query = "<id>" + id + "</id>";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 5;
			// return these fields from the documents
			Map<String, String> list = new HashMap<String, String>();
			list.put("id", "yes");

			CPSSearchRequest search_req = new CPSSearchRequest(query, offset, docs, list);
			CPSSearchResponse search_resp = (CPSSearchResponse) connessione.sendRequest(search_req);

			if (search_resp.getHits() > 0) {
				List<Element> documents = search_resp.getDocuments();

				for (Element element : documents) {
					NodeList attributes = element.getChildNodes();
					int musei = Integer.parseInt(attributes.item(1).getTextContent());
					int chiesa = Integer.parseInt(attributes.item(2).getTextContent());
					int sitiStorici = Integer.parseInt(attributes.item(3).getTextContent());
					int arene = Integer.parseInt(attributes.item(4).getTextContent());
					int edificiArchitettonici = Integer.parseInt(attributes.item(5).getTextContent());
					int sitiReligiosi = Integer.parseInt(attributes.item(6).getTextContent());
					int villa = Integer.parseInt(attributes.item(7).getTextContent());
					Map<String, Integer> preferenze = new HashMap<String, Integer>();
					preferenze.put("Musei", musei);
					preferenze.put("Chiesa", chiesa);
					preferenze.put("SitiStorici", sitiStorici);
					preferenze.put("Arene", arene);
					preferenze.put("EdificiArchitettonici", edificiArchitettonici);
					preferenze.put("SitiReligiosi", sitiReligiosi);
					preferenze.put("Villa", villa);
					questionario = new Questionario(id, preferenze);
					esito = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Esito Recupero Questionario in base al suo Username: " + esito);
		return questionario;

	}

	@Override
	public List<Questionario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(String id) {
		boolean esito = false;

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Questionario");

			String query = "<id>" + id + "</id>";

			// return documents starting with the first one - offset 0
			int offset = 0;
			// return not more than 5 documents
			int docs = 5;
			// return these fields from the documents
			Map<String, String> list = new HashMap<String, String>();
			list.put("id", "yes");

			CPSSearchRequest search_req = new CPSSearchRequest(query, offset, docs, list);
			CPSSearchResponse search_resp = (CPSSearchResponse) connessione.sendRequest(search_req);

			if (search_resp.getHits() > 0) {
				esito = true;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Esito Verifica Esistenza Questionario in base al suo Username: " + esito);
		return esito;

	}

}
