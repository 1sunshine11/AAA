<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/NewFile.css" />
<script src="easyui/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
	function f3() {
		window.location.href = "zhuce.jsp";

	}
</script>
</head>

<body>
	<div class="head">
		<div class="inhead">
			<a class="biao">用户信息管理系统</a>
		</div>
	</div>
	<div class="cont">
		<div class="stishi">${su}</div>
		<div class="box">
			<div class="top">
				<div class="font">
					<a onclick="f1()" id="x1" style="color: #f56600;">用户登录</a> <span
						class="line"></span><a onclick="f2()" id="x2">管理员登录</a>
				</div>

			</div>
			<div class="under" id="xsd">
				<div class="under-in">

					<form>
						<div class="text1">
							<input type="text" name="username" id="username" value=""
								placeholder="&nbsp&nbsp邮箱" /> <a id="UEemail"
								style="color: red"></a>
						</div>

						<div class="text2">
							<input type="password" name="userpass" id="userpass" value=""
								placeholder="&nbsp&nbsp密码" /><a id="UEpass" style="color: red"></a>
						</div>
						<div class="text3">
							<div class="yanz1">
								<img alt="验证码" src="/EXAM/yanzServlet.do"
									onclick="this.src='yanzServlet.do?'+Math.random();"
									style="width: 150px; height: 52px;">
							</div>
							<input type="text" name="yanz" id="yanz"
								placeholder="&nbsp&nbsp填写验证码" />
						</div>
						<div
							style="color: red; width: 200px; height: 20px; margin-top: 10px"
							id="UEyanz"></div>
						<input class="button" type="button" name="panduan" id=""
							onclick="userSumbit()" value="登录" />
					</form>
					<div class="zi">
						<!-- 					  不提交的按钮不能放到form表单里 -->
						<a onclick="f3()">立即注册</a>
					</div>
				</div>

			</div>
			<div id="xsd1" class="under2"
				style="position: relative; left: 0; top: 0; display: none;">
				<div class="under-in">

					<form>
						<div class="text1">
							<input type="text" name="admname" id="admname" value=""
								placeholder="&nbsp&nbsp管理员账号" />
						</div>
						<a id="ANhint" style="color: red;"></a>
						<div class="text2">
							<input type="password" name="admpass" id="admpass" value=""
								placeholder="&nbsp&nbsp密码" />
						</div>
						<a id="APhint" style="color: red;"></a>
						<div class="text3">
							<div class="yanz1">
								<img alt="验证码" src="/EXAM/yanzServlet.do"
									onclick="this.src='yanzServlet.do?'+Math.random();"
									style="width: 150px; height: 52px;">
							</div>
							<input type="text" id="yanz2" name="yanz"
								placeholder="&nbsp&nbsp请填写正确验证码" />
						</div>
						<a id="AYhint" style="color: red;"></a>
						<div
							style="color: red; width: 200px; height: 20px; margin-top: 10px">${yanz}</div>
						<input class="button" type="button" name="panduan" id=""
							onclick="adminlogin()" value="开始管理" />
					</form>

				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		//选项卡
		var a1 = document.getElementById('x1');
		var a2 = document.getElementById('x2');
		var div1 = document.getElementById('xsd');
		var div2 = document.getElementById('xsd1');
		function f1() {
			a1.style.color = "#f56600";
			a2.style.color = "#e0e0e0";
			div1.style.display = "block";
			div2.style.display = "none";
		}
		function f2() {
			a1.style.color = "#e0e0e0";
			a2.style.color = "#f56600";
			div1.style.display = "none";
			div2.style.display = "block";
		}
		function userSumbit() {
			var username = $("#username").val();
			var userpass = $("#userpass").val();
			var yanz = $("#yanz").val();
			if (username == "" || username == null) {
				$("#UEemail").html("账号不能为空!");
				return;
			}
			if (userpass == "" || userpass == null) {
				$("#UEpass").html("密码不能为空!");
				return;
			}
			if (yanz == "" || yanz == null) {
				$("#UEyanz").html("请填写验证码!");
				return;
			}
			var userparam = "/EXAM/login";
			$
					.ajax({
						type : "post",
						url : userparam,
						dataType : "text",
						contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
						data : "action=userlogin&username=" + username
								+ "&userpass=" + userpass + "&yanz=" + yanz
								+ "",
						success : function(data) {
							if (data == 1) {
								window.location.href = "user.jsp";
							}
							if (data == 0) {
								$("#UEyanz").html("这么简单的验证码都能写错？!");
							}
							if (data == 2) {
								$("#UEemail").html("账号都删除了还登!");
							}
							if (data == 3) {
								$("#UEemail").html("账号失没失效不知道？");
							}
							if (data == 4) {
								$("#UEemail").html("账号或密码错误!");
							}
							if (data == 5) {
								$("#UEemail").html("自己啥账号没点数吗!");
							}
						}
					})

		}

		function adminlogin() {

			var admname = $("#admname").val();
			var admpass = $("#admpass").val();
			var yanz = $("#yanz2").val();
			if (admname == "" || admname == null) {
				$("#ANhint").html("账号不能为空!");
				return;
			}
			if (admpass == "" || admpass == null) {
				$("#APhint").html("密码不能为空!");
				return;
			}
			if (yanz == "" || yanz == null) {
				$("#AYhint").html("请填写验证码!");
				return;
			}
			var param = "/EXAM/login";
			$
					.ajax({
						type : "post",
						url : param,
						dataType : "text",
						contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
						data : "action=adminlogin&admname=" + admname
								+ "&admpass=" + admpass + "&yanz=" + yanz + "",//可以传过一行的对象row
						success : function(data) {
							if (data == "-1") {
								$("#ANhint").html("账号已删除!");
							}
							if (data == "3") {
								$("#ANhint").html("账号已失效!");
							}
							if (data == "1") {
								$("#AYhint").html("验证码错误!");
							}
							if (data == "0") {
								window.location.href = "admin.jsp";
							}
							if (data == "2") {
								$("#ANhint").html("账号或密码错误!");
							}
						}

					})
		}
	</script>
</body>
</html>