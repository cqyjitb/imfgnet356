package yj.core.webservice_queryoldzpgdbar.sender;

import yj.core.webservice_queryoldzpgdbar.receiver.DTQUERYOLDZPGDBARSendRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.queryoldzpgdbar.sender
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

    private final static QName _MTQUERYOLDZPGDBARSendReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/QueryOldZpgdbar/Sender",
            "MT_QUERYOLDZPGDBAR_Send_Req");
    private final static QName _MTQUERYOLDZPGDBARSendRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/QueryOldZpgdbar/Sender",
            "MT_QUERYOLDZPGDBAR_Send_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.queryoldzpgdbar.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTQUERYOLDZPGDBARSendRes.RETURN }
     *
     */
    public DTQUERYOLDZPGDBARSendRes.RETURN createDTQUERYOLDZPGDBARSendResRETURN() {
        return new DTQUERYOLDZPGDBARSendRes.RETURN();
    }

    /**
     * Create an instance of {@link DTQUERYOLDZPGDBARSendRes }
     *
     */
    public DTQUERYOLDZPGDBARSendRes createDTQUERYOLDZPGDBARSendRes() {
        return new DTQUERYOLDZPGDBARSendRes();
    }

    /**
     * Create an instance of {@link DTQUERYOLDZPGDBARSendReq.ITEM }
     *
     */
    public DTQUERYOLDZPGDBARSendReq.ITEM createDTQUERYOLDZPGDBARSendReqITEM() {
        return new DTQUERYOLDZPGDBARSendReq.ITEM();
    }

    /**
     * Create an instance of {@link DTQUERYOLDZPGDBARSendReq }
     *
     */
    public DTQUERYOLDZPGDBARSendReq createDTQUERYOLDZPGDBARSendReq() {
        return new DTQUERYOLDZPGDBARSendReq();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTQUERYOLDZPGDBARSendReq }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/QueryOldZpgdbar/Sender", name = "MT_QUERYOLDZPGDBAR_Send_Req")
    public JAXBElement<DTQUERYOLDZPGDBARSendReq> createMTQUERYOLDZPGDBARSendReq(
            DTQUERYOLDZPGDBARSendReq value) {
        return new JAXBElement<DTQUERYOLDZPGDBARSendReq>(
                _MTQUERYOLDZPGDBARSendReq_QNAME,
                DTQUERYOLDZPGDBARSendReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTQUERYOLDZPGDBARSendRes }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/QueryOldZpgdbar/Sender", name = "MT_QUERYOLDZPGDBAR_Send_Res")
    public JAXBElement<DTQUERYOLDZPGDBARSendRes> createMTQUERYOLDZPGDBARSendRes(
            DTQUERYOLDZPGDBARSendRes value) {
        return new JAXBElement<DTQUERYOLDZPGDBARSendRes>(
                _MTQUERYOLDZPGDBARSendRes_QNAME,
                DTQUERYOLDZPGDBARSendRes.class, null, value);
    }

}
