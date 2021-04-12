package com.kobe.vo;

/**
 * 作用：
 * 2020/11/23
 */
public class TeacherToStudent {
    private int ids; //学生id
    private String realname;//学生名字
    private String name;//课程名字
    private int idc;//课程id ->stulist
    private String teacher;//老师名字
    private String origin;//签到信息
    private String account;//签到用来更新签到信息的
    private String week;//上课日
    private String classname;//上课教室
    private String timestart;//上课时间

    @Override
    public String toString() {
        return "TeacherToStudent{" +
                "realname='" + realname + '\'' +
                ", name='" + name + '\'' +
                ", idc=" + idc +
                ", teacher='" + teacher + '\'' +
                ", origin='" + origin + '\'' +
                ", account='" + account + '\'' +
                ", week='" + week + '\'' +
                ", classname='" + classname + '\'' +
                ", timestart='" + timestart + '\'' +
                '}';
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getIds() {
        return ids;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdc() {
        return idc;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getClassname() {

        return classname;
    }

    public String getTimestart() {
        return timestart;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getOrigin() {
        return origin;
    }

    public String getAccount() {
        return account;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setSignInfo(String signInfo) {
        this.origin = signInfo;
    }

    public String getSignInfo() {
        return origin;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRealname() {
        return realname;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }
}
