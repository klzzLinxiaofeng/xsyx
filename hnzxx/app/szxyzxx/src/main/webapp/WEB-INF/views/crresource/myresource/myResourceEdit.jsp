<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css" rel="stylesheet"/>
<!--[if IE 7]>
                  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
                <![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js" type="text/javascript"></script>
        <title>编辑</title>
        <style>
            .myerror {
                color: red !important;
                display: inline-block;
                padding-left: 10px;
            }
             #dvTextBookCatalog select{margin-right:10px;}
        </style>
    </head>
    <body style="background-color: #F3F3F3 !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 0;" class="widget-container">
                        <form id="microForm" class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" style="width: 80px;"><font style="color:red">*</font>标题： </label>
                                <div class="controls" style="margin-left: 100px;">
                                    <input type="text" name="title" id="title" value="${item.title}" class="span5" style="width: 550px;">
                                </div>
                            </div>
<%--                            <div class="control-group">--%>
<%--                                <label class="control-label" style="width: 80px;"> 简介： </label>--%>
<%--                                <div class="controls" style="margin-left: 100px;">--%>
<%--                                    <textarea name="description" id="description" class="span5" style="width: 680px; height: 320px;">${item.description}</textarea>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <c:if test="${personType eq 'res_person' and  item.resType==4}">--%>
<%--                          <div class="control-label" style="display: none;" >--%>
<%--                    <label class="control-label" style="width: 80px;"><font style="color:red">*</font>科目： </label>     --%>
<%--                    <div class="controls" style="margin-left: 100px; " >--%>
<%--                    <select id="subjectSelect">--%>
<%--                              --%>
<%--                              <c:forEach items="${slist}"  var="item">--%>
<%--                              <option  value="${item.code}"--%>
<%--                               <c:if test="${item.code  eq code}">--%>
<%--                               selected="selected"--%>
<%--                               </c:if>--%>
<%--                              >--%>
<%--                              ${item.name}--%>
<%--                              </option>--%>
<%--                              --%>
<%--                              </c:forEach>--%>
<%--                              </select>--%>
<%--                              </div>--%>
<%--                            </div> --%>
<%--                            </c:if>--%>
                            
<%--                <c:if test="${personType != 'res_person'}">--%>
<%--                <input id="isPerson" type="hidden" value="yes"> --%>
<%--                <div class="control-group" id="upload_textbook" hidden="true">--%>
<%--                <label class="control-label" style="width:80px;">--%>
<%--                    <span class="red">*</span> 教材：--%>
<%--                </label>--%>
<%--                <div class="controls" style="margin-left: 100px;">--%>
<%--                    <jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include>	--%>
<%--                </div>--%>
<%--               </div>--%>
<%--                <div class="control-group" id="upload_textbookcatalog" hidden="true">--%>
<%--                    <label class="control-label" style="width:80px;">--%>
<%--                        <span class="red">*</span> 目录：--%>
<%--                    </label>--%>
<%--                    <div id="dvTextBookCatalog" class="select_div" style="margin-left: 100px;">--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--                            --%>
<%--                            </c:if>--%>
                            <!--                            <div class="control-group">
                                                            <label class="control-label" style="width: 80px;"> 知识点： </label>
                                                            <div class="controls" style="margin-left: 100px;">
                                                                <select style="width: 134px;"><option>五年级</option>
                                                                    <option>六年级</option></select> <select style="width: 134px;"><option>语文</option>
                                                                    <option>数学</option></select> <select style="width: 134px;"><option>上册</option>
                                                                    <option>下册</option></select> <select style="width: 134px;"><option>人教版</option>
                                                                    <option>北师大</option></select>
                                                            </div>
                                                        </div>-->
                            <!--						<div class="control-group">
                                                                                    <label class="control-label" style="width: 80px;"> 关键字： </label>
                                                                                    <div class="controls" style="margin-left: 100px;">
                                                                                            <input type="text" value="" placeholder="微课" class="span5"
                                                                                                    style="width: 550px;">
                                                                                    </div>
                                                                            </div>-->
                        </form>
                        <div class="form-actions tan_bottom">
                            <button class="btn btn-warning" onclick="save();" type="button">确认</button>
                            <button class="btn" onclick="$.closeWindow();" type="button">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
    var catalogEnd ;
        $(function () {
        	
        	
//             initValidator();
            if($('#isPerson').val()=='yes'){
            		  uploadShare();
            		  initSelect();
            }
       
<%--//         <c:if test='${entity.extension eq "doc" ||entity.extension eq "docx"}'>--%>
            //编辑器基本按钮数组
//             var baseEditorItems = ["source", "fullscreen", "undo", "redo", "cut", "copy", "paste", "plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "clearhtml", "quickformat", "|",
//                 "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "removeformat", "|",
//                 "emoticons","image", "table", "hr", "pagebreak"];
//             //编辑器基本配置参数
//             var baseEditorSetting = {items: baseEditorItems, filterMode: false, resizeType: 0, filePostName: "file", uploadJson: "${pageContext.request.contextPath}/lads/ke/upload"};
//             //$("#description").css("width", "300px");
//             var focusEditor = KindEditor.create('textarea[name="description"]', baseEditorSetting);
//             focusEditor.html();
//             focusEditor.focus();
<%--//         </c:if>--%>
			KindEditor.ready(function(K) {
				editor = K.create('textarea[id="description"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
                    afterBlur:function () {
                        this.sync();
                    },
					 
					items : [ 'fontname', 'fontsize', '|', 'forecolor',
								'hilitecolor', 'bold', 'italic', 'underline',
								'removeformat', '|', 'justifyleft', 'justifycenter',
								'justifyright', 'insertorderedlist',
								'insertunorderedlist', '|', 'emoticons', 'image',
								'link' ],
				});
				K('input[name=getHtml]').click(function(e) {
					alert(editor.html());
				});
				K('input[name=isEmpty]').click(function(e) {
					alert(editor.isEmpty());
				});
				K('input[name=getText]').click(function(e) {
					alert(editor.text());
				});
				K('input[name=selectedHtml]').click(function(e) {
					alert(editor.selectedHtml());
				});
				K('input[name=setHtml]').click(function(e) {
					editor.html('<h3>Hello KindEditor</h3>');
				});
				K('input[name=setText]').click(function(e) {
					editor.text('<h3>Hello KindEditor</h3>');
				});
				K('input[name=insertHtml]').click(function(e) {
					editor.insertHtml('<strong>插入HTML</strong>');
				});
				K('input[name=appendHtml]').click(function(e) {
					editor.appendHtml('<strong>添加HTML</strong>');
				});
				K('input[name=clear]').click(function(e) {
					editor.html('');
				});
			});
        });
        function uploadShare() {
    	  	//资源共享则显示教材目录
    	  		$("#upload_textbook").show();
    	  		$("#upload_textbookcatalog").show();
    	 }
        
        function initSelect(){
        	$("#stageCode").val('${cr.stageCode}');
        	findTextBook('subjectCode');
        	$("#subjectCode").val('${cr.subjectCode}');
        	findTextBook('publisherId');
        	$("#publisherId").val('${cr.versionCode}');
        	findTextBook('gradeCodeVolumn');
        	$("#gradeCodeVolumn").val('${cr.gradeCode}'+"-"+'${cr.volumnCode}');
        	
        	if('${cr.catalogCode}'!="") {
        		var level = '${textbookCatalog.level}'
    			$.ajax({
    				type : 'post',
    				url : "${ctp}/teach/textBookMaster/master/resTextCatalogList/"+'${cr.catalogCode}',
    				cache : false,
    				data : {},
    				dataType : 'json',
    				success : function(data) {//roomList
    					for(var i=0; i<=level; i++) {
    						jQuery.each(data, function(index, item) {
    							if (item.level==i) {
    								if(item.selected==1) {
    									if(i==0) {
    										findResTextBookCatalog($("#gradeCodeVolumn"), '0', '0');
    										$("#catalog1").val(item.id);
    									} else {
    										findResTextBookCatalog($("#catalog"+i), '1', i);
    										$("#catalog"+(i+1)).val(item.id);
    									}
    								}
    							}
    						});
						}
    				},
    				error : function() {
    					return;
    				}
    			});
        	}
        }
        
        function onSaveMicroSubmit() {
            var title = $.trim($("#title").val());
            var titleLength=title.trim().length;
            var catalog1 = $("#catalog1").val();
            var uploadMicroDescription = $("#description").val();
            var textbookId = catalog1;
           // var detailLength = uploadMicroDescription.trim().length;
            if (title == null || title == "") {
                $.alert("请输入资源标题");
                return false;
            }
            // if (detailLength > 500) {
            //     $.alert("简介不能超过500");
            //     return false;
            // }
            if (titleLength > 50) {
                $.alert("标题不能超过50字");
                return false;
            }

            return true;
        }



        function save() {
        	 if (onSaveMicroSubmit()) {
                var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
                var personType="${personType}";
                var resType="${item.resType}";
                var grade;
                var volumn;
                if(personType==='res_share'||personType==='res_school'){
                    var aa = $("#dvTextBookCatalog").children("select").length;
                    var catalogTemp;
                    for (var num = aa; num >= 1; num--) {
                        var catalogname = "catalog" + num

                        catalogTemp = $("#" + catalogname).val();

                        if (Number(catalogTemp) > 0) {
                            catalogEnd = catalogTemp;
                            catalogEnd = $("#" + catalogname).find("option:selected").attr("data-code");

                            break;
                        } else {
                            catalogEnd = 0;
                        }
                        if (textbookId == null || textbookId == "") {
                            $.alert("请输入目录");
                            return false;
                        }
                        if(catalog1 == null ||catalog1 == ""){
                    		 $.alert("请选择目录");
                    		 return false;
                    	}
                    }
//                     if (aa == 1) {
//                         catalogEnd = 0;
//                     }	
                var array = gradeCodeVolumnValue.split("-");

                if (array.length == 2) {
                    grade = array[0];
                    volumn = array[1];
                  }
                }
                if(resType==="4"){
                	
                if(personType==='res_share'||personType==='res_school'){
                	var subjectCode= $("#subjectCode").val();
//                 	if(subjectCode!=="${code}"){
//                 		$.error("分享的资源科目与个人资源不符，请修改对应个人资源科目");
//                 	}
                }else{
                	var subjectCode=$('#subjectSelect option:selected').val();
                }
                }
                //,"stage":ca.stage,"subject":ca.subject,"grade":ca.grade,"publish":ca.publish,"volumn":ca.volumn,"catalog":ca.catalog
                KindEditor.sync("textarea[id='description']");
                $.ajax({
                    url: "${pageContext.request.contextPath}/crresource/saveOrUpdate",
                    type: "POST",
                    data:
                            {   "title": $("#title").val(),
                                "description": $("#description").val(),
                                "stageCode": $("#stageCode").val(),
                                "subjectCode": subjectCode,
                                "gradeCode": grade,
                                "version": $("#publisherId").val(),
                                "volumn": volumn,
                                "stageName": $("select[id=stageCode] option:selected").text(),
                                "subjectName": $("select[id=subjectCode] option:selected").text(),
                                "versionName": $("select[id=publisherId] option:selected").text(),
                                "textbookId": $("#catalog1").val(),
                                "catalogEnd": catalogEnd,
                                "personType":personType,
                                "id":"${id}"
                            },
                    async: false,
                    success: function (mid) {
                            //上传时的操作
                            $.alert("编辑成功");
                            if("${personType}"!='res_school'){
                        		
                            	parent.core_iframe.location.href = "${pageContext.request.contextPath}/crresource/myResource?resType=${resType}&index=index&personType=res_person";
                            	}else{
                            		parent.core_iframe.location.href = "${pageContext.request.contextPath}/crresource/myResource?resType=${resType}&index=index&personType=res_school";
                            	}
                       	 $.closeWindow();
                        }
                });
            }
        }
    </script>
</html>