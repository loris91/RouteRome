package bho;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.Coordinata;
import model.Luogo;
import model.facade.FacadeLuogo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserXml {
	public static void main(String[] args) {
		CoordinateHelper helper = new CoordinateHelper();
		Coordinata coordinata;
		String nome;
		String via;
		String categoria;
		int durata;
		Luogo item;
		FacadeLuogo facade = new FacadeLuogo();
		
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			Document document = builder.parse(new File("target/luoghi_culturali.xml"));
			
			NodeList elementi = document.getElementsByTagName("item");
			System.out.println("Totale : " + elementi.getLength()); 
			
			for (int i = 0; i < elementi.getLength(); i++) {
				Node nodo = elementi.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					nome = elemento.getElementsByTagName("nome").item(0)
							.getFirstChild().getNodeValue();
					via = elemento.getElementsByTagName("via")
							.item(0).getFirstChild().getNodeValue();
					categoria = elemento.getElementsByTagName("tag")
							.item(0).getFirstChild().getNodeValue();
					durata = Integer.parseInt(elemento.getElementsByTagName("durata").item(0).getFirstChild().getNodeValue());
					coordinata = helper.getCoordinate(via);	
					item = new Luogo(nome, via, categoria, durata, coordinata);
					System.out.println(i);
					facade.addItem(i,item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Terminato!");
	}
}
