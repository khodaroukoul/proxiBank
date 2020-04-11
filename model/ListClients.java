package model;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import bdd.MySQL;
import ejb.ConnexionBDD;
import ws.WS_VirementProxy;

import java.util.*;

import javax.naming.*;

/**
 * Servlet de connexion à l'ejb distant pour vérifier les identifiants de
 * l'employé et tester si c'est un gérant ou un conseiller. Si c'est un
 * conseiller, récupération de sa liste de ces clients, de leur nombre et des
 * soldes de leurs comptes. Si c'est un gérant, récupération de liste des
 * conseillers sous ses ordres.
 * 
 * @author Farhad, Alex et Romain
 */
public class ListClients extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		String pageCl = "listClients.jsp";
		String pageCons = "Gerant";

		try {

			/**
			 * Définition du contexte de connexion à l'EJB qui est déployé sur
			 * un serveur JBOSS distant
			 */
			Properties prop = new Properties();
			prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			prop.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			prop.setProperty(Context.PROVIDER_URL, "192.168.1.27:1099");
			Context context = new InitialContext(prop);
			/**
			 * Instanciation de l'interface de l'EJB
			 */
			ConnexionBDD con = (ConnexionBDD) context.lookup("ConnexionBDDBean/remote");

			String nom = request.getParameter("username");
			String password = request.getParameter("password");
			/**
			 * Appel de la méthode de l'EJB qui vérifie les idenfiants et
			 * retourn true ou false
			 * 
			 */
			boolean ok = con.verifierEmployes(nom, password);

			try {
				/**
				 * Si l'EJb a retourné true, l'employé s'est bien authentifié.
				 * On récupère donc son id son nom et son prenom
				 */
				if (ok) {

					int idCons = con.recupEmployes(nom, password).get(0).getIdCons();
					String nomCons = con.recupEmployes(nom, password).get(0).getNom();
					String prenomCons = con.recupEmployes(nom, password).get(0).getPrenom();
					String profil = con.recupEmployes(nom, password).get(0).getProfil();
					String idAgence = con.recupEmployes(nom, password).get(0).getIdAgence();
					session.setAttribute("idCons", idCons);
					session.setAttribute("nomCons", nomCons);
					session.setAttribute("prenomCons", prenomCons);
					session.setAttribute("idAgence", idAgence);
					/**
					 * Si c'est un conseiller : Récupération de la liste des
					 * clients du conseiller à partir de son ID, de leur nombre
					 * et des soldes de leurs comptes
					 */

					if (profil.equals("conseiller")) {
						MySQL base = new MySQL(ds);
						/**
						 * Liste des clients
						 */
						String requete = "Select * from client where idCons='" + idCons + "';";
						// base.select(requete);

						base.showClient(requete);
						List dataList = base.getDataList();
						session.setAttribute("lesClients", dataList);
						/**
						 * Nombre des clients
						 */
						requete = "Select count(*) as nbCl from client where idCons='" + idCons + "';";
						base.select(requete);
						base.getRs().first();
						int nbCl = base.getRs().getInt("nbCl");

						base.deconnecte();
						session.setAttribute("nbCl", nbCl);
						/**
						 * Soldes des comptes
						 */
						requete = "Select * from compte ;";
						WS_VirementProxy lc = new WS_VirementProxy();
						String[] lc_aux = lc.selectComptes(requete);
						List listSolde = Arrays.asList(lc_aux);
						session.setAttribute("Soldes", listSolde);
						RequestDispatcher dispatcher = request.getRequestDispatcher(pageCl);
						if (dispatcher != null) {
							dispatcher.forward(request, response);
						}
					}
					/**
					 * Si c'est un gérant : Récupération de la liste de ses
					 * conseillers
					 */
					else {
						con.recupEmployes(nom, password);
						
						List listCons_aux = con.listConseillers(idAgence);
						List listCons = new ArrayList();
						for (int i = 0; i < listCons_aux.size(); i++) {
							listCons.add(con.listConseillers(idAgence).get(i).getIdCons());
							listCons.add(con.listConseillers(idAgence).get(i).getNom());
							listCons.add(con.listConseillers(idAgence).get(i).getPrenom());
							listCons.add(con.listConseillers(idAgence).get(i).getAdresse());
							listCons.add(con.listConseillers(idAgence).get(i).getCp());
							listCons.add(con.listConseillers(idAgence).get(i).getVille());
							listCons.add(con.listConseillers(idAgence).get(i).getTel());
							listCons.add(con.listConseillers(idAgence).get(i).getIdAgence());
							listCons.add(con.listConseillers(idAgence).get(i).getPassword());
							listCons.add(con.listConseillers(idAgence).get(i).getProfil());
						}
						;
						session.setAttribute("lesConseillers", listCons);
						RequestDispatcher dispatcher = request.getRequestDispatcher(pageCons);
						if (dispatcher != null) {
							dispatcher.forward(request, response);
						}
					}

				} else {
					/**
					 * Si l'EJB renvoie false, donc si l'employé a entré des
					 * informations erronées, on redirige vers la page d'erreur
					 */
					response.sendRedirect("loginError.jsp");
				}
				/**
				 * problème de langage sql ou de mappage de la table de données.
				 * 
				 * @see ClassNotFoundException
				 * @see SQLException
				 */
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} catch (NamingException e) {
			out.print("<html><head><title>Probleme acces Base de données</title></head><body bgcolor=87CEFA>");
			out.print(
					"<h1>Veuillez vérifier vos paramètres d'acces a la base de données dans la classe ListClients</h1>");
			out.print("<br /><br /><center><a href='/pv4'>retourner à l'acceuil</a></center></body></html>");
			out.flush();
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Context initctx = new InitialContext();
			/**
			 * Mapping d'accèe à la base de données
			 */
			Context envctx = (Context) initctx.lookup("java:comp/env");
			ds = (DataSource) envctx.lookup("jdbc/toto");
		} catch (Exception e) {
			throw new UnavailableException(e.getMessage());
		}
	}
}