package yj.core.webservice_queryoldzpgdbar.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DT_QUERYOLDZPGDBAR_Send_Req complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DT_QUERYOLDZPGDBAR_Send_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_QUERYOLDZPGDBAR_Send_Req", propOrder = { "item" })
public class DTQUERYOLDZPGDBARSendReq {

    @XmlElement(name = "ITEM", required = true)
    protected DTQUERYOLDZPGDBARSendReq.ITEM item;

    /**
     * Gets the value of the item property.
     *
     * @return possible object is {@link DTQUERYOLDZPGDBARSendReq.ITEM }
     *
     */
    public DTQUERYOLDZPGDBARSendReq.ITEM getITEM() {
        return item;
    }

    /**
     * Sets the value of the item property.
     *
     * @param value
     *            allowed object is {@link DTQUERYOLDZPGDBARSendReq.ITEM }
     *
     */
    public void setITEM(DTQUERYOLDZPGDBARSendReq.ITEM value) {
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
     *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "zpgdbar" })
    public static class ITEM {

        @XmlElement(name = "ZPGDBAR", required = true)
        protected String zpgdbar;

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

    }

}
