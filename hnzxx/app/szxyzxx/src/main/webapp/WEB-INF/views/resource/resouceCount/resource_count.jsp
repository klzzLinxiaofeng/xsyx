<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/sx_statistics.css">
<title></title>
</head>
<script type="text/javascript">
    $(function(){
    	var option = {
    			"selector" : "#grade",
    			"condition" : {schoolId:"${sessionScope[sca:currentUserKey()].schoolId}"},
    			"afterHandler" : function() {
//     				refreshList($("#grade").val());
					selectGrade()
    			},
    			"isUseChosen" : true
    		};
		$.customGradeSelector(option);
	    $(".statistics_page").hide();//隐藏wenben
	    $(".statistics_page:eq(0)").show();//显示第一个wenben
	    $("#fenye_statistics a").click(function(){
	    	console.log($(this).index());
	        $(".statistics_xuanx").removeClass("statistics_xuanx");//移除样式
	        $(this).addClass("statistics_xuanx");//添加样式
	        var i=$(this).index();//获得下标
	        $(".statistics_page").hide();//隐藏wenben
	        $(".statistics_page:eq("+i+")").show();//显示第i个wenben
	    });
	    $("#fenye_statistics a:eq(0)").click();
	});
    $.customGradeSelector = function(options) {
		var defOption = {
			"selector" : "#schoolYear",
			"condition" : {},
			"selectedVal" :  "",
			"afterHandler" : function() {},
			"isUseChosen" : true
		};
		options = $.extend({}, defOption, options || {});
		var selector = $(options.selector);
		selector.html("");
		$.getGrade(options.condition, function(data) {
			selector.append("<option value=''>请选择</option>")
			$.each(data, function(index, value) {
				selector.append("<option value='" + value.uniGradeCode + "'>" + value.name + "</option>")
			});
			selector.val(options.selectedVal);
			options.afterHandler(selector);
			if(options.isUseChosen == null || options.isUseChosen) {
				selector.chosen();
			}
		});
	}
    function selectGrade(){
    	$("#grade").on("change",function(){
    		var grade = $(this).val();
    		refreshList(grade);
    	});
    }
    function refreshList(grade){
    	var val = {};
		if (grade != null && grade != "") {
			val.grade = grade;
		}
		var id = "list_content";
		var url = "/resource/countByGrade";
		myPagination(id, val, url);
    }
    function exportData(){
    	var current_index = $(".statistics_xuanx").index();
    	var countTitles = new Array();
    	var countNums = new Array();
    	var countVols = new Array();
    	var subjectCountTitles = new Array();
    	var subjectCountVols = new Array();
    	$("#countTitle td").each(function(index1,value){
    		countTitles.push($(value).text());
    	});
    	$("#countNum td").each(function(index2,value){
    		countNums.push($(value).text());
    	});
    	$("#countVol td").each(function(index3,value){
    		countVols.push($(value).text());
    	});
    	$(".statistics_page table thead tr td").each(function(index4,value){
    		subjectCountTitles.push($(value).text());
    	});
    	var gradeName = null;
    	$("#list_content tr").each(function(index,value){
    		var td_array = new Array();
    		if(index != 0){
    			td_array.push(gradeName);
    		}
    		$(value).find("td").each(function(idx,val){
    			if(index == 0 && idx == 0){
    				gradeName = $(val).text().trim();
    			}
    			td_array.push($(val).text().trim());
    		});
	    	subjectCountVols.push(JSON.stringify(td_array).trim());
    	});
    	var urlCount = "${ctp}/resource/exportCountData";
    	var urlSubject = "${ctp}/resource/exportSubjectData";
    	var preUrl = "${ctp}/resource/preExportData";
    	var $requestData = {};
    	$requestData.countTitles = countTitles; 
    	$requestData.countNums = countNums; 
    	$requestData.countVols = countVols; 
    	$requestData.subjectCountTitles = subjectCountTitles; 
    	$requestData.subjectCountVols = subjectCountVols == "" ? ["0"] : subjectCountVols; 
    	var loader = new loadLayer();
    	loader.show();
    	$.post(preUrl,$requestData,function(data,status){
    		if("success" === status){
    			if(current_index == 0){
	    			location.href = urlCount;
    			}
    			if(current_index == 1){
	    			location.href = urlSubject;
    			}
    		}
    		loader.close();
    	});
    }
</script>
<body>
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon" />
		<jsp:param value="教学资源统计" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
<div class="jxzy_statistics">
<h3>教学资源统计<span>${teachPlanCount.tpCount + commonMicroCount.miCount + examCount.emCount + learningDesignCount.ldCount}</span><p>资源总数：</p></h3>
    <div class="fenye_statistics" id="fenye_statistics">
        <a href="#" class="statistics_xuanx">总计</a>
        <a href="#">分科统计</a>
    </div>
    <div class="statistics_page">
        <h4>各类型教学资源统计表</h4>
        <table cellspacing="0">
            <tr id="countTitle" style="font-weight: bold;background:#f2f2f2;line-height: 30px;">
                <td></td>
                <td>课件</td>
                <td>微课</td>
                <td>视频</td>
                <td>试题</td>
                <td>教案</td>
            </tr>
            <tr id="countNum">
                <td class="context_color">数量</td>
                <td><span>${teachPlanCount.tpCount}</span></td>
                <td><span>${microCount.miCount}</span></td>
                <td><span>${commonMicroCount.miCount}</span></td>
                <td><span>${examCount.emCount}</span></td>
                <td><span>${learningDesignCount.ldCount}</span></td>
            </tr>
            <tr id="countVol">
                <td class="context_color">容量</td>
                <td><span><fmt:formatNumber  value="${teachPlanCount.tpSize/1048576}"  pattern="0.00"/></span><p>GB</p></td>
                <td><span><fmt:formatNumber  value="${microCount.miSize/1048576}"  pattern="0.00"/></span><p>GB</p></td>
                <td><span><fmt:formatNumber  value="${commonMicroCount.miSize/1048576}"  pattern="0.00"/></span><p>GB</p></td>
                <td><span><fmt:formatNumber  value="${examCount.emSize/1048576}"  pattern="0.00"/></span><p>GB</p></td>
                <td><span><fmt:formatNumber  value="${learningDesignCount.ldSize/1048576}"  pattern="0.00"/></span><p>GB</p></td>
            </tr>
        </table>
    </div>
    <div class="statistics_page">
        <div class="statistics_menu">
            <select id="grade" name="grade" class="chzn-select" style="width: 120px;height: 30px;margin: 8px;"></select>
        </div>
        <h4>分科教学资源统计表</h4>
        <table cellspacing="0" class="subject_statistics">
        	<thead>
	            <tr style="font-weight: bold;background:#f2f2f2;line-height: 30px;">
	                <td>年级</td>
	                <td>科目</td>
	                <td>课件</td>
	                <td>微课</td>
	                <td>视频</td>
	                <td>试题</td>
	                <td>教案</td>
	            </tr>
            </thead>
            <tbody id="list_content">
            	<jsp:include page="./resource_count_list.jsp" />
            </tbody>
        </table>
</div>
	    <div class="statistics_option">
	    <div style="margin: 0 auto;width: 188px;">
	        <a href="#" onclick="exportData();" style="margin-right: 20px;"><img src="${pageContext.request.contextPath}/res/images/statistics_export.png" height="90" width="84"></a>
<%-- 	        <a href="#"><img src="${pageContext.request.contextPath}/res/images/statistics_share.png" height="89" width="83"></a> --%>
	    </div>
    </div>
</div>
</div>
</body>
</html>