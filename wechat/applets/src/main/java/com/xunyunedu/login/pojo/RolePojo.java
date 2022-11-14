package com.xunyunedu.login.pojo;

import java.io.Serializable;

/**
 * 用户角色
 *
 * @author: yhc
 * @Date: 2020/11/8 18:29
 * @Description:
 */
public class RolePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学校Id
     */
    private Integer id;

    /**
     * 学校名称
     */
    private String name;

	/**
	 * Type 对应数据库：
	 * 1	教职工
	 * 2	管理员
	 * 3	家长
	 * 4	学生
	 */
	private String userType;


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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
