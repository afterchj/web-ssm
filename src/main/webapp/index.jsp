<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="user/showUser">userList</a>
<a href="upload.jsp">文件上传测试</a>
<a href="login.jsp">test</a>

<form action="user/upload.do" method="post" name="file" enctype="multipart/form-data">
    <p>文件名:<input name="fileName"></p>
    <p>文件描述:<input name="desc"></p>
    <p><input name="file" type="file" value="浏览"></p>
    <p><input type="submit" value="login"></p>
</form>
</body>
</html>
