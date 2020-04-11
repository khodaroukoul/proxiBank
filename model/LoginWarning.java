package model;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;

/**
 * 
 * Servlet de réponse à l'Ajax qui permet de vérifier si les champs login et
 * password ont bien été remplis
 * 
 * @author Farhad, Alex et Romain
 */
public class LoginWarning extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "Veuillez saisir votre login et votre mot de passe !!!";
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("<message>" + msg + "</message>");
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
