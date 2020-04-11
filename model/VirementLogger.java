package model;

import java.util.*;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint.*;

/**
 * Classe qui va servir à définir la méthode utilisée en doAfter après l'update
 * des virments grâce à spring AOP
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class VirementLogger {

	private static Logger virHisLogger = Logger.getLogger("virLogger");

	/**
	 * Cette méthode va insérer dans un fichier externe de tracage les
	 * informations du virement effectué, récupérées de la méthode d'update
	 * 
	 * @param sp
	 * @param virHistory
	 *            informations du virement transmises par la l'autre partie de
	 *            l'AOP
	 */
	public void doAfter(StaticPart sp, List virHistory) {

		double montant = (Double) virHistory.get(0);
		int compteDeb = (Integer) virHistory.get(1);
		int compteCr = (Integer) virHistory.get(2);
		virHisLogger.info(
				"Montant: " + montant + ", N° CompteDebiteur: " + compteDeb + ", N° CompteCrediteur: " + compteCr);

	}
}