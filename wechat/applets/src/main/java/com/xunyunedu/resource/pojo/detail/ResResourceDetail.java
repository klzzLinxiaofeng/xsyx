package com.xunyunedu.resource.pojo.detail;

import com.xunyunedu.resource.pojo.ResResource;
import lombok.Data;

@Data
public class ResResourceDetail extends ResResource {
    /**
     * 是否点赞
     */
    private Boolean isLiked;
    /**
     * 是否收藏
     */
    private Boolean isFaved;

}
