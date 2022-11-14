package com.xunyunedu.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息
 *
 * @author: yhc
 * @Date: 2020/10/15 17:21
 * @Description:
 */
public class EntityFile implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件uuid
     */
    private String uuid;
    private String extension;
    private String contentType;
    private String fileName;
    private String diskFileName;
    private String thumbnailUrl;
    private String relativePath;
    private Date createDate;

    public EntityFile() {
    }

    public EntityFile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDiskFileName() {
        return this.diskFileName;
    }

    public void setDiskFileName(String diskFileName) {
        this.diskFileName = diskFileName;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getRelativePath() {
        return this.relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
        result = 31 * result + (this.md5 == null ? 0 : this.md5.hashCode());
        result = 31 * result + (this.uuid == null ? 0 : this.uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            EntityFile other = (EntityFile) obj;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }

            if (this.md5 == null) {
                if (other.md5 != null) {
                    return false;
                }
            } else if (!this.md5.equals(other.md5)) {
                return false;
            }

            if (this.uuid == null) {
                if (other.uuid != null) {
                    return false;
                }
            } else if (!this.uuid.equals(other.uuid)) {
                return false;
            }

            return true;
        }
    }

    @Override
    public String toString() {
        return "EntityFile [id=" + this.id + ", size=" + this.size + ", md5=" + this.md5 + ", uuid=" + this.uuid + ", extension=" + this.extension + ", contentType=" + this.contentType + ", fileName=" + this.fileName + ", diskFileName=" + this.diskFileName + ", thumbnailUrl=" + this.thumbnailUrl + ", relativePath=" + this.relativePath + ", createDate=" + this.createDate + "]";
    }

}
