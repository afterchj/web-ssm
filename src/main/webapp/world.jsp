<%--
  Created by IntelliJ IDEA.
  User: hjchen
  Date: 2021/3/4
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>世界动态地图</title>
    <script type="text/javascript" src='js/echarts.js'></script>
    <script src="js/world.js" type="text/javascript"></script>
</head>
<body>

<div id="main" style="width: 100%;height:100vh;"></div>

</body>
<script>
    // 获取echarts的容器
    var chart = echarts.init(document.getElementById("main"));
    /*
        图中相关城市经纬度,根据你的需求添加数据
        关于国家的经纬度，可以用首都的经纬度或者其他城市的经纬度
    */
    var geoCoordMap = {
        /* //东盟十国
         '广州': [113.5107, 23.2196],
         '马来西亚': [101.975766, 4.210484],
         '印度尼西亚': [113.921327, -0.789275],
         '泰国': [100.992541, 15.870032],
         '菲律宾': [121.774017, 12.879721],
         '文莱': [114.727669, 4.535277],
         '越南': [108.277199, 14.058324],
         '老挝': [102.495496, 19.85627],
         '缅甸': [95.956223, 21.913965],
         '柬埔寨': [104.990963, 12.565679],
         '新加坡': [103.51, 1.17],*/
        //一带一路
        '广州': [113.5107, 23.2196],
        '中国': [116.40,39.90],
        '蒙古': [103.846656,46.862496],
        '俄罗斯': [105.318756,61.52401],
        '东帝汶': [125.727539,-8.874217],
        '印度': [78.96288,20.593684],
        '巴基斯坦': [69.34511599999999,30.375321],
        '孟加拉国': [90.356331,23.684994],
        '斯里兰卡': [80.77179699999999,7.873053999999999],
        '阿富汗': [67.709953,33.93911],
        '尼泊尔': [84.12400799999999,28.394857],
        '马尔代夫': [73.5,4.2],
        '不丹': [90.433601,27.514162],
        '沙特阿拉伯': [45.079162,23.885942],
        '阿联酋': [53.847818,23.424076],
        '阿曼': [55.923255,21.512583],
        '伊朗': [53.688046,32.427908],
        '土耳其': [35.243322,38.963745],
        '以色列': [34.851612,31.046051],
        '埃及': [30.802498,26.820553],
        '科威特': [47.481766,29.31166],
        '伊拉克': [43.679291,33.223191],
        '卡塔尔': [51.183884,25.354826],
        '约旦': [36.238414,30.585164],
        '黎巴嫩': [35.862285,33.854721],
        '巴林': [50.36,26.12],
        '也门': [48.516388,15.552727],
        '叙利亚': [38.996815,34.80207499999999],
        '巴勒斯坦': [34.28,31.30],
        '波兰': [19.145136,51.919438],
        '罗马尼亚': [24.96676,45.943161],
        '捷克共和国': [15.472962,49.81749199999999],
        '斯洛伐克': [19.699024,48.669026],
        '保加利亚': [25.48583,42.733883],
        '匈牙利': [19.503304,47.162494],
        '拉脱维亚': [24.603189,56.879635],
        '立陶宛': [23.881275,55.169438],
        '斯洛文尼亚': [14.995463,46.151241],
        '爱沙尼亚': [25.013607,58.595272],
        '克罗地亚': [15.2,45.1],
        '阿尔巴尼亚': [20.168331,41.153332],
        '塞尔维亚': [21.005859,44.016521],
        '马其顿': [21.745275,41.608635],
        '波黑': [43.52,18.26],
        '黑山': [19.37439,42.708678],
        '哈萨克斯坦': [66.923684,48.019573],
        '乌兹别克斯坦': [64.585262,41.377491],
        '土库曼斯坦': [59.556278,38.969719],
        '吉尔吉斯斯坦': [74.766098,41.20438],
        '塔吉克斯坦': [71.276093,38.861034],
        '乌克兰': [31.16558,48.379433],
        '白俄罗斯': [27.953389,53.709807],
        '格鲁吉亚': [43.356892,42.315407],
        '阿塞拜疆': [47.576927,40.143105],
        '亚美尼亚': [45.038189,40.069099],
        '摩尔多瓦': [28.369885,47.411631]
        /*//欧美国家
        '挪威': [8.468945999999999,60.47202399999999],
        '丹麦': [9.501785,56.26392],
        '瑞典': [18.643501,60.12816100000001],
        '芬兰': [25.748151,61.92410999999999],
        '英国': [-3.435973,55.378051],
        '荷兰': [5.291265999999999,52.132633],
        '爱尔兰': [-8.24389,53.41291],
        '比利时': [4.469936,50.503887],
        '卢森堡': [6.129582999999999,49.815273],
        '法国': [2.213749,46.227638],
        '西班牙': [-3.74922,40.46366700000001],
        '葡萄牙': [-8.224454,39.39987199999999],
        '德国': [10.451526,51.165691],
        '奥地利': [14.550072,47.516231],
        '瑞士': [8.227511999999999,46.818188],
        '美国': [-95.712891,37.09024],
        '加拿大': [-102.646409, 59.994255],
        '澳大利亚': [133.775136,-25.274398],
        '新西兰': [174.885971,-40.900557]*/
    };

    /*// 东盟10国
    var DMData = [
        [{name: '广州'}, {name: "马来西亚",value: 30}],
        [{name: '广州'}, {name: "印度尼西亚",value: 30}],
        [{name: '广州'}, {name: "泰国",value: 30}],
        [{name: '广州'}, {name: "菲律宾",value: 30}],
        [{name: '广州'}, {name: "文莱",value: 30}],
        [{name: '广州'}, {name: "越南",value: 30}],
        [{name: '广州'}, {name: "老挝",value: 30}],
        [{name: '广州'}, {name: "缅甸",value: 30}],
        [{name: '广州'}, {name: "柬埔寨",value: 30}],
        [{name: '广州'}, {name: "新加坡",value: 30}]
    ];*/
    //一带一路
    var D1LData = [
        [{name: '广州'}, {name: "蒙古",value: 30}],
        [{name: '广州'}, {name: "俄罗斯",value: 30}],
        [{name: '广州'}, {name: "东帝汶",value: 30}],
        [{name: '广州'}, {name: "印度",value: 30}],
        [{name: '广州'}, {name: "巴基斯坦",value: 30}],
        [{name: '广州'}, {name: "孟加拉国",value: 30}],
        [{name: '广州'}, {name: "斯里兰卡",value: 30}],
        [{name: '广州'}, {name: "阿富汗",value: 30}],
        [{name: '广州'}, {name: "尼泊尔",value: 30}],
        [{name: '广州'}, {name: "马尔代夫",value: 30}],
        [{name: '广州'}, {name: "不丹",value: 30}],
        [{name: '广州'}, {name: "沙特阿拉伯",value: 30}],
        [{name: '广州'}, {name: "阿联酋",value: 30}],
        [{name: '广州'}, {name: "阿曼",value: 30}],
        [{name: '广州'}, {name: "伊朗",value: 30}],
        [{name: '广州'}, {name: "土耳其",value: 30}],
        [{name: '广州'}, {name: "以色列",value: 30}],
        [{name: '广州'}, {name: "埃及",value: 30}],
        [{name: '广州'}, {name: "科威特",value: 30}],
        [{name: '广州'}, {name: "伊拉克",value: 30}],
        [{name: '广州'}, {name: "卡塔尔",value: 30}],
        [{name: '广州'}, {name: "约旦",value: 30}],
        [{name: '广州'}, {name: "黎巴嫩",value: 30}],
        [{name: '广州'}, {name: "巴林",value: 30}],
        [{name: '广州'}, {name: "也门",value: 30}],
        [{name: '广州'}, {name: "叙利亚",value: 30}],
        [{name: '广州'}, {name: "巴勒斯坦",value: 30}],
        [{name: '广州'}, {name: "波兰",value: 30}],
        [{name: '广州'}, {name: "罗马尼亚",value: 30}],
        [{name: '广州'}, {name: "捷克共和国",value: 30}],
        [{name: '广州'}, {name: "斯洛伐克",value: 30}],
        [{name: '广州'}, {name: "保加利亚",value: 30}],
        [{name: '广州'}, {name: "匈牙利",value: 30}],
        [{name: '广州'}, {name: "拉脱维亚",value: 30}],
        [{name: '广州'}, {name: "立陶宛",value: 30}],
        [{name: '广州'}, {name: "斯洛文尼亚",value: 30}],
        [{name: '广州'}, {name: "爱沙尼亚",value: 30}],
        [{name: '广州'}, {name: "克罗地亚",value: 30}],
        [{name: '广州'}, {name: "阿尔巴尼亚",value: 30}],
        [{name: '广州'}, {name: "塞尔维亚",value: 30}],
        [{name: '广州'}, {name: "马其顿",value: 30}],
        [{name: '广州'}, {name: "波黑",value: 30}],
        [{name: '广州'}, {name: "黑山",value: 30}],
        [{name: '广州'}, {name: "哈萨克斯坦",value: 30}],
        [{name: '广州'}, {name: "乌兹别克斯坦",value: 30}],
        [{name: '广州'}, {name: "土库曼斯坦",value: 30}],
        [{name: '广州'}, {name: "吉尔吉斯斯坦",value: 30}],
        [{name: '广州'}, {name: "塔吉克斯坦",value: 30}],
        [{name: '广州'}, {name: "乌克兰",value: 30}],
        [{name: '广州'}, {name: "白俄罗斯",value: 30}],
        [{name: '广州'}, {name: "格鲁吉亚",value: 30}],
        [{name: '广州'}, {name: "阿塞拜疆",value: 30}],
        [{name: '广州'}, {name: "亚美尼亚",value: 30}],
        [{name: '广州'}, {name: "摩尔多瓦",value: 30}]
    ];
    /*var OMData = [ //欧美国家
        [{name: '广州'}, {name: "挪威",value: 30}],
        [{name: '广州'}, {name: "丹麦",value: 30}],
        [{name: '广州'}, {name: "瑞典",value: 30}],
        [{name: '广州'}, {name: "芬兰",value: 30}],
        [{name: '广州'}, {name: "英国",value: 30}],
        [{name: '广州'}, {name: "荷兰",value: 30}],
        [{name: '广州'}, {name: "爱尔兰",value: 30}],
        [{name: '广州'}, {name: "比利时",value: 30}],
        [{name: '广州'}, {name: "卢森堡",value: 30}],
        [{name: '广州'}, {name: "法国",value: 30}],
        [{name: '广州'}, {name: "西班牙",value: 30}],
        [{name: '广州'}, {name: "葡萄牙",value: 30}],
        [{name: '广州'}, {name: "德国",value: 30}],
        [{name: '广州'}, {name: "奥地利",value: 30}],
        [{name: '广州'}, {name: "瑞士",value: 30}],
        [{name: '广州'}, {name: "美国",value: 30}],
        [{name: '广州'}, {name: "加拿大",value: 30}],
        [{name: '广州'}, {name: "澳大利亚",value: 30}],
        [{name: '广州'}, {name: "新西兰",value: 30}]
    ];*/

    // 小飞机的图标，可以用其他图形替换
    // var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

    // 获取地图中起点和终点的坐标，以数组形式保存下来
    var convertData = function(data) {
        console.log("fromCoord:",geoCoordMap['中国']);
        console.log("toCoord:",geoCoordMap['广州']);
        var res = [];
        for(var i = 0; i < data.length; i++) {
            var dataItem = data[i];
            var fromCoord = geoCoordMap[dataItem[0].name];
            var toCoord = geoCoordMap[dataItem[1].name];
            if(fromCoord && toCoord) {
                res.push([{
                    coord: fromCoord // 起点坐标
                }, {
                    coord: toCoord // 终点坐标
                }])
            }
        }
        console.log("res：",res);
        return res;
    }

    var color = ['#9ae5fc', '#dcbf71']; // 自定义图中要用到的颜色
    var series = []; // 用来存储地图数据

    [
        // ['广州', DMData],
        ['广州', D1LData]
        // ['广州', OMData]
    ].forEach(function(item, i) {
        series.push({
            // 白色航线特效图
            type: 'lines',
            zlevel: 1, // 用于分层，z-index的效果
            effect: {
                show: true, // 动效是否显示
                period: 6, // 特效动画时间
                trailLength: 0.7, // 特效尾迹的长度
                color: '#fff', // 特效颜色
                symbolSize: 3 // 特效大小
            },
            lineStyle: {
                normal: { // 正常情况下的线条样式
                    color: color[0],
                    width: 0, // 因为是叠加效果，要是有宽度，线条会变粗，白色航线特效不明显
                    curveness: -0.2 // 线条曲度
                }
            },
            data: convertData(item[1]) // 特效的起始、终点位置
        }, { // 小飞机航线效果
            type: 'lines',
            zlevel: 2,
            //symbol: ['none', 'arrow'],   // 用于设置箭头
            symbolSize: 10,
            effect: {
                show: true,
                period: 6,
                trailLength: 0,
                // symbol: planePath, // 特效形状，可以用其他svg pathdata路径代替
                symbolSize: 5
            },
            lineStyle: {
                normal: {
                    color: color[0],
                    width: 1,
                    opacity: 0.6,
                    curveness: -0.2
                }
            },
            data: convertData(item[1]) // 特效的起始、终点位置，一个二维数组，相当于coords: convertData(item[1])
        }, { // 散点效果
            type: 'effectScatter',
            coordinateSystem: 'geo', // 表示使用的坐标系为地理坐标系
            zlevel: 3,
            rippleEffect: {
                brushType: 'stroke' // 波纹绘制效果
            },
            label: {
                normal: { // 默认的文本标签显示样式
                    show: true,
                    position: 'left', // 标签显示的位置
                    formatter: '{b}' // 标签内容格式器
                }
            },
            itemStyle: {
                normal: {
                    color: color[0]
                }
            },
            data: item[1].map(function(dataItem) {
                return {
                    name: dataItem[1].name,
                    value: geoCoordMap[dataItem[1].name], // 起点的位置
                    symbolSize: dataItem[1].value / 8, // 散点的大小，通过之前设置的权重来计算，val的值来自data返回的value
                };
            })
        });
    });

    // 显示终点位置,类似于上面最后一个效果，放在外面写，是为了防止被循环执行多次
    series.push({
        type: 'effectScatter',
        coordinateSystem: 'geo',
        zlevel: 3,
        rippleEffect: {
            brushType: 'stroke'
        },
        label: {
            normal: {
                show: true,
                position: 'left',
                formatter: '{b}'
            }
        },
        symbolSize: function(val) {
            return val[2] / 8;
        },
        itemStyle: {
            normal: {
                color: color[1]
            }
        },
        data: [{
            name: '广州',
            value: [113.5107, 23.2196, 30],
            label: {
                normal: {
                    position: 'right'
                }
            }
        }]
    });

    // 最后初始化世界地图中的相关数据
    chart.setOption({
        backgroundColor: '#000',
        title: {
            text: '广州出口世界税额、退税额',
            subtext:'一带一路',
            textStyle: {
                color: '#fff',
                fontSize: 20
            },
            top: '10px',
            left: '10px'
        },
        geo: {
            map: 'world', // 与引用进来的地图js名字一致
            roam: true, // 禁止缩放平移
            itemStyle: { // 每个区域的样式
                normal: {
                    areaColor: '#323c48'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            },
            regions: [{ // 选中的区域
                name: 'China',
                selected: false,
                itemStyle: { // 高亮时候的样式
                    emphasis: {
                        areaColor: '#7d7d7d'
                    }
                },
                label: { // 高亮的时候不显示标签
                    emphasis: {
                        show: false
                    }
                }
            }]
        },
        series: series, // 将之前处理的数据放到这里
        textStyle: {
            fontSize: 12
        }
    });

    console.log("数据加载完成：",series);
</script>
</html>
