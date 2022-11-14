package com.xunyunedu.common.service.impl;

import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.service.UploaderService;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.common.pojo.FileResult;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @author: yhc
 * @Date: 2020/10/30 14:34
 * @Description:
 */
@Service
public class UploaderServiceImpl implements UploaderService {


    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    private UploaderDao uploaderDao;

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public FileResult upload(MultipartFile file,String fileName) throws IOException {
        if (file != null) {
            //小程序文件上传需要经过
            if(fileName==null || file.isEmpty()){
                fileName=file.getOriginalFilename();
            }
            FileResult result = ftpUtils.upload(file.getInputStream(), StringUtils.getFilenameExtension(fileName), file.getContentType(), fileName, "1");
            return result;
        }
        return null;
    }


    @Override
    public EntityFile add(EntityFile entityFile) {
        if (entityFile == null) {
            return null;
        } else {
            Date createDate = entityFile.getCreateDate();
            if (createDate == null) {
                createDate = new Date();
                entityFile.setCreateDate(createDate);
            }
            uploaderDao.create(entityFile);
            return entityFile;
        }
    }

}
