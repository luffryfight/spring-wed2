package com.kobe.dao;

import com.kobe.pojo.Absence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作用：
 * 2020/12/27
 */
@Repository
@Mapper
public interface AbsenceMapper {
    //学生发起请假
    void insertOne(Absence absence);
    //老师查看所有请假
    List<Absence> getAllAbsencesByTeacher(int idu);
    //学生查看所有请假
    List<Absence> getAllAbsences(int idU);
    //老师审批
    void updateAbsenceState(int id,int pass,String messages);
    //通过Id查询
    Absence selectById(int id);
}
