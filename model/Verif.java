package model;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import bdd.MySQL;

/**
 * 
 * Servlet de r�ponse � un ajax, se connectant � la base de donn�es locale pour
 * mettre � jour puis r�cup�rer les informations clients et les renvoyer � la
 * page d'�dition des infos clients.
 *
 * 
 * @author Farad, Alex et Romain
 * 
 * 
 *
 */
public class Verif extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/** datasource de connexion � la base de donn�es */
	private DataSource ds;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		try {
			/**
			 * connexion � la base de donn�es locale via Tomcat en instanciant
			 * la Classe de connexion
			 */
			MySQL mysql = new MySQL(ds);

			String idCl = request.getParameter("idCl");
			String nomCl = request.getParameter("nomCl");
			String prenomCl = request.getParameter("prenomCl");
			String adresseCl = request.getParameter("adresseCl");
			String villeCl = request.getParameter("villeCl");
			String cpCl = request.getParameter("cpCl");
			String telCl = request.getParameter("telCl");
			String melCl = request.getParameter("melCl");
			String requete = "";
			/**
			 * Si le champs a �t� rempli, on update l'information
			 */

			if (!nomCl.isEmpty()) {
				requete = "update client set `nom`='" + nomCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}

			if (!prenomCl.isEmpty()) {
				requete = "update client set `prenom`='" + prenomCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}

			if (!adresseCl.isEmpty()) {
				requete = "update client set `adresse`='" + adresseCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}

			if (!villeCl.isEmpty()) {
				requete = "update client set `ville`='" + villeCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}

			if (!cpCl.isEmpty()) {
				requete = "update client set `cp`='" + cpCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}

			if (!telCl.isEmpty()) {
				requete = "update client set `tel`='" + telCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}

			if (!melCl.isEmpty()) {
				requete = "update client set `mel`='" + melCl + "' where idClient='" + idCl + "';";
				mysql.update(requete);
			}
			/**
			 * R�cup�ration des infos du clients apr�s la mise � jour
			 */
			requete = "select * from client where idClient='" + idCl + "';";
			mysql.select(requete);

			mysql.getRs().first();
			idCl = mysql.getRs().getString("idClient");
			nomCl = mysql.getRs().getString("nom");
			prenomCl = mysql.getRs().getString("prenom");
			adresseCl = mysql.getRs().getString("adresse");
			villeCl = mysql.getRs().getString("ville");
			cpCl = mysql.getRs().getString("cp");
			telCl = mysql.getRs().getString("tel");
			melCl = mysql.getRs().getString("mel");
			/**
			 * Envoi � l'ajax des infos clients updat�es via un message XMLHTTP
			 */
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter()
					.write("<message><nom>" + nomCl + "</nom>" + "<prenom>" + prenomCl + "</prenom>" + "<adresse>"
							+ adresseCl + "</adresse>" + "<ville>" + villeCl + "</ville>" + "<cp>" + cpCl + "</cp>"
							+ "<tel>" + telCl + "</tel>" + "<mel>" + melCl + "</mel></message>");
			/**
			 * Reconstruction de la liste des clients quand on clique sur Retour
			 */
			session.removeAttribute("lesClients");
			int idCons = (int) session.getAttribute("idCons");
			requete = "select * from client where idCons='" + idCons + "';";
			mysql.showClient(requete);
			List dataList = mysql.getDataList();
			session.setAttribute("lesClients", dataList);
			mysql.deconnecte();

		} catch (SQLException e) {
			request.setAttribute("errMsg", "Probl�me de requete MySQL");
			request.getRequestDispatcher("errors.jsp").forward(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			request.setAttribute("errMsg", "Probl�me de connexion � la base de donn�e");
			request.getRequestDispatcher("errors.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		try {
			Context initctx = new InitialContext();

			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/toto");

		} catch (Exception e) {
			throw new UnavailableException(e.getMessage());
		}
	}
}
