package com.jd.iot.admin.vo;

import com.jd.iot.admin.entity.Auth;

public class AuthWithResname {

    private Auth auth;
    private String resname;

    public AuthWithResname() {

    }

    public AuthWithResname(Auth auth, String resname) {
        this.auth = auth;
        this.resname = resname;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

}
