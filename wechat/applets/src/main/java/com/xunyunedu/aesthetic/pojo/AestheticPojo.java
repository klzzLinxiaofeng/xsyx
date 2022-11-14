package com.xunyunedu.aesthetic.pojo;


import com.xunyunedu.character.pojo.Picture;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AestheticPojo {
    /*
    * 主键id
    */
    private Integer id;
    /*
     * 学生_id
     */
    private Integer studentId;
    /*
     * 学生name
     */
    private String studentName;
    /*
     * 美术作品id
     */
    private String fineArtId;
    /*
     * 美术作品Url
     */
    private String fineArtUrl;
    /*
     * 点评
     */
    private String review;
    /*
     * 评分
     */
    private Integer reviewSore;
    private List<Picture> pictureList;
    private List<Picture> pictureListTwo;
    private List<Picture> pictureListStree;




    /*
     * 比赛作品id
     */
    private String gameWorksId;
    /*
     * 比赛作品Url
     */
    private String gameWorksUrl;
    /*
     * 个人奖状id
     */
    private String jiangzhuanId;
    /*
     * 个人奖状url
     */
    private String jiangzhuanUrl;
    /*
     * 评语
     */
    private String comments;

    /*
    * 创建时间
    */
    private Date createTime;
}
