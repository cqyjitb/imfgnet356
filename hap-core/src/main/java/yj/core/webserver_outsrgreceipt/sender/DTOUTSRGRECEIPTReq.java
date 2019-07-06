package yj.core.webserver_outsrgreceipt.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_OUTSRGRECEIPT_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_OUTSRGRECEIPT_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HEAD" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RECEIPTNM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZDPDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZDPTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZDPUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PRTFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZIPDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZIPTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZIPUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RECEIPTNM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ISSUENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ISSUENMITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VSNDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EBELN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EBELP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="KTSCH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TXZ01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DIECD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SFFLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TTRECEIPTS" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="ZSHNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="ZLFNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="ZGFNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="ZLOST" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="ZTHNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="GMEIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MBLNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZEILE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MJAHR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RUECK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RMZHL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DEDUCTNTENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZDSDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZDSTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZDSUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_OUTSRGRECEIPT_Req", propOrder = { "head", "item" })
public class DTOUTSRGRECEIPTReq {

    @XmlElement(name = "HEAD")
    protected DTOUTSRGRECEIPTReq.HEAD head;
    @XmlElement(name = "ITEM", required = true)
    protected List<ITEM> item;

    /**
     * Gets the value of the head property.
     *
     * @return possible object is {@link DTOUTSRGRECEIPTReq.HEAD }
     *
     */
    public DTOUTSRGRECEIPTReq.HEAD getHEAD() {
        return head;
    }

    /**
     * Sets the value of the head property.
     *
     * @param value
     *            allowed object is {@link DTOUTSRGRECEIPTReq.HEAD }
     *
     */
    public void setHEAD(DTOUTSRGRECEIPTReq.HEAD value) {
        this.head = value;
    }

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
     * {@link DTOUTSRGRECEIPTReq.ITEM }
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
     *         &lt;element name="RECEIPTNM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZDPDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZDPTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZDPUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PRTFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZIPDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZIPTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZIPUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "receiptnm", "werks", "lifnr", "matnr",
            "status", "zdpdat", "zdptim", "zdpuser", "prtflag", "zipdat",
            "ziptim", "zipuser" })
    public static class HEAD {

        @XmlElement(name = "RECEIPTNM", required = true)
        protected String receiptnm;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "LIFNR", required = true)
        protected String lifnr;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "ZDPDAT", required = true)
        protected String zdpdat;
        @XmlElement(name = "ZDPTIM", required = true)
        protected String zdptim;
        @XmlElement(name = "ZDPUSER", required = true)
        protected String zdpuser;
        @XmlElement(name = "PRTFLAG", required = true)
        protected String prtflag;
        @XmlElement(name = "ZIPDAT", required = true)
        protected String zipdat;
        @XmlElement(name = "ZIPTIM", required = true)
        protected String ziptim;
        @XmlElement(name = "ZIPUSER", required = true)
        protected String zipuser;

        /**
         * Gets the value of the receiptnm property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRECEIPTNM() {
            return receiptnm;
        }

        /**
         * Sets the value of the receiptnm property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRECEIPTNM(String value) {
            this.receiptnm = value;
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
         * Gets the value of the status property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSTATUS() {
            return status;
        }

        /**
         * Sets the value of the status property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSTATUS(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the zdpdat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDPDAT() {
            return zdpdat;
        }

        /**
         * Sets the value of the zdpdat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDPDAT(String value) {
            this.zdpdat = value;
        }

        /**
         * Gets the value of the zdptim property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDPTIM() {
            return zdptim;
        }

        /**
         * Sets the value of the zdptim property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDPTIM(String value) {
            this.zdptim = value;
        }

        /**
         * Gets the value of the zdpuser property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDPUSER() {
            return zdpuser;
        }

        /**
         * Sets the value of the zdpuser property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDPUSER(String value) {
            this.zdpuser = value;
        }

        /**
         * Gets the value of the prtflag property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getPRTFLAG() {
            return prtflag;
        }

        /**
         * Sets the value of the prtflag property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setPRTFLAG(String value) {
            this.prtflag = value;
        }

        /**
         * Gets the value of the zipdat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZIPDAT() {
            return zipdat;
        }

        /**
         * Sets the value of the zipdat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZIPDAT(String value) {
            this.zipdat = value;
        }

        /**
         * Gets the value of the ziptim property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZIPTIM() {
            return ziptim;
        }

        /**
         * Sets the value of the ziptim property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZIPTIM(String value) {
            this.ziptim = value;
        }

        /**
         * Gets the value of the zipuser property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZIPUSER() {
            return zipuser;
        }

        /**
         * Sets the value of the zipuser property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZIPUSER(String value) {
            this.zipuser = value;
        }

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
     *         &lt;element name="RECEIPTNM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ISSUENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ISSUENMITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VSNDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="EBELN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="EBELP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="KTSCH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TXZ01" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DIECD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SFFLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TTRECEIPTS" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="ZSHNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="ZLFNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="ZGFNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="ZLOST" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="ZTHNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="GMEIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MBLNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZEILE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MJAHR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RUECK" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RMZHL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DEDUCTNTENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZDSDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZDSTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZDSUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "receiptnm", "item", "werks", "zpgdbar",
            "vornr", "issuenm", "issuenmitem", "vsnda", "ebeln", "ebelp",
            "ktsch", "txz01", "lifnr", "matnr", "diecd", "sfflg", "menge",
            "matkl", "ttreceipts", "zshnum", "zlfnum", "zgfnum", "zlost",
            "zthnum", "gmein", "charg", "status", "mblnr", "zeile", "mjahr",
            "rueck", "rmzhl", "deductntenm", "zdsdat", "zdstim", "zdsuser" })
    public static class ITEM {

        @XmlElement(name = "RECEIPTNM", required = true)
        protected String receiptnm;
        @XmlElement(name = "ITEM", required = true)
        protected String item;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "ZPGDBAR", required = true)
        protected String zpgdbar;
        @XmlElement(name = "VORNR", required = true)
        protected String vornr;
        @XmlElement(name = "ISSUENM", required = true)
        protected String issuenm;
        @XmlElement(name = "ISSUENMITEM", required = true)
        protected String issuenmitem;
        @XmlElement(name = "VSNDA", required = true)
        protected String vsnda;
        @XmlElement(name = "EBELN", required = true)
        protected String ebeln;
        @XmlElement(name = "EBELP", required = true)
        protected String ebelp;
        @XmlElement(name = "KTSCH", required = true)
        protected String ktsch;
        @XmlElement(name = "TXZ01", required = true)
        protected String txz01;
        @XmlElement(name = "LIFNR", required = true)
        protected String lifnr;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "DIECD", required = true)
        protected String diecd;
        @XmlElement(name = "SFFLG", required = true)
        protected String sfflg;
        @XmlElement(name = "MENGE")
        protected double menge;
        @XmlElement(name = "MATKL", required = true)
        protected String matkl;
        @XmlElement(name = "TTRECEIPTS")
        protected double ttreceipts;
        @XmlElement(name = "ZSHNUM")
        protected double zshnum;
        @XmlElement(name = "ZLFNUM")
        protected double zlfnum;
        @XmlElement(name = "ZGFNUM")
        protected double zgfnum;
        @XmlElement(name = "ZLOST")
        protected double zlost;
        @XmlElement(name = "ZTHNUM")
        protected double zthnum;
        @XmlElement(name = "GMEIN", required = true)
        protected String gmein;
        @XmlElement(name = "CHARG", required = true)
        protected String charg;
        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "MBLNR", required = true)
        protected String mblnr;
        @XmlElement(name = "ZEILE", required = true)
        protected String zeile;
        @XmlElement(name = "MJAHR", required = true)
        protected String mjahr;
        @XmlElement(name = "RUECK", required = true)
        protected String rueck;
        @XmlElement(name = "RMZHL", required = true)
        protected String rmzhl;
        @XmlElement(name = "DEDUCTNTENM", required = true)
        protected String deductntenm;
        @XmlElement(name = "ZDSDAT", required = true)
        protected String zdsdat;
        @XmlElement(name = "ZDSTIM", required = true)
        protected String zdstim;
        @XmlElement(name = "ZDSUSER", required = true)
        protected String zdsuser;

        /**
         * Gets the value of the receiptnm property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRECEIPTNM() {
            return receiptnm;
        }

        /**
         * Sets the value of the receiptnm property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRECEIPTNM(String value) {
            this.receiptnm = value;
        }

        /**
         * Gets the value of the item property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getITEM() {
            return item;
        }

        /**
         * Sets the value of the item property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setITEM(String value) {
            this.item = value;
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
         * Gets the value of the issuenm property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getISSUENM() {
            return issuenm;
        }

        /**
         * Sets the value of the issuenm property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setISSUENM(String value) {
            this.issuenm = value;
        }

        /**
         * Gets the value of the issuenmitem property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getISSUENMITEM() {
            return issuenmitem;
        }

        /**
         * Sets the value of the issuenmitem property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setISSUENMITEM(String value) {
            this.issuenmitem = value;
        }

        /**
         * Gets the value of the vsnda property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getVSNDA() {
            return vsnda;
        }

        /**
         * Sets the value of the vsnda property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setVSNDA(String value) {
            this.vsnda = value;
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
         * Gets the value of the ktsch property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getKTSCH() {
            return ktsch;
        }

        /**
         * Sets the value of the ktsch property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setKTSCH(String value) {
            this.ktsch = value;
        }

        /**
         * Gets the value of the txz01 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getTXZ01() {
            return txz01;
        }

        /**
         * Sets the value of the txz01 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setTXZ01(String value) {
            this.txz01 = value;
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
         * Gets the value of the diecd property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDIECD() {
            return diecd;
        }

        /**
         * Sets the value of the diecd property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setDIECD(String value) {
            this.diecd = value;
        }

        /**
         * Gets the value of the sfflg property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSFFLG() {
            return sfflg;
        }

        /**
         * Sets the value of the sfflg property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSFFLG(String value) {
            this.sfflg = value;
        }

        /**
         * Gets the value of the menge property.
         *
         */
        public double getMENGE() {
            return menge;
        }

        /**
         * Sets the value of the menge property.
         *
         */
        public void setMENGE(double value) {
            this.menge = value;
        }

        /**
         * Gets the value of the matkl property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMATKL() {
            return matkl;
        }

        /**
         * Sets the value of the matkl property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMATKL(String value) {
            this.matkl = value;
        }

        /**
         * Gets the value of the ttreceipts property.
         *
         */
        public double getTTRECEIPTS() {
            return ttreceipts;
        }

        /**
         * Sets the value of the ttreceipts property.
         *
         */
        public void setTTRECEIPTS(double value) {
            this.ttreceipts = value;
        }

        /**
         * Gets the value of the zshnum property.
         *
         */
        public double getZSHNUM() {
            return zshnum;
        }

        /**
         * Sets the value of the zshnum property.
         *
         */
        public void setZSHNUM(double value) {
            this.zshnum = value;
        }

        /**
         * Gets the value of the zlfnum property.
         *
         */
        public double getZLFNUM() {
            return zlfnum;
        }

        /**
         * Sets the value of the zlfnum property.
         *
         */
        public void setZLFNUM(double value) {
            this.zlfnum = value;
        }

        /**
         * Gets the value of the zgfnum property.
         *
         */
        public double getZGFNUM() {
            return zgfnum;
        }

        /**
         * Sets the value of the zgfnum property.
         *
         */
        public void setZGFNUM(double value) {
            this.zgfnum = value;
        }

        /**
         * Gets the value of the zlost property.
         *
         */
        public double getZLOST() {
            return zlost;
        }

        /**
         * Sets the value of the zlost property.
         *
         */
        public void setZLOST(double value) {
            this.zlost = value;
        }

        /**
         * Gets the value of the zthnum property.
         *
         */
        public double getZTHNUM() {
            return zthnum;
        }

        /**
         * Sets the value of the zthnum property.
         *
         */
        public void setZTHNUM(double value) {
            this.zthnum = value;
        }

        /**
         * Gets the value of the gmein property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getGMEIN() {
            return gmein;
        }

        /**
         * Sets the value of the gmein property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setGMEIN(String value) {
            this.gmein = value;
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
         * Gets the value of the status property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSTATUS() {
            return status;
        }

        /**
         * Sets the value of the status property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSTATUS(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the mblnr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMBLNR() {
            return mblnr;
        }

        /**
         * Sets the value of the mblnr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMBLNR(String value) {
            this.mblnr = value;
        }

        /**
         * Gets the value of the zeile property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZEILE() {
            return zeile;
        }

        /**
         * Sets the value of the zeile property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZEILE(String value) {
            this.zeile = value;
        }

        /**
         * Gets the value of the mjahr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMJAHR() {
            return mjahr;
        }

        /**
         * Sets the value of the mjahr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMJAHR(String value) {
            this.mjahr = value;
        }

        /**
         * Gets the value of the rueck property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRUECK() {
            return rueck;
        }

        /**
         * Sets the value of the rueck property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRUECK(String value) {
            this.rueck = value;
        }

        /**
         * Gets the value of the rmzhl property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRMZHL() {
            return rmzhl;
        }

        /**
         * Sets the value of the rmzhl property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRMZHL(String value) {
            this.rmzhl = value;
        }

        /**
         * Gets the value of the deductntenm property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getDEDUCTNTENM() {
            return deductntenm;
        }

        /**
         * Sets the value of the deductntenm property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setDEDUCTNTENM(String value) {
            this.deductntenm = value;
        }

        /**
         * Gets the value of the zdsdat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDSDAT() {
            return zdsdat;
        }

        /**
         * Sets the value of the zdsdat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDSDAT(String value) {
            this.zdsdat = value;
        }

        /**
         * Gets the value of the zdstim property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDSTIM() {
            return zdstim;
        }

        /**
         * Sets the value of the zdstim property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDSTIM(String value) {
            this.zdstim = value;
        }

        /**
         * Gets the value of the zdsuser property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZDSUSER() {
            return zdsuser;
        }

        /**
         * Sets the value of the zdsuser property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZDSUSER(String value) {
            this.zdsuser = value;
        }

    }

}
