<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="org.example.springbootBoard.model.dto.MusicDTO" %>
<%@ page import="org.example.springbootBoard.model.dto.MusicPageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>노래 추천 리스트</title>
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
        <th>아티스트</th>
        <th>등록일</th>
    </tr>
    </thead>
    <tbody>
    <%
        MusicPageInfo musicPageInfo = (MusicPageInfo) request.getAttribute("musicPageInfo");
        List<MusicDTO> musicList = musicPageInfo.getMusicList();

        if (musicList != null) {
            for (MusicDTO m : musicList) {
    %>
    <tr>
        <td><a href="<%= request.getContextPath() %>/music/<%= m.getId() %>"><%= m.getId() %></a></td>
        <td><a href="<%= request.getContextPath() %>/music/<%= m.getId() %>"><%= m.getTitle() %></a></td>
        <td><%= m.getArtist() %></td>
        <td><%= m.getMember()%></td>
        <td><%= m.getCreatedAt() %></td>

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
        int startPage =  musicPageInfo.getStartPage();
        int endPage = musicPageInfo.getEndPage();
        int totalPageCount = musicPageInfo.getTotalPageCount();
        int currentPage = musicPageInfo.getPage();

        if (startPage > 1) {
    %>
    <a href="<%= request.getContextPath() %>/music/list?page=<%= startPage - 1 %>">&laquo; 이전</a>
    <%
        }
        for (int i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
    %>
    <a href="#" class="active"><%= i %></a>
    <%
    } else {
    %>
    <a href="<%= request.getContextPath() %>/music/list?page=<%= i %>"><%= i %></a>
    <%
            }
        }
        if (endPage < totalPageCount) {
    %>
    <a href="<%= request.getContextPath() %>/music/list?page=<%= endPage + 1 %>">다음 &raquo;</a>
    <%
        }
    %>
</div>
<a href="<%= request.getContextPath() %>/music/write">
    <button type="button">게시글 작성하러 가기</button>
</a>
</body>
</html>
