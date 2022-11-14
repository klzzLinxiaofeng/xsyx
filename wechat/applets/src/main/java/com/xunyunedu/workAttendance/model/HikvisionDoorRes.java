package com.xunyunedu.workAttendance.model;

import lombok.Data;

/**
 * @description: 海康门禁响应类
 * @author: cmb
 * @create: 2020-11-06 16:52
 **/
@Data
public class HikvisionDoorRes {
    /**
     * 返回码，0-成功，其它参考附录E.2.1 门禁管理错误码 False
     */
    private String code;
    /**
     * 返回描述 False
     */
    private String msg;
    /**
     * 返回数据 False
     */
    private Object data;
    /**
     * 当前页码 False
     */
    private Number pageNo;
    /**
     * 单页展示数据数目 False
     */
    private Number pageSize;
    /**
     * 总结果数 False
     */
    private Number total;
    /**
     * 总页数 False
     */
    private Number totalPage;
    /**
     * 返回数据集合 False
     */
    private HikvisionDoorResList[] list;


}
