package com.xunyunedu.common.pojo;

import lombok.Data;

/**
 * 这个系统的Mybatis怎么那么操蛋
 */
@Data
public class SqlStatement {

    private String sql;

    public SqlStatement(String sql){
        this.sql=sql;
    }

}
