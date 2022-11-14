<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author 陈业强
//@date 2015-08-27
(function($){
	$.createCatalogSelector = function (options) {
		var defaults = {
			"catalogContainer" : "#catalog",
			"knowledgeVersionCode" : "",
			"clickCallback" : function() {},
			"selectedItemTitle" : "",
			"selectedItemId" : "",
			"enableBatch" : false
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
					return ctp() + "/teach/catalog/tree/json?" + param;
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
						ajaxGetNodes(treeNode, "refresh");
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
						$("#catalog_selected_name_span").text(name);
						$(settings.catalogContainer).attr("data-id", id);
						alert(id);
					}
					settings.clickCallback();
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
				var catalogName = $("#catalog_selected_name_span").text();
				var id = $(settings.catalogContainer).attr("data-id");
				id = id != null && id != "undefined" ? id : "";
				if (treeNode.checked) {
					$("#catalog_selected_name_span").text(("请选择" === catalogName || "" === catalogName ? "" : (catalogName + ",")) + treeNode.name);
					$(settings.catalogContainer).attr("data-id", ("" === id ? "" : (id + ",")) + treeNode.id);
				} else {
					catalogName = catalogName.replace(treeNode.name, "").replace(",,", ",");
					id = id.replace(treeNode.id, "").replace(",,", ",");
					if (catalogName.startWith(",")) {
						catalogName = catalogName.substring(1, catalogName.length);
						id = id.substring(1, id.length);
					} 
					
					if (catalogName.endWith(",")) {
						catalogName = catalogName.substring(0, catalogName.length - 1);
						id = id.substring(0, id.length - 1);
					}
					
					if ("" === catalogName) {
						catalogName = "请选择";
						id = "";
					}
					
					$("#catalog_selected_name_span").text(catalogName);
					$(settings.catalogContainer).attr("data-id", id);
				}

			}
			
			setting.callback.onClick = function() {
				
			}
		}
		
		
		
		var initModuleTree = function() {
			var requestData = {};
			if(settings.knowledgeVersionCode != null && settings.knowledgeVersionCode != ""){
				requestData.knowledgeVersionCode = settings.knowledgeVersionCode;
			}
			$.get(ctp() + "/teach/catalog/tree/json", requestData,
			function(value, status) {
				var data = eval("(" + value + ")");
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
				$.fn.zTree.init($("#treecatalog"), setting, data);
				zTree = $.fn.zTree.getZTreeObj("treecatalog");
			});
		}
		
		var execute = function() {
		 	$(settings.catalogContainer).html('<div id="bumen_chzn" class="chzn-container chzn-container-single" style="width: 220px;" title=""><a tabindex="-1" id="chzn-single" class="chzn-single" href="javascript:void(0)"><span id="catalog_selected_name_span">请选择</span><div><b></b></div></a><div class="chzn-drop" style="left: -9000px; width: 218px; top: 30px;"><ul id="treecatalog" class="ztree"></ul></div></div>');
		 	initModuleTree();
		 	
		 	$("#bumen_chzn #chzn-single").bind("click", function(event){
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
		 	
		 	$(settings.catalogContainer).find("#treecatalog").bind("click", function(event) {
		 		event.stopPropagation();
		 	});
		 	
		 	$(document).bind("click", function(event) {
		 		var $this = $(settings.catalogContainer).find("#bumen_chzn #chzn-single");
		 		var isOpen = $this.attr("data-is-open");
		 		if(isOpen != null && isOpen != "undefined" && "true" === isOpen) {
			 		$("#treecatalog").parent().css("left", "-9000px");
			 		$this.attr("data-is-open", "false");
			 		$this.removeClass("chzn-single-with-drop");
			 		$this.parent().removeClass("chzn-container-active");
		 		}
		 		event.stopPropagation();
		 	});
		 	
		 	if (settings.selectedItemId != null && settings.selectedItemId != "") {
				$(settings.catalogContainer).attr("data-id", settings.selectedItemId);
				if (settings.selectedItemTitle != null && settings.selectedItemTitle != "") {
					$("#catalog_selected_name_span").text(settings.selectedItemTitle);
			 	} else {
			 		var requestData = {"id" : settings.selectedItemId};
					$.get(ctp() + "/teach/catalog/tree/json", requestData, function(value, status) {
						var data = eval("(" + value + ")");
						$("#catalog_selected_name_span").text(data[0].name);
					});
			 	}
		 	}
		 	
		}
		execute();
	};
})(jQuery);
</script>