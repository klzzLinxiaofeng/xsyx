package platform.education.rest.jw.service.constant;

import java.io.Serializable;

public class MembershipConstants implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 总校，下设分校
	 */
	public static final String Type_General_School = "pj.general_school";
	
	/**
	 * 分校，级别与学校平行，记录都在school表
	 */
	public static final String Type_Branch_School = "pj.branch_school";
	
	/**
	 * 独立的学校，记录在school表
	 */
	public static final String Type_School = "pj.school";
	      
	/**
	 * 校区，学区
	 */
	public static final String Type_School_Zone = "pj.school_zone";
	
	/**
	 * 部门
	 */
	public static final String Type_Dept = "pj.dept";
	
	/**
	 * 工作组
	 */
	public static final String Type_Workgroup = "pj.workgroup";

}
