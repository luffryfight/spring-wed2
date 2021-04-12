package com.kobe.service.Impl;

import com.kobe.common.ServerResponse;
import com.kobe.common.SignLable;
import com.kobe.common.SignState;
import com.kobe.dao.*;
import com.kobe.pojo.Curriculum;
import com.kobe.pojo.Sign;
import com.kobe.service.SignService;
import com.kobe.util.LocationUtil;
import com.kobe.vo.InfoHistory;
import com.kobe.vo.SignInfo;
import com.kobe.vo.Teacher;
import com.kobe.vo.TeacherToStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 作用：
 * 2020/11/25
 */
@Service
public class SignServiceImpl implements SignService {
    @Autowired
    SignMapper signMapper;
    @Autowired
    private InfoHistoryMapper historyMapper;
    @Autowired
    private UserListMapper userListMapper;
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private StuMiddleMapper middleMapper;
    @Override
    public void addSign(String origin) {
        signMapper.addSign(origin);
    }

    @Override
    public ServerResponse selectSignWay(InfoHistory history, Teacher user) {
        System.out.println("签到说明:"+history.getTitle());

        //给每个学生默认没签到，对于没点签到的学生好处理一点
        List<SignInfo> signInfo = signMapper.getSignInfo(history.getIdc());
        int size=signInfo.size();
        for (int i = 0; i < size; i++) {
            SignInfo info=signInfo.get(i);
            StringBuilder origin=new StringBuilder(info.getOrigin());
            if (origin.indexOf("&")!=-1){//不是第一次签到
                //更新
                String[] str=origin.toString().split("&");
                String last=str[str.length-1];
                int len=last.length();
                int classth=Integer.parseInt(last.substring(0,len-1));//默认没签到
                if(info.getIds()!=history.getIdt()) {//不是老师
                    origin.append((classth + 1) + "").append(SignState.SUNFINISH ).append('&');
                }else{
                    origin.append((classth + 1) + "").append(SignState.SFINISH ).append('&');
                }
            }else{//第一次签到，默认设置没签到
                origin.delete(0,origin.length());
                origin.append("13&");
            }
            //赋值课程打卡
            signMapper.updateSignFromTeacher(info.getIds(),origin.toString());
            //设置密码
            if(info.getIds()!=history.getIdt()) {//不是老师
                ///这里面设置不同的签到方法。
                StringBuilder sb=new StringBuilder();
                if(history.getLable()== SignLable.SNORMAL){//普通签到：
                    sb.append("password" +
                            ":"+history.getLat());
                }else if(history.getLable()==SignLable.SLOCATION){//定位签到
                    sb.append("location" +
                            ":"+history.getLat()+","+history.getIng());
                }
                signMapper.updateSignKey(history.getIdc(),sb.toString());//(7:33.12312,123123)

            }

        }
        //创建历史记录
        history.setIdt(user.getId());
        history.setImg(user.getImg());
        history.setState(SignState.SUNFINISH);
        historyMapper.createInfoHistory(history);
        int id_history=historyMapper.getLastNewId();
        if (history.getId()==1){
            return ServerResponse.createByError("发布失败！");
        }
        //给每个学生用户的idi字段赋值，多对多，默认缺勤
        List<TeacherToStudent> students=userListMapper.getStudents(history.getIdt());
        for(TeacherToStudent ch:students){
            middleMapper.insert(id_history,ch.getIds(),SignState.SUNFINISH);
        }
        return ServerResponse.createBySuccessMsg("发布成功！");
    }



    @Override
    public ServerResponse signWay_password(int idi,String password,int idu,String time) {
        //先是否存在idi，或者idi是否匹配
        InfoHistory history = historyMapper.selectInfoById(idi);
        if(history==null){
            return ServerResponse.createByError("不存在！");
        }
        //获取密钥,然后对比
        Sign sign = signMapper.getSigned(history.getIdc(), idu);
        String temp = sign.getSigned();
        if(temp.equals("-1-1-1-1")) {
            return ServerResponse.createByError("你已签到，不能重复签到！");
        }
        String signed = temp.substring(9);//因为前面有password
        int state=0;
        if (!signed.isEmpty()&&password.equals(signed)) {//签到密码正确
            StringBuilder origin = new StringBuilder(sign.getOrigin());
            //更新
            String last = origin.substring(origin.length() - 2);
            if (!last.equals("3&")){
                return ServerResponse.createByError("你已签到，不能重复签到！");
            }
            state = compareTime(history, time, idu);
            origin.deleteCharAt(origin.length() - 1);//删除0
            origin.deleteCharAt(origin.length() - 1);//删除1
            origin.append(state+"&");
            //赋值 origin
            signMapper.updateSignFromTeacher(sign.getId(), origin.toString());
            //初始化密码
            signMapper.updateSignedForOne(sign.getId(), "-1-1-1-1");//这里应该随机一个UID
        }else {
            return ServerResponse.createByError("签到密钥错误，请重新输入！");
        }
        return ServerResponse.createBySuccess(state);
    }

    @Override
    public ServerResponse signWay_location(String time, int idi, String Lat,
                                           String Ing,int idu) {
        //先是否存在idi，或者idi是否匹配
        InfoHistory history = historyMapper.selectInfoById(idi);
        if(history==null){
            return ServerResponse.createByError("不存在！");
        }
        //获取密钥,然后对比
        Sign sign = signMapper.getSigned(history.getIdc(), idu);
        String temp = sign.getSigned();
        System.out.println(temp);
        if(temp.equals("-1-1-1-1")) {
            return ServerResponse.createByError("你已签到，不能重复签到！");
        }
        int state=0;
        double lat1=Double.parseDouble(Lat);
        double ing1=Double.parseDouble(Ing);
        double lat2=Double.parseDouble(history.getLat());
        double ing2=Double.parseDouble(history.getIng());
        double distence= LocationUtil.getDistance(lat1,ing1, lat2,ing2);
        if(distence<50){//在签到的范围内
            StringBuilder origin = new StringBuilder(sign.getOrigin());
            //更新
            String last = origin.substring(origin.length() - 2);
            if (!last.equals("3&")){
                return ServerResponse.createByError("你已签到，不能重复签到！");
            }
            state = compareTime(history, time, idu);
            origin.deleteCharAt(origin.length() - 1);//删除0
            origin.deleteCharAt(origin.length() - 1);//删除1
            origin.append(state+"&");
            //赋值 origin
            signMapper.updateSignFromTeacher(sign.getId(), origin.toString());
            //初始化密码
            signMapper.updateSignedForOne(sign.getId(), "-1-1-1-1");//这里应该随机一个UID

        }else{
            return ServerResponse.createByError("距离过远");
        }
        return ServerResponse.createByError();

    }

    @Override
    public ServerResponse getAllSigns(int idu) {
        List<Map> res=signMapper.getAllSigns(idu);
        if (res==null||res.size()==0){
            return ServerResponse.createByError("查询结果为空！");
        }
        return ServerResponse.createBySuccess(res);
    }



    @Override
    public ServerResponse selectInfo(int ids) {
        List<Map> histories = historyMapper.selectByStu(ids);
        if (histories==null||histories.size()==0){
            return ServerResponse.createByError("记录为空！");
        }
        return ServerResponse.createBySuccess(histories);
    }

    /**
     * 对比签到时间
     * */
    public int  compareTime(InfoHistory history,String finishTime,int idu) {
        Curriculum curriculum=curriculumMapper.curriculumInfo(history.getIdc());
        String[] start=curriculum.getTimestart().split(":");
        String[] end=curriculum.getTimeend().split(":");
        String[] times = finishTime.split(":");
        int state=0;
        //需要查询是否请假了
        if (history.getState()==SignState.SLEAVE){
            return state;
        }

        if (Integer.parseInt(times[0])<Integer.parseInt(start[0])||(
                times[0].equals(start[0])&&Integer.parseInt(times[1])<Integer.parseInt(start[1]))){
            state=SignState.SFINISH;//签到
        }else if (Integer.parseInt(times[0])<=Integer.parseInt(end[0])&&
                Integer.parseInt(times[1])<Integer.parseInt(end[1])){
            state=SignState.SOUTFINISH;//迟到
        }else {
            state=SignState.SUNFINISH;//签到未成功！
        }

        return state;
    }

    @Override
    public ServerResponse getStuHistory(int idu) {
        List<InfoHistory> stuHistory = middleMapper.getStuHistory(idu);
        if (stuHistory==null||stuHistory.size()==0){
            return ServerResponse.createByError("记录为空");
        }
        List<Map> res=new LinkedList<>();
        for(InfoHistory ch: stuHistory){
            Map map=new HashMap();
            map.put("Info",ch);
            if (ch.getState()==SignState.SLEAVE){

            }
        }
        return null;
    }

    @Override
    public ServerResponse selectInfohistory(int idu) {
        List<Map> infoHistories = historyMapper.selectInfohistory(idu);
        if(infoHistories.size()==0||infoHistories==null){
            return ServerResponse.createByError("记录为空！");
        }
        return ServerResponse.createBySuccess(infoHistories);
    }


    //通过经纬度比较两个地点
    public void signByLocation(int idi,String password,int idu,String time){

    }
    //main方法测试，比较时间的方法对不对
    public static void main(String[] args) {

        SignServiceImpl signService=new SignServiceImpl();
    }

}
