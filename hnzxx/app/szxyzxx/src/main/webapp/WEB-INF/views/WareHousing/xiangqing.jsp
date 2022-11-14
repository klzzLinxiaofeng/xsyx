<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/7
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
        div#div3, div#div2, div#div1,div#div4 {
            position: absolute;
            height: 300px;
            width: 300px;
            background: #eee;
            border: 1px solid #d9d9d9;
            padding: 10px;
            overflow: auto;
            left: 200px;
        }


        div#div3 table button, div#div2 table button, div#div1 table button,div#div4 table button {
            width: 280px;
            height: 30px;
            border: none;
            border-bottom: 1px solid #d9d9d9;
        }


        input.input-medium {
            height: 10px;
            width: 200px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="物资申请" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                        <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">物资详情</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">
                                        申请人<span style="color:red">*</span>：
                                    </label>
                                    <div>
                                        <input type="text" style="display: none" id="shenqingren" name="shenqingren" value="${wareHousing.shenqingren}">
                                        <input type="text" readonly id="shenqingName" name="shenqingName" value="${wareHousing.shenqingName}">
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            审批人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="shenheId"  name="shenheId" style="display:none;" value="${wareHousing.shenheId}"/>
                                        <input type="button" readonly value="${wareHousing.shenheName}"   id="shenheName" name="shenheName" class="span6 left-stripe {required : true,maxlength:40}"/>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            仓储负责人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="fuzeren"  name="fuzeren" style="display:none;" value="${wareHousing.fuzeren}"/>
                                        <input type="button" readonly value="${wareHousing.fuzerenName}"  id="fuzerenName" name="fuzerenName" class="span6 left-stripe {required : true,maxlength:40}"/>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            物资类型<span style="color:red">*</span>：
                                        </label>
                                            <select id="type" readonly name="type" class="span4"
                                                    style="width:200px;" value="${wareHousing.type}">
                                                <c:if test="${wareHousing.type!=null}">
                                                    <c:if test="${wareHousing.type==1}">
                                                        <option value="1">办公用品</option>
                                                    </c:if>
                                                    <c:if test="${wareHousing.type==2}">
                                                        <option value="2">书籍</option>
                                                    </c:if>
                                                    <c:if test="${wareHousing.type==3}">
                                                        <option value="3">防疫物资</option>
                                                    </c:if>
                                                    <c:if test="${wareHousing.type==4}">
                                                        <option value="4">其他</option>
                                                    </c:if>
                                                </c:if>
                                            </select>
                                            <c:if test="${wareHousing.type==4}">
                                                <input readonly type="text"  id="typeName"  id="typeName" value="${wareHousing.typeName}"/>
                                            </c:if>
                                            <c:if test="${wareHousing.type!=4}">
                                                <input readonly type="text" style="display: none" id="typeName"/>
                                            </c:if>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            物资名称<span style="color:red">*</span>：
                                        </label>
                                        <div>
                                        <input type="text" readonly id="WuZiName"  name="WuZiName"  value="${wareHousing.name}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            申请时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" disabled id="createDate" name="createDate" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                               placeholder="xxxx-xx-xx xx:xx"
                                               value="${wareHousing.createDate}">
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            备注<span style="color:red">*</span>：
                                        </label>
                                        <div class="left">
                                            <div class="textarea">
                                            <textarea readonly  style="width:200px;height:100px;padding-top: 4px;" type="text" id="beizhu" name="beizhu" class="span4" autocomplete="off">${wareHousing.beizhu}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            是否需要归还：
                                        </label>
                                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                                <input disabled type="radio" name="isGuihuan"
                                                       value="1" Checked />
                                                是 </label>
                                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                                <input disabled type="radio" name="isGuihuan" value="0"/>
                                                否 </label>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var isGuiHuan=${wareHousing.isGuihuan};
        if(isGuiHuan==1){
           $("input:radio:first").attr("checked","checked");
        }else{
            $("input:radio:last").attr("checked","checked");
        }
    })
</script>
</body>
</html>
