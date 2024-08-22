package org.example.jspServletBoard.model;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
    private BoardRepository repo = BoardRepositoryMysql.getInstance();

    private BoardService() {
    }

    private static BoardService instance = new BoardService();

    public static BoardService getInstance() {
        return instance;
    }


    public BoardDTO read(int bno) throws SQLException {
        BoardDTO board = repo.selectOne(bno);
        board.setReadCount(board.getReadCount() + 1);
        repo.updateReadCount(bno);
        return board;
    }

    public BoardDTO getBoard(int bno) throws SQLException {
        return repo.selectOne(bno);
    }

    public int write(String loginId, BoardDTO board) throws SQLException {

        if (board.getTitle() == null || board.getTitle().isBlank() ||
                board.getWriter() == null || board.getWriter().isBlank()
                || board.getContent() == null || board.getContent().isBlank())
            return -1;

        if (loginId == null)
            return -2;

        return repo.insert(board);
    }

    public List<BoardDTO> getBoards() throws SQLException {
        return repo.selectAll();
    }

    public int update(String loginId, int bno, BoardDTO board) throws SQLException {
        if (board.getTitle() == null || board.getTitle().isBlank()
                || board.getContent() == null || board.getContent().isBlank())
            return -1;

        if (loginId == null || !checkWriter(loginId, bno)) {
            return -2;
        }

        board.setNo(bno);
        return repo.update(board);
    }

    public int delete(String loginId, int bno) throws SQLException {
        if (loginId == null || !checkWriter(loginId, bno)) {
            return -2;
        }

        return repo.delete(bno);
    }

    public boolean checkWriter(String loginId, int bno) throws SQLException {
        BoardDTO boardDTO = repo.selectOne(bno);
        return loginId.equals(boardDTO.getWriter());
    }
}
