package yj.core.webservice_createtp.receiver;

/**
 * Created by 917110140 on 2018/9/20.
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_CREATP_Res complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_CREATP_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RETURN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZFLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_CREATP_Res", propOrder = { "_return" })
public class DTCREATPRes {

    @XmlElement(name = "RETURN", required = true)
    protected DTCREATPRes.RETURN _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link DTCREATPRes.RETURN }
     *
     */
    public DTCREATPRes.RETURN getRETURN() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *            allowed object is {@link DTCREATPRes.RETURN }
     *
     */
    public void setRETURN(DTCREATPRes.RETURN value) {
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
     *         &lt;element name="ZMESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZTPBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "zflag", "zmessage", "ztpbar" })
    public static class RETURN {

        @XmlElement(name = "ZFLAG", required = true)
        protected String zflag;
        @XmlElement(name = "ZMESSAGE", required = true)
        protected String zmessage;
        @XmlElement(name = "ZTPBAR", required = true)
        protected String ztpbar;

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
         * Gets the value of the zmessage property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZMESSAGE() {
            return zmessage;
        }

        /**
         * Sets the value of the zmessage property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZMESSAGE(String value) {
            this.zmessage = value;
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

    }

}

