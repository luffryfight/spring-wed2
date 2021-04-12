package com.kobe.vo;

/**
 * 作用：
 * 2020/11/26
 */
public class SignTeacher {
    private int idc;//课程id
    private String signed;//签到密码
    private String way;//签到方式

    @Override
    public String toString() {
        return "SignTeacher{" +
                "idc=" + idc +
                ", sign='" + signed + '\'' +
                ", way='" + way + '\'' +
                '}';
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public String getSigned() {
        return signed;
    }

    public int getIdc() {
        return idc;
    }

    public String getSign() {
        return signed;
    }

    public String getWay() {
        return way;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setSign(String sign) {
        this.signed = sign;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
