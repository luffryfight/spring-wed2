package com.kobe.pojo;

/**
 * 作用：
 * 2020/12/27
 */
public class Absence {
    private int id;
    private int idu;//请假人的id
    private String nameText;//姓名
    private int idi;//通知记录的id
    private String type1;//请假类型
    private String type2;//是否离校
    private String startTimeText ;//开始时间
    private String endTimeText ;//结束时间
    private String placeText ;//销假地点
    private String myPhoneText ;//联系方式
    private String otherPhoneText ;//紧急联系人
    private String leaveSituationText ;//请假情况说明
    private String img;//添加照片

    public void setId(int id) {
        this.id = id;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public int getIdi() {
        return idi;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public void setStartTimeText(String startTimeText) {
        this.startTimeText = startTimeText;
    }

    public void setEndTimeText(String endTimeText) {
        this.endTimeText = endTimeText;
    }

    public void setPlaceText(String placeText) {
        this.placeText = placeText;
    }

    public void setMyPhoneText(String myPhoneText) {
        this.myPhoneText = myPhoneText;
    }

    public void setOtherPhoneText(String otherPhoneText) {
        this.otherPhoneText = otherPhoneText;
    }

    public void setLeaveSituationText(String leaveSituationText) {
        this.leaveSituationText = leaveSituationText;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public int getIdu() {
        return idu;
    }

    public String getNameText() {
        return nameText;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public String getStartTimeText() {
        return startTimeText;
    }

    public String getEndTimeText() {
        return endTimeText;
    }

    public String getPlaceText() {
        return placeText;
    }

    public String getMyPhoneText() {
        return myPhoneText;
    }

    public String getOtherPhoneText() {
        return otherPhoneText;
    }

    public String getLeaveSituationText() {
        return leaveSituationText;
    }

    public String getImg() {
        return img;
    }
}
