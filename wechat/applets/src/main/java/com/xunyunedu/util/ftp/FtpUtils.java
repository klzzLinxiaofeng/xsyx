package com.xunyunedu.util.ftp;


import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.common.pojo.FileResult;
import com.xunyunedu.common.pojo.StorageResult;
import com.xunyunedu.common.service.UploaderService;
import com.xunyunedu.file.pojo.ResEntityFile;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.UUIDUtil;
import com.xunyunedu.util.pwdutil.FileMd5Util;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Calendar;

/**
 * ftp连接 - 参照老项目开发
 *
 * @author: yhc
 * @Date: 2020/10/15 17:54
 * @Description:
 */
@Configuration
public class FtpUtils {

    Logger log = LoggerFactory.getLogger(FtpUtils.class);

    @Lazy
    @Autowired
    private UploaderService uploaderService;


    /**
     * 获取连接
     *
     * @return
     */
    private FTPClient getConnectedFTPClient() {
        FTPClient ftpClient = new FTPClient();

        try {
            String property = PropertiesUtil.getProperty("storage.properties", "ftp.port");
            String ip = PropertiesUtil.getProperty("storage.properties", "ftp.ip");
            Integer port = property != null && !("").equals(property) ? Integer.parseInt(property) : 21;
            String username = PropertiesUtil.getProperty("storage.properties", "ftp.username");
            String password = PropertiesUtil.getProperty("storage.properties", "ftp.password");
            String time = PropertiesUtil.getProperty("storage.properties", "ftp.connectionTimeout");
            Integer connectionTimeout = time != null && !("").equals(time) ? Integer.parseInt(time) : 1200000;
            String boo = PropertiesUtil.getProperty("storage.properties", "ftp.isPassive");
            Boolean isPassive = boo != null && !("").equals(boo) ? Boolean.parseBoolean(boo) : false;

            ftpClient.connect(ip, port);
            ftpClient.login(username, password);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return null;
            } else {
                ftpClient.setConnectTimeout(connectionTimeout);
                ftpClient.setFileType(2);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setBufferSize(1048576);
                if (isPassive) {
                    ftpClient.enterLocalPassiveMode();
                } else {
                    ftpClient.enterLocalActiveMode();
                }

                return ftpClient;
            }
        } catch (IOException var3) {
            log.error("ftp: 连接失败! {}，请检查配置是否正确" + var3.getMessage());
            return null;
        }
    }


    /**
     * 上传文件
     *
     * @param input
     * @param extension
     * @param contentType
     * @param fileName
     * @param tag
     * @return
     */
    public FileResult upload(InputStream input, String extension, String contentType, String fileName, String tag) {
        if (input == null) {
            throw new IllegalArgumentException("InputStream参数都为null, 属于不合法参数");
        } else if (extension != null && !StringUtils.isEmpty(extension)) {
            if (fileName == null) {
                throw new IllegalArgumentException("fileName参数为null, 属于不合法参数");
            } else if (contentType == null) {
                throw new IllegalArgumentException("contentType参数为null, 属于不合法参数");
            } else {
                EntityFile entityFile = null;
                String md5 = null;
                StreamReuse streamReuse = new StreamReuse(input);

                try {
                    input = streamReuse.getInputStream();
                    md5 = FileMd5Util.getFileMd5(input, 5);
                } catch (IOException var30) {
                    if (this.log.isInfoEnabled()) {
                        this.log.info("上传文件失败：原因为获取md5码失败,异常信息为:", var30);
                    }

                    return new FileResult("UploadFailGenerateMd5Err");
                }
                FileResult efResult = new FileResult(entityFile, "success");
                try {
                    streamReuse.markUsed();
                    input = streamReuse.getInputStream();
                    int size = input.available();
                    String name = md5 + "." + extension;
                    fileName = !"".equals(fileName) ? fileName : md5 + "." + extension;
                    String uploadPath = generatePath();
                    StorageResult result = upload(uploadPath, name, input);
                    String statusCode = result.getStatusCode();
                    FileResult var16;
                    if ("success".equals(statusCode)) {
                        entityFile = new EntityFile();
                        entityFile.setContentType(contentType);
                        entityFile.setDiskFileName(md5);
                        entityFile.setExtension(extension);
                        entityFile.setFileName(fileName);
                        entityFile.setMd5(md5);
                        entityFile.setRelativePath(result.getRelativePath());
                        entityFile.setSize(size);
                        entityFile.setThumbnailUrl("");
                        entityFile.setUuid(UUIDUtil.getUUID());
                        entityFile = uploaderService.add(entityFile);

                        efResult.setEntityFile(entityFile);
                        efResult.setStatusCode("success");
                        efResult.setHttpUrl(relativePath2HttpUrl(entityFile));
                        var16 = efResult;
                        return var16;
                    }

                    var16 = new FileResult(statusCode);
                    return var16;
                } catch (IOException var31) {
                    if (this.log.isInfoEnabled()) {
                        this.log.info("上传文件失败：流错误,异常信息为:", var31);
                    }
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException var28) {
                            var28.printStackTrace();
                        }
                    }

                }
                return new FileResult("UploadFailStreamErr");
            }
        } else {
            throw new IllegalArgumentException("extension参数为null 或者为 空字符串, 属于不合法参数");
        }
    }

    public StorageResult upload(String path, String fileName, InputStream input) {
        path = FileUtil.pathProcessor(path);
        log.info("path: " + path);
        StorageResult result = new StorageResult(true, "success");
        String dirPath = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName") + path;
        log.info("dirPath:" + dirPath);
        FTPClient ftpClient = this.getConnectedFTPClient();

        try {
            if (ftpClient == null) {
                result = new StorageResult(false, "connectServerFail");
            } else {
                dirPath = FileUtil.pathProcessor(dirPath);
                this.createDir(dirPath, ftpClient);
                if (ftpClient.storeFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1"), input)) {
                    if (log.isInfoEnabled()) {
                        log.info("文件上传成功: " + fileName + "已上传到 " + dirPath, this);
                    }

                    result.setFileName(fileName);
                    result.setFilenameExtension(StringUtils.getFilenameExtension(fileName));
                    result.setLength((long) input.available());
                    result.setFullPath(getHttpPrefix() + dirPath + fileName);
                    result.setRelativePath(path + fileName);
                } else {
                    result = new StorageResult(false, "uploadFailStreamErr");
                }
            }
        } catch (IOException var16) {
            log.error("上传失败: {}", var16.getMessage());
            result = new StorageResult(false, "uploadFailStreamErr");
        } finally {
            try {
                if (input != null) {
                    input.close();
                }

                if (ftpClient != null) {
                    ftpClient.logout();
                    if (ftpClient.isConnected()) {
                        ftpClient.disconnect();
                    }
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return result;
    }

    private String getHttpPrefix() {
        String path = PropertiesUtil.getProperty("storage.properties", "ftp.httpPrefix");
        String value = path != null && !("").equals(path) ? path : "ftp://192.168.1.97";
        return value;
    }


    public String generatePath() {

        String boo = PropertiesUtil.getProperty("storage.properties", "ftp.pathPattern");
        String pattern = boo != null && !("").equals(boo) ? boo : "/${year}-${month}-${date}";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(1);
        int month = calendar.get(2) + 1;
        int date = calendar.get(5);
        return pattern.replace("${year}", String.valueOf(year)).replace("${month}", String.valueOf(month)).replace("${date}", String.valueOf(date));
    }


    private boolean createDir(String remote, FTPClient ftpClient) {
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);

        try {
            if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory.getBytes("GBK"), "iso-8859-1"))) {
                int start;
                if (directory.startsWith("/")) {
                    start = 1;
                } else {
                    start = 0;
                }

                int end = directory.indexOf("/", start);

                do {
                    String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                    if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                        if (!ftpClient.makeDirectory(subDirectory)) {
                            log.error("创建目录" + subDirectory + "失败");
                            return false;
                        }
                        ftpClient.changeWorkingDirectory(subDirectory);
                    }
                    start = end + 1;
                    end = directory.indexOf("/", start);
                } while (end > start);
            }
        } catch (IOException var7) {
            log.error("创建目录失败: {}", var7.getMessage());
        }

        return true;
    }


    public String relativePath2HttpUrl(EntityFile entityFile) {
        String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");
        return entityFile != null ? getHttpPrefix() + "/" + spaceName + entityFile.getRelativePath() : "";
    }


    public String relativePath2HttpUrl(String relativePath) {
        String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");
        return relativePath != null ? getHttpPrefix() + "/" + spaceName + relativePath : "";
    }


    public String getPrefix(){
        String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");
        return getHttpPrefix() + "/" + spaceName;
    }

    /**
     * 获取图书馆图片在本服务器的访问地址
     *
     * @return
     */
    public String relativePathLibrary() {
        String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");
        String library = PropertiesUtil.getProperty("storage.properties", "ftp.library.path");
        return spaceName != null ? getHttpPrefix() + "/" + spaceName + library : "";
    }

    /**
     * @param downUrl
     * @Description: fitp文件下载
     * @Param: * @param resEntityFile
     * @return: void
     * @Author: cmb
     * @Date: 2020/10/30
     */

    public void download(ResEntityFile resEntityFile, String downUrl) throws IOException {
        if (resEntityFile != null && downUrl != null && !downUrl.equals("")) {
            String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");
            String ftpUrl = spaceName + resEntityFile.getRelativePath();
            String fileName = resEntityFile.getFileName();
            FTPClient ftp = getConnectedFTPClient();
            log.info("下载文件：" + fileName + "开始:" + System.currentTimeMillis());
            File localFile = new File(downUrl + "/" + fileName);
            if (localFile.getParentFile().exists()) {
                fileName = "新建" + fileName;
                localFile = new File(downUrl + "/" + fileName);
            } else {
                localFile.getParentFile().mkdirs();
                localFile.createNewFile();
            }
            OutputStream is = new FileOutputStream(localFile);
            try {
                ftp.retrieveFile(ftpUrl, is);
                log.info("下载文件：" + fileName + "成功:" + System.currentTimeMillis());
            } catch (IOException e) {
                log.info("下载文件失败", e.getMessage());
            } finally {
                is.close();
                ftp.logout();
            }

        }

    }


    /**
     * @Description: ftp文件预览
     * @Param: * @param resEntityFile
     * @return: java.lang.String
     * @Author: cmb
     * @Date: 2020/10/30
     */
//openoffice软件管理器
    private static OfficeManager officeManager;

    //openOffice软件安装目录
    public String preView(ResEntityFile resEntityFile) {
//        启动连接
        FTPClient ftp = getConnectedFTPClient();
        String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");

        String ftpUrl = spaceName + resEntityFile.getRelativePath();
        String substring = ftpUrl.substring(ftpUrl.indexOf(spaceName), ftpUrl.indexOf("."));
        System.out.println(substring);
        //文件所在路径 F:/ftpFile/education/2020-10-30/cf80188ea7b3f355ab28665ecf5b310d.xlsx
        String prefix = PropertiesUtil.getProperty("storage.properties", "ftp.local.prefixUrl");
        String pdfPath = substring + ".pdf";
        String inputFile = prefix + ftpUrl;
        String preViewFile = prefix + pdfPath;
        File file = new File(preViewFile);
        if (file.exists()) {
            return preViewFile;
        }
        startOffice(spaceName);
        System.out.println("进行文档转换转换:" + inputFile + " --> " + preViewFile);
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        converter.convert(new File(inputFile), new File(preViewFile));
        stopService();
        return preViewFile;
    }

    private static void startOffice(String spaceName) {
        String officeHome = PropertiesUtil.getProperty("storage.properties", "office.home");
        String portStr = PropertiesUtil.getProperty("storage.properties", "office.port");
        Integer port = Integer.valueOf(portStr);
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        try {
            System.out.println("准备启动服务....");
            configuration.setOfficeHome(officeHome);// 设置OpenOffice.org安装目录
            configuration.setPortNumbers(port); // 设置转换端口，默认为8100
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// 设置任务执行超时为5分钟
            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时为24小时
            officeManager = configuration.buildOfficeManager();
            officeManager.start(); // 启动服务
            System.out.println("office转换服务启动成功!");
        } catch (Exception ce) {
            System.out.println("office转换服务启动失败!详细信息:" + ce);
        }
    }

    public static void stopService() {
        System.out.println("关闭office转换服务....");
        if (officeManager != null) {
            officeManager.stop();
        }
        System.out.println("关闭office转换成功!");
    }


}