package yj.core.webservice.sender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * 报工接收数据结构
 * 
 * <p>
 * Java class for DT_PP001_Send_Req complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DT_PP001_Send_Req">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PWERK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="XMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_PP001_Send_Req", propOrder = { "item" })
public class DTPP001SendReq {

	@XmlElement(name = "ITEM", required = true)
	protected List<ITEM> item;

	/**
	 * Gets the value of the item property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the item property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getITEM().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ITEM }
	 * 
	 * 
	 */
	public List<ITEM> getITEM() {
		if (item == null) {
			item = new ArrayList<ITEM>();
		}
		return this.item;
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
	 *         &lt;element name="PWERK" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="BUDAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="GMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="XMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RMNGA" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZSCBC" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZSCX" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZMNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="DATUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZPGDBAR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="ZPGDBH" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="REVERSE" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "pwerk", "aufnr", "vornr", "budat",
			"gmnga", "xmnga", "rmnga", "zscbc", "zscx", "zmnum", "datum",
			"zpgdbar", "zpgdbh", "rsnum", "rspos", "reverse", "attr1", "attr2", "attr3", "attr4", "attr5", "attr6", "attr7", "userName" })
	public static class ITEM {

		@XmlElement(name = "PWERK", required = true)
		protected String pwerk;
		@XmlElement(name = "AUFNR", required = true)
		protected String aufnr;
		@XmlElement(name = "VORNR", required = true)
		protected String vornr;
		@XmlElement(name = "BUDAT", required = true)
		protected String budat;
		@XmlElement(name = "GMNGA", required = true)
		protected String gmnga;
		@XmlElement(name = "XMNGA", required = true)
		protected String xmnga;
		@XmlElement(name = "RMNGA", required = true)
		protected String rmnga;
		@XmlElement(name = "ZSCBC", required = true)
		protected String zscbc;
		@XmlElement(name = "ZSCX", required = true)
		protected String zscx;
		@XmlElement(name = "ZMNUM", required = true)
		protected String zmnum;
		@XmlElement(name = "DATUM", required = true)
		protected String datum;
		@XmlElement(name = "ZPGDBAR", required = true)
		protected String zpgdbar;
		@XmlElement(name = "ZPGDBH", required = true)
		protected String zpgdbh;
		@XmlElement(name = "RSNUM", required = true)
		protected String rsnum;
		@XmlElement(name = "RSPOS", required = true)
		protected String rspos;
		@XmlElement(name = "REVERSE", required = true)
		protected String reverse;
		@XmlElement(name="ATTR1", required=true)
		protected String attr1;
		@XmlElement(name="ATTR2", required=true)
		protected String attr2;
		@XmlElement(name="ATTR3", required=true)
		protected String attr3;
		@XmlElement(name="ATTR4", required=true)
		protected String attr4;
		@XmlElement(name="ATTR5", required=true)
		protected String attr5;
		@XmlElement(name="ATTR6", required=true)
		protected String attr6;
		@XmlElement(name="ATTR7", required=true)
		protected String attr7;
		@XmlElement(name="USERNAME", required=true)
		protected String userName;

		/**
		 * Gets the value of the pwerk property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPWERK() {
			return pwerk;
		}

		/**
		 * Sets the value of the pwerk property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setPWERK(String value) {
			this.pwerk = value;
		}

		/**
		 * Gets the value of the aufnr property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAUFNR() {
			return aufnr;
		}

		/**
		 * Sets the value of the aufnr property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAUFNR(String value) {
			this.aufnr = value;
		}

		/**
		 * Gets the value of the vornr property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getVORNR() {
			return vornr;
		}

		/**
		 * Sets the value of the vornr property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setVORNR(String value) {
			this.vornr = value;
		}

		/**
		 * Gets the value of the budat property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getBUDAT() {
			return budat;
		}

		/**
		 * Sets the value of the budat property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setBUDAT(String value) {
			this.budat = value;
		}

		/**
		 * Gets the value of the gmnga property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGMNGA() {
			return gmnga;
		}

		/**
		 * Sets the value of the gmnga property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGMNGA(String value) {
			this.gmnga = value;
		}

		/**
		 * Gets the value of the xmnga property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getXMNGA() {
			return xmnga;
		}

		/**
		 * Sets the value of the xmnga property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setXMNGA(String value) {
			this.xmnga = value;
		}

		/**
		 * Gets the value of the rmnga property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRMNGA() {
			return rmnga;
		}

		/**
		 * Sets the value of the rmnga property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setRMNGA(String value) {
			this.rmnga = value;
		}

		/**
		 * Gets the value of the zscbc property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZSCBC() {
			return zscbc;
		}

		/**
		 * Sets the value of the zscbc property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZSCBC(String value) {
			this.zscbc = value;
		}

		/**
		 * Gets the value of the zscx property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZSCX() {
			return zscx;
		}

		/**
		 * Sets the value of the zscx property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZSCX(String value) {
			this.zscx = value;
		}

		/**
		 * Gets the value of the zmnum property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZMNUM() {
			return zmnum;
		}

		/**
		 * Sets the value of the zmnum property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZMNUM(String value) {
			this.zmnum = value;
		}

		/**
		 * Gets the value of the datum property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getDATUM() {
			return datum;
		}

		/**
		 * Sets the value of the datum property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setDATUM(String value) {
			this.datum = value;
		}

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

		/**
		 * Gets the value of the zpgdbh property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZPGDBH() {
			return zpgdbh;
		}

		/**
		 * Sets the value of the zpgdbh property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZPGDBH(String value) {
			this.zpgdbh = value;
		}

		/**
		 * Gets the value of the rsnum property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRSNUM() {
			return rsnum;
		}

		/**
		 * Sets the value of the rsnum property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setRSNUM(String value) {
			this.rsnum = value;
		}

		/**
		 * Gets the value of the rspos property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRSPOS() {
			return rspos;
		}

		/**
		 * Sets the value of the rspos property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setRSPOS(String value) {
			this.rspos = value;
		}

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

		public String getAttr1()
		{
			return this.attr1;
		}

		public String getAttr2()
		{
			return this.attr2;
		}

		public String getAttr3()
		{
			return this.attr3;
		}

		public String getAttr4()
		{
			return this.attr4;
		}

		public String getAttr5()
		{
			return this.attr5;
		}

		public String getAttr6()
		{
			return this.attr6;
		}

		public String getAttr7()
		{
			return this.attr7;
		}

		public String getUserName()
		{
			return this.userName;
		}

		public void setAttr1(String attr1)
		{
			this.attr1 = attr1;
		}

		public void setAttr2(String attr2)
		{
			this.attr2 = attr2;
		}

		public void setAttr3(String attr3)
		{
			this.attr3 = attr3;
		}

		public void setAttr4(String attr4)
		{
			this.attr4 = attr4;
		}

		public void setAttr5(String attr5)
		{
			this.attr5 = attr5;
		}

		public void setAttr6(String attr6)
		{
			this.attr6 = attr6;
		}

		public void setAttr7(String attr7)
		{
			this.attr7 = attr7;
		}

		public void setUserName(String userName)
		{
			this.userName = userName;
		}

	}

}
