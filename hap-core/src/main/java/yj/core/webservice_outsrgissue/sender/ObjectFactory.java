package yj.core.webservice_outsrgissue.sender;


import yj.core.webservice_outsrgissue.receiver.DTOUTSRGISSUERes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.outsrgissue.sender package.
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

    private final static QName _MTOUTSRGISSUERes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/OUTSRGISSUE/Sender", "MT_OUTSRGISSUE_Res");
    private final static QName _MTOUTSRGISSUEReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/OUTSRGISSUE/Sender", "MT_OUTSRGISSUE_Req");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.outsrgissue.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOUTSRGISSUEReq.ITEM }
     *
     */
    public DTOUTSRGISSUEReq.ITEM createDTOUTSRGISSUEReqITEM() {
        return new DTOUTSRGISSUEReq.ITEM();
    }

    /**
     * Create an instance of {@link DTOUTSRGISSUERes }
     *
     */
    public DTOUTSRGISSUERes createDTOUTSRGISSUERes() {
        return new DTOUTSRGISSUERes();
    }

    /**
     * Create an instance of {@link DTOUTSRGISSUERes.RETURN }
     *
     */
    public DTOUTSRGISSUERes.RETURN createDTOUTSRGISSUEResRETURN() {
        return new DTOUTSRGISSUERes.RETURN();
    }

    /**
     * Create an instance of {@link DTOUTSRGISSUEReq.HEAD }
     *
     */
    public DTOUTSRGISSUEReq.HEAD createDTOUTSRGISSUEReqHEAD() {
        return new DTOUTSRGISSUEReq.HEAD();
    }

    /**
     * Create an instance of {@link DTOUTSRGISSUEReq }
     *
     */
    public DTOUTSRGISSUEReq createDTOUTSRGISSUEReq() {
        return new DTOUTSRGISSUEReq();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTOUTSRGISSUERes }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/OUTSRGISSUE/Sender", name = "MT_OUTSRGISSUE_Res")
    public JAXBElement<DTOUTSRGISSUERes> createMTOUTSRGISSUERes(
            DTOUTSRGISSUERes value) {
        return new JAXBElement<DTOUTSRGISSUERes>(_MTOUTSRGISSUERes_QNAME,
                DTOUTSRGISSUERes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTOUTSRGISSUEReq }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/OUTSRGISSUE/Sender", name = "MT_OUTSRGISSUE_Req")
    public JAXBElement<DTOUTSRGISSUEReq> createMTOUTSRGISSUEReq(
            DTOUTSRGISSUEReq value) {
        return new JAXBElement<DTOUTSRGISSUEReq>(_MTOUTSRGISSUEReq_QNAME,
                DTOUTSRGISSUEReq.class, null, value);
    }

}

