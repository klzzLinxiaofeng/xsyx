<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css" media="all">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>

    <title>学生表现</title>
    <style>
        .c1 {
            font-size: 14px;
            font-weight: bold;
        }

        .c2 {
        }
    </style>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="学生表现"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        学生表现
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i
                                    class="fa  fa-undo"></i>刷新列表</a>
                        </p>
                    </h3>
                </div>
                <%-- <div class="light_grey">
            </div>--%>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">

                            <ul class="layui-tab-title">
                                <li class="layui-this" style="font-size: 15px" data-status="0">发布表现</li>
                                <li data-status="1" style="margin-left: 10px;font-size: 15px;">历史发布</li>
                            </ul>
                            <div class="layui-tab-content" style="height: 100px;display: contents;">
                                <div class="layui-tab-item layui-show">
                                    <div class="layui-row" style="margin-top: 8px">
                                        <div class="layui-col-xs12">
                                            <div class="grid-demo grid-demo-bg1">
                                                <label class="control-label">
                                                    <font style="color: red;font-size: 17px">|</font>
                                                    <span class="c1">学生</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <span class="c2">请选择学生</span>
                                                </label>
                                            </div>
                                            <div class="controls">
                                                <textarea rows="3" cols="50" id="attendees" name="attendees"
                                                          class="span4 add_renyuan" data-id="" style="width: 60%"
                                                          placeholder="批量选择学生"></textarea>
                                                <input type="hidden" id="studentIds" name="studentIds" data-id=""
                                                       value="">
                                            </div>

                                        </div>
                                    </div>
                                    <div class="layui-row">
                                        <div class="layui-col-xs12">
                                            <label class="control-label">
                                                <font style="color: red;font-size: 17px">|</font>
                                                <span class="c1">表现情况</span> <span class="c2">请选择表现情况</span>
                                            </label>
                                            <input type="radio" name="performanceType" id="male" value="0" checked/>
                                            表扬
                                            <input type="radio" name="performanceType" id="male2" value="1"/>
                                            批评
                                        </div>
                                    </div>
                                    <div class="layui-row" style="margin-top: 5px">
                                        <div class="layui-col-xs6">
                                            <div class="grid-demo grid-demo-bg1"><label class="control-label">
                                                <font style="color: red;font-size: 17px">|</font><span
                                                    class="c1">星级评定</span> <span class="c2">请选择星级情况</span>
                                            </label>
                                            </div>
                                            <div id="stars"></div>
                                        </div>
                                    </div>
                                    <div class="layui-row">
                                        <div class="layui-col-xs6">
                                            <div class="grid-demo grid-demo-bg1"><label class="control-label">
                                                <font style="color: red;font-size: 17px">|</font><span
                                                    class="c1">表现标题</span> <span class="c2">请输入表现标题</span>
                                            </label>
                                            </div>
                                            <input type="text" id="showTitle" name="showTitle" class="span4"
                                                   placeholder=""
                                                   style="width: 70%">
                                        </div>
                                    </div>
                                    <div class="layui-row">
                                        <div class="layui-col-xs6">
                                            <div class="grid-demo grid-demo-bg1"><label class="control-label">
                                                <font style="color: red;font-size: 17px">|</font><span
                                                    class="c1">表现内容</span> <span class="c2">请输入表现内容</span>
                                            </label>
                                            </div>
                                            <textarea id="showContent"
                                                      name="showContent" rows="5"></textarea>
                                        </div>
                                    </div>

                                    <div class="layui-upload" style="margin-top: 10px">
                                        <label class="control-label">
                                            <font style="color: red;font-size: 17px">|</font>
                                            <span> 图片说明： </span><span>非必填，最多可添加9张</span>
                                        </label>
                                        <br/>
                                        <button type="button" class="layui-btn" id="test3"
                                                style="margin: 10px; margin-top: -10px">+
                                            选择图片
                                        </button>
                                        <blockquote class="layui-elem-quote layui-quote-nm"
                                                    style="margin: 10px; background-color: #efefef">
                                            <div class="layui-upload-list" id="demo3" style="margin: 10px">
                                            </div>
                                        </blockquote>
                                    </div>

                                    <div class="layui-row">
                                        <div class="layui-col-xs6">
                                            <button type="button" class="layui-btn layui-btn-radius layui-btn-warm"
                                                    id="send" style="width: 150px">发布
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="select_b">
                                        <div class="select_div"><span>学年：</span>
                                            <select id="xn" name="xn" class="chzn-select" style="width:120px;"
                                                    disabled="disabled"></select>
                                        </div>
                                        <div class="select_div"><span>年级：</span>
                                            <select id="nj" name="nj" class="chzn-select"
                                                    style="width:120px;"></select>
                                        </div>
                                        <div class="select_div">
                                            <span style="font-size: 7px; color: red">不支持年级单独查询</span>
                                        </div>

                                        <div class="select_div"><span>班级：</span><select id="bj" name="bj"
                                                                                        class="chzn-select"
                                                                                        style="width:120px;"></select>
                                        </div>

                                        <div class="select_div mobile" id="stuNames">
                                            <span>学生姓名：</span><input type="text" id="stuName" name="stuName"
                                                                     data-id-container="mobile"
                                                                     style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                                        </div>
                                        <div class="select_div mobile">
                                            <span>星级：</span>
                                            <select id="select_stars">
                                                <option value="" selected="selected">请选择</option>
                                                <option value="1">1星</option>
                                                <option value="2">2星</option>
                                                <option value="3">3星</option>
                                                <option value="4">4星</option>
                                                <option value="5">5星</option>
                                                <option value="6">6星</option>
                                                <option value="7">7星</option>
                                                <option value="8">8星</option>
                                                <option value="9">9星</option>
                                                <option value="10">10星</option>
                                            </select>
                                        </div>
                                        <button type="button" class="btn btn-primary" id="search">查询</button>


                                        <div class="clear"></div>
                                    </div>
                                    <table class="layui-hide" id="test2" lay-filter="test2"></table>
                                </div>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="barDemo2">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script type="text/javascript">

    // 所选标签页
    var systemId = 0;

    var position = '#test'
    var tool = '#barDemo'
    var starsRate = "";

    var uuidArray = new Array();
    $(function () {

        var rate = layui.rate;
        var instance = rate.render({
            elem: '#stars',
            length: 10,
            choose: function (value) {
                rate.render({
                    elem: '#stars',
                    length: 10
                    , choose: function (val) {
                        console.log(val)
                        starsRate = val;
                    }
                })
            }
        });

        $.createTecherStudentSelector({
            "inputIdSelector": "#attendees",
            "ry_type": "teacher_stu"
        });

        // initCheckBtn("jc_subject");
        layui.use(['element', 'table', 'rate'], function () {
            var $ = layui.jquery, table = layui.table, element = layui.element, rate = layui.rate;
            var util = layui.util;
            $.initCascadeSelector({
                "type": "team", "yearCallback": function ($this) {
                    getList("#test", '#barDemo');
                }
            });

            // 标签切换监听
            element.on('tab(docDemoTabBrief)', function (data) {
                var status = $(this).attr('data-status')

                switch (status) {
                    case '0':
                        position = '#test';
                        tool = '#barDemo';
                        break;
                    case '1':
                        position = '#test2';
                        tool = '#barDemo2';
                        break;
                    default:
                        position = '#test';
                        tool = '#barDemo';
                }
                getList(position, tool)
            })

            function getList(position, tool) {
                table.render({
                    elem: position
                    , url: '/student/show/json?dm=${param.dm}'
                    , parseData: function (res) { //res 即为原始返回的数据
                        return {
                            "code": 0, //解析接口状态
                            "msg": "success", //解析提示文本
                            "count": res.page.totalRows, //解析数据长度
                            "data": res.list //解析数据列表
                        };
                    }
                    , where: {
                        //where里是可以传到后台的参数
                        gradeId: $("#nj").val()
                        , teamId: $("#bj").val()
                        , stuName: $("#stuName").val()
                        , teacherName: $("#teacherName").val()
                        , stars: $('#select_stars option:selected').val()
                    }
                    , request: {
                        pageName: 'currentPage',
                        limitName: 'pageSize'
                        //页码和显示数量
                    }
                    , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        , groups: 3 //只显示 1 个连续页码
                    }
                    , cols: [[

                        // 显示参数需要修改
                        {field: 'teacherName', title: '教师', align: 'center', width: '98'}
                        , {
                            field: 'createDate', title: '发布时间', width: '200', align: 'center', templet: function (d) {
                                return util.toDateString(d.createDate);
                            }, edit: true
                        }
                        , {field: 'stuName', title: '学生姓名', sort: true, align: 'center', width: '200'}
                        , {
                            field: 'performanceType', title: '表现', width: '80', align: 'center',
                            templet: function (d) {
                                //0：表扬 1：批评
                                return d.performanceType == 0 ? '表扬' : "批评";
                            }
                        }
                        , {field: 'showTitle', title: '标题', align: 'center', width: '150'}
                        , {field: 'showContent', title: '内容', sort: true, align: 'center', width: '200'}
                        , {
                            field: 'stars', title: '星级评定', align: 'center', width: '300',
                            templet: function (d) {
                                return '<div id="avgScore' + d.id + '"></div>'
                            }
                        }
                        , {
                            field: 'url', title: '图片说明', align: 'center', templet: function (d) {
                                var str = "";
                                var srr = d.url;
                                if (srr != null && srr != '') {
                                    for (var j in srr) {
                                        str += '<div style="margin:0 10px; display:inline-block !important; display:inline;  max-width:70px; max-height:50px;"><a href="' + srr[j] + '" target=_blank><img style=" max-width:50px; max-height:50px;" src="' + srr[j] + '"/><a></div>';
                                    }
                                }
                                return str;
                            }
                            , width: 400
                        }
                    ]]
                    , done: function (res, curr, count) {
                        var data = res.data;//返回的json中data数据
                        for (var item in data) {
                            //司机星级
                            rate.render({
                                elem: '#avgScore' + data[item].id + ''         //绑定元素
                                , length: 10            //星星个数
                                , value: data[item].stars             //初始化值
                                , readonly: true
                            });

                        }
                    }
                });
            }

            //监听工具条

            table.on('tool(test2)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                $.confirm("确定执行此次操作？", function () {
                    executeDel(data.id);
                });
            });
            // 查询功能
            $("#search").bind("click", function () {
                getList(position, tool);
            });
        });

        var upload = layui.upload;
        // 宣传图片
        layui.use('upload', function () {
            var $ = layui.jquery, upload = layui.upload;
            upload.render({
                elem: '#test3',
                url: '${pageContext.request.contextPath}/uploader/common',
                data: {
                    jsessionId: function () {
                        return '<%=request.getSession().getId()%>';
                    }
                },
                accept: 'images',
                acceptMime: 'image/jpg,image/png,image/jpeg',
                exts: 'jpg|png|jpeg',
                size: 1024,
                multiple: true,
                auto: true,
                choose: function (obj) {
                    var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                    //读取本地文件
                    obj.preview(function (index, file, result) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var image = new Image();
                            image.onload = function () {
                                var tr = $(['<div id="upload-' + index + '" class="file-iteme" style="display: inline-block;margin-left: 10px">' +
                                '<div class="removeIcon handle" style="margin:auto;"><input value="" id="btnss' + index + '" hidden> ' +
                                /*'<i class="layui-icon layui-icon-delete" style="color: red ;margin-right: 40%; font-size: 17px"><span class="layui-btn" style="background-color: red">删除</span></i></div>' +*/
                                '<img style="color: blue;width: 250px" src=' + result + ' id="img-' + index + '">' +
                                '</div>'].join(''));
                                // 点击删除
                                // tr.find('.handle').on('click', function () {
                                //     $(this).parent().remove();
                                //     delete files[index];
                                //     // 根据图片uuid 进行删除
                                //     // dels($(this).find(':input:eq(0)').val());
                                // });
                                $('#demo3').append(tr);
                            };
                            image.src = result;
                        };
                        //正式读取文件
                        reader.readAsDataURL(file);
                    });
                },
                before: function (obj) {
                    layer.load(); //上传loading
                },
                done: function (res, index, upload) {
                    delete this.files[index]; //删除文件队列已经上传成功的文件
                    uuidArray.push(res.uuid);
                    layer.closeAll('loading'); //关闭loading
                    // 上传完成判断图片数量，限制上传
                    var length = uuidArray.length;
                    if (length > 8) {
                        $("#test3").attr("disabled", true);
                    } else {
                        $("#test3").removeAttr("disabled")
                    }
                    $("#" + "btnss" + index).val(res.uuid);
                    return;
                }, error: function (res, index, upload) {
                    layer.msg("上传失败，请稍后再试！");
                    delete this.files[index]; //删除文件队列已经上传成功的文件
                    // 移除图片
                    $("#upload-" + res).remove();
                    layer.closeAll('loading'); //关闭loading
                    return;
                }
            });
        });

        $("#send").on("click", function () {
            var data = {};
            var showTitle = $("#showTitle").val();
            var showContent = $("#showContent").val();
            var students = $("#attendees").val();
            var performanceType = $("input[name='performanceType']:checked").val();
            if (starsRate == null || starsRate == undefined || starsRate == "") {
                layer.msg("请填写完整");
                return;
            }
            if (showTitle == null || showTitle == undefined || showTitle == "") {
                layer.msg("请填写完整");
                return;
            }
            if (showContent == null || showContent == undefined || showContent == "") {
                layer.msg("请填写完整");
                return;
            }
            if (students == null || students == undefined || students == "") {
                layer.msg("请填写完整");
                return;
            }
            if (performanceType == null || performanceType == undefined || performanceType == "") {
                layer.msg("请填写完整");
                return;
            }
            data.stars = starsRate;
            data.showTitle = showTitle;
            data.showContent = showContent;
            data.students = students;
            data.performanceType = performanceType;
            data.uuid = uuidArray.join(",");
            var url = "${ctp}/student/show/addRelaseShow";
            $.post(url, data, function (data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        $.success("发布成功！");
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        $.error(data.pk);
                    }
                } else {
                    $.error("操作失败");
                }
            });

        });
    });

    function selectedStuHandler(data) {
        $(parent.window.frames[data.targetWindowName].document).find("#attendees_select").val(data.names);
        $(parent.window.frames[data.targetWindowName].document).find("#attendees").val(data.ids);
        $(parent.window.frames[data.targetWindowName].document).find("#attendees").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }

    $(function () {
        mouseChange();
        $(".file-iteme").children(".info").hide();
        $(".file-iteme").children(".handle").hide();
    });

    //给图片添加删除
    function mouseChange() {
        // $(document).on("mouseenter mouseleave", ".file-iteme", function (event) {
        //     if (event.type === "mouseenter") {
        //         //鼠标悬浮
        //         $(this).children(".info").fadeIn("fast");
        //         $(this).children(".handle").fadeIn("fast");
        //     } else if (event.type === "mouseleave") {
        //         //鼠标离开
        //         $(this).children(".info").hide();
        //         $(this).children(".handle").hide();
        //     }
        // });
    }


    /**
     * 删除数组中的某个元素 判断图片数量
     * @param id
     */
    function dels(id) {
        var len = uuidArray.length;
        // for (var i = 0; i < len; i++) {
        //     //打印数组中的情况，便于跟踪数组中数据的变化
        //     //删除掉所有为2的元素
        //     if (uuidArray[i] === id) {
        //         uuidArray.splice(i, 1);
        //     }
        // }
        // 上传完成判断图片数量，限制上传
        var length = uuidArray.length;
        if (length > 8) {
            $("#test3").attr("disabled", true);
        } else {
            $("#test3").removeAttr("disabled")
        }

    }

</script>

</html>