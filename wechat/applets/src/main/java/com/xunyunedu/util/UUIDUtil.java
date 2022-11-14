package com.xunyunedu.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author: yhc
 * @Date: 2020/10/15 17:33
 * @Description:
 */
public class UUIDUtil {
    public UUIDUtil() {
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String result = uuid.toString();
        result = result.toLowerCase().replaceAll("-", "");
        return result;
    }

    /**
     * 生成唯一的主键 随机数
     * 格式 ： 时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
