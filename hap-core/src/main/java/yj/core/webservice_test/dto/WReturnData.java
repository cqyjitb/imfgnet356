package yj.core.webservice_test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for wReturnData complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="wReturnData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BUKRS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EBELP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NET" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZLPN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZSBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wReturnData", propOrder = { "bukrs", "ebeln", "ebelp",
        "matnr", "message", "net", "type", "werks", "zlpn", "zsbeln" })
public class WReturnData {

    @XmlElement(name = "BUKRS")
    protected String bukrs;
    @XmlElement(name = "EBELN")
    protected String ebeln;
    @XmlElement(name = "EBELP")
    protected String ebelp;
    @XmlElement(name = "MATNR")
    protected String matnr;
    protected String message;
    @XmlElement(name = "NET")
    protected String net;
    protected String type;
    @XmlElement(name = "WERKS")
    protected String werks;
    @XmlElement(name = "ZLPN")
    protected String zlpn;
    @XmlElement(name = "ZSBELN")
    protected String zsbeln;

    /**
     * Gets the value of the bukrs property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBUKRS() {
        return bukrs;
    }

    /**
     * Sets the value of the bukrs property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setBUKRS(String value) {
        this.bukrs = value;
    }

    /**
     * Gets the value of the ebeln property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEBELN() {
        return ebeln;
    }

    /**
     * Sets the value of the ebeln property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setEBELN(String value) {
        this.ebeln = value;
    }

    /**
     * Gets the value of the ebelp property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEBELP() {
        return ebelp;
    }

    /**
     * Sets the value of the ebelp property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setEBELP(String value) {
        this.ebelp = value;
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
     * Gets the value of the message property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the net property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNET() {
        return net;
    }

    /**
     * Sets the value of the net property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setNET(String value) {
        this.net = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the werks property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getWERKS() {
        return werks;
    }

    /**
     * Sets the value of the werks property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setWERKS(String value) {
        this.werks = value;
    }

    /**
     * Gets the value of the zlpn property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getZLPN() {
        return zlpn;
    }

    /**
     * Sets the value of the zlpn property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setZLPN(String value) {
        this.zlpn = value;
    }

    /**
     * Gets the value of the zsbeln property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getZSBELN() {
        return zsbeln;
    }

    /**
     * Sets the value of the zsbeln property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setZSBELN(String value) {
        this.zsbeln = value;
    }

}
