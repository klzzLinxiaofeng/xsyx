package com.xunyunedu.paper.condition;

import lombok.Data;

@Data
public class OaPaperCondition {
    /**
     * 当前登录用户id
     */
    Integer currUserId;

    /**
     * 0：与我相关，1：我发布的
     */
    Integer dataType;

}
