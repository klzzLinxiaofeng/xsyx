package com.xunyunedu.mergin.service;


import com.xunyunedu.mergin.util.Page;

import java.util.List;
import java.util.Map;

public interface JcGcCacheService {
    List<Map<String, Object>> findByTableCodeWithLevel(String tableCode, String level, Page page, boolean isAsc);

}
