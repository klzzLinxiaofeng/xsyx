package com.xunyunedu.logger.vo;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:41
 * @Version 1.0
 */
public class LoggerPojo extends Loggers {
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
