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
function findTextBook(name, type) {
		var selectVal = '#' + name;
		var isPublish=0;
		if(name=='subjectCode'){
			$('#publisherId').empty();
			$('#gradeCodeVolumn').empty();
			$('#subjectCode').empty();
			$("<option >请选择</option>").appendTo($('#subjectCode'));
			$("<option >请选择</option>").appendTo($('#publisherId'));
			$("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
			var temp = $('#stageCode').val();
			if(temp==null || ""==temp) {
				return;
			}
		}else if(name=='publisherId'){
			$('#publisherId').empty();
			$("<option >请选择</option>").appendTo($('#publisherId'));
			
			$('#gradeCodeVolumn').empty();
			$("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
			var temp = $('#subjectCode').val();
			if(temp==null || ""==temp) {
				return;
			}
		} else if(name=="gradeCodeVolumn") {
			$('#gradeCodeVolumn').empty();
			$("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
			var temp = $('#publisherId').val();
			if(temp==null || ""==temp) {
				return;
			}
		}
		if("lp_create"==type) {
			$("#dvTextBookCatalog").html("<select style='width:160px;'><option>请选择</option></select>");
		} else {
			$("#dvTextBookCatalog").empty();
		}
		
		$(selectVal).empty();
		$.ajax({
			type : "post",
			async:false,
			url : "${ctp}/teach/textBookMaster/master/textBook",
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'type' : name,
				'isPublish' : isPublish
			},
			success : function(data) {
				var map = eval("(" + data + ")");
				$.each(map, function(key, values) {
					$("<option value="+values+">" + key + "</option>").appendTo(selectVal);
				});
			}
		});
	}

	//获取公共资源的教材目录
function findPublicTextBook(name, type ,isPublish) {
    var selectVal = '#' + name;
    var isPublish=isPublish;
    if($("#radioType").val() == 'qbzy' || isPublish == 1){
        isPublish = 1;
	}else {
        isPublish = 0;
	}
    if(name=='subjectCode'){
        $('#publisherId').empty();
        $('#gradeCodeVolumn').empty();
        $('#subjectCode').empty();
        $("<option >请选择</option>").appendTo($('#subjectCode'));
        $("<option >请选择</option>").appendTo($('#publisherId'));
        $("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
        var temp = $('#stageCode').val();
        if(temp==null || ""==temp) {
            return;
        }
    }else if(name=='publisherId'){
        $('#publisherId').empty();
        $("<option >请选择</option>").appendTo($('#publisherId'));

        $('#gradeCodeVolumn').empty();
        $("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
        var temp = $('#subjectCode').val();
        if(temp==null || ""==temp) {
            return;
        }
    } else if(name=="gradeCodeVolumn") {
        $('#gradeCodeVolumn').empty();
        $("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
        var temp = $('#publisherId').val();
        if(temp==null || ""==temp) {
            return;
        }
    }
    if("lp_create"==type) {
        $("#dvTextBookCatalog").html("<select style='width:160px;'><option>请选择</option></select>");
    } else {
        $("#dvTextBookCatalog").empty();
    }

    $(selectVal).empty();
    $.ajax({
        type : "post",
        async:false,
        url : "${ctp}/teach/textBookMaster/master/textBook",
        data : {
            'stageCode' : $('#stageCode').val(),
            'subjectCode' : $('#subjectCode').val(),
            'gradeCode' : $('#gradeCode').val(),
            'volumn' : $('#volumn').val(),
            'publisherId' : $('#publisherId').val(),
            'type' : name,
            'isPublish' : isPublish
        },
        success : function(data) {
            var map = eval("(" + data + ")");
            $.each(map, function(key, values) {
                $("<option value="+values+">" + key + "</option>").appendTo(selectVal);
            });
        }
    });
}

	function findTextBookCatalog(obj, id, order) {
		var index = obj.selectedIndex; //选中索引
		var kq;
		if(index==undefined) {
			kq = obj.val();
		} else {
			kq = obj.options[index].value; //选中文本
		}
		
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
				async:false,
				type : 'post',
				url : "${ctp}/teach/textBookMaster/master/textBookCatalogSelect",
				cache : false,
				data : {
					'stageCode' : $('#stageCode').val(),
					'subjectCode' : $('#subjectCode').val(),
					'publisherId' : $('#publisherId').val(),
					'gradeCode' : grade,
					'volumn' : volumn,
					'parentId' : parentId,
					'type' : '',
					'isPublish' : $("#radioType").val() == 'qbzy' || ${resourceType eq 'res_region'} ? 1 : 0,
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
									"<option value='"+item.id+"' data-code='"+item.code+"' parentid='"+item.parentId+"'>"
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
	
	function findResTextBookCatalog(obj, id, order, type) {
		if("lp_create"==type) {
			$("#dvTextBookCatalog").empty();
			$("#res_title").val($("#stageCode").find("option:selected").text()+$("#subjectCode").find("option:selected").text()+$("#publisherId").find("option:selected").text()+$("#gradeCodeVolumn").find("option:selected").text());
			changeText();
		}
		var index = obj.selectedIndex; //选中索引
		var kq;
		if(index==undefined) {
			kq = obj.val();
		} else {
			kq = obj.options[index].value; //选中文本
		}
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
        var url = "";
		if($("#radioType").val() == 'qbzy'  || ${resourceType eq 'res_region'}){
            url = "${ctp}/teach/textBookMaster/master/jcTextBookCatalogSelect";
        }else {
            url = "${ctp}/teach/textBookMaster/master/resTextBookCatalogSelect";
        }
		if(!(parentId==''&&parentId!='0')){
			$.ajax({
				
				type : 'post',
				async: false,
				url : url,
				cache : false,
				data : {
					'stageCode' : $('#stageCode').val(),
					'subjectCode' : $('#subjectCode').val(),
					'publisherId' : $('#publisherId').val(),
					'gradeCode' : grade,
					'volumn' : volumn,
					'parentId' : parentId,
					'type' : '',
					'isPublish' : $("#radioType").val() == 'qbzy'  || ${resourceType eq 'res_region'} ? 1 : 0
				},
				dataType : 'json',
				success : function(data) {//roomList
					if (data.length > 0) {
						var $select = $("<select id="+selectId+" name="+selectId+" class='chzn-select' style='width:160px;' ><option value=''>请选择</option></select>");
						$("#dvTextBookCatalog").append($select); //把创建好的加载到div中 ;
						totalAdd = Number(totalAdd) + 1;
						$("#" + selectId).change(function() {
							findResTextBookCatalog(this, '1', count);
						});

					}
					jQuery.each(data, function(i, item) {
						if (data.length > 0) {
							$("#" + selectId).append(
									"<option value='"+item.id+"' data-code='"+item.code+"' parentid='"+item.parentId+"'>"
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
					if($('#gradeCode_div a')!='undefined' && $('#gradeCode_div a').length>0) {
						$('#gradeCode_div a').sort(function(a,b){  
			    		    var aText = $(a).attr("data-volumn-code");  
			    		    var bText = $(b).attr("data-volumn-code");  
			    		    if(aText>bText) return 1;  
			    		    if(aText<bText) return -1;  
			    		    return 0;  
			    		}).appendTo('#gradeCode_div');
					}
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