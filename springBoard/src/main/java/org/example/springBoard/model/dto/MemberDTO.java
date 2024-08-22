package org.example.springBoard.model.dto;

public class MemberDTO {
    private String userid;
    private String userpw;
    private boolean rememberMe;

    public String getUserid() {
        return userid;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }
    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

}
