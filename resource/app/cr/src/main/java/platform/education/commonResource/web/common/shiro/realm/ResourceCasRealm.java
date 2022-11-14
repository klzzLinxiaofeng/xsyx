package platform.education.commonResource.web.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.commonResource.web.common.shiro.code.token.CrCasToken;
import platform.education.commonResource.web.common.util.MultiDomainResolver;
import platform.education.user.service.UserService;

import java.util.List;
import java.util.Map;

public class ResourceCasRealm extends CasRealm {

	private static Logger log = LoggerFactory.getLogger(ResourceCasRealm.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		return authorizationInfo;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof CrCasToken;//表示此Realm只支持SzxyCasToken类型
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CrCasToken casToken = (CrCasToken)token;
		if (token == null) {
			return null;
		} else {
			String ticket = (String)casToken.getCredentials();
			if (!StringUtils.hasText(ticket)) {
				return null;
			} else {
				TicketValidator ticketValidator = this.ensureTicketValidator();

				try {
					String casService = MultiDomainResolver.resolver(casToken.getServerName(), this.getCasService());

					Assertion casAssertion = ticketValidator.validate(ticket, casService);
					AttributePrincipal casPrincipal = casAssertion.getPrincipal();
					String userId = casPrincipal.getName();
					log.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{ticket, this.getCasServerUrlPrefix(), userId});
					Map<String, Object> attributes = casPrincipal.getAttributes();
					casToken.setUserId(userId);
					String rememberMeAttributeName = this.getRememberMeAttributeName();
					String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
					boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
					if (isRemembered) {
						casToken.setRememberMe(true);
					}

					List<Object> principals = CollectionUtils.asList(new Object[]{userId, attributes});
					PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, this.getName());
					return new SimpleAuthenticationInfo(principalCollection, ticket);
				} catch (TicketValidationException var14) {
					throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", var14);
				}
			}
		}
	}

	/**
	 * 解决cas表单提交乱码
	 *
	 * @return
	 */
	@Override
	protected TicketValidator createTicketValidator() {
		String urlPrefix = this.getCasServerUrlPrefix();
		TicketValidator ticketValidator = null;
		if("saml".equalsIgnoreCase(this.getValidationProtocol())){
			Saml11TicketValidator saml11TicketValidator = new Saml11TicketValidator(urlPrefix);
			saml11TicketValidator.setEncoding("utf-8");
			ticketValidator = saml11TicketValidator;
		}else {
			Cas20ServiceTicketValidator cas20ServiceTicketValidator = new Cas20ServiceTicketValidator(urlPrefix);
			cas20ServiceTicketValidator.setEncoding("utf-8");
			ticketValidator = cas20ServiceTicketValidator;
		}
		return ticketValidator;
	}
}