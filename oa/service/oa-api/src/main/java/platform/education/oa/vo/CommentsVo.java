package platform.education.oa.vo;
import platform.education.oa.model.Comments;
/**
 * Comments
 * @author AutoCreate
 *
 */
public class CommentsVo extends Comments {
	private static final long serialVersionUID = 1L;
	private String distanceNowTime;
	public String getDistanceNowTime() {
		return distanceNowTime;
	}
	public void setDistanceNowTime(String distanceNowTime) {
		this.distanceNowTime = distanceNowTime;
	}
}