<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<style>
.check_con{width: 90%; margin:0 auto; color: #444}
.check_con .check_con_top{ overflow: hidden; line-height: 30px; margin-bottom: -5px;}
.check_con_top .check_con_top_left{ float: left; line-height: 30px;}
.check_con_top .check_con_top_left span{line-height: 30px;
    display: inline-block;
    margin-right: 20px;}
.check_con_top .check_con_top_right{ float: right;overflow:hidden; line-height: 30px;}
.check_con_top .check_con_top_right>div{ float:left; margin-left: 10px; line-height: 30px;}
.check_con_top .check_con_top_right>div input{ line-height: 28px; }
.check_con_top .check_con_top_right>div select{ height: 28px; width: 140px}
.check_but{background: #e68923; border: none; color:#fff; padding:0 15px; cursor:pointer}
.check_con_bottom_title{border-bottom:1px solid #ebebeb; overflow: hidden; margin-bottom: 10px;}
.check_con_bottom_title .check_con_title_left{ float: left; font-weight: bold}
.check_con_bottom_title .check_con_title_right{float: right}
.check_con_bottom_title .check_con_title_right{ display: inline-block;}
.check_con_bottom_list ul li{ width: 25%; float: left; }
.check_con_bottom_list ul li span{text-align: center; background: #ebebeb;display: block; width: 90%; margin: 10px auto;}
#table_check{ width: 100%}
    #table_check th{ font-weight: bold; border-bottom: 1px solid #ebebeb; text-align: left;  padding: 15px 0;}
#table_check td{ line-height: 1.5;    padding: 15px 0; border-bottom: 1px solid #e6e6e6; vertical-align: top;}
#table_check td:nth-child(2){ color: #0fa228}
#table_check td:nth-child(2).red{ color: red}

/*星星样式*/
.content{
}
.title{
    font-size:14px;
    background:#dfdfdf;
    padding:10px;
    margin-bottom:10px;
}
.block{
    width:100%;
    margin:0 0 20px 0;
    padding-top:10px;
    padding-left:50px;
    line-height:21px;
}
.block .star_score{
    float:left;
}
.star_list{
    height:21px;
    margin:50px;
    line-height:21px;
}
.block p,.block .attitude{
    padding-left:20px;
    line-height:21px;
    display:inline-block;
}
.block p span{
    color:#C00;
    font-size:16px;
    font-family:Georgia, "Times New Roman", Times, serif;
}

.star_score {
    background:url(${pageContext.request.contextPath}/res/images/bp/starScore/stark2.png);
    width:160px;
    height:21px;
    position:relative;
}

.star_score a{
    height:21px;
    display:block;
    text-indent:-999em;
    position:absolute;
    left:0;
}

.star_score a:hover{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stars2.png);
    left:0;
}

.star_score a.clibg{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stars2.png);
    left:0;
}

#starttwo .star_score {
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/starky.png);
}

#starttwo .star_score a:hover{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/starsy.png);
    left:0;
}

#starttwo .star_score a.clibg{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/starsy.png);
    left:0;
}
/* ${ctp}/res/images/bbx/bp/starScore */
/*星星样式*/

.show_number li{
    width:240px;
}

.atar_Show{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stark2.png);
    width:160px; height:21px;
    position:relative;
    float:left;
}

.atar_Show p{
    background:url(${pageContext.request.contextPath}/res/images/bbx/bp/starScore/stars2.png);
    left:0;
    height:21px;
    width:134px;
}

.show_number li span{
    display:inline-block;
    line-height:21px;
}
.titme_pj{ text-align: right; color: #999;font-size: 12px}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
<script src="${ctp}/res/js/starScore/starScore.js"></script>
</head>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="SyllabusEvaluate" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<%-- <div class="widget-head">
						<h3>
							SyllabusEvaluate列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
								</c:if>
							</p>
						</h3>
					</div> --%>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<%-- <div class="select_b">
								<div class="select_div">
									<span>周数：</span>
									<select id="week" name="week" >
					                    <option value ="volvo">选择周数</option>
					                    <c:forEach items="${weeks}" var="week">
					                    	<c:choose>
					                    		<c:when test="${week.isCurrent eq true}">
					                    			<option value ="${week.week }" selected="selected">第${week.week }周</option>
					                    		</c:when>
					                    		<c:otherwise>
								                    <option value ="${week.week }">第${week.week }周</option>
					                    		</c:otherwise>
					                    	</c:choose>
					                    </c:forEach>
					                </select>
									<input type="text" id="week" name="week" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div> --%>
						
						<%-- <span> 星期 : ${dayOfWeek} </span>
						<span> 课节 : ${lesson}  </span>
						
						<div class="select_div">
							<span>周数：</span>
							<select id="week" name="week" >
			                    <option value ="volvo">选择周数</option>
			                    <c:forEach items="${weeks}" var="week">
			                    	<c:choose>
			                    		<c:when test="${week.isCurrent eq true}">
			                    			<option value ="${week.week }" selected="selected">第${week.week }周</option>
			                    		</c:when>
			                    		<c:otherwise>
						                    <option value ="${week.week }">第${week.week }周</option>
			                    		</c:otherwise>
			                    	</c:choose>
			                    </c:forEach>
			                </select>
			                <button type="button" class="btn btn-primary" onclick="search()">查询</button>
			             </div> --%>
						
							<!-- <table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th style="display: none;">id</th>
											<th style="display: none;">bw_syllabus_lesson主键</th>
											<th style="display: none;">第几周</th>
											<th>学生姓名</th>
											<th>评价分数</th>
											<th>评价内容</th>
											<th>记录创建时间</th>
											<th>记录修改时间</th>
											<th>记录是否删除</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="syllabusEvaluate_list_content"></tbody>
							</table> -->
							
<div class="check_con">
    <div class="check_con_top">
        <div class="check_con_top_left"><span>星期：${dayOfWeek}</span><span>课节：${lesson}</span></div>
        <div class="check_con_top_right">
            <div>选择周数：</div>
            <div>
                <select id="week" name="week" style="height: 31px;">
                    <option value ="volvo">选择周数</option>
                    <c:forEach items="${weeks}" var="week">
                    	<c:choose>
                    		<c:when test="${week.isCurrent eq true}">
                    			<option value ="${week.week }" selected="selected">第${week.week }周</option>
                    		</c:when>
                    		<c:otherwise>
			                    <option value ="${week.week }">第${week.week }周</option>
                    		</c:otherwise>
                    	</c:choose>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="button"  value="查询" class="check_but" onclick="search()">
            </div>
        </div>
    </div>
    <div class="check_con_bottom" >
        <table cellspacing="0" border="0" id="table_check">
            <tr>
                <th width="100px">学生名字</th>
                <th width="200px">评分<span style="color:red">&nbsp;(平均分:<span id="average"></span>分)</span></th>
                <th>评价内容</th>
                <th width="130px" style="text-align: center;">操作</th>
            </tr>
            <tbody id="syllabusEvaluate_list_content"></tbody>
        </table>
    </div>
</div>
							
							<%-- <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="syllabusEvaluate_list_content" />
								<jsp:param name="url" value="/bbx/syllabusEvaluate/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include> --%>
							<input type="text" style="display: none;" id="syllabusLessonId" name="syllabusLessonId" value="${syllabusLessonId}">
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
scoreFun($("#startone"))
scoreFun($("#starttwo"),{
    fen_d:22,//每一个a的宽度
    ScoreGrade:5//a的个数5
});
//显示分数
$(".show_number li p").each(function(index, element) {
    var num=$(this).attr("tip");
    var www=num*2*16;//
    $(this).css("width",www);
    $(this).parent(".atar_Show").siblings("span").text(num+"分");
});
// ===显示分数插件结束

	$(function() {
		var val = {};
		val.week = $("#week").val();
		var syllabusLessonId = $("#syllabusLessonId").val();
		var url = "${ctp}/bbx/syllabusEvaluate/index?syllabusLessonId="+syllabusLessonId+"&sub=list&dm=${param.dm}";
		$("#syllabusEvaluate_list_content").load(url,val);
	});
	
	function search() {
		var val = {};
		var week = $("#week").val();
		console.log(week);
		if (week == null || week == "" || week == "volvo") {
			$.error("请选择周数!");
			return;
		}
		val.week = week;
		$("#syllabusEvaluate_list_content").empty();
		var syllabusLessonId = $("#syllabusLessonId").val();
		var id = "syllabusEvaluate_list_content";
		var url = "${ctp}/bbx/syllabusEvaluate/index?syllabusLessonId="+syllabusLessonId+"&sub=list&dm=${param.dm}";
		$("#syllabusEvaluate_list_content").load(url,val);
		// myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/syllabusEvaluate/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/syllabusEvaluate/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/syllabusEvaluate/viewer?id=' + id, '700', '400');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/syllabusEvaluate/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>