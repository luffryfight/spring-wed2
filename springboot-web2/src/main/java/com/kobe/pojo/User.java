package com.kobe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作用：
 * 2020/11/13
 */

public class User {
    private Integer id;
    private String account;
    private String password;
    private String realname;
    private String phone;
    private String img;
    private String picture;
    private String openid;
    private String imei;
    private Integer idc;
    private String isteacher;
    private Integer idm;
    private Integer idsigned;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", img='" + img + '\'' +
                ", picture='" + picture + '\'' +
                ", openid='" + openid + '\'' +
                ", imei='" + imei + '\'' +
                ", idc=" + idc +
                ", isteacher='" + isteacher + '\'' +
                ", idm=" + idm +
                ", idsigned=" + idsigned +
                '}';
    }

    public User() {
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public User(Integer id, String password, String realname,
                String phone, String img, String picture,
                String openid, String imei, Integer idc,
                String isteacher, Integer idm, Integer idsigned,String account) {
        this.id = id;
        this.password = password;
        this.realname = realname;
        this.phone = phone;
        this.img = img;
        this.picture = picture;
        this.openid = openid;
        this.imei = imei;
        this.idc = idc;
        this.isteacher = isteacher;
        this.idm = idm;
        this.idsigned = idsigned;
        this.account=account;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setIdc(Integer idc) {
        this.idc = idc;
    }

    public void setIsteacher(String isteacher) {
        this.isteacher = isteacher;
    }

    public void setIdm(Integer idm) {
        this.idm = idm;
    }

    public void setIdsigned(Integer idsigned) {
        this.idsigned = idsigned;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRealname() {
        return realname;
    }

    public String getPhone() {
        return phone;
    }

    public String getImg() {
        return img;
    }

    public String getPicture() {
        return picture;
    }

    public String getOpenid() {
        return openid;
    }

    public String getImei() {
        return imei;
    }

    public Integer getIdc() {
        return idc;
    }

    public String getIsteacher() {
        return isteacher;
    }

    public Integer getIdm() {
        return idm;
    }

    public Integer getIdsigned() {
        return idsigned;
    }
}
