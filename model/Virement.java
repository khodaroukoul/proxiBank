package model;

import java.io.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ws.WS_VirementProxy;

/**
 * 
 * Servlet d'appel du WebService pour récupérer et afficher les comptes servant
 * au virement
 * 
 * @author Farhad, Alex et Romain
 */
public class Virement extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		String page = "virement.jsp";

		String idCl = request.getParameter("idCl");
		String nomCl = request.getParameter("nomCl");

		/**
		 * Appel du Web service de connexion à la base de données distante
		 */

		WS_VirementProxy lc = new WS_VirementProxy();

		/**
		 * Récupération de tous les comptes du client sélectionné
		 */

		String requete = "Select * from compte where idClient='" + idCl + "';";
		String[] lc_aux = lc.selectComptes(requete);
		List listComptesCl = Arrays.asList(lc_aux);
		String idAgence = (String) listComptesCl.get(6);
		session.setAttribute("lesComptesCl", listComptesCl);

		/**
		 * Récupération de tous les comptes de l'agence
		 */

		requete = "Select * from compte where idAgence='" + idAgence + "' order by numeroCompte ASC;";
		lc_aux = lc.selectComptes(requete);
		List listComptesAll = Arrays.asList(lc_aux);
		request.setAttribute("lesComptesAll", listComptesAll);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Context initctx = new InitialContext();

		} catch (Exception e) {
			throw new UnavailableException(e.getMessage());
		}
	}
}