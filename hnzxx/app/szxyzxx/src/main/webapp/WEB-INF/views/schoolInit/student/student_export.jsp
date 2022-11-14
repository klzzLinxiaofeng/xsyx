<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/schoolInitDwr.js"></script>
    <title>学生信息导出</title>
    <style>
        .titles {
            padding: 0px 0 10px 0;
        }
    </style>
</head>
<body style="background-color: #e3e3e3;">
<div class="student_export">
    <div class="student_export_top">
        <div class="f_left">
            <ul>
                <c:forEach items="${result }" var="stage">
                    <li code="${stage.code }">${stage.name }</li>
                </c:forEach>
            </ul>
        </div>
        <div class="f_right">
            <button class="btn btn-blue export" style="margin-top: 8px;">导出</button>
        </div>
        <div class="clear"></div>
    </div>
    <div class="nj_content">
        <jsp:include page="./grade_list.jsp"></jsp:include>
    </div>
</div>
<div class="export_student_message" style="display:none;padding: 30px;">
    <p>已选择<span style="color: #2da1f8;" id="xsmd"></span>的学生名单</p>
    <p style="margin-bottom: 10px;">请选择导出内容：</p>
    <div class="select_div">
        <span>学生状态：</span>
        <select id="studyState">
            <option value="01">在读</option>
            <option value="07">毕业</option>
            <option value="03">退学</option>
            <option value="10">转学</option>
        </select>
    </div>
    <div class="dcnr">


        <%-- 基本信息 --%>
        <div class="titles">基本信息</div>

        <%--        <span><i class="ck on"></i>年级</span>--%>
            <span><i class="ck on"></i>学生账号</span>
            <span><i class="ck on"></i>家长登录账号</span>
            <span><i class="ck on"></i>家长登录手机号</span>
        <%--        <span><i class="ck on"></i>班级</span>--%>
        <%--        <span><i class="ck on"></i>班内学号</span>--%>
        <%--        <span><i class="ck on"></i>家长手机号码</span>--%>
            <span><i class="ck bnd on" style="cursor: not-allowed;"></i>学生姓名<b style="color:red;">*</b></span>
        <%--        <span><i class="ck on"></i>家长姓名</span>--%>
            <span><i class="ck on"></i>性别</span>
            <span><i class="ck on"></i>出生日期</span>
            <span><i class="ck on"></i>出生地</span>
            <span><i class="ck on"></i>籍贯</span>
            <span><i class="ck on"></i>民族</span>
            <span><i class="ck on"></i>国籍/地区</span>
            <span><i class="ck on"></i>身份证件类型</span>
            <span><i class="ck on"></i>身份证件号</span>
            <span><i class="ck on"></i>港澳台侨外</span>
            <span><i class="ck on"></i>政治面貌</span>
            <span><i class="ck on"></i>健康状况</span>
<%--        <span><i class="ck on"></i>血型</span>--%>

            <div class="clear"></div>
    </div>

    <div class="dcnr">
        <div class="titles">辅助信息</div>
        <span><i class="ck on"></i>姓名拼音</span>
        <span><i class="ck on"></i>曾用名</span>
        <span><i class="ck on"></i>身份证有效期</span>
        <span><i class="ck on"></i>户口所在地</span>
        <span><i class="ck on"></i>户口性质</span>
        <span><i class="ck on"></i>特长</span>
        <span><i class="ck on"></i>卡号</span>
        <div class="clear"></div>
    </div>

    <div class="dcnr">
        <div class="titles">学籍信息</div>
        <span><i class="ck on"></i>学籍辅号</span>
        <span><i class="ck on"></i>班内学号</span>
        <span><i class="ck on"></i>年级</span>
        <span><i class="ck on"></i>班级</span>
        <span><i class="ck on"></i>入学时间</span>
        <span><i class="ck on"></i>入学方式</span>
        <span><i class="ck on"></i>就读方式</span>
        <span><i class="ck on"></i>学生来源</span>
        <span><i class="ck on"></i>离校时间</span>
        <div class="clear"></div>
    </div>


    <div class="dcnr">
        <div class="titles">联系方式</div>
        <span><i class="ck on"></i>现住址</span>
        <span><i class="ck on"></i>通信地址</span>
        <span><i class="ck on"></i>家庭地址</span>
        <span><i class="ck on"></i>联系电话</span>
        <span><i class="ck on"></i>邮政编码</span>
        <span><i class="ck on"></i>电子邮箱</span>
        <span><i class="ck on"></i>主页地址</span>
        <div class="clear"></div>
    </div>

    <div class="dcnr">
        <div class="titles">扩展信息</div>
        <span><i class="ck on"></i>是否独生子女</span>
        <span><i class="ck on"></i>是否受过学前教育</span>
        <span><i class="ck on"></i>是否留守儿童</span>
        <span><i class="ck on"></i>是否进城务工人员随迁子女</span>
        <span><i class="ck on"></i>是否孤儿</span>
        <span><i class="ck on"></i>是否烈士或优抚子女</span>
        <span><i class="ck on"></i>随班就读</span>
        <span><i class="ck on"></i>残疾人类型</span>
        <span><i class="ck on"></i>是否由政府购买学位</span>
        <span><i class="ck on"></i>是否需要申请资助</span>
        <span><i class="ck on"></i>是否享受一补</span>
        <div class="clear"></div>
    </div>

    <div class="dcnr">
        <div class="titles">交通方式</div>
        <span><i class="ck on"></i>上下学距离</span>
        <span><i class="ck on"></i>上下学交通方式</span>
        <span><i class="ck on"></i>是否需要乘坐校车</span>
        <div class="clear"></div>
    </div>

    <div class="dcnr">
        <div class="titles">家庭成员-</div>
        <span><i class="ck on"></i>家庭成员或监护人姓名一</span>
        <span><i class="ck on"></i>关系一</span>
        <span><i class="ck on"></i>联系电话一</span>
        <span><i class="ck on"></i>身份证件号一</span>
        <span><i class="ck on"></i>关系说明一</span>
        <span><i class="ck on"></i>是否监护人一</span>
        <span><i class="ck on"></i>民族一</span>
        <span><i class="ck on"></i>身份证类型一</span>
        <span><i class="ck on"></i>工作单位一</span>
        <span><i class="ck on"></i>职务一</span>
        <span><i class="ck on"></i>现住址一</span>
        <span><i class="ck on"></i>户口所在地一</span>
        <div class="clear"></div>
    </div>

    <div class="dcnr">
        <div class="titles">家庭成员二</div>
        <span><i class="ck on"></i>家庭成员或监护人姓名二</span>
        <span><i class="ck on"></i>关系二</span>
        <span><i class="ck on"></i>联系电话二</span>
        <span><i class="ck on"></i>身份证件号二</span>
        <span><i class="ck on"></i>关系说明二</span>
        <span><i class="ck on"></i>是否监护人二</span>
        <span><i class="ck on"></i>民族二</span>
        <span><i class="ck on"></i>身份证类型二</span>
        <span><i class="ck on"></i>工作单位二</span>
        <span><i class="ck on"></i>职务二</span>
        <span><i class="ck on"></i>现住址二</span>
        <span><i class="ck on"></i>户口所在地二</span>
        <div class="clear"></div>
    </div>
    <div class="dcnr">
        <div class="titles">备注</div>
        <span><i class="ck on"></i>备注</span>
        <div class="clear"></div>
    </div>

    <p style="color:#ff5252;margin-top: 10px;">备注：“学生姓名”必选</p>
</div>
</body>
<script>
    $(function () {
        dwr.engine.setActiveReverseAjax(true);
        dwr.engine.setNotifyServerOnPageUnload(true, true);
        dwr.engine.setTimeout(1000 * 60 * 60);
        schoolInitDwr.onPageLoad("${userid}");
        $(".student_export_top ul li").eq(0).click();
    })

    $('.student_export_top ul li').click(function () {
        $(this).siblings().removeClass('on');
        $(this).addClass('on');

        var index = $(this).index();

        var code = $(this).attr("code");

        $(".nj_content").load("${pageContext.request.contextPath}/student/init/grade/list?code=" + code);
    })

    $('body').on('click', '.nj_list .bj i.ck', function () {
        if ($(this).hasClass('on')) {
            $(this).removeClass('on');
            if ($(this).parents('.bj').find('i.ck').length != $(this).parents('.bj').find('i.ck.on').length) {
                $(this).parents('.bj').prev('.f_left').children('.all_choose').removeClass('on');
            }
        } else {
            $(this).addClass('on');
            if ($(this).parents('.bj').find('i.ck').length == $(this).parents('.bj').find('i.ck.on').length) {
                $(this).parents('.bj').prev('.f_left').children('.all_choose').addClass('on');
            }
        }
    });

    $('body').on('click', '.all_choose', function () {
        if ($(this).hasClass('on')) {
            $(this).removeClass('on');
            $(this).parents().next('.bj').find('.ck').removeClass('on');

        } else {
            $(this).addClass('on');
            $(this).parents().next('.bj').find('.ck').addClass('on');
        }
    })

    $('.export_student_message .dcnr .ck').click(function () {
        if (!$(this).hasClass('bnd')) {
            if ($(this).hasClass('on')) {
                $(this).removeClass('on');
            } else {
                $(this).addClass('on');
            }
        }
    })

    $('.export').click(function () {
        if ($('.nj_content .nj_list .ck').hasClass('on')) {
            var s1 = '';
            var qb1 = '';
            $('.nj_content .nj_list').each(function () {
                if ($(this).find('.all_choose').hasClass('on')) {
                    var nj = $(this).find('.f_left').text();
                    s1 = '全部班级';
                    qb1 = qb1 + ('"' + nj + '"' + s1) + " 、";
                } else {
                    if ($(this).find('.ck').hasClass('on')) {
                        var nj = $(this).find('.f_left').text();
                        $(this).find('.bj .on').each(function () {
                            s1 = s1 + $(this).parent('span').text() + '、';
                        })
                        qb1 = qb1 + ('"' + nj + '"' + s1);
                    }
                }
                $('#xsmd').text(qb1);
                s1 = "";
            })

            layer.open({
                type: 1,
                shade: [0.5, '#000'],
                shadeClose: true,
                offset: '20px',
                area: ['800px', '500px'],
                title: '导出学生信息', //不显示标题
                content: $('.export_student_message'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function () {
                    layer.close();
                },
                btn: ['确定导出'],//按钮
                btn1: function (index, layero) {
                    exportExcel();
                }
            });
        } else {
            layer.msg('请选择班级');
        }
    });

    function exportExcel() {
        var teams = new Array();
        $(".student_export .nj_content .bj .on").each(function () {
            var teamid = $(this).attr("teamid");
            teams.push(teamid);
        })

        var syllables = new Array();
        $(".export_student_message .dcnr .on").each(function () {
            var text = $(this).parent().text();
            if (text == "学生姓名*") {
                text = "学生姓名";
            }
            syllables.push(text);
        })

        var param1 = JSON.stringify(teams);
        var param2 = JSON.stringify(syllables);
        var isStutas=$("#studyState").val();
        var loader = new loadDialog();
        loader.show();

        schoolInitDwr.exportStudent(param1, param2,isStutas, {
            callback: function (excel) {
                loader.close();
                dwr.engine.openInDownload(excel);         //调用dwr中的下载函数
            },
            exceptionHandler: function (e) {
                loader.close();
                alert("导出Excel失败!");
            }
        });
    }
</script>
</html>