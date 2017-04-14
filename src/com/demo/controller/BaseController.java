package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.demo.common.util.PageUtil;
import com.demo.common.util.UuidUtil;
import com.demo.entity.PageData;

/**
 * 
 * @see 控制类基类
 * @time 2016年9月30日 上午11:30:21
 * @author admin
 *
 */
public class BaseController
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** 错误视图 */
    protected static final String ERROR_PAGE = "";

    /** 得到PageData */
    public PageData getPageData()
    {
        return new PageData(this.getRequest());
    }

    /** 得到ModelAndView */
    public ModelAndView getModelAndView()
    {
        return new ModelAndView();
    }

    /** 得到request对象 */
    public HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return request;
    }

    /** 得到32位的uuid */
    public String get32UUID()
    {
        return UuidUtil.get32UUID();
    }

    /** 得到分页列表的信息  */
    public PageUtil getPage(int rowCount, String defaultPageSize, String currentPage)
    {
        return new PageUtil(rowCount, defaultPageSize, currentPage);
    }

}
