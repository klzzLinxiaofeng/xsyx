package platform.szxyzxx.library.vo;

/**
 * @Author zhangyong
 * @Date 2022/10/31 9:14
 * @Version 1.0
 */
public class Library {
    private  Integer id;
    private String name;
    private Integer schoolId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
