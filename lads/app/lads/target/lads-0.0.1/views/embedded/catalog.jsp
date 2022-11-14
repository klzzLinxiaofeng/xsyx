<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	//根据教材目录id查找对应的目录
	function textBookCatalogListById(catalogId, functionName, div, prefixId) {
		textBookCatalogList(div, '', '', '', '', '', '', catalogId,
				functionName, prefixId);

	}

	//根据教材目录查询条件查找对应的目录
	function textBookCatalogListByCondition(stageCode, subjectCode, gradeCode,
			publisherId, volumn, functionName, div, prefixId) {
		textBookCatalogList(div, stageCode, gradeCode, subjectCode, volumn,
				publisherId, 'select', '', functionName, prefixId);

	}

	function textBookCatalogList(div, stageCode, gradeCode, subjectCode,
			volumn, publisherId, type, catalogId, functionName, prefixId) {
		//var functionName = '_dblclick';
		//alert("div:"+div+",stageCode:"+stageCode+",gradeCode:"+gradeCode+",subjectCode："+subjectCode+",volumn："+volumn+",publisherId:"+publisherId+",type:"+type+",catalogId:"+catalogId);
		$("#" + div).empty();

		var $divInit = "<div class='unit-area'><div class='hd'><h1 style='text-indent: 0;' id='"+prefixId+"root'></h1></div><div class='bd jspScrollable'><div class='jspContainer'><div class='jspPane' id='"+prefixId+"catalogList_div'><ul class='unit-list mb20' id='"
				+ prefixId + "ulall'</ul></div></div></div>"

		$("#" + div).append($divInit);

		$("#" + prefixId + "ulall").empty();
		if ('select' == type) {
			var flag = InitSelection(stageCode, gradeCode, subjectCode, volumn,
					publisherId);
			if (!flag) {
				return;
			}
		} else {
			if (catalogId == null || catalogId == "") {
				alert("当type类型不为select时，教材内容id不能为空");
				return;
			}
		}

		$
				.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/teach/textBookMaster/master/textBookCatalogList", //${ctp}/teach/textBookMaster/master
					data : {
						'stageCode' : stageCode,
						'subjectCode' : subjectCode,
						'gradeCode' : gradeCode,
						'volumn' : volumn,
						'publisherId' : publisherId,
						'catalogId' : catalogId,
						'type' : type
					},

					success : function(data) {
						var jsonObj = eval("(" + data + ")");
						if (jsonObj.length > 1) {
							var $allli = $("<li class='unit-item' id = '"+prefixId+"all'><a href='javaScript:void(0);' class='unit-link strong ib' title='全部'><b onclick='"
									+ functionName
									+ "(-1, this)'>全部</b></a></li>");
							$("#" + prefixId + "ulall").append($allli);
							//默认选中全部
							$allli.find("b").click();
						} else {
							$("#" + prefixId + "root").empty();
						}
						var unitAddLi = 0;
						$.each(
							jsonObj,
							function(i, item) {
								if (item.level == 0) {
									$("#" + prefixId + "root").prepend(item.name);
									// 	        		var $change = "<br><a href='javascript:void(0)' class='qiehuan'>切换教材</a>";
									var $change = "";
									$("#" + prefixId + "root").append($change);
								} else if (item.level == 1) {
									unitAddLi = Number(unitAddLi) + 1;
									var $unitaddli = "<li class='unit-item' id="+prefixId+"li"+unitAddLi+"><a href='javaScript:void(0);' class='unit-link strong ib' title='"
											+ item.name
											+ "'><b onclick='"
											+ functionName
											+ "("
											+ item.id
											+ ", this);'>"
											+ item.name
											+ "</b></a>";
									var ul = prefixId + "ul"
											+ unitAddLi;

									$unitaddli += "<ul class='lesson-list'  id="+ul+">";

									$unitaddli += "</ul>";
									$unitaddli += "</li>";
									$("#" + prefixId + "ulall")
											.append($unitaddli);

								} else if (item.level > 1) {
									var $uraddli = "<li  class='lesson-item' id="+item.id+"><a title='"
											+ item.name
											+ "' href='javaScript:void(0);' class='lesson-link ib pl5 pt5'><b onclick='"
											+ functionName
											+ "("
											+ item.id
											+ ", this);'>"
											+ item.name
											+ "</b></a></li>";
									var $ul = "#" + prefixId + "ul"
											+ unitAddLi;
									$($ul).append($uraddli);
								}
								
							});

					},
				});
	}

	function InitSelection(stageCode, gradeCode, subjectCode, volumn,
			publisherId) {

		if (gradeCode != null && gradeCode != "") {

		} else {
			$.alert("请选择年级");
			return false;
		}
		if (subjectCode != null && subjectCode != "") {

		} else {
			$.alert("请选择科目");
			return false;
		}
		if (volumn != null && volumn != "") {

		} else {
			$.alert("请选择册次");
			return false;
		}
		if (publisherId != null && publisherId != "") {

		} else {
			$.alert("请选择版本");
			return false;
		}
		return true;
	}
</script>

