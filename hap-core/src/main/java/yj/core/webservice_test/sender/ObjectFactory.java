package yj.core.webservice_test.sender;

import yj.core.webservice_test.dto.InsertOrder;
import yj.core.webservice_test.dto.OReturnData;
import yj.core.webservice_test.dto.QueryOtherWeight;
import yj.core.webservice_test.dto.WReturnData;
import yj.core.webservice_test.receiver.InsertOrderResponse;
import yj.core.webservice_test.receiver.QueryOtherWeightResponse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.yj.service.impl package.
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

    private final static QName _InsertOrderResponse_QNAME = new QName(
            "http://Impl.service.YJ.com/", "insertOrderResponse");
    private final static QName _QueryOtherWeight_QNAME = new QName(
            "http://Impl.service.YJ.com/", "queryOtherWeight");
    private final static QName _QueryOtherWeightResponse_QNAME = new QName(
            "http://Impl.service.YJ.com/", "queryOtherWeightResponse");
    private final static QName _InsertOrder_QNAME = new QName(
            "http://Impl.service.YJ.com/", "insertOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.yj.service.impl
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryOtherWeightResponse }
     *
     */
    public QueryOtherWeightResponse createQueryOtherWeightResponse() {
        return new QueryOtherWeightResponse();
    }

    /**
     * Create an instance of {@link OReturnData }
     *
     */
    public OReturnData createOReturnData() {
        return new OReturnData();
    }

    /**
     * Create an instance of {@link WReturnData }
     *
     */
    public WReturnData createWReturnData() {
        return new WReturnData();
    }

    /**
     * Create an instance of {@link InsertOrder }
     *
     */
    public InsertOrder createInsertOrder() {
        return new InsertOrder();
    }

    /**
     * Create an instance of {@link InsertOrderResponse }
     *
     */
    public InsertOrderResponse createInsertOrderResponse() {
        return new InsertOrderResponse();
    }

    /**
     * Create an instance of {@link QueryOtherWeight }
     *
     */
    public QueryOtherWeight createQueryOtherWeight() {
        return new QueryOtherWeight();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link InsertOrderResponse }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://Impl.service.YJ.com/", name = "insertOrderResponse")
    public JAXBElement<InsertOrderResponse> createInsertOrderResponse(
            InsertOrderResponse value) {
        return new JAXBElement<InsertOrderResponse>(_InsertOrderResponse_QNAME,
                InsertOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link QueryOtherWeight }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://Impl.service.YJ.com/", name = "queryOtherWeight")
    public JAXBElement<QueryOtherWeight> createQueryOtherWeight(
            QueryOtherWeight value) {
        return new JAXBElement<QueryOtherWeight>(_QueryOtherWeight_QNAME,
                QueryOtherWeight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link QueryOtherWeightResponse }{@code >}
     *
     */
    @XmlElementDecl(namespace = "http://Impl.service.YJ.com/", name = "queryOtherWeightResponse")
    public JAXBElement<QueryOtherWeightResponse> createQueryOtherWeightResponse(
            QueryOtherWeightResponse value) {
        return new JAXBElement<QueryOtherWeightResponse>(
                _QueryOtherWeightResponse_QNAME,
                QueryOtherWeightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertOrder }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "http://Impl.service.YJ.com/", name = "insertOrder")
    public JAXBElement<InsertOrder> createInsertOrder(InsertOrder value) {
        return new JAXBElement<InsertOrder>(_InsertOrder_QNAME,
                InsertOrder.class, null, value);
    }

}

