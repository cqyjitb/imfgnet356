package yj.core.webservice_queryXhcard.sender;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_QUERYXHCARD_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_QUERYXHCARD_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="QTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_QUERYXHCARD_Req", propOrder = { "zxhbar", "matnr", "lgort",
        "qtype" })
public class DTQUERYXHCARDReq {

    @XmlElement(name = "ZXHBAR")
    protected String zxhbar;
    @XmlElement(name = "MATNR")
    protected String matnr;
    @XmlElement(name = "LGORT")
    protected String lgort;
    @XmlElement(name = "QTYPE", required = true)
    protected String qtype;

    /**
     * Gets the value of the zxhbar property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getZXHBAR() {
        return zxhbar;
    }

    /**
     * Sets the value of the zxhbar property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setZXHBAR(String value) {
        this.zxhbar = value;
    }

    /**
     * Gets the value of the matnr property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMATNR() {
        return matnr;
    }

    /**
     * Sets the value of the matnr property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMATNR(String value) {
        this.matnr = value;
    }

    /**
     * Gets the value of the lgort property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLGORT() {
        return lgort;
    }

    /**
     * Sets the value of the lgort property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setLGORT(String value) {
        this.lgort = value;
    }

    /**
     * Gets the value of the qtype property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getQTYPE() {
        return qtype;
    }

    /**
     * Sets the value of the qtype property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setQTYPE(String value) {
        this.qtype = value;
    }

}
