<%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 메인</title>
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
            text-align: center;
            width: 300px;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        a {
            display: block;
            margin: 10px 0;
            color: #007BFF;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .hr {
            margin: 20px 0;
            border: 0;
            border-top: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>게시판 메인</h1>
    <%
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null) {
    %>
    <a href="<%=request.getContextPath()%>/user.do?action=login">[로그인]</a>
    <%
    } else {
    %>
    <a href="<%=request.getContextPath()%>/user.do?action=logout">[로그아웃]</a>
    <%
        }
    %>
    <div class="hr"></div>
    <a href="<%=request.getContextPath()%>/board.do?action=list">[목록보기]</a>
    <a href="<%=request.getContextPath()%>/board.do?action=writeForm">[글 작성하기]</a>
</div>
</body>
</html>
