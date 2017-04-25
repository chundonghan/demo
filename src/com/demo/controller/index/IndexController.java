package com.demo.controller.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.controller.BaseController;
import com.demo.entity.PageData;
import com.demo.interceptors.Token;
import com.demo.service.user.UserService;

@Controller
@RequestMapping("/v1")
public class IndexController extends BaseController
{
    @Autowired
    private UserService userService;

    @Token(save = true)
    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value = "condition", required = false)
    String condition)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd.put("SELECT_PRIV", condition);
        List<Map<String, Object>> userList = userService.getUserList(pd);
        mv.addObject("userList", userList);
        mv.setViewName("index");
        System.out.println(request.getAttribute("token"));
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("login_token", "123");
        map.put("login_password", UUID.randomUUID().toString());
        try
        {
            userService.updateToken(map);
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
        return mv;
    }

    @Token(remove = true)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        
    }

    @RequestMapping(value = "/gen", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String insertGen(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        /*PageData pd = getPageData();
        // pd.put("id", null);
        pd.put("column_name", "column_name");
        pd.put("column_copy", "column_copy");
        int gen = userService.insertGen(pd);
        System.out.println(gen);
        if (gen == 1)
            System.out.println(pd.get("id"));*/
        return "{\"message\":\"login\"}";
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    public void token(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/restTemplate", method = RequestMethod.GET)
    @ResponseBody
    public void restTemplate(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        //form.add("key", "value");
        Map<String, Object> map = new HashMap<>();
        try{
            map= restTemplate.postForObject("http://localhost:8080/Demo/v1/gen", form, Map.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(map);
    }
    
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> userList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        List<Map<String, Object>> userList = userService.getUserList(null);
        return userList;
    }
}
