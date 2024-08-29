<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="org.example.springbootBoard.model.dto.MusicDTO" %><%--
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

        .music-section {
            margin-top: 20px;
            display: flex;
            align-items: center;
            background-color: #ffffff;
            padding: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .music-section img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            margin-right: 20px;
        }

        .music-info {
            display: flex;
            flex-direction: column;
        }

        .music-info a {
            font-size: 18px;
            font-weight: bold;
            color: #4CAF50;
            margin-bottom: 5px;
        }

        .music-info a:hover {
            color: #388E3C;
        }
    </style>
</head>
<%
    MusicDTO music = (MusicDTO) request.getAttribute("music");

%>
<body>
<h1>오늘의 노래</h1>
<table>
    <tr>
        <th>글번호</th>
        <td><%= music.getId() %></td>
    </tr>
    <tr>
        <th>작성자</th>
        <td><%= music.getMember() %></td>
    </tr>
    <tr>
        <th>작성일</th>
        <td><%= music.getCreatedAt() %></td>
    </tr>
</table>

<!-- 오늘의 노래 섹션 -->
<div class="music-section">
    <%
        if (music.getAlbumCover() != null) {
    %>
    <img src = "/upload/<%=music.getAlbumCover()%>" alt="앨범 커버">

    <%
        }
    %>
    <div class="music-info">
        <h3>오늘의 노래</h3>
        <a href="<%= music.getMusicLink() %>"><%= music.getTitle() %></a> <!-- 노래 제목에 링크 추가 -->
        <p><%= music.getArtist() %> / <%= music.getReleaseYear() %></p> <!-- 가수와 발매년도 표시 -->
    </div>
</div>
<div class="music-info"><p><%=music.getDescription()%></p></div>

<div style="margin-top: 20px;">
    <a href="<%= request.getContextPath() %>/music/<%= music.getId() %>/edit">
        <button type="button">수정하기</button>
    </a>
    <form action="<%= request.getContextPath() %>/music/<%= music.getId() %>/delete" method="POST" style="display:inline;">
        <button type="submit">삭제하기</button>
    </form>
</div>
</body>
</html>
