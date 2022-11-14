<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>修改大屏设置</title>
    <%@ include file="/views/embedded/common.jsp" %>
  <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 30%;
            display: inline-block;
            padding-left: 10px;
        }

        .control-group {
            float: left;
            width: 50%;
        }

        .grede {
            position: absolute;
        }

        .grede_ul {
            display: none;
            position: relative;
            background-color: #fff;

        }

        .grede_ul > li {
            border-bottom: 1px rgb(127, 127, 127) solid;
            padding-left: 1rem;
        }

        .grede_ul > li > span {
            padding-left: 1rem;

        }

        #grede_btn {
            border: none;
            margin-left: 2rem;


        }

        #grede_btn:hover {
            background: #a0b569;
        }

        .grede_btn2 {
            border: none;
        }

        #grede_btn2:hover {
            background: #a0b569;
        }

        #grede_all, #all, #grede_btn, #grede_btn2 {
            /*display: none;*/
        }
    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            大屏名称：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${boBaoDaPings.name}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            播报设备号：
                        </label>
                        <div class="controls">
                            <input type="text" id="boBaoHao" name="boBaoHao" class="span4" placeholder=""
                                   value="${boBaoDaPings.boBaoHao}">
                        </div>
                    </div>

                    <%--			1.0新增年级选项			--%>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            年级：
                        </label>
                        <div class="controls grede">
                            <input type="text" id="grede" name="gradeNames" class="span4"
                                   placeholder="请选择年级"
                                   value="${boBaoDaPings.gradeNames}" unselectable="on" readonly>
                            <input style="display: none"  type="text" id="gradeIds" name="fullIdsArr" class="span4"
                                   value="${boBaoDaPings.gradeIds}" unselectable="on" readonly>
                            <ul class="grede_ul" id="grede_ul">
                                <li>
                                    <input type="checkbox" value="全选" id="grede_all"><span id="all"> 全选</span>
                                    <button type="button" id="grede_btn">确定</button>
                                    <button type="button" id="grede_btn2">确定</button>
                                </li>

                            </ul>

                        </div>
                    </div>
                    <div  class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${boBaoDaPings.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    function edit() {
        $("#grede_btn2").hide()
    }

    var grede = document.getElementsByName("grede")
    var gredeAll = $("#grede_all")
    var gredeDateIds = [];
    var gredeDate = [];
    $("#grede_btn2").click(function () {
        if (gredeDate.length == 1) {
            $(".grede_ul").hide()
            $("#grede").prop("value", gredeDate)
        }
        //	为空则不允许返回
        if (gredeDate.length == 0) {
            alert("年级不能为空")
        }

    })

    //确定
    $("#grede_btn").click(function () {
        //隐藏
        //	判断数据不为空
        if (gredeDate.length > 0) {
            $(".grede_ul").hide()
            $("#grede").prop("value", gredeDate)
        }
        //	为空则不允许返回
        else {
            alert("年级不能为空")
        }

    })
    <%--年级全选--%>
    var gredeAll = $("#grede_all")
    gredeAll.click(function () {
        gredeDate = [];
        gredeDateIds=[];
        if (gredeAll.is(':checked')) {
            for (var i = 0; i < grede.length; i++) {
                //在[]后添加数据
                grede[i].checked = true;
                gredeDate.push(grede[i].value)
            }
        } else {
            <%--年级反选--%>
            for (var i = 0; i < grede.length; i++) {
                grede[i].checked = false
            }
        }

    })

    //将选择的年级放入gredeDate
    function gredeckecked() {
        gredeDate = [];
        gredeDateIds=[];
        count = 0;
        for (var i = 0; i < grede.length; i++) {
            if (grede[i].checked) {
                gredeDate.push(grede[i].value);
                count++;
            }
        }
        <%--选择所有时成为全选--%>
        if (count == grede.length) {
            gredeAll.prop("checked", 'true');
        }
            <%--不是所有时取消全选--%>
        else {
            gredeAll.removeAttr("checked")
        }
    }

    var checker;
    //查询年级
    $(function () {
        var count = 1;
        $("#grede").click(function () {
            //显示
            $(".grede_ul").show();
            var url = "${ctp}/GuangBo/grades";
            var requestData = "";
            if (count == 1) {
                $.get(url, requestData, function (data) {
                    var grades = $.parseJSON(data)
                    if (grades.grade[0].status == 2) {
                        edit()
                    } else {
                        $("#grede_btn2").hide()
                        $("#grede_btn").show()
                        $("#grede_all").show()
                        $("#all").show()
                    }
                    var s = "";
                    for (var i = 0; i < grades.grade.length; i++) {
                        s += "<li> <input type='checkbox' name='grede' value='" + grades.grade[i].fullName + "' onclick='gredeckecked()'>" + grades.grade[i].fullName + "</input></li>"
                    }
                    $("#grede_ul").append(s)
                })
            }
            count++
        })
        checker = initValidator();

    });

    //规则
    function initValidator() {
        return $("#publicClass_form").validate({
            errorClass: "myerror",
            rules: {
                "name": {
                    required: true,
                    maxlength: 15,
                    stringCheck: false,
                    isContainsSpecialChar: false
                },
                //添加表单对gradeId的校验
                "fullName": {
                    required: true
                }
            },
            messages: {}
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            //alert($("#leixing").val())
            var loader = new loadLayer();
            var $id = $("#id").val();
            //$("#classDesc").val($("#classDesc").val().replace(/\n/g, "&#10;").replace(/\s/g, '&nbsp;'));
            var $requestData = formData2JSONObj("#publicClass_form");
            // 处理textarea换行
            var url = "${ctp}/GuangBo/updateDaPing";
            $requestData.id=$id;
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    if ("success" === data) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                } else {
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }


</script>
</html>