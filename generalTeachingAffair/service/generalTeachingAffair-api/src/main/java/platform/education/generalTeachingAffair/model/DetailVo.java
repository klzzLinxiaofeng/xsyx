package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/**
 * @author: yhc
 * @Date: 2020/9/24 9:20
 * @Description: 远程接口调用实体类
 */
public class DetailVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
    /**
     * 参数类型
     */
    private String datatype;

    public DetailVo(String name, String value, String datatype) {
        this.name = name;
        this.value = value;
        this.datatype = datatype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
}
