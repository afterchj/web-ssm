<%--
  Created by IntelliJ IDEA.
  User: Zhangxq
  Date: 2016/7/16
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
    <style type="text/css">
        a {
            margin: auto 20px;
        }

        table {
            width: 510px;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<table border="1px">
    <tr>
        <td>name</td>
        <td>mobilePhone</td>
        <td>email</td>
        <td>password</td>
        <td>操作</td>
    </tr>
    <c:if test="${!empty userList}">
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.userName}</td>
                <td>${user.userPhone}</td>
                <td>${user.userEmail}</td>
                <td>${user.userPwd}</td>
                <td><a href="editUI.do?id=${user.id}">修改</a><a href="delete.do?id=${user.id}">删除</a></td>
            </tr>
        </c:forEach>
    </c:if>
    <tr>
        <td colspan="5"><a href="editUI.do?id=">新增</a></td>
    </tr>
</table>
</body>
</html>
