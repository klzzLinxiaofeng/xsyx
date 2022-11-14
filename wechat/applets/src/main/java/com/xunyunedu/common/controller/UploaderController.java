package com.xunyunedu.common.controller;

import com.xunyunedu.common.service.UploaderService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.common.pojo.FileResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 附件文件上传接口
 *
 * @author: yhc
 * @Date: 2020/10/30 14:30
 * @Description:
 */
@RequestMapping("/common")
@RestController
public class UploaderController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UploaderService uploaderService;

    /**
     * 文件上传
     * @param file 文件
     * @param fileName 指定文件名称，某些情况下文件名不是真实的名称，需要传参指定
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public ApiResult<FileResult> upload(@RequestParam(value = "file") MultipartFile file,@RequestParam(required = false) String fileName) {
        if (file == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        FileResult result;
        try {
            result = uploaderService.upload(file,fileName);
        } catch (IOException e) {
            log.error("上传文件失败");
            throw new BusinessRuntimeException(ResultCode.UPLOAD_FILE_ERROR);
        }
        return new ApiResult(ResultCode.SUCCESS, result);
    }


    /**
     * 批量文件上传
     *
     * @param files
     * @param fileNames 文件名称拼接后的字符，用英文逗号分隔
     * @return
     */
    @PostMapping("/batch/upload")
    @ResponseBody
    public ApiResult batchUpload(@RequestParam(value = "file") MultipartFile[] files,@RequestParam(value = "fileNames")String fileNames) {
        if (files == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String[] nameArr=null;
        if(fileNames!=null){
            nameArr=fileNames.split(",");
            if(nameArr.length!=files.length){
                ApiResult a=new ApiResult();
                a.setMsg("fileNames参数分割后需要和文件个数一致");
                a.setCode(400);
                return a;
            }
        }

        List<FileResult> list = new ArrayList<>(files.length);
        for (int i = 0; i < files.length; i++) {

            try {
                String fileName = null;
                if (nameArr != null) {
                    fileName = nameArr[i];
                }
                FileResult result = uploaderService.upload(files[i], fileName);
                if (result != null) {
                    list.add(result);
                }
            }catch(IOException e){
                log.error("上传文件失败");
                throw new BusinessRuntimeException(ResultCode.UPLOAD_FILE_ERROR);
            }
        }
        return new ApiResult(ResultCode.SUCCESS, list);
    }
}
