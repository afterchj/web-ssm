<%--
  Created by IntelliJ IDEA.
  User: hongjian.chen
  Date: 2018/2/26
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
    function checkFile() {
        var name = $('#file').val();
        var str = name.substring(name.lastIndexOf('.'));
        if (str == '.xls') {
            return true;
        } else {
            alert("请选择.xls格式的excel文件！");
            return false;
        }
    }
</script>
</head>
<body>
<div>
    <form method="post" action="uploadExcel" enctype="multipart/form-data">
        <h1>黑名单EXCEL:</h1>
        <br> <input type="file" name="file" id="file"/>
        <input type="submit" value="确认文件" onclick="return checkFile()"/>
    </form>
    <p class="required" style="position: absolute;left: 1500px;top: 134px" id="message">${reMessage}</p>
</div>

</body>
</html>
