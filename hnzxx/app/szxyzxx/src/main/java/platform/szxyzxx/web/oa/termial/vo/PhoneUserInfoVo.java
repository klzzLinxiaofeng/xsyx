package platform.szxyzxx.web.oa.termial.vo;

import platform.education.generalTeachingAffair.model.Teacher;

public class PhoneUserInfoVo {
	 /**
	    * 用户id
	    */
	private String user_id;
   /**
    * 用户名称
    */
   private String username;
   /**
    *姓名
    */
   private String name;
   /**
    * 用户头像路径
    */
   private String picture;
   /**
    * 性别
    */
   private String sex;
   /**
    * 职务
    */
   private String position;
   /**
    * 办公电话
    */
   private String telephone;
   /**
    * 手机号码 
    */
   private String mobile;
   /**
    * 教师ID
    */
   private String teacher_id;
   
   public PhoneUserInfoVo(Teacher t,String imgurl){
	   this.user_id=t.getUserId()+"";
	   this.username=t.getUserName();
	   this.name=t.getName();
	   this.picture=imgurl;
	   this.sex=t.getSex();
	   this.position=t.getPosition();
	   this.telephone=t.getTelephone();
	   this.mobile=t.getMobile();
	   this.teacher_id=t.getId()+"";
   }
   
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getTeacher_id() {
	return teacher_id;
}
public void setTeacher_id(String teacher_id) {
	this.teacher_id = teacher_id;
}
   
   
}
