package yj.core.webservice_migo.sender;

import yj.core.webservice_migo.receiver.DTMIGOSendRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.migo.sender package.
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

    private final static QName _MTMIGOSendReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/MIGO/Sender", "MT_MIGO_Send_Req");
    private final static QName _MTMIGOSendRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/MIGO/Sender", "MT_MIGO_Send_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.migo.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTMIGOSendRes }
     *
     */
    public DTMIGOSendRes createDTMIGOSendRes() {
        return new DTMIGOSendRes();
    }

    /**
     * Create an instance of {@link DTMIGOSendReq.ITEM }
     *
     */
    public DTMIGOSendReq.ITEM createDTMIGOSendReqITEM() {
        return new DTMIGOSendReq.ITEM();
    }

    /**
     * Create an instance of {@link DTMIGOSendRes.RETURN }
     *
     */
    public DTMIGOSendRes.RETURN createDTMIGOSendResRETURN() {
        return new DTMIGOSendRes.RETURN();
    }

    /**
     * Create an instance of {@link DTMIGOSendReq }
     *
     */
    public DTMIGOSendReq createDTMIGOSendReq() {
        return new DTMIGOSendReq();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTMIGOSendReq }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/MIGO/Sender", name = "MT_MIGO_Send_Req")
    public JAXBElement<DTMIGOSendReq> createMTMIGOSendReq(DTMIGOSendReq value) {
        return new JAXBElement<DTMIGOSendReq>(_MTMIGOSendReq_QNAME,
                DTMIGOSendReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTMIGOSendRes }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/MIGO/Sender", name = "MT_MIGO_Send_Res")
    public JAXBElement<DTMIGOSendRes> createMTMIGOSendRes(DTMIGOSendRes value) {
        return new JAXBElement<DTMIGOSendRes>(_MTMIGOSendRes_QNAME,
                DTMIGOSendRes.class, null, value);
    }

}
