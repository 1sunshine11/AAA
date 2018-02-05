<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="easyui/themes/black/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<script src="easyui/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="easyui/jquery.easyui.min.js" type="text/javascript"
	charset="utf-8"></script>
</head>


<body>

	<div class="easyui-layout"
		style="width: 100%; height: 700px; margin: 0 auto;">
		<div data-options="region:'north'"
			style="height: 100px; background-color: #e2e2e2;">
			<div class="yjk">
				<a>管理员界面</a>
			</div>
		</div>
		<!--<div data-options="region:'south',split:true" style="height:100px;"></div>-->
		<div data-options="region:'east',split:true,iconCls:'icon-tip'"
			title="须知" style="width: 300px;">
			<div class="xinxi">
				<p style="color: red">&nbsp;&nbsp;1.查询功能可单条件查询，也可多条件查询，姓名为模糊查询。</p>
				<p>&nbsp;&nbsp;2.添加功能尚未实现。</p>
				<p style="color: red">&nbsp;&nbsp;3.性别 1代表男 0代表女。</p>
				<p style="color: red">&nbsp;&nbsp;4.是否 1代表是 0代表否。</p>
				<p style="color: red">&nbsp;&nbsp;5.管理员的删除操作及是 将是否删除列的值改为1。</p>
			</div>

		</div>
		<div
			data-options="region:'west',split:true,resizable:false,iconCls:'icon-man'"
			title="已登录管理员信息" style="width: 300px;">
			<table class="easyui-propertygrid" style="width: 293px"
				data-options="
                url: '/EXAM/AdminServlet',
                method: 'post',
                showGroup: true,
                scrollbarSize: 0,
                columns: mycolumns
            ">
			</table>
		</div>
		<div
			data-options="region:'center',title:'欢 迎 帅 气 的 管理员！',iconCls:'icon-ok'">
			<table id="dg" class="easyui-datagrid" title="管理用户信息"
				style="width: auto; height: auto"
				data-options="
				fitColumns:'true',	
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#tb',
                url: '/EXAM/UserServlet?action=showAllUser',
                method: 'post',
                pagination:true,
                rownumbers:true,
                nowarp:false,
				pageSize:5,
				pageNumber:1,
				pageList:[5,10,15,25,30],
                onClickCell: onClickCell
              
            ">
				<thead>
					<tr>
						<th
							data-options="field:'id',width:20">ID</th>
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
							                        }]   }},formatter:function(value,row){
							                          
									                 return row.sex==1?'男':'女';
								}">性别</th>
						<th
							data-options="field:'descr',width:90,align:'center',editor:{type:'textbox'}">描述</th>
						<th
							data-options="field:'birthdate',width:50,align:'center',editor:'textbox'">出生日期</th>
						<th data-options="field:'createtime',width:80,align:'center'">建号时间</th>
						<th
							data-options="field:'updatetime',width:80,align:'center'">修改日期</th>
						<th
							data-options="field:'isdelete',width:30,align:'center',
							                 formatter:function(value,row){
							                 return row.isdelete==1?'是':'否';
								          }">是否删除</th>
						<th
							data-options="field:'iseffect',width:35,align:'center',
							                  editor:{type:'combobox',
							                 options:{
							                valueField:'label',
							                textField:'value',
							                data:[{
							                        label:1,
							                        value:'有效'},{
							                        label:0,
							                        value:'失效'
							                        }]
							                    }},formatter:function(value,row){
							                          
									                 return row.iseffect==1?'有效':'失效';
								}">是否有效</th>
						<th
							data-options="field:'isadmin',width:60,align:'center',editor:{type:'combobox',
							                options:{
							                valueField:'label',
							                textField:'value',
							                data:[{
							                        label:1,
							                        value:'无敌管理员'},{
							                        label:0,
							                        value:'普通用户'
							                        }]
							                    }},formatter:function(value,row){
							                          
									                 return row.isadmin==1?'无敌管理员':'普通用户';
								}
							                 ">是否是管理员</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">上一步</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" onclick="select()">查询</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" onclick="searchAll()">显示全部信息</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" onclick="logout()">退出登录</a>
	</div>
	<!--查询框-->
	<div id="dlg" class="easyui-dialog" title="查询用户信息"
		style="width: 400px; height: 800px; padding: 10px"
		data-options="
				iconCls: 'icon-search',
				
				buttons: [{
					
					iconCls:'icon-search',
					text:'GO!',
					handler:search
				}]
			">

		<div class="input2">
			<div class="in1">
				<input id="Semail" class="easyui-textbox"
					data-options="prompt:'输入账号查询'" style="width: 200px; height: 40px;" name="SS">

			</div>
			<div class="in2">
				<input id="Sname" class="easyui-textbox"
					data-options="prompt:'输入姓名查询'" style="width: 200px; height: 40px;" name="SS">

			</div>
			<div class="in3">
				<input id="Ssex" class="easyui-textbox"
					data-options="prompt:'输入性别查询'" style="width: 200px; height: 40px;" name="SS">

			</div>
			<div class="in3">
				<input id="Sdescr" class="easyui-textbox"
					data-options="prompt:'输入描述查询'" style="width: 200px; height: 40px;" name="SS">

			</div>
			<div class="in4">
				<input id="Sisd" class="easyui-textbox"
					data-options="prompt:'是否删除查询'" style="width: 200px; height: 40px;" name="SS">

			</div>
			<div class="in5">
				<input id="Sisf" class="easyui-textbox"
					data-options="prompt:'是否失效查询'" style="width: 200px; height: 40px;" name="SS">

			</div>
			<div class="in8" style="margin-top:20px;">
				<input id="SbtStart" class="easyui-textbox"
					data-options="prompt:'生日范围起始时间'"style="width: 120px; height: 40px;" name="SS">
                <input id="SbtEnd" class="easyui-textbox"
					data-options="prompt:'生日范围结束时间'"style="width: 120px; height: 40px;" name="SS">
			</div> 
			<div class="in6">
				<input id="SctStart" class="easyui-textbox"
					data-options="prompt:'创建时间起始时间'"style="width: 120px; height: 40px;" name="SS">
                <input id="SctEnd" class="easyui-textbox"
					data-options="prompt:'创建时间结束时间'"style="width: 120px; height: 40px;" name="SS">
			</div>
			<div class="in7">
				<input id="SutStart" class="easyui-textbox"
					data-options="prompt:'输入修改起始时间'"style="width: 120px; height: 40px;" name="SS">
                <input id="SutEnd" class="easyui-textbox"
					data-options="prompt:'输入修改结束时间'"style="width: 120px; height: 40px;" name="SS">
			</div>
		</div>

	</div>
	<!--用于查询-->
	<div id="div_show" style="display: none"></div>
	<div id="div_Semail" style="display: none"></div>
	<div id="div_Sname" style="display: none"></div>
	<div id="div_Ssex" style="display: none"></div>
	<script type="text/javascript">
			$(document).ready(function() {
				$('#dlg').dialog('close'); //页面加载时关闭查询框
			});
			
			
				
			
		


			var editIndex = undefined;

			function endEditing() {
				if(editIndex == undefined) {
					return true
				} //执行结束
				if($('#dg').datagrid('validateRow', editIndex)) { //验证当前行是否有效
					$('#dg').datagrid('endEdit', editIndex);
					editIndex = undefined;
					return true;
				} else {
					return false;
				}
			}
			var idd;
			var idindex;
			function onClickCell(index, field) { //点击修改
				if(editIndex != index) { //判断是不是一行
					if(endEditing()) {
						$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index); //当前行变为可编辑状态
					     idindex=$('#dg').datagrid('getData').rows[index];
					     idd=$('#dg').datagrid('getData').rows[index].id;//选择当前行的id
						var ed = $('#dg').datagrid('getEditor', {
							index: index,
							field: field
						}); //找到编辑器：后面是当前方法的参数
						if(ed) { //有没有
							//target编辑器的内容
							($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						}
						editIndex = index;
					} else {
						setTimeout(function() {
							$('#dg').datagrid('selectRow', editIndex);
						}, 0);
					}
				}
			}



			function append() {

				if(endEditing()) {
					$('#dg').datagrid('appendRow', {

					});
					editIndex = $('#dg').datagrid('getRows').length - 1;
					$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
				}
				
			}
            //删除操作
			function removeit() {
				//数据库delete
                var rid=idd;//获得点击行的id
                if(rid==null|| rid=="" || rid==undefined){
                	return;
                }
				var roww = $('#dg').datagrid('getRows'); 
				for(var i=0;i<roww.length;i++){
					var rvv = $("#dg").datagrid('getData').rows[i];
					
					
				}
				 var param="/EXAM/UserServlet";//请求的资源路径
					$.ajax({  //ajax请求对象
						type:"post",
						url:param,
						data:"action=userDelete&id="+rid+"",//请求要提交的信息对应submit把form表单发送到servlet
						dataType:"text",
						contentType:'application/x-www-form-urlencoded;charset=UTF-8',
						success:function(data){//data  存放从后台返回的数据
							$("#dg").datagrid('reload');
						}//不可加","
						});
	
			}
            //保存修改操作
			function accept() {
				if(endEditing()) {
					$('#dg').datagrid('acceptChanges');
				}
				
				var id=idd;
				if(id==null|| id=="" || id==undefined){
					return;
				}
				//update
				var rowIndex=$('#dg').datagrid('getRowIndex',idindex);
				var row = $('#dg').datagrid('getRows'); //得到行对象
				for(var i=0;i<row.length;i++){//遍历对象得到每一行
					var rv = $("#dg").datagrid('getData').rows[i];//得到每一行的数据对象
					if(i==rowIndex){//取出数据
						 var email = rv.email;  
					     var name = rv.name;  
					     var sex = rv.sex;  
					     var descr =rv.descr;  
					     var birthdate = rv.birthdate; 
					     var createtime = rv.createtime;  
					     var updatetime = rv.updatetime;  
					     var isdelete = rv.isdelete; 
					     var iseffect = rv.iseffect; 
					     var isadmin = rv.isadmin; 
					}
				}
			
				  

				 var param="/EXAM/UserServlet";//请求的资源路径
				$.ajax({  //ajax请求对象
					type:"post",
					url:param,
					data:"action=userUpdate&id="+id+"&email="+email+"&name="+name+"&sex="+sex+"&descr="+descr+"&birthdate="+birthdate+"&createtime="+createtime+"&updatetime="+updatetime+"&isdelete="+isdelete+"&iseffect="+iseffect+"&isadmin="+isadmin+"",//请求要提交的信息对应submit把form表单发送到servlet
					dataType:"text",
					contentType:'application/x-www-form-urlencoded;charset=UTF-8',
					success:function(data){//data  存放从后台返回的数据
						$("#dg").datagrid('getData');
					}//不可加","
					});
			}

			function reject() {
				$('#dg').datagrid('rejectChanges');
				editIndex = undefined;
			}

			function select() {
				$('#dlg').dialog('open');
			}
			

	//ajax加载页面数据到后台  url是表格加载数据库数据的url
	//查询
			function search() { 
				$("#dg").datagrid("load",({
					email:$("#Semail").val(),
					name:$("#Sname").val(),
					sex:$("#Ssex").val(),
					isdelete:$("#Sisd").val(),
					iseffect:$("#Sisf").val(),
					createtime:$("#Sct").val(),
					updatetime:$("#Sut").val(),
					descr:$("#Sdescr").val(),
					BirthStart:$("#SbtStart").val(),
				    BirthStart:$("#SbtEnd").val(),
				CreatetimeStart:$("#SctStart").val(),
				CreatetimeEnd:$("#SctEnd").val(),
				UpdatetimeStart:$("#SutStart").val(),
				UpdatetimeEnd:$("#SutEnd").val(),
				}))
// 				$("input[name='SS']").textbox("setValue", "");
				$("#Semail").textbox("setValue", "");
				$("#Sname").textbox("setValue", "");
				$("#Ssex").textbox("setValue", "");
				$("#Sisd").textbox("setValue", "");
				$("#Sisf").textbox("setValue", "");
				$("#Sct").textbox("setValue", "");
				$("#Sut").textbox("setValue", "");
				$("#Sdescr").textbox("setValue", "");
				$("#SbtStart").textbox("setValue", "");
				$("#SbtEnd").textbox("setValue", "");
				$("#SctStart").textbox("setValue", "");
				$("#SctEnd").textbox("setValue", "");
				$("#SutStart").textbox("setValue", "");
				$("#SutEnd").textbox("setValue", "");
			}
			function searchAll() { 
				$("#dg").datagrid("load",({
					email:"",
					name:"",
					sex:"",
					isdelete:"",
					iseffect:"",
					createtime:"",
					updatetime:"",
					descr:"",
				}))
			}

			//退出登录
			function logout() {
				
				 var param="/EXAM/login";//请求的资源路径
					$.ajax({  //ajax请求对象
						type:"post",
						url:param,
						data:"action=adminlogout",//提交注销请求
						dataType:"text",
						contentType:'application/x-www-form-urlencoded;charset=UTF-8',
						success:function(data){//data  存放从后台返回的数据
						  alert("注销成功!")	
						  window.location.href = "index.jsp";//注销成功后返回登录界面
						}//不可加","
						});
			}
			//正在登录的管理员信息表 字段
			 var mycolumns = [[
				  {field:'name',title:'姓名',width:80},
	               {field:'email',title:'账号',width:50,resizable:false}
        ]];
		</script>
</body>

</html>