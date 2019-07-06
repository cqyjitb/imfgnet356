package yj.core.webservice_migo.receiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_MIGO_Send_Res complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_MIGO_Send_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MBLNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MJAHR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MTYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MTMSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RETURN" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOG_MSG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE_V1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE_V2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE_V3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE_V4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PARAMETER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ROW" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FIELD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SYSTEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_MIGO_Send_Res", propOrder = { "mblnr", "mjahr", "mtype",
        "mtmsg", "_return" })
public class DTMIGOSendRes {

    @XmlElement(name = "MBLNR", required = true)
    protected String mblnr;
    @XmlElement(name = "MJAHR", required = true)
    protected String mjahr;
    @XmlElement(name = "MTYPE", required = true)
    protected String mtype;
    @XmlElement(name = "MTMSG", required = true)
    protected String mtmsg;
    @XmlElement(name = "RETURN")
    protected List<RETURN> _return;

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
     * Gets the value of the return property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the return property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getRETURN().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTMIGOSendRes.RETURN }
     *
     *
     */
    public List<RETURN> getRETURN() {
        if (_return == null) {
            _return = new ArrayList<RETURN>();
        }
        return this._return;
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
     *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOG_MSG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE_V1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE_V2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE_V3" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MESSAGE_V4" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PARAMETER" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ROW" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="FIELD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SYSTEM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "type", "id", "number", "message",
            "logno", "logmsgno", "messagev1", "messagev2", "messagev3",
            "messagev4", "parameter", "row", "field", "system" })
    public static class RETURN {

        @XmlElement(name = "TYPE", required = true)
        protected String type;
        @XmlElement(name = "ID", required = true)
        protected String id;
        @XmlElement(name = "NUMBER", required = true)
        protected String number;
        @XmlElement(name = "MESSAGE", required = true)
        protected String message;
        @XmlElement(name = "LOG_NO", required = true)
        protected String logno;
        @XmlElement(name = "LOG_MSG_NO", required = true)
        protected String logmsgno;
        @XmlElement(name = "MESSAGE_V1", required = true)
        protected String messagev1;
        @XmlElement(name = "MESSAGE_V2", required = true)
        protected String messagev2;
        @XmlElement(name = "MESSAGE_V3", required = true)
        protected String messagev3;
        @XmlElement(name = "MESSAGE_V4", required = true)
        protected String messagev4;
        @XmlElement(name = "PARAMETER", required = true)
        protected String parameter;
        @XmlElement(name = "ROW", required = true)
        protected String row;
        @XmlElement(name = "FIELD", required = true)
        protected String field;
        @XmlElement(name = "SYSTEM", required = true)
        protected String system;

        /**
         * Gets the value of the type property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getTYPE() {
            return type;
        }

        /**
         * Sets the value of the type property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setTYPE(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the id property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getID() {
            return id;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setID(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the number property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getNUMBER() {
            return number;
        }

        /**
         * Sets the value of the number property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setNUMBER(String value) {
            this.number = value;
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
         * Gets the value of the logno property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLOGNO() {
            return logno;
        }

        /**
         * Sets the value of the logno property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setLOGNO(String value) {
            this.logno = value;
        }

        /**
         * Gets the value of the logmsgno property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLOGMSGNO() {
            return logmsgno;
        }

        /**
         * Sets the value of the logmsgno property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setLOGMSGNO(String value) {
            this.logmsgno = value;
        }

        /**
         * Gets the value of the messagev1 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMESSAGEV1() {
            return messagev1;
        }

        /**
         * Sets the value of the messagev1 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMESSAGEV1(String value) {
            this.messagev1 = value;
        }

        /**
         * Gets the value of the messagev2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMESSAGEV2() {
            return messagev2;
        }

        /**
         * Sets the value of the messagev2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMESSAGEV2(String value) {
            this.messagev2 = value;
        }

        /**
         * Gets the value of the messagev3 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMESSAGEV3() {
            return messagev3;
        }

        /**
         * Sets the value of the messagev3 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMESSAGEV3(String value) {
            this.messagev3 = value;
        }

        /**
         * Gets the value of the messagev4 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getMESSAGEV4() {
            return messagev4;
        }

        /**
         * Sets the value of the messagev4 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setMESSAGEV4(String value) {
            this.messagev4 = value;
        }

        /**
         * Gets the value of the parameter property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getPARAMETER() {
            return parameter;
        }

        /**
         * Sets the value of the parameter property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setPARAMETER(String value) {
            this.parameter = value;
        }

        /**
         * Gets the value of the row property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getROW() {
            return row;
        }

        /**
         * Sets the value of the row property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setROW(String value) {
            this.row = value;
        }

        /**
         * Gets the value of the field property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getFIELD() {
            return field;
        }

        /**
         * Sets the value of the field property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setFIELD(String value) {
            this.field = value;
        }

        /**
         * Gets the value of the system property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSYSTEM() {
            return system;
        }

        /**
         * Sets the value of the system property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSYSTEM(String value) {
            this.system = value;
        }

    }

}

