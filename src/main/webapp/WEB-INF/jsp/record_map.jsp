<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <title>echarts.js案例三</title>
    <link rel="stylesheet" href="../css/record.css">
    <script type="text/javascript" src='/web-ssm/js/echarts.js'></script>
    <script type="text/javascript" src='/web-ssm/js/jquery-1.7.min.js'></script>
</head>
<script type="text/javascript">
    $(function () {
        $("#date").change(function () {
            var date = $("#date").val();
            var account = $("#account").val();
            console.log("date=" + date + ",account=" + account);
//            $.ajax({
//                type: "GET",
//                url: "../charts/showYearExpend",
//                data: {username: date, content: account},
//                dataType: "json",
//                success: function (data) {
//                    console.log("data=" + JSON.stringify(data));
//                }
//            });
        });
    });
</script>
<body>
<h6>当前账号：${account}</h6>
<div id="main" class="small"></div>
<div id="year" class="year"></div>
<div class="search">
    <input id="account" value="${account}" type="hidden">
    选择日期：
    <select id="date" name="date" style="width: 100px;margin-right: 20px">
        <option value="2019-01-01" selected>2018</option>
        <option value="2018-01-01">2017</option>
        <option value="2017-01-01">2016</option>
        <option value="2016-01-01">2015</option>
        <option value="2015-01-01">2014</option>
    </select>
    <input id="search" type="button" value="查询" onclick="initMonth()">
</div>
<div id="main1" class="small"></div>
<div id="month" class="month"></div>
<script type="text/javascript" src="../js/record.js"></script>
</body>
</html>