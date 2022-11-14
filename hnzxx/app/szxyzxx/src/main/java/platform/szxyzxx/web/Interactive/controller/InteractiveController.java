package platform.szxyzxx.web.Interactive.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import platform.education.paper.model.PaperResult;
import platform.szxyzxx.web.bbx.client.vo.ResponseError;
import platform.szxyzxx.web.bbx.client.vo.ResponseInfo;

@Controller
@RequestMapping(value = "/Interactive/data")
public class InteractiveController {
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object uploadXep(@RequestParam("file") MultipartFile xepFile,
			@RequestParam("userId") Integer userId,
			@RequestParam("schoolId") Integer schoolId,
			HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		/**判断必填参数是否为null*/
		if(xepFile==null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("上传文件失败！");
			info.setMsg("服务器异常");
			return new ResponseError("000002", info);
		}
		ResponseInfo info = new ResponseInfo();
		info.setDetail("数据上传成功");
		info.setMsg("数据上传成功");
		return new ResponseError("0", info);
	}
}
