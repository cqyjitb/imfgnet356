
package yj.core.webservice_xhcard.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_XHCARD_SENDX_REQ complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_XHCARD_SENDX_REQ">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHZT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHZT2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHWZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZJYY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCTPTM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBQBD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARGKC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_XHCARD_SENDX_REQ", propOrder = { "item" })
public class DTXHCARDSENDXREQ {

    @XmlElement(name = "ITEM", required = true)
    protected List<ITEM> item;

    /**
     * Gets the value of the item property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the item property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getITEM().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ITEM }
     *
     *
     */
    public List<ITEM> getITEM() {
        if (item == null) {
            item = new ArrayList<ITEM>();
        }
        return this.item;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXHNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXHZT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXHZT2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXHWZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZJYY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZSCTPTM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBQBD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARGKC" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "werks", "matnr", "charg", "zxhnum",
            "zxhzt", "zxhzt2", "lgort", "menge", "meins", "zxhwz", "aufnr",
            "zxhbar", "zjyy", "zscbc", "zscx", "zmnum", "zsctptm", "ztxt",
            "zbqbd", "chargkc" })
    public static class ITEM {

        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "CHARG", required = true)
        protected String charg;
        @XmlElement(name = "ZXHNUM", required = true)
        protected String zxhnum;
        @XmlElement(name = "ZXHZT", required = true)
        protected String zxhzt;
        @XmlElement(name = "ZXHZT2", required = true)
        protected String zxhzt2;
        @XmlElement(name = "LGORT", required = true)
        protected String lgort;
        @XmlElement(name = "MENGE", required = true)
        protected String menge;
        @XmlElement(name = "MEINS", required = true)
        protected String meins;
        @XmlElement(name = "ZXHWZ", required = true)
        protected String zxhwz;
        @XmlElement(name = "AUFNR", required = true)
        protected String aufnr;
        @XmlElement(name = "ZXHBAR", required = true)
        protected String zxhbar;
        @XmlElement(name = "ZJYY", required = true)
        protected String zjyy;
        @XmlElement(name = "ZSCBC", required = true)
        protected String zscbc;
        @XmlElement(name = "ZSCX", required = true)
        protected String zscx;
        @XmlElement(name = "ZMNUM", required = true)
        protected String zmnum;
        @XmlElement(name = "ZSCTPTM", required = true)
        protected String zsctptm;
        @XmlElement(name = "ZTXT", required = true)
        protected String ztxt;
        @XmlElement(name = "ZBQBD", required = true)
        protected String zbqbd;
        @XmlElement(name = "CHARGKC", required = true)
        protected String chargkc;

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
         * Gets the value of the charg property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCHARG() {
            return charg;
        }

        /**
         * Sets the value of the charg property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCHARG(String value) {
            this.charg = value;
        }

        /**
         * Gets the value of the zxhnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXHNUM() {
            return zxhnum;
        }

        /**
         * Sets the value of the zxhnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXHNUM(String value) {
            this.zxhnum = value;
        }

        /**
         * Gets the value of the zxhzt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXHZT() {
            return zxhzt;
        }

        /**
         * Sets the value of the zxhzt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXHZT(String value) {
            this.zxhzt = value;
        }

        /**
         * Gets the value of the zxhzt2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXHZT2() {
            return zxhzt2;
        }

        /**
         * Sets the value of the zxhzt2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXHZT2(String value) {
            this.zxhzt2 = value;
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
         * Gets the value of the menge property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMENGE() {
            return menge;
        }

        /**
         * Sets the value of the menge property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMENGE(String value) {
            this.menge = value;
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
         * Gets the value of the zxhwz property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZXHWZ() {
            return zxhwz;
        }

        /**
         * Sets the value of the zxhwz property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZXHWZ(String value) {
            this.zxhwz = value;
        }

        /**
         * Gets the value of the aufnr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getAUFNR() {
            return aufnr;
        }

        /**
         * Sets the value of the aufnr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setAUFNR(String value) {
            this.aufnr = value;
        }

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
         * Gets the value of the zjyy property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZJYY() {
            return zjyy;
        }

        /**
         * Sets the value of the zjyy property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZJYY(String value) {
            this.zjyy = value;
        }

        /**
         * Gets the value of the zscbc property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZSCBC() {
            return zscbc;
        }

        /**
         * Sets the value of the zscbc property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZSCBC(String value) {
            this.zscbc = value;
        }

        /**
         * Gets the value of the zscx property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZSCX() {
            return zscx;
        }

        /**
         * Sets the value of the zscx property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZSCX(String value) {
            this.zscx = value;
        }

        /**
         * Gets the value of the zmnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMNUM() {
            return zmnum;
        }

        /**
         * Sets the value of the zmnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMNUM(String value) {
            this.zmnum = value;
        }

        /**
         * Gets the value of the zsctptm property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZSCTPTM() {
            return zsctptm;
        }

        /**
         * Sets the value of the zsctptm property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZSCTPTM(String value) {
            this.zsctptm = value;
        }

        /**
         * Gets the value of the ztxt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTXT() {
            return ztxt;
        }

        /**
         * Sets the value of the ztxt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTXT(String value) {
            this.ztxt = value;
        }

        /**
         * Gets the value of the zbqbd property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBQBD() {
            return zbqbd;
        }

        /**
         * Sets the value of the zbqbd property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBQBD(String value) {
            this.zbqbd = value;
        }

        /**
         * Gets the value of the chargkc property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCHARGKC() {
            return chargkc;
        }

        /**
         * Sets the value of the chargkc property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCHARGKC(String value) {
            this.chargkc = value;
        }

    }

}

