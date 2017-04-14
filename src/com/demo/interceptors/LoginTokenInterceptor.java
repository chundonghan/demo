package com.demo.interceptors;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.entity.PageData;
import com.demo.service.user.UserService;

public class LoginTokenInterceptor extends HandlerInterceptorAdapter
{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String m_token = request.getParameter("token")==null?"":request.getParameter("token");
        String m_id = request.getParameter("m_id")==null?"":request.getParameter("m_id");
        if("".equals(m_id)){
            String login_name = request.getParameter("login_name");
            String login_password = request.getParameter("login_password");
            PageData pd = new PageData();
            pd.put("login_name", login_name);
            pd.put("login_password", login_password);
            Map<String, Object> loginMap = userService.login(pd);
            if(loginMap == null){
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-type","text/html;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write("{\"message\":\"login_error\"}");
                return false;
            }
            m_token = UUID.randomUUID().toString().replaceAll("-", "");
            pd.put("login_token", m_token);
            userService.flashLoginToken2(pd);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type","text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write("{\"message\":\"login_success\"}");
            return true;
          
        }else{
            int id = 0;
            try{
                id=Integer.parseInt(m_id);
            }catch(Exception e){
                e.printStackTrace();
            }
            String token = userService.getLoginToken(id);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type","text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write("{\"message\":\"token\"}");
            return kindsOfSituation(m_token, token, id);
        }
        
        
    }
   
    
    private boolean kindsOfSituation(String m_token,String token,int id)
    {
        Map<String, Object> map = new HashMap<>();
        //登录的情况
        if("".equals(token)&&"".equals(m_token)){
            
            map.put("id", id);
            map.put("login_token", UUID.randomUUID().toString().replaceAll("-", ""));
            userService.flashLoginToken(map);
            System.out.println("login");
            return true;
        } 
        
        if(m_token.equals(token)){
            System.out.println("pass");
            return true;
        }else{
            System.out.println("inter");
            return false;
        }
        
    }
    
}
