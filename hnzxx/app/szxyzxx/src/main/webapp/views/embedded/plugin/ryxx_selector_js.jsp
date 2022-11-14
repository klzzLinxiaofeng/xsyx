<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author 潘维良
//@date 2014-08-11
(function($){
	$.createMemberSelector = function (options) {
		var initWindow = function(options) {
			layer.open(options);
		}
		var initWindowOnTop = function(options) {
			top.layer.open(options);
		}

		var defaults = {
			"inputIdSelector" : "#bzr",
			"ry_type" : "teach", //teach, stu
			"clear_btn" : false,
			"isOnTopWindow" : false,
			"excludeSelf":false,//是否排除自己
			"selectSubject" : "",
			"selectSubjectForbidden" : false,//是否禁止选择科目
			"enableBatch" : true,
			"enableMultiCampus" : false,
		};
		var layerOpDef = {
			skin: 'layui-layer-lvse',
			type : 2,
			maxmin : false,
			shadeClose : true,
			title : "人员选择",
			shade : [ 0.1, '#fff' ],
			offset : [ '15px', '' ],
			area : [ "800px", "500px" ],
			shift : 'left'
		};
		var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
		var settings = $.extend({}, defaults, options || {});
		var execute = function() {
			var inputSelector = $(settings.inputIdSelector);
			if(inputSelector.length <= 0) {
				return;
			}
			var clone = inputSelector.clone(true);
			inputSelector.after(clone);
			var id = inputSelector.attr("id");
			var selectorId = id + "_select";
			inputSelector.attr("id", selectorId).attr("name", "");
			clone.attr("style", "display : none").attr("class", "").val(clone.attr("data-id"));
			clone.css("display", "none");
			var url = "${pageContext.request.contextPath}/common/ryxx/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch 
				+ "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
			layerSettings['content'] = url;
			inputSelector.bind("click", function(event) {
				if(settings.isOnTopWindow) {
					initWindowOnTop(layerSettings)
				} else {
					initWindow(layerSettings);
				}
				event.stopPropagation();
			});
			inputSelector.bind("keydown", function(event) {
				$(this).val("");
				event.stopPropagation();
			});
			inputSelector.bind("keyup", function(event) {
				$(this).val("");
				event.stopPropagation();
			});
			if (settings.close_btn) {
				var closeBtn = $('<abbr class="search-choice-close"></abbr>');
				var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");	
				clone.after(divParent);
				inputSelector.appendTo(divParent);
				clone.appendTo(divParent);
				closeBtn.appendTo(divParent);
				divParent.bind("click", function(event) {
					inputSelector.val("");
					clone.val("");
					event.stopPropagation();
				});
			}
			
		}
		execute();
	};
	
	$.createMemberSelectorByClass = function(options) {
		var initWindow = function(options) {
			layer.open(options);
		}
		
		var initWindowOnTop = function(options) {
			top.layer.open(options);
		}
		
		var defaults = {
			"inputClassSelector" : ".bzr",
			"ry_type" : "teach", //teach, stu
			"isOnTopWindow" : false,
			"enableBatch" : true,
			"excludeSelf":false,//是否排除自己
			"selectSubject" : "",
			"selectSubjectForbidden" : false,//是否禁止选择科目
			"enableBatch" : true,
			"enableMultiCampus" : false,
		};
		var layerOpDef = {
			skin: 'layui-layer-lvse',
			type : 2,
			maxmin :false,
			shadeClose : true,
			title : "人员选择",
			shade : [ 0.1, '#fff' ],
			offset : [ '15px', '' ],
			area : [ "800px", "500px" ],
			shift : 'left'
		};
		var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
		var settings = $.extend({}, defaults, options || {});
		var execute = function() {
			var inputSelectors = $(settings.inputClassSelector);
			if(inputSelectors.length <= 0) {
				return;
			}
			
			$.each(inputSelectors, function(index, value) {
				
				var inputSelector = $(value);
				var clone = inputSelector.clone(true);
				inputSelector.after(clone);
				var id = inputSelector.attr("id");
				var selectorId = id + "_select";
				inputSelector.attr("id", selectorId).attr("name", "");
				clone.attr("class", "").val(clone.attr("data-id"));
				clone.css("display", "none");
				inputSelector.bind("click", function(event) {
					layerSettings['content'] =  "${pageContext.request.contextPath}/common/ryxx/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch
					+ "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
// 					initWindow(layerSettings);
					if(settings.isOnTopWindow) {
						initWindowOnTop(layerSettings)
					} else {
						initWindow(layerSettings);
					}
					event.stopPropagation();
				});
				
				if (settings.close_btn) {
					var closeBtn = $('<abbr class="search-choice-close"></abbr>');
					var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");	
					clone.after(divParent);
					inputSelector.appendTo(divParent);
					clone.appendTo(divParent);
					closeBtn.appendTo(divParent);
					divParent.bind("click", function(event) {
						inputSelector.val("");
						clone.val("");
						event.stopPropagation();
					});
				}
				
				inputSelector.bind("keydown", function(event) {
					$(this).val("");
					event.stopPropagation();
				});
				
				inputSelector.bind("keyup", function(event) {
					$(this).val("");
					event.stopPropagation();
				});
			});
		}
		execute();
	}


    $.createTeacheinfoSelector = function (options) {
        var initWindow = function(options) {
            layer.open(options);
        }
        var initWindowOnTop = function(options) {
            top.layer.open(options);
        }

        var defaults = {
            "inputIdSelector" : "#bzr",
            "ry_type" : "teachInfo", //teach, stu
            "clear_btn" : false,
            "isOnTopWindow" : false,
            "excludeSelf":false,//是否排除自己
            "selectSubject" : "",
            "selectSubjectForbidden" : false,//是否禁止选择科目
            "enableBatch" : true,
            "enableMultiCampus" : false,
        };
        var layerOpDef = {
            skin: 'layui-layer-lvse',
            type : 2,
            maxmin : false,
            shadeClose : true,
            title : "人员选择",
            shade : [ 0.1, '#fff' ],
            offset : [ '15px', '' ],
            area : [ "800px", "500px" ],
            shift : 'left'
        };
        var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
        var settings = $.extend({}, defaults, options || {});
        var execute = function() {
            var inputSelector = $(settings.inputIdSelector);
            if(inputSelector.length <= 0) {
                return;
            }
            var clone = inputSelector.clone(true);
            inputSelector.after(clone);
            var id = inputSelector.attr("id");
            var selectorId = id + "_select";
            inputSelector.attr("id", selectorId).attr("name", "");
            clone.attr("style", "display : none").attr("class", "").val(clone.attr("data-id"));
            clone.css("display", "none");
            var url = "${pageContext.request.contextPath}/common/teacher/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch
                + "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
            layerSettings['content'] = url;
            inputSelector.bind("click", function(event) {
                if(settings.isOnTopWindow) {
                    initWindowOnTop(layerSettings)
                } else {
                    initWindow(layerSettings);
                }
                event.stopPropagation();
            });
            inputSelector.bind("keydown", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            inputSelector.bind("keyup", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            if (settings.close_btn) {
                var closeBtn = $('<abbr class="search-choice-close"></abbr>');
                var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");
                clone.after(divParent);
                inputSelector.appendTo(divParent);
                clone.appendTo(divParent);
                closeBtn.appendTo(divParent);
                divParent.bind("click", function(event) {
                    inputSelector.val("");
                    clone.val("");
                    event.stopPropagation();
                });
            }

        }
        execute();
    };
    $.createTimeSelector = function (options) {
        var initWindow = function(options) {
            layer.open(options);
        }
        var initWindowOnTop = function(options) {
            top.layer.open(options);
        }

        var defaults = {
            "inputIdSelector" : "#bzr",
            "ry_type" : "time", //teach, stu
            "clear_btn" : false,
            "isOnTopWindow" : false,
            "excludeSelf":false,//是否排除自己
            "selectSubject" : "",
            "selectSubjectForbidden" : false,//是否禁止选择科目
            "enableBatch" : true,
            "enableMultiCampus" : false,
        };
        var layerOpDef = {
            skin: 'layui-layer-lvse',
            type : 2,
            maxmin : false,
            shadeClose : true,
            title : "人员选择",
            shade : [ 0.1, '#fff' ],
            offset : [ '15px', '' ],
            area : [ "800px", "500px" ],
            shift : 'left'
        };
        var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
        var settings = $.extend({}, defaults, options || {});
        var execute = function() {
            var inputSelector = $(settings.inputIdSelector);
            if(inputSelector.length <= 0) {
                return;
            }
            var clone = inputSelector.clone(true);
            inputSelector.after(clone);
            var id = inputSelector.attr("id");
            var selectorId = id + "_select";
            inputSelector.attr("id", selectorId).attr("name", "");
            clone.attr("style", "display : none").attr("class", "").val(clone.attr("data-id"));
            clone.css("display", "none");
            var url = "${pageContext.request.contextPath}/common/time/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch
                + "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
            layerSettings['content'] = url;
            inputSelector.bind("click", function(event) {
                if(settings.isOnTopWindow) {
                    initWindowOnTop(layerSettings)
                } else {
                    initWindow(layerSettings);
                }
                event.stopPropagation();
            });
            inputSelector.bind("keydown", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            inputSelector.bind("keyup", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            if (settings.close_btn) {
                var closeBtn = $('<abbr class="search-choice-close"></abbr>');
                var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");
                clone.after(divParent);
                inputSelector.appendTo(divParent);
                clone.appendTo(divParent);
                closeBtn.appendTo(divParent);
                divParent.bind("click", function(event) {
                    inputSelector.val("");
                    clone.val("");
                    event.stopPropagation();
                });
            }

        }
        execute();
    };


    // 学生列表
    $.createStudentSelector = function (options) {
        var initWindow = function(options) {
            layer.open(options);
        }
        var initWindowOnTop = function(options) {
            top.layer.open(options);
        }

        var defaults = {
            "inputIdSelector" : "#bzr",
            "ry_type" : "stu", //teach, stu
            "clear_btn" : false,
            "isOnTopWindow" : false,
            "excludeSelf":false,//是否排除自己
            "selectSubject" : "",
            "selectSubjectForbidden" : false,//是否禁止选择科目
            "enableBatch" : true,
            "enableMultiCampus" : false,
        };
        var layerOpDef = {
            skin: 'layui-layer-lvse',
            type : 2,
            maxmin : false,
            shadeClose : true,
            title : "人员选择",
            shade : [ 0.1, '#fff' ],
            offset : [ '15px', '' ],
            area : [ "800px", "500px" ],
            shift : 'left'
        };
        var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
        var settings = $.extend({}, defaults, options || {});
        var execute = function() {
            var inputSelector = $(settings.inputIdSelector);
            if(inputSelector.length <= 0) {
                return;
            }
            var clone = inputSelector.clone(true);
            inputSelector.after(clone);
            var id = inputSelector.attr("id");
            var selectorId = id + "_select";
            inputSelector.attr("id", selectorId).attr("name", "");
            clone.attr("style", "display : none").attr("class", "").val(clone.attr("data-id"));
            clone.css("display", "none");
            var url = "${pageContext.request.contextPath}/common/student/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch
                + "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
            layerSettings['content'] = url;
            inputSelector.bind("click", function(event) {
                if(settings.isOnTopWindow) {
                    initWindowOnTop(layerSettings)
                } else {
                    initWindow(layerSettings);
                }
                event.stopPropagation();
            });
            inputSelector.bind("keydown", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            inputSelector.bind("keyup", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            if (settings.close_btn) {
                var closeBtn = $('<abbr class="search-choice-close"></abbr>');
                var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");
                clone.after(divParent);
                inputSelector.appendTo(divParent);
                clone.appendTo(divParent);
                closeBtn.appendTo(divParent);
                divParent.bind("click", function(event) {
                    inputSelector.val("");
                    clone.val("");
                    event.stopPropagation();
                });
            }

        }
        execute();
    };
    // 根据老师获取学生列表
    $.createTecherStudentSelector = function (options) {
        var initWindow = function(options) {
            layer.open(options);
        }
        var initWindowOnTop = function(options) {
            top.layer.open(options);
        }

        var defaults = {
            "inputIdSelector" : "#bzr",
            "ry_type" : "teacher_stu", //teach, stu teacher_stu_selector.jsp
            "clear_btn" : false,
            "isOnTopWindow" : false,
            "excludeSelf":false,//是否排除自己
            "selectSubject" : "",
            "selectSubjectForbidden" : false,//是否禁止选择科目
            "enableBatch" : true,
            "enableMultiCampus" : false,
        };
        var layerOpDef = {
            skin: 'layui-layer-lvse',
            type : 2,
            maxmin : false,
            shadeClose : true,
            title : "人员选择",
            shade : [ 0.1, '#fff' ],
            offset : [ '15px', '' ],
            area : [ "800px", "500px" ],
            shift : 'left'
        };
        var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
        var settings = $.extend({}, defaults, options || {});
        var execute = function() {
            var inputSelector = $(settings.inputIdSelector);
            if(inputSelector.length <= 0) {
                return;
            }
            var clone = inputSelector.clone(true);
            inputSelector.after(clone);
            var id = inputSelector.attr("id");
            var selectorId = id + "_select";
            inputSelector.attr("id", selectorId).attr("name", "");
            clone.attr("style", "display : none").attr("class", "").val(clone.attr("data-id"));
            clone.css("display", "none");
            var url = "${pageContext.request.contextPath}/common/student/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch
                + "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
            layerSettings['content'] = url;
            inputSelector.bind("click", function(event) {
                if(settings.isOnTopWindow) {
                    initWindowOnTop(layerSettings)
                } else {
                    initWindow(layerSettings);
                }
                event.stopPropagation();
            });
            inputSelector.bind("keydown", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            inputSelector.bind("keyup", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            if (settings.close_btn) {
                var closeBtn = $('<abbr class="search-choice-close"></abbr>');
                var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");
                clone.after(divParent);
                inputSelector.appendTo(divParent);
                clone.appendTo(divParent);
                closeBtn.appendTo(divParent);
                divParent.bind("click", function(event) {
                    inputSelector.val("");
                    clone.val("");
                    event.stopPropagation();
                });
            }

        }
        execute();
    };



    // 学生列表
    $.createStudentSelector2 = function (options) {
        var initWindow = function(options) {
            layer.open(options);
        }
        var initWindowOnTop = function(options) {
            top.layer.open(options);
        }

        var defaults = {
            "inputIdSelector" : "#bzr",
            "ry_type" : "stu", //teach, stu
            "clear_btn" : false,
            "isOnTopWindow" : false,
            "excludeSelf":false,//是否排除自己
            "selectSubject" : "",
            "selectSubjectForbidden" : false,//是否禁止选择科目
            "enableBatch" : true,
            "enableMultiCampus" : false,
        };
        var layerOpDef = {
            skin: 'layui-layer-lvse',
            type : 2,
            maxmin : false,
            shadeClose : true,
            title : "人员选择",
            shade : [ 0.1, '#fff' ],
            offset : [ '15px', '' ],
            area : [ "800px", "500px" ],
            shift : 'left'
        };
        var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
        var settings = $.extend({}, defaults, options || {});
        var execute = function() {
            var inputSelector = $(settings.inputIdSelector);
            if(inputSelector.length <= 0) {
                return;
            }
            var clone = inputSelector.clone(true);
            inputSelector.after(clone);
            var id = inputSelector.attr("id");
            var selectorId = id + "_select";
            inputSelector.attr("id", selectorId).attr("name", "");
            clone.attr("style", "display : none").attr("class", "").val(clone.attr("data-id"));
            clone.css("display", "none");
            var url = "${pageContext.request.contextPath}/common/student/selector?pageT=" + settings.ry_type + "&id=" + id + "&isTop=" + settings.isOnTopWindow + "&targetWN=" + window.name + "&enableBatch=" + settings.enableBatch
                + "&excludeSelf=" + settings.excludeSelf + "&selectSubject=" + settings.selectSubject + "&selectSubjectForbidden=" + settings.selectSubjectForbidden + "&enableMultiCampus=" + settings.enableMultiCampus;
            layerSettings['content'] = url;
            inputSelector.bind("click", function(event) {
                if(settings.isOnTopWindow) {
                    initWindowOnTop(layerSettings)
                } else {
                    initWindow(layerSettings);
                }
                event.stopPropagation();
            });
            inputSelector.bind("keydown", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            inputSelector.bind("keyup", function(event) {
                $(this).val("");
                event.stopPropagation();
            });
            if (settings.close_btn) {
                var closeBtn = $('<abbr class="search-choice-close"></abbr>');
                var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");
                clone.after(divParent);
                inputSelector.appendTo(divParent);
                clone.appendTo(divParent);
                closeBtn.appendTo(divParent);
                divParent.bind("click", function(event) {
                    inputSelector.val("");
                    clone.val("");
                    event.stopPropagation();
                });
            }

        }
        execute();
    };


})(jQuery); 
</script>