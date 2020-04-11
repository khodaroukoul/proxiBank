package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bdd.MySQL;
import ws.WS_VirementProxy;

/**
 * Servlet servant � supprimer de la base de donn�es le client que le conseiller
 * veut supprimer
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class SuppCl extends HttpServlet {

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
			 * Connexion � la base de donn�es locale pour modifier les infos
			 * clients
			 * 
			 * @see MySQL
			 */
			MySQL base = new MySQL(ds);
			int idCl = Integer.parseInt(request.getParameter("idCl"));

			/**
			 * Update de la bdd
			 */
			String requete = "delete from client where idClient=" + idCl + ";";
			base.update(requete);
			/**
			 * R�cup�ration des infos clients pour afficher les mises � jour
			 */
			requete = "select * from client where idCons='" + idCons + "';";
			base.showClient(requete);
			List dataList = base.getDataList();
			session.setAttribute("lesClients", dataList);

			/**
			 * Mise � jour du nombre de clients du portefeuille du conseiller
			 */
			session.removeAttribute("nbCl");

			requete = "Select count(*) as nbCl from client where idCons='" + idCons + "';";
			base.select(requete);
			base.getRs().first();
			int nbCl = base.getRs().getInt("nbCl");

			base.deconnecte();

			session.setAttribute("nbCl", nbCl);
			/**
			 * Suppression du ou des comptes associ�s au client supprim�
			 * 
			 */
			requete = "delete from compte where idClient='" + idCl + "';";
			WS_VirementProxy lc = new WS_VirementProxy();
			lc.updateVir(requete);

			/**
			 * Envoi des infos clients mises � jour � la page d'affichage
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			out.print("<html><head><title>Probleme acces Base de donn�es</title></head><body bgcolor=87CEFA>");
			out.print(
					"<h1>Veuillez v�rifier vos param�tres d'acces a la base de donn�es dans la classe EditClient</h1>");
			out.print("<br /><br /><center><a href='/pv4'>retourner � l'acceuil</a></center></body></html>");
			out.flush();
		} catch (SQLException e) {
			out.print("<html><head><title>Probleme de requete</title></head><body bgcolor=87CEFA>");
			out.print("<h1>Veuillez v�rifier les param�tres de votre requete dans la classe EditClient</h1>");
			out.print("<br /><br /><center><a href='/pv4'>retourner � l'acceuil</a></center></body></html>");
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
