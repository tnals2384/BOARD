package org.example.jspServletBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/alert.do")
public class AlertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        String message;
        String path;

        switch (status) {
            case "success":
                message = "요청에 성공하였습니다.";
                path = req.getContextPath() + "/board.do?action=list";
                break;
            case "fail":
                message = "내부 서버 오류가 발생하였습니다.";
                path = req.getContextPath() + "/main.do";
                break;
            case "noAuthorization":
                message = "권한이 없습니다.";
                path = req.getContextPath() + "/user.do?action=login";
                break;
            case "notEmpty":
                message = "title, content는 비워둘 수 없습니다.";
                path = req.getContextPath() + "/board.do?action=list";
                break;
            default:
                message = "알 수 없는 상태입니다.";
                path = req.getContextPath() + "/main.do";
                break;
        }

        req.setAttribute("msg", message);
        req.setAttribute("path", path);
        req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
    }
}
