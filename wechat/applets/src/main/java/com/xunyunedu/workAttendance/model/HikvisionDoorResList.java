package com.xunyunedu.workAttendance.model;

import lombok.Data;

/**
 * @description: 返回的数据集合
 * @author: cmb
 * @create: 2020-11-06 19:00
 **/
@Data
public class HikvisionDoorResList {
    /**
     * False 	事件ID，唯一标识这个事件
     */
    private String eventId;
    /**
     * 事件名称 False
     */
    private String eventName;
    /**
     * 事件产生时间 False
     */
    private String eventTime;
    /**
     * 卡号 False
     */
    private String cardNo;
    /**
     * 人员唯一编码 False
     */
    private String personId;
    /**
     * 人员名称 False
     */
    private String personName;
    /**
     * 人员所属组织编码 False
     */
    private String orgIndexCode;
    /**
     * 人员所属组织名称 False
     */
    private String orgName;
    /**
     * 门禁点编码  False
     */
    private String doorIndexCode;
    /**
     * 门禁点名称 False
     */
    private String doorName;
    /**
     * 门禁点所在区域编码 False
     */
    private String doorRegionIndexCode;
    /**
     * 抓拍图片地址，通过接口获取门禁事件的图片接口获取门禁事件的图片数据 False
     */
    private String picUri;
    /**
     * 图片存储服务的唯一标识 False
     */
    private String svrIndexCode;
    /**
     * 事件类型，参考附录D2.1 门禁事件 False
     */
    private Number eventType;
    /**
     * 进出类型(1：进
     * 0：出
     * -1:未知
     * 要求：进门读卡器拨码设置为1，出门读卡器拨码设置为2
     * )  False
     */
    private Number inAndOutType;
    /**
     * 读卡器IndexCode False
     */
    private String readerDevIndexCode;
    /**
     * 读卡器名称 False
     */
    private String readerDevName;
    /**
     * 控制器设备IndexCode False
     */
    private String devIndexCode;
    /**
     * 控制器名称 False
     */
    private String devName;
    /**
     * 身份证图片uri，它是一个相对地址，可以通过获取门禁事件的图片接口，获取到图片的数据  False
     */
    private String identityCardUri;
    /**
     * 事件入库时间 False
     */
    private String receiveTime;
    /**
     * 工号  False
     */
    private String jobNo;
    /**
     * 学号 False
     */
    private String studentId;
    /**
     * 证件号码 False
     */
    private Object certNo;

}
