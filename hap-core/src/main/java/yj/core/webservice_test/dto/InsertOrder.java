package yj.core.webservice_test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for insertOrder complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="insertOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BUKRS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZLPN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZSBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EBELP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZFDAT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SENGE" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="PACNUM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertOrder", propOrder = { "bukrs", "werks", "zlpn",
        "zsbeln", "ebeln", "ebelp", "matnr", "maktx", "zfdat", "senge",
        "pacnum", "meins", "lifnr" })
public class InsertOrder {

    @XmlElement(name = "BUKRS")
    protected String bukrs;
    @XmlElement(name = "WERKS")
    protected String werks;
    @XmlElement(name = "ZLPN")
    protected String zlpn;
    @XmlElement(name = "ZSBELN")
    protected String zsbeln;
    @XmlElement(name = "EBELN")
    protected String ebeln;
    @XmlElement(name = "EBELP")
    protected String ebelp;
    @XmlElement(name = "MATNR")
    protected String matnr;
    @XmlElement(name = "MAKTX")
    protected String maktx;
    @XmlElement(name = "ZFDAT")
    protected String zfdat;
    @XmlElement(name = "SENGE")
    protected Double senge;
    @XmlElement(name = "PACNUM")
    protected int pacnum;
    @XmlElement(name = "MEINS")
    protected String meins;
    @XmlElement(name = "LIFNR")
    protected String lifnr;

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
     * Gets the value of the maktx property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMAKTX() {
        return maktx;
    }

    /**
     * Sets the value of the maktx property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMAKTX(String value) {
        this.maktx = value;
    }

    /**
     * Gets the value of the zfdat property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getZFDAT() {
        return zfdat;
    }

    /**
     * Sets the value of the zfdat property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setZFDAT(String value) {
        this.zfdat = value;
    }

    /**
     * Gets the value of the senge property.
     *
     * @return possible object is {@link Double }
     *
     */
    public Double getSENGE() {
        return senge;
    }

    /**
     * Sets the value of the senge property.
     *
     * @param value
     *            allowed object is {@link Double }
     *
     */
    public void setSENGE(Double value) {
        this.senge = value;
    }

    /**
     * Gets the value of the pacnum property.
     *
     */
    public int getPACNUM() {
        return pacnum;
    }

    /**
     * Sets the value of the pacnum property.
     *
     */
    public void setPACNUM(int value) {
        this.pacnum = value;
    }

    /**
     * Gets the value of the meins property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMEINS() {
        return meins;
    }

    /**
     * Sets the value of the meins property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMEINS(String value) {
        this.meins = value;
    }

    /**
     * Gets the value of the lifnr property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLIFNR() {
        return lifnr;
    }

    /**
     * Sets the value of the lifnr property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setLIFNR(String value) {
        this.lifnr = value;
    }

}
