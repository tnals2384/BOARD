<%@ page import="org.example.springBoard.model.dto.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 수정 화면</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }

        form {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        div, input, textarea {
            margin-bottom: 15px;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="text"]:focus, textarea:focus {
            border-color: #4CAF50;
            outline: none;
        }

        input[type="submit"] {
            background-color: #4CAF50; /* Green */
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<%
    BoardDTO board = (BoardDTO) request.getAttribute("board");
%>
<body>
<form action="<%= request.getContextPath() %>/board/<%= board.getNo()%>/edit" method="post">
    <div><strong>글번호:</strong> <%= board.getNo() %></div>
    <input type="text" placeholder="제목을 입력하세요." value="<%= board.getTitle() %>" id="title" name="title" required/><br/>
    <div><strong>작성자:</strong></div>
    <input type="text" name="writer" value="<%=board.getWriter()%>" readonly/><br/>
    <textarea placeholder="내용을 입력하세요." name="content" rows="10" required><%= board.getContent() %></textarea><br/>
    <input type="submit" value="수정완료"/>
</form>
</body>
</html>
