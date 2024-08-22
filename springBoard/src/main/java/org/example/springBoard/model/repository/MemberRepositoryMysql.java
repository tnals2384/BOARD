package org.example.springBoard.model.repository;

import org.example.springBoard.model.dto.MemberDTO;
import org.example.springBoard.model.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberRepositoryMysql implements MemberRepository {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public String login(MemberDTO memberDTO) throws SQLException {
        String memberId = null;
        String sql = "SELECT * FROM MEMBER_TB WHERE USERID = ? AND USERPW = ?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDTO.getUserid());
            ps.setString(2, memberDTO.getUserpw());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                memberId = rs.getString("USERID");
            }
        } catch (SQLException e) {
            System.out.println("login Error");
            throw e;
        }
        finally {
            DBUtil.close(rs,ps,conn);
        }
        return memberId;
    }


    @Override
    public int join(MemberDTO member) throws SQLException {
        int result;
        String sql = "INSERT INTO MEMBER_TB VALUES(?,?)";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,member.getUserid());
            ps.setString(2,member.getUserpw());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("join Error");
            throw e;
        }
        finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

}
