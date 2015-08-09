package persistence.clusterPoint;

import com.clusterpoint.api.CPSConnection;

public class DataSource {
	private String dbURI = "tcps://cloud-eu-0.clusterpoint.com:9008";
	private String username = "frappostep@hotmail.it";
	private String password = "password";
	private String cpID = "1500";

	public CPSConnection getConnection(String dbName) throws Exception {
		CPSConnection conn = new CPSConnection(
				dbURI, dbName,
				username, password, cpID, "document",
				"//document/id");
		return conn;
	}
}
