package com.xunyunedu.exception;

import lombok.Data;

import java.util.Map;

/**
 * 分页查询条件
 * @param <T>
 */
@Data
public class PageCondition<T> {
    /**
     * 查询条件
     */
    T condition;

    /**
     * 排序
     */
    Order order;
    /**
     * 查询第几页,默认1
     */
    Integer pageNum = 1;
    /**
     * 每页的大小，默认10
     */
    Integer pageSize = 10;



    @Data
    public static class Order{

        public String field;

        /**
         * true: 升序
         * false: 降序
         */
        private Boolean asc = true;

    }
}
