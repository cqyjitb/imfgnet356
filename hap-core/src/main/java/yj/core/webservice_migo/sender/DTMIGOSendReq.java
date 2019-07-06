package yj.core.webservice_migo.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_MIGO_Send_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_MIGO_Send_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ACTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REFDOC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BLDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BKTXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BWART" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ERFMG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ERFME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TAKE_IT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SMBLN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SMBLP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SJAHR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UMLGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="UMCHA" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_MIGO_Send_Req", propOrder = { "item" })
public class DTMIGOSendReq {

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
     * {@link DTMIGOSendReq.ITEM }
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
     *         &lt;element name="ACTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="REFDOC" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BLDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BKTXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BWART" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CHARG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ERFMG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ERFME" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TAKE_IT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SMBLN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SMBLP" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SJAHR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UMLGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="UMCHA" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "action", "refdoc", "budat", "bldat",
            "bktxt", "bwart", "werks", "matnr", "lgort", "charg", "erfmg",
            "erfme", "aufnr", "rsnum", "rspos", "takeit", "smbln", "smblp",
            "sjahr", "umlgo", "umcha" })
    public static class ITEM {

        @XmlElement(name = "ACTION", required = true)
        protected String action;
        @XmlElement(name = "REFDOC", required = true)
        protected String refdoc;
        @XmlElement(name = "BUDAT", required = true)
        protected String budat;
        @XmlElement(name = "BLDAT", required = true)
        protected String bldat;
        @XmlElement(name = "BKTXT", required = true)
        protected String bktxt;
        @XmlElement(name = "BWART", required = true)
        protected String bwart;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "LGORT", required = true)
        protected String lgort;
        @XmlElement(name = "CHARG", required = true)
        protected String charg;
        @XmlElement(name = "ERFMG", required = true)
        protected String erfmg;
        @XmlElement(name = "ERFME", required = true)
        protected String erfme;
        @XmlElement(name = "AUFNR", required = true)
        protected String aufnr;
        @XmlElement(name = "RSNUM", required = true)
        protected String rsnum;
        @XmlElement(name = "RSPOS", required = true)
        protected String rspos;
        @XmlElement(name = "TAKE_IT", required = true)
        protected String takeit;
        @XmlElement(name = "SMBLN", required = true)
        protected String smbln;
        @XmlElement(name = "SMBLP", required = true)
        protected String smblp;
        @XmlElement(name = "SJAHR", required = true)
        protected String sjahr;
        @XmlElement(name = "UMLGO", required = true)
        protected String umlgo;
        @XmlElement(name = "UMCHA", required = true)
        protected String umcha;

        /**
         * Gets the value of the action property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getACTION() {
            return action;
        }

        /**
         * Sets the value of the action property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setACTION(String value) {
            this.action = value;
        }

        /**
         * Gets the value of the refdoc property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getREFDOC() {
            return refdoc;
        }

        /**
         * Sets the value of the refdoc property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setREFDOC(String value) {
            this.refdoc = value;
        }

        /**
         * Gets the value of the budat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBUDAT() {
            return budat;
        }

        /**
         * Sets the value of the budat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBUDAT(String value) {
            this.budat = value;
        }

        /**
         * Gets the value of the bldat property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBLDAT() {
            return bldat;
        }

        /**
         * Sets the value of the bldat property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBLDAT(String value) {
            this.bldat = value;
        }

        /**
         * Gets the value of the bktxt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBKTXT() {
            return bktxt;
        }

        /**
         * Sets the value of the bktxt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBKTXT(String value) {
            this.bktxt = value;
        }

        /**
         * Gets the value of the bwart property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBWART() {
            return bwart;
        }

        /**
         * Sets the value of the bwart property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBWART(String value) {
            this.bwart = value;
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
         * Gets the value of the erfmg property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getERFMG() {
            return erfmg;
        }

        /**
         * Sets the value of the erfmg property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setERFMG(String value) {
            this.erfmg = value;
        }

        /**
         * Gets the value of the erfme property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getERFME() {
            return erfme;
        }

        /**
         * Sets the value of the erfme property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setERFME(String value) {
            this.erfme = value;
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
         * Gets the value of the rsnum property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRSNUM() {
            return rsnum;
        }

        /**
         * Sets the value of the rsnum property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRSNUM(String value) {
            this.rsnum = value;
        }

        /**
         * Gets the value of the rspos property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRSPOS() {
            return rspos;
        }

        /**
         * Sets the value of the rspos property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setRSPOS(String value) {
            this.rspos = value;
        }

        /**
         * Gets the value of the takeit property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getTAKEIT() {
            return takeit;
        }

        /**
         * Sets the value of the takeit property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setTAKEIT(String value) {
            this.takeit = value;
        }

        /**
         * Gets the value of the smbln property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSMBLN() {
            return smbln;
        }

        /**
         * Sets the value of the smbln property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSMBLN(String value) {
            this.smbln = value;
        }

        /**
         * Gets the value of the smblp property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSMBLP() {
            return smblp;
        }

        /**
         * Sets the value of the smblp property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSMBLP(String value) {
            this.smblp = value;
        }

        /**
         * Gets the value of the sjahr property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSJAHR() {
            return sjahr;
        }

        /**
         * Sets the value of the sjahr property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSJAHR(String value) {
            this.sjahr = value;
        }

        /**
         * Gets the value of the umlgo property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getUMLGO() {
            return umlgo;
        }

        /**
         * Sets the value of the umlgo property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setUMLGO(String value) {
            this.umlgo = value;
        }

        /**
         * Gets the value of the umcha property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getUMCHA() {
            return umcha;
        }

        /**
         * Sets the value of the umcha property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setUMCHA(String value) {
            this.umcha = value;
        }

    }

}

