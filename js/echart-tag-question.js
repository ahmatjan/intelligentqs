require.config({
     paths: {
  echarts: './js/echarts'
     }
 });

 require(
 [
     'echarts',
     'echarts/chart/line'
 ],

 DrawEChart
 );

 function DrawEChart(ec) {
     //图表渲染的容器对象
    var chartContainer = document.getElementById("main");
     //加载图表
    var myChart = ec.init(chartContainer);

    var nodes = [];
	var links = [];
	var constMaxDepth = 2;
	var constMaxChildren = 7;
	var constMinChildren = 4;
	var constMaxRadius = 10;
	var constMinRadius = 2;

	function createRandomNode(children, depth) {
	    var node = {
			 name : children['info']['name'],
			 value : children['info']['value'],
			 // Custom properties
			 id : children['info']['id'],
			 depth : depth,
			 category : depth
	    }
	    nodes.push(node);
	    return node;
	}

	function forceMockThreeData(json) {
	    var depth = 0;
	    var rootNode = {
			 name : json['root']['name'],
			 value : 0,
			 // Custom properties
			 id : 0,
			 depth : 0,
			 category : 2
			}
	    nodes.push(rootNode);

	    function mock(parentNode, 0) {
			 
			for (var i = 1; i < json['children']['list'] ; i++) {
			    var childNode = createRandomNode(json['children']['list'][i], 0);
			    links.push({
					source : parentNode.id,
					target : childNode.id,
					weight : 1
				});

				for(var j=1; j < json['children']['list'][i]['list']; j++){
					var childNode = createRandomNode(json['children']['list'][i]['list'][j], 1);
						links.push({
						source : parentNode.id,
						target : childNode.id,
						weight : 1
					});
					
					for(var k=1; k < json['children']['list'][i]['list'][j]['list']; k++){
						var childNode = createRandomNode(json['children']['list'][i]['list'][j]['list']['k'], 2);
							links.push({
							source : parentNode.id,
							target : childNode.id,
							weight : 1
						});
					};
				};
	 		}
		}

	    mock(rootNode, 0);
	}

	forceMockThreeData(json);
	console.log(nodes)
	console.log(links)

     myChart.setOption({
	  title : {
		 text: 'Force',
		 subtext: 'Force-directed tree',
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
		 data:['叶子节点','非叶子节点', '根节点']
		    },
		    series : [
			 {
			     type:'force',
			     name : "Force tree",
			     categories : [
			  	{
			      name: '叶子节点'
			  },
			  {
			      name: '非叶子节点'
			  },
			  {
			      name: '根节点'
			  }
			     ],
			     itemStyle: {
			  normal: {
			      label: {
			   show: false
			      },
			      nodeStyle : {
			   brushType : 'both',
			   strokeColor : 'rgba(255,215,0,0.4)',
			   lineWidth : 1
			      }
			  }
			     },
			     minRadius : constMinRadius,
			     maxRadius : constMaxRadius,
			     coolDown: 0.995,
			     steps: 10,

			     nodes : nodes,
			     links : links,
			     steps: 1
			 }
	    ]
 
	});
}