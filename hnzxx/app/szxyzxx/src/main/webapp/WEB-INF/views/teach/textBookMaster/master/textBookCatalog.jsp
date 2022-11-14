<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="select_b">
		<div id="dvTextBook" class="select_div">
			<span>学段: <select id="stageCode" name="stageCode"
				class="chzn-select" style="width: 160px;"
				onchange="findTextBook('subjectCode');">
									<c:forEach items="${stageCodeMap}" var="map">
										<option value="${map.value }">${map.key}</option>
									</c:forEach>
			</select>
			</span> <span>科目:<select id="subjectCode" name="subjectCode"
				class="chzn-select" style="width: 160px;"
				onchange="findTextBook('gradeCode');">
					<option value="">请选择</option>
			</select></span> <span>年级:<select id="gradeCode" name="gradeCode"
				class="chzn-select" style="width: 160px;"
				onchange="findTextBook('volumn');">
					<option value="">请选择</option>
			</select></span> <span>册数:<select id="volumn" name="volumn"
				class="chzn-select" style="width: 160px;"
				onchange="findTextBook('publisherId');">
					<option value="">请选择</option>
			</select></span> <span>版本:<select id="publisherId" name="publisherId"
				class="chzn-select" style="width: 160px;"
				onchange="findTextBookCatalog(this,'0','0');">
					<option value="">请选择</option>
			</select></span>
		</div>
		<div id="dvTextBookCatalog" class="select_div"></div>

	</div>

</body>
<script type="text/javascript">

	function search() {

		var selectVal = '#catalog' + num;
		var value = $(selectVal).val();
		alert(value);

	}

	var totalAdd = 0;
	function findTextBook(name) {
		var selectVal = '#' + name;
		$(selectVal).empty();
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textBookMaster/master/textBook",
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'type' : name
			},

			success : function(data) {
				var map = eval("(" + data + ")");
				
				alert(map);
				
				$.each(map, function(key, values) {
					$("<option value="+values+">" + key + "</option>")
							.appendTo(selectVal);
				});
			}
		});
	}

	function findTextBookCatalog(obj, id, order) {
		var index = obj.selectedIndex; //选中索引
		var kq = obj.options[index].value; //选中文本
		//创建select
		var parentId = 0;
		if (id == 0) {
			parentId = 0;
		} else {
			parentId = kq;
		}
		var count = Number(order) + 1;

		var selectId = 'catalog' + count;

		while (totalAdd > order) {//移除不必要的select选项

			$("#" + 'catalog' + totalAdd).remove();
			totalAdd = Number(totalAdd) - 1;
		}

		$.ajax({
			type : 'post',
			url : "${ctp}/teach/textBookMaster/master/textBookCatalogSelect",
			cache : false,
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'parentId' : parentId,
				'type' : ''
			},
			dataType : 'json',
			success : function(data) {//roomList
				if (data.length > 0) {
					var $select = $("<select id="+selectId+" name="+selectId+" class='chzn-select' style='width:160px;' ><option value=''>请选择</option></select>");
					$("#dvTextBookCatalog").append($select); //把创建好的加载到div中 ;
					totalAdd = Number(totalAdd) + 1;
					$("#" + selectId).change(function() {
						findTextBookCatalog(this, '1', count);
					});

				}
				jQuery.each(data, function(i, item) {
					if (data.length > 0) {
						$("#" + selectId).append(
								"<option value='"+item.id+"'>"
										+ item.name + "</option>");
						//alert(item.id+","+item.name);
					}
				});
			},
			error : function() {
				return;
			}
		});
	}
</script>
</html>