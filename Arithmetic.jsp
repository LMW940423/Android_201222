<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=2.0">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Numerical Arithmetic</title>
</head>
<body>
	<%
		int a = 30;
		int b = 20;

		int sum = a + b;
		int sub = a - b;
		int mul = a * b;
		int div = a / b;
		int mod = a % b;
	%>
	<%=a %> + <%=b %> = <%=sum %><br>
	<%=a %> - <%=b %> = <%=sub %><br>
	<%=a %> * <%=b %> = <%=mul %><br>
	<%=a %> / <%=b %> = <%=div %><br>
	<%=a %> % <%=b %> = <%=mod %><br>
</body>
</html>