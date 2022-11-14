package platform.szxyzxx.web.schoolaffair.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.CanteenCardPojo;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.CanteenCardService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.listener.UserCardListener;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolaffair.vo.CarExportInfo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 食堂补卡管理
 *
 * @author: yhc
 * @Date: 2020/12/3 16:43
 * @Description:
 */
@Controller
@RequestMapping("/canteen/management")
public class CanteenCardController extends BaseController {

    private final static String viewBasePath = "/schoolaffair/canteenManager/cardManager";
    /**
     * 食堂接口
     */
    private static String fileName;

    private static String address;
    //新增修改接口
    private  static String canupdate;
    //补卡
    private  static String buka;
    static {
    fileName = "System.properties";
        // 食堂
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canupdate = PropertiesUtil.getProperty(fileName, "canteen.server.update.url");
        buka= PropertiesUtil.getProperty(fileName, "canteen.server.update.buka.url");
    }
    @Autowired
    @Qualifier("canteenCardService")
    private CanteenCardService canteenCardService;

    @Autowired
    private BasicSQLService basicSQLService;

    private Exporter<CarExportInfo> exporter;

    @Autowired
    private UserCardListener listener;

    public CanteenCardController(){
        ExcelExportParam<CarExportInfo> param=new ExcelExportParam(CarExportInfo.class,"补卡信息");
        exporter=new DefaultExporter<>(param);
    }


    /**
     * 食堂信息列表
     *
     * @param sub
     * @param condition
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") CanteenCardPojo condition,
            @ModelAttribute("page") Page page,
            String startDateStr,
            String endDateStr,
            Model model) {
        String viewPath = null;


        if(StringUtils.isNotEmpty(startDateStr) || StringUtils.isNotEmpty(endDateStr)){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                if(StringUtils.isNotEmpty(startDateStr)){
                    condition.setStartDate(sdf.parse(startDateStr));
                }
                if(StringUtils.isNotEmpty(endDateStr)){
                    Calendar end=Calendar.getInstance();
                    end.setTime(sdf.parse(endDateStr));
                    end.add(Calendar.DAY_OF_MONTH,1);
                    condition.setEndDate(end.getTime());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<CanteenCardPojo> canteens = this.canteenCardService.findCanteenByCondition(condition, page, new Order("created_at"));
//        for (CanteenCardPojo canteen : canteens) {
//            userInfo(canteen);
//        }

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("canteens", canteens);
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping("/exportData")
    public void exportDate(CanteenCardPojo condition, String startDateStr, String endDateStr, HttpServletResponse response){
        if(StringUtils.isNotEmpty(startDateStr) || StringUtils.isNotEmpty(endDateStr)){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                if(StringUtils.isNotEmpty(startDateStr)){
                    condition.setStartDate(sdf.parse(startDateStr));
                }
                if(StringUtils.isNotEmpty(endDateStr)){
                    Calendar end=Calendar.getInstance();
                    end.setTime(sdf.parse(endDateStr));
                    end.add(Calendar.DAY_OF_MONTH,1);
                    condition.setEndDate(end.getTime());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            List<CarExportInfo> list=translate( this.canteenCardService.findCanteenByCondition(condition, null, new Order("created_at")));
            Workbook wb=exporter.exportToNew(list);
            response.setContentType("octets/stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode("补卡信息.xls", "UTF-8")));
            OutputStream out=response.getOutputStream();
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }










    @RequestMapping("/delete")
    @ResponseBody
    public int delete(Integer id){
        try {
            basicSQLService.update("delete from pj_canteen_card where id="+id);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }


    /**
     * 修改
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView modifyCanteen(@CurrentUser UserInfo user, @RequestParam(value = "id") Integer id,
                                      Model model) {
        CanteenCardPojo canteenCardPojo = new CanteenCardPojo();
        canteenCardPojo.setId(id);
        List<CanteenCardPojo> canteenList = this.canteenCardService.findCanteenByCondition(canteenCardPojo, null, null);
        if (canteenList != null && canteenList.size() > 0) {
            CanteenCardPojo canteen = canteenList.get(0);
            //userInfo(canteen);
            model.addAttribute("canteen", canteen);
        }

        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping("/updateCar")
    @ResponseBody
    public String  updateCanteen(CanteenCardPojo canteen) {
        String s="补卡失败";
        try {
            s = this.canteenCardService.modify(canteen,listener.getArtemisConfig(),address+buka);
            if(s==null){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }



    private List<CarExportInfo> translate(List<CanteenCardPojo> canteenList){
        List<CarExportInfo> exportList=new ArrayList<>(canteenList.size());
        for (CanteenCardPojo cardPojo : canteenList) {
            CarExportInfo e=new CarExportInfo();
            e.setName(cardPojo.getName());
            e.setTeamName(cardPojo.getTeamName());
            e.setReason(cardPojo.getMesg());
            e.setOldCar(cardPojo.getOldIcNumber());
            e.setNewCar(cardPojo.getNewIcNumber());
            e.setCreateDate(cardPojo.getCreatedAt());
            exportList.add(e);
        }
        return exportList;
    }


//    public void userInfo(CanteenCardPojo canteen) {
//        // 0学生 1教师
//        Integer userType = canteen.getUserType();
//        if (userType != null) {
//            Integer userId = canteen.getUserId();
//            if (userType == 0) {
//                Student student = studentService.findStudentById(userId);
//                if (student != null) {
//                    canteen.setName(student.getName());
//                    canteen.setTeamName(student.getTeamName());
//                }
//            } else if (userType == 1) {
//                Teacher teacher = teacherService.findTeacherById(userId);
//                if (teacher != null) {
//                    DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
//                    dtCondition.setIsDeleted(false);
//                    dtCondition.setTeacherId(userId);
//                    List<DepartmentTeacher> list = this.departmentTeacherService.findDepartmentTeacherByCondition(dtCondition, null, null);
//                    if (list != null && list.size() > 0) {
//                        canteen.setTeamName(list.get(0).getDepartmentName());
//                    }
//                    canteen.setName(teacher.getName());
//                }
//            }
//        }
//    }


}
