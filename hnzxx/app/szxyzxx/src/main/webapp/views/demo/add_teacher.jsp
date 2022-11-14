<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
input[type="text"],select,textarea{margin-bottom:0;width:100%;}
input[type="text"],textarea{border:0 none;width:95%;}
table{background-color:#fff;}
table td img{width:155px;height:187px;}
table td .up_img {
    background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
    position:absolute;
    top:100px;
    left:30px;
}
</style>
</head>
<body style="background-color:#F5F5F5">
	<div class="add_teacher">
		<p class="p1">添加教师</p>
		<p><span class="red">*</span>为必填项</p>
	</div>
	<table class="table table-bordered">
		<tbody>
		<tr><td><span class="red">*</span>姓名</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>英文名</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td rowspan="6" style="width:165px;position:relative"><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"><a href="javascript:void(0)" class="up_img"></a></td></tr>
			<tr><td>性别</td><td><select><option>男</option><option>女</option></select></td><td>出生日期</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>工号</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>证件类型</td><td><select><option>身份证</option><option>健康证</option></select></td></tr>
			<tr><td><span class="red">*</span>手机号码</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>证件号码</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>参加工作时间</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>国籍</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>入职时间</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>民族</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>离职时间</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>籍贯</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>职务</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>出生地</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>岗位职业</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>婚姻状况</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>学历</td><td><select><option>大专</option><option>本科</option></select></td><td>政治面貌</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>特长</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>宗教信仰</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>岗位编制</td><td><select><option>无</option><option>有</option></select></td><td>户口性质</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>在职状态</td><td><select><option>在编</option><option>离职</option></select></td><td>户口所在地</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>办公室电话</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>现地址</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>邮件</td><td><input type="text" id=""  value="" data-id="" placeholder=""/></td><td>居住地址</td><td colspan="2"><input type="text" id=""  value="" data-id="" placeholder=""/></td></tr>
			<tr><td>备注</td><td colspan="4"><textarea></textarea></td></tr>
		</tbody>
	</table>
	<div style="text-align:center;">
		<button class="btn">保存</button><button class="btn">取消</button>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
	})
</script>
</html>
 