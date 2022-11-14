<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js" type="text/javascript"></script>
<style>
    input[type="radio"]{
        margin:0 5px;
        position:relative;
        top:-1px;
    }
    #dvTextBookCatalog select{margin-right:10px;}
</style>
<!-- 上传标志(res_school校本资源上传, 其它标志为个人资源) -->
<input type="hidden" value="${resourceType}" id="type">
<div class="a2">
    <div class="blue"></div>
    <div class="upload_wk">
        <div class="u_wk">
            <div class="upload_file" id="uploader">
                <div id="picker"></div>
               <p style="color:red;text-align: center;line-height: 40px;font-size: 14px;">如果上传视频，请上传数据速率为7954kbps、总比特率为8051kbps、帧速率为24帧/秒、格式为MP4的视频文件</p>
            </div>
        </div>
    </div>
    <div class="ul_wk" style="display: none">
        <div class="u_message">
            <div class="jindu" id="progress" style="width:635px;display:block;float:left;height:inherit;"></div>
            <a onclick="removeUploadMicro()" class="no" href="javascript:void(0)">取消上传</a>
            <div class="clear"></div>
            <div class="sudu"></div>
            <input type="hidden" name="entityId" id="entityId" value="" >
        </div>
        <div class="u_wk_message">
            <div class="title">资源信息</div>
<%--            <div class="control-group">--%>
<%--                <label class="control-label" style="width:90px;">--%>
<%--                    <font style="color:red">*</font>标题：--%>
<%--                </label>--%>
<%--                <div class="controls" style="margin-left:110px;">--%>
<%--                    <input type="text" name="uploadResTitle" id="uploadResTitle" value="" placeholder="资源标题" class="span5" style="width:550px;">--%>
<%--                </div>--%>
<%--            </div>--%>

            <div class="control-group">
                <label class="control-label" style="width:90px;">
                    <font style="color:red">*</font>共享：
                </label>
                <div class="controls" style="margin-left:110px;">
                    <input type="radio" id="shareBox" name = "shareBox" value="res_school"><span style="margin: 7px;">：校本资源</span>&nbsp;
                </div>
            </div>
            <c:if test="${resourceType eq 'res_school'}">
                <div class="control-group">
                    <label class="control-label" style="width:90px;">
                        <font style="color:red">*</font>类型：
                    </label>
                    <select name="resType" id="resType">
                        <option value="2">课件</option>
                        <option value="4">试卷</option>
                        <option value="3">作业</option>
                        <option value="8">付费</option>
                    </select>
    <%--                <div class="controls" style="margin-left:110px;" id='label'>--%>
    <%--                </div>--%>
                </div>
            </c:if>
<%--            <div class="control-group">--%>
<%--                <label class="control-label" style="width:90px;"> 简介：</label>--%>
<%--                <div class="controls" style="margin-left:110px;">--%>
<%--                    <textarea name="uploadMicroDescription" id="uploadMicroDescription" class="span5" style="width:550px;"></textarea>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--			<div class="control-group">--%>
<%--				<c:if test="${resourceType ne 'res_school' && resourceType ne 'res_region'}">--%>
<%--					<label class="control-label" style="width:90px;">共享：</label>--%>
<%--					<div class="controls" style="margin-left:110px;">--%>
<%--                        <input type="radio" id="shareBox" name = "shareBox" value="xbzy"><span style="margin: 7px;">：校本资源</span>&nbsp;--%>
<%--						<input type="radio" id="publicShareBox" name = "shareBox" value="qbzy"><span style="margin: 7px;">：区本资源</span>--%>
<%--					</div>--%>
<%--				</c:if>--%>
<%--				<c:if test="${resourceType eq 'res_school'}">--%>
<%--					<input id="flag" type="hidden" value="1">--%>
<%--				</c:if>--%>
<%--                <c:if test="${resourceType eq 'res_region'}">--%>
<%--                    <input id="flag" type="hidden" value="2">--%>
<%--                </c:if>--%>
<%--                <input type="hidden" id="radioType">--%>
<%--            </div>--%>
<%--            <div id="integral_div" class="select_div  control-group"  hidden="true">--%>
<%--            	<label class="control-label" style="width:90px;"> 所需积分：</label>--%>
<%--            	<div class="controls" style="margin-left:110px;">--%>
<%--						<select id="integral" type="text">--%>
<%--							<option value="0">0</option>--%>
<%--							<option value="1">1</option>--%>
<%--							<option value="2">2</option>--%>
<%--							<option value="3">3</option>--%>
<%--							<option value="4">4</option>--%>
<%--							<option value="5">5</option>--%>
<%--							<option value="6">6</option>--%>
<%--							<option value="7">7</option>--%>
<%--							<option value="8">8</option>--%>
<%--							<option value="9">9</option>--%>
<%--							<option value="10">10</option>--%>
<%--						</select>--%>
<%--					</div>--%>
<%--		    </div>--%>
<%--            <div class="control-group" id="upload_textbook" hidden="true">--%>
<%--                <label class="control-label" style="width:90px;">--%>
<%--                    <span class="red">*</span> 教材：--%>
<%--                </label>--%>
<%--                <div class="controls" style="margin-left: 110px;">--%>
<%--                    <jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include>--%>
<%--                </div>--%>
<%--             </div>--%>
<%--             <div class="control-group" id="upload_textbookcatalog" hidden="true">--%>
<%--                    <label class="control-label" style="width:90px;">--%>
<%--                        <span class="red">*</span> 目录：--%>
<%--                    </label>--%>
<%--                    <div id="dvTextBookCatalog" class="select_div controls" style="margin-left:100px;"></div>--%>
<%--			 </div>--%>
<%--			 <!----%>
<%--			 <div class="control-group" hidden="true">--%>
<%--				<div class="select_div controls" style="margin-left:110px;">--%>
<%--					下载所需积分：<input id="integral" type="text">--%>
<%--				</div>--%>
<%--			 </div>--%>
<%--			  -->--%>
		</div>
	</div>
</div>
<script type="text/javascript">
var catalogEnd = 0;

var shareBoxtype = 0;
var publicShareBoxtype = 0;
// 断点上传
function initMicroUpload() {
	$("#uploader").fadeIn("slow");
	$("#uploader").xyUploader({
		basePath: "${pageContext.request.contextPath}",
		formData: {'jsessionId': '<%=request.getSession().getId()%>'},
		picker: {id: "#picker", innerHTML: "", multiple: false},
		progress: "progress",
		accept: {title: 'MEDIA', extensions: "*.*", allowExts: "mp4,doc,docx,ppt,pptx,jpg,jpeg,png,gif,swf,xls,xlsx,pdf,rar,mp3,xep"},
		removeTimeout: 20,
		uploadSuccess: function (file, response) {
		    return uploadSucCallBack(response);
		},
		uploadError: function (code) {
			if(code=="Q_TYPE_DENIED") {
				alert("不能上传空的文档!");
			} else {
				 alert(code);
			}
		}
	});
	// 上传回调
	var uploadSucCallBack = function (json) {
		if (json.finishedFlag === 1) {
			$("#entityId").val(json.uuid);
			$.submitSingleFile({"entityFileUUID":json.uuid},function(){});
			if(json.suffix =='mp4'){
				//$("#label").html("").append('<input name="resType" <%--<c:if test="${resType == 1}">checked</c:if> --%>type="radio" value="<%--<%=ResourceType.MICRO%>--%>" /> 微课 ');
				if (json.finishedFlag === 1) {
					$("#entityId").val(json.uuid);
					//调用视频转换服务
					$.submitJaveSingleFile({"entityFileUUID":json.uuid},function(){});
				}
			}
			<%--if(json.suffix != 'mp4'&&json.suffix!='xep'){--%>
			<%--	$("#label").append('<input name="resType" <c:if test="${resType == 2}">checked</c:if> type="radio" value="<%=ResourceType.LEARNING_DESIGN%>" /> 课件 ');--%>
			<%--	$("#label").append('<input name="resType" <c:if test="${resType == 5}">checked</c:if> type="radio" value="<%=ResourceType.TEACHING_PLAN%>" /> 教案 ');--%>
			<%--	$("#label").append('<input name="resType" <c:if test="${resType == 3}">checked</c:if> type="radio" value="<%=ResourceType.HOMEWORK%>" /> 作业 ');--%>
			<%--	$("#label").append('<input name="resType" <c:if test="${resType == 6}">checked</c:if> type="radio" value="<%=ResourceType.MATERIAL%>" /> 素材 ');--%>
			<%--	$("#label").append('<input name="resType" <c:if test="${resType == 8}">checked</c:if> type="radio" value="<%=ResourceType.LEARNING_PLAN%>" /> 导学案 ');--%>
            <%--    $("#label").append('<input name="resType" <c:if test="${resType == 4}">checked</c:if> type="radio" value="<%=ResourceType.EXAM%>" /> 试卷 ');--%>
			<%--}--%>
			<%--if(json.suffix=='xep'){--%>
			<%--	$("#label").append('<input name="resType" <c:if test="${resType == 4}">checked</c:if> type="radio" value="<%=ResourceType.EXAM%>" /> 试卷 ');--%>
			<%--}--%>
		}
	};
}

function onSaveMicroSubmit() {
	// var flag=$("#flag").val();
	// var title = $.trim($("#uploadResTitle").val());
	var entityId = $("#entityId").val();
	// var catalog1 = $("#catalog1").val();
	// var uploadMicroDescription = $("#uploadMicroDescription").val();
	// var textbookId = catalog1;
	// var detailLength=uploadMicroDescription.trim().length;
	// var titleLength=title.trim().length;
	// var resType = $("#resType").find("option:selected").val();
	// if(resType === undefined){
	// 	 $.alert("请选择类型");
	//      return false;
	// }
	// if (title == null || title == "") {
	//     $.alert("请输入资源标题");
	//     return false;
	// }
	if (entityId == null || entityId == "") {
	    $.alert("请上传资源文件");
	    return false;
	}
	// if (detailLength > 500) {
	//     $.alert("简介不能超过500字");
	//     return false;
	// }
	// if (titleLength > 50) {
	//     $.alert("标题不能超过50字");
	//     return false;
	// }
	// if(flag==1){
	// 	if (textbookId == null || textbookId == "") {
	// 	    $.alert("请输入目录");
	// 	    return false;
	// 	}
	// 	if(catalog1 == null ||catalog1 == ""){
	// 		$.alert("请选择目录");
	// 		return false;
	// 	}
 	//}
	// var aa = $("#dvTextBookCatalog").children("select").length;
	// var catalogTemp;
	// for (var num = aa; num >= 1; num--) {
	//     var catalogname = "catalog" + num;
	//     catalogTemp = $("#" + catalogname).val();
	//     if (Number(catalogTemp) > 0) {
	//         catalogEnd = catalogTemp;
	//         catalogEnd = $("#" + catalogname).find("option:selected").attr("data-code");
	//         break;
	//     } else {
	//         catalogEnd = 0;
	//     }
	// }
	
	// var type = $("#type").val();
	// if("res_school"==type || $('#shareBox').is(':checked')) {
	// 	if(catalogEnd==0) {
	// 		$.alert("请选择教材目录");
	// 		return false;
	// 	}
	// 	var integral = $("#integral").val();
	// 	if(integral==null || ""==integral) {
	// 		$.alert("请输入所需积分");
	// 	    return false;
	// 	}
	// }
	
	return true;
}

function getCatalog() {
    var ca = {};
    return ca;
}

//确认上传按钮
function saveMicro() {
    if (onSaveMicroSubmit()) {
        // var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
        // var array = gradeCodeVolumnValue.split("-");
        //
        // var grade;
        // var volumn;
        // if (array.length == 2) {
        //     grade = array[0];
        //     volumn = array[1];
        // }

        var resType = $("#resType").find("option:selected").val();
        var personType="res_person";
        if($("input:radio[name='shareBox']:checked").val() != null){
            personType = $("input:radio[name='shareBox']:checked").val();
        }else{
            resType = "8";//如果是个人资源，则将归类到付费资源上
        }



        // if(flag==1){
        // 	personType="res_school";
        // }else if(flag==2){
        //     personType="res_region";
        // }
        // var ca = getCatalog();
        // var integral = $("#integral").val();
        // KindEditor.sync("textarea[id='uploadMicroDescription']");
        //
        // var shareType = "";
        // obj = document.getElementsByName("shareBox");
        // for(k in obj){
        //     if(obj[k].checked)
        //         shareType+=obj[k].value+",";
        // }


        $.ajax({
            url: "${pageContext.request.contextPath}/resource/saveOrUpdate",
            type: "POST",
            data:{
            	// "title": $("#uploadResTitle").val(),
                "resType": resType,
                // "description": $("#uploadMicroDescription").val(),
                 "entityId": $("#entityId").val(),
                // "stageCode": $("#stageCode").val(),
                // "subjectCode": $("#subjectCode").val(),
                // "gradeCode": grade,
                // "version": $("#publisherId").val(),
                // "volumn": volumn,
                // "stageName": $("select[id=stageCode] option:selected").text(),
                // "subjectName": $("select[id=subjectCode] option:selected").text(),
                // "versionName": $("select[id=publisherId] option:selected").text(),
                // "textbookId": $("#catalog1").val(),
                // "catalogEnd": catalogEnd,
                 "personType":personType,
                // "integral":integral,
                "shareType":shareType
            },
            async: false,
            success: function (mid) {
                 <%--if("${resourceType}"!='res_school'){--%>
                 <%--    if ("${resourceType}"=='res_region'){--%>
                 <%--        location.href = "${pageContext.request.contextPath}/resource/myResource?index=index&resType="+ $("#resType").find("option:selected").val()+"&personType=res_region&dm=${dm}";--%>
                 <%--    }else {--%>
                 <%--	 location.href = "${pageContext.request.contextPath}/resource/myResource?index=index&personType=res_person&dm=${dm}";--%>
                 <%--    }--%>
                 <%--}else{--%>
                     location.href = "${pageContext.request.contextPath}/resource/myResource?index=index&resType=" + resType+"&personType="+personType+"&dm=${dm}";
                 // }
                $.alert("上传成功");
            }
        });
    }
}



function loadDescriptionEditor() {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="uploadMicroDescription"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [
				'fontname', 'fontsize', '|', 'forecolor',
				'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter',
				'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'image',
				'link'
			],
		});
		K('input[name=getHtml]').click(function(e) {
			alert(editor.html());
		});
		K('input[name=isEmpty]').click(function(e) {
			alert(editor.isEmpty());
		});
		K('input[name=getText]').click(function(e) {
			alert(editor.text());
		});
		K('input[name=selectedHtml]').click(function(e) {
			alert(editor.selectedHtml());
		});
		K('input[name=setHtml]').click(function(e) {
			editor.html('<h3>Hello KindEditor</h3>');
		});
		K('input[name=setText]').click(function(e) {
			editor.text('<h3>Hello KindEditor</h3>');
		});
		K('input[name=insertHtml]').click(function(e) {
			editor.insertHtml('<strong>插入HTML</strong>');
		});
		K('input[name=appendHtml]').click(function(e) {
			editor.appendHtml('<strong>添加HTML</strong>');
		});
		K('input[name=clear]').click(function(e) {
			editor.html('');
		});
	});
}

$(function () {
    //使用setIimeout方法延迟1秒加载是为了解决webupload第一次点不了的bug
    setTimeout("initMicroUpload()", 500);
    loadDescriptionEditor();
});

$("#shareBox").click(function(e){
    if(shareBoxtype == 0){
        $(this).attr("checked",true);
        shareBoxtype = 1;
        publicShareBoxtype = 0;
        $("#radioType").val("xbzy");
        uploadShare();
        findPublicTextBook('subjectCode', '${param.type}',0);
    }else {
        $(this).attr("checked",false);
        shareBoxtype = 0;
        uploadShare();
    }
});

$("#publicShareBox").click(function(e){
    if(publicShareBoxtype == 0){
        $(this).attr("checked",true);
        publicShareBoxtype = 1;
        shareBoxtype = 0;
        $("#radioType").val("qbzy");
        uploadShare();
        findPublicTextBook('subjectCode', '${param.type}',1);
    }else {
        $(this).attr("checked",false);
        publicShareBoxtype = 0;
        uploadShare();
    }
});

function uploadShare() {
	//资源共享则显示教材目录
	if(shareBoxtype == 1 || publicShareBoxtype == 1) {
		$("#integral_div").show();
		$("#upload_textbook").show();
		$("#upload_textbookcatalog").show();
	} else if(shareBoxtype == 0 && publicShareBoxtype == 0){
		//隐藏教材
		$("#integral_div").hide();
		$("#upload_textbook").hide();
		$("#upload_textbookcatalog").hide();
		//重置学段选中状态
		$("#stageCode").val("");
		//重置科目相应的下拉选项
		$("#subjectCode").html("<option value=''>请选择</option>");
		//重置版本相应的下拉选项
		$("#publisherId").html("<option value=''>请选择</option>");
		//重置年级册次相应的下拉选项
		$("#gradeCodeVolumn").html("<option value=''>请选择</option>");
		//清空目录信息
		$("#dvTextBookCatalog").html("");
	}
}
$(function() {
	var flag= $('#flag').val();
	if(flag==1){
		$("#upload_textbook").show();
		$("#upload_textbookcatalog").show();
	}else if(flag==2){
        $("#upload_textbook").show();
        $("#upload_textbookcatalog").show();
    }
});
</script>
</html>
