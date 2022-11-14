<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理导学案任务</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dxa/arrange.css">
     <%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<div class="bzdxa">
	<input type="hidden" value="" id="reviewbj">
	<input type="hidden" value="" id="reviewnj">
    <div class="position" dm="${!acl:hasPermission(sessionScope[sca:currentUserKey()].id, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">* 管理导学案任务</div>
    <div class="frame">
    	<c:if test="${!acl:hasPermission(sessionScope[sca:currentUserKey()].id, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
	        <div class="kemu" style="float: left;">
		        <div class="pull-down" style="line-height: 37px;height: 37px; float:left;" >
			        <span style="float:left;">班级</span>
			        <select class="chzn-select" id="bj" name="bj">
			            <c:forEach items="${classGradeMap}" var="cm">
			                <c:forEach items="${cm.value}" var="teamMap">
			                    <c:forEach items="${teamMap}" var="team">
			                     <option value="${team.key}">${fn:split(team.value,"&&")[0]}</option>
			                    </c:forEach>
			                </c:forEach>
			            </c:forEach>
			         </select>
		        </div>
	        </div>
        </c:if>
    
    	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
	    	<div class="select_div" style="display: none;">
				<span>学年：</span>
				<select id="xn" name="xn" class="chzn-select" style="width: 120px;"></select>
			</div>
	    	<div class="select_div" style="display: none;">
				<span style="padding-left: 30px;">学期： </span> <select id="xq"
					name="xq" class="chzn-select" style="width: 160px;" ></select>
			</div>
	        <div class="pull-down" style="line-height: 37px;height: 37px;float: left;">
				<span style="float:left;">年级：</span><select id="nj" name="nj" class="chzn-select" style="width: 120px;"></select>
			</div>
			<div class="pull-down" style="line-height: 37px;height: 37px;float: left;">
				<span style="float:left;">班级：</span><select id="bj" name="bj" class="chzn-select" style="width: 120px;"></select>
			</div>
		</c:if>
        <div class="pull-down">科目：
        	<select id="subject">
        		<option value="">请选择科目</option>
        		<c:forEach items="${subjectList }" var="subject">
					<option value="${subject.code }">${subject.name }</option>
				</c:forEach>
        	</select>
        </div>
        <div class="pull-down"><button onclick="changeAllClass()">查询</button></div>
        <div class="clear"></div>
        <div class="ranking" style="margin:0;" id="kv">
        	<jsp:include page="./manage_list.jsp" />
        </div>
        <div class="zy_list" style="margin-top:20px;height:50px;">
	      	<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	          	<jsp:param name="id" value="kv" />
	          	<jsp:param name="url" value="/learningPlan/index?index=list" />
	          	<jsp:param name="pageSize" value="" />
	        </jsp:include>
     		<div class="clear"></div>
		</div>
    </div>
</div>
</body>
<script type="text/javascript">
var index = 0;
	function deleteTask(id) {
		$.confirm("确认删除该任务吗?", function() {
			var loader = new loadDialog();
	        loader.show();
			$.ajax({
	            url: "${pageContext.request.contextPath}/learningPlan/delete?id="+id,
	            type: "DELETE",
	            data: {},
	            async: true,
	            success: function(data) {
	           		loader.close();
	           		$.success("删除成功");
	           		changeAllClass();
	            }
	        });
		});
	}
	
	function preview(id) {
		var url = "${pageContext.request.contextPath}/cr/learningDesign/learningPlan/edit?type=view&editable=false&id="+id;
		window.open(url, "查看")
		//location.href=url;
        //leftHidden();
	}
	
	function taskDetail(id) {
		location.href="${pageContext.request.contextPath}/learningPlan/task/detail?taskId="+id;
	}
	
	function activityDetail(id) {
		location.href="${pageContext.request.contextPath}/learningPlan/activity/list?taskId="+id;
	}
	
	function tj(id){
		location.href="${pageContext.request.contextPath}/learningPlan/task/statistics/h5?taskId="+id;
	}
	
	function changeAllClass() {
    	var classId=$('#bj').val();
    	var subjectCode=$('#subject').val();
    	
        if(classId===""){
        	$.error("请选择班级");
        	return false;
        }
        
        $("#reviewnj").val($("#nj").val());
    	$("#reviewbj").val(classId);
        
        var url = "/learningPlan/index";
        var data = {"relateId": classId,"relateType":"01","dm":"${param.dm}","index":"list", "subjectCode":subjectCode};
        var loader = new loadDialog();
        loader.show();
        myPagination("kv", data, url,function() {
        	loader.close();
        });
    }
	
	function leftHidden() {
		$(window.parent.document).find(".leftbar").hide();
		$(window.parent.document).find(".left_mu").css({
			width : '15px'
		});
		 $(window.parent.document).find(".man_right").css({
			marginLeft : '0px'
		});
		$(window.parent.document).find(".left_close").addClass("left_open");
	}

    $(function() {
    	var gradeId = $("#reviewnj").val();
    	var relateId = $("#reviewbj").val();
    	var permission = "${acl:hasPermission(sessionScope[sca:currentUserKey()].id, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}";
    	if("false"==permission) {
    		$('#bj').chosen();
    	} else {
    		if(gradeId!="" && relateId!="") {     
	        	$.initCascadeSelector({
					"type" : "team",
					"selectOne":true,
					"isEchoSelected" : true, //是否回显伊选中的选项，默认是false
	                "yearSelectedVal" : "${sessionScope[sca:currentUserKey()].schoolYear}",
    			   	"gradeSelectedVal" : gradeId, //要回显的年级唯一标识
    			  	"teamSelectedVal" : relateId //要回显的班级唯一标识
				});
	        } else {
	        	$.initCascadeSelector({
					"type" : "team",
					"selectOne":true,
				});
	        }
    	}
        changeAllClass();
    });
</script>
</html>