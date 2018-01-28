<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script type="text/javascript">
		function uppercase(x) {
			var y = document.getElementById(x).value
			document.getElementById(x).value = y.toUpperCase();
		}
	</script>

	<title>登录</title>
</head>

<body>
	<span>
		<% 
			if(request.getAttribute("err") != null) {
				out.println(request.getAttribute("err") + "<br/>");
			}
		%>
	</span>
	<form id="login" method="post" action="/demo/ParamServlet">
		<p>用户:</p>
		 <input id="inUser" type="text"  class="for
 		    m-control" name="username" onchange="uppercase(this.id)">
		<p>密码:</p><input type="text" name="pass" />
		<input type="submit" value="登录" />
	</form>
</body>
</html>