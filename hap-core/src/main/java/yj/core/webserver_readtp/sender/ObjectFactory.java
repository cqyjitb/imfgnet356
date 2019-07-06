package yj.core.webserver_readtp.sender;

/**
 * Created by 917110140 on 2018/10/6.
 */


import yj.core.webserver_readtp.receiver.DTREADTPRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.readtp.sender package.
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

    private final static QName _MTREADTPRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READTP/Sender", "MT_READTP_Res");
    private final static QName _MTREADTPReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/READTP/Sender", "MT_READTP_Req");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.readtp.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTREADTPRes.RETURN }
     *
     */
    public DTREADTPRes.RETURN createDTREADTPResRETURN() {
        return new DTREADTPRes.RETURN();
    }

    /**
     * Create an instance of {@link DTREADTPReq }
     *
     */
    public DTREADTPReq createDTREADTPReq() {
        return new DTREADTPReq();
    }

    /**
     * Create an instance of {@link DTREADTPReq.ITEM }
     *
     */
    public DTREADTPReq.ITEM createDTREADTPReqITEM() {
        return new DTREADTPReq.ITEM();
    }

    /**
     * Create an instance of {@link DTREADTPRes }
     *
     */
    public DTREADTPRes createDTREADTPRes() {
        return new DTREADTPRes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTREADTPRes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READTP/Sender", name = "MT_READTP_Res")
    public JAXBElement<DTREADTPRes> createMTREADTPRes(DTREADTPRes value) {
        return new JAXBElement<DTREADTPRes>(_MTREADTPRes_QNAME,
                DTREADTPRes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTREADTPReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/READTP/Sender", name = "MT_READTP_Req")
    public JAXBElement<DTREADTPReq> createMTREADTPReq(DTREADTPReq value) {
        return new JAXBElement<DTREADTPReq>(_MTREADTPReq_QNAME,
                DTREADTPReq.class, null, value);
    }

}
