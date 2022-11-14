<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title>学年学期</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
	<div class="container-fluid" style="padding-bottom: 20px;">
	    <p class="top_link"><span onclick="toIndex();">数据初始化</span> > <span class="s1">学年学期</span></p>
	    <div class="content_main white">
	        <div class="content_top" style="height: 68px;">
	            <div class="f_left"><p class="p1" style="line-height: 35px;">${schoolName}</p><a class="s2" href="${pageContext.request.contextPath}/school/schoolYear/index">学年/学期管理</a></div>
	            <div class="f_right"><button class="btn btn-blue xjxn" style="margin: 18px 20px 0 0;" onclick="loadCreateYearPage();">新建学年</button></div>
	        </div>
	        <table class="table xn_table">
	            <thead>
	            <tr><th>序号</th><th>名称</th><th>开始时间</th><th>结束时间</th><th class="caozuo">操作</th></tr>
	            </thead>
	            <tbody>
	                <c:forEach items="${yearList}" var="item" varStatus="status">
	                    <tr>
	                        <td>${status.count}</td>
	                        <td>${item.year.name}</td>
	                        <td><fmt:formatDate value='${item.year.beginDate}' pattern='yyyy-MM-dd'/></td>
	                        <td><fmt:formatDate value='${item.year.finishDate}' pattern='yyyy-MM-dd'/></td>
	                        <td class="caozuo">
	                            <button class="btn btn-green" onclick="loadEditYearPage('${item.year.id}');">编辑学年</button>
	                            <button class="btn btn-blue xjxq" onclick="loadCreateTermPage('${item.year.year}');">新建学期</button>
	                        </td>
	                    </tr>
	                    <c:forEach items="${item.termList}" var="var">
	                        <tr <c:if test="${var.isCurrent}">class="current"</c:if>>
	                            <td><c:if test="${var.isCurrent}">当前学期</c:if></td>
	                            <td>${var.term.name}</td>
	                            <td><fmt:formatDate value='${var.term.beginDate}' pattern='yyyy-MM-dd'/></td>
	                            <td><fmt:formatDate value='${var.term.finishDate}' pattern='yyyy-MM-dd'/></td>
	                            <td class="caozuo">
	                                <button class="btn btn-peaGreen" onclick="loadEditTermPage('${var.term.id}');">编辑学期</button>
	                                <c:if test="${!var.isCurrent}">
	                                    <button class="btn btn-lightGray setUpCurrent" onclick="setCurrentTerm('${var.term.id}');">设置为当前学期</button>
	                                </c:if>
	                            </td>
	                        </tr>
	                    </c:forEach>
	                </c:forEach>
	            </tbody>
	        </table>
	
	        <div class="next_1_0">
	            <p style=" margin-bottom: 23px;color: #f44336;">请新建“学年”和“学期”，设置完成后点击“下一步”，为当前学期创建数据</p>
	            <button id="next" class="btn btn-blue" disabled="disabled" onclick="next()" style=" float: none; padding: 5px 12px; ">下一步</button>
	        </div>
	    </div>
	</div>
	<div class="tck_xjxn" style="display:none">
	    <div class="select_div">
	        <span><i>*</i>开始时间：</span>
	        <input type="text" placeholder="请输入开始时间 如2014-09-01" id="xn_start">
	    </div>
	    <div class="select_div">
	        <span><i>*</i>结束时间：</span>
	        <input type="text" placeholder="请输入结束时间 如2015-07-01" id="xn_end">
	    </div>
	    <div class="select_div">
	        <span><i>*</i>学年名称：</span>
	        <input type="text" placeholder="请输入学年名称 如2014-2015学年" id="xn_school_year_name">
	    </div>
	</div>
	<div class="tck_xjxq" style="display:none">
	    <div class="select_div">
	        <span><i>*</i>国标学期名称：</span>
	        <select class="span2"><option>秋季学期（上学期）</option></select>
	    </div>
	    <div class="select_div">
	        <span><i>*</i>校内学期名称：</span>
	        <input type="text" value="秋季学期（上学期）" id="xq_school_term_name">
	    </div>
	    <div class="select_div">
	        <span><i>*</i>开始时间：</span>
	        <input type="text" placeholder="请输入开始时间 如2014-09-01" id="xq_start">
	    </div>
	    <div class="select_div">
	        <span><i>*</i>结束时间：</span>
	        <input type="text" placeholder="请输入结束时间 如2015-07-01" id="xq_end">
	    </div>
	</div>
</body>
<script>
$(function() {
	if($(".current").length!=0) {
		$("#next").removeAttr("disabled");
	}
})

function next() {
	window.location.href="${pageContext.request.contextPath}/school/init/page";
}

function toIndex() {
    window.location.href="${pageContext.request.contextPath}/school/init/index";
}

$(function(){
    //学年行字体加粗
    $('tbody tr').each(function(){
        if($(this).children('td:first-child').text()!='' && $(this).children('td:first-child').text()!='当前学期'){
            $(this).addClass('bm');
        }
    });
})
//设置为当前学期
$('body').on('click','.setUpCurrent',function(){
    $('tbody tr.current').children('td:first-child').text('');
    $('tbody tr.current').removeClass('current');
    $(this).parent().siblings('td:first-child').text('当前学期');
    $(this).parents('tr').addClass('current');
    $("#next").removeAttr("disabled");
});

function loadCreateYearPage() {
    $.initWinOnTopFromLeft_qyjx('新建学年', '${pageContext.request.contextPath}/teach/schoolYear/creator', '550', '290');
}

function loadCreateTermPage(year) {
    $.initWinOnTopFromLeft_qyjx('新建学期', "${pageContext.request.contextPath}/teach/schoolTerm/creatorOther?year=" + year, '550', '350');
}

function loadEditYearPage(id){
    $.initWinOnTopFromLeft_qyjx("编辑学年", '${pageContext.request.contextPath}/teach/schoolYear/editor?id=' + id + '&isCK=""', '550', '290');
}

function loadEditTermPage(id) {
    $.initWinOnTopFromLeft_qyjx("编辑学期", '${pageContext.request.contextPath}/teach/schoolTerm/editor?id=' + id + '&isCK=""', '550', '350');
}

function setCurrentTerm(id) {
    $.post("${pageContext.request.contextPath}/teach/schoolTerm/currentTerm?id=" + id, function(data, status) {
        if ("success" === status) {
            if ("success" === data) {
                location.reload();
            } else if ("fail" === data) {
                $.error("设置失败，系统异常!");
            }
        }
    });
}

//编辑学年
function editSchoolYear(obj){
    $('#xn_start').val($(obj).parent('td').siblings('td:nth-child(2)').text());
    $('#xn_end').val($(obj).parent('td').siblings('td:nth-child(3)').text());
    $('#xn_school_year_name').val($(obj).parent('td').siblings('td:nth-child(4)').text());
    layer.open({
        type: 1,
        shade: false,
        area: ['406px', '291px'],
        title: '编辑学年', //不显示标题
        content: $('.tck_xjxn'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        cancel: function(){
            layer.close();
        },
        btn: ['确定','取消'],//按钮
        btn1: function(index, layero){

            $(obj).parent('td').siblings('td:nth-child(2)').text( $('#xn_start').val().trim());
            $(obj).parent('td').siblings('td:nth-child(3)').text($('#xn_end').val().trim());
            $(obj).parent('td').siblings('td:nth-child(4)').text($('#xn_school_year_name').val().trim());
        }
    });
}

//编辑学期
function editSchoolTerm(obj){
    $('#xq_start').val($(obj).parent('td').siblings('td:nth-child(2)').text());
    $('#xq_end').val($(obj).parent('td').siblings('td:nth-child(3)').text());
    $('#xq_school_term_name').val($(obj).parent('td').siblings('td:nth-child(4)').text());
    layer.open({
        type: 1,
        shade: false,
        area: ['422px', '341px'],
        title: '新建学期', //不显示标题
        content: $('.tck_xjxq'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        cancel: function(){
            layer.close();
        },
        btn: ['确定','取消'],//按钮
        btn1: function(index, layero){
            $(obj).parent('td').siblings('td:nth-child(2)').text( $('#xq_start').val().trim());
            $(obj).parent('td').siblings('td:nth-child(3)').text($('#xq_end').val().trim());
            $(obj).parent('td').siblings('td:nth-child(4)').text($('#xq_school_term_name').val().trim());
        }
    });
}
</script>
</html>