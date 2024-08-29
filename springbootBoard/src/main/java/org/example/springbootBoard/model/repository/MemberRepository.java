package org.example.springbootBoard.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.springbootBoard.model.dto.MemberDTO;

import java.sql.SQLException;

@Mapper
public interface MemberRepository {
    String login(MemberDTO member) throws SQLException;
    int join(MemberDTO member) throws SQLException;
}
