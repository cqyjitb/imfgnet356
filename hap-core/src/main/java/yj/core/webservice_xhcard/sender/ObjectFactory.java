package yj.core.webservice_xhcard.sender;

import yj.core.webservice_xhcard.receiver.DTXHCARDSENDXRES;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.pp001.xhcardsender package.
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

    private final static QName _MTXHCARDSENDXRES_QNAME = new QName(
            "http://www.cq-yj.com/HAP/PP001/xhcardSender",
            "MT_XHCARD_SENDX_RES");
    private final static QName _MTXHCARDSENDXREQ_QNAME = new QName(
            "http://www.cq-yj.com/HAP/PP001/xhcardSender",
            "MT_XHCARD_SENDX_REQ");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.pp001.xhcardsender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTXHCARDSENDXREQ }
     *
     */
    public DTXHCARDSENDXREQ createDTXHCARDSENDXREQ() {
        return new DTXHCARDSENDXREQ();
    }

    /**
     * Create an instance of {@link DTXHCARDSENDXRES }
     *
     */
    public DTXHCARDSENDXRES createDTXHCARDSENDXRES() {
        return new DTXHCARDSENDXRES();
    }

    /**
     * Create an instance of {@link DTXHCARDSENDXRES.RETURN }
     *
     */
    public DTXHCARDSENDXRES.RETURN createDTXHCARDSENDXRESRETURN() {
        return new DTXHCARDSENDXRES.RETURN();
    }

    /**
     * Create an instance of {@link DTXHCARDSENDXREQ.ITEM }
     *
     */
    public DTXHCARDSENDXREQ.ITEM createDTXHCARDSENDXREQITEM() {
        return new DTXHCARDSENDXREQ.ITEM();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTXHCARDSENDXRES }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PP001/xhcardSender", name = "MT_XHCARD_SENDX_RES")
    public JAXBElement<DTXHCARDSENDXRES> createMTXHCARDSENDXRES(
            DTXHCARDSENDXRES value) {
        return new JAXBElement<DTXHCARDSENDXRES>(_MTXHCARDSENDXRES_QNAME,
                DTXHCARDSENDXRES.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTXHCARDSENDXREQ }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PP001/xhcardSender", name = "MT_XHCARD_SENDX_REQ")
    public JAXBElement<DTXHCARDSENDXREQ> createMTXHCARDSENDXREQ(
            DTXHCARDSENDXREQ value) {
        return new JAXBElement<DTXHCARDSENDXREQ>(_MTXHCARDSENDXREQ_QNAME,
                DTXHCARDSENDXREQ.class, null, value);
    }

}
