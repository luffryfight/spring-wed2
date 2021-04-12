package com.kobe.controller;

import com.kobe.common.ServerResponse;
import com.kobe.pojo.Curriculum;
import com.kobe.pojo.User;
import com.kobe.service.CurriculumService;
import com.kobe.service.UserListService;
import com.kobe.vo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 作用：
 * 2020/11/12
 */
@Controller
public class UserListController {

    @Autowired
    private UserListService userListService;//这里是注入dao
    @Autowired
    private CurriculumService curriculumService;//这里是注入dao

    @RequestMapping("/test")
    @ResponseBody
    public List<Curriculum> testList(Model model, HttpSession session){//ok
        return null;
    }


    @RequestMapping(value = "/getStudents",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudents(HttpSession session){
        Teacher teacher= (Teacher) session.getAttribute("Teacher");
        if (teacher.getIsteacher().equals("no")){
            return ServerResponse.createByError("你不是教师，没有权限查看课程的学生列表！");
        }
        return  userListService.getStudents(teacher.getId());
    }

    /**
    * 登录状态修改密码
    * */
    @RequestMapping(value = "/reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse reset_password(HttpSession session,
                                         String newPassword,
                                         String msgCode,
                                         String password){
        Teacher teacher = (Teacher) session.getAttribute("Teacher");
        if (teacher==null){
            return ServerResponse.createByError("修改密码失败，可能是登录状态异常，请重新登录！");
        }
        if (msgCode==null){
            return ServerResponse.createByError("验证码为空");
        }
        return userListService.reset_password(teacher,newPassword, msgCode,password,session);
    }

    /**
     * 修改个人信息
     * */
    @RequestMapping(value = "/update_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateUserInfo(User user){
        if (user==null){
            return ServerResponse.createByError("修改信息错误!");
        }
        return userListService.update(user);
    }

    /**
     * 获得个人信息
     * */
    @RequestMapping(value = "/select_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectUserInfo(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("Teacher");
        return userListService.selectBaseInfo(teacher.getId());
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user){
        if (user==null||user.getAccount()==null){
            return ServerResponse.createByError("注册信息完整！");
        }
        //添加
        return userListService.addUser(user);
    }

    @RequestMapping(value = "need_login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse needLogin() {
        return ServerResponse.createByErrorMsg("用户未登录");
    }

}
