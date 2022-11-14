package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjTeacherSalary
 * @author AutoCreate
 *
 */
public class PjTeacherSalary implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所在学校id
	 */
	private Integer schoolId;
	/**
	 * 教师id
	 */
	private Integer teacherId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 教师姓名
	 */
	private String name;
	/**
	 * 工资发放年份
	 */
	private Integer payYear;
	/**
	 * 工资发放月份
	 */
	private Integer payMonth;
	/**
	 * 工资总额
	 */
	private Float salaryTotal;
	/**
	 * 工资明细项01
	 */
	private Float s1;
	/**
	 * 工资明细项02
	 */
	private Float s2;
	/**
	 * 工资明细项03
	 */
	private Float s3;
	/**
	 * 工资明细项04
	 */
	private Float s4;
	/**
	 * 工资明细项05
	 */
	private Float s5;
	/**
	 * 工资明细项06
	 */
	private Float s6;
	/**
	 * 工资明细项07
	 */
	private Float s7;
	/**
	 * 工资明细项08
	 */
	private Float s8;
	/**
	 * 工资明细项09
	 */
	private Float s9;
	/**
	 * 工资明细项10
	 */
	private Float s10;
	/**
	 * 工资明细项11
	 */
	private Float s11;
	/**
	 * 工资明细项12
	 */
	private Float s12;
	/**
	 * 工资明细项13
	 */
	private Float s13;
	/**
	 * 工资明细项14
	 */
	private Float s14;
	/**
	 * 工资明细项15
	 */
	private Float s15;
	/**
	 * 工资明细项16
	 */
	private Float s16;
	/**
	 * 工资明细项17
	 */
	private Float s17;
	/**
	 * 工资明细项18
	 */
	private Float s18;
	/**
	 * 工资明细项19
	 */
	private Float s19;
	/**
	 * 工资明细项20
	 */
	private Float s20;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 记录修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 记录是否删除(默认false)
	 */
	private Boolean isDeleted;
	
	public PjTeacherSalary() {
		
	}
	
	public PjTeacherSalary(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在学校id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所在学校id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取教师id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置教师id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取用户id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取教师姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置教师姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取工资发放年份
	 * @return java.lang.Integer
	 */
	public Integer getPayYear() {
		return this.payYear;
	}
	
	/**
	 * 设置工资发放年份
	 * @param payYear
	 * @type java.lang.Integer
	 */
	public void setPayYear(Integer payYear) {
		this.payYear = payYear;
	}
	
	/**
	 * 获取工资发放月份
	 * @return java.lang.Integer
	 */
	public Integer getPayMonth() {
		return this.payMonth;
	}
	
	/**
	 * 设置工资发放月份
	 * @param payMonth
	 * @type java.lang.Integer
	 */
	public void setPayMonth(Integer payMonth) {
		this.payMonth = payMonth;
	}
	
	/**
	 * 获取工资总额
	 * @return java.lang.Float
	 */
	public Float getSalaryTotal() {
		return this.salaryTotal;
	}
	
	/**
	 * 设置工资总额
	 * @param salaryTotal
	 * @type java.lang.Float
	 */
	public void setSalaryTotal(Float salaryTotal) {
		this.salaryTotal = salaryTotal;
	}
	
	/**
	 * 获取工资明细项01
	 * @return java.lang.Float
	 */
	public Float getS1() {
		return this.s1;
	}
	
	/**
	 * 设置工资明细项01
	 * @param s1
	 * @type java.lang.Float
	 */
	public void setS1(Float s1) {
		this.s1 = s1;
	}
	
	/**
	 * 获取工资明细项02
	 * @return java.lang.Float
	 */
	public Float getS2() {
		return this.s2;
	}
	
	/**
	 * 设置工资明细项02
	 * @param s2
	 * @type java.lang.Float
	 */
	public void setS2(Float s2) {
		this.s2 = s2;
	}
	
	/**
	 * 获取工资明细项03
	 * @return java.lang.Float
	 */
	public Float getS3() {
		return this.s3;
	}
	
	/**
	 * 设置工资明细项03
	 * @param s3
	 * @type java.lang.Float
	 */
	public void setS3(Float s3) {
		this.s3 = s3;
	}
	
	/**
	 * 获取工资明细项04
	 * @return java.lang.Float
	 */
	public Float getS4() {
		return this.s4;
	}
	
	/**
	 * 设置工资明细项04
	 * @param s4
	 * @type java.lang.Float
	 */
	public void setS4(Float s4) {
		this.s4 = s4;
	}
	
	/**
	 * 获取工资明细项05
	 * @return java.lang.Float
	 */
	public Float getS5() {
		return this.s5;
	}
	
	/**
	 * 设置工资明细项05
	 * @param s5
	 * @type java.lang.Float
	 */
	public void setS5(Float s5) {
		this.s5 = s5;
	}
	
	/**
	 * 获取工资明细项06
	 * @return java.lang.Float
	 */
	public Float getS6() {
		return this.s6;
	}
	
	/**
	 * 设置工资明细项06
	 * @param s6
	 * @type java.lang.Float
	 */
	public void setS6(Float s6) {
		this.s6 = s6;
	}
	
	/**
	 * 获取工资明细项07
	 * @return java.lang.Float
	 */
	public Float getS7() {
		return this.s7;
	}
	
	/**
	 * 设置工资明细项07
	 * @param s7
	 * @type java.lang.Float
	 */
	public void setS7(Float s7) {
		this.s7 = s7;
	}
	
	/**
	 * 获取工资明细项08
	 * @return java.lang.Float
	 */
	public Float getS8() {
		return this.s8;
	}
	
	/**
	 * 设置工资明细项08
	 * @param s8
	 * @type java.lang.Float
	 */
	public void setS8(Float s8) {
		this.s8 = s8;
	}
	
	/**
	 * 获取工资明细项09
	 * @return java.lang.Float
	 */
	public Float getS9() {
		return this.s9;
	}
	
	/**
	 * 设置工资明细项09
	 * @param s9
	 * @type java.lang.Float
	 */
	public void setS9(Float s9) {
		this.s9 = s9;
	}
	
	/**
	 * 获取工资明细项10
	 * @return java.lang.Float
	 */
	public Float getS10() {
		return this.s10;
	}
	
	/**
	 * 设置工资明细项10
	 * @param s10
	 * @type java.lang.Float
	 */
	public void setS10(Float s10) {
		this.s10 = s10;
	}
	
	/**
	 * 获取工资明细项11
	 * @return java.lang.Float
	 */
	public Float getS11() {
		return this.s11;
	}
	
	/**
	 * 设置工资明细项11
	 * @param s11
	 * @type java.lang.Float
	 */
	public void setS11(Float s11) {
		this.s11 = s11;
	}
	
	/**
	 * 获取工资明细项12
	 * @return java.lang.Float
	 */
	public Float getS12() {
		return this.s12;
	}
	
	/**
	 * 设置工资明细项12
	 * @param s12
	 * @type java.lang.Float
	 */
	public void setS12(Float s12) {
		this.s12 = s12;
	}
	
	/**
	 * 获取工资明细项13
	 * @return java.lang.Float
	 */
	public Float getS13() {
		return this.s13;
	}
	
	/**
	 * 设置工资明细项13
	 * @param s13
	 * @type java.lang.Float
	 */
	public void setS13(Float s13) {
		this.s13 = s13;
	}
	
	/**
	 * 获取工资明细项14
	 * @return java.lang.Float
	 */
	public Float getS14() {
		return this.s14;
	}
	
	/**
	 * 设置工资明细项14
	 * @param s14
	 * @type java.lang.Float
	 */
	public void setS14(Float s14) {
		this.s14 = s14;
	}
	
	/**
	 * 获取工资明细项15
	 * @return java.lang.Float
	 */
	public Float getS15() {
		return this.s15;
	}
	
	/**
	 * 设置工资明细项15
	 * @param s15
	 * @type java.lang.Float
	 */
	public void setS15(Float s15) {
		this.s15 = s15;
	}
	
	/**
	 * 获取工资明细项16
	 * @return java.lang.Float
	 */
	public Float getS16() {
		return this.s16;
	}
	
	/**
	 * 设置工资明细项16
	 * @param s16
	 * @type java.lang.Float
	 */
	public void setS16(Float s16) {
		this.s16 = s16;
	}
	
	/**
	 * 获取工资明细项17
	 * @return java.lang.Float
	 */
	public Float getS17() {
		return this.s17;
	}
	
	/**
	 * 设置工资明细项17
	 * @param s17
	 * @type java.lang.Float
	 */
	public void setS17(Float s17) {
		this.s17 = s17;
	}
	
	/**
	 * 获取工资明细项18
	 * @return java.lang.Float
	 */
	public Float getS18() {
		return this.s18;
	}
	
	/**
	 * 设置工资明细项18
	 * @param s18
	 * @type java.lang.Float
	 */
	public void setS18(Float s18) {
		this.s18 = s18;
	}
	
	/**
	 * 获取工资明细项19
	 * @return java.lang.Float
	 */
	public Float getS19() {
		return this.s19;
	}
	
	/**
	 * 设置工资明细项19
	 * @param s19
	 * @type java.lang.Float
	 */
	public void setS19(Float s19) {
		this.s19 = s19;
	}
	
	/**
	 * 获取工资明细项20
	 * @return java.lang.Float
	 */
	public Float getS20() {
		return this.s20;
	}
	
	/**
	 * 设置工资明细项20
	 * @param s20
	 * @type java.lang.Float
	 */
	public void setS20(Float s20) {
		this.s20 = s20;
	}
	
	/**
	 * 获取记录创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置记录创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取记录修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置记录修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取记录是否删除(默认false)
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录是否删除(默认false)
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}