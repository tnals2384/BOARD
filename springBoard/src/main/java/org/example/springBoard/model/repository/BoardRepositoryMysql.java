package org.example.springBoard.model.repository;


import org.example.springBoard.model.dto.BoardDTO;
import org.example.springBoard.model.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardRepositoryMysql implements BoardRepository {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int insert(BoardDTO board) throws SQLException {
        int result = -1;
        try {
            String sql = " INSERT INTO " +
                    " BOARD_TB(TITLE,WRITER,CONTENT,REG_DATE) VALUES(?,?,?,NOW()) ";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, board.getTitle());
            ps.setString(2, board.getWriter());
            ps.setString(3, board.getContent());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1); // 키 가져오기
            }
        } catch (SQLException ex) {
            System.out.println("insert error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return result;
    }

    @Override
    public int update(BoardDTO board) throws SQLException {
        int result = 0;

        try {
            String sql = " UPDATE BOARD_TB SET ";

            if (board.getTitle() != null && board.getTitle().length() > 0) { // 제목에 변경하고자 값이 확실히 있는지 체크해서
                sql += " TITLE='" + board.getTitle() + "', ";
            }
            if (board.getContent() != null && board.getContent().length() > 0) {
                sql += " CONTENT='" + board.getContent() + "', ";
            }
            sql = sql.substring(0, sql.length() - 2);

            sql += " WHERE NO = " + board.getNo();
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("update error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }

        return result;
    }

    @Override
    public int delete(int bno) throws SQLException {
        int result = 0;
        try {
            String sql = "DELETE FROM BOARD_TB WHERE NO = " + bno;
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public List<BoardDTO> selectAll() throws SQLException {
        List<BoardDTO> list = new ArrayList<BoardDTO>();
        try {
            String sql = " SELECT NO,TITLE,WRITER,CONTENT,READ_COUNT,REG_DATE FROM BOARD_TB ";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(makeBoardDTO(rs));
            }
        } catch (SQLException ex) {
            System.out.println("update error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return list;
    }

    @Override
    public BoardDTO selectOne(int no) throws SQLException {
        BoardDTO board = null;

        try {
            String sql = " SELECT NO,TITLE,WRITER,CONTENT,READ_COUNT,REG_DATE FROM BOARD_TB WHERE NO=" + no;
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) { // 글번호 이상하면 없을수는 있음.
                board = makeBoardDTO(rs);
            }
        } catch (SQLException ex) {
            System.out.println("update error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return board;
    }

    @Override
    public int updateReadCount(int no) throws SQLException {
        int result = 0;

        try {
            String sql = " UPDATE BOARD_TB SET READ_COUNT = READ_COUNT + 1 WHERE NO=" + no;
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("update error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return result;
    }

    private BoardDTO makeBoardDTO(ResultSet rs) throws SQLException {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setNo(rs.getInt("NO"));
        boardDTO.setTitle(rs.getString("TITLE"));
        boardDTO.setWriter(rs.getString("WRITER"));
        boardDTO.setContent(rs.getString("CONTENT"));
        boardDTO.setReadCount(rs.getInt("READ_COUNT"));
        boardDTO.setRegDate(rs.getString("REG_DATE"));
        return boardDTO;
    }
}
