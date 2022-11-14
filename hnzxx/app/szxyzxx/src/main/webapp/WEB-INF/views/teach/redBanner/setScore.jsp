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
<script type="text/javascript">
$(function() {
    $(".NaN_add").keyup(function(){
    	if($(this).val()>100 && $(this).val()!=1000){
	    		$(this).val($(this).val().substr(0, 2));
	    	}else if($(this).val()==1000){
	    		$(this).val($(this).val().substr(0, 3));
	    	} else if($(this).val()=="."||$(this).val()=="-."){
	    		$(this).val("0.");
	    	} 
    	this.value = this.value.replace(/[^\d.]/g, "");
        //必须保证第一个为数字而不是.
        this.value = this.value.replace(/^\.{2}/g, "");
        //保证只有出现一个.-而没有多个.
        this.value = this.value.replace(/\.{2,}/g, ".");
        //保证.-只出现一次，而不能出现两次以上
        this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        /* 小数点后只能一位 */
        if($(this).val().indexOf(".")>0){
	        var arr = $(this).val().split(".");
	        if(arr[1].length>1){
	            $(this).val($(this).val().substring(0,$(this).val().length-(arr[1].length-1)))
	        }
        }
    });
    $(".NaN_red").keyup(function(){
    	if($(this).val()>100 && $(this).val()!=1000){
	    		$(this).val($(this).val().substr(0, 2));
	    	}else if($(this).val()==1000){
	    		$(this).val($(this).val().substr(0, 3));
	    	} else if($(this).val()=="."||$(this).val()=="-."){
	    		$(this).val("0");
	    	} 
    	this.value = this.value.replace(/[^\d]/g, "");
        //必须保证第一个为数字而不是.
        this.value = this.value.replace(/^\.{2}/g, "");
        //保证只有出现一个.-而没有多个.
        this.value = this.value.replace(/\.{2,}/g, ".");
        //保证.-只出现一次，而不能出现两次以上
        this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        /* 小数点后只能一位 */
        if($(this).val().indexOf(".")>0){
	        var arr = $(this).val().split(".");
	        if(arr[1].length>1){
	            $(this).val($(this).val().substring(0,$(this).val().length-(arr[1].length-1)))
	        }
        }
    });
    /*$(".NaN_add").blur(function(){
    	$( ".NaN_add" ).each(function( index ) {
    		  if(Math.abs($(this).val())=="0"){
    			  $(this).val("");
    		  }
    		});
      });*/
      
    $(".score-standard").hide();//隐藏wenben
    $(".score-standard:eq(0)").show();//显示第一个wenben
    $(".set-up-switch a").click(function(){
        $(".set-up").removeClass("set-up");//移除样式
        $(this).addClass("set-up");//添加样式
        var i=$(this).index();//获得下标
    	if(i==0){
    		$("#flag1").val("1");
    	}else{
    		$("#flag1").val("2");
    	}
        $(".score-standard").hide();//隐藏wenben
        $(".score-standard:eq("+i+")").show();//显示第i个wenben
    });
});

</script>
<body style="background-color:#fff !important" ondragstart="return false" draggable="false"
        ondragenter="event.dataTransfer.dropEffect='none'; event.stopPropagation(); event.preventDefault();"  
        ondragover="event.dataTransfer.dropEffect='none';event.stopPropagation(); event.preventDefault();"  
        ondrop="event.dataTransfer.dropEffect='none';event.stopPropagation(); event.preventDefault();">
        <div class="set-up-switch">
        	<a href="javascript:void(0);" class="set-up" >按分数设置</a>
        	<a href="javascript:void(0);" id="flag2">按排名设置</a>
        	<input type="hidden" id="flag" value="${flag}">
        	<input type="hidden" id="flag1" value="1">
        </div>
	<div class="score-standard">
    <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>年级</th>
                                   <th>获得流动红旗分数值/分以上</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                            <c:forEach items="${score}" var="item">
                            <tr class="">
                            <td>${item.gradeName}</td>
                            <td> <input type="text" name="score" id="${item.gradeId}"class="NaN_add" value="${item.score}"></td>
                            </tr>
                            
                            </c:forEach>
                               
                             </tbody>
                        </table>
                        <div class="push-button">
         <button class="btn-ensure" onclick="save()">保存</button>
                        <c:if test="${flag eq 2}">
                             <button class="btn-negative" onclick="boot()">确定</button>
                         </c:if>
                           <c:if test="${flag eq 1}">
                             <button class="btn-negative" onclick="boot()">取消</button>
                         </c:if>
                         </div>
</div>
<div class="score-standard">
    <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead>
                                <tr role="row">
                                   <th>年级</th>
                                   <th>获取流动红旗数量/排名最前</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                            <c:forEach items="${score}" var="item">
                            <tr class="">
                            <td>${item.gradeName}</td>
                            <td> <input type="text"  name="count" id="${item.gradeId}"class="NaN_red" value="${item.reachCount}"></td>
                            </tr>
                            
                            </c:forEach>
                               
                             </tbody>
                        </table>
                        <div class="push-button">
                        <button class="btn-ensure" onclick="save()">保存</button>
                        <c:if test="${flag eq 1}">
                             <button class="btn-negative" onclick="boot()">确定</button>
                         </c:if>
                           <c:if test="${flag eq 2}">
                             <button class="btn-negative" onclick="boot()">取消</button>
                         </c:if>
                        </div>
</div>
</body>
<script type="text/javascript">
function boot()
{    
   	 var gradeIds =new Array();
   	 var i=0;
	 $("#data-table  input[type='text']").each(function(){
			   gradeIds[i]=$(this).attr("id");
			   i=i+1;
	 });
	 $requestData={};
	 $requestData.gradeId=JSON.stringify(gradeIds);
	 $requestData.flag=$('#flag').val();
	 $.post("${pageContext.request.contextPath}/teach/redBanner/way",$requestData, function(data,status) {
			if ("success" === status) {
				$.success("操作成功");
				$.closeWindow();

			}else{
				
				$.error("操作失败");
			}

		});
	 
}
function save(){
	 $("#data-table  input[type='text']").each(function(){
		 if($(this).val()==""){
			 $.error("必须填写所有空");
			 return false;
		 }
	 });
// 	 alert("s");
	var scores= new Array();
	var gradeIds =new Array();
	var count=new Array();
	var i=0;
	var j=0;
	 $("#data-table  input[type='text']").each(function(){
		  
		   var way=$(this).attr("name");
		   if(way=="score"){
		   scores[i]=$(this).val();
		   gradeIds[i]=$(this).attr("id");
		   i=i+1;
		   }else{
			   count[j]=$(this).val();
			   j++;
		   }
             
             
		  });
//      alert(scores);
//      alert(gradeIds);
//      alert(count);
// 	 alert("s");
	 $requestData={}
	 $requestData.scores=JSON.stringify(scores);
	 $requestData.gradeId=JSON.stringify(gradeIds);
	 $requestData.count=JSON.stringify(count);
	 $requestData.flag=$('#flag1').val();
	 $.post("${pageContext.request.contextPath}/teach/redBanner/Standardr",$requestData, function(data,status) {
					if ("success" === status) {
						$.success("保存成功");
						$.closeWindow();

					}else{
						
						$.error("保存失败");
					}
    
				});
}

</script>
</html>