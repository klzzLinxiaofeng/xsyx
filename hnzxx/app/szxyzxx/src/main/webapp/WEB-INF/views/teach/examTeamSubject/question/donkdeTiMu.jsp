<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        .chzn-container .chzn-results{
            max-height:120px;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid ">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form   id="downLoadForm" class="form-horizontal left-align form-well" novalidate="novalidate">
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>学年：</label>
                        <div class="controls">
                            <select id="schoolYear" disabled  name="schoolYear" class="chzn-select"style="width:220px;">

                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>学期：</label>
                        <div class="controls">
                            <select id="termCode" disabled name="termCode" class="chzn-select"style="width:220px;" ></select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>年级：</label>
                        <div class="controls">
                            <select id="gradeId" disabled name="gradeId" class="chzn-select" style="width:220px;"></select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>班级：</label>
                        <div class="controls">
                            <select id="teamId" disabled name="teamId" class="chzn-select" style="width:220px;" ></select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>考试类型：</label>
                        <div class="controls">
                            <select id="examType" disabled name="examType" class="chzn-select"style="width:220px;" >
                                <c:if test="${exam.examType==1}">
                                    <option value="${exam.examType}">期中考试</option>
                                </c:if>
                                <c:if test="${exam.examType==2}">
                                    <option value="${exam.examType}">期末考试</option>
                                </c:if>
                                <c:if test="${exam.examType==3}">
                                    <option value="${exam.examType}">平时考试</option>
                                </c:if>
                                <c:if test="${exam.examType==4}">
                                    <option value="${exam.examType}">单元测试</option>
                                </c:if>

                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>考试名称：</label>
                        <div class="controls">
                            <select id="examName" disabled name="examName" class="chzn-select" style="width:220px;">
                                <option value="${exam.examName}">${exam.examName}</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><span class="red">*</span>科目：</label>
                        <div class="controls">
                            <select id="subjectCode" disabled name="subjectCode" class="chzn-select" style="width:220px;">

                            </select>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button"
                                onclick="downLoadModel();">下载成绩模板</button>
                        <button class="btn btn-warning" type="button"
                                onclick="daoru();">导入</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    var checkerSelect;
    $(function () {
        initSelect();
    })


    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "schoolYear", "year", "name",${exam.schoolYear})
        var asds=${exam.term};
        addOption('/teach/schoolTerm/list/json?schoolYear='+${exam.schoolYear}, "termCode", "code", "name",asds);
       //因查询年级不需学期，所以不需在学期填充后的回调中执行
        addOption('/teach/grade/list/json?schoolYear=' + ${exam.schoolYear}, "gradeId", "id", "name",${exam.gradeId})
        //添加班级
        addOption('/teach/team/list/json?enableRole=false&gradeId=' + ${exam.gradeId}, "teamId", "id", "name",${exam.teamId})
        addOption("/literacy/findExamSubject?nj="+${exam.gradeId}, "subjectCode", "code","subjectName",${exam.subjectCode})



    }

    function addOption(url, id, valProperty, namePropety,addd, callback) {

        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(id=="termCode"){
                    var arr=obj[valProperty].split("-");
                    var asd=parseInt(arr[0])-parseInt(arr[1])-parseInt(arr[2])
                    if (addd == asd) {
                        $("#" + id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    } else {
                        $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    }
                }else {
                    if (addd == obj[valProperty]) {
                        $("#" + id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    } else {
                        $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    }
                }
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    function initValidatorSelect() {
        return $("#downLoadForm").validate({
            errorClass : "myerror",
            rules : {
                "schoolYear" : {
                    required : true
                },
                "termCode" : {
                    required : true
                },
                "gradeId" : {
                    required : true
                },
                "teamId" : {
                    required : true
                },
                "examType" : {
                    required : true
                },
                "examName" : {
                    required : true
                },
                "subjectCode" : {
                    required : true
                }
            },
            messages : {

            }
        });
    }


    function downLoadModel() {

        checkerSelect = initValidatorSelect();
        if (checkerSelect.form()) {
            var loader = new loadLayer();
            var xn=$("#schoolYear").val();
            var xq=$("#termCode").val();
            var gradeId=$("#gradeId").val();
            var teamId=$("#teamId").val();
            var subjectCode=$("#subjectCode").val();
            var examType=$("#examType").val();
            var examCode=$("#examName").val();
            var url = "${ctp}/examquestion/downLoadChnegJiScore?examId=${examId}&xn="+xn+"&xq="+xq+"" +
                "&gradeId="+gradeId+"&teamId="+teamId+"&subjectCode="+subjectCode+"&examType="+examType+"&examCode="+examCode;
            loader.show();
            $.get(url, function(data, status) {

                if("success" === status) {
                    //乱码问题尚未解决  执行到451行报错  执行不下去了
                    $.success('下载成功');
                    loader.close();
                    window.open(url);
                }else{
                    $.error("服务器异常");
                }

            });

            loader.close();
        }
    }

    function daoru() {
        if(${fenzhiScore!="error"}){
        var loader = new loadLayer();
        var xn=$("#schoolYear").val();
        var xq=$("#termCode").val();
        var gradeId=$("#gradeId").val();
        var teamId=$("#teamId").val();
        var subjectCode=$("#subjectCode").val();
        var examType=$("#examType").val();
        var examCode=$("#examName").val();
        var url = "${ctp}/examquestion/findByDaoRu?examId=${examId}&xn="+xn+"&xq="+xq+"" +
            "&gradeId="+gradeId+"&teamId="+teamId+"&subjectCode="+subjectCode+"&examType="+examType+"&examCode="+examCode;
        $.initWinOnTopFromLeft('导入成绩',url, '800', '450');
        $.closeWindow();
        }else{
            $.error("还有题目未设置好分值");
        }
    }


</script>
</body>

</html>