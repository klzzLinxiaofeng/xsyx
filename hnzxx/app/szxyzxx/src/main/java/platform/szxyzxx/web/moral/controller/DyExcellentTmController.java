package platform.szxyzxx.web.moral.controller;

import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.core.pojo.BasicResult;
import platform.szxyzxx.dy.pojo.DyExcellentTm;
import platform.szxyzxx.dy.service.DyExcellentTmService;
import platform.szxyzxx.excelhelper.exception.CellResolveException;
import platform.szxyzxx.util.PageUtils;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 德育-优秀班主任controller
 */
@Controller
@RequestMapping("/dy/excellentTm")
public class DyExcellentTmController {

    private Logger logger= LoggerFactory.getLogger(DyExcellentTmController.class);

    @Autowired
    BasicSQLService basicSQLService;
    @Autowired
    private DyExcellentTmService service;
    @Autowired
    @Qualifier("fileService")
    private FileService fileService;

    @RequestMapping("/applyIndex")
    public String applyIndex(@CurrentUser UserInfo userInfo, ModelMap modelMap){
        Integer userId=userInfo.getId();
        String teamName=getUserTeamNum(userId);
        if(teamName==null){
            return "/moral/student_week_star/403";
        }
        modelMap.put("filePrefix",fileService.getHttpPrefix() + "/" + fileService.getSpaceName());
        modelMap.put("teamName",teamName);
        modelMap.put("userId",userId);
        return view("index");
    }


    @RequestMapping("/preHandleIndex")
    public String preHandleIndex(ModelMap modelMap){
        modelMap.put("filePrefix",fileService.getHttpPrefix() + "/" + fileService.getSpaceName());
        return view("preHandleIndex");
    }

    @RequestMapping("/finalHandleIndex")
    public String finalHandleIndex(ModelMap modelMap){
        modelMap.put("filePrefix",fileService.getHttpPrefix() + "/" + fileService.getSpaceName());
        return view("finalHandleIndex");
    }

    @RequestMapping("/complete")
    @ResponseBody
    public BasicResult completeFinal(String year,String xq){
        try {
            if(basicSQLService.findUniqueLong("select exists( select 1 from dy_excellent_tm where year='"+year+"' and xq='"+xq+"' and first_state=1 and final_state=0)")==0){
                return BasicResult.error("请勿重复操作");
            }
            basicSQLService.update("update dy_excellent_tm set final_state=2 where year='"+year+"' and xq='"+xq+"' and first_state=1 and final_state=0");
            return BasicResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("操作失败");
        }
    }



    @RequestMapping("/pass")
    @ResponseBody
    public synchronized BasicResult pass(String year,String xq, Integer num,Integer type){
        try {

            if(xqPassed(year,xq,type==0?"first_state":"final_state")){
                return BasicResult.error("该学期已"+(type==0?"初审":"终审")+"，请勿重复操作");
            }

            Object sum=basicSQLService.findUnique("select sum_score from dy_excellent_tm where year='"+year+"' and xq='"+xq+"' and first_state=0 and sum_score is not null order by sum_score desc limit "+(num-1)+",1");
            String cv1="first_state=1";
            String cv2="first_state=2";
            if(type==1){
                cv1="final_state=1";
                cv2="final_state=2";
            }
            if(sum!=null){
                int sumScore=Integer.parseInt(sum.toString());
                basicSQLService.update("update dy_excellent_tm set "+cv1+" where year='"+year+"' and xq='"+xq+"' and first_state=0 and sum_score is not null and sum_score>="+sumScore);
                basicSQLService.update("update dy_excellent_tm set "+cv2+" where year='"+year+"' and xq='"+xq+"' and first_state=0 and sum_score is not null and sum_score<"+sumScore);
            }
            return BasicResult.success();
        }catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("操作失败");
        }
    }


    @RequestMapping("/addIndex")
    public String addIndex(){
        return view("add");
    }


    @RequestMapping("/updateScoreIndex")
    public String updateScoreIndex(ModelMap modelMap,String id){
        modelMap.put("id",id);
        return view("updateScore");
    }

    @RequestMapping("/list")
    @ResponseBody
    public StuVo list(Page page, String queryId,String previousQueryId, DyExcellentTm tm, HttpSession session){
        List<Map<String,Object>> list=null;
        if(tm.getUserId()==null){
            String sessionKey="exTm"+queryId;
            List<Map<String,Object>> cache=(List<Map<String, Object>>) session.getAttribute(sessionKey);
            if(cache==null){
                cache= service.getAll(tm);
                session.setAttribute(sessionKey,cache);
            }
            list=cache;
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(previousQueryId)){
                session.removeAttribute(previousQueryId);
            }
        }else{
            list=service.getAll(tm);
        }
        return PageUtils.pagingGetStuVoByList(list,page);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BasicResult delete( Integer id,@CurrentUser UserInfo user){
        try {
            DyExcellentTm d= service.findById(id);
            if(d==null || !d.getUserId().equals(user.getId())){
                return BasicResult.error("参数有误");
            }

            if(d.getSumScore()!=null){
                return BasicResult.error("申请已打分，无法删除");
            }
            service.delete(id);
            return BasicResult.success();
        }catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("删除失败");
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public BasicResult add( DyExcellentTm tm,@CurrentUser UserInfo user){
        try {
            if(xqPassed(tm.getYear(),tm.getXq(),"first_state")){
                return BasicResult.error("该学期优秀班主任已评审，无法再申请");
            }
            String teamName=getUserTeamNum(user.getId());
            if(teamName==null){
                return BasicResult.error("不是班主任，无权操作");
            }

            if(basicSQLService.findUniqueLong("select exists(select 1 from dy_excellent_tm where user_team='"+teamName+"' and year='"+tm.getYear()+"' and xq='"+tm.getXq()+"')")>0){
                return BasicResult.error("【"+teamName+"班】已申请，请勿重复申请");
            }
            tm.setUserTeam(teamName);
            tm.setCreateTime(new Date());
            tm.setUserId(user.getId());
            tm.setUserName(user.getRealName());
            service.add(tm);
            return BasicResult.success();
        }catch (CellResolveException e){
            return BasicResult.error("第"+(e.getRowIndex()+1)+"行第"+(e.getColIndex()+1)+"列解析失败，请检查数据类型");
        }catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("申请失败");
        }
    }


    @RequestMapping("/updateToFinalPass")
    @ResponseBody
    public BasicResult updateToFinalPass(DyExcellentTm tm,@CurrentUser UserInfo userInfo){
        try {

            tm.setFinalHandleUserId(userInfo.getId());
            tm.setFinalHandleUserName(userInfo.getRealName());
            tm.setFinalHandleTime(new Date());
            service.update(tm);
            return BasicResult.success();
        }catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("操作失败");
        }
    }

    @RequestMapping("/updateScore")
    @ResponseBody
    public BasicResult updateScore(DyExcellentTm tm,@CurrentUser UserInfo userInfo){
        try {
            tm.setFirstHandleUserId(userInfo.getId());
            tm.setFinalHandleTime(new Date());
            tm.setFirstHandleUserName(userInfo.getRealName());
            service.update(tm);
            return BasicResult.success();
        }catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("操作失败");
        }
    }


    @RequestMapping("/upload")
    @ResponseBody
    public BasicResult uploadFile(MultipartFile file){
        try {
            String uploadFileName = file.getOriginalFilename();
            FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
            return BasicResult.success(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BasicResult.error("上传失败");
    }

    private String view(String pageName){
        return "/moral/excellentTm/"+pageName;
    }

    private boolean xqPassed(String year,String xq,String column){
        return basicSQLService.findUniqueLong("select exists(select 1 from dy_excellent_tm where year='"+year+"' and xq='"+xq+"' and "+column+"!=0 )")>0;
    }


    private String getUserTeamNum(Integer userId){
        String sql="select g.grade_number,tm.team_number from  pj_team_teacher tt inner join pj_team tm on tm.id=tt.team_id inner join pj_grade g on g.id=tm.grade_id where tt.user_id="+userId+" and tt.type=1 and tt.is_delete=0";
        List<Map<String,Object>> teamInfoList=basicSQLService.find(sql);
        if(teamInfoList.size()==0){
            return null;
        }
        Map<String,Object> teamInfo=teamInfoList.get(0);
        return teamInfo.get("grade_number")+String.format("%02d",teamInfo.get("team_number"));
    }



}
