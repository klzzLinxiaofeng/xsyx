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
import platform.szxyzxx.dy.pojo.DyExcellentTeamNjpj;
import platform.szxyzxx.dy.service.DyExcellentTeamNjpjService;
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
 * 德育优秀班级-年级评价
 */
@Controller
@RequestMapping("/dy/ex/bjpj")
public class DyExcellentTeamNjpjController {

    @Autowired
    private DyExcellentTeamNjpjService service;

    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<DyExcellentTeamNjpj> importer;

    public DyExcellentTeamNjpjController(){
        ExcelImportParam<DyExcellentTeamNjpj> param=new ExcelImportParam<>(DyExcellentTeamNjpj.class);
        param.setStartRowIndex(3);
        importer=new DefaultImporter<>(param);
    }


    @RequestMapping("/index")
    public String index(ModelMap modelMap,@CurrentUser UserInfo userInfo){
        boolean isAdmin=basicSQLService.findUniqueLong("select exists(SELECT 1 FROM yh_user_role ur LEFT JOIN yh_role r ON r.id = ur.role_id LEFT JOIN yh_user u ON u.id = ur.user_id WHERE(r.`name` = '学校管理员' or r.`name`='运营人员') and u.id="+userInfo.getId()+" AND ur.is_deleted = 0) e")>0;
        if(!isAdmin) {
            modelMap.put("userId", userInfo.getId());
        }
        return view("index");
    }
    @RequestMapping("/list")
    @ResponseBody
    public StuVo list(Page page, DyExcellentTeamNjpj yscg){
        StringBuilder sql=new StringBuilder("select * from dy_excellent_team_njpj where xn='").append(yscg.getXn()).append("' and xq='").append(yscg.getXq()).append("'");
        if(yscg.getXqy()!=null){
            sql.append(" and xqy="+yscg.getXqy());
        }
        if(yscg.getXqz()!=null){
            sql.append(" and xqz="+yscg.getXqz());
        }
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(yscg.getTeamName())){
            sql.append(" and team_name='"+yscg.getTeamName()+"'");
        }
        if(yscg.getCreatorId()!=null){
            sql.append(" and creator_id="+yscg.getCreatorId());
        }
        sql.append(" order by create_time desc,team_name asc");
        List list=basicSQLService.findByPaging(page,sql.toString());
        StuVo s=new StuVo();
        s.setList(list);
        s.setPage(page);
        return s;
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return view("add");
    }

    @RequestMapping("add")
    @ResponseBody
    public BasicResult add(MultipartFile file, DyExcellentTeamNjpj yscg,@CurrentUser UserInfo userInfo){
        try {
            List<Map<String,Object>> existsList=basicSQLService.find("select team_name,creator from dy_excellent_team_njpj where xn='"+yscg.getXn()+"' and xq='"+yscg.getXq()+"' and xqz='"+yscg.getXqz()+"'");
            List<DyExcellentTeamNjpj> list=importer.importBy(file.getInputStream());
            Date now=new Date();
            BasicResult r=checkData(list,existsList);
            if(r!=null){
                return r;
            }
            for (DyExcellentTeamNjpj dyExcellentTeamNjpj : list) {
                if(dyExcellentTeamNjpj.getWspj()==null || dyExcellentTeamNjpj.getWhjspj()==null){
                    continue;
                }
                dyExcellentTeamNjpj.setXn(yscg.getXn());
                dyExcellentTeamNjpj.setXq(yscg.getXq());
                dyExcellentTeamNjpj.setXqz(yscg.getXqz());
                dyExcellentTeamNjpj.setXqy(yscg.getXqy());
                dyExcellentTeamNjpj.setCreateTime(now);
                dyExcellentTeamNjpj.setCreatorId(userInfo.getId());
                dyExcellentTeamNjpj.setCreator(userInfo.getRealName());
                dyExcellentTeamNjpj.setSumScore(dyExcellentTeamNjpj.getWspj()+dyExcellentTeamNjpj.getWhjspj());
                service.add(dyExcellentTeamNjpj);
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
        basicSQLService.update("delete from dy_excellent_team_njpj where id in("+ids+")");
        return BasicResult.success();
    }
    
    private BasicResult checkData(List<DyExcellentTeamNjpj> list,List<Map<String,Object>> existsTeamList){
        for (DyExcellentTeamNjpj dyExcellentTeamNjpj : list) {
            if(dyExcellentTeamNjpj.getWhjspj()!=null || dyExcellentTeamNjpj.getWspj()!=null) {
                dyExcellentTeamNjpj.setTeamName(dyExcellentTeamNjpj.getTeamName().trim());
                String teamName = dyExcellentTeamNjpj.getTeamName();
                if (StringUtils.isNotNumeric(teamName)) {
                    return BasicResult.error("班级名称【"+teamName+"】格式有误，请使用班级数字，例如102,203");
                }
                String creator=findCreatorByTeamName(existsTeamList,teamName);
                if(creator!=null){
                    return BasicResult.error("【"+teamName+"】班的数据已被【"+creator+"】导入");
                }
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
        return "/moral/excellentTeamNjpj/"+pageName;
    }

}
