package platform.szxyzxx.web.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;

@Controller
@RequestMapping("/system/applets")
public class AppletsLoginController {

    @Autowired
    private BasicSQLService basicSQLService;



    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap) {
        modelMap.put("imgPath",basicSQLService.findUnique("select bg_path from sys_login_page where id=1"));
        return "/system/appletLogin/index";
    }

    @RequestMapping(value = "/upBg")
    @ResponseBody
    public int update(String path){
        try {
            if(basicSQLService.findUniqueLong("select exists(select 1 from sys_login_page where id=1) e")<1){
                basicSQLService.update("insert into sys_login_page(id,bg_path) values(1,'"+path+"')");
            }else {
                basicSQLService.update("update sys_login_page set bg_path='" + path + "' where id=1");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * 图书馆上传图片地址
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/library/index")
    public ModelAndView libraryIndex(Model model) {
        String viewPath = "/system/library/indexLibrary";
        return new ModelAndView(viewPath, model.asMap());
    }

}
