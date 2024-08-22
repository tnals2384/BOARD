<%@ page import="org.example.springBoard.model.dto.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 상세 보기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        td, th {
            padding: 12px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 15px;
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
        .content {
            white-space: pre-wrap; /* Preserve whitespace and line breaks */
        }
    </style>
</head>
    <%
        BoardDTO board = (BoardDTO) request.getAttribute("board");
    %>
<body>
<h1>글 상세 보기</h1>
<table>
    <tr>
        <th>글번호</th>
        <td><%= board.getNo() %></td>
    </tr>
    <tr>
        <th>제목</th>
        <td><%= board.getTitle() %></td>
    </tr>
    <tr>
        <th>작성자</th>
        <td><%= board.getWriter() %></td>
    </tr>
    <tr>
        <th>작성일</th>
        <td><%= board.getRegDate() %></td>
    </tr>
    <tr>
        <th>조회수</th>
        <td><%= board.getReadCount() %></td>
    </tr>
    <tr>
        <th>내용</th>
        <td class="content"><%= board.getContent() %></td>
    </tr>
</table>
<div style="margin-top: 20px;">
    <a href="<%= request.getContextPath() %>/board/<%=board.getNo()%>/edit">
        <button type="button">수정하기</button>
    </a>
    <form action="<%= request.getContextPath() %>/board/<%= board.getNo()%>/delete" method="POST" style="display:inline;">
        <button type="submit">삭제하기</button>
    </form>
</div>
</body>
</html>