package platform.szxyzxx.web.common.vo;

import java.io.Serializable;
import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ObjectWithPageAndOrder<T> implements Serializable {

	private static final long serialVersionUID = -5282355718668496302L;

	private List<T> data;

	private Page page;

	private Order order;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
