package com.xunyunedu.exception;

/**
 * @author: yhc
 * @Date: 2020/9/18 14:44
 * @Description: 自定义返回码
 */
public enum ResultCode {
    SUCCESS(200, "成功!"),
    CREATE_SUCCESS(200,"创建成功"),
    FIND_SUCCESS(200,"查询成功"),
    UPDATA_SUCCESS(200,"更新成功"),
    DELETE_SUCCESS(200,"删除成功"),
    CREATE_FAIL(400,"创建失败"),
    DATA_EXITS(400,"数据已存在"),
    NOT_PAY(200,"未支付"),
    FIND_FAIL(400,"查询失败"),
    DOWNLOAD_FILE(500,"下载失败"),
    PREVIEW_SUCCESS(200,"预览成功"),
    PREVIEW_FAIL(500,"预览失败"),
    DOWNLOAD_SUCCESS(200,"下载成功"),
    INSERT_SUCCESS(200,"插入成功"),
    UPLOAD_SUCCESS(200,"上传成功"),
    UPDATA_FAIL(400,"更新失败"),
    DELETE_FAIL(400,"删除失败"),
    SAVE_FAIL(400,"插入失败"),
    BODY_NOT_MATCH(400, "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"),
    USER_NOT_FONUD(400, "用户找不到"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    ERROR_WX_OPENID(508, "微信openid异常"),
    ERROR_TOKEN(504, "token错误"),
    ERROR_WX_CODE(20004, "code错误"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    Authorization(101, "获取授权信息失败"),
    USER_ACCOUNT_ERROR(20003, "用户名或密码错误"),

    CHOOSE_CLASS_ERROR(20005, "用户选课失败"),
    CHOOSE_FULL_ERROR(20006, "人数已满"),
    PARAMS_IS_NULL(10001, "参数为空"),
    PARAMS_NOT_COMPLETE(10002, "参数不全"),
    PARAMS_TYPE_ERROR(1003, "参数类型错误"),
    PARAMS_IS_INVALID(10004, "参数无效"),
    STU_ID_EXISTS(10005, "学籍号已存在"),
    CLASS_ID_EXISTS(10006, "班内学号已存在"),
    ID_CARD_EXISTS(10007, "身份证已存在"),
    PHONE_NUMBER_EXISTS(10008, "手机号已经被使用"),
    STU_NO_EXISTS(10009, "学生不存在"),
    PAY_ORDER_ERROR(10010, "支付下单失败"),
    CREATE_ORDER_ERROR(10011, "创建订单失败"),
    SEARCH_ORDER_ERROR(10012, "查询订单失败"),
    CARD_NOT_EXISTS(10013, "卡号不存在"),
    CARD_NOT_ASD(10015, "未到选课时间"),

    UPLOAD_FILE_ERROR(20008, "上传文件失败"),
    READ_PROPERTIES_ERROR(20007, "读取配置文件失败"),
    USER_ROLE_NULL(20010, "角色为空"),
    USER_ROLE_ERROR(20009, "角色不符"),
    PASSWORD_ERROR(20011, "旧密码错误"),
    Is_ARCHIVING(20012, "已归档"),
    NO_CLASS_FOUND(20013, "未分配任课权限"),
    PAY_NOT_ONLINE(20014, "功能暂未上线"),
    USER_LEAVE_SCHOOL(20015, "用户已离校"),


    ;

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
