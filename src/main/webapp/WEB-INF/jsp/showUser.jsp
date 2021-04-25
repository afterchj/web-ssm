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
    <script type="text/javascript" src="../js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src='/web-ssm/js/JsBarcode.all.min.js'></script>

    <style type="text/css">
        a {
            margin: auto 5px;
        }

        table {
            width: 900px;
            border-collapse: collapse;
        }

        table td {
            width: 60px;
        }
    </style>
</head>
<body>
<table border="1px">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>nick</td>
        <td>email</td>
        <td>department</td>
        <td>qrCode</td>
        <td>操作</td>
    </tr>
    <c:if test="${!empty userList}">
        <c:forEach items="${userList}" var="user" varStatus="u">
            <tr>
                <td>${u.index}_${user.id}</td>
                <td>${user.user_name}</td>
                <td>${user.nick_name}</td>
                <td>${user.email}</td>
                <td>${user.dept_name}</td>
                <td><img id="imagecode${user.id}"  alt="${user.user_name}"></td>
                <td><a href="editUI.do?id=${user.id}">修改</a><a href="delete.do?id=${user.id}">删除</a></td>
            </tr>
        </c:forEach>
    </c:if>
    <tr>
        <td colspan="3"><a href="editUI.do?id=">新增</a></td>
        <td colspan="3">
            <a href="http://172.24.230.36:8003/excel/export?moduleCode=101">导出</a>
            <%--            <input id="export" type="button" value="导出">--%>
        </td>
    </tr>
</table>
</body>
<script>
    $("#export").click(function () {
        // let users={};
        $.getJSON("http://172.24.230.36:8082/user/list", function (result) {
            // users=result;
            <%--let users = '<%=request.getAttribute("userList")%>';--%>
            let data = {
                "moduleCode": "101",
                "data": result
            }
            console.log("data ", data, "\n", typeof result);
        });
        let data = "{\n" +
            "    \"moduleCode\":\"101\",\n" +
            "    \"data\":[{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":1,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":7,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":2,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":8,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":3,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":9,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":4,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":10,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":5,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":11,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":6,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":12,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":7,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":13,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":8,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":14,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":9,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":15,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1},{\"createTime\":\"2020/12/07 17:13:04\",\"businessId\":10,\"fromName\":\"叶阳志\",\"backType\":\"INTERNAL_GENERAL\",\"id\":16,\"fromId\":\"YEYANGZHI\",\"content\":\"我提交了一个内销一般问题反馈\",\"likeNum\":1}]\n" +
            "}"
        $.ajax({
            type: "post",
            url: "http://172.24.230.36:8003/excel/export",
            data: data,
            dataType: 'json',  //【这里要小心啊，不要用jsonp，一定是json】
            responseType: 'blob',
            contentType: 'application/json',
            crossDomain: true,  //【这个很重要，一定要加】
            success: function (result) {
                console.log("success ", result);
                var blob = new Blob([result]);
                var link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = "myFileName.txt";
                link.click();
            },
            error: function (result) {
                console.log("error ", result);
            }
        });

    });

    let datas="${userList}";
    console.log("type",typeof datas,"value",datas)
    console.log("生成二维码...",datas.length);
    for (let i = 0; i < 10; i++) {
        let code = $("#imagecode9");
        console.log("id",code.attr("id"),"alt",code.attr("alt"))
        JsBarcode("#imagecode"+(i+1), "scz20210419/001", {
            format: "CODE39",//选择要使用的条形码类型
            width: 1,//设置条之间的宽度
            height: 50,//高度
            displayValue: true,//是否在条形码下方显示文字
            text: "scz20210419/001(订单号/序号)",//覆盖显示的文本
            // fontOptions:"bold italic",//使文字加粗体或变斜体
            // font:"fantasy",//设置文本的字体
            textAlign: "center",//设置文本的水平对齐方式
            textPosition: "bottom",//设置文本的垂直位置
            textMargin: 5,//设置条形码和文本之间的间距
            fontSize: 12,//设置文本的大小
            background: "#fff",//设置条形码的背景
            lineColor: "#000",//设置条和文本的颜色。
            margin: 5//设置条形码周围的空白边距
        });
    }


</script>
</html>
