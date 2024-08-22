package org.example.springBoard.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.springBoard.global.AlertException;
import org.example.springBoard.model.dto.MemberDTO;
import org.example.springBoard.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static org.example.springBoard.global.AlertType.ALREADY_LOGIN;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String getLoginPage(@SessionAttribute(value = "memberId",required = false) String memberId) {
        if (memberId != null)
            throw new AlertException(ALREADY_LOGIN);

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO loginDTO,
                        HttpServletRequest req, HttpServletResponse resp) throws SQLException {

        HttpSession session = req.getSession();
        if (session.getAttribute("memberId") != null)
            throw new AlertException(ALREADY_LOGIN);

        String memberId = memberService.login(loginDTO);

        session.setAttribute("memberId", memberId);
        setIdCookie(resp, loginDTO.getRememberMe(), memberId);
        return "redirect:/main";
    }

    @GetMapping("/join")
    public String getJoinPage(@SessionAttribute(value = "memberId",required = false) String memberId) {

        if (memberId != null)
            throw new AlertException(ALREADY_LOGIN);

        else return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO loginDTO) throws SQLException {

        memberService.join(loginDTO);
        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    private void setIdCookie(HttpServletResponse resp, boolean rememberMe, String memberId) {
        if (rememberMe) {
            Cookie IdCookie = new Cookie("id", memberId);
            resp.addCookie(IdCookie);
        } else { //기억하기 체크되지않으면 만료시킴
            Cookie IdCookie = new Cookie("id", "");
            IdCookie.setMaxAge(0);
            resp.addCookie(IdCookie);
        }
    }

}
