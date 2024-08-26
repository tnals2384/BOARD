<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.springMybatisBoard.model.dto.BoardDTO" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 목록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin: 4px 2px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        a {
            text-decoration: none;
            color: #000;
        }
    </style>
</head>
<body>
<h1>글 목록</h1>
<table>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <%
        Map<String, Object> pageData = (Map<String, Object>) request.getAttribute("pageData");
        List<BoardDTO> bList = (List<BoardDTO>) pageData.get("boardList");

        if (bList != null) {
            for (BoardDTO b : bList) {
    %>
    <tr>
        <td><a href="<%= request.getContextPath() %>/board/<%= b.getNo() %>"><%= b.getNo() %></a></td>
        <td><a href="<%= request.getContextPath() %>/board/<%= b.getNo() %>"><%= b.getTitle() %></a></td>
        <td><%= b.getWriter() %></td>
        <td><%= b.getRegDate() %></td>
        <td><%= b.getReadCount() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="7">게시글이 없습니다.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<div class="pagination">
    <%
        int startPage = (int) pageData.get("startPage");
        int endPage = (int) pageData.get("endPage");
        int totalPageCount = (int) pageData.get("totalPageCount");
        int currentPage = (int) pageData.get("currentPage");

        if (startPage > 1) {
    %>
    <a href="<%= request.getContextPath() %>/board/list?page=<%= startPage - 1 %>">&laquo; 이전</a>
    <%
        }
        for (int i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
    %>
    <a href="#" class="active"><%= i %></a>
    <%
    } else {
    %>
    <a href="<%= request.getContextPath() %>/board/list?page=<%= i %>"><%= i %></a>
    <%
            }
        }
        if (endPage < totalPageCount) {
    %>
    <a href="<%= request.getContextPath() %>/board/list?page=<%= endPage + 1 %>">다음 &raquo;</a>
    <%
        }
    %>
</div>
<a href="<%= request.getContextPath() %>/board/write">
    <button type="button">게시글 작성하러 가기</button>
</a>
</body>
</html>
