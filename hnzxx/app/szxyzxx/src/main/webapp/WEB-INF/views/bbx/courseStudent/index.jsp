<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
<style>
    .conscc{font-size: 12px;padding:20px 20px 1px 20px;}
  .conscc input,.conscc label{ margin:0;}
  
    #table_con{ border: 1px solid #bbbbbb}
    #table_con tr:first-child td{ border-bottom:1px solid #bbbbbb }
#table_con tr td:first-child{ text-align: center;}
#table_con tr td{ padding: 10px 0; font-size: 12px;}
.but_con{ margin-top: 15px;}
    .but_con input{ margin-left: 10px; }
    .select_b .select_div{line-height:31px;}
</style>	
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" style="margin-top: 7px;">
					<div class="widget-head">
						<h3>
							学生选课
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
			       
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>年级：</span>
									${gradeName }
								</div>
								<div class="select_div">
									<span>开始时间：</span>
									<fmt:formatDate value="${config.signupStartDate }" pattern="yyyy年MM月dd日 HH:mm" />
								</div>
								<div class="select_div">
									<span>结束时间：</span>
									<fmt:formatDate value="${config.signupFinishDate }" pattern="yyyy年MM月dd日 HH:mm" />
								</div>
								<div class="clear"></div>
							</div>
								
							</div>
							 
								<div class="conscc"  id="app">
								    <table width="100%" cellspacing="0" id="table_con">
								        <tr style="background: #e6e6e6; border:1px solid #bbb">
								            <td width="60px">
								            </td>
								            <td>
								          		  科目组
								            </td>
								            <td>
								            	已报名人数
								            </td>
								            <c:if test="${config.isLimited == true}">
								            	<td>
								            		名额
								           		</td>
								            </c:if>
								        </tr>
									        <c:forEach items="${items}" var="item">
										        <tr>
										            <td>
										            <c:choose>
										            <c:when test="${item.disalbed == true }">
										            	<c:choose>
											        		<c:when test="${item.selected == true }">
											                	<input name="courseNames" type="radio" disabled="disabled" checked="checked" id="${item.id }" value="${item.id }" v-model="checkedNames">
											        		</c:when>
											        		<c:otherwise>
											        			<input name="courseNames" type="radio" disabled="disabled" id="${item.id }" value="${item.id }" v-model="checkedNames">
											        		</c:otherwise>
											        	</c:choose>
										            </c:when>
										            <c:otherwise>
											        	<c:choose>
											        		<c:when test="${item.selected == true }">
											                	<input name="courseNames" type="radio" checked="checked" id="${item.id }" value="${item.id }" v-model="checkedNames">
											        		</c:when>
											        		<c:otherwise>
											        			<input name="courseNames" type="radio" id="${item.id }" value="${item.id }" v-model="checkedNames">
											        		</c:otherwise>
											        	</c:choose>
										            </c:otherwise>
										            </c:choose>
										            </td>
										            <td>
										                <label for="${item.id }">${item.courseNames }</label>
										            </td>
										            <td>
										            	<label for="${item.id }">${item.enrollCount }</label>
										            </td>
										            <c:if test="${config.isLimited == true}">
								            			<td>
								            				${item.maxNum}
								           				</td>
								            		</c:if>
										        </tr>
									        </c:forEach>
								    </table>
								    <c:choose>
			        				<c:when test="${prompt == '' }">
								    <div class="but_con">
								        <input class="btn btn-primary" type="button" onclick="save()" value="提交">
								    </div>
								    </c:when>
							        <c:otherwise>
							        	<p style="font-size:28px; line-height:280px;text-align:center;">${prompt }</p>
							        </c:otherwise>
							        </c:choose>
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
/* $(function(){
	$('#snm').html($("#table_con input[type='checkbox']:checked").length);
})
	var sun = ${config.allowedElectiveCount };
	$('#table_con').on('click','input',function (e) {
	   if($("#table_con input[type='checkbox']:checked").length>sun){
	       $(this).attr("checked", false)
	       $.error('只能选'+sun+'科')
	   }else{
	    $('#snm').html($("#table_con input[type='checkbox']:checked").length)
	   }
	}) */
	
	

	function save(){
		var loader = new loadLayer();
		var $requestData = {};
		var jsonArray = [];
		var courseConfigDetailId = $('input:radio:checked').val();	
		/* $("#table_con input[type='checkbox']:checked").each(function(){
			jsonArray.push($(this).val());
		});
		if(jsonArray.length < sun){
			$.error("未达到选择科目数！");
			return;
		} 
		$requestData.courses = JSON.stringify(jsonArray);*/
		$requestData.courseConfigDetailId = courseConfigDetailId;	
		var url = "${ctp}/bbx/courseStudent/creator";
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success(data.responseData);
					$.refreshWin();
				} else if("fail" === data.info){
					$.error(data.responseData);
				}else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
		});
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/courseStudent/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>