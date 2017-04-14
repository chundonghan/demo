package com.demo.service.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.dao.DaoSupport;
import com.demo.entity.PageData;
import com.demo.service.user.UserService;
@Service(value="userService")
public class UserServiceImpl implements UserService{
	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	@Value("${jdbc.url}")
    private String mysql_url;
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getUserList(PageData pd) {
		System.out.println(mysql_url);
		try {
			return (List<Map<String, Object>>) daoSupport.findForList("UserMapper.getUserList", pd);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public int insertGen(PageData pd)
    {
        try {
           return (int) daoSupport.save("UserMapper.insertGen", pd);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
       
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> login(PageData pd)
    {
        try {
            return (Map<String, Object>) daoSupport.findForObject("UserMapper.login", pd);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
    }

    @Override
    public String getLoginToken(int id)
    {
        try {
            return (String) daoSupport.findForObject("UserMapper.getLoginToken", id);
         } catch (Exception e) {
             e.printStackTrace();
             return "";
         }
    }

    @Override
    public int flashLoginToken(Map<String, Object> map)
    {
        try {
             daoSupport.update("UserMapper.flashLoginToken", map);
             return 1;
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
    }

    @Override
    public int flashLoginToken2(PageData pd)
    {
        try {
            daoSupport.save("UserMapper.flashLoginToken2", pd);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int updateToken(Map<String, Object> map)  throws Exception
    {
        daoSupport.update("UserMapper.flashLoginToken", map);
        int i =1/0;
        return 1;
    }
	
	
}
