package com.kobe.dao;

import com.kobe.pojo.Curriculum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作用：
 * 2020/11/23
 */
@Repository
@Mapper
public interface CurriculumMapper {
    //添加课程
    void addCurriculum(Curriculum curriculum);
    //学生查询自己的课程
    List<Curriculum> getCurriculums(int idu);
    //查询课程信息
    Curriculum curriculumInfo(int idc);
    //查询刚创建的课程的id
    int getNewCurriculumId(Curriculum curriculum);
    //查询课程是否已经存在
    int getIsAbsent(Curriculum curriculum);
}
