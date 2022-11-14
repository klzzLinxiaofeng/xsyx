<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
	<title></title>
	<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
	<%@ include file="/views/embedded/common.jsp"%>
	<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
	<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
	<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctp }/res/css/bbx/bbx.css">
	<style>
		.myerror {
			color: red !important;
			width: 22%;
			display: inline-block;
			padding-left: 10px;
		}
		.remove_fj{
			color:#666;
			margin-left:15px;
			font-size:16px;
			font-family:"微软雅黑";
			width:18px;
			height:18px;
			text-align:center;
			line-height:18px;
			display:inline-block;
		}
		.remove{
			margin-top: -35px;
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
	</style>
</head>
<body style="background-color: #F3F3F3 !important;">
<div class="row-fluid">
	<div class="span12">
		<div class="content-widgets" style="margin-bottom:0">
			<div class="widget-container" style="padding: 0;">
				<form class="form-horizontal tan_form" id="homework_form" action="javascript:void(0);">
					<input type="hidden" id="teamId" name="teamId" value="${teamId }"/>
					<div class="trend">
						<div class="edit">
							<div style="padding:0 20px;"><textarea id="contentt" placeholder="请输入作业内容，1000个中文字符以内"  class="span12 zy_text span12 left_red {required : true,maxlength:1000}" >${thwVo.content }</textarea>
								<input type="hidden" value="${thwVo.content  }" id="content" name="content"/>
								<!--             <select class="span12 select_demo" > -->
								<!--                 <option>请选择</option> -->
								<!--                 <option>作业是课本第__页__题。</option> -->
								<!--                 <option>作业是预习__。</option> -->
								<!--                 <option>作业是抄写__，__遍。</option> -->
								<!--                 <option>作业是背诵课文__，__并让家长签字。</option> -->
								<!--                 <option>作业是朗诵课文__，__并让家长签字。</option> -->
								<!--             </select> -->
							</div>
							<div class="control-group">
								<!-- <label class="control-label" style="margin-right:20px;">附件</label> -->


								<div class="controls update_img" style="margin:15px 0 0 20px">
									<c:choose>
										<c:when test="${not empty thwVo.fileUuid }">
											<ul class="zpzs-box">
												<c:forEach items="${thwVo.fileUuid}" var="entity" varStatus="i">
													<%-- <c:if test="${entity.imageState }"> --%>
													<li><div class="img"><img src="${entity.imageSrc }" /></div><a href="#" class="tp"  onclick="delEditImageDiv(this.id)" style="display:none;" id="imgEdit${i.index }"><img src="${ctp }/res/css/bbx/images/x.png" class="ww"></a></li>
													<%-- </c:if> --%>
												</c:forEach>
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
											<%-- <c:forEach items="${thwVo.fileUuid}" var="entity" varStatus="i">
                                                <c:if test="${not entity.imageState }">
                                                    <p style='display:inline-block;margin-bottom:0;width:300px;overflow:hidden;font-size: 16px;font-weight: bold;'><a target="_blank" id="edit_${i.index}" href='<entity:getHttpUrl uuid="${entity.fileId }"/>'>${entity.fileName}</a><a id="editB${i.index}" onclick="deleteEditFile(this);" href="javascript:void(0);" class="remove_fj">x</a></p>
                                                </c:if>
                                            </c:forEach>
                                            <p style='display:inline-block;margin-bottom:0;width:500px;overflow:hidden;'><a taget="_blank" id="a"></a></p> --%>
										</c:when>
										<c:otherwise>
											<ul class="zpzs-box">
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
											<!-- <p style='display:inline-block;margin-bottom:0;width:300px;overflow:hidden;'><a taget="_blank" id="a"></a></p> -->
										</c:otherwise>
									</c:choose>
									<div style="display:none" id="fileUuid">
										<c:if test="${not empty thwVo.fileUuid }">
											<c:forEach items="${thwVo.fileUuid}" var="entity" varStatus="i">
												<%-- <c:if test="${entity.imageState }"> --%>
												<input id="editImage${i.index }" value="${entity.fileId }"/>
												<%-- </c:if> --%>
											</c:forEach>
											<%-- <c:forEach items="${thwVo.fileUuid}" var="entity" varStatus="i">
                                                <c:if test="${not entity.imageState }">
                                                    <input id="editFile${i.index }" value="${entity.fileId }"/>
                                                </c:if>
                                            </c:forEach> --%>
										</c:if>
									</div>
									<%-- <input id="entityId" name="fileUuid" value="${result.fileUuid }"/> --%>
								</div>
							</div>
							<%-- <ul class="zpzs-box">
                                <li><div class="img"><img src="${ctp }/res/css/bbx/images/print.jpg" /></div><a href="#" class="tp"  onclick="delDiv(this.id)" style="display:none;" id="sc"><img src="res/css/bbx/images/x.png" class="ww"></a></li>
                                <li><div class="img"><img src="${ctp }/res/css/bbx/images/print.jpg" /></div><a href="#" class="tp" style="display:none;"  onclick="delDiv(this.id)" id="sd"><img src="res/css/bbx/images/x.png" class="ww"></a></li>
                            </ul> --%>
							<%-- <div style="padding-left:20px;"><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div> --%>
							<div class="remove">
								<c:forEach items="${subjectList}" var="subject">
									<c:choose>
										<c:when test="${subject.id==-1 }">
											<button date-subject-code="${subject.subjectCode }" class="lan">${subject.subjectName }</button>
										</c:when>
										<c:otherwise>
											<button date-subject-code="${subject.subjectCode }">${subject.subjectName }</button>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!--  <button class="lan">数学</button><button>英语</button><button>语文</button><button>历史</button><button>地理</button><button>政治</button><button>科学</button> -->
							</div>

						</div>
						<div class="clear"></div>
						<div class="form-actions tan_bottom_1">
							<input type="hidden" id="id" name="id" value="${thwVo.id}" />
							<c:choose>
								<c:when test="${not empty thwVo}">
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">保存</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">布置</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:void(0)" onclick="closeWin();">取消</a>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        /*作业模板选择*/
        $('.select_demo').change(function(){
            var prev_text=$(this).prev().val();
            var select_text=$(this).children('option:selected').val();
            $(this).prev().val(prev_text+select_text);
        })


        $(".zpzs-box").on("mouseover mouseout","li",function(event){
            if(event.type == "mouseover"){
                $(this).find(".tp").show();
            }else if(event.type == "mouseout"){
                $(".zpzs-box li .tp").hide();
            }
        })

        $(".remove").on("click", "button", function(){
            $(".remove button").removeClass("lan");
            $(this).addClass("lan");
        });

        uploadFile();

    });

    function delDiv(a){
        var i = a.substr(3);
        var entity = "uuid"+i;
        $("#"+entity).remove();
        var divA = document.getElementById(a);
        var p=divA.parentElement
        p.parentNode.removeChild(p);
    }


    function delEditImageDiv(a){
        var i = a.substr(7);
        var entity = "editImage"+i;
        $("#"+entity).remove();
        var divA = document.getElementById(a);
        var p=divA.parentElement
        p.parentNode.removeChild(p);
    }

    var checker;
    $(function() {
        checker = initValidator();
    });

    function initValidator() {
        return $("#homework_form").validate({
            errorClass : "myerror",
            rules : {
                /* "content" : {
                    required : true,
                    minlength : 1,
                    maxlength : 30,
                } */
            },
            messages : {
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            $("#content").val($("#contentt").val().replace(/[\r\n]/g,"<br/>"));

            var $requestData = formData2JSONObj("#homework_form");
            var jsonArray = [];
            var inputLength = $("#fileUuid input").length;
            $("#fileUuid input").each(function(){
                jsonArray.push($(this).val());
            });
            var Items = JSON.stringify(jsonArray);
            $requestData.fileId = Items;
            var subjectCode = $(".remove .lan").attr("date-subject-code");
            var sunjectLength = $(".remove .lan").length;
            $requestData.subjectCode = subjectCode;
            if(inputLength>10){
                $.error("上传文件过多,最多9张图片！");
                return;
            }
            if(sunjectLength<1){
                $.error("请选择科目");
                return;
            }
            var url = "${pageContext.request.contextPath}/clazz/teamHomework/creator";
            //alert(JSON.stringify($requestData))
            if ("" != $id) {
                $requestData._method = "put";
                url = "${pageContext.request.contextPath}/clazz/teamHomework/" + $id;
            }

            loader.show();
            $.post(url, $requestData, function(data, status) {
                if("success" === status) {
                    parent.core_iframe.search();
                    $.success('操作成功');
                    $.closeWindow();
                }else{
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

    var step = 0;
    function uploadFile(){
        var obj = setTimeout(function() {$("#uploader").uploadify({
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
            uploadLimit:9,
            onUploadSuccess: function(file, data, response) {

                var $jsonObj = eval("(" + data + ")");
                /* $("#entityId").val($("#entityId").val()+";"+$jsonObj.uuid); */
                //$("#a").html($("#a").html()+"<br />"+$jsonObj.realFileName);
                var a = "a"+step;
                var b = "b"+step;
                var uuid = "uuid"+step;
                var img = "img"+step;
                var suffix=$jsonObj.suffix;
                var url=$jsonObj.url;
                //	alert(a);
                if(suffix=="gif"||suffix=="jpg"||suffix=="png"||suffix=="jpeg"||suffix=="bmp"){
                    $(".zpzs-box").append("<li><div class='img'><img src='"+url+"' /></div><a href='#' class='tp'  onclick='delDiv(this.id)' style='display:none;' id='"+ img +"'><img src='${ctp}/res/css/bbx/images/x.png' class='ww'></a></li>")
                }/* else{
            	$("#a").append("<p style='display:inline-block;margin-bottom:0;min-width:240px;overflow:hidden;'><a taget='_blank' id='"+ a +"'>'"+ $jsonObj.realFileName +"'</a><a id='"+b+"' onclick='deleteFile(this);' href='javascript:void(0);' class='remove_fj' >x</a></p>");
            } */
                $("#fileUuid").append("<input id='"+uuid+"' value='"+$jsonObj.uuid+"'/> ")
                //$("#a").attr('href',$jsonObj.url);
                //$("#a").attr('target','_blank');
                step++;

            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
                $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        })},10);
    }

    function deleteFile(obj){
        $.confirm("确定执行此次操作？", function() {
            $(obj).parent().remove();
            var id = $(obj).prev().attr("id");
            var i = id.substr(1);
            var entity = "uuid"+i;
            $("#"+entity).remove();
        });
    }

    function deleteEditFile(obj){
        $.confirm("确定执行此次操作？", function() {
            $(obj).parent().remove();
            var id = $(obj).prev().attr("id");
            var i = id.substr(5);
            var entity = "editFile"+i;
            $("#"+entity).remove();
        });
    }

    function closeWin(){
        $.confirm("确定离开此页面？", function() {
            $.closeWindow();
        });
    }

</script>
</html>