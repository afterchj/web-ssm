<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>echarts.js案例一</title>
    <script type="text/javascript" src='/web-ssm/js/echarts.js'></script>
    <script type="text/javascript" src='/web-ssm/js/jquery-1.7.min.js'></script>
</head>
<style type="text/css">
    .year {
        margin-left: 20px;
        width: 600px;
        height: 300px;
        float: left;
    }

    .month {
        clear: left;
        width: 800px;
        height: 400px;
    }
</style>
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
<%--<div id="main" class="small"></div>--%>
<%--<div id="chart" class="year"></div>--%>
<div id="main1" class="month"></div>
</body>
<script type="text/javascript">
    //    $.ajaxSetup({
    //        async: false
    //    });
    //项目案例
    var mChart = echarts.init(document.getElementById('main1'));
    mChart.showLoading();
    $.get(
        "charts/perMonth",
        {startTime: '2019-01-01', endTime: '2019-01-01'}
    ).done(function (data) {
        var monthExpend = [];
        var monthIncome = [];
//        console.log("perMonth=" + JSON.stringify(data));
        $.each(data, function (i, result) {
            if (result.trade_type.trim() == '收入') {
                monthIncome.push(result.total);
            } else {
                monthExpend.push(result.total);
            }
        });
        var myOption = {
            title: {
                text: '年度账单一月收入支出情况',
                subtext: '支付宝账单'
            },
            legend: {
                data: ['收入金额', '支出金额']
            },
            xAxis: {
                data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
            },
            yAxis: {},
            tooltip: {
                show: true,
                formatter: '系列名:{a}<br />类目:{b}<br />数值:{c}'
            },
            series: [{
                name: '收入金额',
                type: 'bar',
                data: monthIncome,
//                data: [200, 312, 431, 241, 175, 275, 369],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [{
                        type: 'average', name: '平均值', itemStyle: {
                            normal: {
                                color: 'green'
                            }
                        }
                    }]
                }
            }, {
                name: '支出金额',
                type: 'line',
//                data: [321, 432, 543, 376, 286, 298, 400],
                data: monthExpend,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [{
                        type: 'average', name: '平均值', itemStyle: {
                            normal: {
                                color: 'blue'
                            }
                        }
                    }]
                }
            }]
        };
        mChart.hideLoading();
        mChart.setOption(myOption);
    });
</script>
</html>