package com.kobe.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kobe.pojo.User;

import javax.websocket.Session;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author: shuo
 * @date: 2019/05/16
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(smt);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }
    public static String serialize(Object object) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(object);
        System.out.println(json);
        return json;

    }

    public static Session sessionUnserialize(String json) throws IOException {
        Session session = objectMapper.readValue(json, Session.class);
        return session;

    }

    public static void main(String[] args) {
        //测试Json转换
        User user=new User();
        user.setAccount("123");

        try {
            System.out.println(JsonUtil.serialize(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
