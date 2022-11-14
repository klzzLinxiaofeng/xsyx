package com.xunyunedu.unhandle.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.unhandle.pojo.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 待办事项
 */
@RestController
@RequestMapping("/oa/unHandle")
public class UnHandleController {

    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 分页查询待办
     * @param condition
     * @return
     */
    @PostMapping("/page")
    public ApiResult pageList(@RequestBody PageCondition<QueryParam> condition){
        ApiResult apiResult = ApiResult.of(ResultCode.SUCCESS);

        String sql="select title,applicant_name as applicantName,apply_explain as applyExplain,apply_result as applyResult,data_id_type as dataIdType,data_id as dataId,create_date as createDate,handle_date as handleDate from oa_apply_notice where receiver_id="+condition.getCondition().getUserId();
        if(condition.getCondition().getUnHandle()){
            sql+=" and apply_result=0";
        }else{
            sql+=" and apply_result=1 or apply_result=2";
        }
        sql+=" order by create_date desc";
        PageInfo<Map<String,Object>> page=basicSQLService.findByPaging(sql,condition.getPageNum(),condition.getPageSize());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String,Object>> list=page.getList();
        for (Map<String, Object> map : list) {
            Date createDate=(Date) map.get("createDate");
            Date handleDate=(Date) map.get("handleDate");
            if(createDate!=null){
                map.put("createDate",sdf.format(createDate));
            }
            if(handleDate!=null){
                map.put("handleDate",sdf.format(handleDate));
            }
        }
        apiResult.setData(page);
        return apiResult;
    }

}
