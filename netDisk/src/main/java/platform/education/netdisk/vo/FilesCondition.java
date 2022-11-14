package platform.education.netdisk.vo;

import platform.education.netdisk.entity.FilesEntity;

import java.util.Date;

public class FilesCondition {
    private FilesEntity filesEntity = new FilesEntity();

    private int type;

    public FilesCondition() {
        this.filesEntity = new FilesEntity();
    }

    public FilesCondition(FilesEntity filesEntity) {
        this.filesEntity = filesEntity;
    }

    public Integer getKey() {
        return filesEntity.getKey();
    }

    public Integer getId() {
        return filesEntity.getId();
    }

    public FilesCondition setId(Integer id) {
        filesEntity.setId(id);
        return this;
    }

    public String getName() {
        return filesEntity.getName();
    }

    public void setName(String name) {
        filesEntity.setName(name);
    }

    public String getUuid() {
        return filesEntity.getUuid();
    }

    public void setUuid(String uuid) {
        filesEntity.setUuid(uuid);
    }

    public Integer getCatalogId() {
        return filesEntity.getCatalogId();
    }

    public void setCatalogId(Integer catalogId) {
        filesEntity.setCatalogId(catalogId);
    }

    public Integer getOwnerId() {
        return filesEntity.getOwnerId();
    }

    public void setOwnerId(Integer ownerId) {
        filesEntity.setOwnerId(ownerId);
    }

    public Date getModifyDate() {
        return filesEntity.getModifyDate();
    }

    public void setModifyDate(Date modifyDate) {
        filesEntity.setModifyDate(modifyDate);
    }

    public Date getCreateDate() {
        return filesEntity.getCreateDate();
    }

    public void setCreateDate(Date createDate) {
        filesEntity.setCreateDate(createDate);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
