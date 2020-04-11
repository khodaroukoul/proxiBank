package model;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import bdd.MySQL;

/**
 * Servlet qui va updater sur la base de données les informations modifiées par le conseiller
 * @author Farhad Alex et Romain 
 */
public class CreerClient extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true); // initialisation des
														// mécanismes de session
		int idCons = (Integer) session.getAttribute("idCons");
		String page = "creerCompte.jsp";

		/**
		 * Récupération des informations client transmises par la JSP
		 */
		String nomCl = request.getParameter("nomCl");
		String prenomCl = request.getParameter("prenomCl");
		String adresseCl = request.getParameter("adresseCl");
		int cpCl = Integer.parseInt(request.getParameter("cpCl"));
		String villeCl = request.getParameter("villeCl");
		String telCl = request.getParameter("telCl");
		String melCl = request.getParameter("melCl");
		String typeCl = request.getParameter("typeCl");

		try {
			/**
			 * Connexion à la base de données et insertion du nouveau client
			 */
			MySQL base = new MySQL(ds);

			String requete = "INSERT INTO client (nom, prenom, adresse, cp, ville, tel, mel, idCons, typeClient) VALUES ('"
					+ nomCl + "', '" + prenomCl + "', '" + adresseCl + "', '" + cpCl + "', '" + villeCl + "','" + telCl
					+ "', '" + melCl + "', '" + idCons + "', '" + typeCl + "');";
			base.update(requete);

			requete = "Select * from client where idCons='" + idCons + "';";
			base.showClient(requete);
			List dataList = base.getDataList();
			session.setAttribute("lesClients", dataList);

			/**
			 * Mise à jour de du nombre de clients du portefeuille du conseiller
			 */
			session.removeAttribute("nbCl");

			requete = "Select count(*) as nbCl from client where idCons='" + idCons + "';";
			base.select(requete);
			base.getRs().first();
			int nbCl = base.getRs().getInt("nbCl");

			/**
			 * Récupération de l'id du nouveau client
			 */
			requete = "select MAX(idClient) as max_id from client";
			base.select(requete);
			base.getRs().first();
			int idCl = base.getRs().getInt("max_id");
			base.deconnecte();

			session.setAttribute("nbCl", nbCl);
			request.setAttribute("idCl", idCl);
			request.setAttribute("nomCl", nomCl + " "+ prenomCl);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			out.print("<html><head><title>Probleme acces Base de données</title></head><body bgcolor=87CEFA>");
			out.print(
					"<h1>Veuillez vérifier vos paramètres d'acces a la base de données dans la classe EditClient</h1>");
			out.print("<br /><br /><center><a href='/pv4'>retourner à l'acceuil</a></center></body></html>");
			out.flush();
		} catch (SQLException e) {
			out.print("<html><head><title>Probleme de requete</title></head><body bgcolor=87CEFA>");
			out.print("<h1>Veuillez vérifier les paramètres de votre requete dans la classe EditClient</h1>");
			out.print("<br /><br /><center><a href='/pv4'>retourner à l'acceuil</a></center></body></html>");
			out.flush();
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