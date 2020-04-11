package model;

import java.rmi.RemoteException;
import java.text.*;
import java.util.*;

import ws.WS_VirementProxy;

/**
 * Classe qui va d�finir la m�thode appel�e en premi�re lors du Spring AOP.
 * Insertion du nouveau virement dans la BDD
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class VirementService {
	private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // hh:mm:ss
	private List virHistory = new ArrayList();

	/**
	 * M�thode qui insere dans la bdd le nouveau compte via le Wab Service
	 * 
	 * @param montant
	 *            : montant du virement
	 * @param compteDeb
	 *            : numero du compte D�biteur
	 * @param compteCr
	 *            : num�ro du compte Cr�diteur
	 * @return virHistory : la liste des informations du virement
	 * @throws RemoteException
	 */

	public List virHistory(double montant, int compteDeb, int compteCr) throws RemoteException {
		Date virDate = new Date();
		WS_VirementProxy lc = new WS_VirementProxy();
		String requete = "INSERT INTO `virhistory`(`dateVir`, `montantVir`, `CompteDeb`, `CompteCr`) VALUES ('"
				+ format.format(virDate) + "'," + montant + "," + compteDeb + "," + compteCr + ");";
		lc.updateVir(requete);

		virHistory.add(montant);
		virHistory.add(compteDeb);
		virHistory.add(compteCr);

		return virHistory;
	}
}