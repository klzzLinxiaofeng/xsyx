<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>发展卡评价</title>
<style>
	.reflective-evaluate th, .reflective-evaluate td{border-right:0 none;}
</style>
<script type="text/javascript">
function onlyNum() {
    if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}
$(function() {
$(".NaN_add").keyup(function(){
	
	if($(this).val()>20 && $(this).val()!=200){
    		$(this).val($(this).val().substr(0, 1));
    	}else if($(this).val()==200){
    		$(this).val($(this).val().substr(0, 2));
    	}else if($(this).val()==0){
    		$(this).val("");
    	}
	
	this.value = this.value.replace(/[^\d]/g, "");

  
  });

  
});

</script>
</head>
<body style="background-color:#fff;">
<div class="outnumber">
    <table class="outnumber-installed" id="tbl">
        <thead>
            <tr role="row">
                <th>年级</th>
                <th>班级之星</th>
                <th>年级之星</th>
                <th>全校之星</th>
            </tr>
        </thead>
        <tbody id="module_list_content">
<!--             <tr class=""> -->
<!--                 <td>一年级</td> -->
<!--                 <td><input type="text" onkeydown="onlyNum();"></td> -->
<!--                 <td><input type="text" onkeydown="onlyNum();"></td> -->
<%--                 <td rowspan=6><input type="text" class="school-star" onkeydown="onlyNum();" value="${schoolnum}"></td> --%>
<!--             </tr> -->
            <c:forEach  items="${items}" var="item" varStatus="status">
            <tr>
            <td>${item.gradeName}<input id="gradeId" type="hidden"   value="${item.gradeId}"></td>
            <td><input type="text" id="teamnum"      class="NaN_add" value="${item.teamCount}"></td>
            <td><input type="text" id="gradenum"     class="NaN_add" value="${item.gradeCount}"></td>
            <c:if test="${status.index==0}">
             <td rowspan=6><input type="text" id="schoolnum" class="school-star NaN_add"  value="${schoolnum}"></td>
            </c:if>
            
            </tr>
            
            </c:forEach>
        </tbody>
    </table>
    <button class="btn-ensure" onclick="save()" style="float: none;margin: 20px auto;">保存</button>
</div>
</body>
<script type="text/javascript">
function save(){
	var s=1;
	 $("input").each(function(){

		 if($(this).val()==""){
	        s=2
		 }
	 });
	if(s==2){
	 $.error("请填写全部填空");
	 return false;
	}
	var gradenum= new Array();
	var teamnum= new Array();
	var gradeIds =new Array();
	var a=0;
	var b=0;
	var c=0;
	$("input").each(function() {
	       var ids=$(this).attr("id");
	       if(ids=='gradeId'){
	    	   
	    	   gradeIds[a]=$(this).val();
	    	   a=a+1;
	       }
	       if(ids=='gradenum'){
	    	   gradenum[b]=$(this).val();
	    	   b=b+1;
	       }
	       if(ids=='teamnum'){
	    	   teamnum[c]=$(this).val();
	    	   c=c+1;
	       }
	    });
//	 alert("s");
	 $requestData={}
	
	 $requestData.gradeId=JSON.stringify(gradeIds);
	 $requestData.gradenum=JSON.stringify(gradenum);
	 $requestData.teamnum=JSON.stringify(teamnum);
	 $requestData.schoolnum=$('#schoolnum').val();
	 $.post("${pageContext.request.contextPath}/teach/starPerson/setNum",$requestData, function(data,status) {
					if ("success" === status) {
                       $.success("保存成功");
						setTimeout(function () {
							parent.core_iframe.sure();
							$.closeWindow();
						},500)

					}else{
						
						$.error("保存失败");
					}
					
				});

}
</script>
</html>