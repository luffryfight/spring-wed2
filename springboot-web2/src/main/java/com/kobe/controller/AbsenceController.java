package com.kobe.controller;

import com.kobe.common.ServerResponse;
import com.kobe.dao.AbsenceMapper;
import com.kobe.pojo.Absence;
import com.kobe.service.AbsenceService;
import com.kobe.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 作用： 请假
 * 没写Impl
 * 2020/12/27
 */
@Controller
public class AbsenceController {
    @Autowired
    private AbsenceMapper absenceMapper;

    @Autowired
    private AbsenceService absenceService;



    /*@RequestMapping(value = "/signFor.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signFor(Absence absence,HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("Teacher");
        absenceMapper.insert(absence);
        return ServerResponse.createBySuccess("申请成功");
    }*/

    /**
     * 学生发起请假
     * */
    @RequestMapping(value = "/insert.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse insert(Absence absence,HttpSession session){
        Teacher teacher = (Teacher)session.getAttribute("Teacher");
        //设置一些必须的东西
        absence.setIdu(teacher.getId());
        absence.setNameText(teacher.getRealname());
        //还需要推送消息给教师端。或者老师查看所有，然后选择某一条。
        if(absence==null){
            return ServerResponse.createByError("消息无效！");
        }
        return absenceService.insertOne(absence);
    }
    /**
     * 用户查看自己的请假记录
     * */
    @RequestMapping(value = "/getAllAbsences.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getAllAbsences(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("Teacher");
        int idu=teacher.getId();

        if (idu==0){
            return ServerResponse.createByError("请求错误！");
        }
        return absenceService.getAllAbsences(idu,teacher.getIsteacher());
    }
    @RequestMapping(value = "/updateAbsenceState.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateAbsenceState(int id,int pass,String messages){
        if(pass!=0&&pass!=1){
            return ServerResponse.createByError("回复错误！");
        }
        return absenceService.updateAbsenceState(id,pass,messages);
    }
}
