package yj.core.werbserver_crhd.sender;

import yj.core.werbserver_crhd.receiver.DTZZ002Res;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.pp001.sap_crhd.sender
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _MTZZ002Req_QNAME = new QName(
			"http://www.cq-yj.com/HAP/PP001/SAP_CRHD/Sender", "MT_ZZ002_Req");
	private final static QName _MTZZ002Res_QNAME = new QName(
			"http://www.cq-yj.com/HAP/PP001/SAP_CRHD/Sender", "MT_ZZ002_Res");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.cq_yj.hap.pp001.sap_crhd.sender
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link DTZZ002Req }
	 * 
	 */
	public DTZZ002Req createDTZZ002Req() {
		return new DTZZ002Req();
	}

	/**
	 * Create an instance of {@link DTZZ002Res }
	 * 
	 */
	public DTZZ002Res createDTZZ002Res() {
		return new DTZZ002Res();
	}

	/**
	 * Create an instance of {@link DTZZ002Res.TRETURN }
	 * 
	 */
	public DTZZ002Res.TRETURN createDTZZ002ResTRETURN() {
		return new DTZZ002Res.TRETURN();
	}

	/**
	 * Create an instance of {@link DTZZ002Res.EMESSAGE }
	 * 
	 */
	public DTZZ002Res.EMESSAGE createDTZZ002ResEMESSAGE() {
		return new DTZZ002Res.EMESSAGE();
	}

	/**
	 * Create an instance of {@link DTZZ002Req.IDATA }
	 * 
	 */
	public DTZZ002Req.IDATA createDTZZ002ReqIDATA() {
		return new DTZZ002Req.IDATA();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DTZZ002Req }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PP001/SAP_CRHD/Sender", name = "MT_ZZ002_Req")
	public JAXBElement<DTZZ002Req> createMTZZ002Req(DTZZ002Req value) {
		return new JAXBElement<DTZZ002Req>(_MTZZ002Req_QNAME, DTZZ002Req.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DTZZ002Res }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PP001/SAP_CRHD/Sender", name = "MT_ZZ002_Res")
	public JAXBElement<DTZZ002Res> createMTZZ002Res(DTZZ002Res value) {
		return new JAXBElement<DTZZ002Res>(_MTZZ002Res_QNAME, DTZZ002Res.class,
				null, value);
	}

}
