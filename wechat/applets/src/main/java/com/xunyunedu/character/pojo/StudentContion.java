package com.xunyunedu.character.pojo;

public class StudentContion implements Comparable<StudentContion> {
    private Integer stidentId;
    private String studnetName;
    private Integer entityId;
    private Integer entityName;
    private Integer teamId;
    private String teamName;
    private Integer gradeId;
    private String gradeName;
    private Integer score;
    private String recordName;
    private String uuid;
    private String uuidUrl;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidUrl() {
        return uuidUrl;
    }

    public void setUuidUrl(String uuidUrl) {
        this.uuidUrl = uuidUrl;
    }

    public StudentContion(){}

    public Integer getEntityName() {
        return entityName;
    }

    public void setEntityName(Integer entityName) {
        this.entityName = entityName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    @Override
    public String toString() {
        return "StudentContion{" +
                "stidentId=" + stidentId +
                ", studnetName='" + studnetName + '\'' +
                ", entityId=" + entityId +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getStidentId() {
        return stidentId;
    }

    public void setStidentId(Integer stidentId) {
        this.stidentId = stidentId;
    }

    public String getStudnetName() {
        return studnetName;
    }

    public void setStudnetName(String studnetName) {
        this.studnetName = studnetName;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(StudentContion o) {
        return this.score.compareTo(o.getScore());
    }

}
