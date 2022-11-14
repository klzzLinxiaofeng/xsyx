package platform.education.commonResource.web.common.controller;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import platform.education.commonResource.web.common.util.FreeMarkerUtil;

@Controller
@RequestMapping(value="test")
public class TestFreeMaker {
	@RequestMapping(method=RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("test");
        HashMap<String,Object> data = new HashMap<String,Object>();
        view.addObject("message", "Say hi for Freemarker.");
        data.put("message", "Say hi for Freemarker.");
        ServletContext context = request.getServletContext();
       // FreeMarkerUtil.crateHTML(context, data, "/test.ftl", "test.html");
        return view;
    }

}
