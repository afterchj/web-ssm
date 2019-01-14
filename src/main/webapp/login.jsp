<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="user/showUser">用户列表</a>
<a href="user/test">文件上传测试</a>
<form action="user/login" method="post" enctype="multipart/form-data">
    <p>name:<input name="userName"></p>
    <p>pswd:<input name="userPwd" type="password"></p>
    <%--<p>photo:<input name="file" type="file" value="浏览"></p>--%>
    <p><input type="submit" value="login"></p>
</form>
</body>
</html>
