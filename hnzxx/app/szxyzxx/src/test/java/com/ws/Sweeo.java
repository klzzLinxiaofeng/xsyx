package com.ws;

import java.util.List;

public class Sweeo {
    private Boolean simpleCourseName;
    private List timetableItems;
    private String planUid;
    private String classroomUid;
    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean getSimpleCourseName() {
        return simpleCourseName;
    }

    public void setSimpleCourseName(Boolean simpleCourseName) {
        this.simpleCourseName = simpleCourseName;
    }

    public List getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(List timetableItems) {
        this.timetableItems = timetableItems;
    }

    public String getPlanUid() {
        return planUid;
    }

    public void setPlanUid(String planUid) {
        this.planUid = planUid;
    }

    public String getClassroomUid() {
        return classroomUid;
    }

    public void setClassroomUid(String classroomUid) {
        this.classroomUid = classroomUid;
    }
}
