package com.kobe.pojo;

/**
 * 作用：
 * 2020/11/13
 */
public class Curriculum {
    private int id;
    private String name;
    private String week;
    private String classname;
    private String teacher;
    private String timestart;
    private String timeend;
    private String peroid;
    private String issingle;
    private String color;

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", week='" + week + '\'' +
                ", classname='" + classname + '\'' +
                ", teacher='" + teacher + '\'' +
                ", timestart='" + timestart + '\'' +
                ", timeend='" + timeend + '\'' +
                ", period='" + peroid + '\'' +
                ", issingle='" + issingle + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public void setPeriod(String period) {
        this.peroid = period;
    }

    public void setIssingle(String issingle) {
        this.issingle = issingle;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeek() {
        return week;
    }

    public String getClassname() {
        return classname;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTimestart() {
        return timestart;
    }

    public String getTimeend() {
        return timeend;
    }

    public String getPeriod() {
        return peroid;
    }

    public String getIssingle() {
        return issingle;
    }

    public String getColor() {
        return color;
    }
}
