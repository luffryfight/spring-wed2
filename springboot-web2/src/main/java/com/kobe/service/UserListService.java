package com.kobe.service;

import com.kobe.common.ServerResponse;
import com.kobe.pojo.User;
import com.kobe.vo.Teacher;
import com.kobe.vo.TeacherToStudent;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 作用：
 * 2020/11/22
 */
public interface UserListService {
    List<User> getUserList();

    ServerResponse addUser(User user);

    ServerResponse<List<TeacherToStudent>> getStudents(int idt);

    Teacher isTeacher(@Param("account") String account);

    ServerResponse login(String account, String password);

    ServerResponse reset_password(Teacher teacher, String newPassword,
                                  String msgCode, String password, HttpSession session);

    ServerResponse update(User user);

    ServerResponse selectBaseInfo(int idu);
}
