package platform.education.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2017-03-14
 */
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        if(params.get("pageNum") != null){
            currPage = Integer.parseInt((String)params.get("pageNum"));
        }
        if(params.get("pageSize") != null){
            limit = Integer.parseInt((String)params.get("pageSize"));
        }

        this.put("offset", (currPage - 1) * limit);
        this.put("page", currPage);
        this.put("limit", limit);

//        防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
//        String sidx = SQLFilter.sqlInject((String)params.get("sidx"));
//        String order = SQLFilter.sqlInject((String)params.get("order"));
//        this.put("sidx", sidx);
//        this.put("order", order);

        //mybatis-plus分页
        this.page = new Page<>(currPage, limit);

        //排序
//        if(StringUtil.isEmpty(sidx) && StringUtil.isEmpty(order)){
//            this.page.setOrderByField(sidx);
//            this.page.setAsc("ASC".equalsIgnoreCase(order));
//        }

    }

    public Page<T> getPage() {
        return page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getLimit() {
        return limit;
    }
}
