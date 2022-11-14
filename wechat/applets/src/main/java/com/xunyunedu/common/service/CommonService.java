package com.xunyunedu.common.service;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/1/21 15:51
 * @Description:
 */
public interface CommonService {

    List<Map<String, Integer>> getValueByCode(String code);
}
