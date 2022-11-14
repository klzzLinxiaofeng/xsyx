package platform.szxyzxx.web.common.vo;

import java.io.Serializable;
import java.util.Date;

public class EntityFileVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String url;
    private String realFileName;
    private String fileName;
    private Integer size;
    private String suffix;
    private Date createDate;
    private String thumbnailUrl;
    private String md5Code;
    private Integer finishedFlag ;
    private String uuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    public Integer getFinishedFlag() {
        return finishedFlag;
    }

    public void setFinishedFlag(Integer finishedFlag) {
        this.finishedFlag = finishedFlag;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
}
