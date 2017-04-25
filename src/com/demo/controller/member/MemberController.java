package com.demo.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.controller.BaseController;
import com.demo.service.member.MemberService;
@Controller
@RequestMapping(value="member")
public class MemberController extends BaseController
{
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String memberLogin(@RequestParam(value="account",defaultValue="") String account,
                              @RequestParam(value="password",defaultValue="") String password)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            return "{\"message\":\"password error!\"}";
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            return "{\"message\":\"account error!\"}";
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            return "{\"message\":\"times error!\"}";
        }
        return "{\"message\":\"login\"}";
    }
}
