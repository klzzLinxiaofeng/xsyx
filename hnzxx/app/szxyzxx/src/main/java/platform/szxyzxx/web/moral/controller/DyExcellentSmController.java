package platform.szxyzxx.web.moral.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.core.pojo.BasicResult;
import platform.szxyzxx.dy.pojo.SmInfo;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import java.util.Collections;
import java.util.List;

/**
 * 德育优秀副班主任controller
 */
@Controller
@RequestMapping("/dy/excellentSm")
public class DyExcellentSmController {

    @Autowired
    private BasicSQLService basicSQLService;

    private Importer<SmInfo> smExcelImporter;

    public DyExcellentSmController(){
        ExcelImportParam<SmInfo> param=new ExcelImportParam(SmInfo.class);
        param.setStartRowIndex(4);
        smExcelImporter=new DefaultImporter<>(param);
    }

    @RequestMapping("/index")
    public String index(){
        return view("index");
    }

    @RequestMapping("/add")
    public String addPage(){
        return view("add");
    }

    @RequestMapping("/list")
    @ResponseBody
    public StuVo list(Page page,String xn,String xq){
        StuVo stuVo = new StuVo();
        stuVo.setList(basicSQLService.findByPaging(page,"select * from dy_excellent_sm where xn='"+xn+"' and xq='"+xq+"' order by score desc"));
        stuVo.setPage(page);
        return stuVo;
    }

    @RequestMapping("/setSm")
    @ResponseBody
    public BasicResult setSm(String xn, String xq, Integer passNum, MultipartFile file,@CurrentUser UserInfo userInfo){
        try {
            if(basicSQLService.findUniqueLong("select exists(select 1 from dy_excellent_sm where xn='"+xn+"' and xq='"+xq+"')")>0){
                return BasicResult.error("请勿重复导入");
            }
            List<SmInfo> list=smExcelImporter.importBy(file.getInputStream());
            Collections.sort(list);
            for(int i=0;i<passNum;i++){
                SmInfo sm=list.get(i);
                Integer sum=sm.getSum();
                if(sum<=0){
                    continue;
                }
                basicSQLService.update("insert into dy_excellent_sm(xq,xn,team_name,score,import_user_name) values('"+xq+"','"+xn+"','"+sm.getTeamName()+"','"+sm.getSum()+"','"+userInfo.getRealName()+"')");
            }
            return BasicResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return BasicResult.error("操作失败");
        }
    }

    private String view(String pageName){
        return "/moral/excellentSm/"+pageName;
    }


}
