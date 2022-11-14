<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
     contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>布置导学案</title>
    <%@ include file="/views/embedded/common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/qyjx/css/all.css">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/new_requirement.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
     <style>
     html{background-color:#e3e3e3}
    </style>
</head>
<body>
<div class="bzdxa bg">
<div class="bz">
    <div class="position bz-top">布置</div>
    
    <!-- 增加导学案标题 zhenxinghui 2018-11-10 -->
<!--     <div class="bzdxa_class"> -->
<!--     	<label><i>*</i>导学案标题：</label> -->
<%--     	<input value="${title }"> --%>
<!--     </div> -->
    
    <div class="bzdxa_class">
        <label><i>*</i>班级：</label>
        <div class="choose_class">
             <div class="selected_class">
                    <ul>
                      <li class="choose"><input type="radio" name="1" value="myTechClass" class="radio" checked="checked"/>我所任教的班级</li>
                      <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].userId, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
                           <li><input type="radio" value="allClass" name="1" "/>所有班级</li>
                      </c:if>
                    </ul>
             </div>
            <div class="choose_grade" style="position: relative;left: 10px;">
                 <div class="choose">
                     <c:forEach items="${myTeachList }" var="team" varStatus="status">
                          <c:if test="${status.first }">
                                <a href="javascript:void(0);" class="display_grade" gradeId="${team.gradeId }">${team.gradeName }</a>
                          </c:if>
                          <c:if test="${!status.first }">
                                <a href="javascript:void(0);" gradeId="${team.gradeId }">${team.gradeName }</a>
                          </c:if>
                     </c:forEach>
                     <div class="clear"></div>
                 </div>
                     <c:forEach items="${myTeachList }" var="grade" varStatus="status">
                          <div class="grade">
                               <div class="check" style="width:100%;"><i class="mcheck all_select"><input type="checkbox" />全选</i></div>
                               <c:forEach items="${grade.teamList}" var="team">
                                <div class="check"><i class="myTechClass team mcheck "><input type="checkbox" value="${team.teamId }"/>${team.teamName }</i></div>
                               </c:forEach>
                          </div>
                     </c:forEach>
                   <div class="clear"></div>      
            </div>
            <div class="choose_grade">
                 <div class="choose_subject">
                     <c:forEach items="${allList }" var="team" varStatus="status">
                          <c:if test="${status.first }">
                               <a href="javascript:void(0);" class="display_subject">${team.gradeName }</a>
                          </c:if>
                          <c:if test="${!status.first }">
                                <a href="javascript:void(0);">${team.gradeName }</a>
                          </c:if>
                     </c:forEach>
                     <div class="clear"></div>
                 </div>
                <c:forEach items="${allList }" var="grade" varStatus="status">
                     <div class="grade2">
                         <div class="check" style="width:100%;"><i class="mcheck all_select"><input type="checkbox" />全选</i></div>
                         <c:forEach items="${grade.teamList}" var="team">
                          <div class="check"><i class="allClass team mcheck "><input type="checkbox" value="${team.teamId }"/>${team.teamName }</i></div>
                         </c:forEach>
                     </div>
                </c:forEach>
               
              
            </div>
            <div class="clear"></div> 
        </div>
    </div>
    
    <!-- 新增附加单元 2018-11-10 zhenxinghui -->
    <div class="bzdxa_class">
			<div style="width: 105px;height: 30px;float: left;text-align: right;">
				<div class="wjl_ts" style="position: relative;display: inline-block;">
					<i class="gth"></i>
					<span class="bg_tis">
						<span>注：导学案布置后，小组总结的分组方案不能修改<br/>附加单元可以在发布后增加，但是增加动作不能撤销</span>
					</span>
				</div>
				附加单元：
			</div>
			<div class="" style="margin-left: 105px;">
				<table border="1" class="fujiadanyuan">

					<thead>
					<tr>
						<th style=" width: 15%;"><i class="ck"></i></th>
						<th>课件交流</th>
						<th>详情</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td style=" width: 15%;"><i class="ck"></i></td>
						<td>小组总结</td>
						<td>
							<select id="groupList">
								<option>默认分组</option>
							</select>
						</td>
					</tr>

					<tr>
						<td style=" width: 15%;"><i class="ck"></i></td>
						<td>师生讨论</td>
						<td>
							-
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<script type="text/javascript">
			$('body').on('click',".choose_grade .grade .check i",function(){
				if($(this).hasClass("acheck")){
					var teamId = $(this).children("input").val();
					if(teamId != "on"){
						var isexist = $("#groupList option[value="+teamId+"]").val();
						if(typeof(isexist) == "undefined"){
							var gradeId = $(".choose").children("a.display_grade").attr("gradeId");
							$.post("${pageContext.request.contextPath}/learningPlan/getGroupJson", {"gradeId":gradeId, "teamId":teamId}, function(data,status){
								var d = eval("("+data+")")
								if(d.length>0){
									$("#groupList").append("<option value="+teamId+">"+d+"</option>");
								}
							});
						}
					}else {
						//全选的时候需要去后端获取
						$(this).parent(".check").parent(".grade").find(".check").each(function(index){
							if(index != 0){
								var gradeId = $(".choose a.display_grade").attr("gradeid");
								var teamId = $(this).children("i").children("input").val();
								var isexist = $("#groupList option[value="+teamId+"]").val();
								if(typeof(isexist) == "undefined"){
									$.post("${pageContext.request.contextPath}/learningPlan/getGroupJson", {"gradeId":gradeId, "teamId":teamId}, function(data,status){
										var d = eval("("+data+")")
										if(d.length>0){
											$("#groupList").append("<option value="+teamId+">"+d+"</option>");
										}
									});
								}
							}
						});
					}
				}
			});
			</script>
		</div>
    <!-- 新增附加单元 2018-11-10 zhenxinghui -->
    
    <div class="clear"></div>
     <div class="bzdxa_class" >
             <label ><i>*</i>权限：</label>
             <div style="margin-left: 110px;">
            <table border="1" class="quanxian">
                <c:forEach items="${catelogs}" var="catelog">
                      <thead>
                            <tr>
                                 <th colspan="3">${catelog.title }</th>
                             </tr>
                      </thead>
                      <tbody>
                           <c:forEach items="${catelog.lpUnitList}" var="unit">
                                <tr>
                                     <td>${unit.title}</td>
                                     <td>${unit.description}</td>
                                     <td>
                                           <i class="unit mcheck"><input type="checkbox" value="${unit.id}"/>锁定</i>
                                     </td>
                                </tr>
                           </c:forEach>
                      </tbody>
                </c:forEach>
             </table>
             </div>
         </div>
       
        <div class="arrange_button">
            <button onclick="publishLearningPlan();" class="btn-blue">布置</button>
            <button onclick="returnView('${jumpfrom}');" class="btn-lightGray">取消</button>
<!--        <button onclick="javascript:history.go(-1);" class="btn-lightGray">取消</button> -->
        </div>
   
  
 
</div>
</div>
</body>
<script type="text/javascript">
$(function(){
     //顶层我任教的班级和所有班级  默认选择第一个
     allClassChoose=0;
     
    $(".grade").hide();//隐藏wenben
    $(".grade:eq(0)").show();//显示第一个wenben
    //点击不同年级切换
    $(".choose_grade .choose a").click(function(){
        $(".choose a").removeClass("display_grade");//移除样式
        $(this).addClass("display_grade");//添加样式
        var i=$(this).index();//获得下标
        $(".grade").hide();//隐藏wenben
        $(".grade:eq("+i+")").show();//显示第i个wenben
    });
    $(".choose_grade").hide();//隐藏wenben
    $(".choose_grade:eq(0)").show();//显示第一个wenben
    //点击不同不同班级切换总体
    $(".selected_class ul li").click(function(){
        $(this).siblings("li").removeClass("choose");
        $(this).addClass("choose");
        i=$(this).index();//获得下标
        allClassChoose=i;
        $(".choose_grade").hide();//隐藏wenben
        $(".choose_grade:eq("+i+")").show();//显示第i个wenben
        //$("input[type='checkbox']").attr("checked", false);
    });
    $(".grade2").hide();//隐藏wenben
    $(".grade2:eq(0)").show();//显示第一个wenben
//     点击不同年级
    $(".choose_subject a").click(function(){
        $(".choose_subject a").removeClass("display_subject");//移除样式
        $(this).addClass("display_subject");//添加样式
        $(".grade2 .mcheck").removeClass("acheck");
       
        var i=$(this).index();//获得下标
        $(".grade2").hide();//隐藏wenben
        $(".grade2:eq("+i+")").show();//显示第i个wenben
        //$(".grade2:eq("+i+")").find("input").prop("checked" , "checked");//切换过来全选
    });
   
    $(".mcheck").click(function(){
     if($(this).hasClass("acheck")){
            $(this).removeClass("acheck");
            $(this).addClass("mcheck");
        }else{
            $(this).addClass("acheck");
        }
    });
   
    $(".all_select").click(function(){
     if($(this).hasClass("acheck")){
          $(this).parent().siblings().children().addClass("acheck");
     }else{
          $(this).parent().siblings().children().removeClass("acheck");
     }
    });
});
function publishLearningPlan() {
     var teamList = new Array();
     if(allClassChoose=="0"){
           var teams = $(".myTechClass.team.acheck input[type='checkbox']");
     }else{
           var teams = $(".allClass.team.acheck input[type='checkbox']");
     }
     
     $(teams).each(function(index, element) {
           var value = $(element).val();
           if(value!=null && "" !=value && value!="on") {
                teamList.push(value);
           }
     })
     
     teamList=Array.from(new Set(teamList));
     
     if(teamList==null || teamList.length==0) {
           $.alert("你未选择班级，请选择班级")
           return false;
     }
     
     //获取unitType zhenxinghui 2018-11-12
     if($(".fujiadanyuan thead tr th i.ck").hasClass("onn")){
    	 unitType = [12,21];
     }else{
    	 unitType = [];
    		 if($(".fujiadanyuan tbody tr").eq(0).children("td").children("i.ck").hasClass("onn")){
    			 unitType.push(12)
    		 }
    		 if($(".fujiadanyuan tbody tr").eq(1).children("td").children("i.ck").hasClass("onn")){
    			 unitType.push(21)
    		 }
     }
//      console.log(unitType)
		var l = JSON.stringify(unitType);
     
     $.ajax({
         url: "${pageContext.request.contextPath}/learningPlan/task/team/exit",
         type: "POST",
         data: {"teamIds":JSON.stringify(teamList),"lpId":"${lpId}","unitType":l},
         async: true,
         success: function(data) {
          var info = JSON.parse(data);
          if(info.size>0) {
                $.confirm("当前导学案已在 "+info.teamNames+" 布置过, 是否重复发布", function() {
                     publish(teamList);
              });
          } else {
                publish(teamList);
          }
         }
     });
}
function publish(teamList) {
     
     var unitIds = new Array();
     $($(".unit.acheck input[type='checkbox']")).each(function(index, element) {
           var value = $(element).val();
           if(value!=null && "" !=value && value!="on") {
                unitIds.push(value);
           }
     })
     
//   alert(JSON.stringify(teamList));
//   alert(JSON.stringify(unitIds))
//   return;

    //获取unitType wuhongqiao 2020-01-11
    var unitType = [];
    if($(".fujiadanyuan thead tr th i.ck").hasClass("onn")){
        unitType = [12,21];
    }else{
        unitType = [];
        if($(".fujiadanyuan tbody tr").eq(0).children("td").children("i.ck").hasClass("onn")){
            unitType.push(12)
        }
        if($(".fujiadanyuan tbody tr").eq(1).children("td").children("i.ck").hasClass("onn")){
            unitType.push(21)
        }
    }
     
     var loader = new loadDialog();
    loader.show();
     $.ajax({
         url: "${pageContext.request.contextPath}/learningPlan/task/publish",
         type: "POST",
         data: {"teamIds":JSON.stringify(teamList),"lpId":"${lpId}","unitIds":JSON.stringify(unitIds), "types":JSON.stringify(unitType)},
         async: true,
         success: function(data) {
          loader.close();
          $.success("发布成功!");
              location.href="${pageContext.request.contextPath}/learningPlan/task/my/index";
         }
     });
}
function returnView(jumpfrom) {
     if("edit"==jumpfrom) {
          location.href="${pageContext.request.contextPath}/learningPlan/edit?id=${lpId}";
     } else {
           window.history.go(-1);
     }
}

$('.fujiadanyuan tbody i.ck').click(function(){

    if($(this).hasClass('onn')){
        $(this).removeClass('onn');
        if( $('.fujiadanyuan tbody i.ck').length!=$('.fujiadanyuan tbody .ck.onn').length){
            $('.fujiadanyuan thead i.ck').removeClass('onn');
        }
    }else{
        $(this).addClass('onn');
        if($('.fujiadanyuan tbody i.ck').length==$('.fujiadanyuan tbody .ck.onn').length){
            $('.fujiadanyuan thead i.ck').addClass('onn');
        }
    }
});
//全选
$('.fujiadanyuan thead i.ck').click(function(){
    if($(this).hasClass('onn')){
        $(this).removeClass('onn');
        $('.ck').removeClass('onn');

    }else{
        $(this).addClass('onn');
        $('.ck').addClass('onn');
    }
});

</script>
</html>