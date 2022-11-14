<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <c:if test="${wareHousing.id==null}">
                        <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请物资</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                    </c:if>
                    <c:if test="${wareHousing.id!=null}">
                        <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">修改物资</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                    </c:if>
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
                                        <input type="text" style="display: none" id="shenqingren" name="shenqingren" value="${userInfo.id}"/>
                                            <input type="text" readonly id="shenqingName" name="shenqingName" value="${userInfo.realName}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            审批人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="shenheId"  name="shenheId" style="display:none;" value="${wareHousing.shenheId}"/>
                                        <input type="button" value="${wareHousing.shenheName}" onclick="xuanze('shenheId','shenheName','div3')"  id="shenheName" name="shenheName" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <div  class="techerId" id="div3" style="display: none;height: 300px">
                                            <input type="text" size="16" placeholder="名称" class="input-medium" id="searchWord3">
                                            <button type="button" class="btn" onclick="search3('biao3','reviewer','reviewerName','div3');"><i class="fa fa-search" class="span6 left-stripe {required : true,maxlength:40}"></i></button>
                                            <table id="biao3" style="height: 200px;overflow: auto">

                                            </table>
                                        </div>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            仓储负责人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="fuzeren"  name="fuzeren" style="display:none;" value="${wareHousing.fuzeren}"/>
                                        <input type="button" value="${wareHousing.fuzerenName}" onclick="xuanze('fuzeren','fuzerenName','div4')"  id="fuzerenName" name="fuzerenName" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <div  class="techerId" id="div4" style="display: none;height: 300px">
                                            <input type="text" size="16" placeholder="名称" class="input-medium" id="searchWord4">
                                            <button type="button" class="btn" onclick="search4('biao4','fuzeren','fuzerenName','div4');"><i class="fa fa-search" class="span6 left-stripe {required : true,maxlength:40}"></i></button>
                                            <table id="biao4" style="height: 200px;overflow: auto">

                                            </table>
                                        </div>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            物资类型<span style="color:red">*</span>：
                                        </label>
                                            <select id="type" name="type" class="span4"
                                                    style="width:200px;" value="${wareHousing.type}">
                                                    <option value="1">办公用品</option>
                                                    <option value="2">书籍</option>
                                                    <option value="3">防疫物资</option>
                                                    <option value="4">其他</option>
                                            </select>
                                                <input type="text" style="display: none" id="typeName"/>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            物资名称<span style="color:red">*</span>：
                                        </label>
                                        <div>
                                        <input type="text" id="WuZiName"  name="WuZiName"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            申请时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="createDate" name="createDate" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                               placeholder="xxxx-xx-xx xx:xx">
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            备注<span style="color:red">*</span>：
                                        </label>
                                        <div class="left">
                                            <div class="textarea">
                                            <textarea style="width:200px;height:100px;padding-top: 4px;"  type="text" id="beizhu" name="beizhu" class="span4" autocomplete="off"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            是否需要归还：
                                        </label>
                                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                                <input type="radio" name="isGuihuan"
                                                       value="1" Checked />
                                                是 </label>
                                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                                <input type="radio" name="isGuihuan" value="0"/>
                                                否 </label>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;" onclick="saveOrUpdate();">确定</button>
                    </p>
                    <input type="hidden" id="id"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    function xuanze(idd,namedd,divs){
        $("#"+divs).attr("style", "display:block;");
        if(divs=="div4"){
            $("#div3").attr("style", "display:none;");
            $("#biao3").html('');
            $("#biao4").html('');
            search4('biao4',idd,namedd,divs);
        }
        if(divs=="div3"){
            $("#div4").attr("style", "display:none;");
            $("#biao3").html('');
            $("#biao4").html('');
            search3('biao3',idd,namedd,divs)
        }
    }
    function search4(biao,idd,namedd,divs){
        $("#"+biao).html('');
        var text=$("#searchWord4").val();
        gelAllTeacher(text,biao,idd,namedd,divs);
    }

    function search3(biao,idd,namedd,divs){
        $("#"+biao).html('');
        var text=$("#searchWord3").val();
        gelAllTeacher3(text,biao,idd,namedd,divs);
    }

    function gelAllTeacher3(searchWord,biao,idd,namedd,divs){
        var url="/wareHousing/findByShenpiYuan";
        if(searchWord!=null && searchWord!=''){
            url+="?name="+searchWord;
        }
        $.get(url,function (d) {
            var dd=JSON.parse(d);
            for(var i=0;i<dd.length;i++){
                var tr = "<tr><td><button type='button' id='td_"+dd[i]['user_id']+"'>"+dd[i]['name']+"</button></td></tr>";
                $("#"+biao).append(tr);
                $("#td_"+dd[i]['user_id']).attr("onclick","quedingqq('"+dd[i]['user_id']+"','"+dd[i]['name']+"','"+idd+"','"+namedd+"','"+divs+"','"+biao+"')");
            }
        })
    }
    function gelAllTeacher(searchWord,biao,idd,namedd,divs){
        var url="/wareHousing/findByCangChuYuan";
        if(searchWord!=null && searchWord!=''){
            url+="?name="+searchWord;
        }
        $.get(url,function (d) {
            var dd=JSON.parse(d);
            for(var i=0;i<dd.length;i++){
                var tr = "<tr><td><button type='button' id='td_"+dd[i]['user_id']+"'>"+dd[i]['name']+"</button></td></tr>";
                $("#"+biao).append(tr);
                $("#td_"+dd[i]['user_id']).attr("onclick","quedingqq('"+dd[i]['user_id']+"','"+dd[i]['name']+"','"+idd+"','"+namedd+"','"+divs+"','"+biao+"')");
            }
        })
    }
    function quedingqq(userid,teacherName,idd,namedd,divs,biao){
        $("#"+idd).val(userid);
        $("#"+namedd).val(teacherName);
        $("#"+divs).attr("style", "display:none;");
        $("#"+biao).html('');
    }
    $("#type").change(function(){
       var type=$("#type").val();
        //添加kemu
        if(type==4) {
            $("#typeName").show();
        }else{
            $("#typeName").hide();
        }
    })

    //保存或更新修改
    function saveOrUpdate() {
        var val={};
        var loader = new loadLayer();
        var $id = $("#id").val();
        val.shenqingren=$("#shenqingren").val();
        val.shenheId=$("#shenheId").val();
        val.shenqingName=$("#shenqingName").val();
        val.shenheName=$("#shenheName").val();
        val.fuzeren=$("#fuzeren").val();
        val.fuzerenName=$("#fuzerenName").val();
        val.type=$("#type").val();
        if($("#type").val()!=4){
            val.typeName=$("#type option:selected").text();
        }else{
            val.typeName=$("#typeName").val();
        }
        val.name=$("#WuZiName").val();
        val.createDate=$("#createDate").val();
        var beizhu=$("#beizhu").val();
        var obj = document.getElementsByName("isGuihuan");
        for(var i = 0; i < obj.length; i ++) {
            if (obj[i].checked) {
                val.isGuihuan= obj[i].value;
            }
        }
        var panduans=panduan(val);
        if(panduans=='true'){
            val.beizhu=beizhu;
            var url="";
            if($id!=null && $id!=''){
               url= "/wareHousing/update";
            }else{
                url="/wareHousing/create";
            }
            loader.show();
            $.post(url, val, function (data, status) {
                if (status=="success") {
                    if ("success" === data) {
                        $.success('操作成功');
                        window.location.href="${ctp}/wareHousing/findByAll?sub=asd";
                    } else {
                        $.error("操作失败");
                    }
                }
                loader.close();
            });
        }else{
            $.error(panduans);
        }

    }
    function panduan(val) {
        if(val.shenqingren==null || val.shenqingren==""){
            return "申请人id不能为空";
        }
        if(val.shenqingName==null || val.shenqingName==""){
            return "申请人不能为空";
        }
        if(val.shenheId==null || val.shenheId==""){
            return "审核人id不能为空";
        }
        if(val.shenheName==null || val.shenheName==""){
            return "审核人姓名不能为空";
        }
        if(val.fuzeren==null || val.fuzeren==""){
            return "负责任人id不能为空";
        }
        if(val.fuzerenName==null || val.fuzerenName==""){
            return "负责任人姓名不能为空";
        }
        if(val.type==null || val.type==""){
            return "物资类型不能为空";
        }
        if(val.typeName==null || val.typeName==""){
            return "物资类型名称不能为空";
        }
        if(val.name==null || val.name==""){
            return "物资名称不能为空";
        }
        if(val.createDate==null || val.createDate==""){
            return "创建时间不能为空";
        }
        if(val.isGuihuan==null || val.isGuihuan==""){
            return "是否需要归还不能为空";
        }
        return "true";
    }
</script>
</body>
</html>
