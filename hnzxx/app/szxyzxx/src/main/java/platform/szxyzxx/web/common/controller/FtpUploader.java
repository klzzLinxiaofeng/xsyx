package platform.szxyzxx.web.common.controller;

import framework.generic.storage.StorageResult;
import framework.generic.storage.util.FileUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import platform.education.paper.model.ResponseEntity;
import platform.education.resource.contants.UploadFinishedFlag;
import platform.education.resource.utils.DownloadUtil;
import platform.service.storage.code.StreamReuse;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.utils.FileMd5Util;
import platform.service.storage.utils.UUIDUtil;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.SessionManager;
import platform.szxyzxx.web.common.vo.EntityFileVo;
import platform.szxyzxx.web.common.vo.ImgResponseVo;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/uploader")
public class FtpUploader extends BaseController {

    Logger log = LoggerFactory.getLogger(FtpUploader.class);

    @Autowired
    @Qualifier("fileService")
    private FileService fileService;

    /**
     * general ??????????????????
     *
     * @param file
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/common", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadV2(@RequestParam("file") MultipartFile file,
                           HttpServletRequest request) throws IOException {
        String jsessionId = request.getParameter("jsessionId");
        String editor = request.getParameter("editor");
        ImgResponseVo vo = new ImgResponseVo();
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
            if (userInfo != null) {
                FileResult result = this.fileService.upload(
                        file.getInputStream(),
                        StringUtils.getFilenameExtension(uploadFileName),
                        file.getContentType(), uploadFileName,
                        String.valueOf(SysContants.SYSTEM_APP_ID));
                if (result != null
                        && FileStatusCode.UPLOAD_SUCCESS.equals(result
                        .getStatusCode())) {
                    EntityFile entityFile = result.getEntityFile();
                    EntityFileVo fileVo = new EntityFileVo();
                    copyEntityFileProperties(entityFile, fileVo);
                    fileVo.setUrl(result.getHttpUrl());
                    vo.setUrl(result.getHttpUrl());
                    vo.setError(0);
                    if ("editor".equals(editor)) {
                        return vo;
                    } else {
                        return fileVo;
                    }
                }
            } else {
                return ResponseInfomation.NO_LOGIN;
            }
        }
        if ("editor".equals(editor)) {
            vo.setError(1);
            vo.setMessage("????????????");
            return vo;
        } else {
            return ResponseInfomation.OPERATION_FAIL;
        }
    }


    /**
     * general ??????????????????
     *
     * @param file
     * @param jsessionId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/common2", method = RequestMethod.POST)
    @ResponseBody
    public Object common2(@RequestParam("file") MultipartFile file,
                          HttpServletRequest request) throws IOException {
        // String jsessionId = request.getParameter("jsessionId");
        try {
            String jsessionId = request.getSession().getId();
            if (file != null) {
                String uploadFileName = file.getOriginalFilename();
                UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
                if (userInfo != null) {
                    FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
                    if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
                        EntityFile entityFile = result.getEntityFile();
                        EntityFileVo fileVo = new EntityFileVo();
                        copyEntityFileProperties(entityFile, fileVo);
                        fileVo.setUrl(result.getHttpUrl());
                        return fileVo;
                    }
                } else {
                    return ResponseInfomation.NO_LOGIN;
                }
            }
            return ResponseInfomation.OPERATION_FAIL;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * general ??????????????????
     *
     * @param file
     * @param jsessionId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cropAvatarUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object cropAvatarUpload(@RequestParam("image") String image, @RequestParam("extension") String extension, HttpServletRequest request) {
        String jsessionId = request.getParameter("jsessionId");
        String contentType = "";
//		if(file.isEmpty()){
//			return ResponseInfomation.OPERATION_FAIL;
//		}
        ImgResponseVo vo = new ImgResponseVo();
        if (image != null) {
            String dataMeta = image.split(",")[0];
            contentType = dataMeta.substring(dataMeta.indexOf(":") + 1, dataMeta.indexOf(";"));
            image = image.split(",")[1];
        }
        Base64 decoder = new Base64();
        byte[] decodedBytes = decoder.decode(image);
        InputStream in = new ByteArrayInputStream(decodedBytes);
        UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
        if (userInfo != null) {
            Date date = new Date();
            String newFileName = date.getTime() + "." + extension;
            FileResult result = this.fileService.upload(in, extension, contentType, newFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
            if (result != null
                    && FileStatusCode.UPLOAD_SUCCESS.equals(result
                    .getStatusCode())) {
                EntityFile entityFile = result.getEntityFile();
                EntityFileVo fileVo = new EntityFileVo();
                copyEntityFileProperties(entityFile, fileVo);
                fileVo.setUrl(result.getHttpUrl());
                vo.setUrl(result.getHttpUrl());
                vo.setError(0);
                return fileVo;
            }
        } else {
            return ResponseInfomation.NO_LOGIN;
        }
        return ResponseInfomation.OPERATION_FAIL;
    }

    /**
     * ??????????????????
     *
     * @param file
     * @param jsessionId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bpru", method = RequestMethod.POST)
    @ResponseBody
    public Object continueUpload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) throws IOException {
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
            UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
            if (userInfo != null) {
                boolean isComplete = chunki == null && chunksi == null ? true
                        : (chunki == (chunksi - 1));
                FileResult result = this.fileService.resumeUpload(
                        file.getInputStream(),
                        StringUtils.getFilenameExtension(uploadFileName), md5,
                        file.getContentType(), uploadFileName,
                        String.valueOf(SysContants.SYSTEM_APP_ID), isComplete);
                if (result != null
                        && FileStatusCode.UPLOAD_SUCCESS.equals(result
                        .getStatusCode()) && isComplete) {
                    EntityFile entityFile = result.getEntityFile();
                    EntityFileVo fileVo = new EntityFileVo();
                    copyEntityFileProperties(entityFile, fileVo);
                    fileVo.setUrl(result.getHttpUrl());
                    fileVo.setFinishedFlag(UploadFinishedFlag.FINISHED);
                    return fileVo;
                }
            } else {
                return ResponseInfomation.NO_LOGIN;
            }
        }
        return null;
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String entityFileUUID = request.getParameter("entityFileUUID");
        String fileName = request.getParameter("fileName");
        EntityFile ent = null;
        if (entityFileUUID != null && !"".equals(entityFileUUID)) {
            ent = this.entityFileService.findFileByUUID(entityFileUUID);
        }
        if (ent != null) {
//			String suffix = ent.getExtension();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(fileName, "UTF-8"));
            response.addHeader("content-type", "application/x-download");
            response.setContentType("application/x-download");
            response.setContentLength(ent.getSize().intValue());
            response.setHeader("Content-Disposition", "attachment;filename="
                    + filename);
            OutputStream out = response.getOutputStream();
            // Integer flag = this.fileUploadService.downloadEntity(entId, out);
            String result = this.fileService.download(entityFileUUID, out);

            if (FileStatusCode.DOWNLOAD_SUCCESS.equals(result)) {
                // ????????????
            } else if (FileStatusCode.DOWNLOAD_FAIL_FILE_NOT_EXIT
                    .equals(result)) {
                // ?????????????????????
                // ??????????????????????????????????????????
            } else if (FileStatusCode.DOWNLOAD_FAIL_STREAM_ERR.equals(result)
                    || FileStatusCode.CONNECT_SERVER_FAIL.equals(result)) {
                // ????????????
                // ??????????????????????????????????????????
            }
        }
        return null;
    }

    /**
     * ??????md5??????
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/exist")
    public Object getFile(@RequestParam("md5") String md5,
                          HttpServletRequest request) {
        FileResult result = this.fileService.preResumeUpload(md5,
                String.valueOf(SysContants.SYSTEM_APP_ID));
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


    /**
     * @param appKey
     * @param fileMd5 ??????MD5
     * @return PaperResult    ????????????
     * @throws Exception ????????????
     * @throws
     * @Title: isExistFile
     * @author pantq
     * @Description: ?????????????????????????????????
     */

    @ResponseBody
    @RequestMapping("/existByFiles")
    public Object isExistFile(@RequestParam("appKey") String appKey, @RequestParam("fileMd5") String fileMd5, @RequestParam("source") String source) throws Exception {
        List<ResponseEntity> responseEntityList = null;
        //??????md5??????????????????","??????
        if (fileMd5 != null) {
            String[] md5s = fileMd5.split(",");
            String[] sources = source.split(",");
            //?????????????????????????????????????????????
            responseEntityList = new ArrayList<ResponseEntity>();
            for (int i = 0, len = md5s.length; i < len; i++) {
                EntityFile entityFile = this.entityFileService.findFileByMD5(md5s[i]);
                //??????????????????md5,uuid,url????????????????????????
                if (entityFile != null) { //?????????????????????
                    ResponseEntity responseEntity = new ResponseEntity();
                    String fileUuid = entityFile.getUuid();
                    responseEntity.setFileMd5(md5s[i]);
                    responseEntity.setFileUuid(fileUuid);
                    String fileUrl = fileService.relativePath2HttpUrlByUUID(fileUuid);
                    responseEntity.setFileUrl(fileUrl);
                    responseEntity.setIsExist(true);
                    responseEntity.setSource(sources[i]);
                    responseEntityList.add(responseEntity);
                } else {//????????????????????????
                    ResponseEntity responseEntity = new ResponseEntity();
                    responseEntity.setFileMd5(md5s[i]);
                    responseEntity.setSource(sources[i]);
                    responseEntity.setIsExist(false);
                    responseEntityList.add(responseEntity);
                }

            }

        }

        return new ResponseVo<List<ResponseEntity>>("0", responseEntityList);
    }


    /**
     * ?????????????????????????????????????????????????????????
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/commonLibrary", method = RequestMethod.POST)
    @ResponseBody
    public Object commonLibrary(@RequestParam("file") MultipartFile file,
                                HttpServletRequest request) throws IOException {
        String jsessionId = request.getParameter("jsessionId");
        String editor = request.getParameter("editor");
        ImgResponseVo vo = new ImgResponseVo();
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            UserInfo userInfo = SessionManager.getUserInfoShiro(jsessionId);
            if (userInfo != null) {
                return upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, 1);
            } else {
                return ResponseInfomation.NO_LOGIN;
            }
        }
        if ("editor".equals(editor)) {
            vo.setError(1);
            vo.setMessage("????????????");
            return vo;
        } else {
            return ResponseInfomation.OPERATION_FAIL;
        }
    }


    /**
     * ????????????
     *
     * @param input
     * @param extension
     * @param contentType
     * @param fileName
     * @param type        1????????????0?????????
     * @return
     */
    public FileResult upload(InputStream input, String extension, String contentType, String fileName, Integer type) {
        if (input == null) {
            throw new IllegalArgumentException("InputStream????????????null, ?????????????????????");
        } else if (extension != null && !StringUtils.isEmpty(extension)) {
            if (fileName == null) {
                throw new IllegalArgumentException("fileName?????????null, ?????????????????????");
            } else if (contentType == null) {
                throw new IllegalArgumentException("contentType?????????null, ?????????????????????");
            } else {
                EntityFile entityFile = null;
                String md5 = null;
                StreamReuse streamReuse = new StreamReuse(input);

                try {
                    input = streamReuse.getInputStream();
                    md5 = FileMd5Util.getFileMd5(input, 5);
                } catch (IOException var30) {
                    if (this.log.isInfoEnabled()) {
                        this.log.info("????????????????????????????????????md5?????????,???????????????:", var30);
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
                    StorageResult result;
                    // ????????????????????????????????????
                    if (type == 0) {
                        result = upload(uploadPath, name, input, type);
                    } else {
                        result = upload(uploadPath, fileName, input, type);
                    }

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

                        efResult.setEntityFile(entityFile);
                        efResult.setStatusCode("success");

                        // ????????????????????????????????????
                        if (type == 1) {
                            entityFile.setRelativePath(result.getRelativePath());
                        }
                        efResult.setHttpUrl(relativePath2HttpUrl(entityFile));
                        var16 = efResult;
                        return var16;
                    }

                    var16 = new FileResult(statusCode);
                    return var16;
                } catch (IOException var31) {
                    if (this.log.isInfoEnabled()) {
                        this.log.info("??????????????????????????????,???????????????:", var31);
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
            throw new IllegalArgumentException("extension?????????null ????????? ????????????, ?????????????????????");
        }
    }

    /**
     * @param path
     * @param fileName
     * @param input
     * @param type     1????????????0?????????
     * @return
     */
    public StorageResult upload(String path, String fileName, InputStream input, Integer type) {
        path = FileUtil.pathProcessor(path);
        log.info("path: " + path);
        StorageResult result = new StorageResult(true, "success");
        String dirPath = "";

        if (type == 0) {
            dirPath = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName") + path;
        } else {
            // ????????????????????????????????????
            dirPath = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName") + PropertiesUtil.getProperty("storage.properties", "ftp.library.path");
            path = PropertiesUtil.getProperty("storage.properties", "ftp.library.path") + "/";
        }

        log.info("dirPath:" + dirPath);
        FTPClient ftpClient = this.getConnectedFTPClient();
        try {
            if (ftpClient == null) {
                result = new StorageResult(false, "connectServerFail");
            } else {
                dirPath = FileUtil.pathProcessor(dirPath);
                this.createDir(dirPath, ftpClient);
                boolean storeFile = ftpClient.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1"), input);
                log.info("???????????????{}" + storeFile);
                if (storeFile) {
                    if (log.isInfoEnabled()) {
                        log.info("??????????????????: " + fileName + "???????????? " + dirPath, this);
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
            log.error("????????????: {}", var16.getMessage());
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
                log.error("???????????????");
            }
        }
        return result;
    }

    private String getHttpPrefix() {
        String path = PropertiesUtil.getProperty("storage.properties", "ftp.httpPrefix");
        String value = path != null && !("").equals(path) ? path : "ftp://192.168.1.97";
        return value;
    }

    /**
     * ????????????
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
            log.error("ftp: ????????????! {}??????????????????????????????" + var3.getMessage());
            return null;
        }
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
                            log.error("????????????" + subDirectory + "??????");
                            return false;
                        }
                        ftpClient.changeWorkingDirectory(subDirectory);
                    }
                    start = end + 1;
                    end = directory.indexOf("/", start);
                } while (end > start);
            }
        } catch (IOException var7) {
            log.error("??????????????????: {}", var7.getMessage());
        }

        return true;
    }


    public String relativePath2HttpUrl(EntityFile entityFile) {
        String spaceName = PropertiesUtil.getProperty("storage.properties", "ftp.spaceName");
        return entityFile != null ? getHttpPrefix() + "/" + spaceName + entityFile.getRelativePath() : "";
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
}
