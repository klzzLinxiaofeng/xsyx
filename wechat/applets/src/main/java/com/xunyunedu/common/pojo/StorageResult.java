package com.xunyunedu.common.pojo;

/**
 *  @author: yhc
 *  @Date: 2020/10/15 17:42
 *  @Description:
 */
public class StorageResult {
    private String statusCode;
    private boolean success;
    private int partNumber = 0;
    private boolean chunk = false;
    private String fileName;
    private String filenameExtension;
    private long length = 0L;
    private String fullPath;
    private String relativePath;

    public StorageResult(boolean success, String statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getFilenameExtension() {
        return this.filenameExtension;
    }

    public void setFilenameExtension(String filenameExtension) {
        this.filenameExtension = filenameExtension;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullPath() {
        return this.fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getRelativePath() {
        return this.relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public boolean isChunk() {
        return this.chunk;
    }

    public void setChunk(boolean chunk) {
        this.chunk = chunk;
    }
}
