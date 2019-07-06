package yj.core.webservice_readbglog.sender;


import yj.core.webservice_readbglog.receiver.DTREADBGLOGRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.readbglog.sender package.
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

    private final static QName _MTREADBGLOGRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READBGLOG/Sender", "MT_READBGLOG_Res");
    private final static QName _MTREADBGLOGReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READBGLOG/Sender", "MT_READBGLOG_Req");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.readbglog.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTREADBGLOGReq.IDATA }
     *
     */
    public DTREADBGLOGReq.IDATA createDTREADBGLOGReqIDATA() {
        return new DTREADBGLOGReq.IDATA();
    }

    /**
     * Create an instance of {@link DTREADBGLOGRes.DETAIL.AFVC }
     *
     */
    public DTREADBGLOGRes.DETAIL.AFVC createDTREADBGLOGResDETAILAFVC() {
        return new DTREADBGLOGRes.DETAIL.AFVC();
    }

    /**
     * Create an instance of {@link DTREADBGLOGRes.RETURN }
     *
     */
    public DTREADBGLOGRes.RETURN createDTREADBGLOGResRETURN() {
        return new DTREADBGLOGRes.RETURN();
    }

    /**
     * Create an instance of {@link DTREADBGLOGRes.DETAIL }
     *
     */
    public DTREADBGLOGRes.DETAIL createDTREADBGLOGResDETAIL() {
        return new DTREADBGLOGRes.DETAIL();
    }

    /**
     * Create an instance of {@link DTREADBGLOGReq }
     *
     */
    public DTREADBGLOGReq createDTREADBGLOGReq() {
        return new DTREADBGLOGReq();
    }

    /**
     * Create an instance of {@link DTREADBGLOGRes.DETAIL.MAKT }
     *
     */
    public DTREADBGLOGRes.DETAIL.MAKT createDTREADBGLOGResDETAILMAKT() {
        return new DTREADBGLOGRes.DETAIL.MAKT();
    }

    /**
     * Create an instance of {@link DTREADBGLOGRes }
     *
     */
    public DTREADBGLOGRes createDTREADBGLOGRes() {
        return new DTREADBGLOGRes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTREADBGLOGRes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READBGLOG/Sender", name = "MT_READBGLOG_Res")
    public JAXBElement<DTREADBGLOGRes> createMTREADBGLOGRes(DTREADBGLOGRes value) {
        return new JAXBElement<DTREADBGLOGRes>(_MTREADBGLOGRes_QNAME,
                DTREADBGLOGRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTREADBGLOGReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READBGLOG/Sender", name = "MT_READBGLOG_Req")
    public JAXBElement<DTREADBGLOGReq> createMTREADBGLOGReq(DTREADBGLOGReq value) {
        return new JAXBElement<DTREADBGLOGReq>(_MTREADBGLOGReq_QNAME,
                DTREADBGLOGReq.class, null, value);
    }

}

