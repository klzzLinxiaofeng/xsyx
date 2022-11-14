package platform.education.service.vo;

import platform.education.service.model.InSchoolRoom;

/**
 * InSchoolRoom
 *
 * @author AutoCreate
 */
public class InSchoolRoomCondition extends InSchoolRoom {
    private static final long serialVersionUID = 1L;

    public InSchoolRoomCondition() {
    }

    public InSchoolRoomCondition(String name) {
        this.setName(name);
    }
}