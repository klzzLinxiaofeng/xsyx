<%--
  Created by IntelliJ IDEA.
  User: panfei
  Date: 2017/6/15
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tr>
	<td style="padding:0;border:0 none;">
<script>
    $(function(){
        //增权限
        var add = ${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)};
        //查权限
        var check = ${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)};
        //改权限
        var update = ${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)};
        //删权限
        var del = ${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)};
        if(add){
            $(".permission_add").show();
        }else{
            $(".permission_add").hide();
        }
        if(check){
            $(".permission_check").show();
        }else{
            $(".permission_check").hide();
        }
        if(update){
            $(".permission_update").show();
        }else{
            $(".permission_update").hide();
        }
        if(del){
            $(".permission_del").show();
        }else{
            $(".permission_del").hide();
        }
    });
</script>
	</td>
</tr>

