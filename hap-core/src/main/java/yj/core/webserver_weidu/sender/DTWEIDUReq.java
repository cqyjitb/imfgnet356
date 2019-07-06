package yj.core.webserver_weidu.sender;

/**
 * Created by 917110140 on 2018/9/29.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_WEIDU_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_WEIDU_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBANB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMODEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_WEIDU_Req", propOrder = { "item" })
public class DTWEIDUReq {

    @XmlElement(name = "ITEM", required = true)
    protected DTWEIDUReq.ITEM item;

    /**
     * Gets the value of the item property.
     *
     * @return possible object is {@link DTWEIDUReq.ITEM }
     *
     */
    public DTWEIDUReq.ITEM getITEM() {
        return item;
    }

    /**
     * Sets the value of the item property.
     *
     * @param value
     *            allowed object is {@link DTWEIDUReq.ITEM }
     *
     */
    public void setITEM(DTWEIDUReq.ITEM value) {
        this.item = value;
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
     *         &lt;element name="ZXHBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "zxhbar" })
    public static class ITEM {

        @XmlElement(name = "WERKS", required = true)
        protected String werks;
        @XmlElement(name = "MATNR", required = true)
        protected String matnr;
        @XmlElement(name = "ZBANB", required = true)
        protected String zbanb;
        @XmlElement(name = "ZMODEL", required = true)
        protected String zmodel;
        @XmlElement(name = "ZXHBAR", required = true)
        protected String zxhbar;

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

    }

}

