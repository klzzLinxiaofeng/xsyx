<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>布置导学案</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dxa/arrange.css">
    <%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<div class="bzdxa">
    <div class="position">* 布置导学案</div>
    <div class="bzdxa_class">
        <label><i>*</i>班级：</label>
        <div class="choose_class">
        <div class="selected_class">
            <input type="radio" name="1"  class="radio" checked="checked" />我所任教的班级
            <input type="radio" name="1" />所有班级
        </div>
            <div class="choose_grade" style="position: relative;left: -43px;">
            <div class="choose">
                <c:forEach items="${myTeachList }" var="team" varStatus="status">
                	<c:if test="${status.first }">
                		<a href="javascript:void(0);" class="display_grade">${team.gradeName }</a>
                	</c:if>
                	<c:if test="${!status.first }">
                		<a href="javascript:void(0);">${team.gradeName }</a>
                	</c:if>
                </c:forEach>
            </div>
             	<c:forEach items="${myTeachList }" var="grade" varStatus="status">
                	<div class="grade">
	                    <div class="check" style="width:100%;"><input type="checkbox" class="all_select"/>全选</div>
	                    <c:forEach items="${grade.teamList}" var="team">
	                    	<div class="check"><input type="checkbox" value="${team.teamId }"/>${team.teamName }</div>
	                    </c:forEach>
                	</div>
                </c:forEach>
                <div class="arrange_button">
                    <button onclick="publishLearningPlan();">布置</button>
                    <button onclick="returnView('${jumpfrom}');">取消</button>
                </div>
            </div>

            <div class="choose_grade">
            <div class="choose_subject">
            	<c:forEach items="${allList }" var="team" varStatus="status">
                	<c:if test="${status.first }">
                	 	<a href="javascript:void(0);" class="display_subject">${team.gradeName }</a>
                	</c:if>
                	<c:if test="${!status.first }">
                		<a href="javascript:void(0);">${team.gradeName }</a>
                	</c:if>
                </c:forEach>
            </div>
                <c:forEach items="${allList }" var="grade" varStatus="status">
                	<div class="grade2">
	                    <div class="check" style="width:100%;"><input type="checkbox" class="all_select"/>全选</div>
	                    <c:forEach items="${grade.teamList}" var="team">
	                    	<div class="check"><input type="checkbox" value="${team.teamId }"/>${team.teamName }</div>
	                    </c:forEach>
                	</div>
                </c:forEach>
                <div class="arrange_button">
                    <button onclick="publishLearningPlan();">布置</button>
                    <button onclick="javascript:history.go(-1);">取消</button>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
</body>
<script type="text/javascript">
$(function(){
    $(".grade").hide();//隐藏wenben
    $(".grade:eq(0)").show();//显示第一个wenben
    $(".choose a").click(function(){
        $(".choose a").removeClass("display_grade");//移除样式
        $(this).addClass("display_grade");//添加样式
        var i=$(this).index();//获得下标
        $(".grade").hide();//隐藏wenben
        $(".grade:eq("+i+")").show();//显示第i个wenben
    });

    $(".choose_grade").hide();//隐藏wenben
    $(".choose_grade:eq(0)").show();//显示第一个wenben
    $(".selected_class input").click(function(){
        $(".selected_class input").removeClass("radio");//移除样式
        $(this).addClass("radio");//添加样式
        var i=$(this).index();//获得下标
        $(".choose_grade").hide();//隐藏wenben
        $(".choose_grade:eq("+i+")").show();//显示第i个wenben
        
        $("input[type='checkbox']").attr("checked", false);
    });

    $(".grade2").hide();//隐藏wenben
    $(".grade2:eq(0)").show();//显示第一个wenben
    $(".choose_subject a").click(function(){
        $(".choose_subject a").removeClass("display_subject");//移除样式
        $(this).addClass("display_subject");//添加样式
        $(".grade2 input").removeAttr("checked");
        var i=$(this).index();//获得下标
        $(".grade2").hide();//隐藏wenben
        $(".grade2:eq("+i+")").show();//显示第i个wenben
        //$(".grade2:eq("+i+")").find("input").prop("checked" , "checked");//切换过来全选
    });
    
    $(".all_select").click(function(){
        if($(this ).is(':checked' )){
               $(this).parent().siblings().children().prop("checked" , "checked");
          } else{
               $(this).parent().siblings().children().removeAttr("checked");
          }
    });
});

function publishLearningPlan() {
	var teamList = new Array();
	
	var teams = $("input[type='checkbox']:checked");
	$(teams).each(function(index, element) {
		var value = $(element).val();
		if(value!=null && "" !=value && value!="on") {
			teamList.push(value);
		}
	})
	
	if(teamList==null || teamList.length==0) {
		$.alert("你未选择班级，请选择班级")
		return false;
	}
	
	$.ajax({
	    url: "${pageContext.request.contextPath}/learningPlan/team/exit",
	    type: "POST",
	    data: {"teamIds":JSON.stringify(teamList),"lpId":"${lpId}"},
	    async: true,
	    success: function(data) {
	    	var info = JSON.parse(data);
	    	if(info.size>0) {
	    		$.confirm("当前导学案已在 "+info.teamNames+" 布置过, 是否重复发布", function() {
	    			publish(teamList);
	    	    });
	    	} else {
	    		publish(teamList);
	    	}
	    }
	});
}

function publish(teamList) {
	var loader = new loadDialog();
    loader.show();
	$.ajax({
	    url: "${pageContext.request.contextPath}/learningPlan/publish",
	    type: "POST",
	    data: {"teamIds":JSON.stringify(teamList),"lpId":"${lpId}"},
	    async: true,
	    success: function(data) {
	    	loader.close();
	    	$.success("发布成功!");
	    	location.href="/learningPlan/index?dm=GUAN_LI_DAO_XUE_AN";
	    }
	});
}

function returnView(jumpfrom) {
	if("edit"==jumpfrom) {
		location.href="${pageContext.request.contextPath}/cr/learningDesign/learningPlan/edit?id=${lpId}";
	} else {
		window.history.go(-1);
	}
}
</script>
</html>