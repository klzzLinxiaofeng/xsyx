package com.xunyunedu.banner.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xunyunedu.banner.dao.MicroBannerDao;
import com.xunyunedu.banner.pojo.MicroBanner;
import com.xunyunedu.banner.service.MicroBannerService;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author edison
 */
@Service
public class MicroBannerServiceImpl implements MicroBannerService {

    @Autowired
    MicroBannerDao dao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Override
    public List<MicroBanner> all() {
        List<MicroBanner> list = dao.all();
        for (MicroBanner microBanner : list) {
            String entityId = microBanner.getEntityId();
            if (!StrUtil.hasEmpty(entityId)) {
                EntityFile entityFile = uploaderDao.findFileByUUID(entityId);
                if (entityFile != null) {
                    microBanner.setHttUrl(ftpUtils.relativePath2HttpUrl(entityFile));
                }
            }
        }
        return list;
    }

    /**
     * 获取banner内容
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, String> getContent(Integer id) {
        return dao.getContent(id);
    }

}
