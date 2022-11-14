package platform.szxyzxx.web.common.shiro.realm;

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
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.shiro.core.token.SzxyCasToken;
import platform.szxyzxx.web.common.util.MultiDomainResolver;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SzxyCasRealm extends CasRealm {

	private static Logger log = LoggerFactory.getLogger(SzxyCasRealm.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		// admin
		String username = (String) principals.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>(userRoleService.findRoleCodesByUsername(username, SysContants.SYSTEM_APP_ID));
//		 角色
		authorizationInfo.setRoles(roles);
		return authorizationInfo;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof SzxyCasToken;//表示此Realm只支持SzxyCasToken类型
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SzxyCasToken casToken = (SzxyCasToken)token;
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
					System.out.println("ticket:--------------->"+ticket);
					System.out.println("casService:--------------->"+casService);
					Assertion casAssertion = ticketValidator.validate(ticket, casService);
					AttributePrincipal casPrincipal = casAssertion.getPrincipal();
					String userId = casPrincipal.getName();
					System.out.println("Validate ticket : {} in CAS server : {} to retrieve user : {}"+ new Object[]{ticket, this.getCasServerUrlPrefix(), userId});
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
					
					var14.printStackTrace();
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