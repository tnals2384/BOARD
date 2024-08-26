package org.example.springMybatisBoard.model.service;

import org.example.springMybatisBoard.global.AlertException;
import org.example.springMybatisBoard.model.dto.BoardDTO;
import org.example.springMybatisBoard.model.dto.FileDTO;
import org.example.springMybatisBoard.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.springMybatisBoard.global.AlertType.*;

@Service
public class BoardService {
    private final BoardRepository repo;
    private final FileService fileService;
    private static final int COUNT_PER_PAGE = 10;

    @Autowired
    public BoardService(BoardRepository repo, FileService fileService) {
        this.repo = repo;
        this.fileService = fileService;
    }


    public BoardDTO read(int bno) throws SQLException {

        BoardDTO board = repo.selectOne(bno);
        if (board == null) throw new AlertException(BOARD_NOT_FOUND);

        board.setReadCount(board.getReadCount() + 1);
        repo.updateReadCount(bno);

        return board;
    }

    public int write(String memberId, BoardDTO board, MultipartFile[] uploadFile) throws SQLException, IOException {

        if (!memberId.equals(board.getWriter()))
            throw new AlertException(NO_AUTHORIZATION);

        int bno = repo.insert(board);

        if (bno == -1) throw new AlertException(SERVER_ERROR);

        fileService.saveFiles(bno, uploadFile);

        return board.getNo();
    }

    //현재 보고자 하는 페이지 정보가 들어왔을 때, 실제 해당 페이지에 보여져야 하는 List<Board>를 포함해서
    //페이지가 총 몇개 필요하고, 하단 페이지 링크는 1~10 or 11~20 같은 페이지 구간 계산
    public Map<String, Object> getBoards(int page) throws SQLException {
        int totalCount = repo.selectCount();
        int totalPageCount = totalCount / COUNT_PER_PAGE;
        if (totalPageCount % COUNT_PER_PAGE > 0) {
            totalPageCount++;
        }

        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        if (totalPageCount < endPage)
            endPage = totalPageCount;

        int startRow = (page - 1) * COUNT_PER_PAGE;
        List<BoardDTO> boardList = repo.selectList(startRow, COUNT_PER_PAGE);

        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", page);
        map.put("totalPageCount", totalPageCount);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        map.put("boardList", boardList);

        return map;
        //return repo.selectAll();
    }

    public int update(String loginId, int bno, BoardDTO board) throws SQLException {

        if (!checkWriter(loginId, board.getWriter()))
            throw new AlertException(NO_AUTHORIZATION);

        board.setNo(bno);
        int result = repo.update(board);

        if (result == 1) return bno;
        else throw new AlertException(SERVER_ERROR);
    }

    public int delete(String loginId, int bno) throws SQLException {

        BoardDTO board = getBoard(bno);
        if (!checkWriter(loginId, board.getWriter()))
            throw new AlertException(NO_AUTHORIZATION);

        int result = repo.delete(bno);

        if (result == 1) return bno;
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
