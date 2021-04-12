package com.kobe.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作用：
 * 2020/12/23
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
    //保存每个MyWebSocket对象
    private static CopyOnWriteArrayList<MyWebSocket> webSockets=new CopyOnWriteArrayList<>();
    //和客户端通信
    private Session session;

    /**
    * 建立连接
    * */
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSockets.add(this);
        System.out.println("加入一人"+",当前人数："+webSockets.size());
        sendMessage("当前人数："+webSockets.size());
    }

    /**
     * 关闭连接
     * */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        System.out.println("离开一人"+",当前人数："+webSockets.size());
    }

    /**
     * 收到客户端消息后
     * */
    @OnMessage
    public void onMessage(String message){
        System.out.println("收到消息："+message);
        //这里做要推送相关的工作
        for (MyWebSocket ch:webSockets){
            ch.sendMessage(message);
        }
    }

    /**
     * 发生错误
     * */
    @OnError
    public void onError(Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    /**
    *  实际发送消息的实现
    * */
    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);//主要方法
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
