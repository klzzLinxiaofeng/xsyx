package platform.szxyzxx.web.common.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

public class CasSessionAttributeListener implements
		HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String key = event.getName();

		if (key.equals(AbstractCasFilter.CONST_CAS_ASSERTION)) {
			Assertion as = (Assertion) event.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
			String username = as.getPrincipal().getName();
			event.getSession().setAttribute("user", username);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {

	}

}
