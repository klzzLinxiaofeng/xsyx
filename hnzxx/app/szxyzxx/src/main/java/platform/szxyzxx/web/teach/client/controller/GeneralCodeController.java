package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalcode.model.Item;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.ExtGcItemVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * API接口模块
 * 基础代码类
 * @date 2016-01-15
 * @author xiemeijie
 *
 */

@Controller
@RequestMapping("/generalCode")
public class GeneralCodeController extends BaseController{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 功能描述：根据输入指定的基础代码分类获取其所有子项内容
	 * 2016-01-14
	 * @param tableCode
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object getGcItemList(
			@RequestParam(value = "code", required = false) String tableCode) {
		
		List<ExtGcItemVo> iteams = new ArrayList<ExtGcItemVo>();
		
		try {
			if(tableCode == null || "".equals(tableCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("tableCode");
				return new ResponseError("060111", info);
			}
			
			List<Item> itemList = this.jcGcCacheService.findItemsByTableCode(tableCode);
			if(itemList.size() > 0) {
				for(Item item : itemList) {
					ExtGcItemVo itemVo = new ExtGcItemVo();
					itemVo.setName(item.getName());
					itemVo.setValue(item.getValue());
					iteams.add(itemVo);
				}
				
				return new ResponseVo<List<ExtGcItemVo>>("0", iteams);
				
			}else {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("根据基础代码分类获取其所有子项内容，找不到数据");
				info.setMsg("找不到数据");
				return new ResponseError("020101", info);
			}
			
			
		} catch (Exception e) {
			log.info("通过tableCode获取基础代码分类的所有子项列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过tableCode获取基础代码分类的所有子项列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
}
