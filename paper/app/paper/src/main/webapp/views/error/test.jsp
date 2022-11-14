<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
	<script type="text/javascript">
	
	
		$(function() {
// 			add();
// 			edit();
// 			del();
			get();
		});
	
		function add() {
			var requestData = {};
			requestData.xxDm = "xxDm";
			requestData.mc = "组织活动";
			requestData.sm = "活动说明";
			requestData.ksSj = "1990-12-28";
			requestData.jsSj = "1991-12-28";
			requestData.hdWz = "活动位置";
			requestData.fzr = "负责人";
			requestData.cyr = "参与人";
			requestData.lrr = "001";
			requestData.fids = [ "70", "71", "72" ];
			$.post("${ctp}/zsjy/zshd/creator", requestData, function(data,
					status) {

			});
		}
		
		function edit() {
			var requestData = {};
			requestData.xxDm = "xxDm1";
			requestData.mc = "组织活动1";
			requestData.sm = "活动说明1";
			requestData.ksSj = "1990-12-29";
			requestData.jsSj = "1991-12-29";
			requestData.hdWz = "活动位置1";
			requestData.fzr = "负责人1";
			requestData.cyr = "参与人1";
			requestData.lrr = "002";
			requestData.fids = [ "73", "74", "75" ];
			requestData._method = "put";
			$.post("${ctp}/zsjy/zshd/6312c7fe25a94d4c9600b64b37a724a5" , requestData, function(data, status) {

			});
		}
		
		function del() {
			var requestData = {"_method" : "delete"};
			$.post("${ctp}/zsjy/zshd/6312c7fe25a94d4c9600b64b37a724a5" , requestData, function(data, status) {

			});
		}
		
		function get() {
			$.get("${ctp}/zsjy/zshd/f5ae43115c4d4488b4d86835ffd0a96a", function(data, status) {
				if("success" === status) {
					alert(data);
				}
			});
		}
	</script>
</body>
</html>