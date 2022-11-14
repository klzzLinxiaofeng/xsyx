package platform.szxyzxx.web.common.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class OverdueAccountException extends AuthenticationException {
	
	private static final long serialVersionUID = 2037243392692664837L;

	public OverdueAccountException() {
		super();
	}

	public OverdueAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public OverdueAccountException(String message) {
		super(message);
	}

	public OverdueAccountException(Throwable cause) {
		super(cause);
	}
	
}
