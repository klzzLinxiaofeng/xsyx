<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>设置评分标准</title>
</head>
<body style="background-color:#fff !important">
	<div class="score-standard">
    <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>年级</th>
                                   <th>获得流动红旗分数值/分</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                                 <tr class="">
                                     <td>一年级</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>二年级</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>三年级</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>四年级</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                 <tr class="">
                                     <td>五年级</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                                <tr class="">
                                     <td>六年级</td>
                                     <td><input type="text" class="td-spacing"></td>
                                </tr>
                             </tbody>
                        </table>
                             <button class="btn-ensure">保存</button>
</div>
</body>
</html>