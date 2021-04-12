package com.kobe.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 作用：
 * 2020/12/10
 */
@Repository
@Mapper
public interface MessageMapper {
    @Select("select * from sms_message where phone=#{phone}")
    Map selectSMSDataByPhone(String phone);

    @Update("update sms_message set sms_Code = #{smsCode} where phone=#{phone}")
    int updateSMSDataByPhone(Map smsMap);

    @Insert("insert into sms_message (phone,sms_code) values (#{phone},#{smscode})")
    int insert(Map smsMap);


}
