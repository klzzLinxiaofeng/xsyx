package com.xunyunedu.screening.controller;


import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.screening.service.ScreeningService;
import com.xunyunedu.screening.vo.Screening;
import com.xunyunedu.util.ftp.FtpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Screening")
@Api(value = "/Screening", description = "资产排查发布")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private UploaderDao uploaderDao;
    @Autowired
    private FtpUtils ftpUtils;

    @RequestMapping("/findByAll")
    @ApiOperation(value = "资产排查记录", httpMethod = "GET")
    public Map<String,Object> findByAll(@RequestParam Integer userId) {
        List<Screening> screeningList  = screeningService.findByAll(userId);
        SimpleDateFormat decimalFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Screening aa:screeningList){
            aa.setPaichaTime(decimalFormat.format(aa.getPaichaTime2()));
        }
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> maps=basicSQLService.find("select * from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id where yur.user_id="+userId);
        Boolean flag=false;
        for(Map<String,Object> aa:maps){
            if(aa.get("code").toString().equals("ZICHAN_PAICHAYUAN")){
                flag=true;
            }
        }
        map.put("list",screeningList);
        map.put("flag",flag);
        return map;
    }

    @RequestMapping("/create")
    @ApiOperation(value = "资产排查申请", httpMethod = "POST")
    public ApiResult create(@RequestBody Screening screening) {
        int num = screeningService.create(screening);
        if(num>0){
            return new ApiResult(ResultCode.SUCCESS);
        }else{
            return new ApiResult(ResultCode.CREATE_FAIL);
        }
    }
    @RequestMapping("/findById")
            @ApiOperation(value = "资产排查详情", httpMethod = "GET")
    public Screening findById(@RequestParam Integer id) {
        Screening screening = screeningService.findById(id);
        SimpleDateFormat decimalFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        screening.setPaichaTime(decimalFormat.format(screening.getPaichaTime2()));
        List<Map<String,Object>> listw=new ArrayList<>();
        List<Map<String,Object>> userList=new ArrayList<>();
        String [] userIdarr=screening.getPaichaUserId().split(",");
        String [] userNamearr=screening.getPaichaUserName().split(",");
        for(int a = 0; a < userIdarr.length; a++){
            Map<String,Object> map=new HashMap<>();
            map.put("userId",userIdarr[a]);
            map.put("userName",userNamearr[a]);
            userList.add(map);
        }
        screening.setUser(userList);
        if(screening.getFujianUuid()!=null && !screening.getFujianUuid().equals("")) {
            String[] fujianarr = screening.getFujianUuid().split(",");
            for (int i = 0; i < fujianarr.length; i++) {
                Map<String,Object> map =new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(fujianarr[i]);
                if (file != null) {
                    map.put("fileName",file.getFileName());
                    map.put("url",ftpUtils.relativePath2HttpUrl(file));
                    listw.add(map);
                }
            }
        }
        screening.setList(listw);
       return screening;
    }

    /*
     * 排查人员列表
     */
    @RequestMapping("/paiChaRenYuan")
    public List<Map<String,Object>> findByTeacher(@RequestParam(value = "name",required = false) String name){
        String sql="select * from pj_teacher pt inner join yh_user_role yur on pt.user_id=yur.user_id inner join yh_role yr on yr.id=yur.role_id where yr.code='ZICHAN_PAICHAYUAN' and pt.is_delete=0 ";
        if(name!=null){
            sql+=" and pt.name like '%"+name+"%'";
        }
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }

}
