package yj.core.webservicepd.sender;

/**
 * Created by 917110140 on 2018/7/25.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_PANDIAN_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_PANDIAN_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PDDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PDTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GAMNG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="QRMNG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZCFWZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZLYLX" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_PANDIAN_Req", propOrder = { "item" })
public class DTPANDIANReq {

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
     *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PDDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PDTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GAMNG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="QRMNG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZCFWZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZLYLX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "werks", "aufnr", "zpgdbar", "zpgdbh",
            "pddat", "pdtim", "vornr", "matnr", "gamng", "datum", "qrmng",
            "zcfwz", "zlylx" })
    public static class ITEM {

        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "AUFNR", required = true)
        protected String aufnr;
        @XmlElement(name = "ZPGDBAR", required = true)
        protected String zpgdbar;
        @XmlElement(name = "ZPGDBH", required = true)
        protected String zpgdbh;
        @XmlElement(name = "PDDAT", required = true)
        protected String pddat;
        @XmlElement(name = "PDTIM", required = true)
        protected String pdtim;
        @XmlElement(name = "VORNR", required = true)
        protected String vornr;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "GAMNG", required = true)
        protected String gamng;
        @XmlElement(name = "DATUM", required = true)
        protected String datum;
        @XmlElement(name = "QRMNG", required = true)
        protected String qrmng;
        @XmlElement(name = "ZCFWZ", required = true)
        protected String zcfwz;
        @XmlElement(name = "ZLYLX", required = true)
        protected String zlylx;

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
         * Gets the value of the zpgdbar property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZPGDBAR() {
            return zpgdbar;
        }

        /**
         * Sets the value of the zpgdbar property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZPGDBAR(String value) {
            this.zpgdbar = value;
        }

        /**
         * Gets the value of the zpgdbh property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZPGDBH() {
            return zpgdbh;
        }

        /**
         * Sets the value of the zpgdbh property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZPGDBH(String value) {
            this.zpgdbh = value;
        }

        /**
         * Gets the value of the pddat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getPDDAT() {
            return pddat;
        }

        /**
         * Sets the value of the pddat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setPDDAT(String value) {
            this.pddat = value;
        }

        /**
         * Gets the value of the pdtim property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getPDTIM() {
            return pdtim;
        }

        /**
         * Sets the value of the pdtim property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setPDTIM(String value) {
            this.pdtim = value;
        }

        /**
         * Gets the value of the vornr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getVORNR() {
            return vornr;
        }

        /**
         * Sets the value of the vornr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setVORNR(String value) {
            this.vornr = value;
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
         * Gets the value of the gamng property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getGAMNG() {
            return gamng;
        }

        /**
         * Sets the value of the gamng property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setGAMNG(String value) {
            this.gamng = value;
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
         * Gets the value of the qrmng property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getQRMNG() {
            return qrmng;
        }

        /**
         * Sets the value of the qrmng property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setQRMNG(String value) {
            this.qrmng = value;
        }

        /**
         * Gets the value of the zcfwz property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZCFWZ() {
            return zcfwz;
        }

        /**
         * Sets the value of the zcfwz property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZCFWZ(String value) {
            this.zcfwz = value;
        }

        /**
         * Gets the value of the zlylx property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZLYLX() {
            return zlylx;
        }

        /**
         * Sets the value of the zlylx property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZLYLX(String value) {
            this.zlylx = value;
        }

    }

}

