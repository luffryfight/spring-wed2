package com.kobe.pojo;

/**
 * 作用：
 * 2020/11/13
 */
public class Sign {
    private int id;
    private String origin;
    private String signed;

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", signed=" + signed +
                '}';
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public String getSigned() {
        return signed;
    }

    public int getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
