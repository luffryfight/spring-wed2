package com.kobe.pojo;

/**
 * 作用：
 * 2020/11/13
 */
public class Middle {
    private int id;
    private int idu;
    private int idc;
    private int ids;

    @Override
    public String toString() {
        return "Middle{" +
                "id=" + id +
                ", idu=" + idu +
                ", idc=" + idc +
                ", ids=" + ids +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getIdu() {
        return idu;
    }

    public int getIdc() {
        return idc;
    }

    public int getIds() {
        return ids;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
}
