package platform.education.netdisk.vo;

import platform.education.netdisk.entity.FilesEntity;
import platform.service.storage.model.EntityFile;

import java.util.Date;

public class ResFileVo {
    FilesEntity file = new FilesEntity();

    EntityFile entityFile = new EntityFile();

    public ResFileVo() {
        this.file = new FilesEntity();
        this.entityFile = new EntityFile();
    }

    public Integer getId() {
        return file.getId();
    }

    public void setId(Integer id) {
        file.setId(id);
    }

    public String getName() {
        return file.getName();
    }

    public void setName(String name) {
        file.setName(name);
    }

    public String getUuid() {
        return file.getUuid();
    }

    public void setUuid(String uuid) {
        file.setUuid(uuid);
    }

    public Integer getCatalogId() {
        return file.getCatalogId();
    }

    public void setCatalogId(Integer catalogId) {
        file.setCatalogId(catalogId);
    }

    public Integer getOwnerId() {
        return file.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        file.setOwnerId(ownerId);
    }

    public Date getModifyDate() {
        return file.getModifyDate();
    }

    public void setModifyDate(Date modifyDate) {
        file.setModifyDate(modifyDate);
    }

    public Date getCreateDate() {
        return file.getCreateDate();
    }

    public void setCreateDate(Date createDate) {
        file.setCreateDate(createDate);
    }

    public Integer getSize() {
        return entityFile.getSize();
    }

    public void setSize(Integer size) {
        entityFile.setSize(size);
    }

    public String getMd5() {
        return entityFile.getMd5();
    }

    public void setMd5(String md5) {
        entityFile.setMd5(md5);
    }

    public String getExtension() {
        return entityFile.getExtension();
    }

    public void setExtension(String extension) {
        entityFile.setExtension(extension);
    }

    public String getContentType() {
        return entityFile.getContentType();
    }

    public void setContentType(String contentType) {
        entityFile.setContentType(contentType);
    }

    public String getFileName() {
        return entityFile.getFileName();
    }

    public void setFileName(String fileName) {
        entityFile.setFileName(fileName);
    }

    public String getDiskFileName() {
        return entityFile.getDiskFileName();
    }

    public void setDiskFileName(String diskFileName) {
        entityFile.setDiskFileName(diskFileName);
    }

    public String getThumbnailUrl() {
        return entityFile.getThumbnailUrl();
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        entityFile.setThumbnailUrl(thumbnailUrl);
    }

    public String getRelativePath() {
        return entityFile.getRelativePath();
    }

    public void setRelativePath(String relativePath) {
        entityFile.setRelativePath(relativePath);
    }
}
