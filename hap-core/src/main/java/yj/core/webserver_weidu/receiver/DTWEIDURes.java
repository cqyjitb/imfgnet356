package yj.core.webserver_weidu.receiver;

/**
 * Created by 917110140 on 2018/9/29.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_WEIDU_Res complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_WEIDU_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MTMSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WEIDUFLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="T_ZTPP0012" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CONNAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BEIZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="T_ZTPP0013" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_WEIDU_Res", propOrder = { "mtype", "mtmsg", "weiduflg",
        "tztpp0012", "tztpp0013" })
public class DTWEIDURes {

    @XmlElement(name = "MTYPE", required = true)
    protected String mtype;
    @XmlElement(name = "MTMSG", required = true)
    protected String mtmsg;
    @XmlElement(name = "WEIDUFLG", required = true)
    protected String weiduflg;
    @XmlElement(name = "T_ZTPP0012")
    protected List<TZTPP0012> tztpp0012;
    @XmlElement(name = "T_ZTPP0013")
    protected List<TZTPP0013> tztpp0013;

    /**
     * Gets the value of the mtype property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMTYPE() {
        return mtype;
    }

    /**
     * Sets the value of the mtype property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMTYPE(String value) {
        this.mtype = value;
    }

    /**
     * Gets the value of the mtmsg property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMTMSG() {
        return mtmsg;
    }

    /**
     * Sets the value of the mtmsg property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMTMSG(String value) {
        this.mtmsg = value;
    }

    /**
     * Gets the value of the weiduflg property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getWEIDUFLG() {
        return weiduflg;
    }

    /**
     * Sets the value of the weiduflg property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setWEIDUFLG(String value) {
        this.weiduflg = value;
    }

    /**
     * Gets the value of the tztpp0012 property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the tztpp0012 property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getTZTPP0012().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTWEIDURes.TZTPP0012 }
     *
     *
     */
    public List<TZTPP0012> getTZTPP0012() {
        if (tztpp0012 == null) {
            tztpp0012 = new ArrayList<TZTPP0012>();
        }
        return this.tztpp0012;
    }

    /**
     * Gets the value of the tztpp0013 property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the tztpp0013 property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getTZTPP0013().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTWEIDURes.TZTPP0013 }
     *
     *
     */
    public List<TZTPP0013> getTZTPP0013() {
        if (tztpp0013 == null) {
            tztpp0013 = new ArrayList<TZTPP0013>();
        }
        return this.tztpp0013;
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
     *         &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MEINS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CONNAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BEIZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "werks", "matnr", "zbanb", "zmodel",
            "maktx", "meins", "status", "statxt", "connam", "beiz" })
    public static class TZTPP0012 {

        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "ZBANB", required = true)
        protected String zbanb;
        @XmlElement(name = "ZMODEL", required = true)
        protected String zmodel;
        @XmlElement(name = "MAKTX", required = true)
        protected String maktx;
        @XmlElement(name = "MEINS", required = true)
        protected String meins;
        @XmlElement(name = "STATUS", required = true)
        protected String status;
        @XmlElement(name = "STATXT", required = true)
        protected String statxt;
        @XmlElement(name = "CONNAM", required = true)
        protected String connam;
        @XmlElement(name = "BEIZ", required = true)
        protected String beiz;

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
         * Gets the value of the zbanb property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBANB() {
            return zbanb;
        }

        /**
         * Sets the value of the zbanb property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBANB(String value) {
            this.zbanb = value;
        }

        /**
         * Gets the value of the zmodel property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMODEL() {
            return zmodel;
        }

        /**
         * Sets the value of the zmodel property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMODEL(String value) {
            this.zmodel = value;
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
         * Gets the value of the statxt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSTATXT() {
            return statxt;
        }

        /**
         * Sets the value of the statxt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSTATXT(String value) {
            this.statxt = value;
        }

        /**
         * Gets the value of the connam property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCONNAM() {
            return connam;
        }

        /**
         * Sets the value of the connam property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCONNAM(String value) {
            this.connam = value;
        }

        /**
         * Gets the value of the beiz property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getBEIZ() {
            return beiz;
        }

        /**
         * Sets the value of the beiz property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setBEIZ(String value) {
            this.beiz = value;
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
     *         &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "zxhbar", "zbanb", "werks", "matnr",
            "zmodel" })
    public static class TZTPP0013 {

        @XmlElement(name = "ZXHBAR", required = true)
        protected String zxhbar;
        @XmlElement(name = "ZBANB", required = true)
        protected String zbanb;
        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "ZMODEL", required = true)
        protected String zmodel;

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
         * Gets the value of the zbanb property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBANB() {
            return zbanb;
        }

        /**
         * Sets the value of the zbanb property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBANB(String value) {
            this.zbanb = value;
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
         * Gets the value of the zmodel property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMODEL() {
            return zmodel;
        }

        /**
         * Sets the value of the zmodel property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMODEL(String value) {
            this.zmodel = value;
        }

    }

}
