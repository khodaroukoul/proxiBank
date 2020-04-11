package bdd;

import java.sql.*;
import java.util.*;
import javax.sql.*;

/**
 * Classe de connexion à une base de données locale
 * 
 * @author Farhad, Alex et Romain
 * 
 */

public class MySQL {

	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private List dataList = null;

	/**
	 * Cette methode permet de se connecter à la base de données
	 * 
	 * @param ds
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public MySQL(DataSource ds) throws ClassNotFoundException, SQLException {
		con = ds.getConnection();
		stmt = con.createStatement();
	}

	/**
	 * Déconnexion à la base de données
	 */

	public void deconnecte() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * Envoyer une requête de type interrogation (SELECT)
	 * 
	 * @param ordre
	 *            : requête
	 */
	public void select(String ordre) {
		try {
			rs = stmt.executeQuery(ordre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Envoyer une requête pour des traitements de mise à jour
	 * 
	 * @param ordre
	 *            : requête
	 */
	public void update(String ordre) throws SQLException {
		stmt.executeUpdate(ordre);
	}

	/**
	 * Lire les informations de clients depuis la base de données
	 * 
	 * @param ordre
	 *            : requête
	 * @throws SQLException
	 */
	public void showClient(String ordre) throws SQLException {
		stmt.executeQuery(ordre);
		rs = stmt.getResultSet();
		dataList = new ArrayList();
		while (rs.next()) {
			dataList.add(rs.getInt("idClient"));
			dataList.add(rs.getString("nom"));
			dataList.add(rs.getString("prenom"));
			dataList.add(rs.getString("adresse"));
			dataList.add(rs.getInt("cp"));
			dataList.add(rs.getString("ville"));
			dataList.add(rs.getString("tel"));
			dataList.add(rs.getString("mel"));
			dataList.add(rs.getString("typeClient"));
		}
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
}
