package com.kobe.dao;

import com.kobe.vo.InfoHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 作用：
 * 2020/12/26
 */
@Mapper
@Repository
public interface InfoHistoryMapper {
    //创建记录
    int createInfoHistory(InfoHistory history);
    //学生获取通知记录
    List<Map> selectByStu(int ids);
    //获取指定签到记录
    InfoHistory selectInfoById(int idi);
    //获取最新的id
    int getLastNewId();
    //获取签到通知记录 idu 用户id
    List<Map> selectInfohistory(int idu);
}
