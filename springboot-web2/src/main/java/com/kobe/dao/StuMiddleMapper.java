package com.kobe.dao;

import com.kobe.vo.InfoHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作用：
 * 2020/12/27
 */
@Repository
@Mapper
public interface StuMiddleMapper {
    //创建
    void insert(int idi,int ids,int state);
    //更新状态
    void updateState(int ids,int idi,int State);
    //学生查询自己的考勤信息
    List<InfoHistory> getStuHistory(int idu);
}
