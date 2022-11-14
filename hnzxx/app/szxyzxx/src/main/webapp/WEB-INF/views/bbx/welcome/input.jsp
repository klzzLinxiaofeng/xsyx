<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<style>

.ban-active a img.on {
	border: 2px solid red;
}
.delete_img{display:none;position: absolute;top: -9px;right: -4px;text-align: right;font-size: 20px;width: 13px;height: 20px;color: red;font-weight: bold;}
.ban-active img{width:186px;height:106px;}
.ban-active .feng{width:190px;}
.ban-active{margin-right:10px;}
.uploadify{position:absolute;top:0;opacity: 0;}

</style>
<body style="background-color: #F3F3F3 !important;">
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal padding15" id="welcomeTemplate" style="padding-right:0;">
						
						<div class="control-group">
						    <c:if test="${wtList.size()>0 }">

								<c:forEach items="${ wtList}" var="wt" varStatus="i">
									<div class="ban-active left">
										<a href="javascript:void(0)" class="feng"><img
											data-id="${wt.fileUuid }" src="${wt.fileURL }"></a>
										<div class="wenzi">系统模板${i.index+1}</div>
									</div>
								</c:forEach>
							</c:if>
							<c:if test="${wttList.size()>0 }">
									 <c:forEach items="${ wttList}" var="wtt" varStatus="j">
										<div class="ban-active left"  style="position:relative;">
											<a href="javascript:void(0)" class="feng test">
											  <img src="${wtt.fileURL }" data-id="${wtt.fileUuid }">
											</a>
											<a href="javascript:void(0)" class="delete_img" onclick="delDiv(this,${wtt.id})" >
									   	        <%-- <img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww" style='position:absolute;top:142px;left:0;height:110px;'> --%>
									   	        X
									   	    </a>
											<div class="wenzi">自定义模板${j.index+1 }</div>
										</div>
									 </c:forEach>
								  </c:if>
							<div class="ban-active left" style="position:relative">
								<a href="javascript:void(0)" class="feng"><img  src="${ctp }/res/css/bbx/images/zdy.jpg"></a>
								<input type="hidden" id="uploader"  />
								<div class="wenzi">自定义</div>
							</div>
						</div>
					</form>
					   <div class="form-actions tan_bottom_1">
						<a href="javascript:void(0)" class="yellow"
							onclick="saveOrUpdate();">确定</a> <a href="javascript:void(0)"
							onclick="closeWin();">取消</a>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
$(function(){
	
	$(".ban-active a img").on("click", function(){
		$(".ban-active a img").removeClass("on");
		$(this).addClass("on");
		imgId = $(this).attr("data-id");
		imgSrc = $(this).attr("src");
	});
	
	uploadFile();

	$(".test").hover(function(){
		  $(this).next().show();
		 },function(){
			 $(this).next().hide();
		 });

	
});


function saveOrUpdate() {
	 $("#fileUuid", parent.core_iframe.window.document).val(imgId);
	 $("#src",parent.core_iframe.window.document).attr("src",imgSrc);
	 $.closeWindow();
}

function closeWin(){
	$.confirm("确定离开此页面？", function() {
		$.closeWindow();
	});
}


//=========图片上传
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
        height: 110,
        width: 190,
        onUploadSuccess: function(file, data, response) {
       	 var $jsonObj = eval("(" + data + ")");
     	$.post("${pageContext.request.contextPath}/bbx/welcomeTemplate/findByTeamIdAndUUId", { fileUuid: $jsonObj.uuid,teamId:$("#teamId").val() },
	       		   function(data){
	       		      if(data==="success"){
	       		    	$.success('上传成功!');
	       		    	window.location.reload();
	       		      }else{
	       		    	$.error("该图片已存在，请重新上传!");
	       		      }
	       		  
	       		
	       		   }); 
       	
	    
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

function delDiv(obj,welcomeTemplateId){
	 $.post("${pageContext.request.contextPath}/bbx/welcomeTemplate/delete", { id: welcomeTemplateId },
       		   function(data){
       		      if(data==="success"){
       		    	$.success('删除成功!');
//        		    	$(obj).closest('div').parent().remove();
       		    	window.location.reload();
       		      }else{
       		    	$.error("服务器出错!");
       		      }
	 });  
       		
   
  }

</script>


</html>