<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2021/10/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp" %>
<html>
<head>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>课堂行为密码设置</title>
    <style>
        #groupList,#groupLists{
            background: #f5f5f8;
            position: fixed;
            text-align:center;
            width: 400px;
            height: 300px;
            box-shadow: 0 3px 4px 0 #00000020, 0 4px 12px #00000020;
            border: 1px solid #d9d9d9;
        }
        .select_div{
            margin:30px;
            margin-bottom:130px;
        }
        .off{
            position:absolute;
            top:5px;
            right:10px;
            color: white;
        }
        /*屏幕中间*/
        #groupList,#groupLists{
            right: 45%;
            top: 30%;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    课堂行为密码设置
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <div class="select_div"><span>班级：</span>
                            <select id="bj" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="piliang()">一键修改密码</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>班级</th>
                            <th>密码</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/TeamClassPassWord/findByAll?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<input style="display: none" type="text" id="ids"/>
<input style="display: none" type="text" id="teamName"/>
<div id="groupLists" style="display: none;border: 1px dashed black">
    <div class="groupListTitle">
        <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">修改班级密码</span>
            <div class="off" onclick="offGroupSet2();">X</div></div>
        <div class="select_div"><span>新密码：</span>
            <input type="text" id="password" style="width: 120px;height: 30px;"/>
        </div>
        <button type="button" class="btn btn-primary" onclick="quedingshenhe()">确定</button>
    </div>
</div>
<div id="groupList" style="display: none;border: 1px dashed black">
    <div class="groupListTitle">
        <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">一键修改班级密码</span>
            <div class="off" onclick="offGroupSet();">X</div></div>
        <div class="select_div"><span>新密码：</span>
            <input type="text" id="password2" style="width: 120px;height: 30px;"/>
        </div>
        <button type="button" class="btn btn-primary" onclick="queding()">确定</button>
    </div>
</div>
<script type="text/javascript">
    function queding() {
        var password=$("#password2").val();
        if(password!=null && password!=""){
            $.get("/TeamClassPassWord/updateOrcreateTwo?passWord="+password,function (d) {
                if(d=="success"){
                    $.success("修改成功")
                    $("#groupList").attr("style", "display:none;");//隐藏div
                    search();
                }else{
                    $.error("修改失败")
                }
            })
        }else{
            $.error("请输入密码");
        }

    }
    function piliang() {
        $("#groupList").attr("style", "display:block;");//显示div
        $("#groupLists").attr("style", "display:none;");//显示div
    }
    function search() {
        var val = {};
        var nj =$("#nj").val();
        var bj =$("#bj").val();
        if (nj != null && nj != "") {
            val.gradeId = nj;
        }
        if (bj != null && bj != "") {
            val.teamId = bj;
        }
        var id = "publicClass_list_content";
        var url = "/TeamClassPassWord/findByAll?sub=list";
        myPagination(id, val, url);

    }
    function offGroupSet2(){
        $("#groupLists").attr("style", "display:none;");//隐藏div
    }
    function offGroupSet(){
        $("#groupList").attr("style", "display:none;");//隐藏div
    }
    function quedingshenhe() {
        var id=$("#ids").val();
        var teamName=$("#teamName").val();
        var password=$("#password").val();
        if(password!=null && password!=""){
            $.get("/TeamClassPassWord/updateOrcreate?teamId="+id+"&teamName="+teamName+"&passWord="+password,function (d) {
                if(d=="success"){
                    $.success("修改成功")
                    $("#groupLists").attr("style", "display:none;");//隐藏div
                    search();
                }else{
                    $.error("修改失败")
                }
            })
        }else{
            $.error("请选择维修工");
        }

    }


    function xiugaimima(id,name){
        $("#ids").val(id);
        $("#teamName").val(name);
        $("#groupLists").attr("style", "display:block;");//显示div
        $("#groupList").attr("style", "display:none;");//显示div
    }

    $(function () {
        initSelect();
    })

    function initSelect() {
        //因查询年级不需学期，所以不需在学期填充后的回调中执行
        addOption('/teach/grade/list/json?schoolYear='+${schoolYear}, "nj", "id", "name")
    }
    //绑定下拉框改变事件
    $("#nj").change(function(){
        $("#bj").html('<option value="">全部</option>');
        if($(this).val()!=null && $(this).val()!=""){
            addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
        }
    })
    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
</script>
</body>
</html>
