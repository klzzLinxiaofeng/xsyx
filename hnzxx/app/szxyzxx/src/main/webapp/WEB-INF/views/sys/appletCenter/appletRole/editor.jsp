<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>权限设置</title>
<style>
.content_main {
     box-shadow: none; 
    border: none; 
}
.table tr td, .table tr th {
    padding-left: 25px;
    height: 33px;
    line-height: 33px;
}
.btn_cz {
	 margin-top: 13px;
}
.btn_cz button {
    width: 80px;
        height: auto;
    font-size: 14px;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<div class="content_main white">
		<div class="input_select">
			<div class="select_div">
				<span style="font-size: 14px;">选择角色：</span>
			</div>
		</div>
		
		<div style="height: 243px;overflow-y: scroll;">
		<table class="table">
			<thead>
				<tr style="border-top: 1px solid #dddddd;"><th><i class="ck" style="top: -11px;"></i></th><th>角色</th></tr>
			</thead>
			<tbody>
			<c:forEach items="${items}" var="item">
				<tr>
					<td>
						<%--<i data-roleCode="${item.roleCode}" <c:if test="${item.hasPermission}">class="ck"</c:if> ></i>--%>
						<i data-rolecode="${item.roleCode}" class="ck <c:if test="${item.hasPermission}">on</c:if>" ></i>
					</td>
					<td>${item.roleName}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	<div class="btn_cz">
		<button class="btn btn-blue" onclick="save()">保存</button>
		<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
	</div>
</div>
<script>
$('.table tbody i.ck').click(function(){
	
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
    	   $('.table thead i.ck').removeClass('on');
       }
    }else{
        $(this).addClass('on');
        if($('tbody i.ck').length==$('tbody i.ck.on').length){
        	$('.table thead i.ck').addClass('on');
        }
    }
});
$('.table thead i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
});

function save() {
    var roleCodes=[];
	//roleCodes  condition
    $(".ck.on").each(function(index, element) {
        var value = $(this).data("rolecode");
        if(value!=null && "" !=value) {
            roleCodes.push(value);
        }
    })
	if(roleCodes.length==0) {
        $.error("请至少选择一个角色");
        return false;
	}
	// alert(JSON.stringify(roleCodes));
    // var loader = new loadDialog();
    // loader.show();
    var condition={};
    condition.appletId=${condition.appletId};
    condition.ownerId=${condition.ownerId};
    $.ajax({
        url: "${ctp}/sys/appletRole/edit",
        type: "POST",
        data: {
            "roleCodes":roleCodes,
			"appletId":${condition.appletId},
			"ownerId":${condition.ownerId}
        },
        traditional: true,
        async: true,
        success: function(data) {
            // var info = JSON.parse(data);
            // alert(data);
            // loader.close();
            $.success("保存成功");
            // $.closeWindow();
            // window.parent.location.reload();
			<c:if test="${!noList}">
			//console.log($('#core_iframe',window.parent.document).contents().find('body').html())
            // console.log( $('#core_iframe',window.parent.document).contents().find('script').list())
			// $('#core_iframe',window.parent.document).contentWindow().list()
			// console.log(window.parent);
            // window.parent.list();
            parent.core_iframe.window.list();

			</c:if>
            $.closeWindow();
        }
    });


}
</script>
</body>
</html>