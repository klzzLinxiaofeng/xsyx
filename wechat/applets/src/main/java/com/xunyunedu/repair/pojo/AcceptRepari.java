package com.xunyunedu.repair.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 * 维修单维修和评价信息
 *
 */
@Data
public class AcceptRepari {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 维修人ID
	 */
	private Integer accepterId;
	/**
	 * 维修人姓名
	 */
	private String accepterName;
	/**
	 * 维修人联系电话
	 */
	private String phone;
	/**
	 * 维修时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private java.util.Date acceptDate;
	/**
	 * 评价星级
	 */
	private Integer appraise;
	/**
	 * 维修说明
	 */
	private String remark;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private java.util.Date createDate;
	/**
	 * 修改时间(评论时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private java.util.Date modifyDate;
	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	/**
	 * 维修申请
	 */
	private Integer repariId;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	
	//======2016-3-8=========
	/**
	 * 解决办法 
	 */
	private String solution;
	/**
	 * 是否耗材
	 */
	private Integer isHaoCai;


	
}