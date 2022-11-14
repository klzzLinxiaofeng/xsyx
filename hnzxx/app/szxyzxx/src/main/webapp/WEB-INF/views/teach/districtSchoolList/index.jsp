<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="区域学校列表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							区域学校列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<input type="hidden" value="${level }" id="level" />
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
								<select style="width: 120px;" id="province" name="province" disabled="disabled"></select>
								<select style="width: 120px;" id="city" name="city" ></select>
								<select style="width: 120px;" id="district" name="district"></select>
								<select style="width: 250px;" id="name" name="name">
									<option value="">请选择</option>
								</select>

								</div>
								<button onclick="search()" class="btn btn-primary" type="button">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>学校名称</th>
										<th>英文名称</th>
										<th>学校代码</th>
										<th>统一代码</th>
										<th>学校类别</th>
										<th>办学类型</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody id="districtSchool_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="districtSchool_list_content" />
								<jsp:param name="url"
									value="/teach/districtSchoolList/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize }" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var level = $("#level").val();
	$(function() {
		if(level=="1"){
		$.initRegionSelector({
    		sjSelector : "province",
			shijSelector : "city",
			qxjSelector : "district",
			isEchoSelected : true,
			sjSelectedVal : "${region.id}",
    	});	
		
		}else if(level=="2"){
			$.initRegionSelector({
	    		sjSelector : "province",
				shijSelector : "city",
				qxjSelector : "district",
				isEchoSelected : true,
				sjSelectedVal : "${region.id}",
				shijSelectedVal : "${region1.id}"
	    	});	
			
			$("#city").prop('disabled', true);
			
		}else if(level=="3"){
			$.initRegionSelector({
	    		sjSelector : "province",
				shijSelector : "city",
				qxjSelector : "district",
				isEchoSelected : true,
				sjSelectedVal : "${region.id}",
				shijSelectedVal : "${region1.id}",
				qxjSelectedVal : "${region2.id}"
	    	});	
			$("#city").prop('disabled', true);
			$("#district").prop('disabled',true);
		}
		
		
		
		$("#district").change(function() {
			$("#name").get(0).options.length = 1;
			getSchoolList();
		});
		
	}); 
	
	// 根据省份、市以及区下异步获取学校下拉框的数据
	function getSchoolList() {
		var district = $("#district").val();
		if (district != '') {
			var url = "${pageContext.request.contextPath}/teach/districtSchoolList/getSchoolList";
			$.ajax({
				url : url,
				data : 'district=' + district,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							$("#name").append(
									"<option value="+data[i].id+">"
											+ data[i].name + "</option>");
						}
						
							remove("name");
						
						
					}
				}
			});
		}
		
	}

	function search() {
		var val = {};
		var province = $("#province").val();
		var city = $("#city").val();
		var district = $("#district").val();
		var name = $("#name").val();
		if (province != null && province != "") {
			val.province = province;
		}

		if (city != null && city != "") {
			val.city = city;
		}

		if (district != null && district != "") {
			val.district = district;
		}

		if (name != null && name != "") {
			val.name = name;
		}

		var id = "districtSchool_list_content";
		var url = "/teach/districtSchoolList/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	function remove(id) {
		var options = "";
		$("#" + id).find("option").each(function(j, m) {
			if (options.indexOf($(m)[0].outerHTML) == -1) {
				options += $(m)[0].outerHTML;
			}
		});
		$("#" + id).html(options);
	}
</script>
</html>