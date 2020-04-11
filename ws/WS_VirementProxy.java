package ws;

/**
 * Classe qui va servir à instancier le Web Service à chaque fois qu'on a besoin
 * et donc faire le lien entre la partie server et la partie client.
 * 
 * @author Farhad, Alex et Romain
 *
 */
public class WS_VirementProxy implements ws.WS_Virement {
	private String _endpoint = null;
	private ws.WS_Virement wS_Virement = null;

	public WS_VirementProxy() {
		_initWS_VirementProxy();
	}

	public WS_VirementProxy(String endpoint) {
		_endpoint = endpoint;
		_initWS_VirementProxy();
	}

	private void _initWS_VirementProxy() {
		try {
			wS_Virement = (new ws.WS_VirementServiceLocator()).getWS_Virement();
			if (wS_Virement != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) wS_Virement)._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) wS_Virement)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (wS_Virement != null)
			((javax.xml.rpc.Stub) wS_Virement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public ws.WS_Virement getWS_Virement() {
		if (wS_Virement == null)
			_initWS_VirementProxy();
		return wS_Virement;
	}

	public boolean verifCompte(int numeroCompte) throws java.rmi.RemoteException {
		if (wS_Virement == null)
			_initWS_VirementProxy();
		return wS_Virement.verifCompte(numeroCompte);
	}

	public java.lang.String[] selectComptes(java.lang.String ordre) throws java.rmi.RemoteException {
		if (wS_Virement == null)
			_initWS_VirementProxy();
		return wS_Virement.selectComptes(ordre);
	}

	public java.lang.String[] selectVirLog(java.lang.String ordre) throws java.rmi.RemoteException {
		if (wS_Virement == null)
			_initWS_VirementProxy();
		return wS_Virement.selectVirLog(ordre);
	}

	public boolean updateVir(java.lang.String ordre) throws java.rmi.RemoteException {
		if (wS_Virement == null)
			_initWS_VirementProxy();
		return wS_Virement.updateVir(ordre);
	}

}