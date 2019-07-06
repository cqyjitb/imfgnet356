package yj.core.webservice.sender;

import yj.core.webservice.receiver.DTPP001SendRes;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.pp001.sender package.
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

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.cq_yj.hap.pp001.sender
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link DTPP001SendReq }
	 * 
	 */
	public DTPP001SendReq createDTPP001SendReq() {
		return new DTPP001SendReq();
	}

	/**
	 * Create an instance of {@link DTPP001SendRes }
	 * 
	 */
	public DTPP001SendRes createDTPP001SendRes() {
		return new DTPP001SendRes();
	}

	/**
	 * Create an instance of {@link DTPP001SendRes.RETURN }
	 * 
	 */
	public DTPP001SendRes.RETURN createDTPP001SendResRETURN() {
		return new DTPP001SendRes.RETURN();
	}

	/**
	 * Create an instance of {@link DTPP001SendRes.DETAIL }
	 * 
	 */
	public DTPP001SendRes.DETAIL createDTPP001SendResDETAIL() {
		return new DTPP001SendRes.DETAIL();
	}

	/**
	 * Create an instance of {@link DTPP001SendRes.DETAIL.MAKT }
	 * 
	 */
	public DTPP001SendRes.DETAIL.MAKT createDTPP001SendResDETAILMAKT() {
		return new DTPP001SendRes.DETAIL.MAKT();
	}

	/**
	 * Create an instance of {@link DTPP001SendReq.ITEM }
	 * 
	 */
	public DTPP001SendReq.ITEM createDTPP001SendReqITEM() {
		return new DTPP001SendReq.ITEM();
	}

}
