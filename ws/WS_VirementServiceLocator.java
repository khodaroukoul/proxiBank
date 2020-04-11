/**
 * WS_VirementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

public class WS_VirementServiceLocator extends org.apache.axis.client.Service implements ws.WS_VirementService {

	public WS_VirementServiceLocator() {
	}

	public WS_VirementServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public WS_VirementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WS_Virement
	private java.lang.String WS_Virement_address = "http://192.168.1.23:8080/WS_FAR_V4/services/WS_Virement";

	public java.lang.String getWS_VirementAddress() {
		return WS_Virement_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String WS_VirementWSDDServiceName = "WS_Virement";

	public java.lang.String getWS_VirementWSDDServiceName() {
		return WS_VirementWSDDServiceName;
	}

	public void setWS_VirementWSDDServiceName(java.lang.String name) {
		WS_VirementWSDDServiceName = name;
	}

	public ws.WS_Virement getWS_Virement() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WS_Virement_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWS_Virement(endpoint);
	}

	public ws.WS_Virement getWS_Virement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			ws.WS_VirementSoapBindingStub _stub = new ws.WS_VirementSoapBindingStub(portAddress, this);
			_stub.setPortName(getWS_VirementWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWS_VirementEndpointAddress(java.lang.String address) {
		WS_Virement_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (ws.WS_Virement.class.isAssignableFrom(serviceEndpointInterface)) {
				ws.WS_VirementSoapBindingStub _stub = new ws.WS_VirementSoapBindingStub(
						new java.net.URL(WS_Virement_address), this);
				_stub.setPortName(getWS_VirementWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("WS_Virement".equals(inputPortName)) {
			return getWS_Virement();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://ws", "WS_VirementService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://ws", "WS_Virement"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {

		if ("WS_Virement".equals(portName)) {
			setWS_VirementEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
			throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
