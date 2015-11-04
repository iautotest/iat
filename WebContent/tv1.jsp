<div id="table_div">111

</div>

<script type="text/javascript">

var table = new TableView('table_div');
table.header = {
	id			: 'Id',
	name		: 'Name',
	name_cn		: '中文名',
	text		: 'Text',
	input		: '输入'
};
table.dataKey = 'id';
table.display.filter = true;
table.display.pager = true;
table.display.title = true; // 显示标题
table.display.marker = true; // 显示行选择框
table.display.pager = false; // 不显示分页控件
table.pager.size = 3;

var input_html = '<input type="text" name="a" size="2" class="textbox" />';

// table.addRange() 可以用来添加数据列表.
table.add({id: 0, name: 'None', name_cn: '幽灵', text: 'None', input: input_html});
table.add({id: 1, name: 'Tom', name_cn: '汤姆', text: 'Tomcat', input: input_html});
table.add({id: 2, name: 'Jerry', name_cn: '杰瑞', text: 'Jerrimy', input: input_html});

table.render();

</script>