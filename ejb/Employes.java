package ejb;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe Pojo liée via Hibernate à la table conseiller de la base de données
 * locale
 * 
 * @author Farhad, Alex et Romain
 *
 */
@Entity
@Table(name = "employes")
public class Employes implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "idCons")
	private int idCons;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "cp")
	private int cp;
	@Column(name = "ville")
	private String ville;
	@Column(name = "tel")
	private String tel;
	@Column(name = "idAgence")
	private String idAgence;
	@Column(name = "password")
	private String password;
	@Column(name = "profil")
	private String profil;

	public Employes() {
	}

	public Employes(int idCons, String nom, String prenom, String adresse, int cp, String ville, String tel,
			String idAgence, String password, String profil) {
		this.idCons = idCons;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.tel = tel;
		this.idAgence = idAgence;
		this.password = password;
		this.profil = profil;

	}

	public int getIdCons() {
		return idCons;
	}

	public void setIdCons(int idCons) {
		this.idCons = idCons;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(String idAgence) {
		this.idAgence = idAgence;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

}
