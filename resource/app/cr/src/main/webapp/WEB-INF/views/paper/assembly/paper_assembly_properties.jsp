<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>试卷属性设置</title>
</head>
<body style="overflow: hidden;">
<div class="test-setting" >
    <div class="selected_radio pdb10">
    <i style="color:red;">*</i>
        <label>创建到</label>
        <ul>
        	<c:if test="${type==1 }">
            	<li class="choose"><input type="radio" value="1">校本库</li>
            	<li><input type="radio" value="2">个人库</li>
            </c:if>
            <c:if test="${type==2 }">
            	<li><input type="radio" value="1">校本库</li>
            	<li class="choose"><input type="radio" value="2">个人库</li>
            </c:if>
        </ul>
    </div>
     <div class="jjmu">
        <p>教材目录：</p>
        <div>
            <i>*</i>
            <span>学段</span>
            <select id="stage" disabled="disabled">
                <option selected="selected" value="${assemblyPaper.stageCode }">${assemblyPaper.stageName }</option>
            </select>
        </div>
        <div style="margin-bottom:0px;">
            <span class="mgr8">科目</span>
            <ul class="kemu">
                <c:forEach items="${assemblyPaper.subject }" var="subject">
                	 <li data-code="${subject.subjectCode }" ><span>${subject.subjectName }</span></li>  
                </c:forEach>
            </ul>
        </div>
        <div class="jc_ml">
        	<c:forEach items="${assemblyPaper.subject }" var="subject" varStatus="status">
	            <c:if test="${status.index==0 }">
		            <div class="jm">
		                <div class="jiaocai">
		                    <span class="mgl20">教材</span>
		                    <select name="textbook">
		                        <option selected="selected" value=0>无</option>
		                    </select>
		                </div>
		                <div class="mulu">
		                    <i></i>
		                    <span class="mgl20" style="float:left;">目录</span>
		                     <div style="margin-left: 80px;"> 
		                    	<select class="first_select">
		                        	<option selected="selected" value=0>无</option>
		                    	</select>
		                     </div> 
		                    
		                </div>
	                </div>
                </c:if>
                <c:if test="${status.index>0 }">
		            <div class="jm" style="display: none">
		                <div class="jiaocai">
		                    <span class="mgl20">教材</span>
		                    <select name="textbook">
		                        <option selected="selected" value=0>无</option>
		                    </select>
		                </div>
		                <div class="mulu">
		                    <i></i>
		                    <span class="mgl20" style="float:left;">目录</span>
		                    <div style="margin-left: 80px;"> 
		                    	<select class="first_select">
		                        <option selected="selected" value=0>无</option>
		                    </select>
		                    </div> 
		                    
		                </div>
	                </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <div class="last_ope">
        <a href="javascript:void(0)" class="btn-blue" onclick="save()">确定</a>
        <a href="javascript:void(0)" class="btn-lightGray" onclick="$.closeWindow();">取消</a>
    </div>
</div>
</body>
<script type="text/javascript">
//滚动条
$(document).ready(function() { 
	$("html").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
});  
$.ajaxSetup({
	async : false
});
var tab = 0;
$(function(){
	  $('.selected_radio ul li').click(function(){
          $(this).siblings("li").removeClass("choose");
          $(this).addClass("choose");
      });
	  
      $('.jjmu div ul.kemu li').click(function(){
          var index = $(this).index();
          tab = index;
          
          $(this).addClass('choose');
          $(this).siblings('li').removeClass('choose');
          
          $('.jc_ml').find('.jm').css('display','none');
          $('.jc_ml').find('.jm').eq(index).css('display','block');
      });
	  
      $('.jjmu div ul.kemu li').eq(0).click();
      
      $('body').on('change','.jm .mulu select',function(){
    	  var textbook = $("select[name='textbook']").eq(tab).val();
    	  var parentid = $(this).val();
    	  if(parentid==0) {
    		  $(this).nextAll().remove();
    	  } else {
    		  var catalogId = $(this).find("option:selected").data("code");
    		  getCatalog(textbook, catalogId);
    	  }
      });
      
      $('body').on('change','.jjmu .jiaocai select',function(){
    	  var textbook = $("select[name='textbook']").eq(tab).val();
    	  $(".jm .mulu").eq(tab).find("select").eq(0).nextAll().remove();
    	  if(textbook==0) {
    		  var option = "<option>无</option>";
    		  $(".jm .mulu").eq(tab).find("select").eq(0).html(option);
    	  } else {
    		  getCatalog(textbook, 0);
    	  }
      });
      
      initTextbook(function() {
    	  review();
      });
})


function initTextbook(afterHandler) {
	var length =  $('.jjmu div ul.kemu li').length;
    $('.jjmu div ul.kemu li').each(function(index){
        var subjectCode = $(this).data("code");
        var stageCode = $("#stage").val();
        
        getTextbook(stageCode, subjectCode, index);
    });
   
    afterHandler();
}

function review() {
	var catalogs = ${catalogs};
	for (var i = 0; i < catalogs.length; i++) {
		tab = i;
		var book = catalogs[i].book;
		var bookid = book.id;
		if(bookid!=0) {
			$("select[name='textbook']").eq(i).val(bookid);
			$("select[name='textbook']").eq(i).trigger("change");
		}
		var bookUnit = catalogs[i].bookUnit;
		var bookUnitcode = bookUnit.code;
		if(bookUnitcode!="" && bookUnitcode!="无") {
			$(".jjmu div.mulu div").eq(i).children("select").eq(0).val(bookUnitcode);
			$(".jjmu div.mulu div").eq(i).children("select").eq(0).trigger("change");
		}
		var bookSction = catalogs[i].bookSction;
		var bookSctioncode = bookSction.code;
		if(bookSctioncode!="" && bookSctioncode!="无") {
			$(".jjmu div.mulu div").eq(i).children("select").eq(1).val(bookSctioncode);
			$(".jjmu div.mulu div").eq(i).children("select").eq(1).trigger("change");
		}
		var bookItem = catalogs[i].bookItem;
		var bookItemcode = bookItem.code;
		if(bookItemcode!="" && bookItemcode!="无") {
			$(".jjmu div.mulu div").eq(i).children("select").eq(2).val(bookItemcode);
			$(".jjmu div.mulu div").eq(i).children("select").eq(2).trigger("change");
		}
	}
	tab = 0;
}

function getTextbook(stageCode, subjectCode, index) {
	var param = {"stageCode":stageCode, "subjectCode":subjectCode}
	$.get("${ctp}/base/widget/getResVersionAndVolumn", param, function(data, status) {
		if ("success" === status) {
			var result = JSON.parse(data);
			var textbook = "<option value=0>请选择</option>"
			for(var i=0; i<result.length; i++) {
				textbook += "<option value="+result[i].id+">"+result[i].info+"</option>"
			}
			$("select[name='textbook']").eq(index).html(textbook);
		}
	});
}

function getCatalog(textbookid, parentid) {
	var url = "${ctp}/base/widget/getResCatalogByParentId";
	
	$.get(url, {"textbookId":textbookid, "parentId":parentid}, function(data, status) {
		if ("success" === status) {
			var param = JSON.parse(data);
			if(param.length==0) {
				return;
			}
			var option = "<option value=0>请选择</option>";
			for (var index = 0; index < param.length; index++) {
				option += "<option value="+param[index].code+" data-code='"+param[index].id+"'>"+param[index].name+"</option>"
			}
			if(parentid==0) {
				$(".jjmu .mulu div").eq(tab).find("select").eq(0).html(option);
			} else {
				var select = "<select class=\"first_select\">";
				select += option + "</select>";
				$(".jjmu .mulu div").eq(tab).append(select);
			}
		}
	});
}

function save() {
	var check = true;
	var finishType = parent.frames["iframe_main"].finishType;
	var catalogs = new Array();
	var textbook_index = 0;
	
	$("select[name='textbook']").each(function(index) {
		var textbookId = $(this).val();
		
		if(finishType==1 && textbookId==0) {
			$(".jjmu div ul.kemu li span").eq(index).click();
			check = false;
			return check;
		}
		
		var textbookName = $(this).find("option:selected").text();
		var catalog = {};
		var book = {"id":textbookId, "name":textbookName};
		catalog["book"] = book;
		catalog["volumn"] = book;
		catalog["bookUnit"] = {"code":"", "name":""};
		catalog["bookSction"] = {"code":"", "name":""};
		catalog["bookItem"] = {"code":"", "name":""};
		
		$(this).parents(".jiaocai").next().find("select").each(function(index) {
			var code = $(this).val();
			var name = $(this).find("option:selected").text();
			
			if(index==0 && code==0 && finishType==1) {
				$(".jjmu div ul.kemu li span").eq(textbook_index).click();
				check = false;
				return check;
			}
			if(code=="0") {
				code="";
				name="";
			}
			var bookUnit = {"code":code, "name":name};
			if(index==0) {
				catalog["bookUnit"] = bookUnit;
			} else if(index==1){
				catalog["bookSction"] = bookUnit;
			} else if(index==2) {
				catalog["bookItem"] = bookUnit;
			}
		})
		
		if(!check) {
			return false;
		}
		catalogs[textbook_index] = catalog;
		textbook_index++;
	})
	
	var subjects = new Array();
	$(".jjmu div ul.kemu li").each(function(index) {
		var subjectCode = $(this).data("code");
		var subjectName = $(this).children("span").text();
		var subject = {"code":subjectCode, "name":subjectName};
		subjects[index] = subject;
	})
	
	var type = $(".selected_radio .choose").children("input").val();
	if(check) {
		if(finishType==1) {
			var catalogStr = JSON.stringify(catalogs);
			var subjectStr = JSON.stringify(subjects);
			saveProperties(type, catalogStr, subjectStr, finishType);
			parent.frames["iframe_main"].finish();
		} else {
			saveProperties(type, JSON.stringify(catalogs), JSON.stringify(subjects));
		}
	}
}

function saveProperties(type, catalogs, subjects, finishType) {
	parent.frames["iframe_main"].ownerModel = type;
	$.ajax({
	    url: "${pageContext.request.contextPath}/paper/assembly/properties/save",
	    type: "POST",
	    data: {"catalogs":catalogs, "subjects":subjects, "type":type},
	    async: false,
	    success: function() {
	    	if(finishType==null) {
	    		$.closeWindow();
	    	}
	    }
	});
}
</script>
</html>