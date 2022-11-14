package platform.education.commonResource.web.common.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.basis.converter.doc.client.service.DocConversionClient;
import platform.basis.converter.doc.client.support.JobCompletedHandlerImpl;
import platform.basis.converter.jave.client.service.JaveConversionClient;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.service.storage.contants.ConversionVideoType;
import platform.service.storage.service.ConversionStatusService;
import platform.service.storage.service.ConversionVideoService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileConversionResult;
import platform.service.storage.vo.FileResult;

@Controller
@RequestMapping(value = "/fileConversion")
public class FileConversionController {
	
	@Resource
	private FileService fileService;
	@Resource
	private DocConversionClient docConversonClient;
	@Resource
	private ConversionStatusService conversionStatusService;
	@Resource
	private JaveConversionClient javeConversonClient;
	@Resource
	private ConversionVideoService conversionVideoService;
	/**
	 * 提交单个文件进入文件转换进程
	 * @param entityFileUUID 文件的uuid，res_entity_file的uuid字段
	 * @author 陈业强
	 */
	@RequestMapping(value = "/submit")
	@ResponseBody
	public void submit(@RequestParam(value = "entityFileUUID",required = true)String entityFileUUID){
		docConversonClient.submitConversionJob(entityFileUUID,SysContants.MAX_PRIORITY, SysContants.SYSTEM_APP_ID + "",new JobCompletedHandlerImpl());
	}
	
	/**
	 * 提交批量文件进入文件转换进程
	 * @param entityFileUUID 文件的uuid，res_entity_file的uuid字段
	 * @author 陈业强
	 */
	@RequestMapping(value = "/batchSubmit")
	@ResponseBody
	public void submit(@RequestParam(value = "entityFileUUID[]",required = true)String[] entityFileUUID){
		docConversonClient.submitConversionJob(entityFileUUID, SysContants.SYSTEM_APP_ID + "",new JobCompletedHandlerImpl());
	}
	
	/**
	 * 获取文档转换结果
	 * @param entityFileUUID 文件的uuid，res_entity_file的uuid字段
	 * @return Map<String,Object> 
	 * 				result : 文档转换状态; pdfUrl : 转换后的pdf文件路径; htmlUrl : 转换后的html文件路径
	 * 				pdfUuid : pdf的res_entity_file uuid; htmlUuid : html的res_entity_file uuid;
	 * @author 陈业强
	 */
	@RequestMapping(value = "/getConversionResult")
	@ResponseBody
	public Map<String,Object> getConversionFile(@RequestParam(value = "entityFileUUID",required = true)String entityFileUUID){
		FileConversionResult result = conversionStatusService.getTargetFileBySourceUUid(entityFileUUID);
		Integer conversionResult = result.getConversionResult();
		String pdfUrl = null;
		String htmlUrl = null;
		String pdfUuid = null;
		String htmlUuid = null;
		Map<Integer,Map<Integer,String>> resultMap = result.getResultMap();
		if(resultMap != null){
			if(resultMap.containsKey(1)){
				Map<Integer,String> uuidMap = resultMap.get(1);
				if(!uuidMap.isEmpty() && uuidMap != null){
					if(resultMap.containsKey(1)){
						pdfUuid = uuidMap.get(1);
						if(pdfUuid != null && !"".equals(pdfUuid)){
							FileResult file = fileService.findFileByUUID(pdfUuid);
							if(file != null ){
								pdfUrl = file.getHttpUrl();
							}
						}
					}
					if(resultMap.containsKey(1)){
						htmlUuid = uuidMap.get(1);
						if(htmlUuid != null && !"".equals(htmlUuid)){
							FileResult file = fileService.findFileByUUID(htmlUuid);
							if(file != null ){
								pdfUrl = file.getHttpUrl();
							}
						}
					}
				}
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", conversionResult);
		map.put("pdfUuid", pdfUuid);
		map.put("htmlUuid", htmlUuid);
		map.put("pdfUrl", pdfUrl);
		map.put("htmlUrl", htmlUrl);
		return map;
	}
	
	/**
	 * 提交流媒体转换任务
	 * @Title: javeSubmit 
	 * @Description: 提交流媒体转换任务
	 * @param entityFileUUID 文件的uuid，res_entity_file的uuid字段
	 * @return: void
	 * @author hmzhang
	 * @date 2016-11-03
	 */
	@RequestMapping(value = "/jave/submit")
	@ResponseBody
	public void javeSubmit(@RequestParam(value = "entityFileUUID",required = true)String entityFileUUID){
		javeConversonClient.submitJaveConversionJob(entityFileUUID);
	}
	
	/**
	 * 批量提交流媒体任务
	 * @Title: javeBatchSubmit 
	 * @Description: 批量提交流媒体任务
	 * @param entityFileUUID 文件的uuid，res_entity_file的uuid字段
	 * @return: void
	 * @author hmzhang
	 * @date 2016-11-03
	 */
	@RequestMapping(value = "/jave/batchSubmit")
	@ResponseBody
	public void javeBatchSubmit(@RequestParam(value = "entityFileUUID[]",required = true)String[] entityFileUUID){
		for(String uuid : entityFileUUID){
			javeConversonClient.submitJaveConversionJob(uuid);
		}
	}
	
	/**
	 * 获取视频转换的结果
	 * @Title: javeGetConversionFile 
	 * @Description: 获取视频转换的结果
	 * @param entityFileUUID
	 * @return
	 * @return: Map<Integer,String>
	 * @author hmzhang
	 * @date 2016-11-03
	 */
	@RequestMapping(value = "/jave/getConversionResult")
	@ResponseBody
	public Map<String, String> javeGetConversionFile(@RequestParam(value = "entityFileUUID",required = true)String entityFileUUID){
		Map<Integer, String> resultMap = this.conversionVideoService.getTargetFileBySourceUUid(entityFileUUID);
		Map<String, String> conversionMap = new LinkedHashMap<String, String>();
		for(Integer type : resultMap.keySet()){
			
			if(ConversionVideoType.HD.equals(type)){
				conversionMap.put(ConversionVideoType.HD_US_TAG_STR, fileService.relativePath2HttpUrlByUUID(resultMap.get(type)));
			}
			if(ConversionVideoType.SD.equals(type)){
				conversionMap.put(ConversionVideoType.SD_US_TAG_STR, fileService.relativePath2HttpUrlByUUID(resultMap.get(type)));
			}
			if(ConversionVideoType.MOBILE.equals(type)){
				conversionMap.put(ConversionVideoType.MOBILE_US_TAG_STR, fileService.relativePath2HttpUrlByUUID(resultMap.get(type)));
			}
			
		}
		return conversionMap;
	}
	
}
