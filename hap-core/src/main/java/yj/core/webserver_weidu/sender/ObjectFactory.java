package yj.core.webserver_weidu.sender;

/**
 * Created by 917110140 on 2018/9/29.
 */

import yj.core.webserver_weidu.receiver.DTWEIDURes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.weidu.sender package.
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

    private final static QName _MTWEIDUReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/WEIDU/Sender", "MT_WEIDU_Req");
    private final static QName _MTWEIDURes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/WEIDU/Sender", "MT_WEIDU_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.weidu.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTWEIDURes.TZTPP0013 }
     *
     */
    public DTWEIDURes.TZTPP0013 createDTWEIDUResTZTPP0013() {
        return new DTWEIDURes.TZTPP0013();
    }

    /**
     * Create an instance of {@link DTWEIDURes }
     *
     */
    public DTWEIDURes createDTWEIDURes() {
        return new DTWEIDURes();
    }

    /**
     * Create an instance of {@link DTWEIDURes.TZTPP0012 }
     *
     */
    public DTWEIDURes.TZTPP0012 createDTWEIDUResTZTPP0012() {
        return new DTWEIDURes.TZTPP0012();
    }

    /**
     * Create an instance of {@link DTWEIDUReq.ITEM }
     *
     */
    public DTWEIDUReq.ITEM createDTWEIDUReqITEM() {
        return new DTWEIDUReq.ITEM();
    }

    /**
     * Create an instance of {@link DTWEIDUReq }
     *
     */
    public DTWEIDUReq createDTWEIDUReq() {
        return new DTWEIDUReq();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTWEIDUReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/WEIDU/Sender", name = "MT_WEIDU_Req")
    public JAXBElement<DTWEIDUReq> createMTWEIDUReq(DTWEIDUReq value) {
        return new JAXBElement<DTWEIDUReq>(_MTWEIDUReq_QNAME, DTWEIDUReq.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTWEIDURes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/WEIDU/Sender", name = "MT_WEIDU_Res")
    public JAXBElement<DTWEIDURes> createMTWEIDURes(DTWEIDURes value) {
        return new JAXBElement<DTWEIDURes>(_MTWEIDURes_QNAME, DTWEIDURes.class,
                null, value);
    }

}

