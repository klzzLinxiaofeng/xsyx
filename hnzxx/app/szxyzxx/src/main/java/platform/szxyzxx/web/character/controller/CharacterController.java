package platform.szxyzxx.web.character.controller;


import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Student;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.character.pojo.Evaluation;
import platform.szxyzxx.character.pojo.Records;
import platform.szxyzxx.character.service.EvaluationService;
import platform.szxyzxx.character.service.RecordsService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/character/cultivation")
public class CharacterController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private RecordsService re;
    @Autowired
    private LoggerService loggerService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;

    private final static String viewBasePath = "/character/cultivation";

    /**
     *
     * @param user
     * @param page
     * @param xq  学年
     * @param bg
     * @param nj
     * @param stuName
     * @param sub
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView feindByList(@CurrentUser UserInfo user,
                                    @ModelAttribute("page") Page page,
                                    @RequestParam(value = "xq", required = false) String xq,
                                    @RequestParam(value = "bg", required = false) Integer bg,
                                    @RequestParam(value = "nj", required = false) Integer nj,
                                    @RequestParam(value = "stuName", required = false) String stuName,
                                    @RequestParam(value = "sub", required = false) String sub,
                                    Model model){
        List<Records> list=evaluationService.findByAllRecords(user.getSchoolId(),xq,bg,nj,stuName,page);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/character/list";
        }else{
            bathUrl="/character/character_cultivationIndex";
        }
     ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
     return modelAndView;
    }
    @RequestMapping("/list/indexx")
    public List<Evaluation> feindByList(Model model){
         List<Evaluation> list2= evaluationService.findByAll();
        return list2;
    }


    /*
    * 显示所有指标
    */
    @RequestMapping("/evaluationIndex")
    public ModelAndView feindCharacter_cultivationIndex(
                                            @RequestParam(value = "sub", required = false) String sub,
                                            @ModelAttribute("page") Page page, Model model){
        List<Evaluation> list= evaluationService.findByAll();
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl=viewBasePath+"/list";
        }else{
            bathUrl=viewBasePath+"/evaluationIndex";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }


    /*添加跳转*/
    @RequestMapping("/input")
    public ModelAndView input(){
        ModelAndView modelAndView=new ModelAndView(viewBasePath+"/input");
        return modelAndView;
    }

    /*添加指标*/
    @RequestMapping("/creator")
    public ResponseInfomation add(Evaluation evaluation, @CurrentUser UserInfo userInfo){
        Boolean flag=evaluationService.create(evaluation);
        ResponseInfomation re=new ResponseInfomation();
        if(flag){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("品格养成指标");
            logger.setType(1);
            logger.setMessage("新增品格养成指标："+evaluation.getName() );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
           return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return re;
    }

    /*删除指标*/
    @RequestMapping("/delete")
    public String delete(@CurrentUser UserInfo userInfo,
                         @RequestParam("ids") String ids){
        String flag=evaluationService.abandonTime(ids);
        Loggers logger = new Loggers();
        logger.setCaozuoId(userInfo.getTeacherId());
        logger.setName(userInfo.getRealName());
        logger.setUsername(userInfo.getUserName());
        logger.setMobile(userInfo.getTelephone());
        logger.setMokuaiName("品格养成指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除品格养成指标" );
        }else{
            Evaluation evaluation=evaluationService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除品格养成指标："+evaluation.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        return flag;
    }

    /*修改跳转*/
    @RequestMapping("/inputs")
    public ModelAndView update(@RequestParam(value = "id", required = false) int id,Model model){
        Evaluation evaluation=evaluationService.findById(id);
        model.addAttribute("evaluation",evaluation);
        ModelAndView modelAndView=new ModelAndView(viewBasePath+"/input",model.asMap());
        return modelAndView;
    }

    /*修改指标*/
    @RequestMapping("/update")
    public ResponseInfomation updatexiugai(Evaluation evaluation, @CurrentUser UserInfo userInfo){
        Boolean flag=evaluationService.update(evaluation);
        ResponseInfomation re=new ResponseInfomation();
        if(flag){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("品格养成指标");
            logger.setType(2);
            logger.setMessage("修改品格养成指标："+evaluation.getName() );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return re;
    }

    /*
    *查看评价记录
    */
    @RequestMapping("/findBypingjiaAll")
    public ModelAndView findByPinJia(@RequestParam(value = "studentId", required = false) Integer studentId,
                                     @CurrentUser UserInfo user,
                                     @RequestParam(value = "sub", required = false) String sub,
                                     @RequestParam(value = "dm", required = false) String dm,
                                     @ModelAttribute("page") Page page, Model model){
        List<Records> recordsList=re.findByAll(user.getSchoolId(),studentId,page);
        List<Records> list2=new ArrayList<>();
        for(Records aa:recordsList){
            Records records=new Records();
            records.setTeaName(aa.getTeaName());
            records.setEvName(aa.getEvName());
            records.setCreateTime(aa.getCreateTime());
            records.setScore(aa.getScore());
            records.setMessage(aa.getMessage());
            records.setPictureId(aa.getPictureId());
            records.setVoice(aa.getVoice());
            if (aa.getPictureId() != null) {
                // 根据uuid查询图片的url
                FileResult file = fileService.findFileByUUID(aa.getPictureId());
                if (file != null) {
                    records.setPictureUrl(file.getHttpUrl());
                }
            }
            list2.add(records);
        }
        String viewPath;
        if ("list".equals(sub)) {
            viewPath = "/character/jiluList";
        } else {
            model.addAttribute("studentId",studentId);
            viewPath = "/character/jilu";
        }

        model.addAttribute("recordsList",list2);

        ModelAndView modelAndView=new ModelAndView(viewPath,model.asMap());
        return modelAndView;
    }
    //跳转到删除图片页面
    @RequestMapping("/evaluationIndexasdaa")
    public ModelAndView shanchutupian(){
        ModelAndView modelAndView=new ModelAndView("/character/shanchupicter");
        return modelAndView;
    }

    //删除图片
    @RequestMapping("/deletePicter")
    public String deletePicter(@CurrentUser UserInfo user,
                               @RequestParam(value = "xq", required = false) String xq,
                               @RequestParam(value = "xn", required = false) String xn,
                               @RequestParam(value = "bg", required = false) Integer bg,
                               @RequestParam(value = "nj", required = false) Integer nj){

        List<Student> studentList=evaluationService.findByStudentAll(user.getSchoolId(),bg,nj,xn);
        for(Student aa:studentList){
            List<Records> recordsList=re.findByAll(user.getSchoolId(),aa.getId(),null);
            if(recordsList!=null){
                for(Records bb:recordsList){
                    if(bb.getPictureId()!=null){
                        // 根据uuid查询图片的url
                        String str=null;
                        FileResult file = fileService.findFileByUUID(bb.getPictureId());
                        if (file != null) {
                            str=file.getHttpUrl();
                        }
                        deleteFile(str);
                    }
                }
            }
        }
        return null;
    }
    /**
     * 删除文件、文件夹
     */
    public void deleteFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] ff = file.listFiles();
            for (int i = 0; i < ff.length; i++) {
                deleteFile(ff[i].getPath());
            }
        }
        file.delete();
    }

}
