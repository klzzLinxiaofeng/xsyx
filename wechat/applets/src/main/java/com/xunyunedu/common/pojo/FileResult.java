package com.xunyunedu.common.pojo;

import java.io.Serializable;

/**
 * 返回信息
 *
 *  @author: yhc
 *  @Date: 2020/10/15 17:23
 *  @Description:
 */
public class FileResult implements Serializable {
    private static final long serialVersionUID = 3498757248347895867L;
    /**
     * 文件实体
     */
    private EntityFile entityFile;
    private String statusCode;
    private long tempFileSize;
    private String httpUrl;

    public FileResult() {
    }

    public FileResult(String statusCode) {
        this.statusCode = statusCode;
    }

    public FileResult(EntityFile entityFile, String statusCode) {
        this.entityFile = entityFile;
        this.statusCode = statusCode;
    }

    public EntityFile getEntityFile() {
        return this.entityFile;
    }

    public void setEntityFile(EntityFile entityFile) {
        this.entityFile = entityFile;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public long getTempFileSize() {
        return this.tempFileSize;
    }

    public void setTempFileSize(long tempFileSize) {
        this.tempFileSize = tempFileSize;
    }

    public String getHttpUrl() {
        return this.httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Override
    public String toString() {
        return "FileResult{entityFile=" + this.entityFile + ", statusCode='" + this.statusCode + '\'' + ", tempFileSize=" + this.tempFileSize + ", httpUrl='" + this.httpUrl + '\'' + '}';
    }
}
