<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<title>预览</title>
<style>
	.first_creat{
	    text-align: center;
    line-height: 463px;
    font-size: 20px;
    font-family: "微软雅黑";}
</style>
</head>
<body>
<div class="container-fluid dxa">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
					<h3>
						<i class="fa fa-asterisk"></i>预览模式-<span class="lpTitle">${title}</span>
						<p class="btn_link" style="float: right;">
							<c:if test="${editable }">
								<c:if test="${!published }">
									<a onclick="edit();" class="a4" href="javascript:void(0)"><i class="fa fa-eye"></i>编辑</a>
								</c:if>
								<c:choose>
									<c:when test="${fn:length(catalogList)>0 }">
										<a class="a2" href="javascript:void(0)" onclick="publish();"><i class="fa fa-file-text"></i>布置</a>
									</c:when>
									<c:otherwise>
										<a class="a2" href="javascript:void(0)" onclick="publish();" style="display: none"><i class="fa fa-file-text"></i>布置</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</p>
					</h3>
				</div>
				<div class="content-widgets cr_grey">
					<div class="creat_dxa">
						<div class="c_dxa_left">
							
							<c:choose>
								<c:when test="${fn:length(catalogList)>0 }">
									<div class="first_creat" style="display:none"><a href="javascript:void(0)"><i></i>创建分组文件夹</a></div>
								</c:when>
								<c:otherwise>
									<div class="first_creat" >暂无分组信息</div>
								</c:otherwise>
							</c:choose>
							
							<div class="second_creat" style="display:none">
								<p><span>自主预习</span><input type="text" value="自主预习"></p>
								<a href="javascript:void(0)">我知道了</a>
							</div>
							
							<c:choose>
								<c:when test="${fn:length(catalogList)>0 }">
									<div class="three_creat">
										<div class="c_top" style="height:745px">
											<c:forEach items="${catalogList }" var="catalog" varStatus="catalogStatus">
												<c:if test="${catalogStatus.first}">
													<div class="folder_div on">
													<a href="javascript:void(0)" class="hide_div" style="display:block"></a>
														<p class="top"><span>${catalog.title }</span><input type="text" data-catalogid="${catalog.id }" value="${catalog.title }"></p>
														<ul>
															<c:forEach items="${catalog.lpUnitList }" var="unit" varStatus="unitStatus">
																<c:if test="${unit.unitType==1 }">
																	<li class='a1' data-id='${catalog.id }${unit.id }'><p><i></i><span >${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==2 }">
																	<li class='a2' data-id='${catalog.id }${unit.id }'><p><i></i><span >${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==3 }">
																	<li class='a3' data-id='${catalog.id }${unit.id }'><p><i></i><span >${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==4 }">
																	<li class='a3' data-id='${catalog.id }${unit.id }'><p><i></i><span >${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==11 }">
																	<li class='a11' data-id='${catalog.id }${unit.id }'><p><i></i><span >${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
															</c:forEach>
														</ul>
													</div>
												</c:if>
												<c:if test="${!catalogStatus.first}">
													<div class="folder_div">
													<a href="javascript:void(0)" class="hide_div" style="display:block"></a>
														<p class="top"><span>${catalog.title }</span><input type="text" data-catalogid="${catalog.id }" value="${catalog.title }"></p>
														<ul>
															<c:forEach items="${catalog.lpUnitList }" var="unit" varStatus="unitStatus">
																<c:if test="${unit.unitType==1 }">
																	<li class='a1' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==2 }">
																	<li class='a2' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==3 }">
																	<li class='a3' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==4 }">
																	<li class='a3' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
																<c:if test="${unit.unitType==11 }">
																	<li class='a11' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
																</c:if>
															</c:forEach>
														</ul>
													</div>
												</c:if>
											</c:forEach>
											
										</div>
								</c:when>
								<c:otherwise>
									<div class="three_creat" style="display:none">
										<div class="c_top">
											<div class="folder_div on">
												<p class="top"><span></span><input type="text" value=""></p>
											</div>
										</div>
								</c:otherwise>
							</c:choose>
							</div>
						</div>
						<div class="c_dxa_right">
							<div class="right_ts" style="display:none"><i></i><p>请点击左侧分组下方创建单元内容</p></div>
							<div class="right_ts_1"><i></i><p>请选中左侧分组或内容</p></div>
							<div class="nr_div">
								<c:forEach items="${catalogList }" var="catalog" varStatus="catalogStatus">
									<c:forEach items="${catalog.lpUnitList }" var="unit" varStatus="unitStatus">
										<c:forEach items="${contentList }" var="item">
											<c:choose>
												<c:when test="${item.unitType == '1' and item.unitId == unit.id}">
													<div class="zhishishuli" style="display: none" data-id="${item.catalogId }${item.unitId }", id="${item.catalogId }${item.unitId }">
														<div class="zssl_top">
															<span>${item.title }</span>
														</div>
														<div class="zssl_bottom">
															<textarea rows="" cols="" class="content" id="content_${item.unitId}">${item.content }</textarea>
														</div>
													</div>
												</c:when>
												<c:when test="${item.unitType == '11' and item.unitId == unit.id}">
													<div class="zhishishuli" style="display: none" data-id="${item.catalogId }${item.unitId }", id="${item.catalogId }${item.unitId }">
														<div class="zssl_top">
															<span>${item.title }</span>
														</div>
														<div class="zssl_bottom">
															<p class="jsts">教师提示/教师总结（选填）：</p>
															<textarea rows="" cols="" class="content" readonly="readonly" id="content_${item.unitId}" class="js_textarea">${item.content }</textarea>
														</div>
													</div>
												</c:when>
												<c:when test="${item.unitType == '2' and item.unitId == unit.id}">
													<div class="yuxizice" style="display: none" data-id="${item.catalogId }${item.unitId }" id="${item.catalogId }${item.unitId }">
									    				<div class="yxzc_top">
									    					<span class="d_title">${unit.title }</span>
									    					<c:if test="${item.title!=null }">
									    						<button class="btn btn-primary" onclick="previewExam(${item.paperId});">预览</button>
									    					</c:if>
									    				</div>
									    				<div class="yxzc_bottom" id="yxzc_bottom_${item.unitId }">
									    					<c:if test="${item.title!=null }">
										    					<ul>
																	<c:if test="${item.personal }">
																		<li>试卷名称：<span>${item.title }</span></li>
											    						<li>适用科目：<span>${item.subject }</span></li>
											    						<li>上传时间：<span><fmt:formatDate value="${item.crateTime }" pattern="yyyy-MM-dd" type="date"/></span></li>
											    						<li>试卷总分：<span>${item.score }</span></li>
																	</c:if>	
																	<c:if test="${!item.personal }">
																		<li>试卷名称：<span>${item.title }</span></li>
											    						<!-- <li>教材版本：<span>${item.version }</span></li> -->
											    						<li>适用科目：<span>${item.subject }</span></li>
											    						<!-- <li>适用年级：<span>${item.grade }</span></li>-->
											    						<li>上传时间：<span><fmt:formatDate value="${item.crateTime }" pattern="yyyy-MM-dd" type="date"/></span></li>
											    						<li>试卷总分：<span>${item.score }</span></li>
																	</c:if>								    				
										    					</ul>
									    					</c:if>
									    					<c:if test="${item.title==null }">
										    					<ul>
																	<li>试卷名称：<span>无</span></li>
										    						<!-- <li>教材版本：<span>无</span></li>-->
										    						<li>适用科目：<span>无</span></li>
										    						<!-- <li>适用年级：<span>无</span></li>-->
										    						<li>上传时间：<span>无</span></li>
										    						<li>试卷总分：<span>无</span></li>
										    					</ul>
									    					</c:if>
									    				</div>
									    			</div>
												</c:when>
												<c:when test="${item.unitType == '3' and item.unitId == unit.id}">
													<div class="weike_" style="display:none" data-id="${item.catalogId }${item.unitId }" id="${item.catalogId }${item.unitId }">
									    				<div class="wk_top">
									    					<span class="d_title">微课</span>
									    				</div>
									    				<div class="wk_bottom" >
									    					<p class="wk_tj">该子单元内已有 <span class="micronum_${item.unitId }">${item.size }</span>个微课</p>
									    					<div class="xkzy_list" id="xkzy_list_${item.unitId }">
									    						<c:forEach items="${item.microList }" var="micro">
											    					<c:if test="${micro.unitId == item.unitId}">
											    						<dl id="unit_file_${micro.lpUnitFileId}">
															                <dt>
															                	<a href="javascript:void(0)" onclick="previewMicro(${micro.id})">
															                        <img src="/cr/res/images/video.png">
															                    </a>
															                </dt>
															                <dd>
															                    <div class="item-msg">
															                        <div class="item-title">
															                            <span class="res-mp4 icon-file res-iconb"></span>
															                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
															                                <a href="javascript:void(0)" onclick="previewMicro(${micro.id})" title="${micro.title }">${micro.title }</a>
															                            </span>
															                        </div>
															                        <span class="i1">教材目录：${tbFn:getCatalogName(micro.catalogCode,'0')}  </span> 
															                        <div class="i1">上传时间：<fmt:formatDate value="${micro.createDate}" pattern="YYYY-MM-dd" /></div>
															                        <div class="cz_btn">
															                        	<button class="btn btn-primary" onclick="previewMicro(${micro.id})">预览</button>
															                          </div>
															                    </div>
							 								                </dd>
							 								            </dl>
							 								        </c:if>
					 								            </c:forEach>
									    					</div>
									    				</div>
									    			</div>
												</c:when>
												<c:when test="${item.unitType == '4' and item.unitId == unit.id}">
													<div class="weike_" style="display:none" data-id="${item.catalogId }${item.unitId }" id="${item.catalogId }${item.unitId }">
									    				<div class="wk_top">
									    					<span class="d_title">课件</span>
									    				</div>
									    				<div class="wk_bottom" >
									    					<p class="wk_tj">该子单元内已有 <span class="micronum_${item.unitId }">${item.size }</span>个课件</p>
									    					<div class="xkzy_list" id="xkzy_list_${item.unitId }">
									    						<c:forEach items="${item.microList }" var="micro">
											    					<c:if test="${micro.unitId == item.unitId}">
											    						<dl id="unit_file_${micro.lpUnitFileId}">
															                <dt>
															                	<a href="javascript:void(0)" onclick="previewMicro(${micro.id})">
															                        <img src="${thumbFn:getConvertedUrl(res.thumbnailUrl,micro.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
															                    </a>
															                </dt>
															                <dd>
															                    <div class="item-msg">
															                        <div class="item-title">
															                            <span class="res-mp4 icon-file res-iconb"></span>
															                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
															                                <a href="javascript:void(0)" onclick="previewMicro(${micro.id})" title="${micro.title }">${micro.title }</a>
															                            </span>
															                        </div>
															                        <span class="i1">教材目录：${tbFn:getCatalogName(micro.catalogCode,'0')}  </span> 
															                        <div class="i1">上传时间：<fmt:formatDate value="${micro.createDate}" pattern="YYYY-MM-dd" /></div>
															                        <div class="cz_btn">
															                        	<button class="btn btn-primary" onclick="previewMicro(${micro.id})">预览</button>
															                          </div>
															                    </div>
							 								                </dd>
							 								            </dl>
							 								        </c:if>
					 								            </c:forEach>
									    					</div>
									    				</div>
									    			</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:forEach>
								</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="nounit_div" style="display:none;">
	<p style="width: 236px;margin: 40px auto 0; text-align:center;">该导学案内有分组为空, 不能进行布置</p>
</div>
<script>
var i=0;
var editor;
var h = document.documentElement.clientHeight;
$(function(){
	createKE();
	
	$(".creat_btn a").click(function(){
		$(".right_ts").hide();
		$(".right_ts_1").show();
		$(".zhishishuli,.yuxizice,.weike_").hide();
	});
	
	$(".creat_dxa .c_dxa_left .second_creat a").click(function(){
		$(".creat_dxa .c_dxa_left .second_creat").hide();
		$(".creat_dxa .c_dxa_left .three_creat").show();
		
	})
	$(".three_creat .c_bottom .creat_btn a").click(function(){
		i=i+1;
	});
	
	//点击文件夹下面的单元
	$(".three_creat .c_top ").on("click",".folder_div ul li p",function(){
		//生成右边div
		$(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on");
		$(this).addClass("light_blue on");
		$(".folder_div p.top").removeClass("current");
		$(".folder_div p.top").removeClass("on");
		$(this).parent().parent().parent().addClass("on");
		$(".right_ts_1").hide();
		var div_id=$(this).parent().data("id");
		$(".nr_div").children().hide();
		$("#"+div_id).show();
	});
	
	
	//点击文件夹名字
	$(".three_creat .c_top").on("click",".folder_div p.top",function(){
		$(".three_creat .c_top .folder_div").removeClass("on");
		$(this).parent().addClass("on");
		$(".three_creat .c_top .folder_div ul li p").removeClass("on light_blue");
		$(".right_ts_1").show();
		$(".nr_div").children().hide();
	})
	
	//展开关闭
	$(".c_zhishi,.c_yuxi,.c_video").click(function(){
		if($(".folder_div.on").children("a").hasClass("hide_div")&&$(".folder_div.on").children("a").is(":hidden")){
			$(".folder_div.on").children(".hide_div").show();
		}
	});
	$("body").on("click",".hide_div",function(){
		$(this).siblings("ul").hide();
		$(this).removeClass("hide_div").addClass("show_div");
	});
	$("body").on("click",".show_div",function(){
		$(this).siblings("ul").show();
		$(this).removeClass("show_div").addClass("hide_div");
	});
	//屏幕高度自适应
	$(".c_dxa_left,.c_dxa_right,.first_creat,.right_ts,.right_ts_1,.zhishishuli, .yuxizice, .weike_").css("height",h-105);
	$(".three_creat .c_top").css("height",h-117);
	$(".zhishishuli .zssl_bottom").css("height",h-162);
	$(".yuxizice .yxzc_bottom").css("height",h-192);
	$(".weike_ .wk_bottom").css("height",h-192);
	
});

function createKE() {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[class="content"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			width:'99.5%',
			height:h-162,
			readonlyMode : true,
			items : [ 'fontname', 'fontsize', '|', 'forecolor',
						'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image',
						'link' ],
					afterChange : function() {
					$(".form-horizontal .textarea label").hide();
	
					var limitNum = 10000;  //设定限制字数
				    
				    if(this.count('text') > limitNum) {
			    	  	var  pattern = ('字数超过限制，请适当删除部分内容');
			    	
			          	//超过字数限制自动截取
			          	var strValue = editor.text();
			          	strValue = strValue.substring(0,limitNum);
			          	editor.text(strValue);      
			         	 }else{
			        	  
			        	  var result = limitNum - this.count('text'); 
			              pattern = '还可以输入' +  result + '字'; 
			          }
				      $('.word_surplus').html(pattern); //输入显示
				 }
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
			//editor.readonly();
	});
}

function previewMicro(mid) {
	var mes = "预览";
	$.initWinOnCurFromLeft(mes,'${pageContext.request.contextPath}/learningPlan/viewer?id='+ mid, '798', '570');
}

//布置导学案
function publish() {
	var id = "${lpId}";
	$.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/check",
        type: "POST",
        data: {"id":id},
        async: true,
        success: function(data) {
        	if(data==0) {
        		var warning = "该导学案内无分组, 不能进行布置";
        		$(".nounit_div").find("p").text(warning);
        		noUnitWindow();
        	} else if(data==1) {
        		var warning = "该导学案内有分组为空, 不能进行布置";
        		$(".nounit_div").find("p").text(warning);
        		noUnitWindow();
        	} else if(data==2) {
        		location.href="${pageContext.request.contextPath}/learningPlan/task/prepare?lpId=${lpId}";
        	} else {
        		$.alert("不存在此状态")
        	}
        }
    });
}

function noUnitWindow() {
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['370px', '192px'],
		  title: '提示', //不显示标题
		  content: $('.nounit_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
}

function preview() {
	location.href="${pageContext.request.contextPath}/learningPlan/edit?type=view&id=${lpId}";
}

function edit() {
	location.href="${pageContext.request.contextPath}/learningPlan/edit?id=${lpId}";
}

function previewExam(paperId) {
	if(paperId==null) {
		$.alert("暂无试卷信息");
		return false;
	} else {
		$.initWinOnCurFromLeft('预览试卷', '${pageContext.request.contextPath}/learningPlan/paper/viewer?back=0&paperId='+paperId, '800', '635');
	}
}

function returnView(jumpfrom) {
	if("edit"==jumpfrom) {
		location.href="${pageContext.request.contextPath}/learningPlan/edit?id=${lpId}";
	} else {
		window.history.go(-1);
	}
}
</script>
</body>

</html>