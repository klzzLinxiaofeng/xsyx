package com.xunyunedu.common.service;

import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.common.pojo.FileResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *  @author: yhc
 *  @Date: 2020/10/30 14:34
 *  @Description:
 */
public interface UploaderService {

    FileResult upload(MultipartFile file,String fileName) throws IOException;

    EntityFile add(EntityFile entityFile);

}
