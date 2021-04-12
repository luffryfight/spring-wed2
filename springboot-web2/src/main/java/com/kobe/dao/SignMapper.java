package com.kobe.dao;

import com.kobe.pojo.Sign;
import com.kobe.vo.SignInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 作用：
 * 2020/11/25
 */
@Repository
@Mapper
public interface SignMapper {
    //添加
    void addSign(String origin);
    //返回id
    int getNewSignId(String origin);
    // 查询一个课程下对应学生的签到信息和签到表id,和学生id
    List<SignInfo> getSignInfo(int idc);
    //默认更新签到信息
    void updateSignFromTeacher(@Param("ids") int ids, @Param("origin")String origin);
    //设置sign（密码）
    void updateSignKey(@Param("idc") int idc, @Param("signed")String signed);
    //获取签到密钥
    Sign getSigned(@Param("idc")int idc, @Param("idu")int idu);
    //是否存在。。
    int isAbsent(String info);
    void updateSignedForOne(int ids, String signed);

    /*//学生获取所有签到
    List<Map> getStuSign(int idu);
    //老师获取所有签到
    List<Map> getTeaSign(int idu);*/
    //上面上个一样实现
    List<Map> getAllSigns(int idu);

}
