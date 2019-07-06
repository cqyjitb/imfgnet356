package yj.core.webservicepd.sender;

/**
 * Created by 917110140 on 2018/7/25.
 */
import yj.core.webservicepd.receiver.DTPANDIANRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.pandian.sender package.
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

    private final static QName _MTPANDIANReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/PANDIAN/Sender", "MT_PANDIAN_Req");
    private final static QName _MTPANDIANRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/PANDIAN/Sender", "MT_PANDIAN_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.pandian.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTPANDIANRes }
     *
     */
    public DTPANDIANRes createDTPANDIANRes() {
        return new DTPANDIANRes();
    }

    /**
     * Create an instance of {@link DTPANDIANReq.ITEM }
     *
     */
    public DTPANDIANReq.ITEM createDTPANDIANReqITEM() {
        return new DTPANDIANReq.ITEM();
    }

    /**
     * Create an instance of {@link DTPANDIANReq }
     *
     */
    public DTPANDIANReq createDTPANDIANReq() {
        return new DTPANDIANReq();
    }

    /**
     * Create an instance of {@link DTPANDIANRes.RETURN }
     *
     */
    public DTPANDIANRes.RETURN createDTPANDIANResRETURN() {
        return new DTPANDIANRes.RETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTPANDIANReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PANDIAN/Sender", name = "MT_PANDIAN_Req")
    public JAXBElement<DTPANDIANReq> createMTPANDIANReq(DTPANDIANReq value) {
        return new JAXBElement<DTPANDIANReq>(_MTPANDIANReq_QNAME,
                DTPANDIANReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTPANDIANRes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/PANDIAN/Sender", name = "MT_PANDIAN_Res")
    public JAXBElement<DTPANDIANRes> createMTPANDIANRes(DTPANDIANRes value) {
        return new JAXBElement<DTPANDIANRes>(_MTPANDIANRes_QNAME,
                DTPANDIANRes.class, null, value);
    }

}

