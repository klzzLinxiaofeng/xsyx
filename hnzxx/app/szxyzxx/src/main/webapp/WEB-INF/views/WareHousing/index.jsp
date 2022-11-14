<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>仓储管理</title>
    <style type="text/css">
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
        div#div3, div#div2, div#div1, div#div4 {z-index:999}
        button.btn.btn-primary {
            height: 30px;
            margin-left: 10px;
            background: #0d7bd5;
            padding: 0 15px;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    仓储管理
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="loadCreatePage();" class="a2" >申请物资</a>
                        <a   class="a2" href="#" onclick="daochu();" class="a2" >导出仓储列表</a>

                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                <div class="select_b">
                    <div class="select_div">
                        <span>申请人：</span>
                        <input id="shenqingName" name="shenqingName" class="" style="width:180px;padding-top: 4px;"/>
                    </div>
                    <div class="select_div">
                        <span>物资类型：</span>
                        <select id="type" name="type">
                            <option value="">请选择</option>
                            <option value="1">办公用品</option>
                            <option value="2">书籍</option>
                            <option value="3">防疫物资</option>
                            <option value="4">其他</option>
                        </select>
                    </div>
                    <div class="select_div">
                        <span>名称：</span>
                        <input id="name" name="name" class=""  style="width:180px;padding-top: 4px;"/>
                    </div>
                    <div class="select_div">
                        <span>申请时间：</span>
                        <input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off"
                               style="width:180px;padding-top: 4px;"
                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                               placeholder="xxxx-xx-xx"/>-
                        <input type="text" id="kaishiTime2" name="kaishiTime2" class="chzn-select" autocomplete="off"
                               style="width:180px;padding-top: 4px;"
                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                               placeholder="xxxx-xx-xx"/>
                    </div>
                    <div class="select_div">
                        <span>状态：</span>
                      <select id="zhuantai" name="zhuantai">
                            <option value="">请选择</option>
                          <option value="0">待审批</option>
                          <option value="1">审批通过</option>
                          <option value="2">审批未通过</option>
                          <option value="3">归还待审批</option>
                          <option value="4">已归还</option>
                          <option value="5">归还驳回</option>


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
                        <th>物资类型</th>
                        <th>物资名称</th>
                        <th>申请时间</th>
                        <th>审核人</th>
                        <th>状态</th>
                        <th class="caozuo" style="max-width: 250px;">操作</th>
                    </tr>
                    </thead>
                    <tbody id="publicClass_list_content">
                    <jsp:include page="./list.jsp"/>
                    </tbody>
                </table>
                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                    <jsp:param name="id" value="publicClass_list_content"/>
                    <jsp:param name="url" value="/wareHousing/findByAll?sub=list"/>
                    <jsp:param name="pageSize" value="${page.pageSize}"/>
                </jsp:include>
                <div class="clear"></div>
            </div>
            </div>
        </div>
    </div>
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
        <button type="button" class="btn btn-primary" onclick="tongguo()">同意</button>
        <button type="button" class="btn btn-primary" onclick="bohui()">驳回</button>
    </div>
    <input id="idssss" style="display: none"/>

</div>
<div id="groupList" style="display: none;border: 1px dashed black">
    <div class="groupListTitle">
        <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">审核</span>
            <div class="off" onclick="offGroupSet();">X</div></div>
        <div class="select_div"><span>拒绝理由：</span>
            <input type="text" id="guihuanliyou" style="width: 120px;height: 30px;"/>
        </div>
        <button type="button" class="btn btn-primary" onclick="guihuanTongguo()">同意</button>
        <button type="button" class="btn btn-primary" onclick="guihuanBohui()">驳回</button>
    </div>
    <input id="idsss" style="display: none"/>

</div>
<script type="text/javascript">
    //申请审批
    function offGroupSet2(){
        $("#groupLists").attr("style", "display:none;");//隐藏div
    }
    function shenpi(id) {
        $("#idssss").val(id);
        $("#groupLists").attr("style", "display:block;");//隐藏div
    }
    function tongguo() {
        var id=$("#idssss").val();
        var zhuantai=1;
        var str="您的物资申请已通过";
        $.get("/wareHousing/updateShenpi?zhuantai="+zhuantai+"&id="+id+"&str="+str,function (d) {
            if(d=="success"){
                 $.success("审批完成");
                 search();
                $("#groupLists").attr("style", "display:none;");//隐藏div
            }else{
                $.success("审批失败");
            }
        })
    }
    function bohui() {
        var id=$("#idssss").val();
        var zhuantai=2;
        var liyou=$("#liyou").val();
        var str="您的物资申请已驳回";
        if(liyou!=null && liyou!=""){
            $.get("/wareHousing/updateShenpi?zhuantai="+zhuantai+"&id="+id+"&liyou="+liyou+"&str="+str,function (d) {
                if(d=="success"){
                    $.success("审批完成");
                    search();
                    $("#groupLists").attr("style", "display:none;");//隐藏div
                }else{
                    $.success("审批失败");
                }
            })
        }else{
            $.error("请输入驳回理由");
        }

    }
function guihuan(id) {
    $.confirm("确定执行此次操作？", function() {
        queguihuan(id);
    });
}
    //归还
    function queguihuan(id) {
        var str="您有一个物资归还待审核";
        $.get("/wareHousing/updateShenpi?zhuantai=3&id="+id+"&str="+str,function (d) {
            if(d=="success"){
                $.success("等待负责人审批");
                search();
            }else{
                $.success("失败");
            }
        })
    }
    function chongxinguihuan(id) {
        $.confirm("确定执行此次操作？", function() {
            chongxinguihuan2(id);
        });
    }
   function  chongxinguihuan2(id){
       $.get("/wareHousing/updateShenpi?zhuantai=3&id="+id,function (d) {
           if(d=="success"){
               $.success("等待负责人审批");
               search();
           }else{
               $.success("失败");
           }
       })
   }

    //归还审批
    function offGroupSet(){
        $("#groupList").attr("style", "display:none;");//隐藏div
    }
    function guihuanShenpi(id) {
        $("#idsss").val(id);
        $("#groupList").attr("style", "display:block;");//显示div
    }
    function guihuanTongguo() {
        var id=$("#idsss").val();
        var zhuantai=4;
        var str="您的物资归还已通过";
        $.get("/wareHousing/updateShenpi?zhuantai="+zhuantai+"&id="+id+"&str="+str,function (d) {
            if(d=="success"){
                $.success("审批完成");
                search();
                $("#groupList").attr("style", "display:none;");//隐藏div
            }else{
                $.success("审批失败");
            }
        })
    }
    function guihuanBohui() {
        var id=$("#idsss").val();
        var zhuantai=5;
        var liyou=$("#guihuanliyou").val();
        if(liyou!=null && liyou!=""){
            var str="您的物资归还已驳回";
            $.get("/wareHousing/updateShenpi?zhuantai="+zhuantai+"&id="+id+"&guihuanLiyou="+liyou+"&str="+str,function (d) {
                if(d=="success"){
                    $.success("审批完成");
                    search();
                    $("#groupList").attr("style", "display:none;");//隐藏div
                }else{
                    $.success("审批失败");
                }
            })
        }else{
            $.error("请输入驳回理由");
        }

    }
    //查询
    function search() {
        var val = {};
        var shenqingName=$("#shenqingName").val();
        var type=$("#type").val();
        var name=$("#name").val();

        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        var zhuantai=$("#zhuantai").val();
        if(shenqingName!=null && shenqingName!=''){
            val.shenqingrenName=shenqingName;
        }
        if(type!=null && type!=''){
            val.type=type;
        }
        if(name!=null && name!=''){
            val.name=name;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            val.startTime=kaishiTime;
        }
        if(kaishiTime2!=null && kaishiTime2!=''){
            val.endTime=kaishiTime2;
        }
        if(zhuantai!=null && zhuantai!=''){
            val.zhuangtai=zhuantai;
        }

        var id = "publicClass_list_content";
        var url = "/wareHousing/findByAll?sub=list";
        myPagination(id, val, url);
    }

    // 	加载创建对话框
    function loadCreatePage() {
        window.location.href="${ctp}/wareHousing/createOrUpdate";
    }
    //  加载编辑对话框
    function bianji(id) {
        window.location.href = "${ctp}/wareHousing/createOrUpdate?id=" + id;
    }
    //  加载详情对话框
    function xiangqing(id) {
        window.location.href = "${ctp}/wareHousing/fingById?id=" + id;
    }

    // 	删除对话框
    function shanchu(id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(id);
        });
    }

    // 	执行删除
    function executeDel(id) {
        $.post("${ctp}/wareHousing/delete?id=" + id, function(data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    search();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }

    function daochu() {
        var url="/wareHousing/findByWareHousingExport?url=asdf";
        var shenqingName=$("#shenqingName").val();
        if(shenqingName!=null && shenqingName!=""){
            url+="&shenqingrenName="+shenqingName;
        }
        var type=$("#type").val();
        if(type!=null && type!=""){
            url+="&type="+type;
        }
        var name=$("#name").val();
        if(name!=null && name!=""){
            url+="&name="+name;
        }
        var kaishiTime=$("#kaishiTime").val();
        if(kaishiTime!=null && kaishiTime!=""){
            url+="&startTime="+kaishiTime;
        }
        var kaishiTime2=$("#kaishiTime2").val();
        if(kaishiTime2!=null && kaishiTime2!=""){
            var data=new Date(kaishiTime2);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate()+1;
            url+="&endTime="+year+"-"+mounth+"-"+day;
        }
        var zhuantai=$("#zhuantai").val();
        if(zhuantai!=null && zhuantai!=""){
            url+="&zhuangtai="+zhuantai;
        }
        $.success("导出成功");
        window.open(url);
    }
</script>
</body>

</html>