package com.demo.service.user;

import java.util.List;
import java.util.Map;

import com.demo.entity.PageData;

public interface UserService {
	public List<Map<String,Object>> getUserList(PageData pd);
	int insertGen(PageData pd);
	Map<String, Object> login(PageData pd);
	String getLoginToken(int id);
	int flashLoginToken(Map<String,Object> map);
	int flashLoginToken2(PageData pd);
	
	int updateToken(Map<String,Object> map) throws Exception;
}
