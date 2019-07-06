package yj.core.werbserver_crhd.receiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for DT_ZZ002_Res complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DT_ZZ002_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="E_MESSAGE" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="T_RETURN" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OBJID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ARBPL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VERWE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LVORM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AEDAT_GRND" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VERAN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="KTEXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ8" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ9" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZBZ10" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_ZZ002_Res", propOrder = { "emessage", "treturn" })
public class DTZZ002Res {

	@XmlElement(name = "E_MESSAGE")
	protected EMESSAGE emessage;
	@XmlElement(name = "T_RETURN")
	protected List<TRETURN> treturn;

	/**
	 * Gets the value of the emessage property.
	 * 
	 * @return possible object is {@link EMESSAGE }
	 * 
	 */
	public EMESSAGE getEMESSAGE() {
		return emessage;
	}

	/**
	 * Sets the value of the emessage property.
	 * 
	 * @param value
	 *            allowed object is {@link EMESSAGE }
	 * 
	 */
	public void setEMESSAGE(EMESSAGE value) {
		this.emessage = value;
	}

	/**
	 * Gets the value of the treturn property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the treturn property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTRETURN().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TRETURN }
	 * 
	 * 
	 */
	public List<TRETURN> getTRETURN() {
		if (treturn == null) {
			treturn = new ArrayList<TRETURN>();
		}
		return this.treturn;
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
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "type", "id", "number", "message" })
	public static class EMESSAGE {

		@XmlElement(name = "TYPE", required = true)
		protected String type;
		@XmlElement(name = "ID", required = true)
		protected String id;
		@XmlElement(name = "NUMBER", required = true)
		protected String number;
		@XmlElement(name = "MESSAGE", required = true)
		protected String message;

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
	 *         &lt;element name="OBJID" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ARBPL" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="VERWE" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="LVORM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="AEDAT_GRND" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="VERAN" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="KTEXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ1" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ2" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ3" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ4" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ5" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ6" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ7" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ8" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ9" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZBZ10" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "objid", "arbpl", "werks", "verwe",
			"lvorm", "aedatgrnd", "veran", "ktext", "zbz1", "zbz2", "zbz3",
			"zbz4", "zbz5", "zbz6", "zbz7", "zbz8", "zbz9", "zbz10" })
	public static class TRETURN {

		@XmlElement(name = "OBJID", required = true)
		protected String objid;
		@XmlElement(name = "ARBPL", required = true)
		protected String arbpl;
		@XmlElement(name = "WERKS", required = true)
		protected String werks;
		@XmlElement(name = "VERWE", required = true)
		protected String verwe;
		@XmlElement(name = "LVORM", required = true)
		protected String lvorm;
		@XmlElement(name = "AEDAT_GRND", required = true)
		protected String aedatgrnd;
		@XmlElement(name = "VERAN", required = true)
		protected String veran;
		@XmlElement(name = "KTEXT", required = true)
		protected String ktext;
		@XmlElement(name = "ZBZ1", required = true)
		protected String zbz1;
		@XmlElement(name = "ZBZ2", required = true)
		protected String zbz2;
		@XmlElement(name = "ZBZ3", required = true)
		protected String zbz3;
		@XmlElement(name = "ZBZ4", required = true)
		protected String zbz4;
		@XmlElement(name = "ZBZ5", required = true)
		protected String zbz5;
		@XmlElement(name = "ZBZ6", required = true)
		protected String zbz6;
		@XmlElement(name = "ZBZ7", required = true)
		protected String zbz7;
		@XmlElement(name = "ZBZ8", required = true)
		protected String zbz8;
		@XmlElement(name = "ZBZ9", required = true)
		protected String zbz9;
		@XmlElement(name = "ZBZ10", required = true)
		protected String zbz10;

		/**
		 * Gets the value of the objid property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getOBJID() {
			return objid;
		}

		/**
		 * Sets the value of the objid property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setOBJID(String value) {
			this.objid = value;
		}

		/**
		 * Gets the value of the arbpl property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getARBPL() {
			return arbpl;
		}

		/**
		 * Sets the value of the arbpl property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setARBPL(String value) {
			this.arbpl = value;
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
		 * Gets the value of the verwe property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getVERWE() {
			return verwe;
		}

		/**
		 * Sets the value of the verwe property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setVERWE(String value) {
			this.verwe = value;
		}

		/**
		 * Gets the value of the lvorm property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getLVORM() {
			return lvorm;
		}

		/**
		 * Sets the value of the lvorm property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setLVORM(String value) {
			this.lvorm = value;
		}

		/**
		 * Gets the value of the aedatgrnd property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAEDATGRND() {
			return aedatgrnd;
		}

		/**
		 * Sets the value of the aedatgrnd property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAEDATGRND(String value) {
			this.aedatgrnd = value;
		}

		/**
		 * Gets the value of the veran property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getVERAN() {
			return veran;
		}

		/**
		 * Sets the value of the veran property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setVERAN(String value) {
			this.veran = value;
		}

		/**
		 * Gets the value of the ktext property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getKTEXT() {
			return ktext;
		}

		/**
		 * Sets the value of the ktext property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setKTEXT(String value) {
			this.ktext = value;
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

		/**
		 * Gets the value of the zbz3 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ3() {
			return zbz3;
		}

		/**
		 * Sets the value of the zbz3 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ3(String value) {
			this.zbz3 = value;
		}

		/**
		 * Gets the value of the zbz4 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ4() {
			return zbz4;
		}

		/**
		 * Sets the value of the zbz4 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ4(String value) {
			this.zbz4 = value;
		}

		/**
		 * Gets the value of the zbz5 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ5() {
			return zbz5;
		}

		/**
		 * Sets the value of the zbz5 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ5(String value) {
			this.zbz5 = value;
		}

		/**
		 * Gets the value of the zbz6 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ6() {
			return zbz6;
		}

		/**
		 * Sets the value of the zbz6 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ6(String value) {
			this.zbz6 = value;
		}

		/**
		 * Gets the value of the zbz7 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ7() {
			return zbz7;
		}

		/**
		 * Sets the value of the zbz7 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ7(String value) {
			this.zbz7 = value;
		}

		/**
		 * Gets the value of the zbz8 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ8() {
			return zbz8;
		}

		/**
		 * Sets the value of the zbz8 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ8(String value) {
			this.zbz8 = value;
		}

		/**
		 * Gets the value of the zbz9 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ9() {
			return zbz9;
		}

		/**
		 * Sets the value of the zbz9 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ9(String value) {
			this.zbz9 = value;
		}

		/**
		 * Gets the value of the zbz10 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZBZ10() {
			return zbz10;
		}

		/**
		 * Sets the value of the zbz10 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZBZ10(String value) {
			this.zbz10 = value;
		}

	}

}
