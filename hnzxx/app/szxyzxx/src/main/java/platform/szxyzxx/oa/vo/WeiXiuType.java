package platform.szxyzxx.oa.vo;

import java.util.Date;

public class WeiXiuType {
    /*
    * id
    */
    private Integer id;
    /*
     * id  308
     */
    private Integer tableId;
    /*
     * id GB-BXLX
     */
    private String tableCode;
    /*
     * 维修类型
     */
    private String name;
    /*
     * id
     */
    private Integer value;
    /*
     * id
     */
    private Integer sortOrder;
    /*
     * id    00
     */
    private Integer level;
    /*
     * id   00
     */
    private Integer disable;
    /*
     * 维修类型
     */
    private String description;
    /*
     *
     */
    private Date create_date;
    /*
     *
     */
    private Date modify_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }
}
