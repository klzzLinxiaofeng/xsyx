<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
	<title></title>
	<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
	<%@ include file="/views/embedded/common.jsp"%>
	<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
	<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
	<style>
		.form-horizontal .controls #zp .img_1 {
			float: left;
			margin: 10px;
			position: relative;
			top: 0;
			width: 233px;
			height: 130px;
		}

		.form-horizontal .controls #zp .img_1 img {
			width: 233px;
			height: 130px;
		}

		.form-horizontal .controls #zp .img_1 a {
			position: absolute;
			font-size: 16px;
			/* font-weight: bold; */
			color: #888;
			right: 0px;
			top: 0px;
			display: block;
			width: 14px;
			height: 14px;
			line-height: 14px;
			text-align: center;
			cursor: pointer;
			background-color: #eee;
			border: 1px solid #aaa;
		}
		.zpzs-box li:hover{ cursor :pointer}
		.zpzs-box li:hover a{  display: block !important;}
		.form-horizontal .controls {
			margin-left: 100px;
		}

		.myerror {
			color: red !important;
			width: 22%;
			display: inline-block;
			padding-left: 10px;
		}
		.date .myerror{
			display:block;
		}
		.uploadify{
			position:absolute;
			opacity:0;
			left:0;
			top:0;
		}
		.uploadify-queue{width:80px;}
		.fileName{
			width: 50px;
			height: 15px;
			overflow: hidden;
			display: inline-block;
		}
		.edit ul{
			padding:0;
		}
		#a p{
			padding:0 0 10px 0;min-width:240px;
		}
		#a p a{
			font-size: 16px;
			font-weight: bold;
		}
		.ban-active img{margin-right:0;}
		.edit ul li{ /* width:100%; */height:auto;}
	</style>
</head>
<body style="background-color: #F3F3F3 !important;">

<div class="row-fluid">
	<div class="span12">
		<div class="content-widgets" style="margin-bottom: 0">
			<div class="widget-container" style="padding: 20px 0 0;">
				<form class="form-horizontal padding15" id="teamactivity_form">
					<input type="hidden" id="teamId" name="teamId" value="${teamId }" />
					<div class="control-group">
						<input type="text" id="name" name="name" class="span12"
							   placeholder="请输入活动标题" value="${teamActivity.name}">
					</div>
					<div style="color:#999;">左侧选择活动封面模版或右侧上传自定义封面</div>
					<c:choose>
						<c:when test="${not empty imgSrc }">
							<div class="control-group">
								<div class="ban-active left" id="acpic"><a href="javascript:void(0)" onclick="choiceImg();" style="display:block"><img id="src" src="${imgSrc }"></a></div>
									<%-- 									<div class="ban-title left"><img src="${ctp }/res/images/huo_title.png"></div> --%>
								<input type="hidden" id="activityImage" name="activityImage" value="${imgId }" />
								<div class="controls update_img" >
									<c:choose>
										<c:when test="${not empty teamActivity.fileId}">
											<ul class="zpzs-box">
												<li><div class="ban-active left" style='position: relative;'><img id="activityImg" src="<entity:getHttpUrl uuid='${teamActivity.fileId}'/>">
													<a href="#" class="tp"  onclick="delEditImageDiv(this)" style="display:none;" id="imgEdit">
														<img src="${ctp }/res/css/bbx/images/x.png" class="ww" style="position: absolute;height:100%; bottom:0; left:0;"></a>
												</div>

												</li>
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:when>
										<c:otherwise>
											<ul class="zpzs-box">
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="control-group">
								<c:choose>
									<c:when test="${not empty teamActivity}">
										<div class="ban-active left" id="acpic"><a href="javascript:void(0)" onclick="choiceImg();" style="display:block"><img id="src" src="${teamActivity.imgSrc }"></a></div>
									</c:when>
									<c:otherwise>
										<div class="ban-active left" id="acpic"><a href="javascript:void(0)" onclick="choiceImg();" style="display:block"><img id="src" src="${ctp }/res/css/bbx/images/hd_0.png"></a></div>
									</c:otherwise>
								</c:choose>
									<%-- 									<div class="ban-title left"><img src="${ctp }/res/images/huo_title.png"></div> --%>
								<input type="hidden" id="activityImage" name="activityImage" value="${teamActivity.activityImage }" />
								<div class="controls update_img">
									<c:choose>
										<c:when test="${not empty teamActivity.fileId}">
											<ul class="zpzs-box">
												<li><div class="ban-active left" style='position: relative;'><img id="activityImg" src="<entity:getHttpUrl uuid='${teamActivity.fileId}'/>">
													<a href="#" class="tp"  onclick='new_del(this)' style="display:none;" id="imgEdit">
														<img src="${ctp }/res/css/bbx/images/x.png" class="ww" style="position: absolute;height:100%; bottom:0; left:0;"></a>
												</div>
												</li>
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:when>
										<c:otherwise>
											<ul class="zpzs-box">
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- 						<div class="control-group">	 -->
					<!-- 								<div class="controls update_img" style="margin:15px 0 0 20px;"> -->
					<%-- 									<c:choose> --%>
					<%-- 										<c:when test="${not empty teamActivity.fileId}"> --%>
					<!-- 											<ul class="zpzs-box"> -->
					<%-- 												<li><div class="img"><img id="activityImg" src="<entity:getHttpUrl uuid='${teamActivity.fileId}'/>"></div> --%>
					<!-- 													<a href="#" class="tp"  onclick="delEditImageDiv(this)" style="display:none;" id="imgEdit"> -->
					<%-- 													<img src="${ctp }/res/css/bbx/images/x.png" class="ww"></a> --%>
					<!-- 												</li> -->
					<!-- 											</ul> -->
					<!-- 											<div style="position:relative;float:left"> -->
					<%-- 												<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div> --%>
					<!-- 												<input type="hidden" id="uploader" name="uploader"/> -->
					<!-- 											</div> -->
					<!-- 											<div class="clear"></div> -->
					<%-- 										</c:when> --%>
					<%-- 										<c:otherwise> --%>
					<!-- 											<ul class="zpzs-box"> -->
					<!-- 											</ul> -->
					<!-- 											<div style="position:relative;float:left"> -->
					<%-- 												<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div> --%>
					<!-- 												<input type="hidden" id="uploader" name="uploader"/> -->
					<!-- 											</div> -->
					<!-- 											<div class="clear"></div> -->
					<%-- 										</c:otherwise> --%>
					<%-- 									</c:choose> --%>
					<!-- 								</div> -->
					<!-- 							</div> -->
					<div class="control-group">
						<div class="input-append date" style="margin-right: 10px;float:left">
							<input style="height: 34px; line-height: 34px; font-size: 12px;width:300px;"
								   class="sj_time" id="startTime" name="startTime"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"
								   placeholder="开始时间"
								   value="<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value='${teamActivity.startTime }' />"
								   type="text">
						</div>
						<div class="input-append date">
							<input style="height: 34px; line-height: 34px; font-size: 12px;width:300px;"
								   class="sj_time" id="finishTime" name="finishTime"
								   onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{m:+5})}',dateFmt:'yyyy-MM-dd HH:mm'});"
								   placeholder="结束时间"
								   value="<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value='${teamActivity.finishTime }' />"
								   type="text">
						</div>
					</div>
					<div class="control-group">
						<input type="text" id="place" name="place" class="span12"
							   placeholder="请输入活动的详细地点" value="${teamActivity.place}">
					</div>
					<div class="control-group">
						<textarea type="text" id="comment" name="comment" class="span12" placeholder="请输入活动的注意事项">${teamActivity.comment}</textarea>
					</div>
				</form>
				<div class="form-actions tan_bottom_1">
					<input type="hidden" id="file" value=""/>
					<c:if test="${isCK == null || isCK == ''}">
						<input type="hidden" id="id" name="id" value="${teamActivity.id}" />
						<c:choose>
							<c:when test="${not empty teamActivity}">
								<a href="javascript:void(0)" class="yellow"
								   onclick="saveOrUpdate();">保存</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)" class="yellow"
								   onclick="saveOrUpdate();">提交</a>
							</c:otherwise>
						</c:choose>
						<a href="javascript:void(0)" onclick="closeWin();">取消</a>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
    var checker;
    $(function() {
        checker = initValidator();
        uploadFile();
        $("#file").val("${teamActivity.fileId}");
        $(".zpzs-box li").each(function(){
            $(this).hover(function(){
                $(this).find(".tp").show();
            },function(){
                $(".zpzs-box li .tp").hide();
            }) ;
        });
        if($('.zpzs-box').find('li').length != 0){
            $("#acpic").find('a').attr('onclick','');
        }
    });

    function initValidator() {
        return $("#teamactivity_form").validate({
            errorClass : "myerror",
            rules : {
                "name" : {
                    required : true,
                    minlength : 1,
                    maxlength : 20
                },
                "startTime" : {
                    required : true
                },
                "finishTime" : {
                    required : true,
                    greaterThanDate1:true
                },
                "place" : {
                    required : true,
                    minlength : 1,
                    maxlength : 100
                },
                "comment" : {
                    required : true,
                    minlength : 1,
                    maxlength : 250
                }

            },
            messages : {

            }
        });
    }

    $.validator.addMethod("greaterThanDate1", function(value, element, param) {
        var result = true;
        var $this = $(element);
        var begin = new Date($("#startTime").val().replace(/-/g, "/"));
        var end = new Date(value.replace(/-/g, "/"));
        if(end - begin< 0){
            result = false;
        }
        return this.optional(element) || result;
    }, "结束时间必须大于开始时间");

    // 	function uploadFile(){
    //     	var obj = $("#uploader").uploadify({
    //             swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
    //             uploader: '${pageContext.request.contextPath}/uploader/common',
    <%--             formData: {'jsessionId': '<%=request.getSession().getId()%>' --%>
    // 							},
    // 							fileObjName : 'file',
    // 							fileTypeDesc : "文件上传",
    // // 							fileTypeExts : "*.*", //默认*.*
    // 							fileTypeExts : '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
    // 							method : 'post',
    // 							multi : false, // 是否能选择多个文件
    // 							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
    // 							removeTimeout : 1,
    // 							queueSizeLimit : 1,
    // 							fileSizeLimit : 4 * 1024,
    // 							buttonText : "上传封面",
    // 							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
    // 							height : 20,
    // 							width : 70,
    // 							onUploadSuccess : function(file, data, response) {
    // 								var $jsonObj = eval("(" + data + ")");
    // 								var img = '<div class="img_1"><a data-id="'
    // 										+ $jsonObj.uuid
    // 										+ '" onclick="reMove(this);">x</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
    // 										+ $jsonObj.url
    // 										+ '" data-id="'+$jsonObj.uuid+'"/>&nbsp;&nbsp;&nbsp;</div>';
    // 								$.success("上传成功!", 9);
    // 								$("#activityImage").val($jsonObj.uuid);
    // 								$("#zp").html(img);
    // 							},
    // 							onUploadStart : function(file) { //上传开始时触发（每个文件触发一次）
    // 								$("#infoBox").prev("p").css("display", "none");
    // 								$("#infoBox").css("display", "block");
    // 							},
    // 							onUploadError : function(file, errorCode, errorMsg,
    // 									errorString) {
    // 								$.alert('The file ' + file.name
    // 										+ ' could not be uploaded: '
    // 										+ errorString);
    // 							}
    // 						});

    // 	}

    function uploadFile(){
        var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.gif; *.jpg; *.png; *.jpeg; *.bmp;", //默认*.*
            method: 'post',
            multi: true, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 10 * 1024,
            buttonText: "上传文件",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 67,
            width: 80,
            onUploadSuccess: function(file, data, response) {

                var $jsonObj = eval("(" + data + ")");
                /* var uuid = "uuid"+step;
                var img = "img"+step; */
                var suffix=$jsonObj.suffix;
                var url=$jsonObj.url;
                //alert($jsonObj.url + "~~" + $jsonObj.uuid +"~~~");
                if(suffix=="gif"||suffix=="jpg"||suffix=="png"||suffix=="jpeg"||suffix=="bmp"||suffix=="JPG"){
                    $(".zpzs-box").html("<li><div class='ban-active left' style='position: relative;'><img src='"+url
                        +"' /><a href='#' class='tp'  onclick='new_del(this)' style='display:none;' id='img'><img src='${ctp}/res/css/bbx/images/x.png' style='height:100%;position: absolute;bottom: 0;left: 0;' class='ww'></a></div></li>")
                }
                /*  $("#fileUuid").append("<input id='"+uuid+"' value='"+$jsonObj.uuid+"'/> ") */
                //step++;

                $("#file").val($jsonObj.uuid);
            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");

                $("#acpic").find('a').attr('onclick','');
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
                $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
    }

    //
    function new_del(div){
        $("#file").val("");
        $(div).parent().parent().remove();
        $("#acpic").find('a').attr('onclick','choiceImg()');
    }
    //保存或更新修改
    function saveOrUpdate() {
        var a = $("#comment").val();
        //alert(a);
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var comment=document.getElementById("comment").value;
            comment=comment.replace('\n',' ');
            document.getElementById("comment").value=comment;
            var $requestData = formData2JSONObj("#teamactivity_form");
// 			alert($requestData.comment);
            $requestData.fileId = $("#file").val();
            //alert(JSON.stringify($requestData));
            var url = "${pageContext.request.contextPath}/clazz/teamActivity/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${pageContext.request.contextPath}/clazz/teamActivity/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function(data, status) {
                if("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if("success" === data.info) {
                        parent.core_iframe.search();
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                }else{
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).next("img").attr("src","${pageContext.request.contextPath}/res/images/no_picture.jpg");
        $(obj).remove();
        $("#activityImage").val("");
    }

    function closeWin(){
        $.confirm("确定离开此页面？", function() {
            $.closeWindow();
        });
    }

    function choiceImg(){
        var teamId = $("#teamId").val();
        $.initWinOnCurFromLeft_bbx('图片选择', '${ctp}/clazz/teamActivity/choiceImg?teamId='+teamId, '500', '350');
    }

</script>
</html>