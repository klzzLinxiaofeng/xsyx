<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link rel="stylesheet" href="${ctp }/res/css/bbx/bbx.css">
<style>
.btn{
    font-weight: bold;
}
.btn-white{
    background: #fff;color: #666;border:0px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.uploadify{
	position:absolute;
	top:0;
	opacity:0;
}
/* .edit ul li{
	position:relative;
}
.tp{
	position:absolute;
	left:0;
	top:67px;
} */
.fileName{
	width:40px;
	display:block;
	overflow:hidden;
}

.chzn-container{vertical-align:middle;margin-right:15px;}
.chzn-container .chzn-results li{line-height:25px;width:100%;}
.edit ul li{ height:auto;}
input[type="radio"], input[type="checkbox"]{ margin:0 4px;margin-left:6px;}
</style>
<script type="text/javascript">
    var select ="";
   
	$(function() {
		$(".zpzs-box li").hover(function() {
			$(this).find(".tp").show();
		}, function() {
			$(".zpzs-box li .tp").hide();
		})
	});

	function delDiv(a) {
		var divA = document.getElementById(a);
		var p = divA.parentElement

		p.parentNode.removeChild(p);
	}
	function delSpan() {
		var divA = document.getElementById("divA");
		divA.innerText = "";
	};

	$(function() {
		$("#shijian ").on("click","div",function() {
							$("#shijian div").removeClass('on');//创建类名
							$("#shijian div").each(function() {//遍历
								$(this).children('img').attr('src','${ctp}/res/css/bbx/images/wei.png');//清除样式
								
							});
							var imgSrc = $(this).children('img').attr('src','${ctp}/res/css/bbx/images/selected.png');//替换并添加样式
								$(this).addClass('on');//添加类名

					 		});
		

		$(".remove .tz_select").on("click","span img",function(){
	        $(".remove .tz_select span img").attr('src','${ctp}/res/css/bbx/images/wei.png');//清除样式
	        $(this).attr('src','${ctp}/res/css/bbx/images/selected.png');//替换并添加样式
	        var i=$(this).parent().index();
	        if(i==1){
	        	select="1";
	            $(".nj_select").show();
	        }else{
	        	select="0"
	            $(".nj_select").hide();
	            $('.lan').each(function(){
		            $(this).removeClass("lan");
				});
					
	        }
	    })
			})
</script>
</head>
<body style="background-color: #F3F3F3 !important;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 0;">
					<form class="form-horizontal tan_form" id="teamMessage_form" action="javascript:void(0);">
					   <input type="hidden" id="teamId" name="teamIds"/>
					   <input type="hidden" id="entityId" name="uuids"/>
					   
						<div class="trend">
							<div class="edit">
								<div class="control-group" id="classMasterSearch" style="margin-top: 20px; margin-left: 20px">
									<select  id="bj" name="teamId1" class="span4 chzn-select" style="width: 120px;"></select>
								</div>	
								<div class="control-group" id="schoolManagerSearch" style="margin-top: 20px; margin-left: 20px">
									<div hidden><select id="xn"></select></div>
									<span style="margin-right:2px;">年级</span>
									<select id="nj" name="gradeId" style="width:160px;"></select>
									<span style="margin-right:2px;">班级</span>
									<select id="bj" name="teamId" style="width:160px; margin-left:15px;vertical-align:bottom;"></select>
								</div>									
								
								<div style="padding: 0 20px; 0 20px;">
									<textarea placeholder="请输入通知详细内容，1000个中文字符以内"
										class="span12 left_red {required : true,maxlength:1000}" id="contentt" name="contentt"></textarea>
									<input type="hidden" value="" id="content" name="content"/>	
								</div>
								
								<div class="control-group" style="padding: 0 20px;">
									<input type="radio" id="check" name="check" onclick="clickRadio(1)" value="1">立即发布
									<input type="radio" id="check" name="check" onclick="clickRadio(2)" value="2">
									<input style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
											class="sj_time" id="startTime" name="startTime"
											onclick="WdatePicker({minDate:'%y-%M-%d %H:%m', dateFmt:'yyyy-MM-dd HH:mm'});"
											placeholder="触发时间" value="" type="text" disabled="disabled">
									<input style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
											class="sj_time" id="finishTime" name="finishTime"
											onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{m:+5})}', dateFmt:'yyyy-MM-dd HH:mm'});"
											placeholder="结束时间" value="" type="text">
								</div>
								
								<div style="padding:0 20px;">
									<ul class="zpzs-box" style="padding:0"></ul>
									<div style=" float:left;position:relative;">
										<a href="#" class="tianjia"> <img src="${ctp}/res/css/bbx/images/add.jpg">
										</a> <input type="hidden" id="uploader" />
									</div>
								</div>
								<div class="clear"></div>
									 <%-- <div class="remove" style="border-bottom: 1px #dfdfdf solid;border-top: 1px #dfdfdf solid;" >
									  <c:choose>
									    <c:when test="${tList.size()>0 }">
									        <c:forEach items="${tList }" var="tl">
										       <button onclick="opt(this,'${tl.id}')" id="${tl.id }"  <c:if test="${tl.id==t.id }">class='lan'</c:if> >${tl.name }</button>
										    </c:forEach>
									    </c:when>
									    <c:otherwise>无相关班级!</c:otherwise>
									  </c:choose>
									</div>  --%>
 							     
							<%-- 	<div id="shijian">
								<div>
									<img src="${ctp}/res/css/bbx/images/wei.png">立即发布
								</div>
								<div>
									<img src="${ctp}/res/css/bbx/images/wei.png">指定时间发布
								</div>
								<input type="text" onclick="WdatePicker();" class="sj_time">
							</div> --%>
							</div>
							
							
							<div class="clear"></div>
							<div class="form-actions tan_bottom_1">
<%-- 								<input type="hidden" id="id" name="id" value="${teamMessageVo.id }" /> --%>
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">发表</a> 
								<a href="javascript:void(0)" onclick="$.closeWindow();">取消</a>
							</div> 
					
					
					<%-- 
					   <input type="hidden" id="currentRole" value="${currentRole }"/>
					   <input type="hidden" id="gradeId" name="gradeIds"/>
					   <input type="hidden" id="teamId" name="teamIds"/>
					   <input type="hidden" id="entityId" name="uuids"/>
					   
						<div class="trend">
							<div class="edit">
								<div style="padding: 0 20px;">
									<textarea placeholder="请输入通知详细内容，1000个中文字符以内"
										class="span12 left_red {required : true,maxlength:1000}" id="contentt" >${teamMessageVo.content }</textarea>
									<input type="hidden" value="${teamMessageVo.content }" id="content" name="content"/>	
								</div>
								<div style="padding:0 20px;">
								<ul class="zpzs-box" style="padding:0">
								  <c:if test="${teamMessageVo.tmfVo.size()>0 }">
									 <c:forEach items="${teamMessageVo.tmfVo }"  var="tmfVo">
									   <li id="${tmfVo.fileId }">
									   	 <div class="img"><img src="${ tmfVo.url}"/></div>
									   	 <a href="#" class="tp" onclick="delDiv(this)" style="display:none;">
									   	   <img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww">
									   	 </a>
									   </li>  
									 </c:forEach>
								  </c:if>
								</ul>
								<div style=" float:left;position:relative;">
									<a href="#" class="tianjia"> <img
										src="${ctp}/res/css/bbx/images/add.jpg">
									</a> <input type="hidden" id="uploader" />

								</div>
								</div>
								<div class="clear"></div>
 								 <c:if test="${currentRole=='CLASS_MASTER' }"> 
									 <div class="remove" style="border-bottom: 1px #dfdfdf solid;border-top: 1px #dfdfdf solid;" >
									  <c:choose>
									    <c:when test="${tList.size()>0 }">
									        <c:forEach items="${tList }" var="tl">
										       <button onclick="opt(this,'${tl.id}')" id="${tl.id }"  <c:if test="${tl.id==t.id }">class='lan'</c:if> >${tl.name }</button>
										    </c:forEach>
									    </c:when>
									    <c:otherwise>无相关班级!</c:otherwise>
									  </c:choose>
									</div> 
 								 </c:if>
 							      <c:if test="${currentRole=='SCHOOL_MASTER' }"> 
 								   <div class="remove" style="border-bottom: 1px #dfdfdf solid;">
            					      <div class="tz_select"><span><img src="${ctp }/res/css/bbx/images/wei.png" id="qx">全校通知</span><span><img src="${ctp }/res/css/bbx/images/wei.png" id="nj">年级通知</span></div>
            					      <div class="nj_select">
            					         <c:if test="${gList.size()>0 }">
	            					         <c:forEach items="${gList }" var="gl">
	            					           <button id="${gl.id }" onclick="opt(this,'${gl.id}')" <c:if test="${gl.id==g.id }">class='lan'</c:if>  >${gl.name }</button>
	            					         </c:forEach>
	            					     </c:if>
	            					     <c:if test="${g!=null }">
	            					        <button id="${g.id }" class="lan">${g.name }</button>
	            					     </c:if>
            					      </div>
						           </div>  
            					</c:if>  
								<div id="shijian">
								<div>
									<img src="${ctp}/res/css/bbx/images/wei.png">立即发布
								</div>
								<div>
									<img src="${ctp}/res/css/bbx/images/wei.png">指定时间发布
								</div>
								<input type="text" onclick="WdatePicker();" class="sj_time">
							</div>
							</div>
							<div class="clear"></div>
							<div class="form-actions tan_bottom_1">
								<input type="hidden" id="id" name="id" value="${teamMessageVo.id }" />
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">发表</a> 
								<a href="javascript:void(0)" onclick="$.closeWindow();">取消</a>
							</div> --%>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
	
	$(function() {
		if(currentRoleCode == "SCHOOL_LEADER"){
			$("#classMasterSearch").html("");
			$("#classMasterSearch").hide();		
			$.initCascadeSelector({
				"type" : "team",
				"gradeFirstOptTitle" : "全校",
				"teamFirstOptTitle" : "全年级",
				"teamCallback" : function($this) {
				}
			});			
		}else{
			$("#schoolManagerSearch").html("");		
			$("#schoolManagerSearch").hide();	
			
			var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
			$.BbxRoleTeamAccountSelector({
				   "selector" : "#bj",
				   "condition" : $requestData,
				   "selectedVal" : "",			
				   "afterHandler" : function() {
					}	
			   });
		}
		
		
		$("#qx").attr('src','${ctp}/res/css/bbx/images/selected.png');
		uploadFile();
		checker = initValidator();
	});

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			
			// 组拼文件id
			var entityId = "";
			$('.zpzs-box li').each(function(){
				entityId+=this.id+",";
			});
			$('#entityId').val(entityId);
			/* var receiverId = "";
			$('.lan').each(function(){
				receiverId+=this.id+",";
			}); */
			//$("#teamId").val(receiverId);
			$("#content").val($("#contentt").val().replace(/[\r\n]/g,"<br/>"));
			var $requestData = {};//formData2JSONObj("#teamMessage_form");
			$requestData.uuids = entityId; 
			$requestData.content = $("#content").val();
			$requestData.gradeId = $("#nj").val();
			$requestData.teamId = $("#bj").val();
			$requestData.startTime = $("#startTime").val();
			if($requestData.startTime == ""){
				$requestData.startTime = (new Date()).format("yyyy-MM-dd hh:mm:ss");
			}
			$requestData.finishTime = $("#finishTime").val();
			//$requestData.teamIds=receiverId;
			
			var url = "${ctp}/bbx/teamMessage/creator";
			
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					parent.core_iframe.search();
					$.closeWindow();
				}else{
					$.error("操作失败");
				}
				loader.close();
			});  
		
		/* if(receiverId==="" ){
				$.error("请选择班级!");
		}else{			
				 loader.show();
				  $.post(url, $requestData, function(data, status) {
					if("success" === status) {
						$.success('操作成功');
						parent.core_iframe.search();
						$.closeWindow();
				 		data = eval("(" + data + ")");
						if("success" === data.info) {
							if(parent.core_iframe != null) {
	 							//parent.core_iframe.window.location.reload();
	 						} else {
	 							parent.window.location.reload();
	 						}
							$.closeWindow();
						} else {
							$.error("操作失败");
						} 
					}else{
						$.error("操作失败");
					}
					loader.close();
				});  
			}*/		
		} 
	}
	
	function clickRadio(type){
		if(type == 1){
			$("#startTime").val("");
			$("#startTime").attr("disabled", true); 
		}else if(type == 2){
			$("#startTime").removeAttr("disabled"); 
		}
	}
	
	function initValidator() {
		return $("#teamMessage_form").validate({
			errorClass : "myerror",
			rules : {
				"contentt" : {
					required : true,
					maxlength : 1000
				},
				"finishTime" : {
					required : true
				},
				"check" : {
					required : true,
					check1 : true
				}
			},
			messages : {
				
			}
		});
	}
		
	$.validator.addMethod("check1", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var startTime = $("#startTime").val();
		var radioValue = $("#check:checked").val();
		if(radioValue == "2" && startTime == ""){
			result = false;
			
		}
	   	return this.optional(element) || result;
	}, "触发时间不能为空");
	
	
 	function opt(obj,id){
 		$(".lan").removeClass("lan");
	 	$("#"+id).addClass("lan");
	} 

	
	
	
	
	
	//图片上传
	/*===================================================*/
	$(function(){
	    $(".zpzs-box li").hover(function(){
	        $(this).find(".tp").show();
	    },function(){
	        $(".zpzs-box li .tp").hide();
	    })
	});

	function delDiv(a){
		$(a).closest('li').remove();
		var divLen = $('.zpzs-box').find('li').length;
		var sunLen = 9;
		if(divLen>=sunLen){
	    	$('#uploader').hide();
	    }else{
	    	$('#uploader').show();
	    }
	}
	
	
	function uploadFile(){
		var obj = $("#uploader").uploadify({
	        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	        uploader: '${pageContext.request.contextPath}/uploader/common',
	        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
	        fileObjName: 'file',
	        fileTypeDesc: "文件上传",
	        fileTypeExts: "*.gif; *.jpg; *.png;*.jpeg;*.bmp", //默认*.*
	        method: 'post',
	        multi: true, // 是否能选择多个文件
	        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
	        removeTimeout: 1,
	        queueSizeLimit: 9,
	        fileSizeLimit: 10 * 1024,
	        buttonText: "上传文件",
	        requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
	        height: 67,
	        width: 80,
	        onUploadSuccess: function(file, data, response) {
//	         	alert(JSON.stringify(data));
	       	 var $jsonObj = eval("(" + data + ")");
	       	var imageStr = '';
	       	//显示已上传的图片 
	       	imageStr += '<li id="'+$jsonObj.uuid+'"><div class="img"><img src="'+$jsonObj.url+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
	       	//给新建的li绑定事件 
	       	$(".zpzs-box").append(imageStr);
	       	$(".zpzs-box li").hover(function(){
		        $(this).find(".tp").show();
		    },function(){
		        $(".zpzs-box li .tp").hide();
		    })
	       	
		    var divLen = $('.zpzs-box').find('li').length;
		    var sunLen = 9;
		    if(divLen>=sunLen){
		    	$('#uploader').hide();
		    	$('.uploadify-queue-item').html('')
		    }else{
		    	$('#uploader').show();
		    }
		    
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
	
	
	
	/*===================================================*/
	
	Date.prototype.format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	
	
	
	
	
</script>
</html>