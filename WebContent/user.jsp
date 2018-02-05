<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
<script src="easyui/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="easyui/jquery.easyui.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background-image: url(img/bg.jpg);
	background-size: 100%;
}

.input2 {
	width: 250px;
	height: 40px;
	margin: 0 auto;
}

.yjk {
	width: 300px;
	height: 100px;
	position: absolute;
	top: 33px;
	left: 90px;
}

.yjk a {
	font-family: "微软雅黑";
	font-size: 30px;
	font-weight: bold;
	font-style: italic;
}
</style>

<body>

	<div class="easyui-layout"
		style="width: 100%; height: 700px; margin: 0 auto;">
		<div data-options="region:'north'"
			style="height: 100px; background-color: #e2e2e2;">
			<div class="yjk">
				<a>用户登录界面</a>
			</div>
		</div>
		<!--<div data-options="region:'south',split:true" style="height:100px;"></div>-->
		<div data-options="region:'east',split:true,iconCls:'icon-tip'"
			title="用户须知" style="width: 300px;">
			<div class="xinxi">
				<p>&nbsp;&nbsp;1.性别 1代表男 0代表女。</p>
				<p>&nbsp;&nbsp;2.用户只能修改 账号 姓名 性别 描述 出生日期。</p>
				<p>&nbsp;&nbsp;3.0和1 代表用户信息状态。</p>
				<p style="color: red">&nbsp;&nbsp;4.用户的删除操作是将
					删除状态值修改为1,等待管理员进行操作。</p>
			</div>

		</div>
		<div
			data-options="region:'west',split:true,resizable:false,iconCls:'icon-man'"
			title="已登录用户姓名" style="width: 300px;">
			<table class="easyui-propertygrid" style="width: 293px"
				data-options="
                url: '/EXAM/UserServlet?action=showUser',
                method: 'get',
                
                scrollbarSize: 0,
                columns: mycolumns
            ">
			</table>
		</div>
		<div
			data-options="region:'center',title:'欢 迎 尊 敬 的 用 户 先 生！',iconCls:'icon-ok'">
			<table id="dg" class="easyui-datagrid" title="用户信息"
				style="width: auto; height: auto"
				data-options="
				fitColumns:'true',	
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#tb',
                url: '/EXAM/UserServlet?action=showUser',
                method: 'get',
                
                onClickCell: onClickCell
              
            ">
				<thead>
					<tr>
						<th data-options="field:'id',width:20">ID</th>
						<th
							data-options="field:'email',width:50,align:'center',editor:{ type:'textbox',options:{required:true}}">账号</th>

						<th
							data-options="field:'name',width:50,align:'center',editor:{type:'textbox'}">姓名</th>
						<th
							data-options="field:'sex',width:50,align:'center',
							editor:{
							      type:'combobox',
							      options:{
							                valueField:'label',
							                textField:'value',
							                data:[{
							                        label:1,
							                        value:'男'},{
							                        label:0,
							                        value:'女'
							                        }]
							                    }},formatter:function(value,row){
							                          
									                 return row.sex==1?'男':'女';
								}">性别</th>
						<th
							data-options="field:'descr',width:90,align:'center',editor:{type:'textbox'}">描述</th>
						<th
							data-options="field:'birthdate',width:40,align:'center',editor:'textbox'">出生日期</th>
						<th data-options="field:'createtime',width:80,align:'center'">建号时间</th>
						<th data-options="field:'updatetime',width:80,align:'center'">修改日期</th>
						<th data-options="field:'isadmin',width:40,align:'center',
							                      formatter:function(value,row){
							                                return row.isadmin==1?'无敌管理员':'普通用户';
								          }">权限</th>
						<th data-options="field:'isdelete',width:30,align:'center',
						                    formatter:function(value,row){
							                 return row.isdelete==1?'是':'否';
								          }">是否删除</th>
						<th data-options="field:'iseffect',width:30,align:'center',
						                      formatter:function(value,row){
							                 return row.iseffect==1?'有效':'失效';
								          }
						                   ">是否有效</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="tb" style="height: auto">
		<!--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>-->
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">上一步</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" onclick="logout()">注销用户</a>
	</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#dlg').dialog('close'); //页面加载时关闭查询框
		});
		var editIndex = undefined;

		function endEditing() {
			if (editIndex == undefined) {
				return true
			} //执行结束
			if ($('#dg').datagrid('validateRow', editIndex)) { //验证当前行是否有效
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}

		function onClickCell(index, field) { //点击修改
			if (editIndex != index) { //判断是不是一行
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index); //当前行变为可编辑状态
					var ed = $('#dg').datagrid('getEditor', {
						index : index,
						field : field
					}); //找到编辑器：后面是当前方法的参数
					if (ed) { //有没有
						//target编辑器的内容
						($(ed.target).data('textbox') ? $(ed.target).textbox(
								'textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function() {
						$('#dg').datagrid('selectRow', editIndex);
					}, 0);
				}
			}
		}

		function removeit() {

			//数据库delete

			var roww = $('#dg').datagrid('getRows');
			for (var i = 0; i < roww.length; i++) {
				var rvv = $("#dg").datagrid('getData').rows[i];
				var did = rvv.id;

			}
			var param = "/EXAM/UserServlet";//请求的资源路径
			$
					.ajax({ //ajax请求对象
						type : "post",
						url : param,
						data : "action=userDelete&id=" + did + "",//请求要提交的信息对应submit把form表单发送到servlet
						dataType : "text",
						contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
						success : function(data) {//data  存放从后台返回的数据
							$("#dg").datagrid('reload');
						}//不可加","
					});
		}

		function accept() {

					     if (endEditing()) {
							$('#dg').datagrid('acceptChanges');
						  }
			//update
			var row = $('#dg').datagrid('getSelected');
			var param = "/EXAM/UserServlet?action=userUpdate";//请求的资源路径
			$
					.ajax({ //ajax请求对象
						type : "post",
						url : param,
						data : row,
						dataType : "text",
						// 					async:false,//同步执行
						contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
						success : function(data) {//data  存放从后台返回的数据
							$("#dg").datagrid('getData');
// 							$("#dg").datagrid('loadData', data)
// 						    $("#dg").datagrid('reload');
						}
					});

		}

		function reject() {
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}

		function select() {
			$('#dlg').dialog('open');
		}

		function logout() {

			var param = "/EXAM/login";//请求的资源路径
			$
					.ajax({ //ajax请求对象
						type : "post",
						url : param,
						data : "action=logout",//提交注销请求
						dataType : "text",
						contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
						success : function(data) {//data  存放从后台返回的数据
							alert("注销成功!")
							window.location.href = "index.jsp";//注销成功后返回登录界面
						}//不可加","
					});

		}

		var mycolumns = [ [ {
			field : 'name',
			title : '姓名',
			width : 80
		}, {
			field : 'email',
			title : '账号',
			width : 50,
			resizable : false
		} ] ];
	</script>
</body>

</html>