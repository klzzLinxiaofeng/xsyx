package platform.szxyzxx.web.jizuController.controller;


import framework.generic.facility.poi.excel.config.ParseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;
import platform.szxyzxx.jizuBiao.service.JiaoZhiGongService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jiaozhigong")
public class JiaoZhiGongController {
    @Autowired
    private JiaoZhiGongService jiaoZhiGongService;
    @RequestMapping("/jiaozhigongView")
    public ModelAndView jiaozhigongView() {
        return new ModelAndView("/jizuBiao/jiaoZhiGong");
    }
    @RequestMapping("/taecherAll")
    public Map<String,Object> getAll(@CurrentUser UserInfo userInfo,
                                     @RequestParam String schoolYaer,
                                     @RequestParam String schoolTrem,
                                     @RequestParam(value = "name",required = false) String name,
                                     @RequestParam Integer zhoushu) {
        return jiaoZhiGongService.findByTeacher(schoolYaer,schoolTrem,name,zhoushu,userInfo);
    }
    //添加
    @RequestMapping("/createAll")
    public String createAll(@CurrentUser UserInfo userInfo, TeacherWoke teacherWoke) {
        return jiaoZhiGongService.createTeackerWoke(teacherWoke);
    }
    /*
     * 导出
     */
    @RequestMapping("/daochuWoke")
    public String daochuWoke(@RequestParam String schoolYear,
                             @RequestParam String schoolTerm,
                             @RequestParam Integer zhoushu,
                             @CurrentUser UserInfo userInfo,
                             @RequestParam(value = "name",required = false) String name,
                             HttpServletResponse response, HttpServletRequest request){
        List<Object> list = new ArrayList();
        List<TeacherWoke> list2 = jiaoZhiGongService.findByDaoChu(schoolYear,schoolTerm,zhoushu,name,userInfo);
        ParseConfig config = SzxyExcelTookit.getConfig("teacherWoke");
        StringBuffer excelName = new StringBuffer();
        excelName.append("第"+zhoushu+"周教职工工作安排表.xls");
        String filename = excelName.toString();
        try {
            for (TeacherWoke studentScoreVo : list2) {
                list.add(studentScoreVo);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
