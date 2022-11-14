<%-- 
    Document   : discussReplyList
    Created on : 2012-10-12, 17:05:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lads" uri="/WEB-INF/lads" %>
<!DOCTYPE html>
<div id="discussUserStatus_<s:property value="#request.id"/>">
    <div class="search"> <span>姓名：</span>
        <input name="discussRealName_<s:property value="#request.id"/>" id="discussRealName_<s:property value="#request.id"/>" type="text"/>
        <a href="javascript:void(0)" onclick="discussNameSearch('<s:property value="#request.id"/>')" class="confirm">
            <img alt="搜索" title="搜索" src="/image/common/lads/lads_confirm_btn.jpg"/>
        </a>
        <%--        <a href="javascript:void(0)" onclick="loadDiscussReply('<s:property value="#request.id"/>')" class="intelligent">
                    <img src="/image/common/lads/lads_discuss.jpg"/>
                </a>--%>
    </div>
    <table id="discussTable_<s:property value="#request.id"/>" class="rtable">
        <thead>
            <tr>
                <th width="100">序号</th>
                <th width="200">学生姓名</th>
                <th width="400">成绩</th>
                <th width="500">上传文件</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator status="st" value="#request.voList" id="vo" >
                <tr>
                    <td><s:property value="#st.getIndex()+1"/></td>
                    <td class="discussRealNameTitle" title="<s:property value="realName"/>"><s:property value="realName"/></td>
                    <td>
                        <span id="discussScoreSpan_<s:property value="status.id"/>" >
                            <s:property value="status.score"/>
                        </span>分
                        <a href="javascript:void(0)" onclick="discussScore('<s:property value="status.id"/>')" >
                            <img alt="评分" title="评分" src="/image/common/lads/lads_pen.png"/>
                        </a>
                    </td>
                    <td>
                        <div class="interaction_content">
                            <div class="fu"> 
                                <s:iterator value="attachmentList">
                                    <div class="wen">
                                        <a class="wenjian" href="/common/resources/frontResourcesAction_download.action?fileId=<s:property value="fileId"/>">
                                            <s:property value="fileName"/>
                                        </a>
                                        <span>
                                            <a onclick="discussDeleteAttachment(this, '<s:property value="fileId"/>', '<s:property value="replyId"/>')" class="delete">
                                                删除
                                            </a>
                                        </span>
                                    </div>
                                </s:iterator>
                            </div>
                        </div>
                    </td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
</div>