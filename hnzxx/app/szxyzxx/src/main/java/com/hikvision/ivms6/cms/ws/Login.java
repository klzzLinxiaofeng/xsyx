
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
 *         &lt;element name="loginAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientMac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "loginAccount",
    "password",
    "serviceIp",
    "clientIp",
    "clientMac"
})
@XmlRootElement(name = "login")
public class Login {

    @XmlElementRef(name = "loginAccount", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> loginAccount;
    @XmlElementRef(name = "password", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> password;
    @XmlElementRef(name = "serviceIp", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceIp;
    @XmlElementRef(name = "clientIp", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientIp;
    @XmlElementRef(name = "clientMac", namespace = "http://ws.cms.ivms6.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientMac;

    /**
     * Gets the value of the loginAccount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLoginAccount() {
        return loginAccount;
    }

    /**
     * Sets the value of the loginAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLoginAccount(JAXBElement<String> value) {
        this.loginAccount = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPassword(JAXBElement<String> value) {
        this.password = value;
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
     * Gets the value of the clientMac property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClientMac() {
        return clientMac;
    }

    /**
     * Sets the value of the clientMac property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClientMac(JAXBElement<String> value) {
        this.clientMac = value;
    }

}
