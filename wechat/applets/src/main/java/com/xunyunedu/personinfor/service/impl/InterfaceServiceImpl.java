package com.xunyunedu.personinfor.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xunyunedu.init.InitHandler;
import com.xunyunedu.personinfor.pojo.LibraryVo;
import com.xunyunedu.personinfor.service.InterfaceService;
import com.xunyunedu.util.file.ImageUtils;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 接口调用
 *
 * @author: yhc
 * @Date: 2020/11/23 10:34
 * @Description:
 */
@Service
public class InterfaceServiceImpl implements InterfaceService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FtpUtils ftpUtils;


    @Override
    public LibraryVo getData(String url) {
        try {
            HttpRequestConfig config = HttpRequestConfig.create().url(url)
                    .addHeader("Content-Type", "application/json");
            HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
            String responseText = httpRequestResult.getResponseText();
            if (!StrUtil.hasEmpty(responseText)) {
                return getPicUrl(responseText);
            }
        } catch (Exception e) {
            log.error("请求接口失败,url: {}, 失败{}", url, e);
        }
        return null;
    }

    /**
     * 获取图片和作者
     *
     * @param responseText
     * @return
     */
    public LibraryVo getPicUrl(String responseText) {
        LibraryVo vo = JSON.parseObject(responseText, new TypeReference<LibraryVo>() {
        });
        Integer status = vo.getStatus();
        if (status == 200) {
            LibraryVo.ResponseBean response = vo.getResponse();
            if (response != null) {
                List<LibraryVo.ResponseBean.DataBean> data = response.getData();
                if (CollUtil.isNotEmpty(data)) {
                    for (int i = 0; i < data.size(); i++) {
                        LibraryVo.ResponseBean.DataBean dataBean = data.get(i);
                        String bookTitle = dataBean.getBookTitle();
                        // 获取图片和作者 图书馆上面的接口中不提供图片和作者
                        if (StrUtil.isNotEmpty(bookTitle)) {
                            String libraryIp = InitHandler.urlParam.get("libraryIp");
                            String url = libraryIp + InitHandler.urlParam.get("libraryPicUrl");
                            try {
                                HttpRequestConfig config = HttpRequestConfig.create().url(url + "?page=1&key=" + bookTitle + "&pageSize=1")
                                        .addHeader("Content-Type", "application/json");
                                HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
                                String text = httpRequestResult.getResponseText();
                                if (!StrUtil.hasEmpty(text)) {
                                    LibraryVo libraryPicVo = JSON.parseObject(text, new TypeReference<LibraryVo>() {
                                    });
                                    if (status == 200) {
                                        LibraryVo.ResponseBean bean = libraryPicVo.getResponse();
                                        if (bean != null) {
                                            List<LibraryVo.ResponseBean.DataBean> listData = bean.getData();
                                            if (CollUtil.isNotEmpty(listData)) {
                                                // 图书馆返回的图片地址无法外网访问，所以后台将图片上传到我们服务器，访问自己服务器地址
                                                dataBean.setPic(ImageUtils.image2Base64(libraryIp + listData.get(0).getPic()));
                                                dataBean.setAuthor(listData.get(0).getAuthor());
                                            }
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                log.error("请求图书馆--获取图片地址失败！{}", e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        return vo;
    }

}
