package com.demo.service.member;

import java.util.Map;


public interface MemberService {
	Map<String,Object> memberLogin(Map<String,Object> params) throws Exception;
	void updateToken(Map<String,Object> params) throws Exception;
	Map<String,Object> authToken(String account) throws Exception;
	void updateTokenExpire(Map<String, Object> params) throws Exception;
}
