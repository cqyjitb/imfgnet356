package yj.core.webservice_xhcard.receiver;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_XHCARD_SENDX_RES complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_XHCARD_SENDX_RES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RETURN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MSGTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGV1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGV2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGV4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_XHCARD_SENDX_RES", propOrder = { "_return" })
public class DTXHCARDSENDXRES {

    @XmlElement(name = "RETURN", required = true)
    protected RETURN _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link RETURN }
     *
     */
    public RETURN getRETURN() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *            allowed object is {@link RETURN }
     *
     */
    public void setRETURN(RETURN value) {
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
     *         &lt;element name="MSGTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MSGID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MSGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MSGV1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MSGV2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MSGV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MSGV4" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "msgty", "msgid", "msgno", "msgv1",
            "msgv2", "msgv3", "msgv4", "message" })
    public static class RETURN {

        @XmlElement(name = "MSGTY", required = true)
        protected String msgty;
        @XmlElement(name = "MSGID", required = true)
        protected String msgid;
        @XmlElement(name = "MSGNO", required = true)
        protected String msgno;
        @XmlElement(name = "MSGV1", required = true)
        protected String msgv1;
        @XmlElement(name = "MSGV2", required = true)
        protected String msgv2;
        @XmlElement(name = "MSGV3", required = true)
        protected String msgv3;
        @XmlElement(name = "MSGV4", required = true)
        protected String msgv4;
        @XmlElement(name = "MESSAGE", required = true)
        protected String message;

        /**
         * Gets the value of the msgty property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGTY() {
            return msgty;
        }

        /**
         * Sets the value of the msgty property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGTY(String value) {
            this.msgty = value;
        }

        /**
         * Gets the value of the msgid property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGID() {
            return msgid;
        }

        /**
         * Sets the value of the msgid property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGID(String value) {
            this.msgid = value;
        }

        /**
         * Gets the value of the msgno property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGNO() {
            return msgno;
        }

        /**
         * Sets the value of the msgno property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGNO(String value) {
            this.msgno = value;
        }

        /**
         * Gets the value of the msgv1 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGV1() {
            return msgv1;
        }

        /**
         * Sets the value of the msgv1 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGV1(String value) {
            this.msgv1 = value;
        }

        /**
         * Gets the value of the msgv2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGV2() {
            return msgv2;
        }

        /**
         * Sets the value of the msgv2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGV2(String value) {
            this.msgv2 = value;
        }

        /**
         * Gets the value of the msgv3 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGV3() {
            return msgv3;
        }

        /**
         * Sets the value of the msgv3 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGV3(String value) {
            this.msgv3 = value;
        }

        /**
         * Gets the value of the msgv4 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMSGV4() {
            return msgv4;
        }

        /**
         * Sets the value of the msgv4 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMSGV4(String value) {
            this.msgv4 = value;
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

    }

}

