package platform.szxyzxx.web.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import platform.education.resource.utils.UUIDUtil;

/**
 * <p>Title:FileOperateUtil.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：文件上传下载处理
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年8月5日
 */
public class FileOperateUtil {
	public static final String REALNAME = "realName";
	public static final String STORENAME = "storeName";// 存储名
	public static final String ALLSTORENAME = "allStoreName";// 服务器完整的存储名
	public static final String BACKSTORENAME = "backStoreName";// 返回前台的服务器存储名
	public static final String SIZE = "size";
	public static final String SUFFIX = "suffix";
	public static final String CONTENTTYPE = "contentType";
	public static final String CREATETIME = "createTime";
	public static final String UPLOADDIR = "uploadDir";
	public static final String FIlEUPLOADDIR = "fileUploadDir";
	public static final String IMGUPLOADDIR = "imgUploadDir";
	public static final String DOWNLOADDIR = "template";

	/**
	 * @Method rename
	 * @Function 功能描述：将上传的文件进行重命名
	 * @param name
	 * @return
	 * @Date 2014年8月5日
	 */
	private static String rename(String name) {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;

		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}

		return fileName;
	}

	/**
	 * @Method zipName
	 * @Function 功能描述：压缩后的文件名
	 * @param name
	 * @return
	 * @Date 2014年8月5日
	 */
	private static String zipName(String name) {
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));
		} else {
			prefix = name;
		}
		return prefix + ".zip";
	}

	/**
	 * @Method upload
	 * @Function 功能描述：上传文件后压缩
	 * @param request
	 * @param params
	 * @param values
	 * @return
	 * @throws Exception
	 * @Date 2014年8月5日
	 */
	public static List<Map<String, Object>> upload(HttpServletRequest request,
			String[] params, Map<String, Object[]> values) throws Exception {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();

		String uploadDir = request.getSession().getServletContext()
				.getRealPath("/")
				+ FileOperateUtil.UPLOADDIR;
		File file = new File(uploadDir);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = null;
		int i = 0;
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
				.iterator(); it.hasNext(); i++) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();

			fileName = mFile.getOriginalFilename();

			String storeName = rename(fileName);

			String noZipName = uploadDir + storeName;
			String zipName = zipName(noZipName);

			// 上传成为压缩文件
			ZipOutputStream outputStream = new ZipOutputStream(
					new BufferedOutputStream(new FileOutputStream(zipName)));
			outputStream.putNextEntry(new ZipEntry(fileName));
			outputStream.setEncoding("GBK");

			FileCopyUtils.copy(mFile.getInputStream(), outputStream);

			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对
			map.put(FileOperateUtil.REALNAME, zipName(fileName));
			map.put(FileOperateUtil.STORENAME, zipName(storeName));
			map.put(FileOperateUtil.SIZE, new File(zipName).length());
			map.put(FileOperateUtil.SUFFIX, "zip");
			map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
			map.put(FileOperateUtil.CREATETIME, new Date());

			// 自定义参数值对
			for (String param : params) {
				map.put(param, values.get(param)[i]);
			}

			result.add(map);
		}
		return result;
	}

	/**
	 * @Method uploadFile
	 * @Function 功能描述：上传文件，图片，返回文件名
	 * @return
	 * @throws Exception
	 * @Date 2014年8月4日
	 */
	public static Map<String, Object> uploadFile(HttpServletRequest request,
			String dir, MultipartFile myfile) throws Exception {
		String uploadDir = request.getSession().getServletContext()
				.getRealPath("/")
				+ dir;
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		String uuid = UUIDUtil.getUUID();
		String oriName = myfile.getOriginalFilename();
		// 文件名后缀处理---start---
		String _suffix = oriName.substring(oriName.lastIndexOf(".") + 1,
				oriName.length());
		// -----end---
		// ---重新处理文件名start---
		String suffix = oriName.substring(oriName.lastIndexOf("."),
				oriName.length());
		String newFileName = uuid + suffix;

		myfile.transferTo(new File(uploadDir + "/" + newFileName));
		// 固定参数值对
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FileOperateUtil.REALNAME, oriName);
		map.put(FileOperateUtil.STORENAME, newFileName);
		map.put(FileOperateUtil.ALLSTORENAME, uploadDir + "/" + newFileName);
		map.put(FileOperateUtil.BACKSTORENAME, "/" + dir + "/" + newFileName);
		map.put(FileOperateUtil.SIZE, myfile.getSize());
		map.put(FileOperateUtil.SUFFIX, _suffix);
		map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
		map.put(FileOperateUtil.CREATETIME, Calendar.getInstance().getTime());
		map.put(FileOperateUtil.UPLOADDIR, uploadDir);
		return map;
	}

	/**
	 * @Method download
	 * @Function 功能描述：下载
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 * @Date 2014年8月5日
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/");
		String downLoadPath = ctxPath  + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		 String name = realName;  
	        String userAgent = request.getHeader("User-Agent");  
	        byte[] bytes = userAgent.contains("MSIE") ? name.getBytes() : name.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题  
	        name = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码  
	        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", name)); // 文件名外的双引号处理firefox的空格截断问题  
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}
