package model;

import java.io.*;
import java.time.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;
import ws.WS_VirementProxy;

/**
 * Servlet servant de lien avec la jsp "Gérant" et qui va récupérer la liste des
 * clients à découvert et les informations nécessaires à l'histogramme
 * 
 * @author Farhad, Alex et Romain
 */
public class Gerant extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Date date = new Date();
	Calendar calendar = Calendar.getInstance();
	Date moisFin = calendar.getTime();
	LocalDate localmoisFin = moisFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		String page = "gerant.jsp";
		/**
		 * Récupération des informations virements sur la dernière semaine
		 */
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date dateFin = calendar.getTime();
		LocalDate localDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date dateDebut = calendar.getTime();
		LocalDate localDateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		WS_VirementProxy wsVir = new WS_VirementProxy();
		String requete = "SELECT count(*) AS nbVir, SUM(montantVir) AS sumVir, dateVir "
				+ "FROM virhistory WHERE dateVir BETWEEN '" + localDateDebut + "' AND '" + localDateFin
				+ "' GROUP by dateVir;";
		String[] lesVir = wsVir.selectVirLog(requete);
		session.setAttribute("lesVirWeek", lesVir);
		/**
		 * Récupération des informations virement sur les 3 derniers mois
		 */
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -2);
		Date moisDebut = calendar.getTime();
		LocalDate localmoisDebut = moisDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		requete = "SELECT count(*) AS nbVir, SUM(montantVir) AS sumVir, MONTHNAME(dateVir) AS dateVir"
				+ " FROM virhistory WHERE dateVir BETWEEN '" + localmoisDebut + "' AND '" + localmoisFin
				+ "' GROUP by MONTH(dateVir);";

		String[] lesVir2 = wsVir.selectVirLog(requete);
		session.setAttribute("lesVirMonths", lesVir2);
		/**
		 * Récupération des soldes de comptes pour ensuite savoir quels comptes
		 * sont à découvert
		 */
		requete = "Select * from compte ;";
		String[] ls_aux = wsVir.selectComptes(requete);
		List listSolde = Arrays.asList(ls_aux);
		session.setAttribute("Soldes", listSolde);

		// Disptching request
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