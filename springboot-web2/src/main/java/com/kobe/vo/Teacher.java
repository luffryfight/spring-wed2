package com.kobe.vo;

/**
 * 作用：
 * 2020/11/23
 */
public class Teacher {
    private String isteacher;//是否是老师
    private String password;//老师的密码
    private String realname;//老师的名字
    private String img;//用户头像
    private int id;//用户的id(因为登录的时候都会查询到一个用户的信息)
    private int idc;//课程得id,学生点击签到时需要id

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "isteacher='" + isteacher + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", id=" + id +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIsteacher() {
        return isteacher;
    }

    public String getPassword() {
        return password;
    }

    public String getRealname() {
        return realname;
    }

    public void setIsteacher(String isteacher) {
        this.isteacher = isteacher;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
