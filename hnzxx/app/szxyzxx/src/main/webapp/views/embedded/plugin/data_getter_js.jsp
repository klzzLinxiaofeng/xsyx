<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script type="text/javascript">
    //@author 潘维良
    //@date 2014-08-11
    (function ($) {

        //根据条件获取多校区
        $.getMembership = function (conditionJSon, afterHandler) {
            $.get("${ctp}/teach/membership/list/json", conditionJSon, function (
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //获得某个班级的科目
        $.getBbxTeamSubject = function (conditionJson, afterHandler) {
            $.get("${ctp}/clazz/teamHomework/subjectList", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //针对班班星不同用户的权限获取所有的班级
        $.getBbxRoleAuthority = function (conditionJson, afterHandler) {
            $.get("${ctp}/bbx/roleAuthority/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //针对班班星不同用户的权限获取已开通的班级账号
        $.getBbxRoleTeamAccouont = function (conditionJson, afterHandler) {
            $.get("${ctp}/bbx/roleTeamAccount/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }


        //根据学期找房室
        $.getRoom = function (conditionJson, aa, afterHandler) {
            $.get("${ctp}/bbx/courseRoom/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //获得教室
        $.getClassroom = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/classroom/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获得供应商名称
        $.getHqCanteenSupply = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/canteenSupply/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获取食堂商品
        $.getHqCanteen = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/canteen/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获取食堂商品
        $.getHqCanteenGoods = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/canteenGoods/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获取楼层
        $.getHqFloor = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/floor/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获取医务室
        $.getHqClinic = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/healthClinic/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获取楼层
        $.getFloor = function (conditionJson, afterHandler) {
            $.get("${ctp}/schoolaffair/floor/list/json2", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据条件获取科目
        $.getPjSubject = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/subject/list/json", conditionJson, function (
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //根据条件获取学年
        $.getSchoolYear = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/schoolYear/list/json", conditionJson, function (
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //获取学期
        $.getSchoolTerm = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/schoolTerm/list/json", conditionJson, function (
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //根据条件获取年级
        $.getGrade = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/grade/list/json", conditionJson, function (data,
                                                                           status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //根据条件获取班级
        $.getTeam = function (conditionJson, enableRole, afterHandler) {
            if (enableRole == null || enableRole === 'undefined') {
                enableRole = false;
            }
            $.get("${ctp}/teach/team/list/json?enableRole=" + enableRole,
                conditionJson, function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据班级ID获取班级
        $.getStudentByTeamId = function (teamId, afterHandler) {
            var conditionJson = {
                "teamId": teamId
            };
            $.get("${ctp}/teach/student/teamStu/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //获取当前学校学段集合
        $.getCurSchoolStageCode = function (afterHandler) {
            var stageCode = "${sessionScope[sca:currentUserKey()].stageCode}";
            afterHandler(stageCode.split(","));
        }

        //获取学制
        $.getSchoolSystem = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/schoolsystem/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //根据学校
        $.getSchool = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/school/info/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        //获取用户组 可根据用户组类型获取
        $.getGroup = function (conditionJson, afterHandler) {
            $.get("${ctp}/sys/group/list/json", conditionJson, function (data,
                                                                         status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //获取应用
        $.getApp = function (conditionJson, afterHandler) {
            $.get("${ctp}/sys/app/list/json", conditionJson, function (data,
                                                                       status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //获取im服务商
        $.getIMProvider = function (conditionJson, afterHandler) {
            $.get("${ctp}/im/imProvider/list/json", conditionJson, function (
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //获取知识点
        $.getKnowledgeCatalog = function (conditionJson, afterHandler) {
            $.get("${ctp}/teach/knowledgeCatalog/list/json", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        };

        $.getRole = function (conditionJson, afterHandler) {
            $.get("${ctp}/sys/role/list/json", conditionJson, function (data,
                                                                        status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        $.getAllAppEditions = function (conditionJson, afterHandler) {
            $.get("${ctp}/im/appAuthorization/getAppEditions", conditionJson,
                function (data, status) {
                    if ("success" === status) {
                        data = eval("(" + data + ")");
                        afterHandler(data);
                    }
                });
        }

        $.getPushObject = function (conditionJson, afterHandler) {
            $.get("${ctp}/im/pushObject/list/json", conditionJson, function (
                data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    afterHandler(data);
                }
            });
        }

        //获取多校区  $.getMembership
        $.MembershipSelector = function (options) {
            var defOption = {
                "selector": "#membership",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            $(selector).html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getMembership(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });

        }
        /* 获得2个日期之间的星期 */
        /* $.getWeek({
            "selector":"#select_week",
            "begin" : "2018-02-03",
            "end" : "2018-07-01",
            "today" : "",
             "isMonday":true,//是否周一开始算一周开始，否则周日算第一天
            "isClear" : false,
            "isSelectCurrentWeek" : true,
            "clearedOptionTitle" : "请选择学期",
        }); */
        $.getWeek = function (options) {
            var defOption = {
                "selector": "#select_week",
                "begin": "",
                "end": "",
                "isClear": false,
                "isSelectCurrentWeek": true,
                "today": "",
                "isMonday": false,
                "afterHandler": function () {

                },
                "firstOptionTitle": "请选择",
                "clearedOptionTitle": "请选择",
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            if (options.isClear) {
                var chznSelector = selector.siblings(options.selector + "_chzn");
                if (chznSelector != null && chznSelector.length > 0) {
                    selector.show().removeClass("chzn-done");

                    var clearedOption = $("<option value=''>" + options.clearedOptionTitle + "</option>");
                    selector.append(clearedOption);
                    chznSelector.remove();
                }
                return;
            }
            var begin = options.begin.replace(/-/g, "/");
            var end = options.end.replace(/-/g, "/");
            var chznSelector = selector.siblings(options.selector + "_chzn");
            if (chznSelector != null && chznSelector.length > 0) {
                selector.show().removeClass("chzn-done");
                chznSelector.remove();
            }
            selector.append("<option value=''>" + options.firstOptionTitle + "</option>");
            Date.prototype.format = function () {
                var s = '';
                s += this.getFullYear() + '-';// 获取年份。
                s += (this.getMonth() + 1) + "-"; // 获取月份。
                s += this.getDate(); // 获取日。
                return (s); // 返回日期。
            };
            var date = begin; //此处也可以写成 17/07/2014 一样识别    也可以写成 07-17-2014  但需要正则转换
            var day = new Date(Date.parse(date)); //需要正则转换的则 此处为 ： var day = new Date(Date.parse(date.replace(/-/g, '/')));
            if (options.isMonday) {
                var today = new Array('星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日');
            } else {
                var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
            }
            var week = today[day.getDay()];
            //alert(day.getDay()) /*求星期*/
            var i = 0;
// 			var today_data="2016-09-01"
            var ab = begin.split("/");
            var ae = end.split("/");
// 			var at = today_data.split("-");
            var db = new Date();
            db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
            var de = new Date();
            de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
// 			var dt = new Date();
// 			dt.setUTCFullYear(at[0], at[1] - 1, at[2]);
            var unixDb = db.getTime();
            var unixDe = de.getTime();
// 			var unixDt = de.getTime();
            for (var k = unixDb; k <= unixDe;) {
                i = i + 1;
                //console.log((new Date(parseInt(k))).format()); //输出全部天数
                k = k + 24 * 60 * 60 * 1000;
            }
            var j = 0;/*总共几周*/
            if (i > 7 - day.getDay()) {
                if ((i - (7 - day.getDay())) % 7 != 0) {
                    var j = Math.floor((i - (7 - day.getDay())) / 7) + 2
                } else {
                    var j = Math.floor((i - (7 - day.getDay())) / 7) + 1
                }
            } else {
                j = 1;
            }
            var date_start = new Date(begin);
            var date_end = new Date(end);
            var date_today;
            if (options.today != "") {
                date_today = new Date(options.today);
            } else {
                date_today = new Date();
            }
            var currentTime = date_today.getTime();

            var date1, date2, date_sun, date_sat, date_mon, date_sun1;
            for (var k = 1; k <= j; k++) {
                if (k == 1) {
                    date1 = (date_start.getFullYear() + "-" + (date_start.getMonth() + 1) + "-" + date_start.getDate())
                    data_sat = new Date(date_start);
                    data_sat.setDate(date_start.getDate() + 6 - day.getDay());
                    data_sun = new Date(date_start);
                    data_sun.setDate(date_start.getDate() + 7 - day.getDay());
                    if (options.isMonday) {
                        date2 = data_sun.getFullYear() + "-" + (data_sun.getMonth() + 1) + "-" + data_sun.getDate();
                    } else {
                        date2 = data_sat.getFullYear() + "-" + (data_sat.getMonth() + 1) + "-" + data_sat.getDate();
                    }
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                } else if (k < j) {
                    date_sat = new Date(data_sat);
                    date_sun = new Date(date_sat);
                    date_sun.setDate(date_sat.getDate() + 1);

                    date_mon = new Date(date_sun);
                    date_mon.setDate(date_sun.getDate() + 1);

                    data_sat = new Date(date_sun);
                    data_sat.setDate(date_sun.getDate() + 6);

                    date_sun1 = new Date(date_sun);
                    date_sun1.setDate(date_sun.getDate() + 7);

                    if (options.isMonday) {
                        date1 = (date_mon.getFullYear() + "-" + (date_mon.getMonth() + 1) + "-" + date_mon.getDate())
                        date2 = date_sun1.getFullYear() + "-" + (date_sun1.getMonth() + 1) + "-" + (date_sun1.getDate());
                    } else {
                        date1 = (date_sun.getFullYear() + "-" + (date_sun.getMonth() + 1) + "-" + date_sun.getDate())
                        date2 = data_sat.getFullYear() + "-" + (data_sat.getMonth() + 1) + "-" + data_sat.getDate();
                    }
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                } else {
                    date_sat = new Date(data_sat);
                    date_sun = new Date(date_sat);
                    date_sun.setDate(date_sat.getDate() + 1);

                    date_mon = new Date(date_sun);
                    date_mon.setDate(date_sun.getDate() + 1);

                    data_sat = new Date(date_end);

                    date_sun1 = new Date(date_sun);
                    date_sun1.setDate(date_sun.getDate() + 7);
                    if (options.isMonday) {
                        date1 = (date_mon.getFullYear() + "-" + (date_mon.getMonth() + 1) + "-" + date_mon.getDate())
                        date2 = date_sun1.getFullYear() + "-" + (date_sun1.getMonth() + 1) + "-" + (date_sun1.getDate());
                    } else {
                        date1 = (date_sun.getFullYear() + "-" + (date_sun.getMonth() + 1) + "-" + date_sun.getDate())
                        date2 = data_sat.getFullYear() + "-" + (data_sat.getMonth() + 1) + "-" + data_sat.getDate();
                    }
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                }
// 				if (currentTime > new Date(date1).getTime() && currentTime < (new Date(date2).getTime()+86400000) && options.isSelectCurrentWeek) {
                if (currentTime > new Date(Date.parse(date1.replace(/-/g, "/"))).getTime()
                    && currentTime < (new Date(Date.parse(date2.replace(/-/g, "/"))).getTime() + 86400000)
                    && options.isSelectCurrentWeek) {
                    selector.append('<option selected="selected" data-prev=' + date1 + ' data-next=' + date2 + ' data-num=' + k + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                } else {
                    selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + ' data-num=' + k + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                }
            }
            if (date_today.getTime() < date_start.getTime() || date_today.getTime() >= (date_end.getTime() + 86400000)) {
                selector.find("option")[1].selected = 'selected';
            }

            selector.chosen();
        }

        /* 简洁周次 */
        $.getNewWeek = function (options) {
            var defOption = {
                "selector": "#select_week",
                "begin": "",
                "end": "",
                "isClear": false,
                "isSelectCurrentWeek": true,
                "today": "",
                "afterHandler": function () {

                },
                "firstOptionTitle": "请选择",
                "clearedOptionTitle": "请选择",
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            if (options.isClear) {
                var chznSelector = selector.siblings(options.selector + "_chzn");
                if (chznSelector != null && chznSelector.length > 0) {
                    selector.show().removeClass("chzn-done");

// 					var clearedOption = $("<li>" + options.clearedOptionTitle + "</li>");
// 					selector.append(clearedOption);
                    chznSelector.remove();
                }
                return;
            }
            var begin = options.begin;
            var end = options.end;
            var chznSelector = selector.siblings(options.selector + "_chzn");
            if (chznSelector != null && chznSelector.length > 0) {
                selector.show().removeClass("chzn-done");
                chznSelector.remove();
            }
            //selector.append("<li value=''>" + options.firstOptionTitle + "</li>");
            Date.prototype.format = function () {
                var s = '';
                s += this.getFullYear() + '-';// 获取年份。
                s += (this.getMonth() + 1) + "-"; // 获取月份。
                s += this.getDate(); // 获取日。
                return (s); // 返回日期。
            };
            var date = begin; //此处也可以写成 17/07/2014 一样识别    也可以写成 07-17-2014  但需要正则转换
            var day = new Date(Date.parse(date)); //需要正则转换的则 此处为 ： var day = new Date(Date.parse(date.replace(/-/g, '/')));
            var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');
            var week = today[day.getDay()];
            //alert(day.getDay()) /*求星期*/
            var i = 0;
// 			var today_data="2016-09-01"
            var ab = begin.split("-");
            var ae = end.split("-");
// 			var at = today_data.split("-");
            var db = new Date();
            db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
            var de = new Date();
            de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
// 			var dt = new Date();
// 			dt.setUTCFullYear(at[0], at[1] - 1, at[2]);
            var unixDb = db.getTime();
            var unixDe = de.getTime();
// 			var unixDt = de.getTime();
            for (var k = unixDb; k <= unixDe;) {
                i = i + 1;
                //console.log((new Date(parseInt(k))).format()); //输出全部天数
                k = k + 24 * 60 * 60 * 1000;
            }
            var j = 0;/*总共几周*/
            if (i > 7 - day.getDay()) {
                if ((i - (7 - day.getDay())) % 7 != 0) {
                    var j = Math.floor((i - (7 - day.getDay())) / 7) + 2
                } else {
                    var j = Math.floor((i - (7 - day.getDay())) / 7) + 1
                }
            } else {
                j = 1;
            }
            var date_start = new Date(begin);
            var date_end = new Date(end);
            var date_today;
            if (options.today != "") {
                date_today = new Date(options.today);
            } else {
                date_today = new Date();
            }
            var currentTime = date_today.getTime();

            var date1, date2, date_sun, date_sat;
            for (var k = 1; k <= j; k++) {
                if (k == 1) {
                    date1 = (date_start.getFullYear() + "-" + (date_start.getMonth() + 1) + "-" + date_start.getDate())
                    data_sat = new Date(date_start);
                    data_sat.setDate(date_start.getDate() + 6 - day.getDay());
                    date2 = data_sat.getFullYear() + "-" + (data_sat.getMonth() + 1) + "-" + data_sat.getDate();
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                } else if (k < j) {
                    date_sat = new Date(data_sat);
                    date_sun = new Date(date_sat);
                    date_sun.setDate(date_sat.getDate() + 1);
                    date1 = (date_sun.getFullYear() + "-"
                        + (date_sun.getMonth() + 1) + "-" + date_sun
                            .getDate())
                    data_sat = new Date(date_sun);
                    data_sat.setDate(date_sun.getDate() + 6);
                    date2 = data_sat.getFullYear() + "-"
                        + (data_sat.getMonth() + 1) + "-"
                        + data_sat.getDate();
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                } else {
                    date_sat = new Date(data_sat);
                    date_sun = new Date(date_sat);
                    date_sun.setDate(date_sat.getDate() + 1);
                    date1 = (date_sun.getFullYear() + "-"
                        + (date_sun.getMonth() + 1) + "-" + date_sun
                            .getDate())
                    data_sat = new Date(date_end);
                    date2 = data_sat.getFullYear() + "-"
                        + (data_sat.getMonth() + 1) + "-"
                        + data_sat.getDate();
// 					selector.append('<option data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周(' + date1 + '~' + date2 + ')</option>');
                }
// 				if (currentTime > new Date(date1).getTime() && currentTime < (new Date(date2).getTime()+86400000) && options.isSelectCurrentWeek) {
                if (currentTime > new Date(Date.parse(date1.replace(/-/g, "/"))).getTime() && currentTime < (new Date(Date.parse(date2.replace(/-/g, "/"))).getTime() + 86400000)
                    && options.isSelectCurrentWeek) {
                    selector.append('<li class="grade" data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周</li>');
                } else {
                    selector.append('<li data-prev=' + date1 + ' data-next=' + date2 + '>第' + k + '周</li>');
                }
            }
            if (date_today.getTime() < date_start.getTime() || date_today.getTime() >= (date_end.getTime() + 86400000)) {
// 				selector.find("option")[1].addClass("grade");
            }
// 			selector.chosen();
        }

        /* 简洁月份 */
        $.getNewMonth = function (options) {
            var defOption = {
                "selector": "#select_month",
                "begin": "",
                "end": "",
                "isClear": false,
                "isSelectCurrentWeek": true,
                "today": "",
                "afterHandler": function () {

                },
                "firstOptionTitle": "请选择",
                "clearedOptionTitle": "请选择",
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            if (options.isClear) {
                var chznSelector = selector.siblings(options.selector + "_chzn");
                if (chznSelector != null && chznSelector.length > 0) {
                    selector.show().removeClass("chzn-done");

                    var clearedOption = $("<li>" + options.clearedOptionTitle + "</li>");
                    selector.append(clearedOption);
                    chznSelector.remove();
                }
                return;
            }
            var begin = options.begin;
            var end = options.end;
            date1 = begin.split('-');
            date2 = end.split('-');
            if (date1[1] < 10) {
                date1[1] = parseInt(date1[1].substring(1, 2));
            }
            var month = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"];
            if (date1[1] < date2[1]) {
                for (var i = date1[1]; i <= date2[1]; i++) {
                    j = month[i - 1]
                    selector.append("<li date-year=" + date1[0] + " data-month=" + i + ">" + j + "月份</li>");
                }
                ;
            } else {
                for (var i = date1[1]; i <= 12; i++) {
                    j = month[i - 1]
                    selector.append("<li date-year=" + date1[0] + " data-month=" + i + ">" + j + "月份</li>");
                }
                ;
                for (var i = 1; i <= date2[1]; i++) {
                    j = month[i - 1]
                    selector.append("<li date-year=" + date2[0] + " data-month=" + i + ">" + j + "月份</li>");
                }
                ;
            }
        }

        //获得某个班级的科目
        $.bbxTeamSubjectSelector = function (options) {
            var defOption = {
                "selector": "#teamSubject",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getBbxTeamSubject(
                options.condition,
                function (data) {
                    $
                        .each(
                            data,
                            function (index, value) {
                                selector
                                    .append("<option value='" + value.subjectCode + "'>"
                                        + value.subjectName
                                        + "</option>")
                            });
                    selector.val(options.selectedVal);
                    options.afterHandler(selector);
                    if (options.isUseChosen == null
                        || options.isUseChosen) {
                        selector.chosen();
                    }
                });
        }

        //班班星角色获取所有班级
        $.BbxRoleAuthoritySelector = function (options) {
            var defOption = {
                "selector": "#authority",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            $.getBbxRoleAuthority(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.teamId + "'>"
                        + value.teamName + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        //班班星角色获取已开通的班级
        $.BbxRoleTeamAccountSelector = function (options) {
            var defOption = {
                "selector": "#authority",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            $.getBbxRoleTeamAccouont(options.condition, function (data) {
                if (data.length > 0) {
                    $.each(data, function (index, value) {
                        var postion = value.type == 1 ? "班主任" : "科任教师";
                        selector.append("<option value='" + value.teamId + "'>" + value.teamName + " -- " + postion + "</option>")
                    });
                } else {
                    selector.append("<option value='-1'>请选择</option>")
                }
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.AppSelector = function (options) {
            var defOption = {
                "selector": "#app",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getApp(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.AppKeySelector = function (options) {
            var defOption = {
                "selector": "#app",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getApp(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.appKey + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.IMProviderSelector = function (options) {
            var defOption = {
                "selector": "#app",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getIMProvider(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.imType + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.AllAppEditionsSelector = function (options) {
            var defOption = {
                "selector": "#app",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getAllAppEditions(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.appKey + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.PushObjectSelector = function (options) {
            var defOption = {
                "selector": "#app",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getPushObject(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.GroupSelector = function (options) {
            var defOption = {
                "selector": "#group",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            var $firstSelector = selector.find("option:first");

            if ($firstSelector.length > 0) {
                $firstSelector.before("<option value=''>"
                    + options.firstOptionTitle + "</option>");
            } else {
                selector.append("<option value=''>" + options.firstOptionTitle
                    + "</option>");
            }

            $.getGroup(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        //获取楼层
        $.HqFloorSelector = function (options) {
            var defOption = {
                "selector": "#floor",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getHqFloor(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
                options.afterHandler(selector);
            });
        }

        //获取校区
        $.HqManySchool = function (options) {
            var defOption = {
                "selector": "#schoolId",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择校区",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);

            selector.append("<a href='javascript:void(0)'><div class='drop_box' title='" + options.firstOptionTitle + "'>"
                + options.firstOptionTitle
                + "</div><b class='hide_btn'></b><input type='text' class='school_id' style='display:none' />" +
                "</a><div class='drop_choice'><ul></ul><div class='drop_confirm'><input type='button' value='确认'/></div>");
            $.getMembership(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.children().children("ul").append(
                        "<li><p value-id=" + value.id + ">" + value.name
                        + "</p><input type='checkbox'/></li>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
            });
        }

        //获取医务室
        $.HqClinicSelector = function (options) {
            var defOption = {
                "selector": "#clinicId",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getHqClinic(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        //获取楼层
        $.FloorSelector = function (options) {
            var defOption = {
                "selector": "#floorId",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getFloor(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        //食堂：获取商品
        $.HqCanteenGoodsSelector = function (options) {
            var defOption = {
                "selector": "#canteenGoods",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getHqCanteenGoods(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });

        }

        //食堂：获取食堂
        $.HqCanteenSelector = function (options) {
            var defOption = {
                "selector": "#canteen",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getHqCanteen(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });

        }

        //后勤获得供应商的名称
        $.HqCanteenSupplySelector = function (options) {
            var defOption = {
                "selector": "#canteenSupply",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getHqCanteenSupply(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });

        }

        $.SchoolYearSelector = function (options) {
            var defOption = {
                "selector": "#schoolYear",
                "condition": {},
                "selectedVal": "${sessionScope[sca:currentUserKey()].schoolYear}",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getSchoolYear(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.year + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.GradeSelector = function (options) {
            var defOption = {
                "selector": "#schoolYear",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getGrade(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.SchoolTermSelector = function (options) {
            var defOption = {
                "selector": "#schoolTerm",
                "condition": {},
                "selectedVal": "${sessionScope[sca:currentUserKey()].schoolTermCode}",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle + "</option>");
            $.getSchoolTerm(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        //房室
        $.SchoolRoomSelector = function (options) {
            var defOption = {
                "selector": "#schoolTerm",
                "condition": {},
                "selectedVal": "${sessionScope[sca:currentUserKey()].schoolTermCode}",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle + "</option>");
            $.getRoom(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.TeamSelector = function (options) {
            var defOption = {
                "selector": "#schoolYear",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getTeam(options.condition, options.enableRole, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.TeamStudentSelector = function (options) {
            var defOption = {
                "selector": "#schoolYear",
                "teamId": "",
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getStudentByTeamId(options.teamId, function (data) {
                $.each(data, function (index, value) {
                    // 				selector.append("<option value='" + value.studentId + "'>" + value.name + "(" + value.number + ")</option>");
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>");
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.SchoolSystemSelector = function (options) {
            var defOption = {
                "selector": "#schoolSystem",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $
                .getSchoolSystem(
                    options.condition,
                    function (data) {
                        $
                            .each(
                                data,
                                function (index, value) {
                                    selector
                                        .append("<option data-num='" + value.gradeNumber + "' value='" + value.gradeCode + "'>"
                                            + value.gradeName
                                            + "</option>")
                                });
                        selector.val(options.selectedVal);
                        options.afterHandler(selector);
                        if (options.isUseChosen == null
                            || options.isUseChosen) {
                            selector.chosen();
                        }
                    });
        }

        $.curSchoolStageCodeSelector = function (options) {
            var defOption = {
                "selector": "#stageCode",
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});
            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");
            $.getCurSchoolStageCode(function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value + "'>" + value
                        + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.SchoolSelector = function (options) {
            var defOption = {
                "selector": "#school",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isShowfirstOptionTitle": true,
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            if (options.isShowfirstOptionTitle) {
                selector.append("<option value=''>" + options.firstOptionTitle
                    + "</option>");
            }

            $.getSchool(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.id + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.PjSubjectSelector = function (options) {
            var defOption = {
                "selector": "#subject",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $.getPjSubject(options.condition, function (data) {
                $.each(data, function (index, value) {
                    selector.append("<option value='" + value.code + "'>"
                        + value.name + "</option>")
                });
                selector.val(options.selectedVal);
                options.afterHandler(selector);
                if (options.isUseChosen == null || options.isUseChosen) {
                    selector.chosen();
                }
            });
        }

        $.RoleSelector = function (options) {
            var defOption = {
                "selector": "#role",
                "condition": {},
                "selectedVal": "",
                "afterHandler": function () {
                },
                "firstOptionTitle": "请选择",
                "isUseChosen": true
            };
            options = $.extend({}, defOption, options || {});

            var selector = $(options.selector);
            selector.html("");
            selector.append("<option value=''>" + options.firstOptionTitle
                + "</option>");

            $
                .getRole(
                    options.condition,
                    function (data) {
                        $
                            .each(
                                data,
                                function (index, value) {
                                    selector
                                        .append("<option data-code='" + value.code + "' value='" + value.id + "'>"
                                            + value.name
                                            + "</option>")
                                });
                        selector.val(options.selectedVal);
                        options.afterHandler(selector);
                        if (options.isUseChosen == null
                            || options.isUseChosen) {
                            selector.chosen();
                        }
                    });
        }

        //级联筛选班级控件
        $.initCascadeSelector = function (options) {
            $.ajaxSetup({
                async: false
            });
            var currentSchoolYear = "${sessionScope[sca:currentUserKey()].schoolYear}";
            var defOption = {
                type: "team", //team / stu
                isUseChosen: true,
                isUseCurrentYear: true,
                selectOne: false,
                enableRole: false, //是否是根据角色获取班级
                yearSelectId: "xn",
                gradeSelectId: "nj",
                teamSelectId: "bj",
                stuSelectId: "stu",

                isEchoSelected: false,
                yearSelectedVal: "",
                gradeSelectedVal: "",
                teamSelectedVal: "",
                stuSelectedVal: "",

                yearFirstOptTitle: "请选择",
                gradeFirstOptTitle: "请选择",
                teamFirstOptTitle: "请选择",
                stuFirstOptTitle: "请选择",

                yearCallback: function ($this) {
                },
                gradeCallback: function ($this) {
                },
                teamCallback: function ($this) {
                },
                stuCallback: function ($this) {
                },

                yearChangeCallback: function () {
                },
            };

            var settings = $.extend({}, defOption, options || {});

            var schoolId = null;
            if (settings.schoolId != null && settings.schoolId != 'undefined') {
                schoolId = settings.schoolId;
            }

            var $yearSelectId = $("#" + settings.yearSelectId);
            var $gradeSelectId = $("#" + settings.gradeSelectId);
            var $teamSelectId = $("#" + settings.teamSelectId);
            var $stuSelectId = $("#" + settings.stuSelectId);

            if (!settings.isUseChosen) {
                $gradeSelectId.append("<option value=''>"
                    + settings.gradeFirstOptTitle + "</option>");
                $yearSelectId
                    .on(
                        "change",
                        function (event) {
                            var $this = $(this);
                            var year = $this.val();
                            settings.yearChangeCallback(year);
                            if (year != "") {
                                $
                                    .GradeSelector({
                                        "selector": "#"
                                            + settings.gradeSelectId,
                                        "condition": {
                                            "schoolYear": year,
                                            "schoolId": schoolId
                                        },
                                        "selectedVal": settings.gradeSelectedVal,
                                        "firstOptionTitle": settings.gradeFirstOptTitle,
                                        "afterHandler": function (
                                            $this) {
                                            if ("stu" === setting.types
                                                || "team" === settings.type) {
                                                if (settings.isEchoSelected) {
                                                    $this
                                                        .trigger("change");
                                                } else if (settings.selectOne) {
                                                    $this
                                                        .find(
                                                            "option:nth-child(2)")
                                                        .prop(
                                                            "selected",
                                                            "selected");
                                                    $this
                                                        .trigger("change");
                                                }
                                            } else {
                                                if (!settings.isEchoSelected
                                                    && settings.selectOne) {
                                                    $this
                                                        .find(
                                                            "option:nth-child(2)")
                                                        .prop(
                                                            "selected",
                                                            "selected");
                                                }
                                                settings.isEchoSelected = false;
                                            }
                                            //回调函数
                                            settings
                                                .gradeCallback($this);
                                        },
                                        "isUseChosen": false
                                    });
                            } else {
                                $gradeSelectId.val("");
                                if ("team" === settings.type
                                    || "stu" === settings.type) {
                                    $teamSelectId.val("");
                                }
                                if ("stu" === settings.type) {
                                    $stuSelectId.val("");
                                }
                            }
                        });

                if ("team" === settings.type || "stu" === settings.type) {
                    $teamSelectId.append("<option value=''>"
                        + settings.teamFirstOptTitle + "</option>");
                    $gradeSelectId
                        .on(
                            "change",
                            function (event) {
                                var $this = $(this);
                                var id = $this.val();
                                if (id != "") {
                                    $
                                        .TeamSelector({
                                            "selector": "#"
                                                + settings.teamSelectId,
                                            "condition": {
                                                "gradeId": id,
                                                "schoolId": schoolId
                                            },
                                            "enableRole": settings.enableRole,
                                            "selectedVal": settings.teamSelectedVal,
                                            "firstOptionTitle": settings.teamFirstOptTitle,
                                            "afterHandler": function (
                                                $this) {
                                                if ("stu" === settings.type) {
                                                    if (settings.isEchoSelected) {
                                                        $this
                                                            .trigger("change");
                                                    } else if (settings.selectOne) {
                                                        $this
                                                            .find(
                                                                "option:nth-child(2)")
                                                            .prop(
                                                                "selected",
                                                                "selected");
                                                        $this
                                                            .trigger("change");
                                                    }
                                                } else {
                                                    if (!settings.isEchoSelected
                                                        && settings.selectOne) {
                                                        $this
                                                            .find(
                                                                "option:nth-child(2)")
                                                            .prop(
                                                                "selected",
                                                                "selected");
                                                    }
                                                    settings.isEchoSelected = false;
                                                }
                                                //回调函数
                                                settings
                                                    .teamCallback($this);
                                            },
                                            "isUseChosen": false
                                        });
                                } else {
                                    $teamSelectId.val("");
                                    if ("stu" === settings.type) {
                                        $stuSelectId.val("");
                                    }
                                }
                            });
                }
                if ("stu" === settings.type) {
                    $stuSelectId.append("<option value=''>"
                        + settings.stuFirstOptTitle + "</option>");
                    $teamSelectId.on("change", function (event) {
                        var $this = $(this);
                        var id = $this.val();
                        if (id != "") {
                            $.TeamStudentSelector({
                                "selector": "#" + settings.stuSelectId,
                                "teamId": id,
                                "selectedVal": settings.stuSelectedVal,
                                "firstOptionTitle": settings.stuFirstOptTitle,
                                "afterHandler": function ($this) {
                                    if (!settings.isEchoSelected
                                        && settings.selectOne) {
                                        $this.find("option:nth-child(2)").prop(
                                            "selected", "selected");
                                    }
                                    settings.isEchoSelected = false;
                                    //回调函数
                                    settings.stuCallback($this);
                                },
                                "isUseChosen": false
                            });
                        } else {
                            $stuSelectId.val("");
                        }
                    });
                }
            } else {
                $gradeSelectId.append("<option value=''>"
                    + settings.gradeFirstOptTitle + "</option>");
                $("body")
                    .on(
                        "click",
                        "#" + settings.yearSelectId
                        + "_chzn .chzn-drop .chzn-results li",
                        function (event) {
                            var $this = $(this);
                            var yearIndex = $this.attr("id").replace(
                                settings.yearSelectId + "_chzn_o_",
                                "");
                            var yearSelector = $yearSelectId;
                            var year = yearSelector.find("option").eq(
                                parseInt(yearIndex)).val();
                            settings.yearChangeCallback(year);
                            if (year != "") {
                                $
                                    .GradeSelector({
                                        "selector": "#"
                                            + settings.gradeSelectId,
                                        "condition": {
                                            "schoolYear": year,
                                            "schoolId": schoolId
                                        },
                                        "selectedVal": settings.gradeSelectedVal,
                                        "firstOptionTitle": settings.gradeFirstOptTitle,
                                        "afterHandler": function (
                                            $this) {
                                            $(
                                                "#"
                                                + settings.gradeSelectId
                                                + "_chzn")
                                                .remove();
                                            $gradeSelectId
                                                .show()
                                                .removeClass(
                                                    "chzn-done")
                                                .chosen();
                                            if (settings.isEchoSelected) {
                                                var index = $gradeSelectId
                                                    .get(0).selectedIndex;
                                                $(
                                                    "#"
                                                    + settings.gradeSelectId
                                                    + "_chzn .chzn-drop .chzn-results li")
                                                    .eq(index)
                                                    .click();
                                            } else if (settings.selectOne) {
                                                $this
                                                    .find(
                                                        "option:nth-child(2)")
                                                    .prop(
                                                        "selected",
                                                        "selected");
                                                $(
                                                    "#"
                                                    + settings.gradeSelectId
                                                    + "_chzn")
                                                    .remove();
                                                $gradeSelectId
                                                    .show()
                                                    .removeClass(
                                                        "chzn-done")
                                                    .chosen();
                                                var index = $gradeSelectId
                                                    .get(0).selectedIndex;
                                                $(
                                                    "#"
                                                    + settings.gradeSelectId
                                                    + "_chzn .chzn-drop .chzn-results li")
                                                    .eq(index)
                                                    .click();
                                            }
                                            $(
                                                "#"
                                                + settings.gradeSelectId
                                                + "_chzn .chzn-drop .chzn-results .result-selected")
                                                .click();
                                            //回调函数
                                            settings
                                                .gradeCallback($this);
                                        },
                                        "isUseChosen": false
                                    });
                            } else {
                                $gradeSelectId.val("");
                                $(
                                    "#" + settings.gradeSelectId
                                    + "_chzn").remove();
                                $gradeSelectId.show().removeClass(
                                    "chzn-done").chosen();
                                if ("team" === settings.type
                                    || "stu" === settings.type) {
                                    $teamSelectId.val("");
                                    $(
                                        "#" + settings.teamSelectId
                                        + "_chzn").remove();
                                    $teamSelectId.show().removeClass(
                                        "chzn-done").chosen();
                                }
                                if ("stu" === settings.type) {
                                    $stuSelectId.val("");
                                    $(
                                        "#" + settings.stuSelectId
                                        + "_chzn").remove();
                                    $stuSelectId.show().removeClass(
                                        "chzn-done").chosen();
                                }
                            }
                        });

                if ("team" === settings.type || "stu" === settings.type) {
                    $teamSelectId.append("<option value=''>"
                        + settings.teamFirstOptTitle + "</option>");
                    $("body")
                        .on(
                            "click",
                            "#"
                            + settings.gradeSelectId
                            + "_chzn .chzn-drop .chzn-results li",
                            function (event) {
                                var $this = $(this);
                                var gradeIndex = $this.attr("id")
                                    .replace(
                                        settings.gradeSelectId
                                        + "_chzn_o_",
                                        "");
                                var gradeSelector = $gradeSelectId;
                                var id = gradeSelector.find("option")
                                    .eq(parseInt(gradeIndex)).val();
                                if (id == "") {
                                    id = -1;
                                }
                                $
                                    .TeamSelector({
                                        "selector": "#"
                                            + settings.teamSelectId,
                                        "condition": {
                                            "gradeId": id,
                                            "schoolId": schoolId
                                        },
                                        "enableRole": settings.enableRole,
                                        "selectedVal": settings.teamSelectedVal,
                                        "firstOptionTitle": settings.teamFirstOptTitle,
                                        "afterHandler": function (
                                            $this) {
                                            $(
                                                "#"
                                                + settings.teamSelectId
                                                + "_chzn")
                                                .remove();
                                            $teamSelectId
                                                .show()
                                                .removeClass(
                                                    "chzn-done")
                                                .chosen();
                                            if (settings.isEchoSelected) {
                                                var index = $teamSelectId
                                                    .get(0).selectedIndex;
                                                $(
                                                    "#"
                                                    + settings.teamSelectId
                                                    + "_chzn .chzn-drop .chzn-results li")
                                                    .eq(index)
                                                    .click();
                                            } else if (settings.selectOne) {
                                                $this
                                                    .find(
                                                        "option:nth-child(2)")
                                                    .prop(
                                                        "selected",
                                                        "selected");
                                                $(
                                                    "#"
                                                    + settings.teamSelectId
                                                    + "_chzn")
                                                    .remove();
                                                $teamSelectId
                                                    .show()
                                                    .removeClass(
                                                        "chzn-done")
                                                    .chosen();
                                                var index = $teamSelectId
                                                    .get(0).selectedIndex;
                                                $(
                                                    "#"
                                                    + settings.teamSelectId
                                                    + "_chzn .chzn-drop .chzn-results li")
                                                    .eq(index)
                                                    .click();
                                            }
                                            //回调函数
                                            settings
                                                .teamCallback($this);
                                        },
                                        "isUseChosen": false
                                    });
                                // 					} else {
                                // 						$teamSelectId.val("");
                                // 						$("#" + settings.teamSelectId + "_chzn").remove();
                                // 						$teamSelectId.show().removeClass("chzn-done").chosen();

                                // 						if("stu" === settings.type) {
                                // 							$stuSelectId.val("");
                                // 							$("#" + settings.stuSelectId + "_chzn").remove();
                                // 							$stuSelectId.show().removeClass("chzn-done").chosen();
                                // 						}

                                // 					}
                            });
                }

                if ("stu" === settings.type) {
                    $stuSelectId.append("<option value=''>"
                        + settings.stuFirstOptTitle + "</option>");
                    $("body")
                        .on(
                            "click",
                            "#"
                            + settings.teamSelectId
                            + "_chzn .chzn-drop .chzn-results li",
                            function (event) {
                                var $this = $(this);
                                var teamIndex = $this.attr("id")
                                    .replace(
                                        settings.teamSelectId
                                        + "_chzn_o_",
                                        "");
                                var teamSelector = $teamSelectId;
                                var id = teamSelector.find("option")
                                    .eq(parseInt(teamIndex)).val();
                                if (id != "") {
                                    $
                                        .TeamStudentSelector({
                                            "selector": "#"
                                                + settings.stuSelectId,
                                            "teamId": id,
                                            "selectedVal": settings.stuSelectedVal,
                                            "firstOptionTitle": settings.stuFirstOptTitle,
                                            "afterHandler": function (
                                                $this) {
                                                if (!settings.isEchoSelected
                                                    && settings.selectOne) {
                                                    $this
                                                        .find(
                                                            "option:nth-child(2)")
                                                        .prop(
                                                            "selected",
                                                            "selected");
                                                }
                                                $(
                                                    "#"
                                                    + settings.stuSelectId
                                                    + "_chzn")
                                                    .remove();
                                                $stuSelectId
                                                    .show()
                                                    .removeClass(
                                                        "chzn-done")
                                                    .chosen();
                                                settings.isEchoSelected = false;
                                                //回调函数
                                                settings
                                                    .stuCallback($this);
                                            },
                                            "isUseChosen": false
                                        });
                                } else {
                                    $stuSelectId.val("");
                                    $(
                                        "#" + settings.stuSelectId
                                        + "_chzn").remove();
                                    $stuSelectId.show().removeClass(
                                        "chzn-done").chosen();
                                }
                            });
                }
            }

            $
                .SchoolYearSelector({
                    "selector": "#" + settings.yearSelectId,
                    "condition": {},
                    "selectedVal": settings.yearSelectedVal,
                    "firstOptionTitle": settings.yearFirstOptTitle,
                    "afterHandler": function ($this) {
                        if (settings.isUseChosen) {
                            $("#" + settings.yearSelectId).chosen();
                            $("#" + settings.gradeSelectId).chosen();
                            if ("team" === settings.type
                                || "stu" === settings.type) {
                                $("#" + settings.teamSelectId).chosen();
                            }
                            if ("stu" === settings.type) {
                                $("#" + settings.stuSelectId).chosen();
                            }
                        }
                        if (settings.isEchoSelected) {
                            if (settings.isUseChosen) {
                                var index = $yearSelectId.get(0).selectedIndex;
                                $(
                                    "#"
                                    + settings.yearSelectId
                                    + "_chzn .chzn-drop .chzn-results li")
                                    .eq(index).click();
                            } else {
                                $this.trigger("change");
                            }
                        } else if (settings.selectOne
                            || settings.isUseCurrentYear) {
                            if (settings.isUseCurrentYear) {
                                $this.val(currentSchoolYear);
                            } else {
                                $this.find("option:nth-child(2)").prop(
                                    "selected", "selected");
                            }

                            if (settings.isUseChosen) {
                                $("#" + settings.yearSelectId + "_chzn")
                                    .remove();
                                $yearSelectId.show().removeClass(
                                    "chzn-done").chosen();
                                var index = $yearSelectId.get(0).selectedIndex;
                                $(
                                    "#"
                                    + settings.yearSelectId
                                    + "_chzn .chzn-drop .chzn-results li")
                                    .eq(index).click();
                            } else {
                                $this.trigger("change");
                            }
                        }
                        //回调函数
                        settings.yearCallback($this);
                    },
                    "isUseChosen": false
                });
        }

        //级联筛选班级控件
        $.initNBSCascadeSelector = function (options) {
            var currentSchoolYear = "${sessionScope[sca:currentUserKey()].schoolYear}";
            var defOption = {
                type: "stu", //team / stu
                isUseChosen: true,
                selectOne: true,
                gradeSelectId: "nj",
                teamSelectId: "bj",
                stuSelectId: "stu",

                isEchoSelected: false,
                gradeSelectedVal: "",
                teamSelectedVal: "",
                stuSelectedVal: "",

                gradeFirstOptTitle: "请选择",
                teamFirstOptTitle: "请选择",
                stuFirstOptTitle: "请选择",

                gradeCallback: function ($this) {
                },
                teamCallback: function ($this) {
                },
                stuCallback: function ($this) {
                }
            };

            var settings = $.extend({}, defOption, options || {});

            var $gradeSelectId = $("#" + settings.gradeSelectId);
            var $teamSelectId = $("#" + settings.teamSelectId);
            var $stuSelectId = $("#" + settings.stuSelectId);

            if (!settings.isUseChosen) {
                if ("team" === settings.type || "stu" === settings.type) {
                    $teamSelectId.append("<option value=''>"
                        + settings.teamFirstOptTitle + "</option>");
                    $gradeSelectId
                        .on(
                            "change",
                            function (event) {
                                var $this = $(this);
                                var id = $this.val();
                                if (id != "") {
                                    $
                                        .TeamSelector({
                                            "selector": "#"
                                                + settings.teamSelectId,
                                            "condition": {
                                                "gradeId": id
                                            },
                                            "enableRole": settings.enableRole,
                                            "selectedVal": settings.teamSelectedVal,
                                            "firstOptionTitle": settings.teamFirstOptTitle,
                                            "afterHandler": function (
                                                $this) {
                                                if ("stu" === settings.type) {
                                                    if (settings.isEchoSelected) {
                                                        $this
                                                            .trigger("change");
                                                    } else if (settings.selectOne) {
                                                        $this
                                                            .find(
                                                                "option:nth-child(2)")
                                                            .prop(
                                                                "selected",
                                                                "selected");
                                                        $this
                                                            .trigger("change");
                                                    }
                                                } else {
                                                    if (!settings.isEchoSelected
                                                        && settings.selectOne) {
                                                        $this
                                                            .find(
                                                                "option:nth-child(2)")
                                                            .prop(
                                                                "selected",
                                                                "selected");
                                                    }
                                                    settings.isEchoSelected = false;
                                                }
                                                //回调函数
                                                settings
                                                    .teamCallback($this);
                                            },
                                            "isUseChosen": false
                                        });
                                } else {
                                    $teamSelectId.val("");
                                    if ("stu" === settings.type) {
                                        $stuSelectId.val("");
                                    }
                                }
                            });
                }
                if ("stu" === settings.type) {
                    $stuSelectId.append("<option value=''>"
                        + settings.stuFirstOptTitle + "</option>");
                    $teamSelectId.on("change", function (event) {
                        var $this = $(this);
                        var id = $this.val();
                        if (id != "") {
                            $.TeamStudentSelector({
                                "selector": "#" + settings.stuSelectId,
                                "teamId": id,
                                "selectedVal": settings.stuSelectedVal,
                                "firstOptionTitle": settings.stuFirstOptTitle,
                                "afterHandler": function ($this) {
                                    if (!settings.isEchoSelected
                                        && settings.selectOne) {
                                        $this.find("option:nth-child(2)").prop(
                                            "selected", "selected");
                                    }
                                    settings.isEchoSelected = false;

                                    //回调函数
                                    settings.stuCallback($this);
                                },
                                "isUseChosen": false
                            });
                        } else {
                            $stuSelectId.val("");
                        }
                    });
                }
            } else {
                if ("team" === settings.type || "stu" === settings.type) {
                    $teamSelectId.append("<option value=''>"
                        + settings.teamFirstOptTitle + "</option>");
                    $("body")
                        .on(
                            "click",
                            "#"
                            + settings.gradeSelectId
                            + "_chzn .chzn-drop .chzn-results li",
                            function (event) {
                                var $this = $(this);
                                var gradeIndex = $this.attr("id")
                                    .replace(
                                        settings.gradeSelectId
                                        + "_chzn_o_",
                                        "");
                                var gradeSelector = $gradeSelectId;
                                var id = gradeSelector.find("option")
                                    .eq(parseInt(gradeIndex)).val();
                                if (id != "") {
                                    $
                                        .TeamSelector({
                                            "selector": "#"
                                                + settings.teamSelectId,
                                            "condition": {
                                                "gradeId": id
                                            },
                                            "enableRole": settings.enableRole,
                                            "selectedVal": settings.teamSelectedVal,
                                            "firstOptionTitle": settings.teamFirstOptTitle,
                                            "afterHandler": function (
                                                $this) {
                                                $(
                                                    "#"
                                                    + settings.teamSelectId
                                                    + "_chzn")
                                                    .remove();
                                                $teamSelectId
                                                    .show()
                                                    .removeClass(
                                                        "chzn-done")
                                                    .chosen();
                                                if (settings.isEchoSelected) {
                                                    var index = $teamSelectId
                                                        .get(0).selectedIndex;
                                                    $(
                                                        "#"
                                                        + settings.teamSelectId
                                                        + "_chzn .chzn-drop .chzn-results li")
                                                        .eq(
                                                            index)
                                                        .click();
                                                } else if (settings.selectOne) {
                                                    $this
                                                        .find(
                                                            "option:nth-child(2)")
                                                        .prop(
                                                            "selected",
                                                            "selected");
                                                    $(
                                                        "#"
                                                        + settings.teamSelectId
                                                        + "_chzn")
                                                        .remove();
                                                    $teamSelectId
                                                        .show()
                                                        .removeClass(
                                                            "chzn-done")
                                                        .chosen();
                                                    var index = $teamSelectId
                                                        .get(0).selectedIndex;
                                                    $(
                                                        "#"
                                                        + settings.teamSelectId
                                                        + "_chzn .chzn-drop .chzn-results li")
                                                        .eq(
                                                            index)
                                                        .click();
                                                }
                                                //回调函数
                                                settings
                                                    .teamCallback($this);
                                            },
                                            "isUseChosen": false
                                        });
                                } else {
                                    $teamSelectId.val("");
                                    $(
                                        "#" + settings.teamSelectId
                                        + "_chzn").remove();
                                    $teamSelectId.show().removeClass(
                                        "chzn-done").chosen();

                                    if ("stu" === settings.type) {
                                        $stuSelectId.val("");
                                        $(
                                            "#"
                                            + settings.stuSelectId
                                            + "_chzn")
                                            .remove();
                                        $stuSelectId.show()
                                            .removeClass(
                                                "chzn-done")
                                            .chosen();
                                    }

                                }
                            });
                }

                if ("stu" === settings.type) {
                    $stuSelectId.append("<option value=''>"
                        + settings.stuFirstOptTitle + "</option>");
                    $("body")
                        .on(
                            "click",
                            "#"
                            + settings.teamSelectId
                            + "_chzn .chzn-drop .chzn-results li",
                            function (event) {
                                var $this = $(this);
                                var teamIndex = $this.attr("id")
                                    .replace(
                                        settings.teamSelectId
                                        + "_chzn_o_",
                                        "");
                                var teamSelector = $teamSelectId;
                                var id = teamSelector.find("option")
                                    .eq(parseInt(teamIndex)).val();
                                if (id != "") {
                                    $.TeamStudentSelector({
                                        "selector": "#"
                                            + settings.stuSelectId,
                                        "teamId": id,
                                        "selectedVal": settings.stuSelectedVal,
                                        "firstOptionTitle": settings.stuFirstOptTitle,
                                        "afterHandler": function (
                                            $this) {
                                            if (!settings.isEchoSelected
                                                && settings.selectOne) {
                                                $this.find("option:nth-child(2)")
                                                    .prop("selected",
                                                        "selected");
                                            }
                                            $("#" + settings.stuSelectId
                                                + "_chzn")
                                                .remove();
                                            $stuSelectId
                                                .show()
                                                .removeClass(
                                                    "chzn-done")
                                                .chosen();
                                            settings.isEchoSelected = false;
                                            //回调函数
                                            settings
                                                .stuCallback($this);
                                        },
                                        "isUseChosen": false
                                    });
                                } else {
                                    $stuSelectId.val("");
                                    $(
                                        "#" + settings.stuSelectId
                                        + "_chzn").remove();
                                    $stuSelectId.show().removeClass(
                                        "chzn-done").chosen();
                                }
                            });
                }
            }

            $
                .GradeSelector({
                    "selector": "#" + settings.gradeSelectId,
                    "condition": {
                        "schoolYear": currentSchoolYear
                    },
                    "selectedVal": settings.gradeSelectedVal,
                    "firstOptionTitle": settings.gradeFirstOptTitle,
                    "afterHandler": function ($this) {
                        if (settings.isUseChosen) {
                            $("#" + settings.gradeSelectId).chosen();
                            if ("team" === settings.type
                                || "stu" === settings.type) {
                                $("#" + settings.teamSelectId).chosen();
                            }
                            if ("stu" === settings.type) {
                                $("#" + settings.stuSelectId).chosen();
                            }
                        }
                        if (settings.isEchoSelected) {
                            if (settings.isUseChosen) {
                                var index = $gradeSelectId.get(0).selectedIndex;
                                $("#" + settings.gradeSelectId
                                    + "_chzn .chzn-drop .chzn-results li")
                                    .eq(index).click();
                            } else {
                                $this.trigger("change");
                            }
                        } else if (settings.selectOne) {
                            $this.find("option:nth-child(2)").prop(
                                "selected", "selected");
                            if (settings.isUseChosen) {
                                $("#" + settings.gradeSelectId + "_chzn")
                                    .remove();
                                $gradeSelectId.show().removeClass(
                                    "chzn-done").chosen();
                                var index = $gradeSelectId.get(0).selectedIndex;
                                $("#" + settings.gradeSelectId
                                    + "_chzn .chzn-drop .chzn-results li")
                                    .eq(index).click();
                            } else {
                                $this.trigger("change");
                            }
                        }
                        //回调函数
                        settings.gradeCallback($this);
                    },
                    "isUseChosen": false
                });
        }
    })(jQuery);
</script>
