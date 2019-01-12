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
        height: 600px;
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
<div id="chart" class="year"></div>
<%--<div id="main1" class="month"></div>--%>
</body>
<script type="text/javascript">

    //    $.ajaxSetup({
    //        async: false
    //    });
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart'));
    myChart.showLoading();
    $.getJSON(
        "charts/perYear",
        {startTime: '2018-01-01', endTime: '2018-01-01'}
    ).done(function (data, status) {
        var yearExpend = [];
        var yearIncome = [];
        if (status == 'success') {
            $.each(data, function (i, result) {
                if (result.trade_type.trim() == '收入') {
                    yearIncome.push(result.total);
                } else {
                    yearExpend.push(result.total);
                }
            });
            var options = {
                //定义一个标题
                title: {
                    text: '年度总收入支出'
                },
                legend: {
                    data: ['收入金额', '支出金额']
                },
//                grid: {
//                    width: 600,
//                    height: 300
//                },
                //X轴设置
                xAxis: {
                    data: [2016, 2017, 2018]
                },
                yAxis: {},
                tooltip: {
                    show: true,
                    formatter: '系列名:{a}<br />类目:{b}<br />数值:{c}'
                },
                //name=legend.data的时候才能显示图例
                series: [{
                    name: '收入金额',
                    type: 'bar',
//            data: [68018.91, 36321.00, 11408.23, 10502.00, 8620.00],
                    data: yearIncome,
//                    data: [json[0].total, json[1].total, json[2].total],
//            itemStyle: {
//                normal: {
//                    label: {
//                        show: true,
//                        position: 'top',
//                        textStyle: {
//                            color: '#615a5a'
//                        },
//                        formatter: function (params) {
//                            if (params.value == 0) {
//                                return '';
//                            } else {
//                                return params.value;
//                            }
//                        }
//                    }
//                }
//            },
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
                    type: 'bar',
//                    data: [35484.01, 80774.24, 145262.08],
                    data: yearExpend,
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
                                    color: 'black'
                                }
                            }
                        }]
                    }
                }]
            };
            myChart.hideLoading();
            myChart.setOption(options);
//            console.log("yearExpend=" + JSON.stringify(yearExpend));
//            console.log("yearIncome=" + JSON.stringify(yearIncome));
        }
    });
</script>
</html>