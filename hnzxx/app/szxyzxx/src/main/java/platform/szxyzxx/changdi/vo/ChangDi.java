package platform.szxyzxx.changdi.vo;

import java.util.Date;

public class ChangDi {
    private Integer id;
    private String  name;
    private String address;
    private Date craeteTime;
    private Date modiyTime;
    private Integer isDelete;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCraeteTime() {
        return craeteTime;
    }

    public void setCraeteTime(Date craeteTime) {
        this.craeteTime = craeteTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
