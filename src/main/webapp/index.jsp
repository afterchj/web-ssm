<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
    <script type="text/javascript" src='/web-ssm/js/JsBarcode.all.min.js'></script>
</head>
<body>
<h2>Hello World!</h2>
<a href="user/showUser">userList</a>
<a href="upload.jsp">文件上传测试</a>
<a href="login.jsp">聊天室</a>
<a href="record_map.jsp">查看案列</a>
<a href="world.jsp">世界地图</a>
<a href="pandaHelp/FAQ">FAQ</a>

<form action="user/upload.do" method="post" name="file" enctype="multipart/form-data">
    <p>账号:<input name="account" value="766256898@qq.com"></p>
    <p>文件描述:<input name="desc"></p>
    <video width="320" height="240" controls>
        <source src=" http://www.uichange.com/ums3-client2/files/013ec16d5e0a461cab6b252c3fbd3ae4/ceshigeshigongchang3.mp4"
                type="video/mp4">
        <%--<source src="/web-ssm/upload/${path}" type="video/mp4">--%>
        <%--<source src="movie.ogg" type="video/ogg">--%>
        您的浏览器不支持Video标签。
    </video>
    <p><input name="file" type="file" value="浏览"></p>
    <p><input type="submit" value="login"></p>
    <img id="imgcode39">
    <br>
    <img id="imgcode128">
</form>
</body>

<script>
    console.log("生成二维码...");
    JsBarcode("#imgcode39", "Q1M24W2AXPXM63T0B0020", {
        format: "CODE39",//选择要使用的条形码类型
        width: 1,//设置条之间的宽度
        height: 15,//高度
        displayValue: true,//是否在条形码下方显示文字
        text:"N/S:Q1M24W2AXPXM63T0B0020",//覆盖显示的文本
        // fontOptions:"bold italic",//使文字加粗体或变斜体
        // font:"fantasy",//设置文本的字体
        textAlign:"center",//设置文本的水平对齐方式
        textPosition:"bottom",//设置文本的垂直位置
        textMargin:3,//设置条形码和文本之间的间距
        fontSize:8,//设置文本的大小
        background:"#fff",//设置条形码的背景
        lineColor:"#000",//设置条和文本的颜色。
        margin:2//设置条形码周围的空白边距
    });
    JsBarcode("#imgcode128", "Q1M24W2AXPXM63T0B0020", {
        format: "CODE128",//选择要使用的条形码类型
        width: 1,//设置条之间的宽度
        height: 15,//高度
        displayValue: false,//是否在条形码下方显示文字
    });

</script>
</html>
