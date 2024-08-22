package org.example.jspServletBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jspServletBoard.model.BoardDTO;
import org.example.jspServletBoard.model.BoardService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/board.do")
public class BoardServlet extends HttpServlet {
    private final BoardService boardService = BoardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String loginId = (String) req.getSession().getAttribute("loginId");
        boolean isUser = loginId != null;

        try {
            //글 전체 목록 보기
            if (action.equals("list")) {
                List<BoardDTO> boardList = boardService.getBoards();
                req.setAttribute("boardList", boardList);
                req.getRequestDispatcher("WEB-INF/views/list.jsp").forward(req, resp);
            }
            //글 상세 보기
            else if(action.equals("detail")) {
                int bno = Integer.parseInt(req.getParameter("bno"));
                BoardDTO board = boardService.read(bno);
                req.setAttribute("board", board);
                req.getRequestDispatcher("WEB-INF/views/boardDetail.jsp").forward(req,resp);
            }
            //글 작성 페이지로 이동
            else if (action.equals("writeForm")) {
                if (isUser)
                    req.getRequestDispatcher("WEB-INF/views/writeForm.jsp").forward(req, resp);
                else resp.sendRedirect(req.getContextPath() + "/alert.do?status=noAuthorization");
            }
            //글 수정 페이지로 이동
            else if (action.equals("editForm")) {
                int bno = Integer.parseInt(req.getParameter("bno"));
                BoardDTO board = boardService.getBoard(bno);
                req.setAttribute("board", board);

                //권한 체크
                if(isUser) {
                    if (boardService.checkWriter(loginId, bno))
                        req.getRequestDispatcher("WEB-INF/views/editForm.jsp").forward(req, resp);
                    else resp.sendRedirect(req.getContextPath() + "/alert.do?status=noAuthorization");
                }
                else resp.sendRedirect(req.getContextPath() + "/alert.do?status=noAuthorization");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("WEB-INF/views/common/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        String action = req.getParameter("action");

        int result = 0;
        try {
            if (action.equals("write"))
                result = write(req);
            else if (action.equals("edit"))
                result = edit(req);
            else if (action.equals("delete"))
                result = delete(req);

            if (result == 1) resp.sendRedirect(req.getContextPath() + "alert.do?status=success");
            else if(result == -1) resp.sendRedirect(req.getContextPath() + "/alert.do?status=notEmpty");
            else if(result == -2) resp.sendRedirect(req.getContextPath() + "/alert.do?status=noAuthorization");

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/alert.do?status=fail");
        }
    }

    private int write(HttpServletRequest req) throws SQLException {
        String loginId = (String) req.getSession().getAttribute("loginId");

        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String content = req.getParameter("content");

        BoardDTO boardDTO = new BoardDTO(title, content, writer);
        return boardService.write(loginId, boardDTO);
    }

    private int edit(HttpServletRequest req) throws SQLException {
        String loginId = (String) req.getSession().getAttribute("loginId");

        int bno = Integer.parseInt(req.getParameter("bno"));
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String content = req.getParameter("content");

        return boardService.update(loginId, bno, new BoardDTO(title, content, writer));
    }

    private int delete(HttpServletRequest req) throws SQLException {
        String loginId = (String) req.getSession().getAttribute("loginId");
        int bno = Integer.parseInt(req.getParameter("bno"));

        return boardService.delete(loginId, bno);
    }

}
