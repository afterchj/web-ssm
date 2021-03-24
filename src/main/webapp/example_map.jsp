<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <title>echarts.js案例一</title>
    <script type="text/javascript" src='js/echarts.js'></script>
</head>
<style type="text/css">
    .small {
        margin-left: 20px;
        width: 400px;
        height: 400px;
        float: left;
    }
    .big{
        clear: left;
        width: 1000px;
        height: 600px;
    }
</style>
<body>
<div id="main" class="small"></div>
<div id="chart" class="small"></div>
<div id="main1" class="big"></div>
</body>
<script type="text/javascript">
    //解析json数组即对JSONArray的遍历
    var data = [{name: "a", age: 12}, {name: "b", age: 11}, {name: "c", age: 13}, {name: "d", age: 14}];
    for (var o in data) {
        console.log("o=" + o);
        console.log("data[o]=" + data[o]);
        console.log("key:" + data[o].name + " ,value:" + data[o].age);
    }
    //遍历json对象
    var myJson = {"name": "after", "password": "1111"};
    for (var p in myJson) {//遍历json对象的每个key/value对,p为key
        console.log(p + " " + myJson[p]);
    }
    var json = [
        {"name": "key1", "value": "12"},
        {"name": "key2", "value": "32"},
        {"name": "key3", "value": "45"},
        {"name": "key4", "value": "21"},
        {"name": "key5", "value": "6"}
    ];
    var str = JSON.stringify(json);
    console.log("str=" + typeof str + ",json=" + typeof json);
    //饼状图
    var pipChart = echarts.init(document.getElementById('main'));
    var pipOption = {
        title: {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
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
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
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
    pipChart.setOption(pipOption);
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart'));
    var options = {
        //定义一个标题
        title: {
            text: '测试成绩'
        },
        legend: {
            data: ['销量']
        },
        //X轴设置
        xAxis: {
            data: ['60分', '70分', '80分', '90分', '100分']
        },
        yAxis: {},
        tooltip: {
            show: true,
            formatter: '系列名:{a}<br />类目:{b}<br />数值:{c}'
        },
        //name=legend.data的时候才能显示图例
        series: [{
            name: '销量',
            type: 'bar',
            data: [json[0].value, json[1].value, json[2].value, json[3].value, json[4].value],
//            data:['12','32','45','21','12'],
//                itemStyle: {
//                    normal: {
//                        label: {
//                            show: true,
//                            position: 'top',
//                            textStyle: {
//                                color: '#000000'
//                            },
//                            formatter: function (params) {
//                                if (params.value == 0) {
//                                    return '';
//                                } else {
//                                    return params.value;
//                                }
//                            }
//                        }
//                    }
//                },
            markLine: {
                data: [
                    {
                        type: 'average', name: '平均值', itemStyle: {
                        normal: {
                            color: 'green'
                        }
                    }
                    }
                ]
            }
        }]
    };
    myChart.setOption(options);
    var echart = echarts.init(document.getElementById('main1'));
    var myOption = {
        title: {
            text: '模拟商店一周销售情况',
            subtext: '虚拟数据'
        },
        legend: {
            data: ['购买金额', '销售金额']
        },
        xAxis: {
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {},
        tooltip: {
            show: true,
            formatter: '系列名:{a}<br />类目:{b}<br />数值:{c}'
        },
        series: [{
            name: '购买金额',
            type: 'bar',
            data: [200, 312, 431, 241, 175, 275, 369],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine: {
                data: [
                    {
                        type: 'average', name: '平均值', itemStyle: {
                        normal: {
                            color: 'green'
                        }
                    }
                    }
                ]
            }
        }, {
            name: '销售金额',
            type: 'line',
            data: [321, 432, 543, 376, 286, 298, 400],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine: {
                data: [
                    {
                        type: 'average', name: '平均值', itemStyle: {
                        normal: {
                            color: 'blue'
                        }
                    }
                    }
                ]
            }
        }]
    };
    echart.setOption(myOption);
</script>
</html>