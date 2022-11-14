package com.xunyunedu.unhandle.pojo;

import lombok.Data;

@Data
public class QueryParam {

    /**
     * 当前用户id
     */
    private Integer userId;
    /**
     * 是否未处理，默认true
     */
    private Boolean unHandle=true;


}
