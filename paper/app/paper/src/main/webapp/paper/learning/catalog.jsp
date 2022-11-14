<%-- 
    Document   : catalog
    Created on : 2014-3-17, 14:47:48
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<div class="unit-area" style="width:336px">
    <div class="hd">
        <h1><s:property value="#request.subject.name"/> <s:property value="#request.publish.name"/> <s:property value="#request.volume.name"/></h1>
    </div>
    <div id="unit-list" class="bd jspScrollable">
        <div class="jspContainer">
            <div class="jspPane">
                <ul class="unit-list mb20">
                    <li class="unit-item selected selected-unit">
                        <a  title="全部资源" class="unit-link strong ib" onclick="quesByAllUnit(this)" href="javascript:void(0)">
                            <b>全部资源</b>
                        </a>
                    </li>
                    <s:iterator value="#request.unitList">
                        <li class="unit-item">
                            <a title="<s:property value="name"/>" id="unita|<s:property value="code"/>" class="unit-link strong ib" onclick="quesByUnit(this)" href="javascript:void(0)">
                                <b><s:property value="name"/></b>
                            </a>
                            <ul id="unitli|<s:property value="id"/>|<s:property value="code"/>" class="lesson-list">
                            </ul>
                        </li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
                            function sectionSearch(cNodeId, cNodeCode) {
                                var url = "/common/portal/json/frontCommonAction_findCataByParId.action";
                                var data = {
                                    "formMap.parentId": cNodeId
                                };
                                var callBack;
                                jQuery.ajax({
                                    type: "POST",
                                    url: url,
                                    cache: false,
                                    async: false,
                                    data: data,
                                    success: function(treeHtml) {
                                        callBack = treeHtml;
                                    }
                                });
                                if (callBack.dataList != null) {
                                    $.each(callBack.dataList, function(i, node) {
                                        createSection(cNodeId, cNodeCode, node.id, node.code, node.name);
                                    });
                                }
                            }

                            function createSection(parentId, parentCode, nodeId, nodeCode, nodeName) {
                                var html = "<li class=\"lesson-item\">"
                                        + "<a onclick=\"quesBySection(this)\" id=\"sectionli|" + nodeCode + "\" class=\"lesson-link ib pl5 pt5\" title=\"" + nodeName + "\" href=\"javascript:void(0)\">" + nodeName + "</a></li>"
                                $("ul[id='unitli|" + parentId + "|" + parentCode + "']").append(html);
                            }

                            function quesBySection(obj) {
                                var sectionCode = $(obj).attr("id").split("|")[1];
                                var unitCode = $(obj).parent().parent().attr("id").split("|")[2];
                                var data = {"unitCode": unitCode, "sectionCode": sectionCode};
                                searchQuestion(data);
                                $(".unit-list").children().each(function() {
                                    $(this).attr("class", "unit-item");
                                });
                                $(".unit-list ul a").each(function() {
                                    $(this).attr("style", "color: rgb(0, 0, 0)");
                                });
                                $(obj).attr("style", "color: rgb(13, 141, 89)");
                            }

                            function quesByUnit(obj) {
                                var unitCode = $(obj).attr("id").split("|")[1];
                                var data = {"unitCode": unitCode};
                                searchQuestion(data);
                                $(".unit-list").children().each(function() {
                                    $(this).attr("class", "unit-item");
                                });
                                $(".unit-list ul a").each(function() {
                                    $(this).attr("style", "color: rgb(0, 0, 0)");
                                });
                                $(obj).parent().attr("class", "unit-item selected selected-unit")
                            }

                            function quesByAllUnit(obj) {
                                searchQuestion();
                                $(".unit-list").children().each(function() {
                                    $(this).attr("class", "unit-item");
                                });
                                $(".unit-list ul a").each(function() {
                                    $(this).attr("style", "color: rgb(0, 0, 0)");
                                });
                                $(obj).parent().attr("class", "unit-item selected selected-unit")
                            }

                            $(function() {
                                $("ul[id^='unitli']").each(function() {
                                    var parentId = $(this).attr("id").split("|")[1];
                                    var parentCode = $(this).attr("id").split("|")[2];
                                    sectionSearch(parentId, parentCode);
                                });

                                //默认查询所有章节的题目
                                quesByAllUnit($("ul[class^='unit-list'] :first a"));
                            });
</script>