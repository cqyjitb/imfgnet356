package yj.core.webserver_outsrgreceipt.sender;

import yj.core.webserver_outsrgreceipt.receiver.DTOUTSRGRECEIPTRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.outsrgreceipt.sender
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

    private final static QName _MTOUTSRGRECEIPTReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender",
            "MT_OUTSRGRECEIPT_Req");
    private final static QName _MTOUTSRGRECEIPTRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender",
            "MT_OUTSRGRECEIPT_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.outsrgreceipt.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOUTSRGRECEIPTRes.RETURN }
     *
     */
    public DTOUTSRGRECEIPTRes.RETURN createDTOUTSRGRECEIPTResRETURN() {
        return new DTOUTSRGRECEIPTRes.RETURN();
    }

    /**
     * Create an instance of {@link DTOUTSRGRECEIPTReq.ITEM }
     *
     */
    public DTOUTSRGRECEIPTReq.ITEM createDTOUTSRGRECEIPTReqITEM() {
        return new DTOUTSRGRECEIPTReq.ITEM();
    }

    /**
     * Create an instance of {@link DTOUTSRGRECEIPTReq }
     *
     */
    public DTOUTSRGRECEIPTReq createDTOUTSRGRECEIPTReq() {
        return new DTOUTSRGRECEIPTReq();
    }

    /**
     * Create an instance of {@link DTOUTSRGRECEIPTRes }
     *
     */
    public DTOUTSRGRECEIPTRes createDTOUTSRGRECEIPTRes() {
        return new DTOUTSRGRECEIPTRes();
    }

    /**
     * Create an instance of {@link DTOUTSRGRECEIPTReq.HEAD }
     *
     */
    public DTOUTSRGRECEIPTReq.HEAD createDTOUTSRGRECEIPTReqHEAD() {
        return new DTOUTSRGRECEIPTReq.HEAD();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTOUTSRGRECEIPTReq }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender", name = "MT_OUTSRGRECEIPT_Req")
    public JAXBElement<DTOUTSRGRECEIPTReq> createMTOUTSRGRECEIPTReq(
            DTOUTSRGRECEIPTReq value) {
        return new JAXBElement<DTOUTSRGRECEIPTReq>(_MTOUTSRGRECEIPTReq_QNAME,
                DTOUTSRGRECEIPTReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTOUTSRGRECEIPTRes }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/OUTSRGRECEIPT/Sender", name = "MT_OUTSRGRECEIPT_Res")
    public JAXBElement<DTOUTSRGRECEIPTRes> createMTOUTSRGRECEIPTRes(
            DTOUTSRGRECEIPTRes value) {
        return new JAXBElement<DTOUTSRGRECEIPTRes>(_MTOUTSRGRECEIPTRes_QNAME,
                DTOUTSRGRECEIPTRes.class, null, value);
    }

}
