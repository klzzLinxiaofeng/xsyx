package platform.education.generalTeachingAffair.bo;


/**
 * @author edison
 */
public class StudentBo {

    Integer id;

    String empCard;

    String name;

    String number;

    String parentName;

    String personMobile;

    Integer teamId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
