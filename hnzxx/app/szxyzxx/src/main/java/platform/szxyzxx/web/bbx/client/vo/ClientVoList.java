package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

import platform.education.clazz.vo.TeamActivityVo;

public class ClientVoList<T> {
	private static final long serialVersionUID = 1L;
	/**
	 * 班级活动的list
	 */
	private List<T> voList;
	
	/**
	 * 数据总条数
	 */
	private Integer totalRows;
	/**
	 * 当前页
	 */
	private Integer currentPage;

	public List<T> getVoList() {
		return voList;
	}

	public void setVoList(List<T> voList) {
		this.voList = voList;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
