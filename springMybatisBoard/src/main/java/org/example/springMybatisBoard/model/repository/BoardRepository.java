package org.example.springMybatisBoard.model.repository;

import org.example.springMybatisBoard.model.dto.BoardDTO;

import java.sql.SQLException;
import java.util.List;

public interface BoardRepository {
    int insert(BoardDTO board) throws SQLException;
    int update(BoardDTO board) throws SQLException;
    int delete(int bno)throws SQLException;
    List<BoardDTO> selectAll()throws SQLException;
    BoardDTO selectOne(int id)throws SQLException;
    int updateReadCount(int no) throws SQLException;
}
