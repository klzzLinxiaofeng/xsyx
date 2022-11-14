package platform.szxyzxx.web.common.vo;

import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;

/**
 * Created by Administrator on 2018/1/8.
 */
public class XbullUserInfo {

    private static final long serialVersionUID = -5179686936016731378L;
    /**用户唯一标识*/
    private String xbull_openid;
    /**角色标识*/
    private String role;
    /**账号id（微信端和PC端不同）*/
    private Integer uid;


    private Integer school_id;

    private Integer school_app_code;

    private String school_name;


    private Integer teacher_id;

    private Integer teacher_app_code;

    private String teacher_name;

    private String teacher_sex;


    private Integer parent_id;

    private Integer parent_app_code;

    private String parent_name;

    private String parent_sex;


    private Integer child_id;

    private Integer child_app_code;

    private String child_name;

    private String child_sex;


    private String[] teacher_headimgurl;

    private String[] child_headimgurl;

    public String getXbull_openid() {
        return xbull_openid;
    }

    public void setXbull_openid(String xbull_openid) {
        this.xbull_openid = xbull_openid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public Integer getSchool_app_code() {
        return school_app_code;
    }

    public void setSchool_app_code(Integer school_app_code) {
        this.school_app_code = school_app_code;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getTeacher_app_code() {
        return teacher_app_code;
    }

    public void setTeacher_app_code(Integer teacher_app_code) {
        this.teacher_app_code = teacher_app_code;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_sex() {
        return teacher_sex;
    }

    public void setTeacher_sex(String teacher_sex) {
        this.teacher_sex = teacher_sex;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getParent_app_code() {
        return parent_app_code;
    }

    public void setParent_app_code(Integer parent_app_code) {
        this.parent_app_code = parent_app_code;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_sex() {
        return parent_sex;
    }

    public void setParent_sex(String parent_sex) {
        this.parent_sex = parent_sex;
    }

    public Integer getChild_id() {
        return child_id;
    }

    public void setChild_id(Integer child_id) {
        this.child_id = child_id;
    }

    public Integer getChild_app_code() {
        return child_app_code;
    }

    public void setChild_app_code(Integer child_app_code) {
        this.child_app_code = child_app_code;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public String getChild_sex() {
        return child_sex;
    }

    public void setChild_sex(String child_sex) {
        this.child_sex = child_sex;
    }

    public String[] getTeacher_headimgurl() {
        return teacher_headimgurl;
    }

    public void setTeacher_headimgurl(String[] teacher_headimgurl) {
        this.teacher_headimgurl = teacher_headimgurl;
    }

    public String[] getChild_headimgurl() {
        return child_headimgurl;
    }

    public void setChild_headimgurl(String[] child_headimgurl) {
        this.child_headimgurl = child_headimgurl;
    }
}
