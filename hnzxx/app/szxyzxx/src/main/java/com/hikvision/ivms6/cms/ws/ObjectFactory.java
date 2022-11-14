
package com.hikvision.ivms6.cms.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hikvision.ivms6.cms.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AutoLoginAutoLoginSession_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "autoLoginSession");
    private final static QName _AutoLoginClientIp_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "clientIp");
    private final static QName _AutoLoginClientmac_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "clientmac");
    private final static QName _AutoLoginServiceIp_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "serviceIp");
    private final static QName _VerifyTokenAndPrivilegeOperCode_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "operCode");
    private final static QName _VerifyTokenAndPrivilegeSt_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "st");
    private final static QName _VerifyTokenAndPrivilegeIndexCode_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "indexCode");
    private final static QName _LoginResponseReturn_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "return");
    private final static QName _VerifyServicePrivilegeIp_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "ip");
    private final static QName _LoginClientMac_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "clientMac");
    private final static QName _LoginLoginAccount_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "loginAccount");
    private final static QName _LoginPassword_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "password");
    private final static QName _ApplyTokenTgt_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "tgt");
    private final static QName _ApplyTokenByUrlUrl_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "url");
    private final static QName _LogoutTgc_QNAME = new QName("http://ws.cms.ivms6.hikvision.com", "tgc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hikvision.ivms6.cms.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplyTokenByUrlResponse }
     * 
     */
    public ApplyTokenByUrlResponse createApplyTokenByUrlResponse() {
        return new ApplyTokenByUrlResponse();
    }

    /**
     * Create an instance of {@link ApplyAutoLoginResponse }
     * 
     */
    public ApplyAutoLoginResponse createApplyAutoLoginResponse() {
        return new ApplyAutoLoginResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link ApplyTokenResponse }
     * 
     */
    public ApplyTokenResponse createApplyTokenResponse() {
        return new ApplyTokenResponse();
    }

    /**
     * Create an instance of {@link VerifyServicePrivilegeResponse }
     * 
     */
    public VerifyServicePrivilegeResponse createVerifyServicePrivilegeResponse() {
        return new VerifyServicePrivilegeResponse();
    }

    /**
     * Create an instance of {@link ApplyTokenByUrl }
     * 
     */
    public ApplyTokenByUrl createApplyTokenByUrl() {
        return new ApplyTokenByUrl();
    }

    /**
     * Create an instance of {@link ApplyAutoLogin }
     * 
     */
    public ApplyAutoLogin createApplyAutoLogin() {
        return new ApplyAutoLogin();
    }

    /**
     * Create an instance of {@link VerifyTokenAndPrivilegeResponse }
     * 
     */
    public VerifyTokenAndPrivilegeResponse createVerifyTokenAndPrivilegeResponse() {
        return new VerifyTokenAndPrivilegeResponse();
    }

    /**
     * Create an instance of {@link VerifyTokenAndPrivilege }
     * 
     */
    public VerifyTokenAndPrivilege createVerifyTokenAndPrivilege() {
        return new VerifyTokenAndPrivilege();
    }

    /**
     * Create an instance of {@link ApplyToken }
     * 
     */
    public ApplyToken createApplyToken() {
        return new ApplyToken();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link LoginByStResponse }
     * 
     */
    public LoginByStResponse createLoginByStResponse() {
        return new LoginByStResponse();
    }

    /**
     * Create an instance of {@link VerifyToken }
     * 
     */
    public VerifyToken createVerifyToken() {
        return new VerifyToken();
    }

    /**
     * Create an instance of {@link AutoLogin }
     * 
     */
    public AutoLogin createAutoLogin() {
        return new AutoLogin();
    }

    /**
     * Create an instance of {@link AutoLoginResponse }
     * 
     */
    public AutoLoginResponse createAutoLoginResponse() {
        return new AutoLoginResponse();
    }

    /**
     * Create an instance of {@link LoginBySt }
     * 
     */
    public LoginBySt createLoginBySt() {
        return new LoginBySt();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link VerifyServicePrivilege }
     * 
     */
    public VerifyServicePrivilege createVerifyServicePrivilege() {
        return new VerifyServicePrivilege();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link VerifyTokenResponse }
     * 
     */
    public VerifyTokenResponse createVerifyTokenResponse() {
        return new VerifyTokenResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "autoLoginSession", scope = AutoLogin.class)
    public JAXBElement<String> createAutoLoginAutoLoginSession(String value) {
        return new JAXBElement<String>(_AutoLoginAutoLoginSession_QNAME, String.class, AutoLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "clientIp", scope = AutoLogin.class)
    public JAXBElement<String> createAutoLoginClientIp(String value) {
        return new JAXBElement<String>(_AutoLoginClientIp_QNAME, String.class, AutoLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "clientmac", scope = AutoLogin.class)
    public JAXBElement<String> createAutoLoginClientmac(String value) {
        return new JAXBElement<String>(_AutoLoginClientmac_QNAME, String.class, AutoLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "serviceIp", scope = AutoLogin.class)
    public JAXBElement<String> createAutoLoginServiceIp(String value) {
        return new JAXBElement<String>(_AutoLoginServiceIp_QNAME, String.class, AutoLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "operCode", scope = VerifyTokenAndPrivilege.class)
    public JAXBElement<String> createVerifyTokenAndPrivilegeOperCode(String value) {
        return new JAXBElement<String>(_VerifyTokenAndPrivilegeOperCode_QNAME, String.class, VerifyTokenAndPrivilege.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "st", scope = VerifyTokenAndPrivilege.class)
    public JAXBElement<String> createVerifyTokenAndPrivilegeSt(String value) {
        return new JAXBElement<String>(_VerifyTokenAndPrivilegeSt_QNAME, String.class, VerifyTokenAndPrivilege.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "indexCode", scope = VerifyTokenAndPrivilege.class)
    public JAXBElement<String> createVerifyTokenAndPrivilegeIndexCode(String value) {
        return new JAXBElement<String>(_VerifyTokenAndPrivilegeIndexCode_QNAME, String.class, VerifyTokenAndPrivilege.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = LoginResponse.class)
    public JAXBElement<String> createLoginResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, LoginResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = ApplyTokenResponse.class)
    public JAXBElement<String> createApplyTokenResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, ApplyTokenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "st", scope = ApplyAutoLogin.class)
    public JAXBElement<String> createApplyAutoLoginSt(String value) {
        return new JAXBElement<String>(_VerifyTokenAndPrivilegeSt_QNAME, String.class, ApplyAutoLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "ip", scope = VerifyServicePrivilege.class)
    public JAXBElement<String> createVerifyServicePrivilegeIp(String value) {
        return new JAXBElement<String>(_VerifyServicePrivilegeIp_QNAME, String.class, VerifyServicePrivilege.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = LoginByStResponse.class)
    public JAXBElement<String> createLoginByStResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, LoginByStResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "clientMac", scope = Login.class)
    public JAXBElement<String> createLoginClientMac(String value) {
        return new JAXBElement<String>(_LoginClientMac_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "clientIp", scope = Login.class)
    public JAXBElement<String> createLoginClientIp(String value) {
        return new JAXBElement<String>(_AutoLoginClientIp_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "serviceIp", scope = Login.class)
    public JAXBElement<String> createLoginServiceIp(String value) {
        return new JAXBElement<String>(_AutoLoginServiceIp_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "loginAccount", scope = Login.class)
    public JAXBElement<String> createLoginLoginAccount(String value) {
        return new JAXBElement<String>(_LoginLoginAccount_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "password", scope = Login.class)
    public JAXBElement<String> createLoginPassword(String value) {
        return new JAXBElement<String>(_LoginPassword_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = AutoLoginResponse.class)
    public JAXBElement<String> createAutoLoginResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, AutoLoginResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "st", scope = LoginBySt.class)
    public JAXBElement<String> createLoginByStSt(String value) {
        return new JAXBElement<String>(_VerifyTokenAndPrivilegeSt_QNAME, String.class, LoginBySt.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = VerifyServicePrivilegeResponse.class)
    public JAXBElement<String> createVerifyServicePrivilegeResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, VerifyServicePrivilegeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = ApplyAutoLoginResponse.class)
    public JAXBElement<String> createApplyAutoLoginResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, ApplyAutoLoginResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "tgt", scope = ApplyToken.class)
    public JAXBElement<String> createApplyTokenTgt(String value) {
        return new JAXBElement<String>(_ApplyTokenTgt_QNAME, String.class, ApplyToken.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = VerifyTokenResponse.class)
    public JAXBElement<String> createVerifyTokenResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, VerifyTokenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "st", scope = VerifyToken.class)
    public JAXBElement<String> createVerifyTokenSt(String value) {
        return new JAXBElement<String>(_VerifyTokenAndPrivilegeSt_QNAME, String.class, VerifyToken.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "tgt", scope = ApplyTokenByUrl.class)
    public JAXBElement<String> createApplyTokenByUrlTgt(String value) {
        return new JAXBElement<String>(_ApplyTokenTgt_QNAME, String.class, ApplyTokenByUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "url", scope = ApplyTokenByUrl.class)
    public JAXBElement<String> createApplyTokenByUrlUrl(String value) {
        return new JAXBElement<String>(_ApplyTokenByUrlUrl_QNAME, String.class, ApplyTokenByUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "tgc", scope = Logout.class)
    public JAXBElement<String> createLogoutTgc(String value) {
        return new JAXBElement<String>(_LogoutTgc_QNAME, String.class, Logout.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = LogoutResponse.class)
    public JAXBElement<String> createLogoutResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, LogoutResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = ApplyTokenByUrlResponse.class)
    public JAXBElement<String> createApplyTokenByUrlResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, ApplyTokenByUrlResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.cms.ivms6.hikvision.com", name = "return", scope = VerifyTokenAndPrivilegeResponse.class)
    public JAXBElement<String> createVerifyTokenAndPrivilegeResponseReturn(String value) {
        return new JAXBElement<String>(_LoginResponseReturn_QNAME, String.class, VerifyTokenAndPrivilegeResponse.class, value);
    }

}
