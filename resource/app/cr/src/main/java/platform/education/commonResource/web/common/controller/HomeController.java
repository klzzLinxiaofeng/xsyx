package platform.education.commonResource.web.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.MultiDomainResolver;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.vo.ResourceLibraryCondition;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
    @Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
	
	@RequestMapping(value={"/", "/home", "index"})
	public String toHome(HttpServletRequest request,Model model) {
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
        Integer relateAppId = this.getRelateApp(request);
        Integer relateSchoolId = this.getRelateSchool(request);
        List<ResourceLibrary> list = this.resourceLibraryService.findResourceLibraryByCondition(null);
        ResourceLibraryCondition condition = new ResourceLibraryCondition();
        condition.setOwerId(ownerId);
        condition.setAppId(appId);
        List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
        String url = "";
        String host = FtlContants.FTL_HTMLURL;
        host = MultiDomainResolver.resolver(request.getServerName(), host);

        if(resourceLibraryList.size()<=0){
        	condition = new ResourceLibraryCondition();
        	condition.setOwerId(0);
            condition.setAppId(6);
            List<ResourceLibrary> resLibList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
            ResourceLibrary resourceLibrary = null;
            if(resLibList.size()==1){
            	resourceLibrary = resLibList.get(0);
            }
            if(resourceLibrary!=null){
            	url = "redirect:" + host + "index_"+ appId +"_"+ ownerId + "_"+ resourceLibrary.getUuid()+ ".html";
            }
        }else{
        	ResourceLibrary rl = null;
        	rl = resourceLibraryList.get(0);
        	if(rl!=null){
        		String filePath = FtlContants.FTL_HTMLPATH+File.separator+"index_"+ rl.getAppId() +"_"+ rl.getOwerId() + "_"+rl.getUuid()+".html";
            	File file = new File(filePath);
            	if(file.exists()){
//            		String host = FtlContants.FTL_HTMLURL;FTL_HTMLURL
            		url = "redirect:"+ host +"index_"+ rl.getAppId() +"_"+ rl.getOwerId() + "_"+rl.getUuid()+".html";
            	}else{
            		condition = new ResourceLibraryCondition();
                	condition.setOwerId(ownerId);
                    condition.setAppId(appId);
                    List<ResourceLibrary> resLibList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
                    ResourceLibrary resourceLibrary = null;
                    if(resLibList.size()==1){
                    	resourceLibrary = resLibList.get(0);
                    }
                    if(resourceLibrary!=null){
//                    	String host = FtlContants.FTL_HTMLURL;
                    	url = "redirect:" + host + "index_"+ appId +"_"+ ownerId + "_"+ resourceLibrary.getUuid()+ ".html";
                    }
            	}
        	}
        	
        }
        return url;
	}
	
}
