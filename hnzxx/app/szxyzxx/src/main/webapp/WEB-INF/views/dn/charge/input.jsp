<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp"%>
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
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="charge_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <input type="text" id="gradeId" name="gradeId" class="span13" placeholder="" readonly="readonly" value="${chargeVo.gradeName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            班级：
                        </label>
                        <div class="controls">
                            <input type="text" id="teamId" name="teamId" class="span13" placeholder="" readonly="readonly" value="${chargeVo.teamName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            姓名：
                        </label>
                        <div class="controls">
                            <input type="text" id="studentName" name="studentName" class="span13" placeholder="" readonly="readonly" value="${chargeVo.studentName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            项目：
                        </label>
                        <div class="controls">
                            <input type="text" id="itemId" name="itemId" class="span13" placeholder="" readonly="readonly" value="${chargeVo.itemName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            缴费：
                        </label>
                        <div class="controls">
                            <input type="text" id="amount" name="amount" class="span13" placeholder="" maxlength="11" value="${chargeVo.amount}">
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${chargeVo.id}" />
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var checker;
    $(function() {
        checker = initValidator();
        $("#amount").keyup(function(event){
            var keycode = event.which;
            if (keycode != 37&&keycode != 38&&keycode != 39&&keycode != 40) {
                //匹配负号，数字
                this.value = this.value.replace(/[^\d.]/g, "");
                //匹配第一个输入的字符不是小数点
                this.value = this.value.replace(/^\./g, "");
                //只能输入两个小数
                this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
                //保证.-只出现一次，而不能出现两次以上
                this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
                this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
                //保证-号只能是第一位
            }
        });
    });

    function initValidator() {
        jQuery.validator.addMethod("minNumber",function(value, element){
            var returnVal = true;
            inputZ=value;
            var ArrMen= inputZ.split(".");    //截取字符串
            if(ArrMen.length==2){
                if(ArrMen[1].length>2){    //判断小数点后面的字符串长度
                    returnVal = false;
                    return false;
                }
            }
            return returnVal;
        },"小数点后最多为两位");

        return $("#charge_form").validate({
            errorClass : "myerror",
            rules : {
                "amount" : {
                    number: true,
                    min : 0.01,
                    minNumber : $("#amount").val()
                }
            },
            messages : {
                "amount" : {
                    number: "请输入正确的金额"
                }
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var val = {};
            val.id = $id;
            val.amount = $("#amount").val();
            val._method = "put";
            url = "${ctp}/dn/charge/" + $id;
            loader.show();
            $.post(url, val, function(data, status) {
                if("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if("success" === data.info) {
                        if(parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                }else{
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

</script>
</html>