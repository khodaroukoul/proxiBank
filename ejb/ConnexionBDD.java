package ejb;

import java.util.List;

import javax.ejb.Remote;

/**
 * Interface de notre EJB qui va servir à faire le lien avec la classe qui sera
 * sur le serveur
 * 
 * @author Farhad, Alex et Romain
 *
 */
@Remote
public interface ConnexionBDD {

	public boolean verifierEmployes(String nom, String password);

	public List<Employes> recupEmployes(String nom, String password);

	public List<Employes> listConseillers(String idAgence);
}
