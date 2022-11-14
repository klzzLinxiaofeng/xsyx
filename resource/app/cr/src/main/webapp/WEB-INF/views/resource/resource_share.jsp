<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="/views/embedded/common.jsp"%>
<link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css" rel="stylesheet"/>
<!--[if IE 7]>
                  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
                <![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js" type="text/javascript"></script>
<body style="background-color:#eee">
<div class="form-horizontal" style="padding-top:20px;">
	<div class="control-group">
		<label class="control-label" style="width:100px;">标题：</label>
		<div class="controls" style="margin-left: 120px;">
			<input type="text" value="${item.title}" id="t" style="height:30px;line-height:30px;">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" style="width:100px;">所需积分：</label>
		<div class="controls" style="margin-left: 120px;">
			<select id="integral" style="width:206px;">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" style="width:90px;">共享：</label>
		<div class="controls" style="margin-left:120px;">
			<input type="radio" id="shareBox" name = "shareBox" value="xbzy"><span style="margin: 7px;">：校本资源</span>&nbsp;
			<input type="radio" id="publicShareBox" name = "shareBox" value="qbzy"><span style="margin: 7px;">：区本资源</span>
			<input type="hidden" id="radioType">
		</div>
	</div>
    <div class="control-group" id="upload_textbook" hidden="true">
        <label class="control-label" style="width:100px;">
            <span class="red">*</span> 教材：
        </label>
        <div class="controls" style="margin-left: 120px;">
            <jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include>	
        </div>
     </div>
     <div class="control-group" id="upload_textbookcatalog" hidden="true">
          <label class="control-label" style="width:100px;">
              <span class="red">*</span> 目录：
          </label>
          <div id="dvTextBookCatalog" class="select_div" style="margin-left: 120px;"></div>
      </div>
	  <div class="form-actions tan_bottom">
	  		<button class="btn btn-warning" type="button" onclick="verifyAdd();">确认共享</button>	
	  </div>
</div>
<script type="text/javascript">
  $(function() {
//	  uploadShare();
  });

  var shareBoxtype = 0;
  var publicShareBoxtype = 0;

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
  		$("#upload_textbook").show();
  		$("#upload_textbookcatalog").show();
  }
  function verifyAdd() {
	 var personType="${personType}";
   	 var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
     var array = gradeCodeVolumnValue.split("-");
     var grade;
     var volumn;

     if (array.length == 2) {
         grade = array[0];
         volumn = array[1];
     }
     var catalogEnd=0;
     var aa = $("#dvTextBookCatalog").children("select").length;
     var catalogTemp;
     for (var num = aa; num >= 1; num--) {
         var catalogname = "catalog" + num

         catalogTemp = $("#" + catalogname).val();

         if (Number(catalogTemp) > 0) {
             catalogEnd = catalogTemp;
             catalogEnd = $("#" + catalogname).find("option:selected").attr("data-code");

             break;
         } else {
             catalogEnd = 0;
         }

     }
	 $.confirm("确定共享此资源吗？", function() {
		 var title = $.trim($("#t").val());
         var code="${code}";
         <%--if(code!== $("#stageCode").val()&&"${item.resType}"==='4'){--%>
        	 <%--$.error("与个人资源的科目不符，请返回修改个人资源的科目。");--%>
        	 <%--return false;--%>
         <%--}--%>
         var catalog1 = $("#catalog1").val();
     
         var textbookId = catalog1;
       
         var titleLength=title.trim().length;
      
         if (title == null || title == "") {
             $.alert("请输入资源标题");
             return false;
         }
         if (titleLength > 100) {
             $.alert("标题不能超过50字");
             return false;
         }
         if (textbookId == null || textbookId == "") {
             $.alert("请输入目录");
             return false;
         }
         if(catalog1 == null ||catalog1 == ""){
     		 $.alert("请选择目录");
     		 return false;
     	 }
         var integral = $("#integral").val();
         if(integral==null || integral == "") {
        	 $.alert("请输入所需积分");
        	 return false;
         }
         var shareType = "";
         obj = document.getElementsByName("shareBox");
         for(k in obj){
             if(obj[k].checked)
                 shareType+=obj[k].value+",";
         }
   	 	$.ajax({
         url: "${pageContext.request.contextPath}/resource/verifyAdd",
         type: "POST",
         data:{
                     "resType": "${item.resType}",
                     "resId":"${item.objectId}",
                     "title":$('#t').val(),
                     "Id":"${item.id}",
                     "stageCode": $("#stageCode").val(),
                     "subjectCode": $("#subjectCode").val(),
                     "gradeCode": grade,
                     "version": $("#publisherId").val(),
                     "volumn": volumn,
                     "stageName": $("select[id=stageCode] option:selected").text(),
                     "subjectName": $("select[id=subjectCode] option:selected").text(),
                     "versionName": $("select[id=publisherId] option:selected").text(),
                     "textbookId": $("#catalog1").val(),
                     "catalogEnd": catalogEnd,
                     "integral": integral,
			 		  "shareType":shareType
                 },
         async: false,
         success: function () {
        	 $.success("资源共享成功！");
        	 parent.core_iframe.location.href = "${pageContext.request.contextPath}/resource/myResource?resType=${r.resType}&index=index&personType=res_person&dm=${param.dm}";
        	 $.closeWindow();
         }
     });
	});
 }
</script>
</body>