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
    <title>维修审核</title>
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
                    维修审核管理
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
                        <div class="select_div"><span>申请人：</span>
                            <input type="text" size="16" placeholder="发布人" class="input-medium" id="searchWord">
                        </div>
                        <div class="select_div"><span>审核人：</span>
                            <select id="shenheren" name="xn" class="chzn-select"
                                    style="width:200px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>类别：</span>
                            <select id="leibie" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>是否通过审核：</span>
                            <select id="isShenhe" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                                <option value="1">已通过</option>
                                <option value="2">未通过</option>
                                <option value="0">待审核</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>申请人</th>
                            <th>标题</th>
                            <th>类别</th>
                            <th>审核人</th>
                            <th>审核状态</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./weixiushenhelist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/oa/applyrepair/shenheView?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        </div>
</div>
<div id="groupList" style="display: none;border: 1px dashed black">
    <div class="groupListTitle">
        <div style="background-color: #2f6144;height: 30px;"><span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">审核</span><div class="off" onclick="offGroupSet();">X</div></div>
        <div class="select_div"><span>维修人：</span>
            <select id="weixiuren" name="xn" class="chzn-select"
                    style="width:200px;">
            </select>
        </div>
        <button type="button" class="btn btn-primary" onclick="quedingshenhe()">确定</button>
    </div>
    <input id="ids" style="display: none"/>
    <input id="typeid" style="display: none"/>
</div>

<div id="groupLists" style="display: none;border: 1px dashed black">
    <div class="groupListTitle">
        <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">审核</span>
            <div class="off" onclick="offGroupSet2();">X</div></div>
        <div class="select_div"><span>拒绝理由：</span>
            <input type="text" id="liyou" style="width: 120px;height: 30px;"/>
        </div>
        <button type="button" class="btn btn-primary" onclick="quedingshenhe2()">确定</button>
    </div>
    <input id="idss" style="display: none"/>
</div>



<script type="text/javascript">
    function offGroupSet(){
        $("#groupList").attr("style", "display:none;");//隐藏div

    }

    //不提供调用，请勿调用（绿色title）
    initWindowBase = function (onWhere, title, url, width, height, topVal, shift) {
        if ("top" === onWhere) {
            return window.top.layer.open({
                id:'wer',
                skin: 'layui-layer-lvse', //样式类名
                type: 2,
                title: title,
                //closeBtn: false, //显示关闭按钮
                shadeClose: true,
                shade: 0.8,
                area: [width + 'px', height + 'px'],
                /*  offset:[ topVal + 'px', '' ], */
                maxmin: false, //开启最大化最小化按钮
                shift: shift,
                content: url //iframe的url，no代表不显示滚动条
                // time: 2000, //2秒后自动关闭
            });
        } else if ('cur' === onWhere) {
            return layer.open({
                /* extend: ['skin/myskin/style.css'], //加载您的扩展样式
                skin: 'layer-ext-yourskin', //一旦设定，所有弹层风格都采用此主题。 */
                skin: 'layui-layer-lvse', //样式类名
                type: 2,
                title: title,
                //closeBtn: false, //显示关闭按钮
                shadeClose: true,
                shade: 0.8,
                area: [width + 'px', height + 'px'],
                /*  offset:[ top + 'px', '' ], */
                maxmin: false, //开启最大化最小化按钮
                shift: shift,
                content: url //iframe的url，no代表不显示滚动条
            });
        }
    }

    //在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
    initWinOnTopFromLeft = function (title, url, width, height, top) {
        if (width === undefined) {
            width = $(parent.window).width() - 50;
        }
        if (height == undefined) {
            height = $(parent.window).height() - 50;
        }
        if (top == undefined) {
            /* top = '20'; */
        }
        return initWindowBase('top', title, url, width, height, top, 'left');
    }

    $(function () {
        initSelect();
    });
    function search() {
        var val = {};
        var searchWord=$("#searchWord").val();
        var shenheren=$("#shenheren").val();
        var leibie=$("#leibie").val();
        var isShenhe=$("#isShenhe").val();
            if(searchWord!=null && searchWord!=""){
                val.shenqingren=searchWord;
            }
            if (shenheren != null && shenheren != "") {
                val.shenherenId = shenheren;
            }
            if (leibie != null && leibie != "") {
               val.typeId = leibie;
            }
            if (isShenhe != null && isShenhe != "") {
               val.isShenhe = isShenhe;
            }
            var id = "publicClass_list_content";
            var url = "/oa/applyrepair/shenheView?sub=list";
            myPagination(id, val, url);
    }

    function initSelect() {
        //初始填充审核人，维修类型，
        addOption('/oa/applyrepair/findshenheren', "shenheren", "user_id", "name")
        addOption('/oa/applyrepair/typeAll', "leibie", "id", "name");

    }

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

    //查看详情
    function chakan(id) {
        initWinOnTopFromLeft('查看申请详情', '/oa/applyrepair/shenhexiangqing?id='+id, '1200', '650');
    }
    //通过审核
    function tongguo(id,typeid) {
        $("#ids").val(id);
        $("#weixiuren").html('<option value="">请选择</option>');
        addOption("/oa/applyrepair/findBaoXiuRen?typeId=" + typeid, "weixiuren", "teacher_id", "teacherName");
        $("#groupList").attr("style", "display:block;");//显示div
        $("#groupLists").attr("style", "display:none;");//显示div
    }
    function quedingshenhe() {
        var id=$("#ids").val();
        var weixiugong=$("#weixiuren").val();
        var isShenhe=1;
        if(weixiugong!=null && weixiugong!=""){
            $.get("/oa/applyrepair/shenhe?id="+id+"&weixiugong="+weixiugong+"&isShenHe="+isShenhe,function (d) {
                if(d=="success"){
                    $.success("审核成功")
                    $("#groupList").attr("style", "display:none;");//隐藏div
                    search();
                }else{
                    $.error("审核失败")
                }
            })
        }else{
            $.error("请选择维修工")
        }

    }

    //不通过审核
    function nottongguo(id) {
        $("#idss").val(id);
        //addOption("/oa/applyrepair/findBaoXiuRen?typeId=" + typeid, "weixiuren", "teacher_id", "teacherName");
        $("#groupLists").attr("style", "display:block;");//显示div
        $("#groupList").attr("style", "display:none;");//显示div
    }
    function quedingshenhe2() {
        var id=$("#idss").val();
        var isShenhe=2;
        var liyou=$("#liyou").val();
            $.get("/oa/applyrepair/shenhe?id="+id+"&liyou="+liyou+"&isShenHe="+isShenhe,function (d) {
                if(d=="success"){
                    $.success("审核成功")
                    $("#groupLists").attr("style", "display:none;");//隐藏div
                    $("#idss").val("")
                    $("#liyou").val("");
                    search();
                }else{
                    $.error("审核失败")
                }
            })
    }
</script>

</body>
</html>
