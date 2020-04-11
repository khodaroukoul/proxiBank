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
 * Servlet qui va updater sur la base de données les informations modifiées par
 * le conseiller via la JSP
 * 
 * @author Farhad, Alex et Romain
 */

public class EditClient extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		int idCons = (Integer) session.getAttribute("idCons");
		String page = "listClients.jsp";
		try {
			/**
			 * Connexion à la base de données locale pour modifier les
			 * informations clients
			 * 
			 * @see MySQL
			 */
			MySQL base = new MySQL(ds);
			int idCl = Integer.parseInt(request.getParameter("idCl"));
			String nomCl = request.getParameter("nomCl");
			String prenomCl = request.getParameter("prenomCl");
			String adresseCl = request.getParameter("adresseCl");
			int cpCl = Integer.parseInt(request.getParameter("cpCl"));
			String villeCl = request.getParameter("villeCl");
			String telCl = request.getParameter("telCl");
			String melCl = request.getParameter("melCl");
			/**
			 * Update de la bdd
			 */
			String requete = "Update client set nom='" + nomCl + "', prenom='" + prenomCl + "', adresse='" + adresseCl
					+ "', cp='" + cpCl + "', ville='" + villeCl + "', tel='" + telCl + "', mel='" + melCl
					+ "' where idClient='" + idCl + "';";
			base.update(requete);
			/**
			 * Récupération des informations client pour afficher les mises à
			 * jour
			 */
			requete = "select * from client where idCons='" + idCons + "';";
			base.showClient(requete);
			List dataList = base.getDataList();
			session.setAttribute("lesClients", dataList);
			base.deconnecte();

			/**
			 * Envoi des infos clients mises à jour à la page d'affichage
			 */
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