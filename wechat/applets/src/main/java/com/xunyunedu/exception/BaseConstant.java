package com.xunyunedu.exception;

/**
 * 常用量字段
 *
 * @author: yhc
 * @Date: 2020/10/27 10:55
 * @Description:
 */
public enum BaseConstant {
    SUCCESS(200),
    APPLETS_TYPE_EDIT( "UPDATE_APPLETS_STUDENT")
    ;

    BaseConstant(Object mesg){
        this.mesg = mesg;
    }

    private Object mesg;

    public Object getMesg(){
        return mesg;
    }
}
