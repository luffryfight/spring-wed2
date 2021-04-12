package com.kobe.service;

import com.kobe.common.ServerResponse;
import com.kobe.pojo.Absence;

/**
 * @author 86137
 * @date 2021/3/7 19:38
 */
public interface AbsenceService {
    //学生发起请假
    ServerResponse insertOne(Absence absence);
    //ServerResponse getAllAbsencesByTeacher(int idu);
    //老师查看所有请假
    ServerResponse getAllAbsences(int idt,String isTeacher);
    //老师审批
    ServerResponse updateAbsenceState(int id,int pass,String messages);
}
