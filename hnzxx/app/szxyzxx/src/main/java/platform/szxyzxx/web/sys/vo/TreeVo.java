package platform.szxyzxx.web.sys.vo;

import java.io.Serializable;

public class TreeVo implements Serializable {

	private static final long serialVersionUID = 6308485025295100775L;

	private String id;

	private String pId;

	private String name;

	private Integer moduleLv;

	private boolean open;

	private boolean isParent;
	
	public boolean checked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getModuleLv() {
		return moduleLv;
	}

	public void setModuleLv(Integer moduleLv) {
		this.moduleLv = moduleLv;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
