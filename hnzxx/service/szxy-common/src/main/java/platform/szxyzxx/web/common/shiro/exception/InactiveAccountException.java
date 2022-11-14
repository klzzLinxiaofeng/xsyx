package platform.szxyzxx.web.common.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class InactiveAccountException extends AuthenticationException {

	private static final long serialVersionUID = -351357024094791278L;

	public InactiveAccountException() {
		super();
	}

	public InactiveAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public InactiveAccountException(String message) {
		super(message);
	}

	public InactiveAccountException(Throwable cause) {
		super(cause);
	}

}
