package platform.education.netdisk.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import platform.education.annotation.AuthIgnore;
import platform.education.annotation.PermissionIgnore;

/**
 * 文件上传下载
 *
 * @author XYSM
 */
@RestController
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    /**
     * 文件保存路径
     */
    @Value("${file.uploadPath}")
    private String filePath;

    /**
     * 单图片上传
     *
     * @param file
     * @return
     */
    @AuthIgnore
    @RequestMapping(value = "/upload")
    public Result upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new FrameworkException("上传文件不能为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            fileName = "image/" + UUID.randomUUID().toString().replaceAll("-", "") + "/" + fileName;
            String path = filePath + "/" + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            fileName = getstrBackUrl(request) + "/download?fileName=" + fileName;
            Map<String, Object> map = new HashMap<>();
            map.put("url", fileName);
            map.put("name", file.getOriginalFilename());
            return Result.ok(map);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("文件上传失败");
    }

    /**
     * 富文本编辑器图片上传
     *
     * @param request
     * @param file
     * @return
     */
    @AuthIgnore
    @RequestMapping(value = "/uploadWangEditor")
    public Object uploadWangEditor(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new FrameworkException("上传文件不能为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            fileName = "image/" + UUID.randomUUID().toString().replaceAll("-", "") + "/" + fileName;
            String path = filePath + "/" + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            fileName = getstrBackUrl(request) + "/download?fileName=" + fileName;
            Map<String, Object> map = new HashMap<String, Object>();
            List<String> stringArr = new ArrayList<>();
            stringArr.add(fileName);
            map.put("errno", 0);
            map.put("data", stringArr);//这里应该是项目路径
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 上传视频
     *
     * @param request
     * @param response
     * @param identifier
     * @param chunkNumber
     * @param file
     * @param chunks
     */
    @PermissionIgnore
    @RequestMapping("/uploadVideo")
    public void uploadVideo(HttpServletRequest request, HttpServletResponse response, String identifier,
                            Integer chunkNumber, MultipartFile file, Integer chunks) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                // 临时目录用来存放所有分片文件
                String tempFileDir = filePath + "/video/" + identifier;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                File tempPartFile = new File(parentFileDir, identifier + "_" + chunkNumber + ".part");
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并视频
     *
     * @param guid
     * @param fileName
     * @throws Exception
     */
    @PermissionIgnore
    @RequestMapping("/merge")
    public Result mergeFile(String uniqueIdentifier, String name) {
        // 得到 destTempFile 就是最终的文件
        name = UUID.randomUUID().toString().replaceAll("-", "") + "_" + name;
        try {
            File parentFileDir = new File(filePath + "/video/" + uniqueIdentifier);
            if (parentFileDir.isDirectory()) {
                File destTempFile = new File(filePath + "/video" + "/merge/", name);
                if (!destTempFile.exists()) {
                    // 先得到文件的上级目录，并创建上级目录，在创建文件,
                    destTempFile.getParentFile().mkdir();
                    try {
                        // 创建文件
                        destTempFile.createNewFile(); // 上级目录没有创建，这里会报错
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, "/" + uniqueIdentifier + "_" + i + ".part");
                    FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                    // 遍历"所有分片文件"到"最终文件"中
                    FileUtils.copyFile(partFile, destTempfos);
                    destTempfos.close();
                }
                // 删除临时目录中的分片文件
                FileUtils.deleteDirectory(parentFileDir);
                Map<String, Object> map = new HashMap<>();
                map.put("fileName", "/video" + "/merge/" + name);
                return Result.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
        return null;
    }

    /**
     * 文件批量上传
     *
     * @param request
     * @return
     */
    @PermissionIgnore
    @PostMapping("/batchUpload")
    public Result handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < files.size(); ++i) {
            Map<String, Object> map = new HashMap<>();
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String fileName = file.getOriginalFilename();
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    // 设置文件存储路径
                    fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;
                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));// 设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                    map.put("fileName", fileName);
                } catch (Exception e) {
                    return Result.error("第 " + i + " 个文件上传失败 ");
                }
            } else {
                return Result.error("第 " + i + " 个文件上传失败,文件为空 ");
            }
            list.add(map);
        }
        return Result.ok(list);
    }

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @return
     */
    @AuthIgnore
    @GetMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        if (fileName != null) {
            // 设置文件路径
            File file = new File(filePath + fileName);
            // File file = new File(realPath , fileName);
            if (file.exists()) {
                // response.setContentType("application/force-download");// 设置强制下载不打开
                // response.addHeader("Content-Disposition", "attachment;fileName=" +
                // fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return;
    }

    public static String getstrBackUrl(HttpServletRequest request) {
        String strBackUrl = "http://" + request.getServerName() // 服务器地址
                + ":" + request.getServerPort() // 端口号
                + request.getContextPath().replace("//", "/"); // 项目名称
        return strBackUrl;
    }
}
