<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>


</body>
<script type="text/javascript">


function search(){

	var selectVal = '#catalog'+num;
	var value =  $(selectVal).val(); 
	
}

var totalAdd = 0;
function findTextBook(name,type) {
		var selectVal = '#' + name;
		$(selectVal).empty();
		
		var url ="${ctp}/teach/textBookMaster/master/textBook";
		if(type==0){
			url="${ctp}/teach/textBookMaster/master/resTextBook"
		}
		$.ajax({
			type : "post",
			url : url,
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'type' : name,
			},
			success : function(data) {
				var map = eval("(" + data + ")");
				$.each(map, function(key, values) {
					$("<option value="+values+">" + key + "</option>").appendTo(selectVal);
				});
			}
		});
	}

	function findTextBookCatalog(obj, id, order,type) {
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
		
		var gradeCodeVolumnValue = $('#gradeCodeVolumn').val();
		var array = gradeCodeVolumnValue.split("-");
		var grade;
		var volumn;
		if(array.length == 2){
			grade = array[0];
			 volumn = array[1];
		}
		var url ="${ctp}/teach/textBookMaster/master/textBookCatalogSelect";
		if(type==0){
			url="${ctp}/teach/textBookMaster/master/resTextBookCatalogSelect";
		}
		if(!(parentId==''&&parentId!='0')){
			$.ajax({
				type : 'post',
				url : url,
				cache : false,
				data : {
					'stageCode' : $('#stageCode').val(),
					'subjectCode' : $('#subjectCode').val(),
					'publisherId' : $('#publisherId').val(),
					'gradeCode' : grade,
					'volumn' : volumn,
					'parentId' : parentId
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
									"<option value='"+item.id+"' data-code='"+item.code+"'>"
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
	}
	
	function findResTextBookCatalog(obj, id, order) {

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
		
		var gradeCodeVolumnValue = $('#gradeCodeVolumn').val();
		var array = gradeCodeVolumnValue.split("-");
		var grade;
		var volumn;
		if(array.length == 2){
			grade = array[0];
			 volumn = array[1];
		}
		if(!(parentId==''&&parentId!='0')){
		$.ajax({
			type : 'post',
			url : "${ctp}/teach/textBookMaster/master/resTextBookCatalogSelect",
			cache : false,
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'publisherId' : $('#publisherId').val(),
				'gradeCode' : grade,
				'volumn' : volumn,
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
								"<option value='"+item.id+"' data-code='"+item.code+"'>"
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
	}
	
	$.getTextBook = function(data, afterHandler) {
		var defOption = {
			'stageCode' : $('#stageCode').val(),
			'subjectCode' : $('#subjectCode').val(),
			'gradeCode' : $('#gradeCode').val(),
			'volumn' : $('#volumn').val(),
			'publisherId' : $('#publisherId').val(),
			'type' : "",
			"async" : true
		};
		options = $.extend({}, defOption, data || {});
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textBookMaster/master/textBook",
			data : options,
			async : options.async,
			success : function(data) {
				data = eval("(" + data + ")");
				if(afterHandler != null) {
					afterHandler(data);
				} else {
					var selectVal = '#' + options.type;
					$(selectVal).empty();
					$.each(data, function(key, values) {
						$("<option value="+values+">" + key + "</option>").appendTo(selectVal);
					});
				}
			}
		});
	}
	
	$.getResTextBook = function(data, afterHandler) {
		var defOption = {
			'stageCode' : $('#stageCode').val(),
			'subjectCode' : $('#subjectCode').val(),
			'gradeCode' : $('#gradeCode').val(),
			'volumn' : $('#volumn').val(),
			'publisherId' : $('#publisherId').val(),
			'type' : "",
			"async" : true
		};
		options = $.extend({}, defOption, data || {});
		$.ajax({
			type : "post",
			url : "${ctp}/teach/textBookMaster/master/resTextBook",
			data : options,
			async : options.async,
			success : function(data) {
				data = eval("(" + data + ")");
				if(afterHandler != null) {
					afterHandler(data);
				} else {
					var selectVal = '#' + options.type;
					$(selectVal).empty();
					$.each(data, function(key, values) {
						$("<option value="+values+">" + key + "</option>").appendTo(selectVal);
					});
				}
			}
		});
	}
	
</script>
</html>