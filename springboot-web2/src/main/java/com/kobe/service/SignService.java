package com.kobe.service;

import com.kobe.common.ServerResponse;
import com.kobe.vo.InfoHistory;
import com.kobe.vo.Teacher;

/**
 * 作用：
 * 2020/11/25
 */
public interface SignService {
    void addSign(String origin);



    ServerResponse selectSignWay(InfoHistory history, Teacher teacher);

    /*ServerResponse getStuSign(int idu);

    ServerResponse getTeaSign(int idu);*/

    ServerResponse getAllSigns(int idu);

    ServerResponse selectInfo(int ids);

    //比较时间
    // compareTime(String finishTime,int idu);

    ServerResponse getStuHistory(int idu);

    ServerResponse selectInfohistory(int idu);

    ServerResponse signWay_password(int idi,String password,int idu,String time);

    ServerResponse signWay_location(String time,int idi, String Lat,String Ing,int idu);
}
