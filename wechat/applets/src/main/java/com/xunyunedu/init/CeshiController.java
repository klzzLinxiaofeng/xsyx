package com.xunyunedu.init;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.pay.pojo.CanteenPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;


public class CeshiController {
   //static final Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        String ip = PropertiesUtil.getProperty("application.properties", "139.159.242.158:8090");
        String addres = PropertiesUtil.getProperty("application.properties", "/api/meal/MealChangeMoney/add_PerRecharge");
          String  url = ip + addres;
        try {
            CanteenPojo p = new CanteenPojo();
            PayOrderPojo order=new PayOrderPojo();


            /*p.setEmp_code(order.getEmpCode());
            p.setEmp_name(order.getEmpName());
            p.setEmp_card(order.getEmpCard());*/

            p.setEmp_code("8610004076");
            p.setEmp_name("一年级(9)班");
            p.setEmp_card("1232223423");

//            p.setEmp_code("000013");
//            p.setEmp_name("姓名测试");
//            p.setEmp_card("0000131313");

            //PayAmountPojo amount = payOrderDao.getAmount(order.getPayAmountId());
            // 食堂接口以元为单位
            p.setChange_money(-0.1);
            p.setChange_flag(2);
            p.setClock_id("999");
            p.setCreatePerson("一年级(9)班"/*order.getEmpName()*/);
            p.setChange_why("充值");
            p.setChange_type("微信线上");
            Object obj = JSONArray.toJSON(p);
            HttpRequestConfig config = HttpRequestConfig.create().url(url)
                    .addHeader("Content-Type", "application/json")
                    .httpEntityType(HttpEntityType.ENTITY_STRING)
                    .json(obj.toString());
            HttpRequestResult httpRequestResult = HttpClientUtils.post(config);
            System.out.println("发送支付订单信息--返回信息: {}"+httpRequestResult);
            if (BaseConstant.SUCCESS.getMesg().equals(httpRequestResult.getCode())) {
                String responseText = httpRequestResult.getResponseText();
                if (!StrUtil.hasEmpty(responseText)) {
                    JSONObject jsonObject = JSONObject.parseObject(responseText);
                    String statusCode = jsonObject.getString("statusCode");
                    String result = jsonObject.getString("result");
                    if (!StrUtil.hasEmpty(statusCode, result)) {
                        if (BaseConstant.SUCCESS.getMesg().toString().equals(statusCode) && "true".equals(result)) {
                            System.out.println("发送支付订单信息--食堂接口成功: {}"+ order.getOrderNumber());
                            System.out.println(1);
                        } else {
                            System.out.println("发送支付订单信息--食堂接口失败：{}， 返回信息：{}"+order.getOrderNumber()+"---"+httpRequestResult);
                            System.out.println(0);
                        }
                    }
                }
            } else {
                System.out.println("发送支付订单信息--http请求食堂接口失败: {}"+httpRequestResult.getCode());
            }
        } catch (Exception e) {
            System.out.println("发送支付订单信息--食堂接口失败: {}"+e.getMessage());
        }
        System.out.println("edd"+0);

    }
}
