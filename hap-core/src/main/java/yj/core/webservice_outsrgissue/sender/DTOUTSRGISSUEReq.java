package yj.core.webservice_outsrgissue.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_OUTSRGISSUE_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_OUTSRGISSUE_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HEAD" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ISSUENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PRTFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TXZ01" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *                   &lt;element name="ISSUENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VSNDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EBELN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="EBELP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="KTSCH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TXZ01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ETENR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DIECD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SFFLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZISNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="GMEIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZISDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZISTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZISUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_OUTSRGISSUE_Req", propOrder = { "head", "item" })
public class DTOUTSRGISSUEReq {

    @XmlElement(name = "HEAD")
    protected DTOUTSRGISSUEReq.HEAD head;
    @XmlElement(name = "ITEM", required = true)
    protected List<ITEM> item;

    /**
     * Gets the value of the head property.
     *
     * @return possible object is {@link DTOUTSRGISSUEReq.HEAD }
     *
     */
    public DTOUTSRGISSUEReq.HEAD getHEAD() {
        return head;
    }

    /**
     * Sets the value of the head property.
     *
     * @param value
     *            allowed object is {@link DTOUTSRGISSUEReq.HEAD }
     *
     */
    public void setHEAD(DTOUTSRGISSUEReq.HEAD value) {
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
     * {@link DTOUTSRGISSUEReq.ITEM }
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
     *         &lt;element name="ISSUENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PRTFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TXZ01" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    @XmlType(name = "", propOrder = { "issuenm", "werks", "lifnr", "matnr",
            "status", "prtflag", "txz01", "zipdat", "ziptim", "zipuser" })
    public static class HEAD {

        @XmlElement(name = "ISSUENM", required = true)
        protected String issuenm;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "LIFNR", required = true)
        protected String lifnr;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "PRTFLAG", required = true)
        protected String prtflag;
        @XmlElement(name = "TXZ01", required = true)
        protected String txz01;
        @XmlElement(name = "ZIPDAT", required = true)
        protected String zipdat;
        @XmlElement(name = "ZIPTIM", required = true)
        protected String ziptim;
        @XmlElement(name = "ZIPUSER", required = true)
        protected String zipuser;

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
     *         &lt;element name="ISSUENM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ITEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VSNDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="EBELN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="EBELP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="KTSCH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TXZ01" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ETENR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LIFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DIECD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SFFLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="MATKL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZISNUM" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="GMEIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZISDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZISTIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZISUSER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "issuenm", "item", "werks", "zpgdbar",
            "vornr", "vsnda", "ebeln", "ebelp", "ktsch", "txz01", "etenr",
            "lifnr", "matnr", "diecd", "sfflg", "menge", "matkl", "zisnum",
            "gmein", "charg", "status", "zisdat", "zistim", "zisuser" })
    public static class ITEM {

        @XmlElement(name = "ISSUENM", required = true)
        protected String issuenm;
        @XmlElement(name = "ITEM", required = true)
        protected String item;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "ZPGDBAR", required = true)
        protected String zpgdbar;
        @XmlElement(name = "VORNR", required = true)
        protected String vornr;
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
        @XmlElement(name = "ETENR", required = true)
        protected String etenr;
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
        @XmlElement(name = "ZISNUM")
        protected double zisnum;
        @XmlElement(name = "GMEIN", required = true)
        protected String gmein;
        @XmlElement(name = "CHARG", required = true)
        protected String charg;
        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "ZISDAT", required = true)
        protected String zisdat;
        @XmlElement(name = "ZISTIM", required = true)
        protected String zistim;
        @XmlElement(name = "ZISUSER", required = true)
        protected String zisuser;

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
         * Gets the value of the etenr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getETENR() {
            return etenr;
        }

        /**
         * Sets the value of the etenr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setETENR(String value) {
            this.etenr = value;
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
         * Gets the value of the zisnum property.
         *
         */
        public double getZISNUM() {
            return zisnum;
        }

        /**
         * Sets the value of the zisnum property.
         *
         */
        public void setZISNUM(double value) {
            this.zisnum = value;
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
         * Gets the value of the zisdat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZISDAT() {
            return zisdat;
        }

        /**
         * Sets the value of the zisdat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZISDAT(String value) {
            this.zisdat = value;
        }

        /**
         * Gets the value of the zistim property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZISTIM() {
            return zistim;
        }

        /**
         * Sets the value of the zistim property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZISTIM(String value) {
            this.zistim = value;
        }

        /**
         * Gets the value of the zisuser property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZISUSER() {
            return zisuser;
        }

        /**
         * Sets the value of the zisuser property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZISUSER(String value) {
            this.zisuser = value;
        }

    }

}
