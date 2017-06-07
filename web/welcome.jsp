<%--
  Created by IntelliJ IDEA.
  User: zcy
  Date: 2017/6/7
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎登录！
<%
out.println(request.getAttribute("name"));
%>
</body>
</html>
