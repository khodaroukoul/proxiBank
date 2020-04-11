package model;

import java.io.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet servant à envoyer à la JSP "creerCompte" l'id et et le com du client
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class Recup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String page = "creerCompte.jsp";

		String idCl = request.getParameter("idCl");
		String nomCl = request.getParameter("nomCl");
		request.setAttribute("idCl", idCl);
		request.setAttribute("nomCl", nomCl);

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
