package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * @author Eternityhua
 * @create 2020-12-13 10:58
 */
public class StudentHealthArchiveType implements Model<Integer> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer studentHealthId;

    private Integer healthType;

    public Integer getStudentHealthId() {
        return studentHealthId;
    }

    public void setStudentHealthId(Integer studentHealthId) {
        this.studentHealthId = studentHealthId;
    }

    public Integer getHealthType() {
        return healthType;
    }

    public void setHealthType(Integer healthType) {
        this.healthType = healthType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "StudentHealthArchiveType{" +
                "id=" + id +
                ", studentHealthId=" + studentHealthId +
                ", healthType=" + healthType +
                '}';
    }

    @Override
    public Integer getKey() {
        return this.id;
    }
}
