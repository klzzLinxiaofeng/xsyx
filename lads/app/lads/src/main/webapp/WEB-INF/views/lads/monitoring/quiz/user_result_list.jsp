<%-- 
    Document   : user_result_list
    Created on : 2012-9-18, 11:46:26
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--表格开始-->
<div class="floattable floattab0">
    <table>
        <thead>
            <tr>
                <th width="277">学生姓名</th>
                <th width="277">成绩</th>
                <th width="277">完成时间</th>
                <th width="277">操作</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="#request.voList" var="vo">
                <s:if test="resultList.size()<=0">
                    <tr onMouseMove="this.style.background = '#f0f7fd'" onMouseOut="this.style.background = ''">
                        <td align="center" width="277">
                            ${vo.realName}
                        </td>
                        <td align="center" width="277">
                            -
                        </td>
                        <td align="center" width="277">
                            -
                        </td>
                        <td align="center" width="277">
                            -
                        </td>
                    </tr>
                </s:if>
                <s:iterator status="st" var="result" id="result" value="resultList">
                    <tr onMouseMove="this.style.background = '#f0f7fd'" onMouseOut="this.style.background = ''">
                        <s:if test="#st.getIndex()==0">
                            <td align="center" rowspan="<s:property value="resultList.size()"/>" width="277" >
                                ${vo.realName}
                            </td>
                        </s:if>
                        <td align="center" width="277">
                            ${result.score} 分
                        </td>
                        <td align="center" width="277">
                            <s:date name="createTime" format="yyyy年MM月dd日 HH:mm"/>
                        </td>
                        <td align="center" width="277">
                            <a target='_blank'
                               href='/common/lads/ladsQuizAction_reportQuizResult.action?resultId=${result.id}&score=true&sysType=<s:property value="#request.sysType"/>'>查看详情</a>
                        </td>
                    </tr>
                </s:iterator>
            </s:iterator>
        <script type="text/javascript">
            $(function(){

            })
        </script>
        </tbody>
    </table>
</div>
<!--表格结束-->