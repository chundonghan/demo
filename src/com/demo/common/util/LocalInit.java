package com.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.service.user.UserService;
@Component
public class LocalInit implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    private UserService userService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent  event)
    {
        if(event.getApplicationContext().getParent() == null&& event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){//root application context 没有parent，他就是老大.  
           //初始化参数 操作数据库初始化信息
           System.out.println(userService.getUserList(null));
           
       } 
       
    }

}
