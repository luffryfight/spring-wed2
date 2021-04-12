package com.kobe.controller;

import com.kobe.common.ServerResponse;
import com.kobe.common.SignState;
import com.kobe.dao.MiddlerMapper;
import com.kobe.service.SignService;
import com.kobe.vo.InfoHistory;
import com.kobe.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 作用：
 * 2020/11/25
 */
@Controller
public class SignController {
    @Autowired
    SignService signService;

    @Autowired
    MiddlerMapper middlerMapper;

    /**
    * 选择签到方式之后，给每个学生的sign 赋值密码，然后学生签到时比对密码
    * */
    @RequestMapping(value = "/selectSignWay.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectSignWay(InfoHistory history, HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("Teacher");
        history.setIdt(teacher.getId());
        history.setState(SignState.SUNFINISH);
        history.setName(teacher.getRealname());
        history.setImg(teacher.getImg());
        if (history==null){
            return ServerResponse.createByError("签到信息错误！");
        }
        return signService.selectSignWay(history, (Teacher) session.getAttribute("Teacher"));
    }




    /**
     * 学生查看自己的考勤信息，未完成
     * */
    @RequestMapping("/selecByStudent.do")
    @ResponseBody
    public ServerResponse selecByStudent(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("Teacher");
        return ServerResponse.createByError("时间格式错误！");
    }

    /**
     * （查看签到通知）学生查看签到记录  目前先使用getAllSigns查看所有课程的全部签到
     * */
    @RequestMapping("/selecInfoHistory.do")
    @ResponseBody
    public ServerResponse selecInfoHistory(HttpSession session){
        Teacher teacher = (Teacher)session.getAttribute("Teacher");
        return signService.selectInfohistory(teacher.getId());
    }

    /**
     * 教师查看对应课程的考勤信息，未完成
     * */
    @RequestMapping("/selecByTeacher.do")
    @ResponseBody
    public ServerResponse selecByTeacher(InfoHistory history,HttpSession session){
        return ServerResponse.createByError();
    }




    /**
     *      学生签到  测试：密码签到
     *      参数 ： idi Infohistory id
     *      参数 ： time 签到的时间
     * */
    @RequestMapping(value = "/signWay_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signWay_password(String time ,int idi,String password, HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher==null){
            return ServerResponse.createByError("请求信息错误！");
        }
        //先查询签到的起始和结束时间
        if (time==null){
            return ServerResponse.createByError("签到时间错误！");
        }
        if (!time.contains(":")){
            return ServerResponse.createByError("时间格式错误！");
        }
        return signService.signWay_password(idi,password,teacher.getId(),time);
    }
    /**
     *      学生签到  定位签到
     *      参数 ： idi Infohistory id
     *      参数 ： time 签到的时间
     * */
    @RequestMapping(value = "/signWay_location.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signWay_location(String time,int idi,
                                           String Lat,String Ing, HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher==null){
            return ServerResponse.createByError("登录状态错误！");
        }
        //先查询签到的起始和结束时间
        if (time==null){
            return ServerResponse.createByError("签到时间错误！");
        }
        if (!time.contains(":")||!Lat.contains(".")||!Ing.contains(".")){
            return ServerResponse.createByError("参数格式错误！");
        }
        return signService.signWay_location(time,idi,Lat,Ing,teacher.getId());
    }

    /**
     * 学生和老师    查看所有的课程签到信息
     * */
    @RequestMapping("/getAllSigns")
    @ResponseBody
    public ServerResponse getStuSign(HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher==null){
            return ServerResponse.createByError("用户信息错误！");
        }
        return signService.getAllSigns(teacher.getId());
    }

    /**
     * 学生端获取签到通知  重复的。。，？
     * idc 课程的Id
     * 根据老师idc，可知对应history
     * */
    @RequestMapping("/selectInfo")
    @ResponseBody
    public ServerResponse selectInfo(HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");

        return signService.selectInfo(teacher.getId());
    }

    /**
     * 教师查看自己对应课程下学生的签到信息
     * */
    /*@RequestMapping("/getTeaSign/{idc}")
    @ResponseBody
    public ServerResponse getTeaSign(@PathVariable int idc,HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");

        if (middlerMapper.isAbsentT(teacher.getId())==0){
            return ServerResponse.createByError("无对应列表！");
        }

        return signService.getTeaSign(idc);
    }*/

}
