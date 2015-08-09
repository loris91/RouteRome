package persistence.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Utente;
import persistence.UtenteDAO;

public class UtenteDAOPostgress implements UtenteDAO{
	private DataSource data;

	public UtenteDAOPostgress() {
		this.data = new DataSource();
	}

	@Override
	public boolean insert(Utente utente) {
		boolean esito = false;

		try {
			Connection connessione = this.data.getConnection();
			String query = "INSERT INTO utenti (username,password,email,nome,cognome) VALUES (?,?,?,?,?)";
			String username = utente.getUsername();
			String password = utente.getPassword();
			String email = utente.getEmail();
			String nome = utente.getNome();
			String cognome = utente.getCognome();

			try {
				PreparedStatement p = connessione.prepareStatement(query);
				p.setString(1, username);
				p.setString(2, password);
				p.setString(3, email);
				p.setString(4, nome);
				p.setString(5, cognome);

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
	public boolean delete(Utente utente) {
		boolean esito = false;

		try {
			Connection connessione = this.data.getConnection();
			String query = "DELETE FROM utenti WHERE username=?";
			String username = utente.getUsername();

			try {
				PreparedStatement p = connessione.prepareStatement(query);
				p.setString(1, username);
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
	public boolean deleteAll() {
		boolean esito = false;

		try {
			Connection connessione = this.data.getConnection();
			String query = "DELETE FROM utenti";

			try {
				PreparedStatement p = connessione.prepareStatement(query);
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
	public Utente findByUsername(String username) {
		Utente utente = null;

		try {
			Connection connessione = this.data.getConnection();
			String query = "SELECT * FROM utenti WHERE username=?";
			try {
				PreparedStatement p = connessione.prepareStatement(query);
				p.setString(1, username);
				ResultSet result = p.executeQuery();

				if (result.next()) {
					utente = new Utente();
					utente.setUsername(result.getString("username"));
					utente.setPassword(result.getString("password"));
					utente.setEmail(result.getString("email"));
					utente.setNome(result.getString("nome"));
					utente.setCognome(result.getString("cognome"));
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
		return utente;
	}

	@Override
	public boolean findByEmail(String email) {
		try {
			Connection connessione = this.data.getConnection();
			String query = "SELECT * FROM utenti WHERE email=?";
			try {
				PreparedStatement p = connessione.prepareStatement(query);
				p.setString(1, email);
				ResultSet result = p.executeQuery();

				if (result.next()) {
					if (result.getString("email").equals(email)) {
						return true;
					}
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
		return false;
	}

}
