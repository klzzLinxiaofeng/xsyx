package platform.education.netdisk.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import platform.education.netdisk.entity.CatalogEntity;
import platform.education.netdisk.entity.FilesEntity;

import java.text.DecimalFormat;
import java.util.Date;

public class FilesViewVo {
    private Integer id;

    private String name;

    private String size;

    private Integer type;

    private Integer parentId;

    private String httpUrl;

//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date modifyDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public FilesViewVo(){
    }

    public FilesViewVo(FilesEntity filesEntity) {
        this.id = filesEntity.getId();
        this.name = filesEntity.getName();
        this.type = 1;
        this.parentId = filesEntity.getCatalogId();
        this.size = "0";
//        this.modifyDate = filesEntity.getModifyDate();
        this.createDate = filesEntity.getCreateDate();
    }

    public FilesViewVo(ResFileVo resFileVo) {
        this.id = resFileVo.getId();
        this.name = resFileVo.getName();
        this.type = 1;
        this.parentId = resFileVo.getCatalogId();
        this.size = resFileVo.getSize().toString();
//        this.modifyDate = resFileVo.getModifyDate();
        this.createDate = resFileVo.getCreateDate();

        int level = 0;
        String[] unit = new String[]{"B", "K", "M", "G"};
        double size = resFileVo.getSize();
        while (size > 1024){
            size /= 1024;
            level++;
        }
        DecimalFormat df =new java.text.DecimalFormat("#.0");
        this.size = df.format(size)+" "+unit[level];
    }

    public FilesViewVo(CatalogEntity catalogEntity) {
        this.id = catalogEntity.getId();
        this.name = catalogEntity.getName();
        this.type = 0;
        this.parentId = catalogEntity.getParentId();
        this.size = "-";
//        this.modifyDate = catalogEntity.getModifyDate();
        this.createDate = catalogEntity.getCreateDate();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

//    public Date getModifyDate() {
//        return modifyDate;
//    }
//
//    public void setModifyDate(Date modifyDate) {
//        this.modifyDate = modifyDate;
//    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public FilesViewVo setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
        return this;
    }
}
