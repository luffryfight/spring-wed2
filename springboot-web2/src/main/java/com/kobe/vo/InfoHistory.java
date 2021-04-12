package com.kobe.vo;

/**
 * 作用：签到通知（历史记录）
 * 2020/12/24
 */

public class InfoHistory {
    private int id;              //主键id
    private int idt;             //老师 的id
    private String Theme;       //签到主题
    private String Title;       //签到说明
    private int Lable;       //签到类型
    private int idc;   		//课程的id
    private String StartTime;   //开始时间
    private String EndTime;     //结束时间
    private String Lat;         //经度
    private String Ing;         //纬度 (签到的中心点)
    private int State;          //签到状态
    private String Name;        //老师的名字
    private String img;          //老师的图像
    private String FinishTime;  //（如果完成签到）完成签到的时间

    public int getId() {
        return id;
    }

    public int getIdt() {
        return idt;
    }

    public String getTheme() {
        return Theme;
    }

    public String getTitle() {
        return Title;
    }


    public int getIdc() {
        return idc;
    }

    public int getLable() {
        return Lable;
    }

    public void setLable(int lable) {
        Lable = lable;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public String getLat() {
        return Lat;
    }

    public String getIng() {
        return Ing;
    }

    public int getState() {
        return State;
    }

    public String getName() {
        return Name;
    }

    public String getImg() {
        return img;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public void setIng(String ing) {
        Ing = ing;
    }

    public void setState(int state) {
        State = state;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }
}
