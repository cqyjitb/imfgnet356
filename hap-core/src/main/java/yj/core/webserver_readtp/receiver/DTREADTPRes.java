package yj.core.webserver_readtp.receiver;

/**
 * Created by 917110140 on 2018/10/6.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_READTP_Res complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_READTP_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RETURN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPZT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPZT2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPWZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_READTP_Res", propOrder = { "_return" })
public class DTREADTPRes {

    @XmlElement(name = "RETURN", required = true)
    protected DTREADTPRes.RETURN _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link DTREADTPRes.RETURN }
     *
     */
    public DTREADTPRes.RETURN getRETURN() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *            allowed object is {@link DTREADTPRes.RETURN }
     *
     */
    public void setRETURN(DTREADTPRes.RETURN value) {
        this._return = value;
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
     *         &lt;element name="ZFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPZT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPZT2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MENGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPWZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "zflag", "message", "ztpzt", "ztpzt2",
            "lgort", "menge", "ztpwz", "ztpbar", "matnr" })
    public static class RETURN {

        @XmlElement(name = "ZFLAG", required = true)
        protected String zflag;
        @XmlElement(name = "MESSAGE", required = true)
        protected String message;
        @XmlElement(name = "ZTPZT", required = true)
        protected String ztpzt;
        @XmlElement(name = "ZTPZT2", required = true)
        protected String ztpzt2;
        @XmlElement(name = "LGORT", required = true)
        protected String lgort;
        @XmlElement(name = "MENGE", required = true)
        protected String menge;
        @XmlElement(name = "ZTPWZ", required = true)
        protected String ztpwz;
        @XmlElement(name = "ZTPBAR", required = true)
        protected String ztpbar;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;

        /**
         * Gets the value of the zflag property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZFLAG() {
            return zflag;
        }

        /**
         * Sets the value of the zflag property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZFLAG(String value) {
            this.zflag = value;
        }

        /**
         * Gets the value of the message property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMESSAGE() {
            return message;
        }

        /**
         * Sets the value of the message property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMESSAGE(String value) {
            this.message = value;
        }

        /**
         * Gets the value of the ztpzt property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPZT() {
            return ztpzt;
        }

        /**
         * Sets the value of the ztpzt property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPZT(String value) {
            this.ztpzt = value;
        }

        /**
         * Gets the value of the ztpzt2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPZT2() {
            return ztpzt2;
        }

        /**
         * Sets the value of the ztpzt2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPZT2(String value) {
            this.ztpzt2 = value;
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
         * Gets the value of the ztpwz property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZTPWZ() {
            return ztpwz;
        }

        /**
         * Sets the value of the ztpwz property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZTPWZ(String value) {
            this.ztpwz = value;
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

    }

}
