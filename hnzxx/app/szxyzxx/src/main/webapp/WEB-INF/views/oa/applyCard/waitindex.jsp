<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>车辆</title>
<script type="text/javascript">
	$(function(){
		addData();
		
		$(".entry #allMenuId li a").click(function(){
			$(".entry ul li").removeClass("on");
			$(this).parent().addClass("on");
		});
		$('body').on('click','.shenhe .s_four .cancel,.shenhe .close_div',function(){
			$(this).parent().parent().hide();
			$(".zhezhao").hide();
		});
		$(".top_ul li a").click(function(){
			var i=$(this).parent().index();
			$(".top_ul li a").removeClass("on");
			$(this).addClass("on");
		})
		$("body").on('click','.pz button',function(){
			$(".pz button").removeClass("btn-warning");
			$(this).addClass("btn-warning");
		})
		//搜索
		ss();
		
		//月份下拉选
		getYear();
		
		//onchange事件
		dateChangeInit();
		});
	function shenhe(id){
		$("#zhezhao"+id).show();
		$("#"+id).show();
		var h = document.documentElement.clientHeight;
		var w= document.documentElement.clientWidth;
		var h1 = (document.documentElement.clientHeight-455)/2;
		var w1= (document.documentElement.clientWidth-900)/2;
		$(".zhezhao").css({"width":w,"height":h});
		$(".shenhe").css({"left":w1,"top":h1});
	}
	
	function pass(id){
		$("#status"+id).val("1");
	}
	
	function noPass(id){
		$("#status"+id).val("4");
	}
	
	function ss(){
		$("#ss").keyup(function(e){
		    if(!e) var e = window.event; 
		    	var val = {
						"ssword" : $("#ss").val()	
					};
					var id = "wait";
					var url = "/oa/applycard/index?sub=list&auditType="+$("#spStatus").val()+"&menu=sp&dm=${param.dm}";
					myPagination(id, val, url);
// 					addData();
		 });
	}
	
	function getData(type){
			var val = {};
			var id = "wait";
			var url = "";
			$.ajaxSetup({
			   async: false
			});
			if(type==="wait"){
				//后面用于区分是在审批还是未审批下的部门的人
				window.location.reload();
				$("#spStatus").val("wait");
// 				url = "/oa/applycard/index?sub=list&auditType=wait&menu=sp&dm=${param.dm}";
// 				myPagination(id, val, url);
// 				$("#tj_list").hide();
// 				$("#sq_list").show();
			}else if(type==="already"){
				$("#spStatus").val("already");
				url = "/oa/applycard/index?sub=list&auditType=already&menu=sp&dm=${param.dm}";		
				myPagination(id, val, url);
				$("#tj_list").hide();
				$("#sq_list").show();
			}else{
				init();
				$("#sq_list").hide();
				$("#tj_list").show();
			}
// 			addData();
	}
	
// 	提交审批
	function submit(id) {
	var serviceCondition = $("#status"+id).val(); 
	if(serviceCondition==""){
		$.alert("请选择是否通过！");
		return;
	}
		$.post("${ctp}/oa/applycard/" + id, {"_method" : "put","auditStatus" : serviceCondition}, function(data, status) {
			if ("success" === status) {
			data = eval("(" + data + ")");
				if ("success" === data.info) {
					$.success("审批成功");
					window.location.reload();
				}else if("fail" === data.info){
					$.error("该车该时间段已被使用", 1);
				} else {
					$.error("审批失败", 1);
				}
			}
		});
	}
	
	//统计的js月份分割
	function getYear(){
		var curenData = new Date();
		var curenYear = curenData.getFullYear();
		var dateSelect = $("#sj");
		for(var i = 1;i <= 12;i++){
			var  day = new Date(curenYear,i,0); 
			var daycount = day.getDate();
			var datas = convertData(i);
			var n = curenData.getMonth()+1;
			if(n==i){
				var opt = "<option selected='selected' value='"+ curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +"'>" +
				"本月（" + 
				curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +
				"）</option>";
			}else{
				var opt = "<option value='"+ curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +"'>" +
				datas + "月（" + 
				curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +
				"）</option>";
			}
			dateSelect.append(opt);
		}
		 $(".chzn-select").chosen();
	}
	
	function convertData(num){
		var cdata = ["一","二","三","四","五","六","七","八","九","十","十一","十二"];
		return cdata[num-1];
	}
	
	//触发下拉框事件
	function dateChangeInit(){
		$("#sj").change("on",function(){
			var currentValue = $(this).val();
			var url = "${ctp}/oa/applycard/monthChange?curenMonth="+currentValue+"&auditType=wait&menu=sp&dm=${param.dm}";
			$.post(url,null,function(data){
				var bodyHtml = "";
				var tatol = "";
				var wait = "";
				var alerty = "";
				$.each(data,function(index,value){
					tatol = value.tatolAudit;
					wait = value.waitAudit;
					alerty = value.alertyAudit;
					index = index + 1;
					bodyHtml = bodyHtml + "<tr>";
					bodyHtml = bodyHtml + "<td>" + index + "</td>";
					bodyHtml = bodyHtml + "<td>" + value.proposerName + "</td>";
					bodyHtml = bodyHtml + "<td>" + value.depetmrnt + "</td>";
					bodyHtml = bodyHtml + "<td>" + value.phone + "</td>";
					bodyHtml = bodyHtml + "<td>" + value.cardName + "【" +value.plateNumber + "】" + "</td>";
					bodyHtml = bodyHtml + "<td>" + value.title + "</td>";
					if(value.auditStatus==1){
						bodyHtml = bodyHtml + "<td>已处理</td>";
					}else{
						bodyHtml = bodyHtml + "<td>待处理</td>";
					}
					
					if(value.releaseDate==null){
						bodyHtml = bodyHtml + "<td></td>";
					}else{
						var date =  new Date(value.releaseDate);
						var m = 1;
						m = m + date.getMonth();
						var d = date.getFullYear() + "年" + m + "月" + date.getDate() + "日";
						bodyHtml = bodyHtml + "<td>" + d + "</td>";
					}
					bodyHtml = bodyHtml + "</tr>";
				});
				$("#tatol").html("").append(tatol);
				$("#waitAudit").html("").append(wait);
				$("#alerty").html("").append(alerty);
				if(tatol==""){
					$("#tatol").html("").append("0");
				}
				if(wait==""){
					$("#waitAudit").html("").append("0");
				}
				if(alerty==""){
					$("#alerty").html("").append("0");
				}
				$("#tbodys").html("").append(bodyHtml);
			},'json')
		});
	}
	
	function init(){
		var currentValue = $("#sj").val();
		var url = "${ctp}/oa/applycard/monthChange?curenMonth="+currentValue+"&auditType=wait&menu=sp&dm=${param.dm}";
		$.post(url,null,function(data){
			var bodyHtml = "";
			var tatol = "";
			var wait = "";
			var alerty = "";
			$.each(data,function(index,value){
				tatol = value.tatolAudit;
				wait = value.waitAudit;
				alerty = value.alertyAudit;
				index = index + 1;
				bodyHtml = bodyHtml + "<tr>";
				bodyHtml = bodyHtml + "<td>" + index + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.proposerName + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.depetmrnt + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.phone + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.cardName + "【" +value.plateNumber + "】" + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.title + "</td>";
				if(value.auditStatus==1 || value.auditStatus==4){
					bodyHtml = bodyHtml + "<td>已处理</td>";
				}else{
					bodyHtml = bodyHtml + "<td>待处理</td>";
				}
				
				if(value.releaseDate==null){
					bodyHtml = bodyHtml + "<td></td>";
				}else{
					var date =  new Date(value.releaseDate);
					var m = 1;
					m = m + date.getMonth();
					var d = date.getFullYear() + "年" + m + "月" + date.getDate() + "日";
					bodyHtml = bodyHtml + "<td>" + d + "</td>";
				}
				bodyHtml = bodyHtml + "</tr>";
			});
			$("#tatol").html("").append(tatol);
			$("#waitAudit").html("").append(wait);
			$("#alerty").html("").append(alerty);
			if(tatol==""){
				$("#tatol").html("").append("0");
			}
			if(wait==""){
				$("#waitAudit").html("").append("0");
			}
			if(alerty==""){
				$("#alerty").html("").append("0");
			}
			$("#tbodys").html("").append(bodyHtml);
		},'json')
	}
	
	//需要查询待审批 和 已审批 下的部门的人  可以在路径中加上当前点击的部门是待审批 还是已审批的 的相关参数就可以了
	function depentmentSeach(departmentId,obj){
		if(departmentId==='all'){
			departmentId='';
		}
		var auditType = $("#spStatus").val();
		var val = {
			"ssword" : $("#ss").val()
		}
		var id = "wait";
		var url = "/oa/applycard/index?sub=list&auditType=" + auditType + "&departmentId="+departmentId+"&menu=sp&dm=${param.dm}";
		$.ajaxSetup({
			   async: false
		});
		myPagination(id, val, url);
// 		addData();
		$(".entry_li").removeClass("on");
		var i=$(obj).parent().index()/2;
		$(".entry_li").eq(i).addClass("on");
	}
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="车辆" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<input type="hidden" value="wait" id="spStatus"/>
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on" onclick="getData('wait');">待审批</a></li>
				            <li><a href="javascript:void(0)" onclick="getData('already');">已审批</a></li>
				            <li><a href="javascript:void(0)" onclick="getData('count');">用车统计</a></li>
				        </ul>
					</div>
					<div class="sq_list" id="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号" id="ss">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>

						<div class="clsq" id="wait" style="margin-bottom:15px;">
							<jsp:include page="./waitlist.jsp" />
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="wait" />
							<jsp:param name="url" value="/oa/applycard/index?sub=list&auditType=wait&menu=sp&dm=${param.dm}"/>
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
						<div class="clear"></div>
					</div>
					
					<!-- 统计 -->
					<div class="sq_list" id="tj_list" style="display: none;">
						<div class="tj_1">
							<div class="time">
								<span>时间范围：</span>
								<select id="sj" class="chzn-select">
								</select>
							</div>
<!-- 							<div class="people"> -->
<!-- 								<p class="p1">车辆负责人</p> -->
<%-- 								<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!-- 								<div class="name"> -->
<!-- 									<p><b>刘艳青</b></p> -->
<!-- 									<p>后勤部</p> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
						<div class="tj_2">
							<p><span>申请次数：</span><b class="b1" id="tatol">0</b></p>
							<p><span>待处理：</span><b class="b2" id="waitAudit">0</b></p>
							<p><span>已处理：</span><b class="b3" id="alerty">0</b></p>
						</div>
						<div class="tj_3">
							<p class="top">申请明细</p>
							<table class="responsive table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>申请人</th>
										<th>部门</th>
										<th>联系电话</th>
										<th>申请车辆</th>
										<th>申请事由</th>
										<th>处理状态</th>
										<th>处理时间</th>
									</tr>
								</thead>
								<tbody id="tbodys">
								</tbody>
							</table>
						</div>
					</div>
<!-- 					统计页面结束 -->
					
				</div>
			</div>
		</div>
	</div>
	<div class="zhezhao" style="display:none"></div>
</body>
</html>