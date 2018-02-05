<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="easyui/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>
<style type="text/css">
body {
	position: absolute;
}

.text3 {
	width: 300px;
	height: 48px;
	margin-top: 15px;
}

.yanz1 {
	display: inline-block;
}

.text3 input {
	border: 1px solid #e0e0e0;
	width: 130px;
	height: 48px;
	line-height: 48px;
	display: inline-block;
	float: right;
}

.button {
	border: 0 none;
	margin-top: 20px;
	width: 300px;
	height: 50px;
	line-height: 50px;
	display: block;
	margin-bottom: 14px;
	text-align: center;
	font-size: 14px;
	color: #fff;
	cursor: pointer;
	background-color: #f56600;
}

textarea {
	width: 300px;
	height: 90px;
	resize: none;
}
</style>
</head>
<body bgcolor="#f9f9f9">
	<div
		style="width: 800px; height: 800px; background-color: white; overflow: hidden; position: relative; left: 570px; top: 45px;">
		<p style="text-align: center; font-weight: bold; font-size: 20px">注册页面</p>
		<br />
		<div style="width: 300px; height: 800px; margin: 0 auto;">
			<form action="" method="" style="width:535px">
				<input placeholder="&nbsp&nbsp请填写用户名(邮箱)" type="text"
					name="username" id="email" value=""
					style="width: 300px; height: 40px;" /> <a id="Ehint" style="color: red;"></a>
				<br /> <br /> <input placeholder="&nbsp&nbsp请填写密码" type="password"
					name="userpass" id="pass" value=""
					style="width: 300px; height: 40px" /><a id="Phint" style="color: red;"></a> <br /> <br /> <input
					placeholder="&nbsp&nbsp请填写真实姓名" type="text" id="name" value=""
					name="name" style="width: 300px; height: 40px;" /><a id="Nhint" style="color: red;"></a> <br /> <br />
				男：<input type="radio" id="check2" value=1 name="check" /> 女：<input
					type="radio" id="check" value=0 name="check" /><a id="Chint" style="color: red;"></a> <br /> <br />
				<p>描述：</p>
				<textarea name="descr" id="descr"></textarea>
				<br /> <br /> <input placeholder="&nbsp&nbsp请填写出生日期" type="text"
					id="birth" value="" name="birth"
					style="width: 300px; height: 40px;" /> <br /> <br />
				<div class="text3">
					<div class="yanz1">
						<img alt="验证码" src="/EXAM/yanzServlet.do"
							onclick="this.src='yanzServlet.do?'+Math.random();"
							style="width: 130px; height: 52px;">
					</div>
					<input placeholder="&nbsp&nbsp请填写正确验证码" type="text" name="yanz"
						id="yanz" />
				</div>
				<div
					id="Yhint" style="color: red; width: 200px; height: 20px; margin-top: 10px;text-align: right;">${yanz1}</div>
				<input type="button" name="panduan" id="" value="注册" class="button"
					onclick="btn()" />
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function btn() {
			if ($("#email").val() == "") {
				$("#Ehint").html("邮箱不能为空!");
				return false;
			}
			var email = $("#email").val();
			if (!email
					.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
				$("#Ehint").html("邮箱格式不正确!");
				$("#email").focus();
			}
			if ($("#pass").val() == "") {
				$("#Phint").html("密码不能为空!");
				return false;
			}
			if ($("#name").val() == "") {
				$("#Nhint").html("姓名不能为空!");
				return false;
			}
			var check = $("input[type='radio']:checked").val();
			if (check == null) {
				$("#Chint").html("请选择性别!");
				return false;
			}
			if ($("#yanz").val() == "") {
				$("#Yhint").html("请填写验证码!");
				return false;
			}
			var pass = $("#pass").val();
			var name = $("#name").val();

			var descr = $("#descr").val();
			var birth = $("#birth").val();
			var yanz = $("#yanz").val();
			var param = "/EXAM/login";//请求的资源路径
			$
					.ajax({ //ajax请求对象
						type : "post",
						url : param,
						data : "action=register&username=" + email + "&userpass=" + pass
								+ "&name=" + name + "&sex=" + check + "&descr="
								+ descr + "&yanz=" + yanz + "&birth=" + birth
								+ "",//请求要提交的信息对应submit把form表单发送到servlet
						dataType : "text",
						contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
						success : function(data) {//data  存放从后台返回的数据
							if (data == "1") {
								alert("注册成功!");
								window.location.href = "index.jsp";

							} else if (data == "0") {
								$("#Ehint").html("用户名重复!");
							} else if (data == "3") {
								$("#Yhint").html("验证码错误!");
							}

						}//不可加","
					});
		}
	</script>
</body>
</html>