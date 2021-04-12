package com.kobe.common;

/**
 * 作用： 签到标签
 * 2020/12/25
 */
public class SignLable {
    //签到的标签
    public static final int SNORMAL = 1;          //普通签到
    //:密码格式：password:2222
    public static final int FACERECOGNITION = 2;    //人脸识别,活体检测

    public static final int STOOKPHOTO = 3;         //拍照签到

    public static final int SBLUETOOTH = 4;         //蓝牙签到

    public static final int SLOCATION = 5;          //定位签到

    public static final int SFACELOCATION = 6;      //人脸识别,活体检测+定位 (默认)

    public static final int SBLUETOOTHLOCATION = 7; //蓝牙,人脸识别,活体检测
}
