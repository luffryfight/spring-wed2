package com.kobe.service;

import com.kobe.common.ServerResponse;
import com.kobe.pojo.Curriculum;

import java.util.List;

/**
 * 作用：
 * 2020/11/23
 */

public interface CurriculumService {


    ServerResponse addCurriculum(Curriculum curriculum);

    //学生查询自己的课程
    ServerResponse getCurriculums(int idu);

    Curriculum curriculumInfo(int idc);

    int getNewCurriculumId(Curriculum curriculum);

    ServerResponse joinCurriculum(int idu,int idc);
}
