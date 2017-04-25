package com.demo.service.member.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.DaoSupport;
import com.demo.service.member.MemberService;

@Service(value="memberService")
public class MemberServiceImpl implements MemberService{
	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> memberLogin(Map<String, Object> params) throws Exception
    {
        return (Map<String, Object>) daoSupport.findForObject("MemberMapper.memberLogin", params);
    }
    
    @Override
    public void updateToken(Map<String, Object> params) throws Exception
    {
        daoSupport.update("MemberMapper.updateToken", params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> authToken(String account) throws Exception
    {
        return (Map<String, Object>) daoSupport.findForObject("MemberMapper.authToken", account);
    }
    @Override
    public void updateTokenExpire(Map<String, Object> params) throws Exception
    {
        daoSupport.update("MemberMapper.updateTokenExpire", params);
    }
	
}
