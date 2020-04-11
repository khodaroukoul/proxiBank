package model;

import java.io.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ws.WS_VirementProxy;

/**
 * Servlet de création de nouveau compte avec connexion à la base de données et
 * mise à jour de la table "Comptes"
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		/**
		 * Récupération des informations du nouveau compte transmises par la jsp
		 */
		String idCl = request.getParameter("idCl");
		String nomCl = request.getParameter("nomCl");
		String type = request.getParameter("typeCompte");
		int numeroCompte = Integer.parseInt(request.getParameter("numeroCompte"));
		String date = request.getParameter("date");
		String solde = request.getParameter("solde");
		String idAgence = request.getParameter("idAgence");
		String page = "listClients.jsp";
		/**
		 * appel du Web Service de connexion à la base de données distante
		 */
		WS_VirementProxy lc = new WS_VirementProxy();
		/**
		 * Vérification si le numero de compte existe déjà Si la réponse est
		 * "non", insertion dans la table "compte" du nouveau compte
		 */
		boolean compteExiste = lc.verifCompte(numeroCompte);

		if (!compteExiste) {
			String requete = "insert into compte (typeCompte, numeroCompte, date, solde, idClient, idAgence) values('"
					+ type + "','" + numeroCompte + "','" + date + "','" + solde + "','" + idCl + "','" + idAgence
					+ "');";

			lc.updateVir(requete);

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
			/**
			 * Si la réponse est "oui", renvoi sur une page d'erreur
			 */
		} else {
			request.setAttribute("errMsg", "le compte existe déjà!");
			request.setAttribute("idCl", idCl);
			request.setAttribute("nomCl", nomCl);
			RequestDispatcher dispatcher = request.getRequestDispatcher("errors.jsp");
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
			return;
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
