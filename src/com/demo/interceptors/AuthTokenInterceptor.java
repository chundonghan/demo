package com.demo.interceptors;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.entity.PageData;
import com.demo.service.member.MemberService;
import com.demo.service.user.UserService;

public class AuthTokenInterceptor extends HandlerInterceptorAdapter
{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MemberService memberService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
      HttpSession session = request.getSession();
      
      String account = (String) session.getAttribute("currentUser");
      if(account==null){
          logger.info("{}","请先登录");
          return false;
      }
      Map<String,Object> auth=memberService.authToken(account);
      if(auth==null){
          logger.info("{}","无此账号");
          return false;
      }
      if(auth.get("auth_token").equals(session.getAttribute("auth_token"))){
          long tokenExpire = (System.currentTimeMillis()/1000);
          if((int)auth.get("token_expire")
                  >= tokenExpire){
              Map<String,Object> params = new HashMap<>();
              params.put("account", account);
              params.put("token_expire", tokenExpire+1000);
              memberService.updateTokenExpire(params);
              logger.info("{}","token正常");
              return true;
          }else{
              logger.info("{}","token过期");
              return false;
          }
          
      }else{
          logger.info("{}","token失效");
          return false;
      }
      //return super.preHandle(request, response, handler);
    }
   
   
    
}
