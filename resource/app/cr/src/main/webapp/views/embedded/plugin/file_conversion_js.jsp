<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author hmzhang
//@date 2015-04-21
(function($){
	//获取文档转换结果
	//conditionJson 需提供 entityFileUUID（即res_entity_file的uuid字段）
	//afterHandler 有入参
	$.getConversionResult = function(conditionJson,afterHandler){
		$.get("${ctp}/fileConversion/getConversionResult",conditionJson,function(data,status){
			if("success" === status) {
				data = eval("(" + data + ")");
				afterHandler(data);
			}
		});
	}
	
	//将单个文档提交到转换队列
	//conditionJson 需提供 entityFileUUID（即res_entity_file的uuid字段）
	//afterHandler 无入参
	$.submitSingleFile = function(conditionJson,afterHandler){
		$.get("${ctp}/fileConversion/submit",conditionJson,function(data,status){
			if("success" === status) {
				afterHandler();
			}
		});
	}
	
	//将多个文档提交到转换队列
	//conditionJson 需提供 entityFileUUID（即res_entity_file的uuid字段）数组 
	//afterHandler 无入参
	$.submitBatchFile = function(conditionJson,afterHandler){
		$.post("${ctp}/fileConversion/batchSubmit",conditionJson,function(data,status){
			if("success" === status) {
				afterHandler();
			}
		});
	}
	
	//获取视频转换结果
	//conditionJson 需提供 entityFileUUID（即res_entity_file的uuid字段）
	//afterHandler 有入参
	//author hmzhang
	$.getJaveConversionResult = function(conditionJson,afterHandler){
		$.get("${ctp}/fileConversion/jave/getConversionResult",conditionJson,function(data,status){
			if("success" === status) {
				data = eval("(" + data + ")");
				afterHandler(data);
			}
		});
	}
	
	//将单个视频提交到转换队列
	//conditionJson 需提供 entityFileUUID（即res_entity_file的uuid字段）
	//afterHandler 无入参
	//author hmzhang
	$.submitJaveSingleFile = function(conditionJson,afterHandler){
		$.get("${ctp}/fileConversion/jave/submit",conditionJson,function(data,status){
			if("success" === status) {
				afterHandler();
			}
		});
	}
	
	//将多个视频提交到转换队列
	//conditionJson 需提供 entityFileUUID（即res_entity_file的uuid字段）数组 
	//afterHandler 无入参
	//author hmzhang
	$.submitBatchFile = function(conditionJson,afterHandler){
		$.post("${ctp}/fileConversion/jave/batchSubmit",conditionJson,function(data,status){
			if("success" === status) {
				afterHandler();
			}
		});
	}
	
	
})(jQuery);
</script>
