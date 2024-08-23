package org.example.springMybatisBoard.model.service;

import org.example.springMybatisBoard.global.AlertException;
import org.example.springMybatisBoard.model.dto.BoardDTO;
import org.example.springMybatisBoard.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static org.example.springMybatisBoard.global.AlertType.*;

@Service
public class BoardService {
    private final BoardRepository repo;

    @Autowired
    private BoardService(BoardRepository repo) {
        this.repo = repo;
    }

    public BoardDTO read(int bno) throws SQLException {

        BoardDTO board = repo.selectOne(bno);
        if (board == null) throw new AlertException(BOARD_NOT_FOUND);

        board.setReadCount(board.getReadCount() + 1);
        repo.updateReadCount(bno);

        return board;
    }

    public int write(String memberId, BoardDTO board) throws SQLException {

        if (!memberId.equals(board.getWriter()))
            throw new AlertException(NO_AUTHORIZATION);

        int bno = repo.insert(board);

        if (bno == -1) throw new AlertException(SERVER_ERROR);

        return bno;
    }

    public List<BoardDTO> getBoards() throws SQLException {
        return repo.selectAll();
    }

    public int update(String loginId, int bno, BoardDTO board) throws SQLException {

        if (!checkWriter(loginId, board.getWriter()))
            throw new AlertException(NO_AUTHORIZATION);

        board.setNo(bno);
        int result = repo.update(board);

        if(result == 1) return bno;
        else throw new AlertException(SERVER_ERROR);
    }

    public int delete(String loginId, int bno) throws SQLException {

        BoardDTO board = getBoard(bno);
        if (!checkWriter(loginId, board.getWriter()))
            throw new AlertException(NO_AUTHORIZATION);

        int result = repo.delete(bno);

        if(result == 1) return bno;
        else throw new AlertException(SERVER_ERROR);
    }

    public BoardDTO getBoard(int bno) throws SQLException {

        BoardDTO board = repo.selectOne(bno);
        if (board == null) throw new AlertException(BOARD_NOT_FOUND);

        return board;
    }

    public boolean checkWriter(String loginId, String writer) {
        return loginId.equals(writer);
    }
}
