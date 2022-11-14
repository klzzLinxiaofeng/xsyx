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
    <title>资产排查登记</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
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
        <jsp:param value="资产排查登记" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">资产排查登记</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">
                                        排查人<span style="color:red">*</span>：
                                    </label>
                                    <div class="textarea">
                                        <input type="button" id="paichaName" readonly="readonly" name="paichaName" style="width:800px;height:100px;" value="${screening.paichaUserName}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            排查时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="paichaTime" name="paichaTime" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               readonly="readonly"
                                               value="${screening.paichaTime2}"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                               placeholder="xxxx-xx-xx xx:xx:xx">

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            排查区域<span style="color:red">*</span>
                                        </label>
                                        <div>
                                            <input type="text" readonly="readonly"  value="${screening.screeningArea}" id="screeningArea"  name="screeningArea"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            水电<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="waterElectricity"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="waterElectricity" value="1"/>
                                            不正常 </label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            安全隐患<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="trouble"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="trouble" value="1"/>
                                            不正常 </label>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            建筑质量<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="construction"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="construction" value="1"/>
                                            不正常 </label>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            设施设备<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="facilities"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="facilities" value="1"/>
                                            不正常 </label>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            其他：
                                        </label>
                                        <input type="text" id="qitaName" value="${screening.qitaName}"/>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="qita"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="qita" value="1"/>
                                            不正常 </label>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            备注：
                                        </label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea style="width:200px;height:100px;padding-top: 4px;"  type="text" id="beizhu" name="beizhu" class="span4" autocomplete="off">${screening.beizhu}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            附件：
                                        </label>
                                        <div class="controls">

                                            <div id="zpa" style="display:inline-block;">
                                                <c:forEach items="${screening.list}" var="item" varStatus="i">
                                                    <a href="${item['url']}">${item['fileName']}</a>
                                                </c:forEach>
                                            </div>
                                        </div>
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
        var waterElectricity=${screening.waterElectricity};
        if(waterElectricity==0){
            $("input[name='waterElectricity']:radio:first").attr("checked","checked");
        }else{
            $("input[name='waterElectricity']:radio:last").attr("checked","checked");
        }

        var trouble=${screening.trouble};
        if(trouble==0){
            $("input[name='trouble']:radio:first").attr("checked","checked");
        }else{
            $("input[name='trouble']:radio:last").attr("checked","checked");
        }

        var construction=${screening.construction};
        if(construction==0){
            $("input[name='construction']:radio:first").attr("checked","checked");
        }else{
            $("input[name='construction']:radio:last").attr("checked","checked");
        }

        var facilities=${screening.facilities};
        if(facilities==0){
            $("input[name='facilities']:radio:first").attr("checked","checked");
        }else{
            $("input[name='facilities']:radio:last").attr("checked","checked");
        }
        var qita=${screening.qita};
        if(qita==0){
            $("input[name='qita']:radio:first").attr("checked","checked");
        }else{
            $("input[name='qita']:radio:last").attr("checked","checked");
        }
    })
</script>
</body>
</html>
