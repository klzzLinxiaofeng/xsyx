<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 75%;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="canteenRecipes_form"  action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>日期</label>
                        <div class="controls">
<%--                            <input type="text" id="date" name="date" class="span4" placeholder="xxxx-xx-xx"--%>

<%--                            ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}--%>
<%--                                   onblur="modifyDateIsempty()"--%>
<%--                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${canteenRecipes.modifyDate}"></fmt:formatDate>'--%>
<%--                            >--%>

                            <input type="text" id="date" name="date"
                                   onFocus="WdatePicker({date:'#F{$dp.$D(\'date\')}'})"
                                   style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2020-02-02"
                                    ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}
                                   onblur="modifyDateIsempty()"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${canteenRecipes.createDate}"></fmt:formatDate>'
                                   >
                        </div>
                    </div>


                    <%--菜系添加--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            包含菜系：
                        </label>
                        <div class="controls teacher">

                            <input type="text" onclick="openPage()" id="cxList" name="cxList" class="span4" placeholder=""
                                   data-id="${canteenRecipes.cxList.name!=null ? canteenRecipes.cxList.name:canteenRecipes.cuisineName }" value="${canteenRecipes.cxList.name!=null ? canteenRecipes.cxList.name:canteenRecipes.cuisineName}"
                                   %>



                            <input type="hidden" name="ids" id="ids" value="${canteenRecipes.cuisineIds}" >

<%--                            <c:if test="${not empty canteenRecipes.cxList.name}">--%>
<%--                            <input type="text" onclick="openPage()" id="cxList" name="cxList" class="span4" placeholder=""--%>
<%--                                   data-id="${canteenRecipes.cxList.name}" value="${canteenRecipes.cxList.name}" %>--%>
<%--                                <input type="hidden" name="ids" id="ids" >--%>
<%--                            </c:if>--%>

<%--                            <c:if test="${not empty canteenRecipes.cuisineName}">--%>
<%--                                <input type="text" onclick="openPage()" id="cxList" name="cxList" class="span4" placeholder=""--%>
<%--                                       data-id="${canteenRecipes.cuisineName}" value="${canteenRecipes.cuisineName}" %>--%>
<%--                                <input type="hidden" name="ids" id="ids" value="${canteenRecipes.cuisineIds}">--%>
<%--                            </c:if>--%>
                        </div>
                    </div>


                    <div class="form-actions tan_bottom"
                         style="padding-left: 0; background-color: #eee; text-align: center">
                        <c:if test="${isCK == null || isCk == ''}">
                            <input type="hidden" id="id" name="id" value="${canteenRecipes.id}"/>
                            <button class="btn btn-warning" type="button"
                                    onclick="saveOrUpdate();">确定
                            </button>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var checker;
    var windowIndex;
    $(function() {
        checker = initValidator();
    });


    function cxSelected(names,ids){
        $("#cxList").val(names);
        $("#ids").val(ids);
        window.top.layer.close(windowIndex);
    }

    function openPage(){

        var $id = $("#id").val();

        var url = "${ctp}/schoolaffair/recipes/goods/index?dm=SHI_PU_GUAN_LI";
        if ("" != $id) {
            url = "${ctp}/schoolaffair/recipes/goods/index?dm=SHI_PU_GUAN_LI&id=" + $id;
        }

        windowIndex=$.initWinOnTopFromLeft('选择菜系', url, '1000', '550');
    }


    function initValidator() {
        return $("#canteenRecipes_form").validate({
            errorClass: "myerror",
            rules: {
                "date": {
                    required: true,
                    maxlength: 200
                },
                "description": {
                    required: true,
                    maxlength: 200
                },
                "cxList": {
                    required: true,
                    maxlength: 200
                }
            },
            messages: {}
        });

    }
    <%--    校验日期--%>

    function modifyDateIsempty() {
        if ($("#date").val() == "") {
            alert("日期不能为空")
        } else if ($("#date").val() != "") {
            var m = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
            var value = $("#date").val()
            if (!m.test(value)) {
                alert("日期格式错误")
            }
        }
    }

    <%--    校验食谱--%>

    function descriptionIsempty() {
        if ($("#cuisineName").val() === "") {
            alert("食谱不能为空")
        }
    }

    //保存或更新修改
    function saveOrUpdate() {
        modifyDateIsempty()
        descriptionIsempty()
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();

            var $requestData = formData2JSONObj("#canteenRecipes_form");
            var url = "${ctp}/schoolaffair/recipes/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/schoolaffair/recipes/" + $id;
            }

            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                            window.top.layer.closeAll();

                        } else {
                            parent.window.location.reload();
                            window.top.layer.closeAll();
                        }

                    } else {
                        $.error("操作失败");
                    }
                } else {
                    $.error("操作失败");
                }

            });
        }
    }
</script>
</html>