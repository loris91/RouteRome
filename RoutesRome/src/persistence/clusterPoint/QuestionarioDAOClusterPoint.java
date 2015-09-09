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
			docs.add("<document><id>" + id + "</id><arena>" + preferene.get("Arena") + "</arena><castello>"
			+ preferene.get("Castello") + "</castello><chiesa>" + preferene.get("Chiesa") + "</chiesa><cimitero>" 
			+ preferene.get("Cimitero") + "</cimitero><edificio>" + preferene.get("EdificioArchitettonico") 
			+ "</edificio><fontana>" + preferene.get("Fontana") + "</fontana><giardino>" + preferene.get("Giardino") 
			+ "</giardino><monumento>" + preferene.get("Monumento") + "</monumento><marte>" + preferene.get("MuseoArte") 
			+ "</marte><msettore>" + preferene.get("MuseoSettore") + "</msettore><mstorico>" + preferene.get("MuseoStorico") 
			+ "</mstorico><palazzo>" + preferene.get("Palazzo") + "</palazzo><parco>" + preferene.get("ParchiNatura") 
			+ "</parco><passeggiata>" + preferene.get("PasseggiateSitoStorico") + "</passeggiata><piazza>" + preferene.get("Piazza") 
			+ "</piazza><ponte>" + preferene.get("Ponte") + "</ponte><rovine>" + preferene.get("RovineAntiche") + "</rovine><sreligioso>" 
			+ preferene.get("SitoReligioso") + "</sreligioso><sstorico>" + preferene.get("SitoStorico") + "</sstorico><villa>"
			+ preferene.get("Villa") + "</villa></document>");

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
		System.out.println("Esito Inserimento Questionario: " + esito);
		return esito;

	}

	@Override
	public Questionario findByID(String id) {
		boolean esito = false;

		Questionario questionario = new Questionario();

		CPSConnection connessione;
		try {
			connessione = this.data.getConnection("Questionari");

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
					int arena = Integer.parseInt(attributes.item(1).getTextContent());
					int castello = Integer.parseInt(attributes.item(2).getTextContent());
					int chiesa = Integer.parseInt(attributes.item(3).getTextContent());
					int cimitero = Integer.parseInt(attributes.item(4).getTextContent());
					int edificio = Integer.parseInt(attributes.item(5).getTextContent());
					int fontana = Integer.parseInt(attributes.item(6).getTextContent());
					int giardino = Integer.parseInt(attributes.item(7).getTextContent());
					int monumento = Integer.parseInt(attributes.item(8).getTextContent());
					int marte = Integer.parseInt(attributes.item(9).getTextContent());
					int msettore = Integer.parseInt(attributes.item(10).getTextContent());
					int mstorico = Integer.parseInt(attributes.item(11).getTextContent());
					int palazzo = Integer.parseInt(attributes.item(12).getTextContent());
					int parco = Integer.parseInt(attributes.item(13).getTextContent());
					int passeggiata = Integer.parseInt(attributes.item(14).getTextContent());
					int piazza = Integer.parseInt(attributes.item(15).getTextContent());
					int ponte = Integer.parseInt(attributes.item(16).getTextContent());
					int rovine = Integer.parseInt(attributes.item(17).getTextContent());
					int sreligioso = Integer.parseInt(attributes.item(18).getTextContent());
					int sstorico = Integer.parseInt(attributes.item(19).getTextContent());
					int villa = Integer.parseInt(attributes.item(20).getTextContent());
					
					Map<String, Integer> preferenze = new HashMap<String, Integer>();
					
					preferenze.put("Arena", arena);
					preferenze.put("Castello", castello);
					preferenze.put("Chiesa", chiesa);
					preferenze.put("Cimitero", cimitero);
					preferenze.put("EdificioArchitettonico", edificio);
					preferenze.put("Fontana", fontana);
					preferenze.put("Giardino", giardino);
					preferenze.put("Monumento", monumento);
					preferenze.put("MuseoArte", marte);
					preferenze.put("MuseoSettore", msettore);
					preferenze.put("MuseoStorico", mstorico);
					preferenze.put("Palazzo", palazzo);
					preferenze.put("ParchiNatura", parco);
					preferenze.put("PasseggiateSitoStorico", passeggiata);
					preferenze.put("Piazza", piazza);
					preferenze.put("Ponte", ponte);
					preferenze.put("RovineAntiche", rovine);
					preferenze.put("SitoReligioso", sreligioso);
					preferenze.put("SitoStorico", sstorico);
					preferenze.put("Villa", villa);
					esito = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			connessione = this.data.getConnection("Questionari");

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
			e.printStackTrace();
		}
		System.out.println("Esito Verifica Esistenza Questionario in base al suo Username: " + esito);
		return esito;

	}

}
