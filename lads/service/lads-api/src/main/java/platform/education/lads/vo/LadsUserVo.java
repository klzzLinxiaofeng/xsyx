/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.vo;

/**
 * @see Lads的用户信息类，可方便外部系统接入用户
 * @author Administrator
 */
public class LadsUserVo {

    private Integer userId;
    private String realName;
    private String photo;
    private double score;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
}
