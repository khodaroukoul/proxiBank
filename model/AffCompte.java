package model;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ws.WS_VirementProxy;

/**
 * Sevlet qui appelle le WEB-Service de connexion à la base de données distante
 * des comptes et récupère la liste des comptes
 * 
 * @author Farhad, Alex et Romain
 */
public class AffCompte extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		String page = "affCompte.jsp";

		String idCl = request.getParameter("idCl");
		String nomCl = request.getParameter("nomCl");
		String requete = "Select * from compte where idClient='" + idCl + "';";
		/**
		 * 
		 * Appel du Web-Service
		 */
		WS_VirementProxy lc = new WS_VirementProxy();
		/**
		 * utilisation de la methode d'affichage de la liste des comptes du
		 * Web-Service
		 */
		String[] lc_aux = lc.selectComptes(requete);
		lc_aux = lc.selectComptes(requete);
		List listComptes = Arrays.asList(lc_aux);
		session.setAttribute("lesComptes", listComptes);

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