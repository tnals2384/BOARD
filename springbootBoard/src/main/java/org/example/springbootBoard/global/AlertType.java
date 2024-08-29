package org.example.springbootBoard.global;

import jakarta.servlet.http.HttpServletRequest;

public enum AlertType {

    ALREADY_LOGIN("이미 로그인된 회원입니다.", "/"),
    MEMBER_NOT_FOUND("해당하는 회원을 찾을 수 없습니다.", "/member/login"),
    ALREADY_JOIN("이미 회원가입된 회원입니다. 로그인을 해주세요.", "/member/login"),
    NO_SESSION("로그인 후 이용할 수 있습니다.", "/member/login"),
    NO_AUTHORIZATION("권한이 없습니다.", "/board/list"),
    MUSIC_NOT_FOUND("해당하는 음악을 찾을 수 없습니다.", "/board/list"),
    SERVER_ERROR("서버에러입니다. 관리자에게 문의해주세요.", "/"),
    ;

    private final String message;
    private final String path;

    AlertType(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
