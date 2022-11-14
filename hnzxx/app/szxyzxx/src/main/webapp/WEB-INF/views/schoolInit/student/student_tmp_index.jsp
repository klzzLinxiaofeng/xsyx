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
<title></title>
</head>
<body style="background-color: #e3e3e3;height:100%"  onload="IFrameResize()">
	<div class="sjcsh_xx_detail">
		<div class="f_left">
			<ul>
				<li class="on" id="spi_daoru_ok">导入成功</li>
				<li id="spi_warn_data" size="${warnSize }">警告数据（${warnSize }）</li>
				<li id="spi_wrong_data" size="${errorSize }">错误数据（${errorSize }）</li>
			</ul>
		</div>
		<div class="f_right">
			<button class="btn btn-blue jxImport">继续导入</button>
			<button class="btn btn-lightGray" onclick="batchDelete(0)">批量删除</button>
		</div>
		<div class="f_right" style="display:none">
			<button class="btn btn-blue jxImport">继续导入</button>
			<button class="btn btn-red" onclick="batchDelete(1)">批量删除</button>
			<div class="plsc_ts">
				<span class="ts">注：确定批量导入将覆盖冲突的数据</span>
				<span class="tu"><i class="fa fa-exclamation"></i></span>
			</div>
		</div>
		<div class="f_right"  style="display:none">
			<button class="btn btn-blue jxImport">继续导入</button>
		</div>
	</div>
	<div style="background-color: #ffffff;padding:20px;margin: 0 20px 20px;">
		<iframe src="${pageContext.request.contextPath}/tmp/student/list?index=index" width="100%" frameborder="0" id="iframe_third" name="iframe_third" style="min-height:441px;max-height:690px"></iframe>
	</div>
	<div class="tis_jxdr" style="display:none;text-align: center;padding-top:20px;">
	<p>继续导入将覆盖当前临时数据</p>
	<p>是否继续导入</p>
</div>
<script>
function IFrameResize(){  
	 //alert(this.document.body.scrollHeight); //弹出当前页面的高度  
	 var obj = parent.document.getElementById("iframe_sencond");  //取得父页面IFrame对象  
	 //alert(obj.height); //弹出父页面中IFrame中设置的高度  
	 var _height=this.document.body.scrollHeight;
	 obj.height = _height;  //调整父页面中IFrame的高度为此页面的高度  
	 $("#iframe_first",window.parent.parent.document).height(_height+71)
}
	
$('.sjcsh_xx_detail ul li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	var t = setTimeout("IFrameResize()",1000)
	$('.f_right').hide();
	$('.f_right').eq($(this).index()).show();

	var index = $(".sjcsh_xx_detail ul li").index(this);
	
	$(".f_right").each(function() {
		$(this).hide();
	})
	$(".f_right").eq(index).show();
	
	var url = "${pageContext.request.contextPath}/tmp/student/list?index=index&status=" + index;
	$("#iframe_third").attr("src", url);
	
});

$('#iframe_third').load(function(){  
    var iframeHeight=$(this).contents().height();
    $(this).height(iframeHeight+'px');
});

$('.jxImport').click(function(){
	layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '20px',
		  area: ['337px', '191px'],
		  title: '继续导入提示', //不显示标题
		  content: $('.tis_jxdr'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			 $(window.parent.parent.document).find("#iframe_first").attr("src", "${pageContext.request.contextPath}/student/init/index")
			 //$(window.parent.document).find("#student_import").click();
		  }
	});
});

function batchDelete(type) {
	var ids = new Array();
	
	$(window.frames["iframe_third"].document).find(".table .on").each(function() {
		var studentid = $(this).attr("studentid");
		if(typeof(studentid)!="undefined") {
			ids.push(studentid);
		}
	});
	
	if(ids.length==0) {
		return;
	}
	
	var loader = new loadDialog();
    loader.show();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/student/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	loader.close();
        	$.success("删除成功!");
        	if(type==0) {
            	$("#spi_daoru_ok").click();
        	} else {
        		var size = $("#spi_warn_data").attr("size");
            	size=size-ids.length;
            	$("#spi_warn_data").text("警告数据（"+size+"）");
            	$("#spi_warn_data").attr("size",size);
            	$("#spi_warn_data").click();
        	}
        }
    });
}
</script>
</body>
</html>