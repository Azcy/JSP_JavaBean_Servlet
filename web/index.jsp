<%--
  Created by IntelliJ IDEA.
  User: zcy
  Date: 2017/6/6
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <!-- 输出错误信息 -->
  <span style="color: crimson;font-weight: bold">
    <%
    if (request.getAttribute("err")!=null){
        out.println(request.getAttribute("err")+"<br/>");
    }
    %>
  </span>
  请输入用户名和密码：
  <!-- 登录表单，该表单提交到Servlet -->
  <form action="login" method="post">
    用户名：<input type="text" name="username">
    <br>
    密&nbsp;&nbsp;码： <input type="text" name="pass">
    <br>
    <input type="submit" value="提交">
  </form>
  </body>
</html>
