package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Person;
/**
 * JwPerson
 * @author AutoCreate
 *
 */
public class PersonVo extends Person {
	private static final long serialVersionUID = 1L;
	
	private Integer nationalMinority;//少数民族
	
	private Integer unNationalMinority;//非少数民族
	
	private Integer notNational;//没有民族信息

	public Integer getNationalMinority() {
		return nationalMinority;
	}

	public void setNationalMinority(Integer nationalMinority) {
		this.nationalMinority = nationalMinority;
	}

	public Integer getUnNationalMinority() {
		return unNationalMinority;
	}

	public void setUnNationalMinority(Integer unNationalMinority) {
		this.unNationalMinority = unNationalMinority;
	}

	public Integer getNotNational() {
		return notNational;
	}

	public void setNotNational(Integer notNational) {
		this.notNational = notNational;
	}
	
}