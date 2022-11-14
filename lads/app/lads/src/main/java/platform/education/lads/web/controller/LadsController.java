/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import platform.education.lads.contants.EmbedSystem;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.vo.EntityFileVo;
import platform.education.lads.web.common.contants.SysContants;
import platform.education.resource.contants.EntityIOStatus;
import platform.education.resource.contants.UploadFinishedFlag;
import platform.education.resource.utils.DownloadUtil;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

/**
 *
 * @author Administrator
 */
@Controller("ladsController")
@Scope("prototype")
@RequestMapping(value = "/lads/common")
public class LadsController {

    @Resource
    private LadsService ladsService;
    @Resource
    private LadsUserService ladsUserService;
    @Resource
    private AuthoringController authoringController;
    @Resource
    private FileService fileService;
    @Resource
    private EntityFileService entityFileService;
    public static final String commonDir = "common";

    //删除功能
    @RequestMapping(value = "/deleteLd")
    public String deleteLd(HttpServletRequest request, HttpServletResponse response) {
        String[] ldIds = request.getParameterValues("ldIds");
        this.ladsService.deleteLearningDesigns(ldIds);
        return "referer";
    }

    /**
     * general 上传文件
     *
     * @param file
     * @param jsessionId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploader")
    @ResponseBody
    public Object uploader(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsessionId = request.getParameter("jsessionId");
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, String.valueOf(this.ladsUserService.getRelateAppId(request)));
            if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
                EntityFile entityFile = result.getEntityFile();
                EntityFileVo entityFileVo = new EntityFileVo();
                entityFileVo = copyEntityFileProperties(entityFile, entityFileVo);
                entityFileVo.setUrl(result.getHttpUrl());
                return entityFileVo;
            }
        }
        return null;
    }

    /**
     * 进行资源上传
     *
     * @param file
     * @param jsessionId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bpru", method = RequestMethod.POST)
    @ResponseBody
    public Object continueUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String md5 = request.getParameter("md5");
        String chunk = request.getParameter("chunk");
        String chunks = request.getParameter("chunks");
        Integer chunki = null;
        Integer chunksi = null;
        if (chunk != null && !"".equals(chunk)) {
            chunki = Integer.parseInt(chunk);
        }
        if (chunks != null && !"".equals(chunks)) {
            chunksi = Integer.parseInt(chunks);
        }
        String jsessionId = request.getParameter("jsessionId");
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            boolean isComplete = chunki == null && chunksi == null ? true : (chunki == (chunksi - 1));
            FileResult result = this.fileService.resumeUpload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), md5, file.getContentType(), uploadFileName, String.valueOf(this.ladsUserService.getRelateAppId(request)), isComplete);
            if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode()) && isComplete) {
                EntityFile entityFile = result.getEntityFile();
                EntityFileVo fileVo = new EntityFileVo();
                fileVo = copyEntityFileProperties(entityFile, fileVo);
                fileVo.setUrl(result.getHttpUrl());
                fileVo.setFinishedFlag(UploadFinishedFlag.FINISHED);
                return fileVo;
            }
        }
        return null;
    }

    /**
     * 文件md5验证
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/exist")
    public Object getFile(@RequestParam("md5") String md5, HttpServletRequest request) {
        FileResult result = this.fileService.preResumeUpload(md5, String.valueOf(this.ladsUserService.getRelateAppId(request)));
        EntityFile entityFile = result.getEntityFile();
        long size = result.getTempFileSize();
        EntityFileVo entityFileVo = new EntityFileVo();
        if (entityFile != null) {
            copyEntityFileProperties(entityFile, entityFileVo);
            entityFileVo.setUrl(result.getHttpUrl());
            entityFileVo.setFinishedFlag(UploadFinishedFlag.FINISHED);
            return entityFileVo;
        } else if (size > 0) {
            entityFileVo.setSize((int) result.getTempFileSize());
            entityFileVo.setFinishedFlag(UploadFinishedFlag.NOT_FINISHED);
            return entityFileVo;
        }
        return "no_exist";
    }

    private EntityFileVo copyEntityFileProperties(EntityFile entityFile, EntityFileVo entityFileVo) {
        entityFileVo.setFileName(entityFile.getDiskFileName());
        //日期永远新建,因为要显示的是当前上传日期
        entityFileVo.setCreateDate(new Date());
        entityFileVo.setId(entityFile.getId());
        entityFileVo.setMd5Code(entityFile.getMd5());
        entityFileVo.setRealFileName(entityFile.getFileName());
        entityFileVo.setSize(entityFile.getSize());
        entityFileVo.setSuffix(entityFile.getExtension());
        entityFileVo.setUuid(entityFile.getUuid());
        return entityFileVo;
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String entityId = request.getParameter("entityId");
        String downloadTitle = null;
        String suffix = null;
        EntityFile ent = this.entityFileService.findFileByUUID(entityId);
        if (ent != null) {
            suffix = ent.getExtension();
            downloadTitle = ent.getFileName();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if(downloadTitle.contains(".")){
                downloadTitle = downloadTitle.substring(0, downloadTitle.lastIndexOf("."));
            }
            String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(downloadTitle, "UTF-8"));
            response.addHeader("content-type ", "application/x-download");
            response.setContentType("application/x-download");
            response.setContentLength(ent.getSize().intValue());
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + "." + suffix);
            OutputStream out = response.getOutputStream();
            String result = this.fileService.download(entityId, out);
            if (FileStatusCode.DOWNLOAD_SUCCESS.equals(result)) {
                //下载成功
            } else if (FileStatusCode.DOWNLOAD_FAIL_FILE_NOT_EXIT.equals(result)) {
                //远程文件不存在
                //还没处理好错误信息在前端显示
            } else if (FileStatusCode.DOWNLOAD_FAIL_STREAM_ERR.equals(result) || FileStatusCode.CONNECT_SERVER_FAIL.equals(result)) {
                //系统错误
                //还没处理好错误信息在前端显示
            }
        } else {
            request.setAttribute("flag", EntityIOStatus.ENTITY_NOT_EXIST);
            return commonDir + "/download_error";
        }
        return null;
    }

    //复制功能
    @RequestMapping("/copyLD")
    public String copyLD(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String ldId = request.getParameter("ldId");
        String jcb = request.getParameter("callback");
        JSONObject jsonObject = authoringController.createAuthorJson(ldId);
        jsonObject.put("ldId", "");
        JSONArray jsonArray = jsonObject.getJSONArray("activitys");
        this.ladsService.copyJson(jsonArray);
        LadsLearningdesign ld = authoringController.saveImpl(jsonObject.toString());
        JSONObject obj = new JSONObject();
        obj.put("copyldId", ld!=null?ld.getUuid():"fail");
        PrintWriter pw = this.setAjaxResponse(request, response);
        if(jcb!=null&&!"".equals(jcb)){
           pw.print(jcb + "(" + obj.toString() + ")");
        }else{
           pw.print(obj.toString()); 
        }
        return null;
    }
    
    //获取用户学习状态
    @RequestMapping(value = "/getUserFinishedStatus")
    public String getUserFinishedStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ldId = request.getParameter("ldId");
        String userId = request.getParameter("userId");
        String jcb = request.getParameter("callback");
        Integer fi = this.ladsService.getFinishedByUser(ldId, Integer.parseInt(userId));
        JSONObject obj = new JSONObject();
        obj.put("status", fi);
        PrintWriter pw = this.setAjaxResponse(request, response);
        if(jcb!=null&&!"".equals(jcb)){
           pw.print(jcb + "(" + obj.toString() + ")");
        }else{
           pw.print(obj.toString()); 
        }
        return null;
    }

    @RequestMapping(value = "/ladsAction_resourcesHouse.action")
    public String resourcesHouse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sysType = request.getParameter("sysType");
        String toolId = request.getParameter("toolId");
        if (sysType.equals(EmbedSystem.TONG_BU_KE_TANG)) {
            String suffix = request.getParameter("suffix");
            String subjectCode = request.getParameter("subjectCode");
            String publishCode = request.getParameter("publishCode");
            String volumeCode = request.getParameter("volumeCode");
            String unitCode = request.getParameter("unitCode");
            String sectionCode = request.getParameter("sectionCode");
            String keyWord = request.getParameter("keyWord");
            Map<String, String[]> map = new HashMap<String, String[]>();
            if (keyWord != null && !"".equals(keyWord)) {
                map.put("f_name", new String[]{keyWord});
            } else {
                if (subjectCode != null && !"".equals(subjectCode)) {
                    map.put("subject_code", new String[]{subjectCode});
                }
                if (publishCode != null && !"".equals(publishCode)) {
                    map.put("publish_code", new String[]{publishCode});
                }
                if (volumeCode != null && !"".equals(volumeCode)) {
                    map.put("volume_code", new String[]{volumeCode});
                }
                if (unitCode != null && !"".equals(unitCode)) {
                    map.put("unit_code", new String[]{unitCode});
                }
                if (sectionCode != null && !"".equals(sectionCode)) {
                    map.put("section_code", new String[]{sectionCode});
                }
            }
            if (suffix != null && !"".equals(suffix)) {
                String split[] = suffix.split("\\|");
                map.put("f_suffix", split);
            }
//            String solrString = SolrQueryUtil.getSolrQueryString(map);
//            System.out.println("solr=" + solrString);
//            FileQueryService k = new FileQueryService();
//            List<ResourceFile> list = k.getFileQueryList(solrString, pagination, true);
//            request.setAttribute("list", list);
//            request.setAttribute("toolId", toolId);
//            request.setAttribute("urlPara", LadsCommonUtils.createUrlPara(request));
            return "common_xtjyResources_list";
        }
        return null;
    }
    
    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
}
