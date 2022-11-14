package com.xunyunedu.pay.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.pay.pojo.CanteenPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.service.PayService;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author zhangyong
 */
@Component
public  class MyListener {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PayService payService;

    @RequestMapping("/zhifus")
    @ApiOperation(value = "支付发送", httpMethod = "GET")
    public void findByShouDong() throws InterruptedException {
        findByFaSong();
    }

    @Scheduled(fixedDelay = 20000)//20秒执行一次
    public void findByFaSong(){
        String fileName = "application.properties";
        String ip = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        String addres = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        //sendState = Boolean.parseBoolean(PropertiesUtil.getProperty(fileName, "send.canteen"));
        String url = ip + addres;
        List<PayOrderPojo> payOrderPojo =payService.findByAll();
        if(payOrderPojo.size()>0){
           for(int i=0;i<payOrderPojo.size();i++){
                try {
                    CanteenPojo p = new CanteenPojo();
                    p.setEmp_code(payOrderPojo.get(i).getEmpCode());
                    p.setEmp_name(payOrderPojo.get(i).getEmpName());
                    p.setEmp_card(payOrderPojo.get(i).getEmpCard());
                    p.setChange_money(payOrderPojo.get(i).getPayAmount());
                    p.setChange_flag(2);
                    p.setClock_id("999");
                    p.setCreatePerson(payOrderPojo.get(i).getEmpName());
                    p.setChange_why("充值");
                    p.setChange_type("微信线上");
                    Object obj = JSONArray.toJSON(p);
                    HttpRequestConfig config = HttpRequestConfig.create().url(url)
                            .addHeader("Content-Type", "application/json")
                            .httpEntityType(HttpEntityType.ENTITY_STRING)
                            .json(obj.toString());
                    HttpRequestResult httpRequestResult = HttpClientUtils.post(config);
                    log.debug("发送支付订单信息--返回信息: {}", httpRequestResult);
                    if (BaseConstant.SUCCESS.getMesg().equals(httpRequestResult.getCode())) {
                        String responseText = httpRequestResult.getResponseText();
                        if (!StrUtil.hasEmpty(responseText)) {
                            JSONObject jsonObject = JSONObject.parseObject(responseText);
                            String statusCode = jsonObject.getString("statusCode");
                            String result = jsonObject.getString("result");
                            if (!StrUtil.hasEmpty(statusCode, result)) {
                                if (BaseConstant.SUCCESS.getMesg().toString().equals(statusCode) && "true".equals(result)) {
                                  Integer asd=payService.updateASFAS(payOrderPojo.get(i).getId());
                                  if(asd>0){
                                      System.out.println("发送支付订单信息--食堂接口成功: {}"+payOrderPojo.get(i).getEmpName()+"-------"+ payOrderPojo.get(i).getOrderNumber());
                                  }else{
                                      log.info("修改失败");
                                  }

                                } else {
                                    log.error("发送支付订单信息--食堂接口失败：{}， 返回信息：{}"+payOrderPojo.get(i).getEmpName(),payOrderPojo.get(i).getOrderNumber(), httpRequestResult);
                                }
                            }
                        }
                    } else {
                        log.error("发送支付订单信息--http请求食堂接口失败: {}", httpRequestResult.getCode());
                    }
                } catch (Exception e) {
                    log.error("发送支付订单信息--食堂接口失败: {}", e.getMessage());
                }
            }

        }else {
            System.out.println("job 开始执行"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }

}