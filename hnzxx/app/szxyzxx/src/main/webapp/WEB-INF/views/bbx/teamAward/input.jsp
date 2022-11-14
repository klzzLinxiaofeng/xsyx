<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>添加荣誉</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link rel="stylesheet" href="${ctp }/res/css/bbx/bbx.css">
<style type="text/css">
	.form-horizontal .control-group{padding: 0 15px;}
	.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.biao-file{padding:0;}
.uploadify{opacity:0;position:absolute;top:0;left:0}
#SWFUpload_0{position:relative;left:0}
#uploderImg img{width:220px;height:135px;}	
</style>
</head>
<script type="text/javascript">
	function closeframe(){
		$.closeWindow();
	}

</script>
<body>
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="teamAward_form">
						<input type="hidden" value="${teamId }" id="teamId"/>
						<div class="control-group" style="position:relative">
						       <c:choose>
									<c:when test="${not empty teamAwardVo.awardImage  }">
										<p id="viewImg" style='position:absolute; top:1px;left:23px;margin:0;padding:0;width:220px;height:135px;overflow:hidden;'>
										    <img src="${teamAwardVo.awardImageURL }" alt="img" style="width:220px;height:135px;z-index:4;position:relative;" />
										</p>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCk != '' }"></c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
											  
									
									
 								<!-- <input type="file" id="uploader" name="uploader" style="height:59px;" >  -->
							   <span class="btn left btn-file biao-file" style="position:relative;width:220px;height:135px;line-height:113px;">
							    <span > <a class="close_pic" onclick="close_img()" href="javascript:void(0)" style="z-index:99999;position:absolute;right:3px;top:3px;border:1px solid #999;font-size:12px;font-weight:bold;text-align:center;display:block;color:#999;background-color:#fff;z-index:10;width:14px;line-height:14px;height:14px;" >X</a>
							    <div class="jiangzhuang" style="position:relative;width:220px;height:135px;z-index:1;">
						        	<img src="${ctp }/res/css/bbx/images/bbx_rongyu.png" alt="img" style="position:absolute;top:0;left:0;width:220px;height:135px;" />
						        	<p class="p1" style="position:absolute;text-align:center;left:16px;width:186px;overflow:hidden;top:55px;height:40px;line-height:20px;color:#69521E;font-family:'STXingkai';font-weight:bold;font-size:20px;padding-top:0">${teamAwardVo.name }</p>
<%-- 						        	<p class="p2" style="position:absolute;font-size:10px;-webkit-transform:scale(0.8);padding-top:0;top:97px;right:27px;line-height:15px;"><fmt:formatDate pattern="yyyy-MM-dd   " value="${teamAwardVo.awardTime }"></fmt:formatDate></p> --%>
						        </div> 
							   <div style="z-index:3;position:absolute;top:0;left:0;width:220px;">
							   <span id="uploderImg" ></span>
											  <input type="hidden" id="uploader" name="uploader"/>
									<input type="hidden" id="entityId" name="awardImage" value="${teamAwardVo.awardImage }"/>
								<strong style="font-size:24px;">+</strong>
								<span class="fileupload-new" >添加图片</span>
								<c:if test="${teamAwardVo.awardImageURL!='' && teamAwardVo.awardImageURL!=null  }">
								  
							      <%--  <input type="hidden" value="${teamAwardVo.awardImage}" id="awardImage" name="awardImage"/>
								   <img src="${teamAwardVo.awardImageURL }" alt="img" style="position:absolute;top:0;left:0;width:248px;height:149px;z-index:9" /> --%>
						   			</span>
						   		</c:if> 
						   		</div>
							</span> 
						
						</div>
					
							<div class="control-group">
								<input type="text" id="name" name="name"  class="span12 left_red {required : true,maxlength:50}" placeholder="请输入所获荣誉名称,少于50个中文字符" value="${teamAwardVo.name }">
							</div>
						
							<div class="control-group">
						    	<input class="span12 left_red {required:true}" id="awardTime" name="awardTime" data-format="yyyy-MM-dd HH:mm:ss"  onclick="WdatePicker({maxDate:end});" type="text" placeholder="请输入获得时间" value="<fmt:formatDate pattern="yyyy-MM-dd   " value="${teamAwardVo.awardTime }"></fmt:formatDate>"/>
						    </div>
						<div class="form-actions tan_bottom_1">
						      <input type="hidden" id="id" name="id" value="${teamAwardVo.id }" />
						        <c:choose>
						         <c:when test="${teamAwardVo!=null}">
						         	<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">保存</a>
						         </c:when>
						         <c:otherwise>
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">发表</a>
						         </c:otherwise>
						        </c:choose>
								<a href="javascript:void(0)" onclick="closeframe();">取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	var end = new Date();
	var checker;
	 $(function() {
		 $(".close_pic").click(function(){
			$("#entityId").val("");
			$("#viewImg img").remove();
			
		}) 
		
// 		 $("#name").keyup(function(){
// 			 $(".p1").text($("#name").val());
// 			});
		uploadFile();
		checker = initValidator();
	});
	
	function close_img(){
		$("#uploderImg img").remove();
		$("#entityId").val("");
		
		
	}
	
	function initValidator() {
		return $("#teamAward_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	
	//图片上传
	function uploadFile(){
	var obj = $("#uploader").uploadify({
        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
        uploader: '${pageContext.request.contextPath}/uploader/common',
        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
        fileObjName: 'file',
        fileTypeDesc: "文件上传",
        fileTypeExts: "*.gif; *.jpg; *.png;*.jpeg;*.bmp", //默认*.*
        method: 'post',
        multi: false, // 是否能选择多个文件
        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
        removeTimeout: 1,
        queueSizeLimit: 1,
        fileSizeLimit: 10 * 1024,
        buttonText: "上传文件",
        requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
        height: 135,
        width: 220,
        onUploadSuccess: function(file, data, response) {
//         	alert(JSON.stringify(data));
       	 var $jsonObj = eval("(" + data + ")");
       	 var imgHtml = "<img src='"+ $jsonObj.url +"'/><span> <a class='close_pic' onclick='close_img()' id='close_pic' href='javascript:void(0)' style='position:absolute;right:3px;top:3px;border:1px solid #999;font-size:12px;font-weight:bold;text-align:center;display:block;color:#999;background-color:#fff;z-index:10;width:14px;line-height:14px;height:14px;'>X</a>";
       	 $("#entityId").val($jsonObj.uuid);
//        	 $("#a").text($jsonObj.realFileName);
		$("#viewImg").html("");
       	 $("#uploderImg").html(imgHtml);
       	 $("#a").attr('href',$jsonObj.url); 
       	 $("#a").attr('target','_blank');
       	
        },
        onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
            $("#infoBox").prev("p").css("display", "none");
            $("#infoBox").css("display", "block");
        },
        onUploadError: function(file, errorCode, errorMsg, errorString) {
            $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        }
    });
	
}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var teamId = $("#teamId").val();
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#teamAward_form");
			if (teamId != null && teamId!="") {
				$requestData.teamId = teamId;
			}
			var img = $("#entityId").val();
			if(img == ""){
				$.error("请上传图片");
			}else{
				var url = "${ctp}/bbx/teamAward/creator";
				if ("" != $id) {
					$requestData._method = "put";
					url = "${ctp}/bbx/teamAward/" + $id;
				}
				loader.show();
	// 			alert(JSON.stringify($requestData));
				$.post(url, $requestData, function(data, status) {
					if ("success" === status) {
						$.success('操作成功');
						parent.core_iframe.search();
						$.closeWindow();
						/* data = eval("(" + data + ")");
						if ("success" === data.info) {
							if (parent.core_iframe != null) {
								parent.core_iframe.window.location.reload();
							} else {
								parent.window.location.reload();
							}
							$.closeWindow();
						} else {
							$.error("操作失败");
						} */
					} else {
						$.error("操作失败");
					}
					loader.close();
				});
			}

		}
	}
</script>
</html>