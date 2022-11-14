package com.xunyunedu;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

public class TestStree {
    public static void main(String[] args) {
        HttpServletRequest req=null;
       String str= test03(req);
        System.out.println(str);
    }
    public static String test03(HttpServletRequest req){
        System.out.println(1234);
        // 存放在二维码中的内容
        String text = "我是小铭";
        // 嵌入二维码的图片路径
       // String imgPath = "G:/qrCode/dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "E:/qrCode/jam1.jpg";
        //生成二维码
        try {
            QRCodeUtil.encode(text, null, destPath, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析二维码
        String str = null;
        try {
            str = QRCodeUtil.decode(destPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打印出解析出的内容
        return str;
    }
    @Test
    public void sdfw(){
        String asd="2022-03-14 00:00:00";
        System.out.println(asd.substring(0,10));//2019-3-16 下午20:59:38

    }

}
