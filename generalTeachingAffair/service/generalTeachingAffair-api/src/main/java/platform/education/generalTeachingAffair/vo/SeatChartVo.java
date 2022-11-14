package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.SeatChart;
/**
 * SeatChart
 * @author AutoCreate
 *
 */
public class SeatChartVo extends SeatChart {
	private static final long serialVersionUID = 1L;
	
	private String floorName;//大楼名称
	private Integer classroomStorey;//教室楼层
	private String classroomPosition;//教室的具体位置
	private String classroomTypeName;
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public Integer getClassroomStorey() {
		return classroomStorey;
	}
	public void setClassroomStorey(Integer classroomStorey) {
		this.classroomStorey = classroomStorey;
	}
	public String getClassroomPosition() {
		return classroomPosition;
	}
	public void setClassroomPosition(String classroomPosition) {
		this.classroomPosition = classroomPosition;
	}
	public String getClassroomTypeName() {
		return classroomTypeName;
	}
	public void setClassroomTypeName(String classroomTypeName) {
		this.classroomTypeName = classroomTypeName;
	}
	
	
}