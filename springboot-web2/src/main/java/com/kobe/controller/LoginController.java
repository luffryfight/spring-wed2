package com.kobe.controller;

import com.kobe.common.ServerResponse;
import com.kobe.service.UserListService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 作用：
 * 2020/11/11
 */
@Controller
public class LoginController {
    @Autowired
    private UserListService userListService;//这里是注入d


    @RequestMapping(value = "/haohao")
    public String haohao(){
        return "haohao";
    }
    /**
     *作用:  实现登录功能
     *@date 2020/11/11 21:01
     */

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(
            @RequestParam("account") String account,
            @RequestParam("password")String password,
            HttpSession session) {
        ServerResponse serverResponse;

        try {
            serverResponse = userListService.login(account,password);
            session.setAttribute("Teacher",userListService.isTeacher(account));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ServerResponse.createByErrorMsg("用户名或密码错误！");
        }
        return serverResponse;
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String login2(
            @RequestParam("account") String account,
            @RequestParam("password")String password,
            HttpSession session) {
        ServerResponse serverResponse;
        try {
            serverResponse = userListService.login(account,password);
            if(serverResponse.getStatus()==1){
                return serverResponse.getMsg();
            }
            session.setAttribute("Teacher",userListService.isTeacher(account));
            session.setAttribute("account",account);

        } catch (Exception e) {
            System.out.println(e.toString());
            return "未知错误";
        }
        return "登录成功";
    }

    /**
     * qq登录 发送请求到腾讯服务器
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/qqLogin")
    public void qqLogin(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回调方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = {"/connect","/constant","/index"})
    public void connect(HttpServletRequest request, HttpServletResponse response)throws Exception{

        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();
        out.println("欢迎你，代号为 " + 1 + " 的用户!");
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken   = null,
                    openID        = null;
            long tokenExpireIn = 0L;


            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                out.println("欢迎你，代号为 " + openID + " 的用户!");
                request.getSession().setAttribute("demo_openid", openID);



                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                out.println("<br/>");
                if (userInfoBean.getRet() == 0) {
                    out.println(userInfoBean.getNickname() + "<br/>");
                    out.println(userInfoBean.getGender() + "<br/>");

                    out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "><br/>");
                    out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "><br/>");
                    out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "><br/>");
                } else {
                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                }
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }
}
