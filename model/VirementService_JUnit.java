package model;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Classe de test du web service via JUnit
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class VirementService_JUnit {
	/**
	 * @param virTest
	 *            va servir à instancier la classe à tester
	 */
	private static VirementService virServiceTest = null;

	@BeforeClass
	public static void init() {
		System.err.println("initialisation des tests");
		virServiceTest = new VirementService();
	}

	@AfterClass
	public static void fin() {
		System.err.println("fin des tests");
	}

	/**
	 * Methode de test
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateVir() throws Exception {
		System.out.println("tests sur la méthode virHistory(montant,compteDeb,compteCr)");
		List expecteds = new ArrayList();
		expecteds.add(10000.0);
		expecteds.add(179898);
		expecteds.add(264125);
		List actuals = virServiceTest.virHistory(10000.0, 179898, 264125);
		assertNotNull(actuals);
		assertEquals(actuals.size(), 3);
		assertEquals(expecteds, actuals);
	}
}
