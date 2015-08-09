package persistence.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private String dbURI = "jdbc:postgresql://localhost:5432/progettoSII";
	private String username = "postgres";
	private String password = "password";

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURI, username, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
