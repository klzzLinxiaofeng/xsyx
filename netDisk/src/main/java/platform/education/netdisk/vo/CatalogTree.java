package platform.education.netdisk.vo;

import platform.education.netdisk.entity.CatalogEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatalogTree {
    CatalogEntity catalog = new CatalogEntity();

    List<CatalogTree> children;

    public CatalogTree() {
        this.catalog = new CatalogEntity();
    }

    public CatalogTree(CatalogEntity catalog) {
        if(catalog == null){
            this.catalog = new CatalogEntity();
        } else {
            this.catalog = catalog;
        }
    }

    public Integer getKey() {
        return catalog.getKey();
    }

    public CatalogEntity getCatalogEntity() {
        return catalog;
    }

    public Integer getId() {
        return catalog.getId();
    }

    public void setId(Integer id) {
        catalog.setId(id);
    }

    public Integer getParentId() {
        return catalog.getParentId();
    }

    public void setParentId(Integer parentId) {
        catalog.setParentId(parentId);
    }

    public String getName() {
        return catalog.getName();
    }

    public void setName(String name) {
        catalog.setName(name);
    }

//    public Integer getOwnerId() {
//        return catalog.getOwnerId();
//    }
//
//    public void setOwnerId(Integer ownerId) {
//        catalog.setOwnerId(ownerId);
//    }

//    public Integer getPublished() {
//        return catalog.getPublished();
//    }
//
//    public void setPublished(Integer published) {
//        catalog.setPublished(published);
//    }
//
//    public Date getModifyDate() {
//        return catalog.getModifyDate();
//    }
//
//    public void setModifyDate(Date modifyDate) {
//        catalog.setModifyDate(modifyDate);
//    }
//
//    public Date getCreateDate() {
//        return catalog.getCreateDate();
//    }
//
//    public void setCreateDate(Date createDate) {
//        catalog.setCreateDate(createDate);
//    }

    public List<CatalogTree> getChildren() {
        return children;
    }

    public void setChildren(List<CatalogTree> children) {
        this.children = children;
    }

    public void addChildren(CatalogTree child) {
        if(this.children == null){
            this.children = new ArrayList<CatalogTree>();
        }
        this.children.add(child);
    }
}
