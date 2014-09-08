
        // 按需加载
        // Step:3 conifg ECharts's path, link to echarts.js from current page.
        // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
        require.config({
            paths: {
                echarts: './js/echarts' //echarts.js的路径
            }
        });
        // Step:4 require echarts and use it in the callback.
        // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
        require(
        [
            'echarts',
            'echarts/chart/line'
        ],
        //回调函数
        DrawEChart
        );

        //渲染ECharts图表
        function DrawEChart(ec) {
            //图表渲染的容器对象
            var chartContainer = document.getElementById("main");
            //加载图表
            var myChart = ec.init(chartContainer);
            myChart.setOption({
                title : {
			        
			        x:'right',
			        y:'bottom'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: '{a} : {b}'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    legend: {
			        x: 'left',
			        data:['家人','朋友']
			    },
			    series : [
			        {
			            type:'force',
			            name : "人物关系",
			            categories : [
			                {
			                    name: '人物'
			                },
			                {
			                    name: '家人'
			                },
			                {
			                    name:'朋友'
			                }
			            ],
			            itemStyle: {
			                normal: {
			                    label: {
			                        show: true,
			                        textStyle: {
			                            color: '#333'
			                        }
			                    },
			                    nodeStyle : {
			                        brushType : 'both',
			                        strokeColor : 'rgba(255,215,0,0.4)',
			                        lineWidth : 1
			                    }
			                },
			                emphasis: {
			                    label: {
			                        show: false
			                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
			                    },
			                    nodeStyle : {
			                        //r: 30
			                    },
			                    linkStyle : {}
			                }
			            },
			            useWorker: false,
			            minRadius : 6,
			            maxRadius : 10,
			            gravity: 1.1,
			            scaling: 1.1,
			            linkSymbol: 'arrow',
			            nodes:[
			                {category:0, name: '乔布斯', value : 10},
			                {category:1, name: '丽萨-乔布斯',value : 2},
			                {category:1, name: '保罗-乔布斯',value : 3},
			                {category:1, name: '克拉拉-乔布斯',value : 3},
			                {category:1, name: '劳伦-鲍威尔',value : 7},
			                {category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
			                {category:2, name: '奥巴马',value : 8},
			                {category:2, name: '比尔-盖茨',value : 9},
			                {category:2, name: '乔纳森-艾夫',value : 4},
			                {category:2, name: '蒂姆-库克',value : 4},
			                {category:2, name: '龙-韦恩',value : 1},
			            ],
			            links : [
			                {source : '丽萨-乔布斯', target : '乔布斯', weight : 1},
			                {source : '保罗-乔布斯', target : '乔布斯', weight : 2},
			                {source : '克拉拉-乔布斯', target : '乔布斯', weight : 1},
			                {source : '劳伦-鲍威尔', target : '乔布斯', weight : 2},
			                {source : '史蒂夫-沃兹尼艾克', target : '乔布斯', weight : 3},
			                {source : '奥巴马', target : '乔布斯', weight : 6},
			                {source : '比尔-盖茨', target : '乔布斯', weight : 6},
			                {source : '乔纳森-艾夫', target : '乔布斯', weight : 1},
			                {source : '蒂姆-库克', target : '乔布斯', weight : 1},
			                {source : '龙-韦恩', target : '乔布斯', weight : 1},
			                {source : '克拉拉-乔布斯', target : '保罗-乔布斯', weight : 1},
			                {source : '奥巴马', target : '保罗-乔布斯', weight : 1},
			                {source : '奥巴马', target : '克拉拉-乔布斯', weight : 1},
			                {source : '奥巴马', target : '劳伦-鲍威尔', weight : 1},
			                {source : '奥巴马', target : '史蒂夫-沃兹尼艾克', weight : 1},
			                {source : '比尔-盖茨', target : '奥巴马', weight : 6},
			                {source : '比尔-盖茨', target : '克拉拉-乔布斯', weight : 1},
			                {source : '蒂姆-库克', target : '奥巴马', weight : 1}
			            ]
			        }
			    ]
               
       		});
       }