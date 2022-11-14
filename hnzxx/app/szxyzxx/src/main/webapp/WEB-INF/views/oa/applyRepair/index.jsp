<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
	<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
	<title>报修</title>
	<style>

		.find {
			display: flex;
			padding: 20px 0;
			width: 96%;
			min-width: 1300px;
			margin: 20px 2%;
			background-color: #f8f8f8;
		}


		input {
			height: 24px;
		}

		#find,
		#group,
		#export {
			margin-left: 10px;
			border: 1px solid #0d7bd5;
			margin-right: 5px;
			font-size: 14px;
			padding: 0 25px;
			border-radius: 3px;
			background-color: #0d7bd5;
			color: white;
		}

		#find:hover,
		#export:hover,
		#group:hover {
			box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
		}

		.input {
			height: 30px;
			width: 120px;
			border: 1px solid #d9d9d9;
		}

		.title {
			text-align: center;
			font-size: 20px;
		}

		#week {
			margin: 5px;
			text-align: center;
			width: 20px;
		}

		table {
			width: 100%;
			border-collapse: collapse;
			box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
		}

		th,
		td {
			padding: 10px 20px;
			width: 9%;
			height: 40px;
			text-align: center;
			font-size: 20px;
			border: 1px solid #d9d9d9;
		}

		th {
			background-color: #66ccff40;
			color: #002244;
			font-size: 24px;
			position: relative;
		}

		td {
			position: relative;
			font-weight: bolder;
		}

		.dt {
			width: 15px;
			height: 15px;
			position: absolute;
			bottom: 3px;
			right: 3px;
			background-image: url('../../../images/删除.png');
			background-size: 15px;
			display: none;
			cursor: pointer;
			z-index: 99;
		}

		.sp {
			width: 15px;
			height: 15px;
			position: absolute;
			bottom: 3px;
			left: 3px;
			background-image: url('../../../images/管理人员.png');
			background-size: 15px;
			display: none;
			cursor: pointer;
			z-index: 99;
		}

		td:hover .dt,
		td:hover .sp {
			display: block;
		}



		th:hover{
			display: block;
		}

		.groupList,.groupLists {
			width: 800px;
			height: 500px;
			border: 1px solid #002244;
			background-color: white;
			z-index: 999;
			position: fixed;
			top: 0px;
			margin: 200px 30%;
		}

		.groupListTitle {
			background-color: #0d7bd5;
			height: 40px;
		}

		.workListTitle div,
		.groupListTitle div {
			display: inline;
			float: left;
			height: 30px;
			margin: 8px 20px 0px;
			color: white;
			font-weight: bolder;
		}

		.workListTitle .off,
		.groupListTitle .off {
			float: right;
			cursor: pointer;
		}
		.groupList::-webkit-scrollbar{
			width: 0;
			height: 0;
		}
		.groupList{
			overflow: auto;
		}
		.addGroup,.addGroups {
			float: right;
			margin: 5px 20px;
			background-color: #fa7000;
			border: 1px solid #fa7000;
			border-radius: 5px;
			box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.24);
			color: white;
			font-weight: bolder;
		}

		.gList,.gLists {
			width: 760px;
			margin: 20px;
		}

		.gList th,.gLists th,.gLists td,
		.gList td {
			padding: 0px;
			font-size: 14px;
			height: 35px;
			font-weight: normal;
		}

		.edit {
			width: 50px;
			height: 25px;
			background-color: #0d7bd5;
			border: 1px solid #0d7bd5;
			border-radius: 3px;
			color: white;
			margin: 0 5px;
		}

		.del {
			width: 50px;
			height: 25px;
			background-color: #ff3939;
			border: 1px solid #ff3939;
			border-radius: 3px;
			color: white;
			margin: 0 5px;
		}

		.save {
			background-color: #fa7000;
			border: 1px solid #fa7000;
			border-radius: 3px;
			color: white;
			width: 50px;
			height: 25px;
		}

		 .nums,.gLists,
		.gList .num {
			width: 60px;
		}

		 .workContent {
			width: 425px;
		}

		 .source,.sources,
		 .gLists .source,
		.gList .source {
			width: 125px;
		}
		.handles,
		.gLists .handles,
		 .handle,
		.gList .handle {
			width: 150px;
		}

		.workContent textarea,
		.gList textarea {
			margin-top: 5px;
			width: 90%;
			height: 25px;
			outline: none;
			resize: none;
			border: none;
			background: transparent;
		}
		.gLists textarea {
			margin-top: 5px;
			width: 90%;
			height: 25px;
			outline: none;
			resize: none;
			border: none;
			background: transparent;
		}

		.workContent textarea::-webkit-scrollbar,
		.gList textarea::-webkit-scrollbar {
			width: 0;
			height: 0;
		}

		.gLists textarea::-webkit-scrollbar {
			width: 0;
			height: 0;
		}

		.delTeacher,
		.setPrincipal {
			padding: 10px 10px;
			width: 350px;
			height: 150px;
			background-color: white;
			border: 1px solid #d9d9d9;
			box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.24);
			text-align: center;
			z-index: 999;
			position: fixed;
			top: 0px;
			margin: 400px 40%;
		}

		.delTeacher p,
		.setPrincipal p {
			margin: 35px 40px;
		}

		.delTeacher .dt_no,
		.setPrincipal .sp_yes {
			background-color: #0d7bd5;
			border: 1px solid #0d7bd5;
			border-radius: 3px;
			color: white;
			margin: 20px;
		}

		.delTeacher .dt_yes {
			background-color: #d9d9d9;
			border: 1px solid #d9d9d9;
			border-radius: 3px;
			color: #444444;
			margin: 20px;
		}
		.refresh {
			float: right;
			margin: 5px;
			background-color: #008000;
			border: 1px solid #008000;
			border-radius: 5px;
			box-shadow: 0 2px 3px 0 #00000024;
			color: white;
			font-weight: bolder;
		}
	</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="报修" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
		<div class="span12 white">
			<div class="content-widgets">
				<div class="widget-head">
						<h3 class="x-head content-top">
						<a id="my" href="javascript:void(0);" class="on" onclick="showOwn();">我申请的</a>
						<a id="all" href="javascript:void(0);" onclick="showAll();">全部报修</a>
						<a id="tjs" href="javascript:void(0);" onclick="showTj()">报修统计</a></h3>
						<input type="hidden" value="" id="searchType"/>
				</div>
				<div id="contan">
				<div class="widget-container">
				  <div class="clearfix list-search-bar x-search">
					<div class="select_b">
				  	<div class="select_div">
						<span style="position:relative;display:block;float:left;"></span>
							<input type="text" size="16" placeholder="标题/发布人" class="input-medium" id="searchWord">
					</div>

					<div class="select_div">
						<span>类别:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
							<select name="typeName" id="typeName">
								<option value="">请选择</option>
								<c:forEach items="${typeList}" var="type" >
									<option value="${type.id}">${type.name}</option>
								</c:forEach>
						</select>
					</div>
						<div class="select_div">
							<span>申请时间：</span>
							<input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off"
								   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" placeholder="开始时间"
								   >-
							<input type="text" id="endTime" name="endTime" class="chzn-select" autocomplete="off"
								   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'kaishiTime\')}'})" placeholder="结束时间"
								   >
						</div>

<%--					  <button type="button" class="btn btn-success right" onclick="search();" style="color: #0d8d59; border-radius: 25px; border: 2px solid #8AC007;">查询</button></h4>--%>

					  <button type="button" class="btn" onclick="search();"><i class="fa fa-search"></i></button>
						<button id="export" onclick="daochu();">导出</button>
                    </div>

                  </div>
				</div>
				<div class="x-main">
					<button class="btn btn-success right" type="button" id="sqbutton" onclick="settingleixing();">设置维修类型</button></h4>
					<button class="btn btn-success right" type="button" id="sqbutton" onclick="loadCreatePage();">申请报修</button></h4>
					<div id="applyrepair_page">
					<jsp:include page="./list.jsp" />
					</div>
					<!--根据需求分页放在list页面 -->
					<%-- <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
						<jsp:param name="id" value="applyrepair_page" />
						<jsp:param name="url" value="/oa/applyrepair/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include> --%>
				</div>
				</div>
				</div>
				
				<div id="tj" style="display: none;">
				<div class="widget-container clearfix x-tongji">
					<form class="form-horizontal span7 left" style="padding:0">
						<div>
							<label class="control-label">时间范围:</label>
							<div class="date">
									<select id="sj">
									</select>
							</div>
						</div>
					</form>
				</div>
				<div id="countDatas">
				
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
	function daochu() {
		var url = "/oa/applyrepair/finddaochuWeixiu?qwer=asd"
		var name=$("#searchWord").val();
		var typeName=$("#typeName").val();
		var startTime=$("#kaishiTime").val();
		var endTime=$("#endTime").val()
			if(name!=null && name!=""){
				url+="&weixiuren="+name;
			}
			if(typeName!=null && typeName!=""){
				url+="&typeId="+typeName;
			}
		if(startTime!=null && startTime!=""){
			url+="&startTime="+startTime;
		}
		if(endTime!=null && endTime!=""){
			var data=new Date(endTime);
			var year= data.getFullYear();
			var mounth=data.getMonth()+1;
			var day=data.getDate()+1;
			var time=year+"-"+mounth+"-"+day;
			url+="&endTime="+time;
		}
		window.open(url);

	}
	//级组设置刷新
	function shuaxinqwe(){
		offGroupSet2();
		settingleixing();
	}
	function offGroupSet2() {
		document.getElementsByClassName('groupList')[0].remove();
	}
	// 关闭维修弹窗
	function offGroupSet() {
		document.getElementsByClassName('groupList')[0].remove();
	}
	// 打开故障类型设置
	function settingleixing() {

		if (document.getElementsByClassName('groupList')[0]) {
			document.getElementsByClassName('groupList')[0].remove();
		}

		var body = document.getElementsByTagName('body')[0];

		var gList = document.createElement('div');
		gList.setAttribute('class', 'groupList');
		body.appendChild(gList);

		var groupList = document.getElementsByClassName('groupList')[0];
			//头部
		var glt = document.createElement('div');
		glt.setAttribute('class', 'groupListTitle');
		groupList.appendChild(glt);

		document.getElementsByClassName('groupListTitle')[0].innerHTML = '<div>级组设置</div><div class="off" onclick="offGroupSet();">X</div>'
		//按钮
		var ag = document.createElement('input');
		ag.setAttribute('type', 'button');
		ag.setAttribute('class', 'addGroup');
		ag.setAttribute('value', '添加维修类型');
		ag.setAttribute('onclick', 'addGroup();'); // 添加级维修类型方法
		groupList.appendChild(ag);

		var gl = document.createElement('table');
		gl.setAttribute('class', 'gList');
		groupList.appendChild(gl);

		var grList = document.getElementsByClassName('gList')[0];
		var tr = document.createElement('tr');
		grList.appendChild(tr);
		grList.getElementsByTagName('tr')[0].innerHTML = '<th class="num">序号</th><th class="source">维修名称</th><th class="handle">操作</th>';

		$.get("/oa/applyrepair/typeAll",function (d) {
			var list=JSON.parse(d);
			for (var n = 0; n < list.length; n++) {

				var glTr = document.createElement('tr');
				grList.appendChild(glTr);

				var num = n + 1;
				var gtr = grList.getElementsByTagName('tr')[num];

				var tn = document.createElement('td');
				tn.setAttribute('class', 'num');
				gtr.appendChild(tn);
				var tb = document.createElement('td');
				tb.setAttribute('class', 'source');
				tb.setAttribute('id','td_'+n);
				gtr.appendChild(tb);
				var th = document.createElement('td');
				th.setAttribute('class', 'handle');
				gtr.appendChild(th);

				gtr.getElementsByClassName('num')[0].innerHTML = num;
				//gtr.getElementsByClassName('source')[0].innerHTML = list[n].jizuName;
				var tds = $('#td_'+n);
				var str="<textarea  disabled>"+list[n].name+"</textarea>";

				tds.append(str);
				/* var textarea=document.createElement('textarea');
                 textarea.setAttribute('id','text_'+n);
                 textarea.setAttribute('disabled','disabled');*/



				var del = document.createElement('input');
				del.setAttribute('type', 'button');
				del.setAttribute('class', 'del');
				del.setAttribute('value', '删除');
				del.setAttribute('onclick', 'deletes('+list[n].id+')');//删除方法
				var set = document.createElement('input');
				set.setAttribute('type', 'button');
				set.setAttribute('class', 'edit');
				set.setAttribute('value', '设置维修工');
				set.setAttribute('onclick', 'shezhi('+list[n].id+')');//删除方法
				grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(set);
				grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
			}
		})
	}
	// 添加故障类型
	function addGroup() {

		/* if (document.getElementsByClassName('gList')[0].getElementsByTagName('textarea')[0] == undefined) {*/
		var grList = document.getElementsByClassName('gList')[0];

		var trNum = grList.getElementsByTagName('tr').length;

		var atr = document.createElement('tr');
		grList.appendChild(atr);

		var aTr = grList.getElementsByTagName('tr')[trNum];


		var atn = document.createElement('td');
		atn.setAttribute('class', 'num');
		aTr.appendChild(atn);
		var atb = document.createElement('td');
		atb.setAttribute('class', 'source');
		aTr.appendChild(atb);
		var ath = document.createElement('td');
		ath.setAttribute('class', 'handle');
		aTr.appendChild(ath);

		aTr.getElementsByClassName('num')[0].innerHTML = trNum;
		aTr.getElementsByClassName('source')[0].innerHTML = '<textarea id="tontent"></textarea>';
		aTr.getElementsByClassName('handle')[0].innerHTML = '<input type="button" class="save" onclick="jizuAdd()" value="确定">';

	}
	/*故障类型添加*/
	function jizuAdd() {
		var tontent=$("#tontent").val();
		var val={};
		val.name=tontent;
		$.post('/oa/applyrepair/createWeiType',val,function (d) {
			if(d=="success"){
				$.success("添加成功");
				settingleixing();
			}else{
				$.error("添加失败,请联系管理员");
			}
		})
	}
	//删除维修类型
	function deletes(id) {
		$.confirm("确定执行删除操作？", function () {
			queding(id);
		});
	}
	function queding(id) {
		$.get('/oa/applyrepair/deleteWeiType?id='+id,function (d) {
			if(d=="success"){
				$.success("删除成功")
				shuaxinqwe();
			}else{
				$.error("删除失败")

			}       })
	}
	//设置维修工
	function shezhi(id){
		offGroupSet();
		if (document.getElementsByClassName('groupList')[0]) {
			document.getElementsByClassName('groupList')[0].remove();
		}

		var body = document.getElementsByTagName('body')[0];

		var gList = document.createElement('div');
		gList.setAttribute('class', 'groupList');
		body.appendChild(gList);

		var groupList = document.getElementsByClassName('groupList')[0];

		var glt = document.createElement('div');
		glt.setAttribute('class', 'groupListTitle');
		groupList.appendChild(glt);

		document.getElementsByClassName('groupListTitle')[0].innerHTML = '<div>维修工设置</div><div class="off" onclick="offGroupSets();">X</div>'

		var ag = document.createElement('input');
		ag.setAttribute('type', 'button');
		ag.setAttribute('class', 'addGroup');
		ag.setAttribute('value', '添加维修人员');
		ag.setAttribute('onclick', "addWeiXiu('"+id+"');"); // 添加级维修人方法
		groupList.appendChild(ag);
		var refresh = document.createElement('input');
		refresh.setAttribute('type','button');
		refresh.setAttribute('class','refresh');
		refresh.setAttribute('value','刷新列表');
		refresh.setAttribute('onclick','shuaxings("'+id+'")'); // 刷新列表方法
		groupList.appendChild(refresh);

		var gl = document.createElement('table');
		gl.setAttribute('class', 'gList');
		groupList.appendChild(gl);

		var grList = document.getElementsByClassName('gList')[0];
		var tr = document.createElement('tr');
		grList.appendChild(tr);
		grList.getElementsByTagName('tr')[0].innerHTML = '<th class="num">序号</th><th class="source">维修人员</th><th class="handle">操作</th>';

		// console.log(getGroupList())
		$.get("/oa/applyrepair/findByWeiXiuGongAll?atId="+id,function (d) {
			var list=JSON.parse(d);
			for (var n = 0; n < list.length; n++) {

				var glTr = document.createElement('tr');
				grList.appendChild(glTr);

				var num = n + 1;
				var gtr = grList.getElementsByTagName('tr')[num];

				var tn = document.createElement('td');
				tn.setAttribute('class', 'num');
				gtr.appendChild(tn);
				var tb = document.createElement('td');
				tb.setAttribute('class', 'source');
				tb.setAttribute('id','td_'+n);
				gtr.appendChild(tb);
				var th = document.createElement('td');
				th.setAttribute('class', 'handle');
				gtr.appendChild(th);

				gtr.getElementsByClassName('num')[0].innerHTML = num;
				//gtr.getElementsByClassName('source')[0].innerHTML = list[n].jizuName;
				var tds = $('#td_'+n);
				var str="<textarea  disabled>"+list[n].teacherName+"</textarea>";

				tds.append(str);


				var del = document.createElement('input');
				del.setAttribute('type', 'button');
				del.setAttribute('class', 'del');
				del.setAttribute('value', '删除');
				del.setAttribute('onclick', 'deleteweixiugong("'+list[n].id+'")');//删除方法
				grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
			}
		})
	}
	var asd="";
	//关闭添加维修工堂框
	function offGroupSets() {
		document.getElementsByClassName('groupList')[0].remove();
		settingleixing();
	}
	function shuaxings(id){
		shezhi(id)
	}
	//不提供调用，请勿调用（绿色title）
	initWindowBase = function (onWhere, title, url, width, height, topVal, shift) {
		if ("top" === onWhere) {
			return window.top.layer.open({
				id:'wer',
				skin: 'layui-layer-lvse', //样式类名
				type: 2,
				title: title,
				//closeBtn: false, //显示关闭按钮
				shadeClose: true,
				shade: 0.8,
				area: [width + 'px', height + 'px'],
				/*  offset:[ topVal + 'px', '' ], */
				maxmin: false, //开启最大化最小化按钮
				shift: shift,
				content: url //iframe的url，no代表不显示滚动条
				// time: 2000, //2秒后自动关闭
			});
		} else if ('cur' === onWhere) {
			return layer.open({
				/* extend: ['skin/myskin/style.css'], //加载您的扩展样式
                skin: 'layer-ext-yourskin', //一旦设定，所有弹层风格都采用此主题。 */
				skin: 'layui-layer-lvse', //样式类名
				type: 2,
				title: title,
				//closeBtn: false, //显示关闭按钮
				shadeClose: true,
				shade: 0.8,
				area: [width + 'px', height + 'px'],
				/*  offset:[ top + 'px', '' ], */
				maxmin: false, //开启最大化最小化按钮
				shift: shift,
				content: url //iframe的url，no代表不显示滚动条
			});
		}
	}

	//在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
	initWinOnTopFromLeft = function (title, url, width, height, top) {
		if (width === undefined) {
			width = $(parent.window).width() - 50;
		}
		if (height == undefined) {
			height = $(parent.window).height() - 50;
		}
		if (top == undefined) {
			/* top = '20'; */
		}
		return initWindowBase('top', title, url, width, height, top, 'left');
	}

	// 添加故维修工
	function addWeiXiu(id) {
		// 教师选择框
		initWinOnTopFromLeft('添加教师', '/oa/applyrepair/weixiugongLie?sub=asd&id='+id, '1200', '650');
	}
	/*维修工添加*/
	function jizuAdds() {
		var tontent=$("#tontent").val();
		var val={};
		val.teacherId=tontent;
		val.atId=asd;
		$.post('/oa/applyrepair/createWeiXiuGong',val,function (d) {
			if(d=="success"){
				$.success("添加成功");
				shezhi(asd);
			}else{
				$.error("添加失败,请联系管理员");
			}
		})
	}
	//删除维修工
	function deleteweixiugong(id){
		$.confirm("确定执行删除操作？", function () {
			quedings(id);
		});
	}
	function quedings(id) {
		$.get('/oa/applyrepair/updateWeiXiuGongDelete?id='+id,function (d) {
			if(d=="success"){
				$.success("删除成功")
				shezhi(asd);
			}else{
				$.error("删除失败")

			}       })
	}
</script>
<script type="text/javascript">
	$(function(){
		// 获取类别
		//$.jcGcSelector("#typeName", {"tc" : "GB-BXLX"});

		getYear();
		ChangeTimeCount();
		showOwn();
	})
	
	function search() {
		var searchWord= $("#searchWord").val();
		var typeId=$("#typeName option:selected").val();
		var startTime=$("#kaishiTime").val();
		var endTime=$("#endTime").val();
		var val = {
			/*"searchWord" : $("#searchWord").val(),
			"typeId" : $("#typeName option:selected").val(),
			"startTime":$("#kaishiTime").val(),
			"endTime": $("#endTime").val(),*/
		};

		if(searchWord!=null && searchWord!=""){
			val.searchWord=searchWord;
		}
		if(typeId!=null && typeId!=""){
			val.typeId=typeId;
		}
		if(startTime!=null && startTime!=""){
			val.startTime=startTime;
		}
		if(endTime!=null && endTime!=""){
			var data=new Date(endTime);
			var year= data.getFullYear();
			var mounth=data.getMonth()+1;
			var day=data.getDate()+1;
			val.endTime=year+"-"+mounth+"-"+day;
		}
		// var typeId = $("typeId option:selected").val();
		var own = $("#searchType").val();
		// var typeId = typeId;
		var id = "applyrepair_page";
		var url = "/oa/applyrepair/index?sub=list&dm=${param.dm}&own="+own;
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		window.location.href="${ctp}/oa/applyrepair/creator";
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		window.location.href = "${ctp}/oa/applyrepair/editor?id=" + id;
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/oa/applyrepair/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function del(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}

	// 	执行删除
	function executeDel(id) {
		$.post("${ctp}/oa/applyRepair/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#apply_" + id).remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	//申请
	function sqRepari(id){
		$.ajax({
			type : "post",
			url : "${ctp}/oa/applyrepair/myApply?applyId="+id,
			dataType : "html", 
			success : function(data) {
				$(".sq").empty();
				$(".wx").empty();
				$(".pj").empty();
				$("#wx_div"+id).append(data);
			}
		});
	}
	
	//维修
	function wxRepari(id){
		$.ajax({
			type : "post",
			url : "${ctp}/oa/acceptrepari/ownRepair?applyId="+id+"&isCk=" + id,
			dataType : "html", 
			success : function(data) {
				$(".sq").empty();
				$(".wx").empty();
				$(".pj").empty();
				$("#wx_div"+id).append(data);
			}
		});
	}
	
	//评价
	function pjRepari(id){
		var isCK = "isCK";
		$.ajax({
			type : "post",
			url : "${ctp}/oa/applyrepair/appraise?applyId="+id+"&isCK=" + isCK,
			dataType : "html", 
			success : function(data) {
				$(".sq").empty();
				$(".wx").empty();
				$(".pj").empty();
				$("#pj_div"+id).append(data);
			}
		});
	}
	
	//统计
	function showTj(){
		var currentValue = $("#sj").val();
		//判断有时时间没有填充上去的时候，重新填充数据，并获取第一个时间作为当前时间
		if(currentValue == null){
			var curenData = new Date();
			var curenYear = curenData.getFullYear();
			var dateSelect = $("#sj");
			dateSelect.html("");
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
			 $("#sj").chosen();
			 currentValue = $("#sj").val();
		}
		
		//发送请求
		$.ajax({
			type : "post",
			url : "${ctp}/oa/applyrepair/applyCount?selectMonth="+currentValue,
			dataType : "html", 
			success : function(data) {
				$("#countDatas").empty();
				$("#countDatas").append(data);
			}
		});
		$("#contan").hide();
		$("#tj").show();
	}
	
	//全部维修
	function showAll(){
		$("#sqbutton").hide();
		$("#contan").show();
		$("#tj").hide();
		$("#searchType").val("");
		var val = {};
		var id = "applyrepair_page";
		var url = "/oa/applyrepair/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	function showOwn(){
		$("#sqbutton").show();
		$("#contan").show();
		$("#tj").hide();
		$("#searchType").val("own");
		var val = {};
		var id = "applyrepair_page";
		var url = "/oa/applyrepair/index?sub=list&dm=${param.dm}&own=own";
		myPagination(id, val, url);
	}
	
	function ChangeTimeCount(){
		$("#sj").change("on",function(){
			var currentValue = $(this).val();
			$.ajax({
				type : "post",
				url : "${ctp}/oa/applyrepair/applyCount?sub=list&selectMonth="+currentValue,
				dataType : "html",
				success : function(data) {
					$("#countDatas").empty();
					$("#countDatas").append(data);
				}
			});
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
		 $("#sj").chosen();
	}
	
	function convertData(num){
		var cdata = ["一","二","三","四","五","六","七","八","九","十","十一","十二"];
		return cdata[num-1];
	}
</script>
</html>