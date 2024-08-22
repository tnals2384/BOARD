package org.example.springBoard.model.service;

import org.example.springBoard.global.AlertException;
import org.example.springBoard.model.dto.MemberDTO;
import org.example.springBoard.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import static org.example.springBoard.global.AlertType.*;

@Service
public class MemberService {

    public final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String login(MemberDTO memberDTO) throws SQLException {

        String memberId = memberRepository.login(memberDTO);
        if (memberId == null) throw new AlertException(MEMBER_NOT_FOUND);

        return memberRepository.login(memberDTO);
    }

    public void join(MemberDTO memberDTO) throws SQLException {

        if (memberRepository.login(memberDTO) != null)
            throw new AlertException(ALREADY_JOIN);

        if(memberRepository.join(memberDTO)!= 1) throw new AlertException(SERVER_ERROR);
    }
}
