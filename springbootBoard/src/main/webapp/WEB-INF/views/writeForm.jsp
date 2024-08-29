<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 쓰기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"], input[type="file"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            height: 150px;
        }

        button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 12px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 4px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>오늘의 노래</h1>
<form action="<%= request.getContextPath() %>/music/write" method="post" enctype="multipart/form-data">

    <!-- 오늘의 노래 정보 입력 -->
    <h2>오늘의 노래</h2>

    <label for="title">노래 제목:</label>
    <input type="text" id="title" name="title" required>

    <label for="artist">가수:</label>
    <input type="text" id="artist" name="artist" required>

    <label for="releaseYear">발매년도:</label>
    <input type="text" id="releaseYear" name="releaseYear" required>

    <label for="musicLink">노래 링크 (URL):</label>
    <input type="text" id="musicLink" name="musicLink" required>

    <!-- 앨범 커버 업로드 -->
    <label for="file">앨범 커버:</label>
    <input type="file" id="file" name="file" accept="image/*">


    <label for="description">오늘의 노래를 선택한 이유를 적어주세요!</label>
    <textarea id="description" name="description" required></textarea>

    <!-- 제출 버튼 -->
    <button type="submit">글 등록</button>
</form>
</body>
</html>
