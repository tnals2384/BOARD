package org.example.springBoard.model.repository;

import org.example.springBoard.model.dto.MemberDTO;

import java.sql.SQLException;

public interface MemberRepository {
    String login(MemberDTO member) throws SQLException;
    int join(MemberDTO member) throws SQLException;
}
