package platform.szxyzxx.web.gc.vo;

import java.util.List;

import platform.education.generalcode.model.Framework;
import platform.education.generalcode.model.Item;
import platform.education.generalcode.model.Table;

public class CodeBaseList {
	public CodeBaseList(List<Framework> frameworkList, List<Table> tableList, List<Item> itemList) {
		super();
		this.frameworkList = frameworkList;
		this.tableList = tableList;
		this.itemList = itemList;
	}
	
	public CodeBaseList() {
		super();
	}

	//基础代码1级
	private List<Framework> frameworkList;
	//基础代码2级
	private List<Table> tableList;
	//基础代码3级
	private List<Item> itemList;

	public List<Framework> getFrameworkList() {
		return frameworkList;
	}

	public void setFrameworkList(List<Framework> frameworkList) {
		this.frameworkList = frameworkList;
	}

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
}
