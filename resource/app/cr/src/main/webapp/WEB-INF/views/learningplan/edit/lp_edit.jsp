<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
     contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@include file="/views/embedded/plugin/jquery.jsp" %>
<!-- 系统样式框架 相关样式以及js -->
<%@include file="/views/embedded/plugin/falgun.jsp" %>
<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<!-- 分页组件 -->
<%@include file="/views/embedded/plugin/pagination.jsp" %>
<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>编辑</title>
<style>
     .xkzy_list dl dd .cz_btn{top:48px;}
     .xkzy_list dl dd .item-msg .i1{   
     margin-right: 119px;   
     overflow: hidden;
    height: 75px;
    display: block;}
    ::-ms-clear, ::-ms-reveal{display: none;}
    .form-horizontal .controls {
    margin-left: 137px;
}
.form-horizontal .control-label {
    width: 130px;
}
.layui-layer-btn .layui-layer-btn0{
     background: #0d7bd5;
     box-shadow: none;
}
.layui-layer-btn .layui-layer-btn1{
     background: #d0cfd6;
     box-shadow: none;
}
.layui-layer-btn a{
     width: 84px;
    height: 34px;
    line-height: 34px;
}
.layui-layer-title{
     background-color: #0d7bd5;
    color: #fff;
    font-weight: bold;
}
.layui-layer-btn{
     margin-bottom: 18px;
}
</style>
</head>
<body>
<div class="container-fluid dxa">
     <div class="row-fluid">
           <div class="span12">
                <div class="content-widgets white" style="margin:0">
                     <div class="widget-head">
                           <h3>
                                <i class="fa fa-asterisk"></i>编辑模式-<span class="lpTitle">${title}</span>
                                <p class="btn_link" style="float: right;">
                                     <a href="javascript:void(0)" class="a3" onclick="lpProperties(${lpId});"><i class="fa  fa-info-circle"></i>属性</a>
                                     <a onclick="preview();" class="a4" href="javascript:void(0)"><i class="fa fa-eye"></i>预览</a>
                                     <c:choose>
                                           <c:when test="${fn:length(catalogList)>0 }">
                                                <a class="a2" href="javascript:void(0)" onclick="publish();"><i class="fa fa-file-text"></i>布置</a>
                                           </c:when>
                                           <c:otherwise>
                                                <a class="a2" href="javascript:void(0)" onclick="publish();" style="display: none"><i class="fa fa-file-text"></i>布置</a>
                                           </c:otherwise>
                                     </c:choose>
                                     </p>
                           </h3>
                     </div>
                     <div class="content-widgets cr_grey">
                           <div class="creat_dxa">
                                <div class="c_dxa_left">
                                     <c:choose>
                                           <c:when test="${fn:length(catalogList)>0 }">
                                                <div class="first_creat" style="display:none"><a href="javascript:void(0)" class="createFolder"><i></i>创建分组文件夹</a>
                                                     <a href="javascript:void(0)" onclick="daoru()" class="daoru">导入模板</a>
                                                </div>
                                           </c:when>
                                           <c:otherwise>
                                                <div class="first_creat" ><a href="javascript:void(0)" class="createFolder"><i></i>创建分组文件夹</a>
                                                     <a href="javascript:void(0)" onclick="daoru()" class="daoru">导入模板</a>
                                                </div>
                                           </c:otherwise>
                                     </c:choose>
                                     
                                     <div class="second_creat" style="display:none">
                                           <p><span>自主预习</span><input type="text" value="自主预习"></p>
                                           <div class="i_know"></div>
                                           <a href="javascript:void(0)">我知道了</a>
                                     </div>
                                     
                                     <c:choose>
                                           <c:when test="${fn:length(catalogList)>0 }">
                                                <div class="three_creat">
                                                <b class="rename_ts"></b>
                                                     <div class="c_top">
                                                          <c:forEach items="${catalogList }" var="catalog" varStatus="catalogStatus">
                                                                <c:if test="${catalogStatus.first}">
                                                                      <div class="folder_div on">
                                                                           <a href="javascript:void(0)" class="hide_div" style="display:block"></a>
                                                                           <p class="top"><span>${catalog.title }</span><input type="text" data-catalogid="${catalog.id }" maxlength="8" value="${catalog.title }"></p>
                                                                           <ul>
                                                                                <c:forEach items="${catalog.lpUnitList }" var="unit" varStatus="unitStatus">
                                                                                      <c:if test="${unit.unitType==1 }">
                                                                                           <li class='a1' data-id='${catalog.id }${unit.id }'><p  ><i></i><span >${unit.title }</span><input type="text" value='${unit.title }' maxlength='8' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==2 }">
                                                                                           <li class='a2' data-id='${catalog.id }${unit.id }'><p ><i></i><span >${unit.title }</span><input type="text" value='${unit.title }' maxlength='8' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==3 }">
                                                                                           <li class='a3' data-id='${catalog.id }${unit.id }'><p  ><i></i><span >${unit.title }</span><input type="text" value='${unit.title }' maxlength='8' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==4 }">
                                                                                           <li class='a12' data-id='${catalog.id }${unit.id }'><p  ><i></i><span >${unit.title }</span><input type="text" value='${unit.title }' maxlength='8' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==11 }">
                                                                                           <li class='a11' data-id='${catalog.id }${unit.id }'><p  ><i></i><span >${unit.title }</span><input type="text" value='${unit.title }' maxlength='8' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                </c:forEach>
                                                                           </ul>
                                                                      </div>
                                                                </c:if>
                                                                <c:if test="${!catalogStatus.first}">
                                                                      <div class="folder_div">
                                                                      <a href='javascript:void(0)' class='hide_div' style="display:block"></a>
                                                                           <p class="top"><span>${catalog.title }</span><input type="text" maxlength="8" data-catalogid="${catalog.id }" value="${catalog.title }"></p>
                                                                           <ul>
                                                                                <c:forEach items="${catalog.lpUnitList }" var="unit" varStatus="unitStatus">
                                                                                      <c:if test="${unit.unitType==1 }">
                                                                                           <li class='a1' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input type="text" maxlength='8' value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==2 }">
                                                                                           <li class='a2' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input type="text" maxlength='8' value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==3 }">
                                                                                           <li class='a3' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input type="text" maxlength='8' value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==4 }">
                                                                                           <li class='a12' data-id='${catalog.id }${unit.id }'><p  ><i></i><span >${unit.title }</span><input type="text" value='${unit.title }' maxlength='8' data-unitId='${unit.id }' style='display:none'></p></li>
                                                                                      </c:if>
                                                                                      <c:if test="${unit.unitType==11 }">
                                                                                           <li class='a11' data-id='${catalog.id }${unit.id }'><p><i></i><span>${unit.title }</span><input type="text" maxlength='8' value='${unit.title }' data-unitId='${unit.id }' style='display:none'></p></li>
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
                                                           <a href='javascript:void(0)' class='hide_div'></a>
                                                                <p class="top"><span></span><input type="text" maxlength="8" value=""></p>
                                                          </div>
                                                     </div>
                                           </c:otherwise>
                                     </c:choose>
                                           <div class="c_bottom">
                                                <div class="creat_btn">
                                                     <a href="javascript:void(0)" class="c_folde"><div class="btn_tishi">新建文件夹</div></a>
                                                     <a href="javascript:void(0)" class="c_zhishi"><div class="btn_tishi">新建文本单元</div></a>
                                                     <a href="javascript:void(0)" class="c_yuxi"><div class="btn_tishi">新建试卷单元</div></a>
                                                     <a href="javascript:void(0)" class="c_video"><div class="btn_tishi">新建微课单元</div></a>
                                                     <a href="javascript:void(0)" class="c_zja"><div class="btn_tishi">新建总结案单元</div></a>
                                                     <a href="javascript:void(0)" class="c_kejian"><div class="btn_tishi">新建课件单元</div></a>
                                                </div>
                                                <div class="cz_btn">
                                                     <a href="javascript:void(0)" class="cz_up">上移</a>
                                                     <a href="javascript:void(0)" class="cz_down">下移</a>
                                                     <a href="javascript:void(0)" class="cz_rename">重命名</a>
                                                     <a href="javascript:void(0)" class="cz_delete">删除</a>
                                                     <a href="javascript:void(0)" class="cz_saveTemplate">保存模板</a>
                                                     <a href="javascript:void(0)" class="cz_saveTemplate2" style="display:none;background:grey;cursor: not-allowed;width: 128px;margin-top: 10px;">保存模板</a>
                                                     <a href="javascript:void(0)" onclick="daoru1()" class="cz_drTemplate">导入模板</a>
                                                </div>
                                                <div class="not_click"></div>
                                                <button class="btn btn-danger tcgm">退出重命名</button>
                                           </div>
                                     </div>
                                </div>
                                <div class="c_dxa_right">
                                     <div class="right_ts" style="display:none"><i></i><p>请点击左侧分组下方创建单元内容</p></div>
                                     <div class="right_ts_1 cmm_ts" style="display:none"><i></i><span>请在左侧进行重命名操作</span><p>退出重命名操作后才能进行其他操作</p></div>
                                    <c:choose>
                                     <c:when test="${fn:length(catalogList) == 0}">
                                           <div class="right_first_ts" ></div>
                                           <div class="right_ts_1" style="display:none"><i></i><p>请选中左侧分组或内容</p></div>
                                     </c:when>
                                     <c:otherwise>
                                           <div class="right_ts_1"><i></i><p>请选中左侧分组或内容</p></div>
                                     </c:otherwise>
                                    </c:choose>
                                     <div class="nr_div">
                                           <c:forEach items="${catalogList }" var="catalog" varStatus="catalogStatus">
                                                <c:forEach items="${catalog.lpUnitList }" var="unit" varStatus="unitStatus">
                                                     <c:forEach items="${contentList }" var="item">
                                                          <c:choose>
                                                                <c:when test="${item.unitType == '1' and item.unitId == unit.id }">
                                                                      <div class="zhishishuli" style="display: none" data-id="${item.catalogId }${item.unitId }", id="${item.catalogId }${item.unitId }">
                                                                           <div class="zssl_top">
                                                                                <span class="d_title">${item.title }</span>
                                                                                <button class="btn btn-danger" onclick="updateUnitContent(${item.unitId }, true)">保存</button>
                                                                           </div>
                                                                           <div class="zssl_bottom">
                                                                                <textarea rows="" cols="" class="content" id="content_${item.unitId }">${item.content }</textarea>
                                                                           </div>
                                                                      </div>
                                                                </c:when>
                                                                <c:when test="${item.unitType == '11' and item.unitId == unit.id }">
                                                                      <div class="zhishishuli" style="display: none" data-id="${item.catalogId }${item.unitId }", id="${item.catalogId }${item.unitId }">
                                                                           <div class="zssl_top">
                                                                                <span class="d_title">${item.title }</span>
                                                                                <button class="btn btn-danger" onclick="updateActivityUnit(${item.unitId }, true)" >保存</button>
                                                                           </div>
                                                                           <div class="zssl_bottom">
                                                                           <p class="jsts">教师提示/教师总结（选填）：</p>
                                                                                <textarea rows="" cols="" class="materail_content" maxlength="1000" id="content_${item.unitId }" class="js_textarea">${item.content }</textarea>
                                                                           </div>
                                                                      </div>
                                                                </c:when>
                                                                <c:when test="${item.unitType == '2' and item.unitId == unit.id  }">
                                                                      <div class="yuxizice" style="display: none" data-id="${item.catalogId }${item.unitId }" id="${item.catalogId }${item.unitId }">
                                                                      <div class="yxzc_top">
                                                                           <span class="d_title">${unit.title }</span>
                                                                           <c:if test="${item.title!=null }">
                                                                                <button class="btn btn-danger" id="add_${item.unitId }" onclick="changeExam(${item.unitId });">更改</button>
                                                                                <button class="btn btn-primary" id="preview_${item.unitId }" onclick="previewExam(${item.paperId});">预览</button>
                                                                           </c:if>
                                                                           <c:if test="${item.title==null }">
                                                                                <button class="btn btn-danger" onclick="selectExam(${item.unitId });">添加</button>
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
                                                                                               <!-- <li>课件版本：<span>${item.version }</span></li> -->
                                                                                               <li>适用科目：<span>${item.subject }</span></li>
                                                                                               <!--<li>适用年级：<span>${item.grade }</span></li>-->
                                                                                               <li>上传时间：<span><fmt:formatDate value="${item.crateTime }" pattern="yyyy-MM-dd" type="date"/></span></li>
                                                                                               <li>试卷总分：<span>${item.score }</span></li>
                                                                                           </c:if>                                                              
                                                                               </ul>
                                                                           </c:if>
                                                                           <c:if test="${item.title==null }">
                                                                               <ul>
                                                                                           <li>试卷名称：<span>无</span></li>
                                                                                     <li>课件版本：<span>无</span></li>
                                                                                     <li>适用科目：<span>无</span></li>
                                                                                     <li>适用年级：<span>无</span></li>
                                                                               </ul>
                                                                           </c:if>
                                                                      </div>
                                                                </div>
                                                                </c:when>
                                                                <c:when test="${item.unitType == '3' and item.unitId == unit.id}">
                                                                      <div class="weike_" style="display:none" data-id="${item.catalogId }${item.unitId }" id="${item.catalogId }${item.unitId }">
                                                                      <div class="wk_top">
                                                                           <span class="d_title">${unit.title }</span>
                                                                           <button class="btn btn-danger" onclick="addMicro(${item.unitId }, 0)"><i class="fa fa-plus"></i>添加</button>
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
                                                                                                        <span class="i1">微课目录：${tbFn:getCatalogName(micro.catalogCode,'0')}  </span>
                                                                                                        <div class="i1">上传时间：<fmt:formatDate value="${micro.createDate}" pattern="YYYY-MM-dd" /></div>
                                                                                                        <div class="cz_btn">
                                                                                                           <button class="btn btn-primary" onclick="previewMicro(${micro.id})">预览</button>
                                                                                                           <c:if test="${item.size==1}">
                                                                                                                <button class="btn btn-danger" data-type="3" data-file_id="${micro.lpUnitFileId}" onclick="addMicro(${item.unitId }, 1)">更改</button>
                                                                                                           </c:if>
                                                                                                           <c:if test="${item.size>1}">
                                                                                                                <button class="btn btn-danger" data-type="3" data-file_id="${item.unitId }" onclick="lpUnitFileDelete(${micro.lpUnitFileId},this,${item.unitId },'确定删除该微课吗？')">删除</button>
                                                                                                           </c:if>
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
                                                                           <span class="d_title">${unit.title }</span>
                                                                           <button class="btn btn-danger" onclick="addMaterail(${item.unitId }, 0)"><i class="fa fa-plus"></i>添加</button>
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
                                                                                                            <span class="${iconFn:getClassName(micro.iconType)} icon-file res-iconb"></span>
                                                                                                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
                                                                                                                <a href="javascript:void(0)" onclick="previewMicro(${micro.id})" title="${micro.title }">${micro.title }</a>
                                                                                                            </span>
                                                                                                        </div>
                                                                                                        <span class="i1">课件目录：${tbFn:getCatalogName(micro.catalogCode,'0')}  </span>
                                                                                                        <div class="i1">上传时间：<fmt:formatDate value="${micro.createDate}" pattern="YYYY-MM-dd" /></div>
                                                                                                        <div class="cz_btn">
                                                                                                           <button class="btn btn-primary" onclick="previewMicro(${micro.id})">预览</button>
                                                                                                           <c:if test="${item.size==1}">
                                                                                                                <button class="btn btn-danger" data-type="4" data-file_id="${micro.lpUnitFileId}" onclick="addMaterail(${item.unitId }, 1)">更改</button>
                                                                                                           </c:if>
                                                                                                           <c:if test="${item.size>1}">
                                                                                                                <button class="btn btn-danger" data-type="4" data-file_id="${micro.lpUnitFileId}" onclick="lpUnitFileDelete(${micro.lpUnitFileId},this, ${item.unitId }, '你确定要删除本课件吗？')">删除</button>
                                                                                                           </c:if>
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
<div class="zzc"></div>
<div class="nounit_div" style="display:none;">
     <p style="width: 236px;margin: 40px auto 0; text-align:center;">该导学案内有分组为空, 不能进行布置</p>
</div>
<script>
var i=0;
var editor;
var h = document.documentElement.clientHeight;
var m,t;
var changeMicro = 0;
//没有导学案模板 置灰导入模板按钮
<c:if test="${templateIsEmpty}">
     $(".cz_drTemplate").attr("onclick","");
     $(".cz_drTemplate").css({"background":"grey","cursor":"not-allowed"});
     $(".daoru").attr("onclick","");
     $(".daoru").css({"background":"grey","cursor": "not-allowed"});
</c:if>
$(function(){
     $(".zzc").height(h);//遮罩层高度
     //新建文件夹(第一次进入页面)
     $(".creat_dxa .c_dxa_left .first_creat .createFolder").click(function(){
           layer.confirm('<div class="form-horizontal" style="padding:0"><div class="control-group" style="margin-top:30px;"><label class="control-label"><span class="red">*</span>名称：</label><div class="controls"><input type="text" maxlength="8" class="wjm_name"></div></div></div>', {
                  title: '新建文件夹',
                  area: ['420px', ''], //宽高
                  btn: ['确定','取消'], //按钮
                }, function(index){
                     if($(".wjm_name").val()!=null && $(".wjm_name").val()!=""){
                           $(".creat_dxa .c_dxa_left .first_creat").hide();
                           $(".creat_dxa .c_dxa_left .second_creat,.zzc").show();
                           $(".creat_dxa .c_dxa_left .second_creat").show();
                           $(".right_ts").show();
                           
                           //添加导学案目录
                           $.ajax({
                             url: "${pageContext.request.contextPath}/learningPlan/catalog/create",
                             type: "POST",
                             data: {"title":$(".wjm_name").val(),"lpId":"${lpId}"},
                             async: true,
                             success: function(data) {
                                $(".c_dxa_right .right_first_ts").hide();
                                     $(".creat_dxa .c_dxa_left .second_creat p").text($(".wjm_name").val());
                                     $(".creat_dxa .c_dxa_left .three_creat .folder_div").eq(0).children("p").children("span").text($(".wjm_name").val());
                                     $(".creat_dxa .c_dxa_left .three_creat .folder_div").eq(0).children("p").children("input").val($(".wjm_name").val());
                                     $(".creat_dxa .c_dxa_left .three_creat .folder_div").eq(0).children("p").children("input").attr("data-catalogId", data);
                                     layer.close(index);
                                     $(".a2").show();
                             }
                         });
                     } else {
                           $.alert("请输入名称");
                     }
                }, function(index){
                     layer.close(index);
                });
     });
     //保存模板
     $(".cz_saveTemplate").click(function(){
           layer.confirm('<div class="form-horizontal" style="padding:0"><div class="control-group" style="margin-top:30px;"><label class="control-label">名称：</label><div class="controls"><input type="text" maxlength="8" class="moban_name" value="导学案模板1"></div></div></div>', {
                  title: '保存模板',
                  area: ['498px', ''], //宽高
                  btn: ['确定','取消'], //按钮
                }, function(index){
                     var loader = new loadDialog();
                     if($(".moban_name").val()!=null && $(".moban_name").val()!=""){
                           loader.show();  
                           $.ajax({
                                url:"${pageContext.request.contextPath}/learningPlan/addTemplate",
                                type:"POST",
                                data:{"title":$(".moban_name").val(),"lpId":"${lpId}"},
                                async:true,
                                success:function(data){
                                     if(data=="success"){
                                           layer.close(index);
                                           loader.close();
                                           $.success("保存成功！");
                                           //导入按钮是置灰的话 恢复原样
                                          $(".cz_drTemplate").attr("onclick","daoru1()");
                                          $(".cz_drTemplate").css({"background":"#f0ad4e", "cursor": "pointer"});
                                     }else{
                                           loader.close();
                                           $.error("保存失败！");
                                     }
                                },
                                error:function(){
                                     loader.close();
                                     $.error("保存失败！");
                                }
                           });
                           
                     }else{
                           $.alert("请输入名称");
                     }
                }, function(index){
                     layer.close(index);
                });
     });
     
     $(".creat_btn a").click(function(){
           $(".right_ts").hide();
           $(".right_ts_1").hide();
           $(".zhishishuli,.yuxizice,.weike_").hide();
     });
     
     $(".creat_dxa .c_dxa_left .second_creat a").click(function(){
           $(".creat_dxa .c_dxa_left .second_creat").hide();
           $(".creat_dxa .c_dxa_left .three_creat").show();
           $(".creat_dxa .c_dxa_left .second_creat,.zzc").hide();
           
     })
     $(".three_creat .c_bottom .creat_btn a").click(function(){
           i=i+1;
     });
     
     //新建文件夹(建立有文件夹后)
     $(".c_folde").click(function(){
           layer.confirm('<div class="form-horizontal" style="padding:0"><div class="control-group" style="margin-top:30px;"><label class="control-label"><span class="red">*</span>名称：</label><div class="controls"><input type="text" maxlength="8" class="wjm_name"></div></div></div>', {
                  title: '新建文件夹',
                  area: ['420px', ''], //宽高
                  btn: ['确定','取消'], //按钮
                }, function(index){
                     if($(".wjm_name").val()!=null && ""!=$(".wjm_name").val()){
                           var wjj_name=$(".wjm_name").val()
                           //添加导学案目录
                           $.ajax({
                             url: "${pageContext.request.contextPath}/learningPlan/catalog/create",
                             type: "POST",
                             data: {"title":wjj_name,"lpId":"${lpId}"},
                             async: true,
                             success: function(data) {
                                $("<div class='folder_div'><a href='javascript:void(0)' class='hide_div'></a><p class='top'><span>"+wjj_name+"</span><input type='text' data-catalogId='"+data+"' maxlength='8' value='"+wjj_name+"'></p></div>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top")
                                     layer.close(index);
                                $(".a2").show();
                             }
                         });
                           $('.cz_saveTemplate').show();
                           $('.cz_saveTemplate2').hide();
                     } else {
                           $.alert("请输入名称");
                     }
                     
                }, function(index){
                     layer.close(index);
                });
     })
     
     $(".c_zhishi").click(function(){
           if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
                $(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
           }
           $(".nr_div").children().hide();
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on")
           var catalogId = $(".folder_div.on").children().children("input").data("catalogid");//当前
           
           if(catalogId==null) {
                $.alert("请选择一个分组");
                return;
           }
           
           //添加导学案目录单元
           $.ajax({
             url: "${pageContext.request.contextPath}/learningPlan/unit/create",
             type: "POST",
             data: {"title":"知识梳理","catalogId":catalogId,"unitType":1,"lpId":"${lpId}"},
             async: true,
             success: function(data) {
                unitId = data;
                $("<li class='a1' data-id="+i+"><p class='light_blue on'><i></i><span style='display:none'>知识梳理</span><input type='text' value='知识梳理' maxlength='8' data-unitId='"+data+"'></p></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
                $("input").focus();
                
                //生成右边div
                var h1=h-121;
                var h2=h-179;
                var zsst = $(
                          '<div class="zhishishuli" style="height:'+h1+'px;" data-id=' + i + ' id=' + i + '>'+
                          '<div class="zssl_top">'+
                                '<span class="d_title">知识梳理</span>'+
                                '<button class="btn btn-danger" onclick="updateUnitContent('+data+', true)">保存</button>'+
                          '</div>'+
                          '<div class="zssl_bottom" style="height:'+h2+'px">'+
                                '<textarea rows="" cols="" class="content" id="content_'+data+'"></textarea>'+
                          '</div>'+
                     '</div>'
                     );
                $(".nr_div").append(zsst);
                createKE();
             }
         });
     });
     
     $(".c_zja").click(function(){
           if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
                $(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
           }
           $(".nr_div").children().hide();
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on")
           var catalogId = $(".folder_div.on").children().children("input").data("catalogid");//当前
           
           if(catalogId==null) {
                $.alert("请选择一个分组");
                return;
           }
           
           //添加导学案目录单元
           $.ajax({
             url: "${pageContext.request.contextPath}/learningPlan/unit/create",
             type: "POST",
             data: {"title":"学生小结","catalogId":catalogId,"unitType":11,"lpId":"${lpId}","content":"请写下你的学习感悟和学习后存在的疑问"},
             async: true,
             success: function(data) {
                unitId = data;
                $("<li class='a11' data-id="+i+"><p class='light_blue on'><i></i><span style='display:none'>学生小结</span><input type='text' value='学生小结' maxlength='8' data-unitId='"+data+"'></p></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
                $("input").focus();
                
                //生成右边div
                var h1=h-121;
                var h2=h-179;
                var zsst = $(
                          '<div class="zhishishuli" style="height:'+h1+'px;" data-id=' + i + ' id=' + i + '>'+
                          '<div class="zssl_top">'+
                                '<span class="d_title">学生小结</span>'+
                                '<button class="btn btn-danger" onclick="updateActivityUnit('+data+', true)">保存</button>'+
                          '</div>'+
                          '<div class="zssl_bottom" style="height:'+h2+'px">'+
                                '<p class="jsts">教师提示/教师总结（选填）：</p>'+
                                '<textarea rows="" cols="" class="materail_content" id="content_'+data+'" class="js_textarea">请写下你的学习感悟和学习后存在的疑问</textarea>'+
                          '</div>'+
                     '</div>'
                     );
                $(".nr_div").append(zsst);
                createKELimit();
                //$(".js_textarea").css("height",h-216);
             }
         });
     });
     
     $(".c_yuxi").click(function(){
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on")
           if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
                $(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
           }
           
           var catalogId = $(".folder_div.on").children().children("input").data("catalogid");//当前
           if(catalogId==null) {
                $.alert("请选择一个分组");
                return;
           }
           
           //添加导学案目录单元
           $.ajax({
             url: "${pageContext.request.contextPath}/learningPlan/unit/create",
             type: "POST",
             data: {"title":"预习自测","catalogId":catalogId,"unitType":2,"lpId":"${lpId}"},
             async: true,
             success: function(data) {
                $("<li class='a2' data-id='uuid"+data+"' style='display:none'><p><i></i><span style='display:none'>预习自测</span><input type='text' value='预习自测' maxlength='8' data-unitId='"+data+"' id='"+data+"'></p></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
                $("input").focus();
                
                //生成右边div
                var h1=h-121;
                var h2=h-209;
                var yxzc = $(
                          '<div class="yuxizice" style="display: none;height:'+h1+'px;" data-id=' + i + ' id="uuid'+data+'">'+
                          '<div class="yxzc_top">'+
                                '<span class="d_title">预习自测</span>'+
                                '<button class="btn btn-danger" onclick="changeExam('+data+');" id="add_'+data+'">添加</button>'+
                          '</div>'+
                          '<div class="yxzc_bottom" style="height:'+h2+'px" id="yxzc_bottom_'+data+'">'+
                          '</div>'+
                     '</div>'   
                     );
                $(".nr_div").append(yxzc);
                selectExam(data);
             }
         });
     });
     
     $(".c_video").click(function(){
           
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on");
           if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
                $(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
           }
           
           var catalogId = $(".folder_div.on").children().children("input").data("catalogid");//当前
           
           if(catalogId==null) {
                $.alert("请选择一个分组");
                return;
           }
           
           //添加导学案目录单元
           $.ajax({
             url: "${pageContext.request.contextPath}/learningPlan/unit/create",
             type: "POST",
             data: {"title":"微课","catalogId":catalogId,"unitType":3,"lpId":"${lpId}"},
             async: true,
             success: function(data) {
                $("<li class='a3' data-id='uuid"+data+"' style='display:none'><p><i></i><span style='display:none'>微课</span><input type='text' value='微课' type='text' maxlength='8' data-unitId='"+data+"' id='"+data+"'></p></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
                $("input").focus();
                
                //生成右边div
                var h1=h-121;
                var h2=h-209;
                var wk = $(
                          '<div class="weike_" style="display:none;height:'+h1+'px;" data-id=' + i + ' id="uuid'+data+'">'+
                          '<div class="wk_top">'+
                                '<span class="d_title">微课</span>'+
                                '<button class="btn btn-danger" onclick="addMicro('+data+',0)"><i class="fa fa-plus"></i>添加</button>'+
                          '</div>'+
                          '<div class="wk_bottom" style="height:'+h2+'px;">'+
                          '<p class="wk_tj">该子单元内已有 <span class="micronum_'+data+'">0</span> 个微课</p>'+
                          '<div class="xkzy_list" id="xkzy_list_'+data+'">'+
                          '</div>'+
                          '</div>'+
                          '</div>'
                     );
                $(".nr_div").append(wk);
                changeMicro = 1;
                selectMicro(data);
             }
         });
     });
     
     $(".c_kejian").click(function(){
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on");
           if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
                $(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
           }
           
           var catalogId = $(".folder_div.on").children().children("input").data("catalogid");//当前
           
           if(catalogId==null) {
                $.alert("请选择一个分组");
                return;
           }
           
           //添加导学案目录单元
           $.ajax({
             url: "${pageContext.request.contextPath}/learningPlan/unit/create",
             type: "POST",
             data: {"title":"课件","catalogId":catalogId,"unitType":4,"lpId":"${lpId}"},
             async: true,
             success: function(data) {
                $("<li class='a12' data-id='uuid"+data+"' style='display:none'><p><i></i><span style='display:none'>课件</span><input type='text' value='课件' type='text' maxlength='8' data-unitId='"+data+"' id='"+data+"'></p></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
                $("input").focus();
                
                //生成右边div
                var h1=h-121;
                var h2=h-209;
                var wk = $(
                          '<div class="weike_" style="display:none;height:'+h1+'px;" data-id=' + i + ' id="uuid'+data+'">'+
                          '<div class="wk_top">'+
                                '<span class="d_title">课件</span>'+
                                '<button class="btn btn-danger" onclick="addMaterail('+data+',0)"><i class="fa fa-plus"></i>添加</button>'+
                          '</div>'+
                          '<div class="wk_bottom" style="height:'+h2+'px;">'+
                          '<p class="wk_tj">该子单元内已有 <span class="micronum_'+data+'">0</span> 个课件</p>'+
                          '<div class="xkzy_list" id="xkzy_list_'+data+'">'+
                          '</div>'+
                          '</div>'+
                          '</div>'
                     );
                $(".nr_div").append(wk);
                changeMicro = 1;
                selectMaterial(data);
             }
         });
     });
     
     //单元input框失去焦点时
     $("body").on("blur",".folder_div ul li p input",function(){
           $(".rename_ts").hide()
           var name=trim($(this).val());
           if(name==null || name==""){
                layer.confirm("请输入单元名称", { btn: '确定'},function () {
                     $("input").focus();
                     layer.closeAll();
                });
                return false;
           }
           var unitId = $(this).attr("data-unitId");
           if($(".c_top").hasClass("rename")==false){
                //修改导学案目录单元
                unitModify(name, unitId);
                
                $(this).prev().text(name);
                $(this).prev().show()
                $(this).hide();
                if($(this).parent().hasClass("on")){
                     $(".three_creat .c_top .folder_div ul li p").removeClass("on")
                     $(this).parent().addClass("light_blue on");
                }
                var div_id=$(this).parent().parent().data("id");
                $("#"+div_id).children().children(".d_title").text(name)
           }else{
                unitModify(name, unitId);
                //重命名
                var div_id=$(this).parent().parent().data("id");
                $("#"+div_id).children().children(".d_title").text(name)
           }
     });
     
     //文件夹input框失去焦点时
     $("body").on("blur",".folder_div p.top input",function(){
           $(".rename_ts").hide();
           var name=$(this).val();
           if(name==null || name==""){
                layer.confirm("请输入文件夹名称", { btn: '确定'},function () {
                     $("input").focus();
                     layer.closeAll();
                });
                return false;
           }
           var catalogId = $(this).attr("data-catalogId");
           if($(".c_top").hasClass("rename")==false){
                //修改导学案目录
                catalogModify(name, catalogId);
                
                $(this).prev().text(name);
                $(this).prev().show()
                $(this).hide();
                $(this).parent().addClass("current");
           }else{
                //重命名
                catalogModify(name, catalogId);
           }
     });
     
     //文件夹input框获得焦点时
     $("body").on("focus",".folder_div p.top input,.folder_div ul li p input",function(){
           if($(".c_top").hasClass("rename")==true){
                //重命名
                $(".rename_ts").show();
                window.clearTimeout(t);
                $(document).click(function(e){
                     t=setTimeout("$('.rename_ts').hide();",3000)
                     m = e.pageY;    //鼠标据顶部的距离
                     $(".rename_ts").css("top",m-"95");
                });
                $(".three_creat .c_top").scroll(function ()
                {
                     var st = $(this).scrollTop();
                     $(".rename_ts").css("top",m-st-"95");
                });
                
           }
     });
     
     //点击文件夹下面的单元
     $(".three_creat .c_top ").on("click",".folder_div ul li p",function(){
           //生成右边div
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on");
           $(this).addClass("light_blue on");
           /* $(this).children("span").hide();
           $(this).children("input").show();
           if($(".c_top").hasClass("rename")==false){
                $("input").focus();
           } */
           $(".folder_div p.top").removeClass("current");
           $(".folder_div").removeClass("on");
           $(this).parent().parent().parent().addClass("on");
           $(".right_ts_1").hide();
           var div_id=$(this).parent().data("id");
           if($(".c_top").hasClass("rename")){
                $(".cmm_ts").show();
           }else{
                $(".nr_div").children().hide();
                $("#"+div_id).show();
           }
     });
     
     //双点击文件夹下面的单元
     $(".three_creat .c_top ").on("dblclick",".folder_div ul li p",function(){
           //生成右边div
           $(".three_creat .c_top .folder_div ul li p").removeClass("light_blue on");
           $(this).addClass("on");
           $(this).children("span").hide();
           $(this).children("input").show();
           if($(".c_top").hasClass("rename")==false){
                $("input").focus();
                console.log(1212)
           }
           $(".folder_div p.top").removeClass("current");
           $(".folder_div").removeClass("on");
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
           /* $(this).children("span").hide();
           $(this).children("input").show();
           $(".folder_div p").removeClass("current");
           if($(".c_top").hasClass("rename")==false){
           $("input").focus();
           } */
           $(".three_creat .c_top .folder_div p.top").removeClass("current");
           $(this).addClass("current")
           $(".three_creat .c_top .folder_div ul li p").removeClass("on light_blue");
           if($(".c_top").hasClass("rename")){
                $(".cmm_ts").show();
           }else{
                $(".right_ts_1").show();
                $(".right_ts").hide();
                $(".cmm_ts").hide();
                $(".nr_div").children().hide(); 
           }
           
           /* 并展开这文件夹所有单元 */
           $(this).next().show();
          $(this).prev().removeClass("show_div").addClass("hide_div")
     })
     
           //双点击文件夹名字
     $(".three_creat .c_top").on("dblclick",".folder_div p.top",function(){
           $(".three_creat .c_top .folder_div").removeClass("on");
           $(this).parent().addClass("on");
           $(this).children("span").hide();
           $(this).children("input").show();
           $(".folder_div p.top").removeClass("current");
           if($(".c_top").hasClass("rename")==false){
           $("input").focus();
           }
           $(".three_creat .c_top .folder_div ul li p").removeClass("on light_blue");
           $(".right_ts_1").show();
           $(".right_ts").hide();
           $(".nr_div").children().hide();
     })
     
     //上移
     $(".cz_up").click(function(){
           //单元
           if($(".folder_div ul li p").hasClass("light_blue")){
                var unitPre = $(".folder_div ul li .light_blue").children("input").data("unitid");//当前
                var unitNext = $(".folder_div ul li .light_blue").parent().prev().children().children("input").data("unitid");//前一个
                unitMove(unitPre, unitNext);
                $(".folder_div ul li .light_blue").parent().prev().before($(".folder_div ul li .light_blue").parent());
           }
           //目录
           if($(".folder_div.on p").hasClass("current")){
                var catalogPre = $(".folder_div.on .current").children("input").data("catalogid");//当前
                var catalogNext = $(".folder_div.on .current").parent().prev().children().children("input").data("catalogid");//前一个
                catalogMove(catalogPre, catalogNext);
                $(".folder_div.on .current").parent().prev().before($(".folder_div.on .current").parent());
           }
     });
     
     //下移
     $(".cz_down").click(function(){
           //单元
           if($(".folder_div ul li p").hasClass("light_blue")){
                var unitPre = $(".folder_div ul li .light_blue").children("input").data("unitid");//当前
                var unitNext = $(".folder_div ul li .light_blue").parent().next().children().children("input").data("unitid")//后一个
                unitMove(unitPre, unitNext);
                $(".folder_div ul li .light_blue").parent().next().after($(".folder_div ul li .light_blue").parent());
           }
           //目录
           if($(".folder_div.on p").hasClass("current")){
                var catalogPre = $(".folder_div.on .current").children("input").data("catalogid");//当前
                var catalogNext = $(".folder_div.on .current").parent().next().children().children("input").data("catalogid");//后一个
                catalogMove(catalogPre, catalogNext);
                $(".folder_div.on .current").parent().next().after($(".folder_div.on .current").parent());
           }
     });
     
     //   重命名
     $(".cz_rename").click(function(){
           $(".folder_div p.top input").show();
           $(".folder_div p.top span").hide();
           $(".folder_div ul li p input").show();
           $(".folder_div ul li p span").hide();
           $(".c_top").addClass("rename");
           $(".not_click,.tcgm").show();
           $(".right_ts_1").hide();
           $(".right_ts").hide();
           $(".nr_div").children().hide();
           $(".cmm_ts").show();
           $(".zzc").show().css("z-index","1");
           $(".three_creat").css("z-index","2");
     });
     
     //   退出重命名
     $(".tcgm").click(function(){
           $(".not_click,.tcgm").hide();
           $(".folder_div input").each(function(){
                var nr=$(this).val();
                $(this).prev().text(nr);
                $(this).prev().show()
                $(this).hide();
           });
           $(".c_top").removeClass("rename");
           $(".zzc").hide()
     });
     
     $(".cz_delete").click(function(){
           
           var div_id=$(".folder_div ul li .light_blue").parent().data("id");
           
           $("#"+div_id).remove();
           $(".right_ts_1").show();
           $(".cmm_ts").hide();
           $(".nr_div").children().hide();
           if($(".folder_div ul li p").hasClass("light_blue")){
                var unitid=$(".folder_div ul li .light_blue").children("input").data("unitid"); //单元删除的unitid
                //删除导学案目录单元
                unitDelete(unitid);
                $(".folder_div ul li .light_blue").parent().remove();
           }
           if($(".folder_div.on p").hasClass("current")){
                var catalogid=$(".folder_div.on .current").children("input").data("catalogid"); //目录删除的id
                //删除导学案目录
                catalogDelete(catalogid);
                $(".folder_div.on .current").parent().remove();
                if($(".three_creat .c_top .folder_div").length==0){
                     $(".a2").hide();
                }
           }
           
           if( $(".c_top").children('.folder_div').length==0){
                $('.cz_saveTemplate2').show();
                $('.cz_saveTemplate').hide();
           }
     });
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
     $(".c_dxa_left,.c_dxa_right,.right_ts,.right_ts_1").css("height",h-105);
     $(".zhishishuli, .yuxizice, .weike_").css("height",h-117);
     $(".three_creat .c_top").css("height",h-366);
     $(".zhishishuli .zssl_bottom").css("height",h-162);
     $(".yuxizice .yxzc_bottom").css("height",h-192);
     $(".weike_ .wk_bottom").css("height",h-192);
     $(".js_textarea").css("height",h-216);
     
     createKE();
     createKELimit();
     
});
function lpProperties(lpId) {
     $.initWinOnCurFromLeft('基础属性设置', '${pageContext.request.contextPath}/learningPlan/properties/get?id='+lpId, '1100', '500');
}
function createKE() {
     KindEditor.ready(function(K) {
           editor = K.create('textarea[class="content"]', {
                resizeType : 1,
                allowPreviewEmoticons : false,
                uploadJson       : '${pageContext.request.contextPath}/uploader/forKe?editor=editor&jsessionId=' + '<%=request.getSession().getId()%>',
                filePostName     : 'file',
                allowImageUpload : true,
                fillDescAfterUploadImage : true,
                dir: 'image',
                width:'99%',
                height:h-172,
                afterBlur :function() {
                     var unitid=$(window.parent.document).find(".three_creat .c_top .folder_div ul li p.on input").data("unitid");
                     updateUnitContent(unitid, false);
                },
                items : [ 'fontname', 'fontsize', '|', 'forecolor',
                                'hilitecolor', 'bold', 'italic', 'underline',
                                'removeformat', '|', 'justifyleft', 'justifycenter',
                                'justifyright', 'insertorderedlist',
                                'insertunorderedlist', '|','image',
                                'link' ],
                                
                afterChange : function() {
                     $(".form-horizontal .textarea label").hide();
                     var limitNum = 10000;  //设定限制字数
                   
                    if(this.count('text') > limitNum) {
                     var pattern = ('字数超过限制，请适当删除部分内容');
                      //超过字数限制自动截取
                      var strValue = editor.text();
                      strValue = strValue.substring(0,limitNum);
                      editor.text("");
                      editor.insertHtml(strValue);     
                   }else{
                        var result = limitNum - this.count('text');
                         pattern = '还可以输入' +  result + '字';
                     }
                      $('.word_surplus').html(pattern); //输入显示
                 },
                 afterCreate : function () { 
                this.loadPlugin('autoheight'); 
                var __doc = this.edit.doc; 
                $(__doc).bind('paste', null, function () { //右键粘贴, 包括 ctrl+v 
                    setTimeout(function () { 
                        uploadWebImg(editor); 
                    }, 200); 
                }); 
            },
           });
     });
}
function createKELimit() {
     KindEditor.ready(function(K) {
           editor = K.create('textarea[class="materail_content"]', {
                resizeType : 1,
                allowPreviewEmoticons : false,
                uploadJson       : '${pageContext.request.contextPath}/uploader/forKe?editor=editor&jsessionId=' + '<%=request.getSession().getId()%>',
                filePostName     : 'file',
                allowImageUpload : true,
                fillDescAfterUploadImage : true,
                dir: 'image',
                width:'99%',
                height:h-200,
                afterBlur :function() {
                     var unitid=$(window.parent.document).find(".three_creat .c_top .folder_div ul li p.on input").data("unitid");
                     updateUnitContent(unitid, false);
                },
                items : [ 'fontname', 'fontsize', '|', 'forecolor',
                                'hilitecolor', 'bold', 'italic', 'underline',
                                'removeformat', '|', 'justifyleft', 'justifycenter',
                                'justifyright', 'insertorderedlist',
                                'insertunorderedlist', '|','image',
                                'link' ],
                                
                afterChange : function() {
                     $(".form-horizontal .textarea label").hide();
                     var limitNum = 1000;  //设定限制字数
                   
                    if(this.count('text') > limitNum) {
                     var pattern = ('字数超过限制，请适当删除部分内容');
                      //超过字数限制自动截取
                      var strValue = editor.text();
                      strValue = strValue.substring(0,limitNum);
                      editor.text("");
                      editor.insertHtml(strValue);     
                   }else{
                        var result = limitNum - this.count('text');
                         pattern = '还可以输入' +  result + '字';
                     }
                      $('.word_surplus').html(pattern); //输入显示
                 }
           });
     });
}
//删除导学案目录
function catalogDelete(catalogid) {
     $.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/catalog/delete?id="+catalogid,
        type: "DELETE",
        data: {},
        async: true,
        success: function(data) {
        }
    });
}
//修改导学案目录
function catalogModify(name, catalogId) {
     $.ajax({
         url: "${pageContext.request.contextPath}/learningPlan/catalog/modify",
         type: "POST",
         data: {"title":name,"id":catalogId},
         async: true,
         success: function(data) {
         }
     });
}
//导学案目录移动
function catalogMove(catalogIdPre, catalogIdNext) {
     if(catalogIdPre!=null && catalogIdNext != null) {
           var catalogId = new Array()
           catalogId[0] = catalogIdPre;
           catalogId[1] = catalogIdNext;
           
           $.ajax({
               url: "${pageContext.request.contextPath}/learningPlan/catalog/move",
               type: "POST",
               data: {"catalogList":JSON.stringify(catalogId)},
               async: true,
               success: function(data) {
               }
           });
     }
}
//删除导学案单元
function unitDelete(unitid) {
     $.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/unit/delete?id="+unitid,
        type: "DELETE",
        data: {},
        async: true,
        success: function(data) {
        }
    });
}
//修改导学案单元
function unitModify(name, unitId) {
     $.ajax({
         url: "${pageContext.request.contextPath}/learningPlan/unit/modify",
         type: "POST",
         data: {"title":name,"id":unitId},
         async: true,
         success: function(data) {
         }
     });
}
//导学案单元移动
function unitMove(unitIdPre, unitIdNext) {
     if(unitIdPre!=null && unitIdNext != null) {
           var catalogId = new Array()
           catalogId[0] = unitIdPre;
           catalogId[1] = unitIdNext;
           
           $.ajax({
               url: "${pageContext.request.contextPath}/learningPlan/unit/move",
               type: "POST",
               data: {"unitList":JSON.stringify(catalogId)},
               async: true,
               success: function(data) {
               }
           });
     }
}
function updateUnitContent(unitId, info) {
     var content = $("#content_"+unitId).prev().children(".ke-edit").children("iframe").contents().find("body").html();
     
     $.ajax({
         url: "${pageContext.request.contextPath}/learningPlan/unit/modify",
         type: "POST",
         data: {"content":content,"id":unitId},
         async: true,
         success: function(data) {
          if(info) {
                $.success("保存成功");
          }
         }
     });
}
function updateActivityUnit(unitId, info) {
     var content = $("#content_"+unitId).prev().children(".ke-edit").children("iframe").contents().find("body").html();
     
     $.ajax({
         url: "${pageContext.request.contextPath}/learningPlan/unit/modify",
         type: "POST",
         data: {"content":content,"id":unitId},
         async: true,
         success: function(data) {
          if(info) {
                $.success("保存成功");
          }
         }
     });
}
function previewMicro(mid) {
     var mes = "预览";
     $.initWinOnCurFromLeft(mes,'${pageContext.request.contextPath}/learningPlan/viewer?id='+ mid, '805', '570');
}
function selectExam(unitId) {
    var mes = "添加试卷";
    layer.open({
             type: 2,
             skin: 'layui-layer-lvse', //样式类名
             title: mes,
             shadeClose: false,
             shade: 0.8,
             area: ['1000px', '635px'],
             content: '${pageContext.request.contextPath}/learningPlan/resource/index?type=4&isNew=true&unitId='+unitId, //iframe的url
             cancel: function(index, layero){
                  $("#uuid"+unitId).remove();
                  $("#"+unitId).parent().parent().remove();
                  layer.close(index);
                  unitDelete(unitId);
           }  
     });
  //$.initWinOnCurFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/exam/index?unitId='+unitId, '1000', '635');
}
function changeExam(unitId) {
    var mes = "添加试卷";
     $.initWinOnCurFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/resource/index?type=4&isNew=false&unitId='+unitId, '1000', '635');
}
function selectMicro(unitId) {
     var mes = "添加微课";
     layer.open({
             type: 2,
             skin: 'layui-layer-lvse', //样式类名
             title: mes,
             shadeClose: false,
             shade: 0.8,
             area: ['1000px', '635px'],
             content: '${pageContext.request.contextPath}/learningPlan/resource/index?type=1&isNew=true&unitId='+unitId, //iframe的url
             cancel: function(index, layero){
                  $("#uuid"+unitId).remove();
                  $("#"+unitId).parent().parent().remove();
                  changeMicro = 0;
                  layer.close(index);
                  unitDelete(unitId);
           }  
     });
     //$.initWinOnCurFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/micro/index?unitId='+unitId, '1000', '635');
}
function selectMaterial(unitId) {
     var mes = "添加课件";
     layer.open({
             type: 2,
             skin: 'layui-layer-lvse', //样式类名
             title: mes,
             shadeClose: false,
             shade: 0.8,
             area: ['1100px', '635px'],
             content: '${pageContext.request.contextPath}/learningPlan/resource/index?type=2&isNew=true&unitId='+unitId, //iframe的url
             cancel: function(index, layero){
                  $("#uuid"+unitId).remove();
                  $("#"+unitId).parent().parent().remove();
                  changeMicro = 0;
                  layer.close(index);
                  unitDelete(unitId);
           }  
     });
     //$.initWinOnCurFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/micro/index?unitId='+unitId, '1000', '635');
}
function addMicro(unitId, type) {
     var mes = "添加微课";
     if(type==1) {
           mes = "更改微课";
           changeMicro = 1;
     } else {
           changeMicro = 0;
     }
     $.initWinOnCurFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/resource/index?type=1&isNew=false&unitId='+unitId, '1000', '635');
}
function addMaterail(unitId, type) {
     var mes = "添加课件";
     if(type==1) {
           mes="更改课件";
           changeMicro = 1;
     } else {
           changeMicro = 0;
     }
     $.initWinOnCurFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/resource/index?type=2&isNew=false&unitId='+unitId, '1000', '635');
}
function lpUnitFileDelete(id,obj,unitid, msg) {
     $.confirm(msg, function() {
           var loader = new loadDialog();
        loader.show();
        $.ajax({
            url: "${pageContext.request.contextPath}/learningPlan/unit/file/delete?id="+id,
            type: "DELETE",
            data: {},
            async: false,
            success: function() {
                     $(".micronum_"+unitid).text((parseInt($(".micronum_"+unitid).text())-1));
                     
                     $(obj).parent().parent().parent().parent().remove();
                     var length = $("#xkzy_list_"+unitid).children("dl").length;
                     console.log($("#xkzy_list_"+unitid).children("dl").html());
                     console.log(length);
                     if(length==1) {
                           var file_id = $("#xkzy_list_"+unitid).children("dl").eq(0).find("button").eq(1).data("file_id");
                           var type = $("#xkzy_list_"+unitid).children("dl").eq(0).find("button").eq(1).data("type");
                           if("3"==type) {
                                var button = '<button class="btn btn-danger" data-file_id='+file_id+' data-type="3" onclick="addMicro('+unitid+',1)">更换</button>';
                           } else {
                                var button = '<button class="btn btn-danger" data-file_id='+file_id+' data-type="4" onclick="addMaterail('+unitid+',1)">更换</button>';
                           }
                          $("#xkzy_list_"+unitid).children("dl").eq(0).find("button").eq(1).remove();
                          $("#xkzy_list_"+unitid).children("dl").eq(0).find(".cz_btn").append(button);
                     }
                $.success("删除成功！");
                loader.close();
            }
        });
    });
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
                location.href="${pageContext.request.contextPath}/learningPlan/task/prepare?lpId=${lpId}&jumpfrom=edit";
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
function previewExam(paperId) {
     if(paperId==null) {
           $.alert("暂无试卷信息");
           return false;
     }else {
           $.initWinOnCurFromLeft('预览试卷', '${pageContext.request.contextPath}/learningPlan/paper/viewer?paperId='+paperId, '1000', '635');
     }
}
function uploadWebImg(editor) { 
    var relaceSrc = []; //图片地址对象容器 
    var imgs = $(editor.html()).find('img'); 
 
    imgs.map(function () { 
        var _src = $(this).attr('src'); 
        //if ((_src.indexOf('http://') >= 0 || _src.indexOf('https://') >= 0) && checkimgok(_src)) { 
        if (_src.indexOf('http://') >= 0 || _src.indexOf('https://') >= 0) { //考虑可能有动态生成的图片 
            relaceSrc.push({ k: _src }); 
        }; 
    }); 
 
    if (relaceSrc.length == 0) return; 
 
    var msg = '内容包含' + relaceSrc.length.toString() + '张远程图片，是否现在上传？'; 
 
    confirmLayerNormal(msg, function (_index) { 
        var loading = layer.load(0); 
        var paramdata = { 
            action: "791c252eee12530f4f3af326674b7d97", 
            arg: { imgs: relaceSrc }, 
        }; 
 
        doAjaxPost(paramdata, function (result) { 
            layer.close(loading); 
            if (!result.success) { 
                SuperSite.MsgError(result.msg); 
                return; 
            } 
 
            //替换编辑器图片源 
            var _content = editor.html(); 
            $(relaceSrc).each(function (idx, dom) { 
                _content = _content.replace(dom.k, result.data[idx].value); 
            }); 
            editor.html(_content); 
 
            SuperSite.MsgOK('远程图片上传成功'); 
        }); 
        layer.close(_index); 
    }); 
}
//去前面空格
function lTrim(str){
  var i;
  for(i=0;i<str.length;i++){
    if(str.charAt(i)!=" ")
      break;
  }
  str = str.substring(i,str.length);
  return str;
}
//去后面空格
function rTrim(str){
  var i;
  for(i=str.length-1;i>=0;i--){
    if(str.charAt(i)!=" ")
      break;
  }
  str = str.substring(0,i+1);
  return str;
}
//去前面空格
function trim(str){
   return lTrim(rTrim(str));
 }
 
//导入模板
//   $(".cz_drTemplate").click(function(){
function daoru1(){
     layer.confirm('<div  style="font-size: 16px; color: #333333;text-align: center;line-height: 90px;">导入模板会清空当前导学案内的内容，是否继续？</div>', {
             title: '导入模板',
             area: ['498px', ''], //宽高
             btn: ['确定','取消'], //按钮
           }, function(index){
                $.initWinOnTopFromLeft_qyjx("导学案模板", '${pageContext.request.contextPath}/learningPlan/getTemplate?lpId=${lpId}', '790', '612');
                layer.close(index);
           }, function(index){
                layer.close(index);
           });
}
function daoru(){
     $.initWinOnTopFromLeft_qyjx("导学案模板", '${pageContext.request.contextPath}/learningPlan/getTemplate?lpId=${lpId}', '790', '612');
}
</script>
</body>
</html>
