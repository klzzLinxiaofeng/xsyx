<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author yhc
//@date 2020年12月8日
(function($){




    $.createGradeSelector = function (options) {
		var defaults = {
			"deptContainer" : "#grade",
			"clickCallback" : function() {},
			"selectedItemTitle" : "",
			"selectedItemId" : "",
			"enableBatch" : false,
			"schoolId" : ""
		};
		var requestData = {};
		var zTree;
		var settings = $.extend({}, defaults, options || {});
		
		var ajaxGetNodes = function(treeNode, reloadType) {
			if (reloadType == "refresh") {
				zTree.updateNode(treeNode);
			}
			zTree.reAsyncChildNodes(treeNode, reloadType, true);
		};
		
		var setting = {
			async : {
				enable : true,
				url : function(treeId, treeNode) {
					var param = "parentId=" + treeNode.id;
					return ctp() + "/micro/management/getGradel?" + param;
				}
			},
			
			data : {
				simpleData : {
					enable : true
				}
			},
			view : {
				expandSpeed : "",
			},
			callback : {
				beforeExpand : function(treeId, treeNode) {
					if (!treeNode.isAjaxing) {
						treeNode.times = 1;
						// ajaxGetNodes(treeNode, "refresh");
						return true;
					} else {
						return false;
					}
				},
				onAsyncSuccess : function(event, treeId, treeNode, msg) {
					if (!msg || msg.length == 0) {
						return;
					}
					var totalCount = treeNode.count;
					if (treeNode.children.length < totalCount) {
						setTimeout(function() {
							ajaxGetNodes(treeNode);
						}, perTime);
					} else {
						$.each(treeNode.children, function(index, value) {
							value.icon = "${ctp}/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
							zTree.updateNode(value);
							if(settings.enableBatch) {
								if (settings.selectedItemId != null && settings.selectedItemId != "") {
									if (settings.selectedItemId.indexOf(value.id) != -1){
										zTree.checkNode(treeNode.children[index]);
									}
								}
							}
						});
						zTree.selectNode(treeNode.children[0]);
					}
				},
				onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
					treeNode.icon = "";
					zTree.updateNode(treeNode);
				},
				onClick : function() {
					var nodes = zTree.getSelectedNodes();
					if(nodes && nodes.length > 0) {
						var id = nodes[0].id;
						var name = nodes[0].name;				
						$("#dept_selected_name_span").text(name);
						$(settings.deptContainer).attr("data-id", id);
					}
					settings.clickCallback(nodes[0]);
				},
				
			}
		};
		
		if (settings.enableBatch) {
			setting.check = {
				autoCheckTrigger : true,
				chkboxType : {"Y" : "" , "N" : ""},
				chkStyle : "checkbox",
				enable : true,
				nocheckInherit : true,
				chkDisabledInherit : false,
				radioType : "level"
			}
			setting.callback.onCheck = function(event, treeId, treeNode) {
				var deptName = $("#dept_selected_name_span").text();
				var id = $(settings.deptContainer).attr("data-id");
				id = id != null && id != "undefined" ? id : "";
				if (treeNode.checked) {
					$("#dept_selected_name_span").text(("请选择" === deptName || "" === deptName ? "" : (deptName + ",")) + treeNode.name);
					$(settings.deptContainer).attr("data-id", ("" === id ? "" : (id + ",")) + treeNode.id);

				} else {
					deptName = deptName.replace(treeNode.name, "").replace(",,", ",");
					id = id.replace(treeNode.id, "").replace(",,", ",");
					if (deptName.startWith(",")) {
						deptName = deptName.substring(1, deptName.length);
						id = id.substring(1, id.length);
					} 
					
					if (deptName.endWith(",")) {
						deptName = deptName.substring(0, deptName.length - 1);
						id = id.substring(0, id.length - 1);
					}
					
					if ("" === deptName) {
						deptName = "请选择";
						id = "";
					}
					
					$("#dept_selected_name_span").text(deptName);
					$(settings.deptContainer).attr("data-id", id);
				}
				settings.clickCallback(treeNode);
			}
			setting.callback.onClick = function(event, treeId, treeNode) {
				settings.clickCallback(treeNode);
			}
		}
		
		var initModuleTree = function() {
			var requestData = {};
			
			if (settings.schoolId != "" && settings.schoolId != null) {
				requestData.schoolId = settings.schoolId;
			}
			
			$.get(ctp() + "/micro/management/getGradel", requestData,
			function(value, status) {
				var val = {"id":"0","pId":"0","name":"请选择","moduleLv":null,"open":false,"isParent":true,"checked":false};
				var data = eval("(" + value + ")");
				data.unshift(val);
				$.each(data, function(index, valueTmp) {
					valueTmp.icon = ctp() + "/res/plugin/zTree3.5/css/zTreeStyle/img/btn_1.png";
					if(settings.enableBatch) {
						if (settings.selectedItemId != null && settings.selectedItemId != "") {
							if (settings.selectedItemId.indexOf(valueTmp.id) != -1){
								valueTmp.checked = true;
							}
						}
					}
				});
				$.fn.zTree.init($("#treeDept"), setting, data);
				zTree = $.fn.zTree.getZTreeObj("treeDept");
                var va = '${publicClass.typeId}';
                if (va == "1"){
                    $("#grade").attr("data-id","");
                    zTree.checkAllNodes(true);
                } else if(va != null && va != '' && va != undefined){
                    var gradeIds = '${publicClass.gradeIds}';
                    if(gradeIds != null && gradeIds != '' && gradeIds != undefined){
                        var arr = gradeIds.split(',');
                        var dataId = "";
                        var span = "";
                        for (var i = 0; i < arr.length; i++) {
                            var node = zTree.getNodeByParam("id", arr[i]);//根据ID找到该节点
                            zTree.checkNode(node);//根据该节点选中
                            dataId += node.id + ",";
                            span += node.name +","
                        }
                        if (dataId.length > 0) {
                            dataId = dataId.substr(0,dataId.length - 1);
                        }
                        if (span.length > 0) {
                            span = span.substr(0,span.length - 1);
                        }
                        if (span == ""){
                            span = "请选择";
                        }
                        $("#dept_selected_name_span").text(span);
                        $("#grade").attr("data-id",dataId);
                    }

                }

			});
		}

		var execute = function() {
		 	$(settings.deptContainer).html('<div id="bumen_chzn" class="chzn-container chzn-container-single" style="width: 220px;" title=""><a tabindex="-1" id="chzn-single" class="chzn-single" href="javascript:void(0)"><span id="dept_selected_name_span">请选择</span><div><b></b></div></a><div class="chzn-drop" style="left: -9000px; width: 218px; top: 30px;"><ul id="treeDept" class="ztree"></ul></div></div>');
		 	initModuleTree();




            $("#type").on("change", function(){
                var va = $("#type option:selected").val();
                var treeObj = $.fn.zTree.getZTreeObj("treeDept");
                if (va == "1"){
                    $("#grade").attr("data-id","");
                    treeObj.checkAllNodes(true);
                } else {
                    $("#grade").attr("data-id","");
                    treeObj.checkAllNodes(false);
                    $("#dept_selected_name_span").text("请选择");
                }
            })



		 	$("#bumen_chzn #chzn-single").bind("click", function(event){
                var va = $("#type option:selected").val();
                // 学校风采，对应所有课程
                if (va == "1"){
                    // 清空全部选中的节点
                    var treeObj = $.fn.zTree.getZTreeObj("treeDept");
                    $("#grade").attr("data-id","");
                    treeObj.checkAllNodes(true);
                    return;
                }
		 		var $this = $(this);
		 		$this.toggleClass("chzn-single-with-drop");
				var left_size = $this.next().css("left") == "-9000px" ? '0px' : '-9000px';
				var isOpen = $this.attr("data-is-open");
				isOpen = isOpen != null && isOpen != "undefined" && "true" === isOpen ? "false" : "true";
				$this.attr("data-is-open", isOpen);
				$this.next().css("left",left_size);
				$this.parent().toggleClass("chzn-container-active");
				event.stopPropagation();
			});


		}
		execute();
	};
})(jQuery);
</script>