package platform.education.oa.vo;
import platform.education.oa.model.Device;
/**
 * Device
 * @author AutoCreate
 *
 */
public class DeviceVo extends Device {
	private static final long serialVersionUID = 1L;
	private String blidingName;
	private String roomName;
	public String getBlidingName() {
		return blidingName;
	}
	public void setBlidingName(String blidingName) {
		this.blidingName = blidingName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
}