package com.xunyunedu.banner.service;

import com.xunyunedu.banner.pojo.MicroBanner;

import java.util.List;
import java.util.Map;

public interface MicroBannerService {
    List<MicroBanner> all();

    Map<String, String> getContent(Integer id);
}
