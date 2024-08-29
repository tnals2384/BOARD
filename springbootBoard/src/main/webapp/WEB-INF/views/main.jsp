<%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/22/24
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 메인</title>
</head>
<%@ include file="../common/header.jsp"%>
<h2>게시판에 오신 것을 환영합니다!</h2>
<a href="<%=request.getContextPath()%>/music/list">[글 목록 보러가기]</a>
<a href="<%=request.getContextPath()%>/music/write">[글 작성하기]</a>
</body>
</html>
