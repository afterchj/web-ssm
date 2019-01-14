<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>echarts.js案例三</title>
    <script type="text/javascript" src='/web-ssm/js/echarts.js'></script>
    <script type="text/javascript" src='/web-ssm/js/jquery-1.7.min.js'></script>
</head>
<style type="text/css">

    .small {
        float: left;
        width: 30%;
        height: 45%;
    }

    .year {
        float: left;
        width: 60%;
        height: 45%;
    }

    .month {
        float: left;
        width: 60%;
        height: 45%;
    }

    .search {
        clear: both;
        text-align: right;
        width: 90%;
        float: left;
        /*margin-right: 5%;*/
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
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('year'));
    myChart.showLoading();
    //饼状图
    var pipChart = echarts.init(document.getElementById('main'));
    pipChart.showLoading();
    var pipChart1 = echarts.init(document.getElementById('main1'));
    pipChart1.showLoading();
    var account = $("#account").val();
    $.getJSON(
        "charts/perYear",
        {date: '2019-01-01', account: account}
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
                //X轴设置
                xAxis: {
                    data: [2014, 2015, 2016, 2017, 2018]
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
//                    data: [68018.91, 36321.00, 11408.23, 10502.00, 8620.00],
                    data: yearIncome,
//                    data: [json[0].total, json[1].total, json[2].total],
                    itemStyle: {
                        normal: {
                            color: 'green',
                            label: {
                                show: true,
                                rotate: 45,
                                padding: [0, 0, 5, 40],
                                position: 'top',
                                textStyle: {
                                    color: 'grey'
                                }
                            }
                        }
                    }
                }, {
                    name: '支出金额',
                    type: 'bar',
//                    data: [35484.01, 80774.24, 145262.08],
                    data: yearExpend,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                rotate: 45,
                                padding: [0, 0, 5, 40],
                                position: 'top',
                                textStyle: {
                                    color: 'gray'
                                }
                            }
                        }
                    }
                }]
            };
            myChart.hideLoading();
            myChart.setOption(options);
//            console.log("yearExpend=" + JSON.stringify(yearExpend));
//            console.log("yearIncome=" + JSON.stringify(yearIncome));
            var pipOption = {
                title: {
                    text: '历年的收入信息图',
                    subtext: '来自支付宝',
                    x: 'center'
                },
                tooltip: {
                    show: true,
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['2014年', '2015年', '2016年', '2017年', '2018年']
                },
                series: [
                    {
                        name: '类目',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: yearIncome[0], name: '2014年'},
                            {value: yearIncome[1], name: '2015年'},
                            {value: yearIncome[2], name: '2016年'},
                            {value: yearIncome[3], name: '2017年'},
                            {value: yearIncome[4], name: '2018年'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            pipChart.hideLoading();
            pipChart.setOption(pipOption);
            var pipOption = {
                title: {
                    text: '历年的消费信息图',
                    subtext: '来自支付宝',
                    x: 'center'
                },
                tooltip: {
                    show: true,
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['2014年', '2015年', '2016年', '2017年', '2018年']
                },
                series: [
                    {
                        name: '金额',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: yearExpend[0], name: '2014年'},
                            {value: yearExpend[1], name: '2015年'},
                            {value: yearExpend[2], name: '2016年'},
                            {value: yearExpend[3], name: '2017年'},
                            {value: yearExpend[4], name: '2018年'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            pipChart1.hideLoading();
            pipChart1.setOption(pipOption);
        }
    });
    //项目案例
    var mChart = echarts.init(document.getElementById('month'));
    var date = $("#date").val();
    var account = $("#account").val();
    mChart.showLoading();
    function initMonth() {
        var date = $("#date").val();
        console.log("date=" + date + ",account=" + account);
        $.get(
            "charts/perMonth",
            {date: date, account: account}
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
                    itemStyle: {
                        normal: {
                            color: 'green',
                        }
                    },
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
                                    color: 'black'
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
    }
    initMonth();
</script>
</html>