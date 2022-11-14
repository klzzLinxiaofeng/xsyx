package platform.szxyzxx.web.moral.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.core.pojo.BasicResult;
import platform.szxyzxx.dy.pojo.DyExcellentTeamRy;
import platform.szxyzxx.dy.service.DyExcellentTeamRyService;
import platform.szxyzxx.excelhelper.exception.CellResolveException;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.util.StringUtils;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 德育优秀班级-个人荣誉、集体荣誉
 */
@Controller
@RequestMapping("/dy/ex/ry")
public class DyExcellentTeamRyController {

    @Autowired
    private DyExcellentTeamRyService service;

    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<DyExcellentTeamRy> grimporter;
    private Importer<DyExcellentTeamRy> jtimporter;

    public DyExcellentTeamRyController(){
        ExcelImportParam<DyExcellentTeamRy> param=new ExcelImportParam<>(DyExcellentTeamRy.class);
        param.setStartRowIndex(3);
        grimporter=new DefaultImporter<>(param);

        ExcelImportParam<DyExcellentTeamRy> param2=new ExcelImportParam<>(DyExcellentTeamRy.class);
        param2.setStartRowIndex(2);
        jtimporter=new DefaultImporter<>(param2);

    }


    @RequestMapping("/index")
    public String index(Integer type,ModelMap modelMap,@CurrentUser UserInfo userInfo){
        boolean isAdmin=basicSQLService.findUniqueLong("select exists(SELECT 1 FROM yh_user_role ur LEFT JOIN yh_role r ON r.id = ur.role_id LEFT JOIN yh_user u ON u.id = ur.user_id WHERE(r.`name` = '学校管理员' or r.`name`='运营人员') and u.id="+userInfo.getId()+" AND ur.is_deleted = 0) e")>0;
        if(!isAdmin) {
            modelMap.put("userId", userInfo.getId());
        }
        modelMap.put("type",type);
        return view("index");
    }

    @RequestMapping("/list")
    @ResponseBody
    public StuVo list(Page page, DyExcellentTeamRy obj,@CurrentUser UserInfo userInfo){
        StringBuilder sql=new StringBuilder("select * from dy_excellent_team_ry where xn='").append(obj.getXn()).append("' and xq='").append(obj.getXq()).append("'").append(" and `type`=").append(obj.getType());
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(obj.getTeamName())){
            sql.append(" and team_name='"+obj.getTeamName()+"'");
        }
        if(obj.getCreatorId()!=null){
            sql.append(" and creator_id="+obj.getCreatorId());
        }
        
        sql.append(" order by create_time desc,team_name asc");
        List list=basicSQLService.findByPaging(page,sql.toString());
        StuVo s=new StuVo();
        s.setList(list);
        s.setPage(page);
        return s;
    }

    @RequestMapping("/addPage")
    public String addPage(ModelMap modelMap,Integer type){
        modelMap.put("type",type);
        return view("add");
    }

    @RequestMapping("add")
    @ResponseBody
    public BasicResult add(MultipartFile file, DyExcellentTeamRy obj,@CurrentUser UserInfo userInfo){
        try {
            List<Map<String,Object>> existsList=basicSQLService.find("select team_name,creator from dy_excellent_team_ry where xn='"+obj.getXn()+"' and xq='"+obj.getXq()+"' and `type`='"+obj.getType()+"'");
            Importer<DyExcellentTeamRy> importer=grimporter;
            if(obj.getType()==1){
                importer=jtimporter;
            }
            List<DyExcellentTeamRy> list=importer.importBy(file.getInputStream());
            Date now=new Date();
            BasicResult r= checkAndSetData(list,existsList);
            if(r!=null){
                return r;
            }
            for (DyExcellentTeamRy importObj : list) {
                if(importObj.getShengji()==null && importObj.getShiji()==null && importObj.getGjj()==null && importObj.getZj()==null && importObj.getXj()==null){
                    continue;
                }
                importObj.setType(obj.getType());
                importObj.setXn(obj.getXn());
                importObj.setXq(obj.getXq());
                importObj.setCreateTime(now);
                importObj.setCreatorId(userInfo.getId());
                importObj.setCreator(userInfo.getRealName());
                service.add(importObj);
            }
            return BasicResult.success();
        }catch (CellResolveException e){
            e.printStackTrace();
            return BasicResult.error("第"+(e.getRowIndex()+1)+"行第"+(e.getColIndex()+1)+"列解析失败，请检查数据类型");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return BasicResult.error("导入失败");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BasicResult delete(String ids){
        basicSQLService.update("delete from dy_excellent_team_ry where id in("+ids+")");
        return BasicResult.success();
    }
    
    private BasicResult checkAndSetData(List<DyExcellentTeamRy> list,List<Map<String,Object>> existsTeamList){
        for (DyExcellentTeamRy importObj : list) {
            //忽略掉”注“这一行
            if(importObj.getShengji()==null && importObj.getShiji()==null && importObj.getGjj()==null && importObj.getZj()==null && importObj.getXj()==null){
                continue;
            }

            if(importObj.getShengji()==null){
                importObj.setShengji(0);
            }
            if(importObj.getShiji()==null){
                importObj.setShiji(0);
            }
            if(importObj.getGjj()==null){
                importObj.setGjj(0);
            }
            if(importObj.getZj()==null){
                importObj.setZj(0);
            }
            if(importObj.getXj()==null){
                importObj.setXj(0);
            }

            importObj.setTeamName(importObj.getTeamName().trim());
            String teamName = importObj.getTeamName();
            if (StringUtils.isNotNumeric(teamName)) {
                return BasicResult.error("班级名称【"+teamName+"】格式有误，请使用班级数字，例如102,203");
            }
            importObj.setSumScore(importObj.getGjj()+importObj.getShengji()+importObj.getShiji()+importObj.getZj()+importObj.getXj());
            String creator=findCreatorByTeamName(existsTeamList,teamName);
            if(creator!=null){
                return BasicResult.error("【"+teamName+"】班的数据已被【"+creator+"】导入");
            }

        }
        return null;
    }

    private String findCreatorByTeamName(List<Map<String,Object>> teamList, String teamName){
        for (Map<String, Object> map : teamList) {
            if(map.get("team_name").equals(teamName)){
                return map.get("creator").toString();
            }
        }
        return null;
    }




    private String view(String pageName){
        return "/moral/excellentTeamRy/"+pageName;
    }

}
