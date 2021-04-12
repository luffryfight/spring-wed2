package com.kobe.dao;


import com.kobe.common.ServerResponse;
import com.kobe.pojo.User;
import com.kobe.vo.Teacher;
import com.kobe.vo.TeacherToStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作用：
 * 2020/11/13
 */
@Repository
@Mapper
public interface UserListMapper {

    //查询列表
    List<User> getUserList();
    //添加用户
    ServerResponse addUser(User user);

    List<TeacherToStudent> getStudents(int idc);
    //是否是老师
    Teacher isTeacher(@Param("account") String account);
    //登录状态下修改密码
    int reset_password(int idu,String newPassword);
    //获取手机号
    String getPhone(int id);
    //更新信息
    void update(User user);
    //返回学生或老师的基本信息
    User selectBaseInfo(int idu);
}
