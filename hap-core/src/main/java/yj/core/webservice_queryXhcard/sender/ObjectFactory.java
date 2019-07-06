package yj.core.webservice_queryXhcard.sender;

import yj.core.webservice_queryXhcard.receiver.DTQUERYXHCARDRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.queryxhcard.sender package.
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

    private final static QName _MTQUERYXHCARDRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/queryXhcard/Sender", "MT_QUERYXHCARD_Res");
    private final static QName _MTQUERYXHCARDReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/queryXhcard/Sender", "MT_QUERYXHCARD_Req");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.queryxhcard.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTQUERYXHCARDRes }
     *
     */
    public DTQUERYXHCARDRes createDTQUERYXHCARDRes() {
        return new DTQUERYXHCARDRes();
    }

    /**
     * Create an instance of {@link DTQUERYXHCARDRes.XHCARD }
     *
     */
    public DTQUERYXHCARDRes.XHCARD createDTQUERYXHCARDResXHCARD() {
        return new DTQUERYXHCARDRes.XHCARD();
    }

    /**
     * Create an instance of {@link DTQUERYXHCARDReq }
     *
     */
    public DTQUERYXHCARDReq createDTQUERYXHCARDReq() {
        return new DTQUERYXHCARDReq();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTQUERYXHCARDRes }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/queryXhcard/Sender", name = "MT_QUERYXHCARD_Res")
    public JAXBElement<DTQUERYXHCARDRes> createMTQUERYXHCARDRes(
            DTQUERYXHCARDRes value) {
        return new JAXBElement<DTQUERYXHCARDRes>(_MTQUERYXHCARDRes_QNAME,
                DTQUERYXHCARDRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTQUERYXHCARDReq }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/queryXhcard/Sender", name = "MT_QUERYXHCARD_Req")
    public JAXBElement<DTQUERYXHCARDReq> createMTQUERYXHCARDReq(
            DTQUERYXHCARDReq value) {
        return new JAXBElement<DTQUERYXHCARDReq>(_MTQUERYXHCARDReq_QNAME,
                DTQUERYXHCARDReq.class, null, value);
    }

}
