package platform.szxyzxx.knowledge.vo;

import java.util.Date;
import java.util.List;

public class Menu {
    /*
    * 主键id
    */
    private Integer id;
    /*
     * 菜单名称
     */
    private String name;
    /*
     * 排序
     */
    private Integer paixu;
    /*
     * 课本id
     */
    private Integer knowId;
    /*
     * 父菜单id
     */
    private Integer parentMenu;
    /*
     * 父菜单name
     */
    private String parentMenuName;
    /*
     * 是否删除
     */
    private Integer isDelete;
    /*
     * 层级
     */
    private Integer leven;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modiyTime;
    List<Menu> children;

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

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

    public Integer getPaixu() {
        return paixu;
    }

    public void setPaixu(Integer paixu) {
        this.paixu = paixu;
    }

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }

    public Integer getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Integer parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getLeven() {
        return leven;
    }

    public void setLeven(Integer leven) {
        this.leven = leven;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }
}
