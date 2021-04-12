package com.kobe.controller;

import com.kobe.common.ServerResponse;
import com.kobe.dao.SignMapper;
import com.kobe.pojo.Curriculum;
import com.kobe.service.CurriculumService;
import com.kobe.service.MiddleService;
import com.kobe.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 作用：
 * 2020/11/24
 */
@Controller
public class CurriculumController {
    @Autowired
    private CurriculumService curriculumService;//这里是注入dao

    @Autowired
    private SignMapper signService;
    @Autowired
    private MiddleService middleService;
    @GetMapping("/addCurriculumInfo")
    public String addaddCurriculum(){
        return "user/addCurriculum.html";
    }

    /*
     * 添加课程后，创建一个连接，然后跳转到一个提示页面，老师把其中的连接发给学生
     * 请求 课程的信息、教师id
     * */
    @RequestMapping("/add_Curriculum.do")
    @ResponseBody
    public ServerResponse addCurriculum(@RequestBody Curriculum curriculum, HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher.getIsteacher().equals("no")){
            return ServerResponse.createByError("你不是教师无法创建课程！");
        }
        System.out.println(curriculum.getClassname());
        curriculum.setTeacher(teacher.getRealname());
        return curriculumService.addCurriculum(curriculum);
    }

    /*
    * 学生确认加入课程页面
    * 展示课程信息，点击确认后在后台添加课程,然后返回首页
    * */
    @RequestMapping("/curriculumInfo/{idc}")
    @ResponseBody
    public ServerResponse curriculumInfo(@PathVariable("idc")int idc
            ,HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher.getIsteacher().equals("yes")){
            return ServerResponse.createByError("你不能加入其他其他老师的课程！");
        }
        session.setAttribute("classId",idc);
        Curriculum curriculum = curriculumService.curriculumInfo(idc);
        if (curriculum==null){
            return ServerResponse.createByError("没有找到该课程！");
        }
        return ServerResponse.createBySuccess("请确认课程信息",curriculum);
    }

    //同时需要创建对应的签到表，不用添加签到信息，当老师发起签到后才台添加默认的
    //添加middle & sign信息->-1表示没上过课
    @RequestMapping("/joinCurriculum/{idc}")
    @ResponseBody
    public ServerResponse joinCurriculum(@PathVariable("idc")int idc,HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        int idu = teacher.getId();//用户id

        String info=idc+","+idu;
        if (signService.isAbsent(info)>0){//课程是否存在
            return ServerResponse.createByError("重复加入该课程！");
        }else{
            signService.addSign(info);
        }
        return curriculumService.joinCurriculum(idu,idc);
    }

    /**
    * 学生查看课程列表
    * */
    @RequestMapping(value = "/get_Curriculums.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCurriculums(HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher.getIsteacher().equals("yes")){
            return ServerResponse.createByError("学生才能查看！");
        }
        return curriculumService.getCurriculums(teacher.getId());//必须时是学生的id
    }


    /**
     * 教师查看课程列表
     * */
    @RequestMapping(value = "/get_CurriculumsTea.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCurriculumsTea(HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher.getIsteacher().equals("no")){
            return ServerResponse.createByError("教师才能查看！");
        }
        return curriculumService.getCurriculums(teacher.getId());
    }
}