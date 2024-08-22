package org.example.jspServletBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    private static final String USERID = "sumin";
    private static final String USERPW = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("login")) {
            HttpSession session = req.getSession();
            String loginId = (String) session.getAttribute("loginId");

            if (loginId == null) {
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "이미 로그인 된 회원입니다.");
                req.setAttribute("path", req.getContextPath() + "/main.do");
                req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
            }
        } else if (action.equals("logout")) {
            HttpSession session = req.getSession();
            session.invalidate();

            req.setAttribute("msg", "로그아웃 되었습니다.");
            req.setAttribute("path", req.getContextPath() + "/main.do");
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        if (userId.equals(USERID) && userPw.equals(USERPW)) {
            HttpSession session = req.getSession();
            session.setAttribute("loginId", userId);

            String rememberMe = req.getParameter("rememberMe");

            if (rememberMe == null) { //기억하기 체크 되어있지 않으면 만료시킴
               Cookie IdCookie = new Cookie("id","");
               IdCookie.setMaxAge(0);
               resp.addCookie(IdCookie);
            }
            else {
                Cookie IdCookie = new Cookie("id", userId);
                resp.addCookie(IdCookie);
            }

            req.setAttribute("msg", "로그인 완료 되었습니다.");
            req.setAttribute("path", req.getContextPath() + "/main.do");
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
        } else {

            req.setAttribute("msg", "로그인 실패하였습니다. 아이디나 패스워드를 확인하세요..");
            req.setAttribute("path", req.getContextPath() + "/user.do?action=login");
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
        }
    }
}
