package yj.core.webservice.receiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * 生产订单报工返回
 * 
 * <p>
 * Java class for DT_PP001_Send_Res complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DT_PP001_Send_Res">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RETURN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MSGTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSDID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="DETAIL" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="FEVOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MAKT" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SPRAS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="AFVC" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="LTXA1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSGTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MSGID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DT_PP001_Send_Res", propOrder = { "_return", "detail" })
public class DTPP001SendRes {

	@XmlElement(name = "RETURN", required = true)
	protected RETURN _return;
	@XmlElement(name = "DETAIL")
	protected List<DETAIL> detail;

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
	 * Gets the value of the detail property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the detail property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getDETAIL().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link DETAIL }
	 * 
	 * 
	 */
	public List<DETAIL> getDETAIL() {
		if (detail == null) {
			detail = new ArrayList<DETAIL>();
		}
		return this.detail;
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
	 *         &lt;element name="AUFNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RSNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="RSPOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="FEVOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="TXT" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="MAKT" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="SPRAS" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="AFVC" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="LTXA1" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="MSGTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="MSGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="MSGID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
	@XmlType(name = "", propOrder = { "aufnr", "matnr", "rsnum", "rspos",
			"fevor", "txt", "makt", "afvc", "msgty", "msgno", "msgid", "msgv1",
			"msgv2", "msgv3", "msgv4", "message" })
	public static class DETAIL {

		@XmlElement(name = "AUFNR", required = true)
		protected String aufnr;
		@XmlElement(name = "MATNR", required = true)
		protected String matnr;
		@XmlElement(name = "RSNUM", required = true)
		protected String rsnum;
		@XmlElement(name = "RSPOS", required = true)
		protected String rspos;
		@XmlElement(name = "FEVOR", required = true)
		protected String fevor;
		@XmlElement(name = "TXT", required = true)
		protected String txt;
		@XmlElement(name = "MAKT")
		protected List<MAKT> makt;
		@XmlElement(name = "AFVC")
		protected List<AFVC> afvc;
		@XmlElement(name = "MSGTY", required = true)
		protected String msgty;
		@XmlElement(name = "MSGNO", required = true)
		protected String msgno;
		@XmlElement(name = "MSGID", required = true)
		protected String msgid;
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
		 * Gets the value of the fevor property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFEVOR() {
			return fevor;
		}

		/**
		 * Sets the value of the fevor property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setFEVOR(String value) {
			this.fevor = value;
		}

		/**
		 * Gets the value of the txt property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTXT() {
			return txt;
		}

		/**
		 * Sets the value of the txt property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setTXT(String value) {
			this.txt = value;
		}

		/**
		 * Gets the value of the makt property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the makt property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getMAKT().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link MAKT }
		 * 
		 * 
		 */
		public List<MAKT> getMAKT() {
			if (makt == null) {
				makt = new ArrayList<MAKT>();
			}
			return this.makt;
		}

		/**
		 * Gets the value of the afvc property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the afvc property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getAFVC().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link AFVC }
		 * 
		 * 
		 */
		public List<AFVC> getAFVC() {
			if (afvc == null) {
				afvc = new ArrayList<AFVC>();
			}
			return this.afvc;
		}

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

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="VORNR" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="LTXA1" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "vornr", "ltxa1" })
		public static class AFVC {

			@XmlElement(name = "VORNR", required = true)
			protected String vornr;
			@XmlElement(name = "LTXA1", required = true)
			protected String ltxa1;

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
			 * Gets the value of the ltxa1 property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getLTXA1() {
				return ltxa1;
			}

			/**
			 * Sets the value of the ltxa1 property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setLTXA1(String value) {
				this.ltxa1 = value;
			}

		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="SPRAS" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="MAKTX" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "spras", "maktx" })
		public static class MAKT {

			@XmlElement(name = "SPRAS", required = true)
			protected String spras;
			@XmlElement(name = "MAKTX", required = true)
			protected String maktx;

			/**
			 * Gets the value of the spras property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSPRAS() {
				return spras;
			}

			/**
			 * Sets the value of the spras property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setSPRAS(String value) {
				this.spras = value;
			}

			/**
			 * Gets the value of the maktx property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getMAKTX() {
				return maktx;
			}

			/**
			 * Sets the value of the maktx property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setMAKTX(String value) {
				this.maktx = value;
			}

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
	 *         &lt;element name="MSGTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="MSGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="MSDID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
	@XmlType(name = "", propOrder = { "msgty", "msgno", "msdid", "msgv1",
			"msgv2", "msgv3", "msgv4", "message" })
	public static class RETURN {

		@XmlElement(name = "MSGTY", required = true)
		protected String msgty;
		@XmlElement(name = "MSGNO", required = true)
		protected String msgno;
		@XmlElement(name = "MSDID", required = true)
		protected String msdid;
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
		 * Gets the value of the msdid property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getMSDID() {
			return msdid;
		}

		/**
		 * Sets the value of the msdid property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setMSDID(String value) {
			this.msdid = value;
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
