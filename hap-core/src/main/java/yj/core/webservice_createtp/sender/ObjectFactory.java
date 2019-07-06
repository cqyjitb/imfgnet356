package yj.core.webservice_createtp.sender;

import yj.core.webservice_createtp.receiver.DTCREATPRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.creatp.sender package.
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

    private final static QName _MTCREATPReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/CREATP/Sender", "MT_CREATP_Req");
    private final static QName _MTCREATPRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/CREATP/Sender", "MT_CREATP_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.creatp.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTCREATPRes.RETURN }
     *
     */
    public DTCREATPRes.RETURN createDTCREATPResRETURN() {
        return new DTCREATPRes.RETURN();
    }

    /**
     * Create an instance of {@link DTCREATPRes }
     *
     */
    public DTCREATPRes createDTCREATPRes() {
        return new DTCREATPRes();
    }

    /**
     * Create an instance of {@link DTCREATPReq }
     *
     */
    public DTCREATPReq createDTCREATPReq() {
        return new DTCREATPReq();
    }

    /**
     * Create an instance of {@link DTCREATPReq.ITEM }
     *
     */
    public DTCREATPReq.ITEM createDTCREATPReqITEM() {
        return new DTCREATPReq.ITEM();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTCREATPReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/CREATP/Sender", name = "MT_CREATP_Req")
    public JAXBElement<DTCREATPReq> createMTCREATPReq(DTCREATPReq value) {
        return new JAXBElement<DTCREATPReq>(_MTCREATPReq_QNAME,
                DTCREATPReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTCREATPRes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/CREATP/Sender", name = "MT_CREATP_Res")
    public JAXBElement<DTCREATPRes> createMTCREATPRes(DTCREATPRes value) {
        return new JAXBElement<DTCREATPRes>(_MTCREATPRes_QNAME,
                DTCREATPRes.class, null, value);
    }

}
