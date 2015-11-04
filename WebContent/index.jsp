<!-- 
/**
 * SVG è½¬æ¢ç±»ï¼å®ç°å°SVGæä»¶è½¬æ¢ä¸ºå¸¸è§å¾çæ ¼å¼æä»¶
 * @author Zhy
 * 
 * publish on Highchartsä¸­æç½  http://www.hcharts.cn 
 *
 */
-->
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8"> <!-- æä»¶ç¼ç ä¸å®æ¯UTF-8 -->
	<title>Highcharts Exporting Server | Power by Hcharts.cn</title>
	<meta name="description" content="Highcharts Exporting Server" />
	<!-- script type="text/javascript" src="js/jquery-1.8.3.min.js"></script -->
	<!-- http://cdn.bootcss.com/jquery/1.8.3/jquery.js -->
	<!-- script type="text/javascript" src="js/highcharts.js"></script-->
	<script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.8.3/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/4.0.1/modules/exporting.js"></script> 
	<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
	<script>
	<div id="table_div"></div>

	<script type="text/javascript">
	//<
	var sel = new SelectorView('sel_div');
	sel.src.header = {
		id			: 'Id',
		name		: 'Name',
		name_cn		: '中文名',
		text		: 'Text'
	};
	sel.dst.header = {
		id			: 'Id',
		name		: 'Name',
		name_cn		: '中文名'
	};
	sel.src.dataKey = 'id';
	sel.src.title = '可选';

	sel.dst.dataKey = 'id';
	sel.dst.title = '已选';
	sel.render();
	//>
	
	$(function () {

	    // Set up the chart
	    var chart = new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            margin: 100,
	            type: 'scatter',
	        },
	        exporting: {
	            buttons: {
	                contextButton: {
	                    menuItems: [
	                    	Highcharts.getOptions().exporting.buttons.contextButton.menuItems[0]
	                    ]
	                    .concat([{
	                        separator: true
	                    },{
	                        text: 'å¯¼åºPNGæä»¶',
	                        onclick:function() {
	                        	exportChart(chart,"image/png");
	                        }
	                    },{
	                        text: 'å¯¼åºJPGæä»¶',
	                        onclick:function() {
	                        	exportChart(chart,"image/jpeg");
	                        }
	                    },{
	                        text: 'å¯¼åºSVGæä»¶',
	                        onclick:function() {
	                        	exportChart(chart,"image/svg+xml");
	                        }
	                    },{
	                        text: 'å¯¼åºPDFæä»¶',
	                        onclick:function() {
	                        	exportChart(chart,"application/pdf");
	                        }
	                    }])
	                }
	            }
	        },
	        title: {
	            text: 'Monthly Average Temperature',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Source: WorldClimate.com',
	            x: -20
	        },
	        xAxis: {
	            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	        },
	        yAxis: {
	            title: {
	                text: 'Temperature (Â°C)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: 'Â°C'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: 'Tokyo',
	            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
	        }, {
	            name: 'New York',
	            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
	        }, {
	            name: 'Berlin',
	            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
	        }, {
	            name: 'London',
	            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
	        }]
	    });
	   
	    
	    $(".download").click(function(){
	    	exportChart(chart,$(this).attr("type"));
	    })
	    
	    $(".show").click(function(){
	    	exportShow(chart,$(this).attr("type"));
	    })
	    
	});
	
	function exportChart(chart,type) {
		$("input[name='svg']").val(chart.getSVG());
		$("input[name='width']").val(600);
		$("input[name='type']").val(type);
		$("input[name='method']").val("download");
		$("form").submit();
	}
	
	function exportShow(chart,type) {
		$.ajax({
			url:"servlet/ExportServlet",
			type:"post",
			data:{
				svg:chart.getSVG(),
				width:600,
				type:type,
				method:'show'
			},
			dataType:"json",
			success:function(data){
				$("img").attr("src","temp/chart.png");
				$("img").show();
			}
		});
	}
	
	</script>
</head>

<body>

	111111__index.jsp
	<div id="container" style="min-width:800px;height:400px"></div>
	<!-- éèçè¡¨å -->
	<form method="post" action="servlet/ExportServlet">
		<input type="hidden" name="svg">
		<input type="hidden" name="width">
		<input type="hidden" name="type">
		<input type="hidden" name="method">
	</form>

	
	<hr>

	<p>
		<img src="" style="display:none">
	</p>
</body>

</html>