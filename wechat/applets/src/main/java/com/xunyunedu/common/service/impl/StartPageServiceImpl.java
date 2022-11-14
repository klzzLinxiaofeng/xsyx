package com.xunyunedu.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xunyunedu.common.dao.StartPageDao;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.common.pojo.StartPagePojo;
import com.xunyunedu.common.service.StartPageService;
import com.xunyunedu.util.ftp.FtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 启动页管理
 *
 * @author: yhc
 * @Date: 2020/12/14 15:57
 * @Description:
 */
@Service
public class StartPageServiceImpl implements StartPageService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StartPageDao startPageDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;


    @Override
    public StartPagePojo find() {
        List<StartPagePojo> list = startPageDao.find();
        if (CollUtil.isNotEmpty(list)) {
            StartPagePojo startPagePojo = list.get(0);
            String uuid = startPagePojo.getUuid();
            if (uuid != null && !("").equals(uuid)) {
                // 根据uuid查询封面的url
                EntityFile file = uploaderDao.findFileByUUID(uuid);
                if (file != null) {
                    startPagePojo.setUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            return startPagePojo;
        }
        return null;
    }
}
