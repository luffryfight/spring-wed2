package com.kobe.service.Impl;

import com.kobe.common.ServerResponse;
import com.kobe.dao.CurriculumMapper;
import com.kobe.dao.MessageMapper;
import com.kobe.dao.UserListMapper;
import com.kobe.pojo.Curriculum;
import com.kobe.pojo.User;
import com.kobe.service.UserListService;
import com.kobe.util.JWTUtil;
import com.kobe.util.MD5Util;
import com.kobe.vo.Teacher;
import com.kobe.vo.TeacherToStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用：
 * 2020/11/13
 */
@Service

public class UserListServiceImpl implements UserListService {
    @Autowired
    public MessageMapper messageMapper;
    @Autowired
    private UserListMapper userListMapper;
    @Autowired
    private MessageServiceImpl messageServiceImpl;
    @Autowired
    private CurriculumMapper curriculumMapper;

    public List<User> getUserList(){
        return userListMapper.getUserList();
    }

    public ServerResponse addUser(User user){
        List<User> users= userListMapper.getUserList();
        for (int i = 0; i < users.size(); i++) {//这样其实遍历了两边，查询一遍，这里一遍
            User temp=users.get(i);
            if (temp.getPhone().equals(user.getPhone())){
                return ServerResponse.createByError("手机号已存在！");
            }
            if (temp.getAccount().equals(user.getAccount())){
                return ServerResponse.createByError("帐号已存在");
            }
        }
        userListMapper.addUser(user);
        return ServerResponse.createBySuccessMsg("初步注册成功！");
    }

    @Override
    public ServerResponse<List<TeacherToStudent>> getStudents(int idt) {
        List<TeacherToStudent> students = userListMapper.getStudents(idt);
        if (students==null&&students.size()==0){
            return ServerResponse.createByErrorMsg("学生列表为0");
        }
        return ServerResponse.createBySuccess(students);
    }

    @Override
    public Teacher isTeacher(String account) {
        return userListMapper.isTeacher(account);
    }

    @Override
    public ServerResponse login(String account, String password) {
        //这里要密码加密
        //String MD5Password=MD5Util.MD5Util.MD5EncodeUtf8(password);
        ServerResponse serverResponse;
        String MD5Password=MD5Util.MD5EncodeUtf8(password);
        if (!(serverResponse=checkAccount(account,MD5Password)).isSuccess()){
            return serverResponse;
        }
        Teacher teacher=userListMapper.isTeacher(account);
        teacher.setPassword(MD5Password);
        //这里只返回teacher，因为不需要过多的信息

        return addToken(teacher);
    }

    @Override
    public ServerResponse reset_password(Teacher teacher,
                                         String newPassword,
                                         String msgCode,
                                         String password,
                                         HttpSession session) {
        String phone = userListMapper.getPhone(teacher.getId());
        if (phone==null||phone.equals("")){
            return ServerResponse.createByError("手机号为空");
        }
        String MdPassword = MD5Util.MD5EncodeUtf8(password);
        if (!teacher.getPassword().equals(MdPassword)){
            return ServerResponse.createByError("原密码错误！");
        }
        if (!messageServiceImpl.checkMsg(phone,msgCode).isSuccess()){
            return ServerResponse.createByError("验证码错误！");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("phone",phone);
        res.put("smsCode","");
        if (userListMapper.reset_password(teacher.getId(),
                MD5Util.MD5EncodeUtf8(newPassword))>0){
            //设置成功后，验证码赋值为空
            messageMapper.updateSMSDataByPhone(res);
            //然后取消session
            session.removeAttribute("Teacher");
            return ServerResponse.createBySuccessMsg("修改成功，请重新登录");
        }

        return ServerResponse.createByError("修改失败！");
    }

    @Override
    public ServerResponse update(User user) {
        userListMapper.update(user);
        return ServerResponse.createBySuccess("修改成功！");
    }

    @Override
    public ServerResponse selectBaseInfo(int idu) {
        User user = userListMapper.selectBaseInfo(idu);
        List<Curriculum> curriculums=curriculumMapper.getCurriculums(idu);
        Map map=new HashMap();
        map.put("user",user);
        map.put("curriculums",curriculums);
        if (user==null||curriculums==null||curriculums.size()==0){
            return  ServerResponse.createByError("查询失败！");
        }
        return ServerResponse.createBySuccess(map);
    }

    public ServerResponse checkAccount(String account, String password){
        Teacher teacher= userListMapper.isTeacher(account);
        if (!StringUtils.isEmpty(account) ){
            String MD5Password=MD5Util.MD5EncodeUtf8(password);
            if ( MD5Util.MD5EncodeUtf8(teacher.getPassword()).equals(MD5Password)){
                return ServerResponse.createBySuccessMsg("登录验证通过！");
            }
        }else{
            return ServerResponse.createByError("帐号为空！");
        }
        return ServerResponse.createByError("用户名或者密码错误！");
    }
    private ServerResponse addToken(Teacher teacher) {
        if(teacher != null)
        {
            String token = null;
            try {
                token = JWTUtil.createToken(teacher.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map map = new HashMap(4);
            map.put("user", teacher);
            map.put("token", token);
            return ServerResponse.createBySuccess("登录成功",map);
        }else {
            return ServerResponse.createByErrorMsg("登录失败");
        }
    }
}
