package persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Coordinata;
import model.Item;
import persistence.ItemDAO;

public class ItemDAOPostgres implements ItemDAO {
	private DataSource data;

	public ItemDAOPostgres() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(int key, Item item) {
		boolean esito = false;

		try {
			Connection connessione = this.data.getConnection();
			String query = "INSERT INTO edificiculturali (key,nome,via,latitudine,longitudine,categoria,durata) VALUES (?,?,?,?,?,?,?)";
			String nome = item.getNome();
			String via = item.getVia();
			Double latitudine = item.getCoordinata().getLatitudine();
			Double longitudine = item.getCoordinata().getLongitudine();
			String categoria = item.getCategoria();
			int durata = item.getDurata();

			try {
				PreparedStatement p = connessione.prepareStatement(query);
				p.setInt(1, key);
				p.setString(2, nome);
				p.setString(3, via);
				p.setDouble(4, latitudine);
				p.setDouble(5, longitudine);
				p.setString(6, categoria);
				p.setInt(7, durata);

				p.executeUpdate();
				esito = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connessione.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		return esito;
	}


	@Override
	public List<Item> findByCategoria(String tag) {
		List<Item> items = new LinkedList<Item>();
		Item item = null;
		String nome;
		String via;
		String categoria;
		int durata;
		Coordinata coordinata;

		try {
			Connection connessione = this.data.getConnection();
			String query = "SELECT * FROM edificiculturali WHERE categoria = ?";

			try {
				PreparedStatement p = connessione.prepareStatement(query);
				p.setString(1, tag);
				ResultSet result = p.executeQuery();
				while (result.next()) {
					nome = result.getString("nome");
					via = result.getString("via");
					coordinata = new Coordinata(result.getDouble("latitudine"), 
							result.getDouble("longitudine"));
					categoria = result.getString("categoria");
					durata = result.getInt("durata");
					
					item = new Item(nome, via, categoria, durata, coordinata);

					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connessione.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public List<Item> findAll() {
		List<Item> items = new LinkedList<Item>();
		Item item = null;
		String nome;
		String via;
		String categoria;
		int durata;
		Coordinata coordinata;

		try {
			Connection connessione = this.data.getConnection();
			String query = "SELECT * FROM edificiculturali";

			try {
				PreparedStatement p = connessione.prepareStatement(query);
				ResultSet result = p.executeQuery();
				while (result.next()) {
					nome = result.getString("nome");
					via = result.getString("via");
					coordinata = new Coordinata(result.getDouble("latitudine"), 
							result.getDouble("longitudine"));
					categoria = result.getString("categoria");
					durata = result.getInt("durata");
					
					item = new Item(nome, via, categoria, durata, coordinata);

					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connessione.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return items;
	}
}
