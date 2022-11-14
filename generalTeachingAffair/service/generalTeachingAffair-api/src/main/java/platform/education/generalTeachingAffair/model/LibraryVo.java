package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/**
 * @author: yhc
 * @Date: 2020/9/28 15:17
 * @Description: 图书馆接口对接实体
 */
public class LibraryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 读者编号
     */
    private String No;
    /**
     * 读者押金
     */
    private String money;
    /**
     * 读者姓名
     */
    private String name;
    /**
     * 是否 使能
     */
    private String enabled;
    /**
     * 身份证号
     */
    private String identityNo;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birth;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String addr;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色Id
     */
    private String roleId;
    /**
     * 角色姓名
     */
    private String roleName;
    /**
     * 图片，需要上传图片则 存放图片的base64编码即可,没有图片则必须为null
     */
    private String photoUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
