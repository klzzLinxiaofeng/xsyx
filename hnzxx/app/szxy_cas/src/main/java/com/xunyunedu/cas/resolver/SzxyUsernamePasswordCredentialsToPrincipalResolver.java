package com.xunyunedu.cas.resolver;

import org.jasig.cas.authentication.principal.AbstractPersonDirectoryCredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import platform.education.user.model.User;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserService;

public class SzxyUsernamePasswordCredentialsToPrincipalResolver extends
		AbstractPersonDirectoryCredentialsToPrincipalResolver {

	private UserBindingService userBindingService;

	private UserService userService;

	public void setUserBindingService(UserBindingService userBindingService) {
		this.userBindingService = userBindingService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Credentials credentials) {
		return credentials != null && UsernamePasswordCredentials.class.isAssignableFrom(credentials.getClass());
	}

	@Override
	protected String extractPrincipalId(Credentials credentials) {
		final UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) credentials;
		String username = usernamePasswordCredentials.getUsername();
		User user = this.userService.findUserByUsername(username);
		if(user != null) {
			username = user.getUserName();
		}
		return username;
	}

}
