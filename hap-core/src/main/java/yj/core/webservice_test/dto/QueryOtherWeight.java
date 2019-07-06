package yj.core.webservice_test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for queryOtherWeight complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="queryOtherWeight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZSBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryOtherWeight", propOrder = { "zsbeln" })
public class QueryOtherWeight {

    @XmlElement(name = "ZSBELN")
    protected String zsbeln;

    /**
     * Gets the value of the zsbeln property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getZSBELN() {
        return zsbeln;
    }

    /**
     * Sets the value of the zsbeln property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setZSBELN(String value) {
        this.zsbeln = value;
    }

}

