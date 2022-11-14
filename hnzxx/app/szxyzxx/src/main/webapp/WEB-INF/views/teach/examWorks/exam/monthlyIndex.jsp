<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <title>期末考试</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>添加 ${name} 考试信息</p>
        <a href="javascript:void(0)" class="btn btn-green" onclick="toIndex();">返回</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq">${title}</p>
        </div>
        <div class="kwgl_main">
            <div class="ks_list" style="padding-top:20px;">
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead>
                        <tr>
                            <c:choose>
                                <c:when test="${type == '03'}"><th>考试次份</th></c:when>
                                <c:otherwise><th>考试月份</th></c:otherwise>
                            </c:choose>
                            <th>考试名称</th>
                            <th>考试时间段</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${examWorksList}" var="item">
                        <tr>
                            <td class="count" data-count="${item.examRound}">${item.examRound}</td>
                            <td>${item.name}</td>
                            <td>
                                <fmt:formatDate value="${item.examDateBegin}" pattern="yyyy/MM/dd" type="date"/>
                                 -
                                <fmt:formatDate value="${item.examDateEnd}" pattern="yyyy/MM/dd" type="date"/>
                            </td>
                            <td><button class="btn btn-orange" onclick="toModifyPage('${item.id}')">修改</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="qd-btn">
                    <button class="btn btn-green" onclick="toAddPage();">添加</button>
                    <button class="btn btn-lightGray" style="display: none;">添加</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    $(function(){
        $(".ks_list table tbody tr a").click(function(){
            $(this).toggleClass("on");
        })
    })

    function toIndex() {
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/index";
    }

    function toAddPage(){
        var rounds = "";
        $(".count").each(function () {
            rounds += $(this).data("count") + ",";
        });
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/addPage?type=${type}&year=${year}&termCode=${termCode}&rounds=" + rounds;
    }

    function toModifyPage(id) {
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/modifyPage?type=${type}&year=${year}&termCode=${termCode}&examWorksId=" +id;
    }



    $(function () {
        var type = "${type}";
        if (type == "03"){
            $(".count").each(function () {
                var num = $(this).text();
                $(this).text("第" + NumberToChinese(num) + "次");
            });
        }
        if (type == "12"){
            $(".count").each(function () {
                var num = $(this).text();
                $(this).text(NumberToChinese(num) + "月份");
            });
        }

        //判断是否可添加
        var count = "${count}";
        var length = $(".ks_list table tbody tr").length;
        if (length >= count) {
            $(".qd-btn .btn-lightGray").show();
            $(".qd-btn .btn-green").hide();
        } else {
            $(".qd-btn .btn-lightGray").hide();
            $(".qd-btn .btn-green").show();
        }
    });

    //数字转汉字
    var chnNumChar = ["零","一","二","三","四","五","六","七","八","九"];
    var chnUnitSection = ["","万","亿","万亿","亿亿"];
    var chnUnitChar = ["","十","百","千"];
    function NumberToChinese(num){
        var unitPos = 0;
        var strIns = '', chnStr = '';
        var needZero = false;

        if(num === 0){
            return chnNumChar[0];
        }

        while(num > 0){
            var section = num % 10000;
            if(needZero){
                chnStr = chnNumChar[0] + chnStr;
            }
            strIns = SectionToChinese(section);
            strIns += (section !== 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
            chnStr = strIns + chnStr;
            needZero = (section < 1000) && (section > 0);
            num = Math.floor(num / 10000);
            unitPos++;
        }

        return chnStr;
    }
    function SectionToChinese(section){
        var strIns = '', chnStr = '';
        var unitPos = 0;
        var zero = true;
        while(section > 0){
            var v = section % 10;
            if(v === 0){
                if(!zero){
                    zero = true;
                    chnStr = chnNumChar[v] + chnStr;
                }
            }else{
                zero = false;
                strIns = chnNumChar[v];
                strIns += chnUnitChar[unitPos];
                chnStr = strIns + chnStr;
            }
            unitPos++;
            section = Math.floor(section / 10);
        }
        return chnStr;
    }

</script>
</html>