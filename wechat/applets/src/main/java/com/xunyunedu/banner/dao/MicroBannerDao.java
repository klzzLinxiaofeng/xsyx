package com.xunyunedu.banner.dao;

import com.xunyunedu.banner.pojo.MicroBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author edison
 */
public interface MicroBannerDao {

    List<MicroBanner> all();

    Map<String, String> getContent(@Param("id") Integer id);
}