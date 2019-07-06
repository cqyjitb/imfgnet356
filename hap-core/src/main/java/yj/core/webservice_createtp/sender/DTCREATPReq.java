package yj.core.webservice_createtp.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_CREATP_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_CREATP_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTXT2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBQBD" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_CREATP_Req", propOrder = { "item" })
public class DTCREATPReq {

    @XmlElement(name = "ITEM", required = true)
    protected DTCREATPReq.ITEM item;

    /**
     * Gets the value of the item property.
     *
     * @return possible object is {@link DTCREATPReq.ITEM }
     *
     */
    public DTCREATPReq.ITEM getITEM() {
        return item;
    }

    /**
     * Sets the value of the item property.
     *
     * @param value
     *            allowed object is {@link DTCREATPReq.ITEM }
     *
     */
    public void setITEM(DTCREATPReq.ITEM value) {
        this.item = value;
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
     *         &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTXT2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBQBD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "werks", "datum", "matnr", "zmnum",
            "ztxt2", "menge", "ztpbar", "zbqbd" })
    public static class ITEM {

        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "DATUM", required = true)
        protected String datum;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "ZMNUM", required = true)
        protected String zmnum;
        @XmlElement(name = "ZTXT2", required = true)
        protected String ztxt2;
        @XmlElement(name = "MENGE", required = true)
        protected String menge;
        @XmlElement(name = "ZTPBAR", required = true)
        protected String ztpbar;
        @XmlElement(name = "ZBQBD", required = true)
        protected String zbqbd;

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
         * Gets the value of the datum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDATUM() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setDATUM(String value) {
            this.datum = value;
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
         * Gets the value of the ztxt2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTXT2() {
            return ztxt2;
        }

        /**
         * Sets the value of the ztxt2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTXT2(String value) {
            this.ztxt2 = value;
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
         * Gets the value of the ztpbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPBAR() {
            return ztpbar;
        }

        /**
         * Sets the value of the ztpbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPBAR(String value) {
            this.ztpbar = value;
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

    }

}
