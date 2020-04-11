/**
 * WS_Virement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

/**
 * Interface de liaison avec la partie Serveur
 * 
 * @author Farhad, Alex et Romain
 *
 */
public interface WS_Virement extends java.rmi.Remote {
	public boolean verifCompte(int numeroCompte) throws java.rmi.RemoteException;

	public java.lang.String[] selectComptes(java.lang.String ordre) throws java.rmi.RemoteException;

	public java.lang.String[] selectVirLog(java.lang.String ordre) throws java.rmi.RemoteException;

	public boolean updateVir(java.lang.String ordre) throws java.rmi.RemoteException;
}
