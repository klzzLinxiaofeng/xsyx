package platform.education.oa.vo;
import platform.education.oa.model.AcceptRepari;
/**
 * AcceptRepari
 * @author AutoCreate
 *
 */
public class AcceptRepariVo extends AcceptRepari {
	private static final long serialVersionUID = 1L;
	private Integer number;
	private Integer appraiseNum;
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getAppraiseNum() {
		return appraiseNum;
	}
	public void setAppraiseNum(Integer appraiseNum) {
		this.appraiseNum = appraiseNum;
	}
}