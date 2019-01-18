<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <title>echarts.js案例三</title>
    <link rel="stylesheet" href="css/record.css">
    <script type="text/javascript" src='/web-ssm/js/echarts.js'></script>
    <script type="text/javascript" src='/web-ssm/js/jquery-1.7.min.js'></script>
</head>
<%--<script type="text/javascript">--%>
<%--$(function () {--%>
<%--$.ajax({--%>
<%--type: "GET",--%>
<%--url: "charts/showYearExpend",--%>
<%--//                data: {username:$("#username").val(), content:$("#content").val()},--%>
<%--dataType: "json",--%>
<%--success: function (data) {--%>
<%--//                console.log("data=" + JSON.stringify(data));--%>
<%--$('#resText').empty();   //清空resText里面的所有内容--%>
<%--var html = '';--%>
<%--$.each(data, function (commentIndex, comment) {--%>
<%--html += '<div class="comment"><h6>' + comment['perYear'] + '：' + comment['total'] + '</div>';--%>
<%--});--%>
<%--$('#resText').html(html);--%>
<%--}--%>
<%--});--%>
<%--});--%>
<%--</script>--%>
<body>
<div id="resText"></div>
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
    </select>
    <input id="search" type="button" value="查询" onclick="initMonth()">
</div>
<div id="main1" class="small"></div>
<div id="month" class="month"></div>
<script type="text/javascript" src="js/record.js"></script>
</body>
<script type="text/javascript">
    //    $.ajaxSetup({
    //        async: false
    //    });
    //    $(function () {
    //        console.log("初始化函数！" + date);
    //        $("#search").bind("click", function () {
    //            initMonth(date);
    //        });
    //    });

</script>
</html>