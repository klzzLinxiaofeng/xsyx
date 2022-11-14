<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ include file="/views/embedded/common.jsp"%>
<c:choose>
	<c:when test="${type=='undisposed' }">
		<div class="sz_2 " >
			<textarea rows="6" cols="" placeholder="驳回理由" id="nontreat_${id }" ></textarea>
		</div>
		<div class="sz_1">
			<a href="javascript:void(0)" class="a4 on" id="ti_${id }"
				onclick="tijiao('${type }','${id}')">提交</a>
		</div>
	</c:when>
	<c:when test="${type=='pending' }">
		<div class="sz_2">
			<input type="hidden"
				value="<fmt:formatDate pattern="yyyy-MM-dd "
						value="${publishDate }"></fmt:formatDate>"
				id="publishDate" name="publishDate" /> <input type="text"
				class="span12" placeholder="预计完成时间"
				onclick="WdatePicker({minDate:'#F{$dp.$D(\'publishDate\')}'});"
				id="yuji_${id }" />
		</div>
		<div class="sz_1">
			<a href="javascript:void(0)" class="a4 on" id="ti1_${id}"
				onclick="tijiao('${type }','${id}')">提交</a>
		</div>
	</c:when>
	<c:when test="${type=='deal' }"></c:when>
</c:choose>

<script>
	function tijiao(type, id) {
		var url = "${pageContext.request.contextPath}/office/applayIndia/submit";
		var val = {};
		if (type == "undisposed") {
// 			$("#ti_" + id).addClass("on");
			var nontreat = $("#nontreat_" + id).val();
			val = {
				"type" : type,
				"id" : id,
				"nontreat" : nontreat
			};
		} else if (type == "pending") {
// 			$("#ti1_" + id).addClass("on");
			var yuji = $("#yuji_" + id).val();
			val = {
				"type" : type,
				"id" : id,
				"yuji" : yuji
			};
		}
     
		if(type=="undisposed" ){
			if(val.nontreat.length>140){
				$.error("驳回理由不得多于140个中文字符!");
				return;
			}else{
				$.post(url, val, function(data, status) {
					if ("success" === status) {
						$.success('提交成功');
						window.location.reload();
					} else {
						$.error("提交失败");
					}
				});
				
			}
		}else{
			$.post(url, val, function(data, status) {
				if ("success" === status) {
					$.success('提交成功');
					window.location.reload();
				} else {
					$.error("提交失败");
				}
			});
			
		}
		

	}
</script>