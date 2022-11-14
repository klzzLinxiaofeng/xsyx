package platform.szxyzxx.web.teach.client.vo;

/**
 * 功能描述：年级（pj_grade）
 * @author 
 * @Date 2016-01-13
 */
public class ExtGradeVo {

	/**
	 * 年级ID
	 */
	private Integer id;
	
	/**
	 * 年级名称
	 */
	private String name;

	/*================ Added on 2017-08-24 BEGIN ======================*/

	/**
	 * 学年值
	 */
	private String schoolYear;

	/**
	 * 年级代码
	 */
	private String code;

	/*================ Added on 2017-08-24 END ======================*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
