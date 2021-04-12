package com.kobe.vo;

import com.kobe.pojo.Sign;

/**
 * 作用：
 * 2020/11/25
 */
public class SignInfo {
    private int idc;//课程id
    private int idu;//学生id
    private int ids;//签到id
    private String origin;
    public SignInfo(int idc, int idu, int ids) {
        this.idc = idc;
        this.idu = idu;
        this.ids = ids;
    }
    public SignInfo(){}

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "SignInfo{" +
                "idc=" + idc +
                ", idu=" + idu +
                ", ids=" + ids +
                '}';
    }

    public int getIdc() {
        return idc;
    }

    public int getIdu() {
        return idu;
    }

    public int getIds() {
        return ids;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
}
