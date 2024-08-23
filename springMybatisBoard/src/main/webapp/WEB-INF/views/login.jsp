<%@ page import="org.example.springMybatisBoard.model.dto.MemberDTO" %><%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>로그인</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="checkbox"] {
            margin-right: 10px;
        }
        label {
            font-size: 14px;
            color: #666;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<%
    String userId = null;
    boolean rememberMe = false;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("id".equals(cookie.getName())) {
                userId = cookie.getValue();
                rememberMe = true;
                break;
            }
        }
    }

%>
<div class="container">
    <h1>로그인</h1>
    <form action="<%=request.getContextPath()%>/member/login" method="post">
        <input type="text" name="userid" placeholder="ID" value="<%= (userId != null) ? userId : "" %>" required/><br>
        <input type="password" name="userpw" placeholder="PW" required/><br>
        <input type="checkbox" name="rememberMe" id="rememberMe" <%= rememberMe ? "checked" : "" %> />아이디 기억하기<br>
        <input type="submit" value="로그인" />
    </form>
</div>
</body>
</html>
