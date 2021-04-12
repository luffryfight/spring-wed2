package com.kobe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 作用：
 * 2020/11/25
 */
@Repository
@Mapper
public interface MiddlerMapper {
    //添加对应
    void addMiddle(int idu,int idc,int ids);
    //是否存在
    int isAbsent(int idu,int idc);
    //
    int isAbsentT(int idu);
}
