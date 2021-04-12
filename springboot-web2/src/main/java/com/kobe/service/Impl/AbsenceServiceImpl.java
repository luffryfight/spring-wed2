package com.kobe.service.Impl;

import com.kobe.common.ServerResponse;
import com.kobe.common.SignState;
import com.kobe.dao.AbsenceMapper;
import com.kobe.dao.InfoHistoryMapper;
import com.kobe.dao.SignMapper;
import com.kobe.dao.StuMiddleMapper;
import com.kobe.pojo.Absence;
import com.kobe.pojo.Sign;
import com.kobe.service.AbsenceService;
import com.kobe.vo.InfoHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * to do :
 *
 * @author 86137
 * @date 2021/3/7 19:39
 */
@Service
public class AbsenceServiceImpl  implements AbsenceService {
    @Autowired
    private AbsenceMapper absenceMapper;
    @Autowired
    private StuMiddleMapper stumiddleMapper;
    @Autowired
    private InfoHistoryMapper historyMapper;

    @Autowired
    private SignMapper signMapper;

    @Override
    public ServerResponse insertOne(Absence absence) {
        absenceMapper.insertOne(absence);
        return ServerResponse.createBySuccessMsg("消息发送成功！");
    }


    @Override
    public ServerResponse getAllAbsences(int idu,String isTeacher) {
        List<Absence> absences=new ArrayList<>();
        //学生和老师查询的sql不同
        if(isTeacher.equals("yes")){
            absences=absenceMapper.getAllAbsencesByTeacher(idu);
        }else{
         absences= absenceMapper.getAllAbsences(idu);
        }
        if(absences==null||absences.size()==0){
            return ServerResponse.createByError("无请假记录");
        }
        return ServerResponse.createBySuccess(absences);
    }

    @Override
    public ServerResponse updateAbsenceState(int id, int pass,String messages) {
        //还要在其他地方更新签到：中间表的状态;签到表中也要更新；
        System.out.println(id+" :"+pass+" :"+messages);
        Absence absence = absenceMapper.selectById(id);
        InfoHistory history = historyMapper.selectInfoById(absence.getIdi());
        Sign sign = signMapper.getSigned(history.getIdc(), absence.getIdu());
        if (absence==null){
            return ServerResponse.createByError("无法查询到信息！");
        }
        if(pass==1){//同意请假：在中间的状态赋值为请假
            stumiddleMapper.updateState(absence.getIdu(),absence.getIdi(), SignState.SLEAVE);

            StringBuilder origin=new StringBuilder(sign.getOrigin());
            origin.deleteCharAt(origin.length() - 1);//删除&
            origin.deleteCharAt(origin.length() - 1);//删除1
            origin.append(SignState.SLEAVE).append("&");
            signMapper.updateSignFromTeacher(sign.getId(),origin.toString());
            //初始化密码
            signMapper.updateSignedForOne(sign.getId(), "-1-1-1-1");
        }
        absenceMapper.updateAbsenceState(id,pass,messages);
        return ServerResponse.createBySuccessMsg("教师已审批");
    }
}
