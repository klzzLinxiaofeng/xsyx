package platform.education.netdisk.common;


public enum ResultEnum {
    //未知错误

    //默认成功
    /**
     * SUCCESS    成功
     * <p>
     * ERROR_301		TOKEN失效，需要重新登录
     * ERROR_400  	请求无效
     * ERROR_401  	未授权访问
     * ERROR_404  	请求的网页/路径不存在
     * ERROR_500  	未知错误
     * SUCCESS_201	操作异常,需要进行验证
     */
    SUCCESS(200000, "成功"),
    ERROR_201(201000, "操作异常,需要进行验证"),
    ERROR_301(301000, "身份信息失效:请重新登录"),
    ERROR_302(302000, "账号已经被锁定，请联系管理员"),
    ERROR_400(400000, "参数错误"),
    ERROR_401(401000, "未授权访问"),
    ERROR_404(404000, "请求的网页/路径不存在"),
    ERROR_500(500000, "系统错误:请联系管理员");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
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
