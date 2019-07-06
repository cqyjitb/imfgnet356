package yj.core.webservice_test.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for oReturnData complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="oReturnData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mtmsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oReturnData", propOrder = { "mtmsg", "mtype" })
public class OReturnData {

    protected String mtmsg;
    protected String mtype;

    /**
     * Gets the value of the mtmsg property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMtmsg() {
        return mtmsg;
    }

    /**
     * Sets the value of the mtmsg property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMtmsg(String value) {
        this.mtmsg = value;
    }

    /**
     * Gets the value of the mtype property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMtype() {
        return mtype;
    }

    /**
     * Sets the value of the mtype property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMtype(String value) {
        this.mtype = value;
    }

}
