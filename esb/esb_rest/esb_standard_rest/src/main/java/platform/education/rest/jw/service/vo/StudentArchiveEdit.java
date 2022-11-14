package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class StudentArchiveEdit implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4293270601121606921L;
	
	private Boolean canEdit;
	
	public StudentArchiveEdit(){
		
	}

	
	public StudentArchiveEdit(Boolean canEdit)
	{
		super();
		this.canEdit = canEdit;
	}

	public Boolean getCanEdit()
	{
		return canEdit;
	}

	public void setCanEdit(Boolean canEdit)
	{
		this.canEdit = canEdit;
	}

	
	
}
