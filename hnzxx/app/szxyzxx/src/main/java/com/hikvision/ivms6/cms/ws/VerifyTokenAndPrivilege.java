
package com.hikvision.ivms6.cms.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="st" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="indexCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "st",
    "resType",
    "indexCode",
    "operCode"
})
@XmlRootElement(name = "verifyTokenAndPrivilege")
public class VerifyTokenAndPrivilege {

    @XmlElementRef(name = "st", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> st;
    protected Integer resType;
    @XmlElementRef(name = "indexCode", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> indexCode;
    @XmlElementRef(name = "operCode", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operCode;

    /**
     * Gets the value of the st property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSt() {
        return st;
    }

    /**
     * Sets the value of the st property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSt(JAXBElement<String> value) {
        this.st = value;
    }

    /**
     * Gets the value of the resType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getResType() {
        return resType;
    }

    /**
     * Sets the value of the resType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setResType(Integer value) {
        this.resType = value;
    }

    /**
     * Gets the value of the indexCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndexCode() {
        return indexCode;
    }

    /**
     * Sets the value of the indexCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndexCode(JAXBElement<String> value) {
        this.indexCode = value;
    }

    /**
     * Gets the value of the operCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperCode() {
        return operCode;
    }

    /**
     * Sets the value of the operCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperCode(JAXBElement<String> value) {
        this.operCode = value;
    }

}
