package com.kobe.controller;

import com.aliyuncs.utils.StringUtils;
import com.kobe.common.ServerResponse;
import com.kobe.service.Impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作用：
 * 2020/12/10
 */

@Controller
public class MessageController {

    @Autowired
    public MessageServiceImpl messageServiceImpl;
    /**
     *作用: 获取短信验证码
     *@date 2020/12/10 20:16
     */
    @RequestMapping(value = "/get_msgcode.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getSMSMessage(String phone){
        if (phone==null||phone==""){
            return ServerResponse.createByError("手机号为空");
        }
        return messageServiceImpl.getSMSMessage(phone);
    }
    /**
     *作用: 校验验证码
     *@date 2020/12/10 20:16
     */
    @RequestMapping(value = "/checkMsg.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkMsg(String phone,String smsCode){
        if (StringUtils.isEmpty(phone)|| StringUtils.isEmpty(smsCode)){
            return ServerResponse.createByError("验证错误！");
        }
        return messageServiceImpl.checkMsg(phone,smsCode);
    }
}
