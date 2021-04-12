package com.kobe.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kobe.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 作用：
 * 2020/12/10
 */
@Component
public class MessageUtils {
    @Autowired
    private static MessageMapper messageMapper;

    @Value("${sms.default.connect.timeout}")
    private  String DEFAULT_CONNECT_TIMEOUT;

    @Value("${sms.default.read.timeout}")
    private String DEFAULT_READ_TIMEOUT;

    @Value("${sms.timeout}")
    private  String SMS_TIMEOUT;

    @Value("${sms.product}")
    private  String SMS_PRODUCT;

    @Value("${sms.domain}")
    private  String SMS_DOMAIN;

    @Value("${sms.access.key.id}")
    private  String SMS_ACCESSKETID;

    @Value("${sms.access.key.secret}")
    private  String SMS_ACCESSKEYSECRET;

    @Value("${sms.template.code}")
    private  String TEMPLATE_CODE;

    private static String code;

    //获取验证码
    public  Map getPhoneMsg(String phone){
        //设置超时时间
        System.setProperty(DEFAULT_CONNECT_TIMEOUT,SMS_TIMEOUT);
        System.setProperty(DEFAULT_READ_TIMEOUT,SMS_TIMEOUT);
        //初始化asclient
        final String accessKeyId=SMS_ACCESSKETID;
        final String accessKeySecret=SMS_ACCESSKEYSECRET;

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        Map map = new HashMap();

        IAcsClient client = new DefaultAcsClient(profile);
        code=vcode();

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "我在校园签到");
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);


        try {
            String code=vcode();
            request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
            CommonResponse response = client.getCommonResponse(request);
            String status=response.getHttpResponse().getReasonPhrase();
            map.put("status",status );
            if (status!=null&&status.equals("OK")) {
                // 请求成功
                map.put("msg", code);
            } else {
                //如果验证码出错，会输出错误码告诉你具体原因
                map.put("msg", response.getData().indexOf("Message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "FAIL");
            map.put("msg", "获取短信验证码失败");
        }
        return map;
    }

    //生成六位随机验证码
    public static String vcode(){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((int) (Math.random() * 9));
        }
        return sb.toString();
    }


    public static void main(String[] args) throws JsonProcessingException {
        code=vcode();
        String jo = JSONObject.toJSONString(new String(code));
        System.out.println(jo);
    }
}
