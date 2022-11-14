package platform.education.rest.bp.service.vo;

import platform.education.clazz.model.Room;

import java.io.Serializable;
import java.util.List;

public class BpRoomVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String RoomTypeName;
	
	private String RoomTypeCode;
	
	List<Room> roomList;

	public String getRoomTypeName() {
		return RoomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		RoomTypeName = roomTypeName;
	}

	public String getRoomTypeCode() {
		return RoomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		RoomTypeCode = roomTypeCode;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
	
	
	
}
