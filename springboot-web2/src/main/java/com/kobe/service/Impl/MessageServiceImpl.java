package com.kobe.service.Impl;

import com.kobe.common.ServerResponse;
import com.kobe.dao.MessageMapper;
import com.kobe.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 作用：
 * 2020/12/10
 */
@Service
public class MessageServiceImpl {
    @Autowired
    public MessageMapper messageMapper;

    @Autowired
    public MessageUtils messageUtils;

    public ServerResponse getSMSMessage(String phone){
        if (phone==null||phone.equals("")){
            return ServerResponse.createByError("手机号为空");
        }
        Map  map=messageMapper.selectSMSDataByPhone(phone);
        if (map!=null){
            String sms_code = (String) map.get("sms_code");
            if (sms_code!=null&&!"".equals(sms_code)){//上次的验证码还没使用
                return ServerResponse.createByError("请稍后再获取验证码！");

            }
        }

        //用于返回的集合
        Map<String, Object> res = new HashMap<>();
        //远端发送验证码
        Map smsMap = messageUtils.getPhoneMsg(phone);
        if ("OK".equals(smsMap.get("status"))){//发送成功
            //查询数据库中该手机的信息
            Map data = messageMapper.selectSMSDataByPhone(phone);
            res.put("phone",phone);
            res.put("smsCode",smsMap.get("msg"));
            /*后面学了redis 可以再回来修改*/
            if (data!=null){//存在该手机的信息
                messageMapper.updateSMSDataByPhone(res);
            }else{//不存在就插入
                messageMapper.insert(res);
            }
            return ServerResponse.createBySuccess(res);
        }
        return ServerResponse.createByError(res);
    }

    //校验验证码
    public ServerResponse checkMsg(String phone,String smsCode){
        Map data = messageMapper.selectSMSDataByPhone(phone);
        if (data==null){
            return ServerResponse.createByError("该手机号未发送验证码");
        }
        String code= (String) data.get("sms_code");
        if (!smsCode.equals(code)){
            return ServerResponse.createByError("验证码错误！");
        }
        //赋值为空
        Map<String, Object> res = new HashMap<>();
        res.put("phone",phone);
        res.put("smsCode",smsCode);
        messageMapper.updateSMSDataByPhone(res);
        return ServerResponse.createBySuccessMsg("验证通过！");
    }
}
