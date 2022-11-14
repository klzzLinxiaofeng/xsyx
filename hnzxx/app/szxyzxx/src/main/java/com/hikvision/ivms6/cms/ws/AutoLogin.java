
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
 *         &lt;element name="autoLoginSession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientmac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "autoLoginSession",
    "clientmac",
    "clientIp",
    "serviceIp"
})
@XmlRootElement(name = "autoLogin")
public class AutoLogin {

    @XmlElementRef(name = "autoLoginSession", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> autoLoginSession;
    @XmlElementRef(name = "clientmac", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientmac;
    @XmlElementRef(name = "clientIp", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientIp;
    @XmlElementRef(name = "serviceIp", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceIp;

    /**
     * Gets the value of the autoLoginSession property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAutoLoginSession() {
        return autoLoginSession;
    }

    /**
     * Sets the value of the autoLoginSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAutoLoginSession(JAXBElement<String> value) {
        this.autoLoginSession = value;
    }

    /**
     * Gets the value of the clientmac property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClientmac() {
        return clientmac;
    }

    /**
     * Sets the value of the clientmac property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClientmac(JAXBElement<String> value) {
        this.clientmac = value;
    }

    /**
     * Gets the value of the clientIp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClientIp() {
        return clientIp;
    }

    /**
     * Sets the value of the clientIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClientIp(JAXBElement<String> value) {
        this.clientIp = value;
    }

    /**
     * Gets the value of the serviceIp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceIp() {
        return serviceIp;
    }

    /**
     * Sets the value of the serviceIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceIp(JAXBElement<String> value) {
        this.serviceIp = value;
    }

}
