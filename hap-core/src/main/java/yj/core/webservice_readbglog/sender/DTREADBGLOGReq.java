package yj.core.webservice_readbglog.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_READBGLOG_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_READBGLOG_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="I_DATA" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FILED" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="SIGN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="OPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LOW" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="HIGH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_READBGLOG_Req", propOrder = { "reverse", "idata" })
public class DTREADBGLOGReq {

    @XmlElement(name = "REVERSE", required = true)
    protected String reverse;
    @XmlElement(name = "I_DATA", required = true)
    protected List<IDATA> idata;

    /**
     * Gets the value of the reverse property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getREVERSE() {
        return reverse;
    }

    /**
     * Sets the value of the reverse property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setREVERSE(String value) {
        this.reverse = value;
    }

    /**
     * Gets the value of the idata property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the idata property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getIDATA().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTREADBGLOGReq.IDATA }
     *
     *
     */
    public List<IDATA> getIDATA() {
        if (idata == null) {
            idata = new ArrayList<IDATA>();
        }
        return this.idata;
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
     *         &lt;element name="FILED" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="SIGN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="OPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LOW" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="HIGH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "filed", "sign", "option", "low", "high",
            "zbz1", "zbz2" })
    public static class IDATA {

        @XmlElement(name = "FILED", required = true)
        protected String filed;
        @XmlElement(name = "SIGN", required = true)
        protected String sign;
        @XmlElement(name = "OPTION", required = true)
        protected String option;
        @XmlElement(name = "LOW", required = true)
        protected String low;
        @XmlElement(name = "HIGH", required = true)
        protected String high;
        @XmlElement(name = "ZBZ1", required = true)
        protected String zbz1;
        @XmlElement(name = "ZBZ2", required = true)
        protected String zbz2;

        /**
         * Gets the value of the filed property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getFILED() {
            return filed;
        }

        /**
         * Sets the value of the filed property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setFILED(String value) {
            this.filed = value;
        }

        /**
         * Gets the value of the sign property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getSIGN() {
            return sign;
        }

        /**
         * Sets the value of the sign property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setSIGN(String value) {
            this.sign = value;
        }

        /**
         * Gets the value of the option property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getOPTION() {
            return option;
        }

        /**
         * Sets the value of the option property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setOPTION(String value) {
            this.option = value;
        }

        /**
         * Gets the value of the low property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getLOW() {
            return low;
        }

        /**
         * Sets the value of the low property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setLOW(String value) {
            this.low = value;
        }

        /**
         * Gets the value of the high property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getHIGH() {
            return high;
        }

        /**
         * Sets the value of the high property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setHIGH(String value) {
            this.high = value;
        }

        /**
         * Gets the value of the zbz1 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ1() {
            return zbz1;
        }

        /**
         * Sets the value of the zbz1 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ1(String value) {
            this.zbz1 = value;
        }

        /**
         * Gets the value of the zbz2 property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getZBZ2() {
            return zbz2;
        }

        /**
         * Sets the value of the zbz2 property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setZBZ2(String value) {
            this.zbz2 = value;
        }

    }

}

