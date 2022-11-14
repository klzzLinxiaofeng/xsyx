<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>年级升级</title>
<style type="text/css">
.select_b {
	background-color: #e9e9e9;
}
#matters p {
    margin: 15px 30px;
    font-size: 16px;
    font-family: 微软雅黑;
    line-height: 1.5;
}
hr {
	margin: 10px 30px;
    border: 0;
    border-top: 1px solid #9e9e9e;
    border-bottom: 1px solid #9e9e9e;
    width: 95%;
}
.choice_area ul li {
    float: left;
    margin-right: 30px;
    font-size: 14px;
    line-height:30px;
    margin-bottom:10px;
}
input[type="checkbox"] {
	width:16px;
	height:16px;
	position:relative;
	top:-2px;
	margin-right:10px;
}
.dtitle {
	margin-top: 10px;
    padding: 8px 0;
    width: 130px;
    background-color: #FFC125;
    text-align: center;
}
.stitle {
	font-size: 20px;
    font-family: 黑体;
}
#btn_1, #btn_2 {
	width: 130px;
    height: 50px;
    font-size: 20px;
    font-family: 黑体;
}
.btn_c {
    border-color: #999999;
    background-color: #fafafa;
}
#year, #grade {
	width:105px;
	background-color: #fff;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="年级升级" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							年级升级
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)"  class="a3" onclick="history.go(-1);"><i class="fa fa-reply"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div id="matters">
									<p>
									1. 升级只对年机制的行政班级有效，教学班级和年届制班级无效。<br/>
									2. 升级前请先创建升级后的学年。<br/>
									3. 升级后班级自动创建，如果班级已经存在则中断此班级的升级。<br/>
									4. 升级后原班级的学生和班主任保留，任课教师需要重新指定。如学生需打乱重新分班，请走分班流程。<br/>
									5. 升级过程不可逆，请谨慎操作。
									</p>
								</div>
								<div class="clear"></div>
							</div>
							
							<div class="dtitle">
								<span class="stitle">升级前</span>
							</div>
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select> 
								</div>
								<div class="select_div">
									<span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;" onchange="getTeams();"></select> 
								</div>
								<div class="clear"></div>
								<hr/>
								<div style="margin: 0px 32px;">
									<div class="choice_area" style="font-size: 14px;">
										<span><b>选择待升级班级 : </b></span><input class="all_select" type="checkbox" style="margin-bottom:0" />全选
										<ul style="margin: 10px 0px;" id="teams">
										
										</ul>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							
							<div class="dtitle" id="dtitle2">
								<span class="stitle">升级后</span>
							</div>
							<div class="select_b" id="sel_div2">
								<div style="margin: 5px 0;padding: 0 30px;">
									<p>
										<span>学年：</span><input id="year" type="text" readonly="readonly"/>
										<button onclick="toCraeteYear();" class="btn btn_c" type="button" style="margin-top: -10px;">创建学年</button>
									</p>
									<p>
										<span>年级：</span><input id="grade" type="text" readonly="readonly"/>
										<button onclick="toCreateGrade();" class="btn btn_c" type="button" style="margin-top: -10px;">创建年级</button>
									</p>
								</div>
								
								<div class="clear"></div>
							</div>
							<div style="text-align: center;margin-top: 40px;">
								<p>
									<button onclick="upgrade();" id="btn_1" class="btn btn_c" type="button" >升级</button>
									<button onclick="graduate();" id="btn_2" class="btn btn_c" type="button" >毕业</button>
								</p>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){	 
		//学年--年级级联下拉
		$.initCascadeSelector({
			"type" : "grade",
			"selectOne" : true,
			"gradeCallback" : function($this){
				getTeams();
			}
		});
		
		//全选
		$(".all_select").click(function(){
			if($(this ).is(':checked')){
				$(this).parent().find("li").children().prop("checked" , "checked");
			}else{
				$(this).parent().find("li").children().removeAttr("checked");
			}
		});
	});
	
	
	//年级改变时，显示升级后的学年、年级 或 显示为毕业
	function getTeams(){
		//“全选” 去除选中
		$(".all_select").removeAttr("checked");
		//清空原班级列表
		$("#teams").empty();
		
		var gradeId = $("#nj").val();
		if(gradeId == ""){
			return;
		}
		$("#year").val("");
		$("#grade").val("");
		$("#year").attr("data-id", ""); 
		$("#grade").attr("data-id", "");
		var year = (parseInt($("#xn").val()) + 1).toString();
		var url = "${pageContext.request.contextPath}/updata/getNewYG";
		$.post(url, {"year":year, "gradeId":gradeId}, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					var map = data.responseData;
					if(map != null){
						//添加班级列表
						var teams = map.teamList;
						if(teams != undefined){
							for(var i=0; i<teams.length; i++){
								$("#teams").append("<li><input type='checkbox' value='" + teams[i].id + "'/>" + teams[i].name + "</li>")
							}
						}
						//按钮切换，"升级后"显示
						if(map.isGraduate){
							$("#dtitle2").hide();
							$("#sel_div2").hide();
							$("#btn_1").hide();
							$("#btn_2").show();
						}else{
							$("#dtitle2").show();
							$("#sel_div2").show();
							$("#btn_1").show();
							$("#btn_2").hide();
						}
						//升级后学年、班级赋值
						if(map.schoolYear != undefined){
							$("#year").val(map.schoolYear.name);
							$("#year").attr("data-id", map.schoolYear.year); 
						} 
						if(map.grade != undefined){
							$("#grade").val(map.grade.name);
							$("#grade").attr("data-id", map.grade.id);
						}
					}
					
				}
			}else{
				$.error("");
			}
		});
	}
	
	
	//自动创建年级  “升级后”的刷新
	function refresh(){
		$("#year").val("");
		$("#grade").val("");
		$("#year").attr("data-id", ""); 
		$("#grade").attr("data-id", "");
		var year = (parseInt($("#xn").val()) + 1).toString();
		var gradeId = $("#nj").val();
		var url = "${pageContext.request.contextPath}/updata/getNewYG";
		$.post(url, {"year":year, "gradeId":gradeId}, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					var map = data.responseData;
					if(map != null){
						if(map.schoolYear != undefined){
							$("#year").val(map.schoolYear.name);
							$("#year").attr("data-id", map.schoolYear.year); 
						} 
						if(map.grade != undefined){
							$("#grade").val(map.grade.name);
							$("#grade").attr("data-id", map.grade.id);
						}
					}
				}
			}
		});
	}
	
	//创建学年窗口
	function toCraeteYear(){
		$.initWinOnTopFromLeft('新增学年', '${pageContext.request.contextPath}/teach/schoolYear/creator', '600', '300');
	}
	
	//创建年级窗口
	function toCreateGrade(){
		$.initWinOnTopFromLeft('新增年级', '${pageContext.request.contextPath}/teach/grade/addGradePage', '600', '400');
	}
	
	//升级
	function upgrade(){
		var loader = new loadDialog();
		var year = $("#year").attr("data-id"); 
		if(year == ""){
			$.error("请先创建升级后的学年");
			return;
		}
		
		var teamIds = "";
		$(".choice_area ul li input").each(function(){
			if($(this).is(':checked')){
				teamIds += $(this).val() + ",";
			}
		})
		if(teamIds.length == 0){
			$.error("请选择待升级的班级");
			return;
		}
		
		var oldGradeId = $("#nj").val();
		var newGradeId = $("#grade").attr("data-id");
// 		console.log(teamIds);
// 		console.log("旧年级："+oldGradeId);
// 		console.log("新年级："+newGradeId);
		
		loader.show();
		var url = "${pageContext.request.contextPath}/updata/upgradeGrade";
		$.post(url, {"teamIds":teamIds, "oldGradeId":oldGradeId, "newGradeId":newGradeId}, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				//console.log(data);
				if("success" === data.info) {
					var skipTeam = data.pk;
					if(skipTeam != ""){
						$.success("升级完成，但跳过原" + skipTeam + "的升级（新班级已存在）");
					}else{
						$.success("升级完成");	
					}
					refresh();
				}
			}else{
				$.error("");
			}
			loader.close();
		}); 
		
	}

	//毕业
	function graduate(){
		var loader = new loadLayer();
		var teamIds = "";
		$(".choice_area ul li input").each(function(){
			if($(this).is(':checked')){
				teamIds += $(this).val() + ",";
			}
		})
		if(teamIds.length == 0){
			$.error("请选择毕业的班级");
			return;
		}
		var oldGradeId = $("#nj").val();
		
		loader.show();
		var url = "${pageContext.request.contextPath}/updata/graduateGrade";
		$.post(url, {"teamIds" : teamIds, "oldGradeId" : oldGradeId}, function(data, status){
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success("设置班级毕业完成");
				} else if("fail" == data.info) {
					$.error("非毕业班，无法进行毕业操作");
				}
			}else{
				$.error("");
			}
			loader.close();
		});
	}



</script>
</html>