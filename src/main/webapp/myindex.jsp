<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
    <title>时间选择</title>
    <script type="text/javascript" src="js/jquery-1.7.min.js"></script>

    <script type="text/javascript">


        $(document).ready(function () {
            $('#show').click(function () {
                alert($('#beginTime').val());
            });
        });


    </script>
</head>
<body>


</body>
<h1>时间选择：</h1>
<!--<input type="time" value="13:59"/>-->
<input id="beginTime" type="time" value="13:59:59"/>
<button id="show">showTime</button>
</html>