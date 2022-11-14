package platform.szxyzxx.web.aesthetic.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.resource.utils.DownloadUtil;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.ErWerMa;
import platform.szxyzxx.aesthetic.service.AestheticService;
import platform.szxyzxx.erweima.util.QRCodeUtil;
import platform.szxyzxx.innovation.service.PracticeInnovationService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.EntityFileVo;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.*;

/**
 * 艺术审美
 */
@RestController
@RequestMapping("/aesthetic")
public class AestheticController {
    @Autowired
    private AestheticService aestheticService;
    @Autowired
    private PracticeInnovationService practiceInnovationService;

    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;

    @Autowired
    @Qualifier("entityFileService")
    protected EntityFileService entityFileService;

    @RequestMapping(value = "/student/json")
    public ModelAndView findByStudent(@CurrentUser UserInfo userInfo,
                                      @RequestParam(value = "teamId",required = false) Integer teamId,
                                      @RequestParam(value = "stuName",required = false) String stuName,
                                      @RequestParam(value = "schoolYear",required = false) String schoolYear,
                                      @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                      @ModelAttribute("page") Page page,
                                      @RequestParam(value = "sub", required = false) String sub,
                                      Model model){
        List<Student> list = practiceInnovationService.findByStudent(userInfo, gradeId, schoolYear, teamId,stuName, page);
        /*if(list!=null && list.size()<=0){
            ModelAndView modelAndView=new ModelAndView("/bus/pick/error");
            return modelAndView;
        }*/
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/aesthetic/list";
        } else {
            bathUrl = "/aesthetic/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    /*
    * 查看学生所有获奖记录
    */
    @RequestMapping(value = "/chakan")
    public ModelAndView findByAestheticAll(@CurrentUser UserInfo userInfo,
                                           @RequestParam(value = "studentId" ,required = false) Integer studentId,
                                           @RequestParam(value = "schoolYear" ,required = false) String schoolYear,
                                           @RequestParam(value = "schoolTrem" ,required = false) String schoolTrem,
                                           @RequestParam(value = "sub" ,required = false) String sub,
                                           @ModelAttribute Page page,
                                           Model model){
        List<AestheticPojo> aestheticPojo=aestheticService.findByAestheticPojo(userInfo,studentId,schoolYear,schoolTrem,page);
        if(aestheticPojo.size()>0) {
            for(AestheticPojo aa:aestheticPojo){
                if (aa.getFineArtId() != null && !aa.getFineArtId() .equals("")) {
                    String [] uuid=aa.getFineArtId().split(",");
                    Map<String,String> map=new HashMap<>();
                    // 根据id查询封面的url
                    for(int a=0;a<uuid.length;a++){
                        FileResult file = fileService.findFileByUUID(uuid[a]);
                        if (file != null) {
                            map.put(uuid[a],file.getHttpUrl());
                        }
                    }
                    aa.setFineArtPicter(map);
               /* // 根据PctreId查询优秀作品的url
                FileResult file = fileService.findFileByUUID(aestheticPojo.getFineArtId());
                if (file != null) {
                    aestheticPojo.setFineArtUrl(file.getHttpUrl());
                }*/
                }
                if (aa.getJiangzhuanId() != null && !aa.getJiangzhuanId().equals("")) {
                    String [] uuid=aa.getJiangzhuanId().split(",");
                    Map<String,String> map=new HashMap<>();
                    // 根据id查询封面的url
                    for(int a=0;a<uuid.length;a++){
                        FileResult file = fileService.findFileByUUID(uuid[a]);
                        if (file != null) {
                            map.put(uuid[a],file.getHttpUrl());
                        }
                    }
                    aa.setJiangzhuanPicter(map);
                }
                if (aa.getGameWorksId() != null && !aa.getGameWorksId() .equals("")) {
                    String [] uuid=aa.getGameWorksId().split(",");
                    Map<String,String> map=new HashMap<>();
                    // 根据id查询封面的url
                    for(int a=0;a<uuid.length;a++){
                        FileResult file = fileService.findFileByUUID(uuid[a]);
                        if (file != null) {
                            map.put(uuid[a],file.getHttpUrl());
                        }
                    }
                    aa.setGameWorksPicter(map);

                }
            }
            }
        model.addAttribute("aestheticPojo",aestheticPojo);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/aesthetic/chakanlist";
        } else {
            bathUrl = "/aesthetic/chakanindex";
        }
        model.addAttribute("studentId",studentId);
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    /*
    *
    */
    @RequestMapping(value = "/add")
    public ModelAndView createAesthetic(@CurrentUser UserInfo userInfo,
                                        @RequestParam(value = "studentId" ,required = false) Integer studentId,
                                          Model model){
        String url="/aesthetic/bianji";
        model.addAttribute("studentId",studentId);
        return new ModelAndView(url,model.asMap());
    }
    @RequestMapping(value = "/inputOrchakan")
    public ModelAndView findByAesthetic(@CurrentUser UserInfo userInfo,
                                      @RequestParam(value = "id" ,required = false) Integer id,
                                      @RequestParam(value = "sub" ,required = false) String sub,
                                      Model model){
        AestheticPojo aestheticPojo=aestheticService.findByAestheticPojoId(id);
        if(aestheticPojo!=null) {
            if (aestheticPojo.getFineArtId() != null && !aestheticPojo.getFineArtId() .equals("")) {
                String [] uuid=aestheticPojo.getFineArtId().split(",");
                Map<String,String> map=new HashMap<>();
                // 根据id查询封面的url
                for(int a=0;a<uuid.length;a++){
                    FileResult file = fileService.findFileByUUID(uuid[a]);
                    if (file != null) {
                        map.put(uuid[a],file.getHttpUrl());
                    }
                }
                aestheticPojo.setFineArtPicter(map);
                // 根据PctreId查询优秀作品的url
                FileResult file = fileService.findFileByUUID(aestheticPojo.getFineArtId());
                if (file != null) {
                    aestheticPojo.setFineArtUrl(file.getHttpUrl());
                }
            }
            if (aestheticPojo.getJiangzhuanId() != null && !aestheticPojo.getJiangzhuanId().equals("")) {
                String [] uuid=aestheticPojo.getJiangzhuanId().split(",");
                Map<String,String> map=new HashMap<>();
                // 根据id查询封面的url
                for(int a=0;a<uuid.length;a++){
                    FileResult file = fileService.findFileByUUID(uuid[a]);
                    if (file != null) {
                        map.put(uuid[a],file.getHttpUrl());
                    }
                }
                aestheticPojo.setJiangzhuanPicter(map);

                // 根据uuid查询奖状的url
                FileResult file = fileService.findFileByUUID(aestheticPojo.getJiangzhuanId());
                if (file != null) {
                    aestheticPojo.setJiangzhuanUrl(file.getHttpUrl());
                }
            }
            if (aestheticPojo.getGameWorksId() != null && !aestheticPojo.getGameWorksId() .equals("")) {
                String [] uuid=aestheticPojo.getGameWorksId().split(",");
                Map<String,String> map=new HashMap<>();
                // 根据id查询封面的url
                for(int a=0;a<uuid.length;a++){
                    FileResult file = fileService.findFileByUUID(uuid[a]);
                    if (file != null) {
                        map.put(uuid[a],file.getHttpUrl());
                    }
                }
                aestheticPojo.setGameWorksPicter(map);

            }
        }
        model.addAttribute("aestheticPojo",aestheticPojo);
        String bathUrl;
        if (sub.equals("inputs")) {
            bathUrl = "/aesthetic/input";
        } else {
            bathUrl = "/aesthetic/chakan";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping(value = "/inputCreate")
    public String createOrupdate(@CurrentUser UserInfo userInfo,
                                 AestheticPojo aestheticPojo) {
        if(aestheticPojo.getId()==null){
            aestheticPojo.setSchoolYear(userInfo.getSchoolYear());
            aestheticPojo.setSchoolYear(userInfo.getSchoolTermCode());
        }
        String falg = aestheticService.createOrupdate(userInfo,aestheticPojo);
    return falg;
    }

        /*
        *生成二维码
         */
    @RequestMapping(value = "/CreateERWerma")
    public String CreateERWerma(@RequestParam(value = "studentId",required = false)Integer studentId,
                                @RequestParam Integer schoolId,
                                @CurrentUser UserInfo userInfo,
                                HttpServletRequest request){
        String sql="select * from pj_student where is_delete=0 and  school_id="+schoolId;
        if(studentId!=null){
            sql=sql+" and id="+studentId;
        }
        List<Map<String,Object>> list=basicSQLService.find(sql);
        List<String> asd=new ArrayList<>();
        for(Map<String,Object> aa:list) {
            ErWerMa erWerMa1 = aestheticService.findByEeWeiMa(Integer.parseInt(aa.get("id").toString()), userInfo.getSchoolYear());
            if (erWerMa1 == null) {
                // 存放在二维码中的内容
                String text = String.valueOf(aa.get("id"));
                // 嵌入二维码的图片路径
                // String imgPath = "G:/qrCode/dog.jpg";
                // 生成的二维码的路径及名称
                String destPath = aa.get("team_name") + "+" + aa.get("name") + ".jpg";
                //String destPath = "https://www.dglbxsyx.net/zhxy.files/erweima/"+aa.get("team_name")+"+"+aa.get("name")+".jpg";
                ErWerMa erWerMa = new ErWerMa();
                erWerMa.setSchoolYear(userInfo.getSchoolYear());
                try {
                    InputStream inputStream = QRCodeUtil.encode(text, destPath, true);
                    FileResult result = this.fileService.upload(
                            inputStream,
                            StringUtils.getFilenameExtension(destPath),
                            "text/plain", aa.get("team_name") + "+" + aa.get("name"),
                            String.valueOf(SysContants.SYSTEM_APP_ID));
                    if (result != null
                            && FileStatusCode.UPLOAD_SUCCESS.equals(result
                            .getStatusCode())) {
                        EntityFile entityFile = result.getEntityFile();
                        EntityFileVo fileVo = new EntityFileVo();
                        copyEntityFileProperties(entityFile, fileVo);
                        fileVo.setUrl(result.getHttpUrl());
                        erWerMa.setUrl(result.getHttpUrl());
                        erWerMa.setUuid(entityFile.getUuid());
                        erWerMa.setStudentId(Integer.valueOf(aa.get("id").toString()));
                        erWerMa.setCreateTime(new Date());
                        aestheticService.createErweima(erWerMa);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    /*
     *批量生成二维码
     */
    @RequestMapping(value = "/PiLiangCreateERWerma")
    public String PiLiangCreateERWerma(@RequestParam(value = "gradeId",required = false)Integer gradeId,
                                @RequestParam(value = "teamId",required = false)Integer teamId,
                                @CurrentUser UserInfo userInfo,
                                @RequestParam(value = "studentName",required = false)String studentName,
                                @RequestParam(value = "xn",required = false)String xn,
                                HttpServletRequest request){
        List<Student> list = practiceInnovationService.findByStudentErweiMa(userInfo, gradeId, xn, teamId,studentName, null);
        for(Student aa:list){
            ErWerMa erWerMa1=aestheticService.findByEeWeiMa(aa.getId(),userInfo.getSchoolYear());
            if(erWerMa1==null){
                // 存放在二维码中的内容
                String text = String.valueOf(aa.getId());
                // 嵌入二维码的图片路径
                // String imgPath = "G:/qrCode/dog.jpg";
                // 生成的二维码的路径及名称
                String destPath = aa.getTeamName()+"+"+aa.getName()+".jpg";
                //String destPath = "https://www.dglbxsyx.net/zhxy.files/erweima/"+aa.get("team_name")+"+"+aa.get("name")+".jpg";
                ErWerMa erWerMa=new ErWerMa();
                erWerMa.setSchoolYear(userInfo.getSchoolYear());
                try {
                    InputStream  inputStream=  QRCodeUtil.encode(text, destPath, true);
                    FileResult result = this.fileService.upload(
                            inputStream,
                            StringUtils.getFilenameExtension(destPath),
                            "text/plain",aa.getTeamName()+"+"+aa.getName(),
                            String.valueOf(SysContants.SYSTEM_APP_ID));
                    if (result != null
                            && FileStatusCode.UPLOAD_SUCCESS.equals(result
                            .getStatusCode())) {
                        EntityFile entityFile = result.getEntityFile();
                        EntityFileVo fileVo = new EntityFileVo();
                        copyEntityFileProperties(entityFile, fileVo);
                        fileVo.setUrl(result.getHttpUrl());
                        erWerMa.setUrl(result.getHttpUrl());
                        erWerMa.setUuid(entityFile.getUuid());
                        erWerMa.setStudentId(Integer.valueOf(aa.getId()));
                        erWerMa.setCreateTime(new Date());
                        aestheticService.createErweima(erWerMa);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
        return "success";
    }
        /*
        * 二维码页面展示
        */
        @RequestMapping(value = "/showErWeiMa")
        public ModelAndView CreateERWerma(@RequestParam(value = "studentName",required = false)String studentName,
                                          @RequestParam(value = "teamId",required = false)Integer teamId,
                                          @RequestParam(value = "xn",required = false)String xn,
                                          @RequestParam(value = "gradeId",required = false)Integer gradeId,
                                          @CurrentUser UserInfo userInfo,
                                          @ModelAttribute("page") Page page,
                                          @RequestParam(value = "sub", required = false) String sub,
                                          Model model) {
            List<Student> list = practiceInnovationService.findByStudentErweiMa(userInfo, gradeId, xn, teamId,studentName, page);
            List<ErWerMa> erWerMaList=new ArrayList<>();
            for(Student aa:list){
                ErWerMa erWerMa=new ErWerMa();
                erWerMa.setStudentId(aa.getId());
                erWerMa.setTeamId(aa.getTeamId());
                erWerMa.setTeamName(aa.getTeamName());
                erWerMa.setStudnetName(aa.getName());
                ErWerMa erWerMa1=aestheticService.findByEeWeiMa(aa.getId(),userInfo.getSchoolYear());
                if(erWerMa1!=null){
                    if(erWerMa1.getUrl()!=null){
                        erWerMa.setId(erWerMa1.getId());
                        erWerMa.setUrl(erWerMa1.getUrl());
                    }
                }
                erWerMaList.add(erWerMa);
            }
            model.addAttribute("list", erWerMaList);
            String bathUrl;
            if (sub.equals("list")) {
                bathUrl = "/aesthetic/erweima/list";
            } else {
                bathUrl = "/aesthetic/erweima/index";
            }
            ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
            return modelAndView;
        }

        //二维码下载
        @RequestMapping(value = "/XiaZaiERWerma")
        public String xiazaiERWerma(@RequestParam(value = "id",required = false)Integer id,
                                    @CurrentUser UserInfo userInfo,
                 HttpServletResponse response,HttpServletRequest request) throws IOException {
            String sql="select pse.*,ps.name,ps.team_name from pj_student_erweima pse inner join pj_student ps on ps.id=pse.student_id where pse.is_delete=0 and pse.id="+id;
            //获取图片uuid
            List<Map<String,Object>> list=basicSQLService.find(sql);
            String uuid=list.get(0).get("uuid").toString();
            //设置下载文件名
            String fileName=list.get(0).get("team_name").toString()+list.get(0).get("name").toString()+".jpg";
            EntityFile ent = null;
            if (uuid != null && !"".equals(uuid)) {
                ent = this.entityFileService.findFileByUUID(uuid);
            }
            if (ent != null) {
                //开始下载
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(fileName, "UTF-8"));

                response.addHeader("content-type", "image/jpeg");
                response.setContentType("image/jpeg");
                response.setContentLength(ent.getSize().intValue());
                response.setHeader("Content-Disposition", "attachment;filename="
                        + filename);
                OutputStream out = response.getOutputStream();
                // Integer flag = this.fileUploadService.downloadEntity(entId, out);
                String result = fileService.download(uuid, out);
                if (FileStatusCode.DOWNLOAD_SUCCESS.equals(result)) {
                } else if (FileStatusCode.DOWNLOAD_FAIL_FILE_NOT_EXIT
                        .equals(result)) {
                   // System.out.println("result"+result);
                    // 远程文件不存在
                    // 还没处理好错误信息在前端显示
                } else if (FileStatusCode.DOWNLOAD_FAIL_STREAM_ERR.equals(result)
                        || FileStatusCode.CONNECT_SERVER_FAIL.equals(result)) {
                    // 系统错误
                    // 还没处理好错误信息在前端显示
                  //  System.out.println("result"+result);
                }
            }
            return "success";
        }



    //二维码批量下载
    @RequestMapping(value = "/PiLiangXiaZaiERWerma")
    public String PiLiangXiaZaiERWerma(@RequestParam(value = "studentName",required = false)String studentName,
                                       @RequestParam(value = "teamId",required = false)Integer teamId,
                                       @RequestParam(value = "xn",required = false)String xn,
                                       @RequestParam(value = "gradeId",required = false)Integer gradeId,
                                @CurrentUser UserInfo userInfo,
                                       HttpServletResponse response,HttpServletRequest request) throws IOException {
       //获取到需要下载的学生id
        List<Student> list = practiceInnovationService.findByStudentErweiMa(userInfo, gradeId, xn, teamId,studentName, null);
        for(Student aa:list){
            ErWerMa erWerMa=aestheticService.findByEeWeiMa(aa.getId(),userInfo.getSchoolYear());
            String uuid=erWerMa.getUuid();
            //设置下载文件名
            String fileName=aa.getTeamName()+aa.getName()+".jpg";
            EntityFile ent = null;
            if (uuid != null && !"".equals(uuid)) {
                ent = this.entityFileService.findFileByUUID(uuid);
            }
            if (ent != null) {
                //开始下载
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(fileName, "UTF-8"));
                response.addHeader("content-type", "image/jpeg");
                response.setContentType("image/jpeg");
                response.setContentLength(ent.getSize().intValue());
                response.setHeader("Content-Disposition", "attachment;filename="
                        + filename+".jpg");
                OutputStream out = response.getOutputStream();
                // Integer flag = this.fileUploadService.downloadEntity(entId, out);
                String result = fileService.download(uuid, out);
                if (FileStatusCode.DOWNLOAD_SUCCESS.equals(result)) {
                } else if (FileStatusCode.DOWNLOAD_FAIL_FILE_NOT_EXIT
                        .equals(result)) {
                    System.out.println("result"+result);
                    // 远程文件不存在
                    // 还没处理好错误信息在前端显示
                } else if (FileStatusCode.DOWNLOAD_FAIL_STREAM_ERR.equals(result)
                        || FileStatusCode.CONNECT_SERVER_FAIL.equals(result)) {
                    // 系统错误
                    // 还没处理好错误信息在前端显示
                    System.out.println("result"+result);
                }
        }
        }
        return "success";
    }

    private void copyEntityFileProperties(EntityFile entityFile,
                                          EntityFileVo entityFileVo) {
        entityFileVo.setFileName(entityFile.getDiskFileName());
        entityFileVo.setCreateDate(entityFile.getCreateDate());
        entityFileVo.setId(entityFile.getId());
        entityFileVo.setMd5Code(entityFile.getMd5());
        entityFileVo.setRealFileName(entityFile.getFileName());
        entityFileVo.setSize(entityFile.getSize());
        entityFileVo.setSuffix(entityFile.getExtension());
        entityFileVo.setUuid(entityFile.getUuid());
    }


}
