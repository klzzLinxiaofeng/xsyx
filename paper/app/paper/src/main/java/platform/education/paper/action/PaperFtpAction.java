/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import platform.education.resource.model.Entity;
import platform.education.resource.service.FileUploadService;
import platform.education.resource.utils.PathConverter;

/**
 *
 * @author Administrator
 */
@Controller("paperFtpAction")
@Scope("prototype")
@RequestMapping(value = "/common/paper")
public class PaperFtpAction {
    
    @Resource
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uploadType = request.getParameter("dir");
        PrintWriter pw = this.setAjaxResponse(request, response);
        String[] fileTypes = null;
        long maxSize = 0;
        if (uploadType.equals("image")) {
            //定义允许上传的文件扩展名
            fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
            //允许最大上传文件大小  10M
            maxSize = 1024000 * 10;
        } else if (uploadType.equals("media")) {
            //定义允许上传的文件扩展名
            fileTypes = new String[]{"mp3", "mp4"};
            //允许最大上传文件大小  1024M
            maxSize = 1024000 * 1024;
        } else {
        }
        //获得上传的文件名
        String fileName = file.getOriginalFilename();
        //得到上传文件的扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
                .toLowerCase();
        //检查扩展名
        if (!Arrays.<String>asList(fileTypes).contains(fileExt)) {
            pw.print(getError("上传文件扩展名是不允许的扩展名。"));
            return null;
        }
        //检查文件大小
        if (file.getSize() > maxSize) {
            pw.print(getError("上传文件大小超过限制。"));
            return null;
        }
        
        Entity persistentFile = this.fileUploadService.uploadFile(1, file, fileName);
        if (persistentFile!=null) {
            //发送给 KE 
            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", PathConverter.converHttpUrl(persistentFile, request.getServerName()));
            pw.print(obj.toString());
        } else {
            pw.print(getError("网络或服务器原因导致上传失败"));
        }
        return null;
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
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
