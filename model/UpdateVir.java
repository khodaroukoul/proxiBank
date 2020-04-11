package model;

import java.io.*;

import java.util.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ws.WS_VirementProxy;

/**
 * Servlet appellant le WebService permettant de faire un update sur les comptes
 * après un virement. Avec Spring AOP pour pour coordoner update des virements
 * et tracage des virements
 * 
 * @author Farhad, Alex et Romain
 */
public class UpdateVir extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		session.removeAttribute("lesComptes");
		String page = "affCompte.jsp";

		/**
		 * Récupération des informations du virement entrées par le conseiller
		 */
		String idCl = request.getParameter("idCl");
		String nomCl = request.getParameter("nomCl");
		double montant = Double.parseDouble(request.getParameter("montant"));
		int compteCr = Integer.parseInt(request.getParameter("compteCr"));
		int compteDeb = Integer.parseInt(request.getParameter("compteDeb"));
		/**
		 * Appel du WS
		 */
		WS_VirementProxy lc = new WS_VirementProxy();
		/**
		 * Récupération du solde et du type de compte du compte débité
		 */

		String requete = "Select * from compte where numeroCompte='" + compteDeb + "';";
		String[] lc_aux = lc.selectComptes(requete);
		List listComptes = Arrays.asList(lc_aux);
		double soldeCompteDeb = Double.parseDouble((String) listComptes.get(4));
		String typeCompteDeb = (String) listComptes.get(1);

		/**
		 * Récupération du solde et du type de compte du compte crédité
		 */

		requete = "select * from compte where numeroCompte='" + compteCr + "';";
		lc_aux = lc.selectComptes(requete);
		listComptes = Arrays.asList(lc_aux);
		double soldeCompteCr = Double.parseDouble((String) listComptes.get(4));
		/**
		 * Vérification que le solde du compte est suffisant pour réaliser le
		 * virement selon le type de compte
		 */
		if (compteCr != compteDeb) {
			if (typeCompteDeb.compareTo("courant") == 0) {
				if (montant <= (soldeCompteDeb + 1000)) {
					soldeCompteDeb -= montant;
					soldeCompteCr += montant;
				} else {
					request.setAttribute("errMsg", "le solde du compte courant est insuffisant!");
					request.setAttribute("idCl", idCl);
					request.setAttribute("nomCl", nomCl);
					RequestDispatcher dispatcher = request.getRequestDispatcher("errors.jsp");
					if (dispatcher != null) {
						dispatcher.forward(request, response);
					}
					return;
				}
			} else {
				if (montant <= soldeCompteDeb) {
					soldeCompteDeb -= montant;
					soldeCompteCr += montant;
				} else {
					request.setAttribute("errMsg", "le solde du compte epargne est insuffisant!");
					request.setAttribute("idCl", idCl);
					request.setAttribute("nomCl", nomCl);
					RequestDispatcher dispatcher = request.getRequestDispatcher("errors.jsp");
					if (dispatcher != null) {
						dispatcher.forward(request, response);
					}
					return;
				}
			}
			/**
			 * update des soldes des comptes sur la BDD
			 */
			requete = "Update compte set solde='" + soldeCompteDeb + "' where numeroCompte='" + compteDeb + "';";
			lc.updateVir(requete);
			requete = "Update compte set solde='" + soldeCompteCr + "' where numeroCompte='" + compteCr + "';";
			lc.updateVir(requete);
			/**
			 * récupération des informations des comptes mises à jour
			 */
			requete = "Select * from compte where idClient='" + idCl + "';";
			lc_aux = lc.selectComptes(requete);
			listComptes = Arrays.asList(lc_aux);
			session.setAttribute("lesComptes", listComptes);

			/**
			 * Appel du Spring AOP pour insérer le nouveau virement dans la base
			 * de données et en même temps le tracer dans un fichier externe
			 */

			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
			VirementService UpdateVirService = (VirementService) context.getBean("virLogService");
			UpdateVirService.virHistory(montant, compteDeb, compteCr);

			/**
			 * Mise à jour de la liste des comptes suite au virement
			 */
			requete = "Select * from compte ;";
			lc_aux = lc.selectComptes(requete);
			List listSolde = Arrays.asList(lc_aux);
			session.setAttribute("Soldes", listSolde);

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}

			/**
			 * Si le conseiller essaie de faire un virement sur le même compte
			 * envoi d'une page d'erreur
			 */

		} else {
			request.setAttribute("errMsg", "Le virement n'est pas autorisé sur le m&ecirc;me compte");
			request.setAttribute("idCl", idCl);
			request.setAttribute("nomCl", nomCl);
			RequestDispatcher dispatcher = request.getRequestDispatcher("errors.jsp");
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
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