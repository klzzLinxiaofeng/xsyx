package com.xunyunedu.repair.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 报修实体类
 * @author lee
 * @Date 2020/12/9
 */
@Data
public class ApplyrepairPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 学校ID
     */
    private Integer shcoolId;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String picture;
    /**
     * 图片
     */
    private String pictureUrl;
    /**
     * 详情
     */
    private String details;
    /**
     * 维修类型
     */
    private Integer typeId;

    /**
     * 地点
     */
    private String place;
    /**
     * 大楼ID
     */
    private Integer bildingId;
    /**
     * 房间ID
     */
    private Integer roomId;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private java.util.Date appointmentDate;
    /**
     * 维修状态，也就是填写维修信息勾选的状态(01：待处理，02，处理中，03：已处理，04：未修好)
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private java.util.Date createDate;
    /**
     * 修改时间
     */
    private java.util.Date modifyDate;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 申请人ID
     */
    private Integer proposerId;
    /**
     * 申请人姓名（冗余）
     */
    private String proposerName;
    /**
     * 维修完后评价上传的图片
     */
    private String wholePicture;

    /**
     *  报修部门
     */
    private Integer departmentId;
    /**
     * 报修编号
     */
    private String number;

    private String typeName;

    /**
     * 维修单的评价和维修信息
     */
    private AcceptRepari acceptRepari;
    /**
     * 维修单的真实维修状态（0：待维修，1：已维修，2：已评论（已完成））
     */
    private Integer realStatus;
    /**
     *  审核人id
     */
    private Integer shenherenId;
    /**
     *  审核人姓名
     */
    private String shenherenName;
    /**
     *  申请图片
     */
    private String shenqingPictureId;
    /**
     *  申请图片url
     */
    private String shenqingPictureUrl;
    /*
    * 是否耗材
    */
    private Integer isHaoCai;
    /*
     * 审核状态
     */
    private Integer isShenHe;
    /*
     * 拒绝理由
     */
    private String liyou;
     /*
     *维修人id
     */
    private Integer weixiuGongId;
    /*
     *维修人姓名
     */
    private String weixiuGongName;
    private Date weixiuTime;
    private String weixiuphone;
    private String weixiumodiyDate;
    private Integer xingji;
    private String weixiuremark;

}
