package yj.core.webservice_newbg.sender;

/**
 * Created by 917110140 on 2018/8/11.
 */


import yj.core.webservice_newbg.receiver.DTBAOGONGSendRes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cq_yj.hap.baogong.sender package.
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

    private final static QName _MTBAOGONGSendReq_QNAME = new QName(
            "http://www.cq-yj.com/HAP/BAOGONG/Sender", "MT_BAOGONG_Send_Req");
    private final static QName _MTBAOGONGSendRes_QNAME = new QName(
            "http://www.cq-yj.com/HAP/BAOGONG/Sender", "MT_BAOGONG_Send_Res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.cq_yj.hap.baogong.sender
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTBAOGONGSendRes.DETAIL.MAKT }
     *
     */
    public DTBAOGONGSendRes.DETAIL.MAKT createDTBAOGONGSendResDETAILMAKT() {
        return new DTBAOGONGSendRes.DETAIL.MAKT();
    }

    /**
     * Create an instance of {@link DTBAOGONGSendReq }
     *
     */
    public DTBAOGONGSendReq createDTBAOGONGSendReq() {
        return new DTBAOGONGSendReq();
    }

    /**
     * Create an instance of {@link DTBAOGONGSendRes.RETURN }
     *
     */
    public DTBAOGONGSendRes.RETURN createDTBAOGONGSendResRETURN() {
        return new DTBAOGONGSendRes.RETURN();
    }

    /**
     * Create an instance of {@link DTBAOGONGSendReq.ITEM }
     *
     */
    public DTBAOGONGSendReq.ITEM createDTBAOGONGSendReqITEM() {
        return new DTBAOGONGSendReq.ITEM();
    }

    /**
     * Create an instance of {@link DTBAOGONGSendRes.DETAIL.AFVC }
     *
     */
    public DTBAOGONGSendRes.DETAIL.AFVC createDTBAOGONGSendResDETAILAFVC() {
        return new DTBAOGONGSendRes.DETAIL.AFVC();
    }

    /**
     * Create an instance of {@link DTBAOGONGSendRes }
     *
     */
    public DTBAOGONGSendRes createDTBAOGONGSendRes() {
        return new DTBAOGONGSendRes();
    }

    /**
     * Create an instance of {@link DTBAOGONGSendRes.DETAIL }
     *
     */
    public DTBAOGONGSendRes.DETAIL createDTBAOGONGSendResDETAIL() {
        return new DTBAOGONGSendRes.DETAIL();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTBAOGONGSendReq }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/BAOGONG/Sender", name = "MT_BAOGONG_Send_Req")
    public JAXBElement<DTBAOGONGSendReq> createMTBAOGONGSendReq(
            DTBAOGONGSendReq value) {
        return new JAXBElement<DTBAOGONGSendReq>(_MTBAOGONGSendReq_QNAME,
                DTBAOGONGSendReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link DTBAOGONGSendRes }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://www.cq-yj.com/HAP/BAOGONG/Sender", name = "MT_BAOGONG_Send_Res")
    public JAXBElement<DTBAOGONGSendRes> createMTBAOGONGSendRes(
            DTBAOGONGSendRes value) {
        return new JAXBElement<DTBAOGONGSendRes>(_MTBAOGONGSendRes_QNAME,
                DTBAOGONGSendRes.class, null, value);
    }

}


