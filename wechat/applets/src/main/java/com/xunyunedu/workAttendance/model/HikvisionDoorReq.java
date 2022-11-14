package com.xunyunedu.workAttendance.model;

import lombok.Data;

/**
 * @description: 海康门禁请求类
 * @author: cmb
 * @create: 2020-11-06 16:36
 **/
@Data
public class HikvisionDoorReq {
    /**
     *  学生id或教师登录id true
     */
    private Integer userId;
    /**
     * 当前页码 true
     */
    private Integer pageNo;
    /**
     * 每页展示数目（0<pageSize<=1000）true
     */
    private Integer pageSize;
    /**
     * 门禁点唯一标识数组，最大支持10个门禁点 false
     */
    private String[] doorIndexCodes;
    /**
     *门禁点名称，支持模糊查询， false
     */
    private String doorName;
    /**
     * 读卡器唯一标识数组，最大支持50个读卡器，false
     */
    private String[] readerDevIndexCodes;
    /**
     *开始时间（事件开始时间，采用ISO8601时间格式，与endTime配对使用，不能单独使用，时间范围最大不能超过3个月）true
     */
    private String startTime;
    /**
     *结束时间（事件结束时间，采用ISO8601时间格式，最大长度32个字符，与startTime配对使用，不能单独使用，时间范围最大不能超过3个月）true
     */
    private String endTime;
    /**
     *入库开始时间，采用ISO8601时间格式，与receiveEndTime配对使用，不能单独使用，时间范围最大不能超过3个月 true
     */
    private String receiveStartTime;
    /**
     * 入库结束时间，采用ISO8601时间格式，最大长度32个字符，与receiveStartTime配对使用，不能单独使用，时间范围最大不能超过3个月 true
     */
    private String receiveEndTime;
    /**
     *门禁点所在区域集合 false
     */
    private String[] doorRegionIndexCodes;
    /**
     * 事件类型 false
     */
    private Number[] eventTypes;
    /**
     * 人员姓名(支持中英文字符，不能包含 ’ / \ : * ? " < > false
     */
    private String personName;
    /**
     *排序字段（支持personName、doorName、eventTime填写排序的字段名称）false
     */
    private String sort;
    /**
     * 	升/降序（指定排序字段是使用升序（asc）还是降序（desc）false
     */

}
